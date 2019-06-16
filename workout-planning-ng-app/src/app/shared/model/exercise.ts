export interface Exercise {
  id: string;
  name: string;
  description: { 'technique', 'features' };
  measureList: string[];
  infForRecommendation: {
    complexity: number,
    muscleLoad: Map<string, number>;
  };
}
