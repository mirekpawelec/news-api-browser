import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'src/app/core/message.service';
import { ArticleModel, PaginationParams } from '../model';
import { NewsService } from '../news.service';
import { ActivatedRoute } from '@angular/router';
import { NewsNavComponent } from '../news-nav/news-nav.component';

@Component({
  selector: 'nws-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {

  @ViewChild('newsFilter') newsFilter: NewsNavComponent;
  allArticles: ArticleModel[];
  articlesFiltered: ArticleModel[];
  singlePageArticles: ArticleModel[];
  articlesFilterKey: string;
  parameters = {
    countries: ['ae', 'ar', 'at', 'au', 'be', 'bg', 'br', 'ca', 'ch', 'cn', 'co', 'cu', 'cz', 'de',
      'eg', 'fr', 'gb', 'gr', 'hk', 'hu', 'id', 'ie', 'il', 'in', 'it', 'jp', 'kr', 'lt',
      'lv', 'ma', 'mx', 'my', 'ng', 'nl', 'no', 'nz', 'ph', 'pl', 'pt', 'ro', 'rs', 'ru',
      'sa', 'se', 'sg', 'si', 'sk', 'th', 'tr', 'tw', 'ua', 'us', 've', 'za'],
    categories: ['business', 'entertainment', 'general', 'health', 'science', 'sports', 'technology']
  }
  paginationParams: PaginationParams = {
    totalItemsNo: 0,
    totalPagesNo: 0,
    itemPerPageNo: 5,
    currentPageNo: 1,
    dataToBeDisplayByPageNo: {}
  }

  constructor(
    private activatedRoute: ActivatedRoute,
    private newsService: NewsService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(
      params => {
        const countryParam = params.get('country') || this.newsFilter.countrySelected;
        const categoryParam = params.get('category') || this.newsFilter.categorySelected;
        this.newsService.getNews(countryParam, categoryParam).subscribe(
          response => {
            this.allArticles = response.articles;
            this.articlesFiltering();
          },
          ({ error }) => {
            console.log(error);
            this.messageService.error("Not found any data for given category.");
          }
        )
      }
    )
  }

  articlesFiltering() {
    this.setArticlesFilteredArray();
    this.setPaginationParamsObject();
    this.setSinglePageArticlesArray();
  }

  switchPage(value: number) {
    this.paginationParams = {
      ...this.paginationParams,
      currentPageNo: this.paginationParams.currentPageNo + value
    }
    this.setSinglePageArticlesArray()
  }

  setPage(pageNo: number) {
    this.setSinglePageArticlesArray(pageNo);
  }

  setPerPage(currValuePerPage: number) {
    this.paginationParams = {
      ...this.paginationParams,
      itemPerPageNo: currValuePerPage
    }
    this.setPaginationParamsObject();
    this.setSinglePageArticlesArray();
  }

  setArticlesFilteredArray() {
    if (this.articlesFilterKey && this.articlesFilterKey.length) {
      const regExpKeyWord = new RegExp(this.articlesFilterKey, 'gi');
      regExpKeyWord.ignoreCase;
      this.articlesFiltered = this.allArticles
        .filter(({ author, title, description, sourceName }) =>
          (author ? regExpKeyWord.test(author) : false) ||
          (title ? regExpKeyWord.test(title) : false) ||
          (description ? regExpKeyWord.test(description) : false) ||
          (sourceName ? regExpKeyWord.test(sourceName) : false)
        );
    } else {
      this.articlesFiltered = null;
    }
  }

  setPaginationParamsObject() {
    const articlesToBeProcessed = this.articlesFiltered || this.allArticles;
    let itemNo = 0;
    let pageNo = 1;
    this.paginationParams = {
      ...this.paginationParams,
      totalItemsNo: articlesToBeProcessed.length,
      totalPagesNo: Math.ceil(articlesToBeProcessed.length / this.paginationParams.itemPerPageNo),
      dataToBeDisplayByPageNo: articlesToBeProcessed
          .reduce((articlePagesObject, article) => {
            if (this.paginationParams.itemPerPageNo > itemNo) {
              itemNo++;
            } else {
              pageNo++;
              itemNo = 1;
            }
            pageNo in articlePagesObject ?
              articlePagesObject[pageNo] = [...articlePagesObject[pageNo], article] :
              articlePagesObject[pageNo] = [article];
            return articlePagesObject;
          }, {})
    }
  }

  setSinglePageArticlesArray(pageNo?: number) {
    this.singlePageArticles = this.paginationParams.dataToBeDisplayByPageNo[pageNo || this.paginationParams.currentPageNo];
  }
}
