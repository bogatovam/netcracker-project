export class User {
  constructor(public id?: string,
              public fullName?: string,
              public email?: string,
              public login?: string,
              public token?: string,
              public password?: string,
              public roles?: string,
              public state?: State,
              public gender?: string,
              public dateOfBirth?: Date,
              public weight?: number,
              public growth?: number,
              public workoutsGoal?: Goals
  ) {
  }
}

export enum State {
  ACTIVE, BANNED, DELETED
}

export enum Goals {
  WEIGHT_LOSS,
  SET_MASS,
  MUSCLE_RELIEF
}

export class Gender {
  name: string;
  char: string;
}

export class Goal {
  name: string;
  goal: Goals;
}

