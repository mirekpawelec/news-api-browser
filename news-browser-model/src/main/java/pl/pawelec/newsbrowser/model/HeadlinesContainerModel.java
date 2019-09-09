package pl.pawelec.newsbrowser.model;

import java.util.ArrayList;
import java.util.List;

public class HeadlinesContainerModel {
    private String status;
    private int totalResults = 0;
    private List<HeadlineModel> articles = new ArrayList<>();

    public HeadlinesContainerModel() {
    }

    public HeadlinesContainerModel(String status, int totalResults, List<HeadlineModel> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<HeadlineModel> getArticles() {
        return articles;
    }

    public void setArticles(List<HeadlineModel> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "HeadlinesContainerModel{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}
