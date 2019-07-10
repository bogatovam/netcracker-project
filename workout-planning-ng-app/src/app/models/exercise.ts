export interface Exercise {
  id: string;
  name: string;
  description: { 'technique': string, 'features': string };
  measureList: string[];
  infForRecommendation: {
    complexity: number,
    muscleLoad: Map<string, number>;
  };
}
