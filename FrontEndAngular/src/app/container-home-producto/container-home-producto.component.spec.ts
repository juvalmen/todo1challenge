import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainerHomeProductoComponent } from './container-home-producto.component';

describe('ContainerHomeProductoComponent', () => {
  let component: ContainerHomeProductoComponent;
  let fixture: ComponentFixture<ContainerHomeProductoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContainerHomeProductoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainerHomeProductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
