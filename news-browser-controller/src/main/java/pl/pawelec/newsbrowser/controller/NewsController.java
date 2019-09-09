package pl.pawelec.newsbrowser.controller;

import pl.pawelec.newsbrowser.model.NewsModel;
import pl.pawelec.newsbrowser.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Api(value = "/news", produces = "application/json")
@RequestMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @ApiOperation(value = "", notes = "Gets the NewsModel with article list\n", response = NewsModel.class)
    @GetMapping("/{country}/{category}")
    public NewsModel getByCountryAndCategory(
            @ApiParam("country symbol") @PathVariable("country") String country,
            @ApiParam("category name") @PathVariable("category") String category) {
        return newsService.getByCountryAndCategory(country, category);
    }
}
