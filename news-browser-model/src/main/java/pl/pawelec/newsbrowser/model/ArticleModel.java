package pl.pawelec.newsbrowser.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class ArticleModel extends BaseArticleDataModel {
    private ZonedDateTime date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

    public ArticleModel() {
    }

    private ArticleModel(Builder builder) {
        this.author = builder.author;
        this.title = builder.title;
        this.description = builder.description;
        this.date = builder.date;
        this.sourceName = builder.sourceName;
        this.articleUrl = builder.articleUrl;
        this.imageUrl = builder.imageUrl;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ArticleModel{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", sourceName='" + sourceName + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static class Builder {
        private String author;
        private String title;
        private String description;
        private ZonedDateTime date;
        private String sourceName;
        private String articleUrl;
        private String imageUrl;
        private String content;

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(ZonedDateTime date) {
            this.date = date;
            return this;
        }

        public Builder sourceName(String sourceName) {
            this.sourceName = sourceName;
            return this;
        }

        public Builder articleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ArticleModel build() {
            return new ArticleModel(this);
        }
    }
}
