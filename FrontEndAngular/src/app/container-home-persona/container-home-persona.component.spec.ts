import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainerHomePersonaComponent } from './container-home-persona.component';

describe('ContainerHomePersonaComponent', () => {
  let component: ContainerHomePersonaComponent;
  let fixture: ComponentFixture<ContainerHomePersonaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContainerHomePersonaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainerHomePersonaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
