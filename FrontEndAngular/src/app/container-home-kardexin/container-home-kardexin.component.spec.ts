import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainerHomeKardexinComponent } from './container-home-kardexin.component';

describe('ContainerHomeKardexinComponent', () => {
  let component: ContainerHomeKardexinComponent;
  let fixture: ComponentFixture<ContainerHomeKardexinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContainerHomeKardexinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainerHomeKardexinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
