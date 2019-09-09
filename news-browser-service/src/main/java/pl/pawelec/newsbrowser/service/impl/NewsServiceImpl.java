package pl.pawelec.newsbrowser.service.impl;

import pl.pawelec.newsbrowser.model.HeadlinesContainerModel;
import pl.pawelec.newsbrowser.model.NewsModel;
import pl.pawelec.newsbrowser.network.NewsNetwork;
import pl.pawelec.newsbrowser.service.NewsService;
import pl.pawelec.newsbrowser.service.assembler.NewsAssembler;
import pl.pawelec.newsbrowser.service.exception.AnyNewsNotFoundException;
import pl.pawelec.newsbrowser.service.exception.NewsApiConnectionException;
import pl.pawelec.newsbrowser.service.exception.NewsFilterKeysIncorrectValueException;
import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.NewsBrowserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;


@Service
public class NewsServiceImpl implements NewsService {

    private final NewsNetwork newsNetwork;
    private final NewsAssembler newsAssembler;

    public NewsServiceImpl(NewsNetwork newsNetwork, NewsAssembler newsAssembler) {
        this.newsNetwork = newsNetwork;
        this.newsAssembler = newsAssembler;
    }

    @Override
    public NewsModel getByCountryAndCategory(String country, String category) {

        if (Objects.isNull(country) || country.isBlank() || Objects.isNull(category) || category.isBlank()) {
            throw new NewsFilterKeysIncorrectValueException();
        }
        ResponseEntity headlinesContainerResponse = newsNetwork.getTopHeadlinesByCountryAndCategory(country, category);
        if (headlinesContainerResponse.getStatusCode() == HttpStatus.OK) {
            HeadlinesContainerModel headlinesContainer = (HeadlinesContainerModel) headlinesContainerResponse.getBody();
            if (CollectionUtils.isEmpty(headlinesContainer.getArticles())) {
                throw new AnyNewsNotFoundException(country, category);
            }
            return newsAssembler.assembleFrom(country, category, headlinesContainer);
        } else if (headlinesContainerResponse.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
            throw new NewsApiConnectionException(String.valueOf(headlinesContainerResponse.getBody()));
        } else {
            throw new NewsBrowserException();
        }
    }
}
