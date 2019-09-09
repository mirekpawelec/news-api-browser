package pl.pawelec.newsbrowser.standalone;

import pl.pawelec.newsbrowser.service.exception.model.ErrorMessageModel;
import pl.pawelec.newsbrowser.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import static pl.pawelec.newsbrowser.service.exception.errorcode.NewsBrowserErrorCode.NB_0001;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:/application.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsBrowserIntegrationTest {

    private static final String COUNTRY = "pl";
    private static final String CATEGORY = "technology";

    private String newsApiUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.newsApiUrl = String.format("http://localhost:%s/news/{country}/{category}", this.port);
    }

    @Test
    public void shouldReturnTheNewsModelAsJsonObject_whenGivenCorrectParameterValues() {
        //given
        //when
        ResponseEntity response = testRestTemplate.getForEntity(newsApiUrl, String.class, COUNTRY, CATEGORY);
        Map<String, Object> returnedNewsModelMap = JsonUtils.parseToMap((String) response.getBody());

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(CollectionUtils.isEmpty(returnedNewsModelMap));
        assertEquals(COUNTRY, returnedNewsModelMap.get("country"));
        assertEquals(CATEGORY, returnedNewsModelMap.get("category"));
        assertFalse(CollectionUtils.isEmpty((List<Object>) returnedNewsModelMap.get("articles")));
    }

    @Test
    public void shouldReturnTheErrorMessageModelAsJsonObject_whenGivenWrongParameterValues() {
        //given
        final String errorSource = "news-browser";
        final String unknownCountry = "unknownCountry";
        final String unknownCategory = "unknownCategory";

        //when
        ResponseEntity response = testRestTemplate.getForEntity(newsApiUrl, ErrorMessageModel.class,
                unknownCountry, unknownCategory);
        ErrorMessageModel returnedErrorMessageModel = (ErrorMessageModel) response.getBody();

        //then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(returnedErrorMessageModel);
        assertNotNull(returnedErrorMessageModel.getErrorId());
        assertNotNull(returnedErrorMessageModel.getOccurrenceDate());
        assertEquals(errorSource, returnedErrorMessageModel.getErrorSource());
        assertEquals(NB_0001.getValue(), returnedErrorMessageModel.getErrorCode());
        assertEquals(String.format(NB_0001.getMessage(), unknownCountry, unknownCategory),
                returnedErrorMessageModel.getErrorMessage());
        assertThat(returnedErrorMessageModel.getParameters(),
                hasItems(containsString(unknownCountry), containsString(unknownCategory)));
    }
}