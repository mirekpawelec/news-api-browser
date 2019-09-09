package pl.pawelec.newsbrowser.service;

import pl.pawelec.newsbrowser.model.HeadlinesContainerModel;
import pl.pawelec.newsbrowser.model.NewsModel;
import pl.pawelec.newsbrowser.network.NewsNetwork;
import pl.pawelec.newsbrowser.network.config.NetworkConfiguration;
import pl.pawelec.newsbrowser.service.assembler.NewsAssembler;
import pl.pawelec.newsbrowser.service.config.ServiceConfiguration;
import pl.pawelec.newsbrowser.service.exception.AnyNewsNotFoundException;
import pl.pawelec.newsbrowser.service.exception.NewsApiConnectionException;
import pl.pawelec.newsbrowser.service.exception.NewsFilterKeysIncorrectValueException;
import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.NewsBrowserException;
import pl.pawelec.newsbrowser.service.impl.NewsServiceImpl;
import pl.pawelec.newsbrowser.utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfiguration.class, NetworkConfiguration.class})
@TestPropertySource(locations = "classpath:/application.yml")
public class NewsServiceTest {

    private static final String COUNTRY = "pl";
    private static final String CATEGORY = "technology";
    private static final String WRONG_URL = "https://newsapi.org/v2/top-headliness";

    private ObjectMapper mapper = new ObjectMapper();
    private NewsService testedService;

    @Mock
    private NewsNetwork newsNetwork;
    @Autowired
    private NewsAssembler newsAssembler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testedService = new NewsServiceImpl(newsNetwork, newsAssembler);
    }

    @Test
    public void shouldGetTheNewsModel_whenGivenArgumentsAreCorrect() throws Exception {
        //given
        HeadlinesContainerModel headlinesContainer = mapper.readValue(FileUtils.readFile("TopHeadlines.json"),
                HeadlinesContainerModel.class);
        when(newsNetwork.getTopHeadlinesByCountryAndCategory(COUNTRY, CATEGORY))
                .thenReturn(new ResponseEntity(headlinesContainer, HttpStatus.OK));

        //when
        NewsModel resultEntity = testedService.getByCountryAndCategory(COUNTRY, CATEGORY);

        //then
        assertNotNull(resultEntity);
        assertEquals(COUNTRY, resultEntity.getCountry());
        assertEquals(CATEGORY, resultEntity.getCategory());
        assertEquals(3, resultEntity.getArticles().size());
    }

    private static Stream<Arguments> prepareWrongArguments() {
        return Stream.of(
                Arguments.of(COUNTRY, null),
                Arguments.of(null, CATEGORY),
                Arguments.of(COUNTRY, " "),
                Arguments.of(" ", CATEGORY)
        );
    }

    @ParameterizedTest
    @MethodSource("prepareWrongArguments")
    public void shouldThrowTheNewsFilterKeysIncorrectValueException_whenOneOfTheArgumentsButAllAreWrong(
            String country, String category) {
        //given
        final String expectedErrorMessage = "The filter keys (country, category) are required.";

        //when
        //then
        NewsFilterKeysIncorrectValueException exception = assertThrows(NewsFilterKeysIncorrectValueException.class,
                () -> testedService.getByCountryAndCategory(country, category));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void shouldThrowTheAnyNewsNotFoundException_whenGiveUnknownParameterValues() {
        //given
        final String countryUnknown = COUNTRY + "unknown";
        final String categoryUnknown = CATEGORY + "unknown";
        final HeadlinesContainerModel headlinesContainer = new HeadlinesContainerModel("ok", 0, Collections.emptyList());
        final String expectedErrorMessage = String.format("Any news not found (country= %s, category= %s).",
                countryUnknown, categoryUnknown);
        when(newsNetwork.getTopHeadlinesByCountryAndCategory(countryUnknown, categoryUnknown))
                .thenReturn(new ResponseEntity(headlinesContainer, HttpStatus.OK));

        //when
        //then
        AnyNewsNotFoundException exception = assertThrows(AnyNewsNotFoundException.class,
                () -> testedService.getByCountryAndCategory(countryUnknown, categoryUnknown));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void shouldThrowTheNewsApiConnectionException_whenHttpStatusResponseCodeEquals503() {
        //given
        final String expectedErrorMessage = String.format("Connection with server can't be estabilished (url= %s).", WRONG_URL);
        when(newsNetwork.getTopHeadlinesByCountryAndCategory(COUNTRY, CATEGORY))
                .thenReturn(new ResponseEntity(WRONG_URL, HttpStatus.SERVICE_UNAVAILABLE));

        //when
        NewsApiConnectionException connectionException = assertThrows(NewsApiConnectionException.class,
                () -> testedService.getByCountryAndCategory(COUNTRY, CATEGORY));

        //then
        assertEquals(expectedErrorMessage, connectionException.getMessage());
    }

    @Test
    public void shouldThrowTheNewsBrowserException_whenAnyConnectionErrorOccured() {
        //given
        when(newsNetwork.getTopHeadlinesByCountryAndCategory(COUNTRY, CATEGORY))
                .thenReturn(new ResponseEntity(WRONG_URL, HttpStatus.INTERNAL_SERVER_ERROR));

        //when
        //then
        NewsBrowserException internalException = assertThrows(NewsBrowserException.class,
                () -> testedService.getByCountryAndCategory(COUNTRY, CATEGORY));
    }
}
