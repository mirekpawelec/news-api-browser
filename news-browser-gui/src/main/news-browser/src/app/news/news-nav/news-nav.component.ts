import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MessageService } from 'src/app/core/message.service';
import { PaginationParams } from '../model';

@Component({
  selector: 'nws-news-nav',
  templateUrl: './news-nav.component.html',
  styleUrls: ['./news-nav.component.css']
})
export class NewsNavComponent implements OnInit {

  @Input() countries: string[] = [];
  @Input() categories: string[] = [];
  @Input() paginationParams: PaginationParams;

  @Output() switchpage = new EventEmitter<number>(); 
  @Output() page = new EventEmitter<number>(); 
  @Output() perpage = new EventEmitter<number>();

  countrySelected: string = 'pl';
  categorySelected: string = 'technology';

  constructor(private massageService: MessageService) { }

  ngOnInit() {
  }

  previousPage() {
    this.switchpage.emit(-1);
  }

  nextPage() {
    this.switchpage.emit(1);
  }

  setPage(pageNo: number) {
    this.page.emit(pageNo);
  }

  setPerPage(perPageNo: number) {
    this.perpage.emit(perPageNo);
  }
}
