import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsService } from './news.service';
import { CoreModule } from '../core/core.module';
import { FormsModule } from '@angular/forms';
import { NewsCardComponent } from './news-card/news-card.component';
import { NewsNavComponent } from './news-nav/news-nav.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    NewsListComponent,
    NewsCardComponent,
    NewsNavComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    FormsModule,
    RouterModule
  ],
  providers: [
    NewsService
  ],
  exports: [
    NewsListComponent
  ]
})
export class NewsModule { }
