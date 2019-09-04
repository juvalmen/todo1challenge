import { TestBed } from '@angular/core/testing';

import { KardexoutService } from './kardexout.service';

describe('KardexoutService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KardexoutService = TestBed.get(KardexoutService);
    expect(service).toBeTruthy();
  });
});
