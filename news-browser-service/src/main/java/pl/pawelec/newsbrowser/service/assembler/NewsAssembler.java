package pl.pawelec.newsbrowser.service.assembler;

import pl.pawelec.newsbrowser.model.ArticleModel;
import pl.pawelec.newsbrowser.model.HeadlinesContainerModel;
import pl.pawelec.newsbrowser.model.NewsModel;
import pl.pawelec.newsbrowser.service.utils.TimeUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class NewsAssembler {

    public NewsModel assembleFrom(String country, String category, HeadlinesContainerModel headlinesContainer) {
        NewsModel newsModel = new NewsModel(country, category);
        newsModel.setArticles(
                headlinesContainer.getArticles().stream()
                        .map(article ->
                                new ArticleModel.Builder()
                                    .author(article.getAuthor())
                                    .title(article.getTitle())
                                    .description(article.getDescription())
                                    .sourceName(article.getSource().getName())
                                    .articleUrl(article.getUrl())
                                    .imageUrl(article.getUrlToImage())
                                    .date(TimeUtils.parseStringToZonedDateTime(article.getPublishedAt()))
                                    .build()
                        )
                        .collect(Collectors.toList())
        );
        return newsModel;
    }
}
