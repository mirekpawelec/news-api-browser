package pl.pawelec.newsbrowser.service;

import pl.pawelec.newsbrowser.model.NewsModel;

public interface NewsService {

    NewsModel getByCountryAndCategory(String country, String category);
}

