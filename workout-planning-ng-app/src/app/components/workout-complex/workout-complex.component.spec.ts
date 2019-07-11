import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutComplexComponent } from './workout-complex.component';

describe('WorkoutComplexComponent', () => {
  let component: WorkoutComplexComponent;
  let fixture: ComponentFixture<WorkoutComplexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutComplexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutComplexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
