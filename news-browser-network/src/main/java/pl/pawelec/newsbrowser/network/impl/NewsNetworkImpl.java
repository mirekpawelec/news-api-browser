package pl.pawelec.newsbrowser.network.impl;

import pl.pawelec.newsbrowser.model.HeadlinesContainerModel;
import pl.pawelec.newsbrowser.network.NewsNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class NewsNetworkImpl implements NewsNetwork {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsNetworkImpl.class);
    private static final String COUNTRY_QUERYPARAM_NAME = "country";
    private static final String CATEGORY_QUERYPARAM_NAME = "category";
    private static final String APIKEY_QUERYPARAM_NAME = "apiKey";

    @Value("${network.api-key}")
    private String apiKey;

    @Value("${network.api.get-top-headlines-url}")
    private String topHeadlinesUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity getTopHeadlinesByCountryAndCategory(String country, String category) {
        final String completeUrl = UriComponentsBuilder.fromHttpUrl(topHeadlinesUrl)
                .queryParam(COUNTRY_QUERYPARAM_NAME, country)
                .queryParam(CATEGORY_QUERYPARAM_NAME, category)
                .queryParam(APIKEY_QUERYPARAM_NAME, apiKey)
                .toUriString();

        LOGGER.info("completeUrl= {}", completeUrl);

        try {
            return restTemplate.getForEntity(completeUrl, HeadlinesContainerModel.class);
        } catch (HttpStatusCodeException e) {
            LOGGER.info(e.getMessage(), e);
            return new ResponseEntity(completeUrl, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity(completeUrl, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
