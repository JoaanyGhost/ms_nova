import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalizeStyle } from './personalize-style';

describe('PersonalizeStyle', () => {
  let component: PersonalizeStyle;
  let fixture: ComponentFixture<PersonalizeStyle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonalizeStyle],
    }).compileComponents();

    fixture = TestBed.createComponent(PersonalizeStyle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
