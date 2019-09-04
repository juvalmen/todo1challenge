import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KardexoutAdminComponent } from './kardexout.component';

describe('KardexoutAdminComponent', () => {
  let component: KardexoutAdminComponent;
  let fixture: ComponentFixture<KardexoutAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KardexoutAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KardexoutAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
