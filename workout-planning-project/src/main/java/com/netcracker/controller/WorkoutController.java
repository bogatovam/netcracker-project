package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.ScheduledWorkout;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.netcracker.controller.ControllersPaths.WorkoutController.*;

@RestController
public class WorkoutController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_WORKOUT_WITH_COMPLEX)
    @ApiOperation(value = "")
    public Workout createExercise(
            @ApiParam(value = "")
            @PathVariable String wcid,
            @ApiParam(value = "")
            @RequestBody Workout workout,
             Authentication authentication) {
        return planningService.createWorkout(wcid, workout);
    }
    @PostMapping(CREATE_WORKOUT)
    @ApiOperation(value = "")
    public Workout createExercise(
            @ApiParam(value = "")
            @RequestBody Workout workout,
             Authentication authentication) {
        return planningService.createWorkout(workout);
    }

    @GetMapping(GET_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ResponseEntity<?> getWorkoutById(
            @ApiParam(value = "")
            @PathVariable String id ,
            Authentication authentication){

        Workout workoutById = dataDisplayService.getWorkoutById(id, authentication.getName());
        if (workoutById == null)
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        return ResponseEntity.ok(workoutById);
    }

    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "")
    public String getWorkoutName(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkoutName(id, authentication.getName());
    }

    @GetMapping(GET_SOURCE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "")
    public WorkoutComplex getSourceWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id ,
            Authentication authentication){
        return dataDisplayService.getSourceWorkoutComplex(id, authentication.getName());
    }

    @GetMapping(GET_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public List<Exercise> getExercises(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication ){
        return dataDisplayService.getExercises(id, authentication.getName());
    }

    @GetMapping(GET_NAMES_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getExercisesNames(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication ){
        return dataDisplayService.getWorkoutsExercisesNames(id, authentication.getName());
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public List<ScheduledWorkout> getScheduledWorkouts(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication ){
        return dataDisplayService.getScheduledWorkouts(id, authentication.getName());
    }

    @PostMapping(ADD_LIST_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public Workout addListExercises(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody List<String> exerciseIdList,
             Authentication authentication){
        return planningService.addListExercises(id, exerciseIdList);
    }

    @PostMapping(DEL_LIST_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public Workout delListExercises(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody List<String> exerciseIdList,
             Authentication authentication){
        return planningService.delListExercises(id, exerciseIdList);
    }

    @PostMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "")
    public Workout setWorkoutName(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String name,
             Authentication authentication){
        return planningService.setNameWorkout(id, name);
    }

    @GetMapping(DELETE_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout deleteWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
             Authentication authentication){
        return planningService.deleteWorkout(id);
    }

}
