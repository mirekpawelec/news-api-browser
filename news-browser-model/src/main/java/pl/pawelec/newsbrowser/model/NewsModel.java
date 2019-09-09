package pl.pawelec.newsbrowser.model;

import java.util.ArrayList;
import java.util.List;

public class NewsModel {
    private String country;
    private String category;
    private List<ArticleModel> articles = new ArrayList<>();

    public NewsModel() {
    }

    public NewsModel(String country, String category) {
        this.country = country;
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", articles=" + articles +
                '}';
    }
}
