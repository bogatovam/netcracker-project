import { TestBed } from '@angular/core/testing';

import { HttpClient } from './http-client.service';

describe('HttpClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpClient = TestBed.get(HttpClient);
    expect(service).toBeTruthy();
  });
});
