package com.netcracker.controller;

public class ControllersPaths {
    public static final String ORIGIN = "http://localhost:4200";

    /*   Paths to Users Controller   */
    public static class usersController {
        public static final String USER_SIGN_IN = "/authentication/signin";
        public static final String USER_SIGN_UP = "/authentication/signup";
        public static final String GET_USER_BY_ID = "/user/{id}";
        public static final String DELETE_USER_BY_ID = "/user/{id}";
    }
    /*   Paths to Exercise Controller   */
    public static class ExerciseController {
        /*   Create operations   */
        public static final String CREATE_EXERCISE = "/exercises/create";

        /*   Read   operations   */
        public static final String GET_ALL_EXERCISES = "/free/exercises/all";
        public static final String GET_ALL_EXERCISES_LOAD = "/free/exercises/load";
        public static final String GET_EXERCISE_BY_ID = "/free/exercise/{id}";

        /*   Update operations   */
        public static final String SET_NAME_BY_ID = "/exercises/{id}/name";
        public static final String SET_DESCRIPTION_BY_ID = "/exercises/{id}/description";

        /*   Delete operations   */
        public static final String DELETE_EXERCISE_BY_ID = "/exercises/{id}/delete";
    }
    /*   Paths to Workout Controller   */
    public static class WorkoutController {
        /*   Create operations   */
        public static final String CREATE_WORKOUT_WITH_COMPLEX = "/workout/create/{wcid}";

        /*   Read   operations   */
        public static final String GET_WORKOUT_BY_ID = "/workout/{id}";
        public static final String GET_SOURCE_WORKOUT_COMPLEX_BY_ID = "/workout/complex/{id}";
        public static final String GET_EXERCISES_BY_ID = "/workout/exercises/{id}";
        public static final String GET_ALL_WORKOUT = "/workout/all";

        /*   Update operations   */
        public static final String UPDATE_WORKOUT = "/workout/update";
        public static final String CHANGE_SOURCE_WORKOUT_COMPLEX = "/workout/change/{id}/{oldwcid}/{newwcid}";

        /*   Delete operations   */
        public static final String DELETE_WORKOUT_BY_ID = "/workout/delete/{id}";
    }

    /*   Paths to Workout Complex Controller   */
    public static class WorkoutComplexController {

        /*   Create operations   */
        public static final String CREATE_WORKOUT_COMPLEX = "/workout-complex/create";

        /*   Read   operations   */
        public static final String GET_WORKOUT_COMPLEX_BY_ID = "/workout-complex/{id}";
        public static final String GET_ALL_WORKOUTS_COMPLEXES = "/workout-complex/all";
        public static final String GET_WORKOUTS_BY_ID = "/workout-complex/{id}/workouts";

        /*   Update operations   */
        public static final String ADD_WORKOUT_BY_ID = "/workout-complex/{id}/workouts/add";
        public static final String DEL_WORKOUT_BY_ID = "/workout-complex/{id}/workouts/{wid}/del";
        public static final String UPDATE_WORKOUT_COMPLEX = "/workout-complex/update";

        /*   Delete operations   */
        public static final String DELETE_WORKOUT_COMPLEX_BY_ID = "/workout-complex/delete/{id}";
    }
}
