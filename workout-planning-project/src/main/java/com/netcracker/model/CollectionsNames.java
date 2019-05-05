package com.netcracker.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CollectionsNames {
    /* Documents */
    public static final String USER = "user";
    public static final String WORKOUT = "workout";
    public static final String SCHEDULED_WORKOUT = "scheduled-workout";
    public static final String WORKOUT_COMPLEX = "workout-complex";
    public static final String EXERCISE = "exercise";
    public static final String MEASUREMENTS_OF_EXERCISE = "measurements_of_exercise";

    /* Edges */
    public static final String USER_TO_WORKOUT_COMPLEX = "user-to-wcomplex";
    public static final String WORKOUT_COMPLEX_TO_WORKOUT = "wcomplex-to-workout";
    public static final String WORKOUT_TO_DATE = "workout-to-date";
    public static final String WORKOUT_TO_EXERCISE = "workout-to-exercise";
    public static final String EXERCISE_TO_MEASUREMENTS= "exercise-to-measurements";
    public static final String EXERCISES_MEASUREMENT_IN_SCHEDULED_WORKOUT= "scheduled-workout-to-exercise-measurement";
}
