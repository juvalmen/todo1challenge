import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KardexinAdminComponent } from './kardexin.component';

describe('KardexinAdminComponent', () => {
  let component: KardexinAdminComponent;
  let fixture: ComponentFixture<KardexinAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KardexinAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KardexinAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
