package com.netcracker.controller;

public class ControllersPaths {

    /*   Paths to Exercise Controller   */
    public static class ExerciseController {
        /*   Create operations   */
        public static final String CREATE_EXERCISE = "/exercises/create";

        /*   Read   operations   */
        public static final String GET_ALL_EXERCISES = "/exercises/all";
        public static final String GET_ALL_NAMES_OF_EXERCISES = "/exercises/names";
        public static final String GET_EXERCISE_BY_ID = "/exercises/{id}";
        public static final String GET_NAME_BY_ID = "/exercises/{id}/name"; //??
        public static final String GET_DESCRIPTION_BY_ID = "/exercises/{id}/description";
        public static final String GET_MEASURES_BY_ID = "/exercises/{id}/measures";
        public static final String GET_ALL_MEASUREMENT_BY_ID = "/exercises/{id}/measurements/all";
        public static final String GET_ALL_MEASUREMENT_WITH_DATE_BY_ID = "/exercises/{id}/measurements/with-date";
        public static final String GET_LAST_MEASUREMENT_BY_ID = "/exercises/{id}/measurements/last";

        /*   Update operations   */
        public static final String ADD_MEASUREMENTS_GROUP_BY_ID = "/exercises/{id}/measurements/add"; //??
        public static final String ADD_MEASUREMENT_BY_ID = "/exercises/{id}/measurements/{mid}/add"; //??
        public static final String DEL_MEASUREMENTS_GROUP_BY_ID = "/exercises/{id}/measurements/{mid}/del"; //??
        public static final String DEL_MEASUREMENT_BY_ID = "/exercises/{id}/measurements/{mid}/{num}/del"; //??
        public static final String SET_NAME_BY_ID = "/exercises/{id}/name";
        public static final String SET_DESCRIPTION_BY_ID = "/exercises/{id}/description";

        /*   Delete operations   */
        public static final String DELETE_EXERCISE_BY_ID = "/exercises/{id}/delete";
    }
    /*   Paths to Workout Controller   */
    public static class WorkoutController {

        /*   Create operations   */
        public static final String CREATE_WORKOUT = "/workout/create";

        /*   Read   operations   */
        public static final String GET_WORKOUT_BY_ID = "/workout/{id}";
        public static final String GET_NAME_BY_ID = "/workout/{id}/name";
        public static final String GET_SOURCE_WORKOUT_COMPLEX_BY_ID = "/workout/{id}/complex";
        public static final String GET_EXERCISES_BY_ID = "/workout/{id}/exercises";
        public static final String GET_NAMES_OF_EXERCISES_BY_ID = "/workout/{id}/exercises/names";
        public static final String GET_ALL_SCHEDULED_WORKOUT_BY_ID = "/workout/{id}/scheduled";

        /*   Update operations   */
        public static final String ADD_LIST_OF_EXERCISES_BY_ID = "/workout/{id}/exercises/add";
        public static final String DEL_LIST_OF_EXERCISES_BY_ID = "/workout/{id}/exercises/del";
        public static final String SET_NAME_BY_ID = "/workout/{id}/name";

        /*   Delete operations   */
        public static final String DELETE_WORKOUT_BY_ID = "/workout/{id}/delete";
    }

    /*   Paths to Scheduled Workout Controller   */
    public static class ScheduledWorkoutController {

        /*   Create operations   */
        public static final String CREATE_SCHEDULED_WORKOUT = "/workout/{id}/plan";

        /*   Read   operations   */
        public static final String GET_SCHEDULED_WORKOUT_BY_ID = "/workout/{id}";
        public static final String GET_ALL_SCHEDULED_WORKOUTS = "/scheduled-workout/all";
        public static final String GET_SOURCE_WORKOUT_BY_ID = "/scheduled-workout/{id}/source-workout";
        public static final String GET_NAME_SOURCE_WORKOUT_BY_ID = "/scheduled-workout/{id}/source-workout/name";
        public static final String GET_SCHEDULED_DATE_BY_ID = "/scheduled-workout/{id}/date";
        public static final String GET_STATUS_BY_ID = "/scheduled-workout/{id}/status";
        public static final String GET_ALL_CURRENT_EXERCISES_BY_ID = "/scheduled-workout/{id}/exercises";
        public static final String GET_EXERCISES_MEASUREMENTS_BY_ID = "/scheduled-workout/{id}/exercise-measurements";
        public static final String GET_EXERCISE_MEASUREMENT_BY_ID = "/scheduled-workout/{id}/exercise/{eid}";
        public static final String GET_INFORMATION_BY_ID = "/scheduled-workout/{id}/information";

        /*   Update operations   */
        public static final String SET_STATUS_BY_ID = "/scheduled-workout/{id}/status";
        public static final String ADD_EXERCISE_MEASUREMENT_BY_ID = "/scheduled-workout/{id}/exercise/{eid}/add";
        public static final String UPDATE_EXERCISE_MEASUREMENT_BY_ID = "/scheduled-workout/{id}/exercise/{eid}/measurement/{num}";
        public static final String DEL_EXERCISE_MEASUREMENT_BY_ID = "/scheduled-workout/{id}/exercise/{eid}/del";

        /*   Delete operations   */
        public static final String DELETE_SCHEDULED_WORKOUT_BY_ID = "/workout/plan/{id}/delete/{swid}";
    }

    /*   Paths to Workout Complex Controller   */
    public static class WorkoutComplexController {

        /*   Create operations   */
        public static final String CREATE_WORKOUT_COMPLEX = "/workout-complex/create";

        /*   Read   operations   */
        public static final String GET_WORKOUT_COMPLEX_BY_ID = "/workout-complex/{id}";
        public static final String GET_ALL_WORKOUTS_COMPLEXES = "/workout-complex/all";
        public static final String GET_ALL_NAMES_WORKOUTS_COMPLEXES = "/workout-complex/all/names";
        public static final String GET_NAME_BY_ID = "/workout-complex/{id}/name";
        public static final String GET_WORKOUTS_BY_ID = "/workout-complex/{id}/workouts";
        public static final String GET_NAMES_OF_WORKOUTS_BY_ID = "/workout-complex/{id}/workouts/names";

        /*   Update operations   */
        public static final String ADD_WORKOUT_BY_ID = "/workout-complex/{id}/workouts/add";
        public static final String DEL_WORKOUT_BY_ID = "/workout-complex/{id}/workouts/{wid}/del";
        public static final String SET_NAME_WORKOUT_BY_ID = "/workout-complex/{id}/workouts/{wid}/name";
        public static final String SET_NAME_BY_ID = "/workout-complex/{id}/name";

        /*   Delete operations   */
        public static final String DELETE_WORKOUT_COMPLEX_BY_ID = "/workout-complex/{id}/delete";
    }
}
