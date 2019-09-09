import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsNavComponent } from './news-nav.component';

describe('NewsFilterComponent', () => {
  let component: NewsNavComponent;
  let fixture: ComponentFixture<NewsNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsNavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
