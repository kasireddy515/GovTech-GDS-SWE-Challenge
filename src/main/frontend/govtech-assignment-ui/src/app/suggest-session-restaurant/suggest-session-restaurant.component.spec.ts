import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuggestSessionRestaurantComponent } from './suggest-session-restaurant.component';

describe('SuggestSessionRestaurantComponent', () => {
  let component: SuggestSessionRestaurantComponent;
  let fixture: ComponentFixture<SuggestSessionRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuggestSessionRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuggestSessionRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
