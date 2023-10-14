import { TestBed } from '@angular/core/testing';

import { UsernotificationService } from './usernotification.service';

describe('UsernotificationService', () => {
  let service: UsernotificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsernotificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
