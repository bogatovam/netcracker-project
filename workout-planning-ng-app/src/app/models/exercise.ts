export class Exercise {
  constructor(public id?: string,
              public name?: string,
              public description?: Description,
              public measureList?: string[],
              public infForRecommendation?: InfForRecommendation
  ) { }
}

export interface Description {
  technique: string;
  features: string;
}

export interface InfForRecommendation {
  complexity: number;
  muscleLoad: Map<string, number>;
}
