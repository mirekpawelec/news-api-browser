import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewsListComponent } from './news/news-list/news-list.component';
import { NotFoundPageComponent } from './core/not-found-page/not-found-page.component';

const routes: Routes = [
    {path: 'news', component: NewsListComponent},
    {path: '', redirectTo: 'news', pathMatch: 'full'},
    {path: '**', component: NotFoundPageComponent}
]
@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
