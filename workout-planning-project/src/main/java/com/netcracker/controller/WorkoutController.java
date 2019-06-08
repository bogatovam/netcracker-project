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
    @ApiOperation(value = "Create workout with complex")
    public Workout createWorkoutWithWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String wcid,
            @ApiParam(value = "Body of created workout")
            @RequestBody Workout workout,
             Authentication authentication) {
        return planningService.createWorkout(wcid, workout, authentication.getName());
    }
    @PostMapping(CREATE_WORKOUT)
    @ApiOperation(value = "Create workout")
    public Workout createWorkout(
            @ApiParam(value = "Body of created workout")
            @RequestBody Workout workout,
             Authentication authentication) {
        return planningService.createWorkout(workout, authentication.getName());
    }

    @GetMapping(GET_WORKOUT_BY_ID)
    @ApiOperation(value = "Get workout by id")
    public ResponseEntity<?> getWorkoutById(
            @ApiParam(value = "Workout id")
            @PathVariable String id ,
            Authentication authentication){

        Workout workoutById = dataDisplayService.getWorkoutById(id, authentication.getName());
        if (workoutById == null)
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        return ResponseEntity.ok(workoutById);
    }

    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "Get name by id")
    public String getWorkoutName(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkoutName(id, authentication.getName());
    }

    @GetMapping(GET_SOURCE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "Get source workout complex")
    public WorkoutComplex getSourceWorkoutComplex(
            @ApiParam(value = "Workout id")
            @PathVariable String id ,
            Authentication authentication){
        return dataDisplayService.getSourceWorkoutComplex(id, authentication.getName());
    }

    @GetMapping(GET_EXERCISES_BY_ID)
    @ApiOperation(value = "Get all exercises")
    public List<Exercise> getExercises(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication ){
        return dataDisplayService.getExercises(id, authentication.getName());
    }

    @GetMapping(GET_NAMES_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "Get names of exercise by workout id")
    public Map<String, String> getExercisesNames(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication ){
        return dataDisplayService.getWorkoutsExercisesNames(id, authentication.getName());
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "Get all scheduled workout by id")
    public List<ScheduledWorkout> getScheduledWorkouts(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication ){
        return dataDisplayService.getScheduledWorkouts(id, authentication.getName());
    }

    @PostMapping(ADD_LIST_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "Add list of exercise to workout")
    public Workout addListExercises(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            @ApiParam(value = "List of exercise")
            @RequestBody List<String> exerciseIdList,
             Authentication authentication){
        return planningService.addListExercises(id, exerciseIdList, authentication.getName());
    }

    @DeleteMapping(DEL_LIST_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "Del list of exercide from workout")
    public Workout delListExercises(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            @ApiParam(value = "List of exercise")
            @RequestBody List<String> exerciseIdList,
             Authentication authentication){
        return planningService.delListExercises(id, exerciseIdList, authentication.getName());
    }

    @PutMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "Set name of workout")
    public Workout setWorkoutName(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            @ApiParam(value = "New workout name")
            @RequestBody String name,
             Authentication authentication){
        return planningService.setNameWorkout(id, name, authentication.getName());
    }

    @DeleteMapping(DELETE_WORKOUT_BY_ID)
    @ApiOperation(value = "Delete workout by id")
    public Workout deleteWorkout(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
             Authentication authentication){
        return planningService.deleteWorkout(id, authentication.getName());
    }
}
