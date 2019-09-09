import { Component, OnInit, Input } from '@angular/core';
import { ArticleModel } from '../model';

@Component({
  selector: 'nws-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.css']
})
export class NewsCardComponent implements OnInit {

  @Input() article: ArticleModel;
  
  constructor() { }

  ngOnInit() {
  }

}
