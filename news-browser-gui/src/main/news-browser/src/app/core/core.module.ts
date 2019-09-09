import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageService } from './message.service';
import { Config, CONFIG } from '../model';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';

const config: Config= {
  domainUrl: 'http://localhost:8001'
}

@NgModule({
  declarations: [
    NotFoundPageComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [
    MessageService,
    {provide: CONFIG, useValue: config}
  ],
  exports: [
    NotFoundPageComponent
  ]
})
export class CoreModule { }
