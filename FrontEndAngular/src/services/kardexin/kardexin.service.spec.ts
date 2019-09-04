import { TestBed } from '@angular/core/testing';

import { KardexinService } from './kardexin.service';

describe('KardexinService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KardexinService = TestBed.get(KardexinService);
    expect(service).toBeTruthy();
  });
});
