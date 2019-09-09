package pl.pawelec.newsbrowser.network;

import pl.pawelec.newsbrowser.model.HeadlinesContainerModel;
import org.springframework.http.ResponseEntity;

public interface NewsNetwork {

    ResponseEntity<HeadlinesContainerModel> getTopHeadlinesByCountryAndCategory(String country, String category);
}