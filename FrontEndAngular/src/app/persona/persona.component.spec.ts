import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonAdminComponent } from './persona.component';

describe('PersonAdminComponent', () => {
  let component: PersonAdminComponent;
  let fixture: ComponentFixture<PersonAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
