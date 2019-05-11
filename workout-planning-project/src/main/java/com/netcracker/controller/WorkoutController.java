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

    @PostMapping(CREATE_WORKOUT)
    @ApiOperation(value = "")
    public Workout createExercise(
            @ApiParam(value = "")
            @RequestBody Workout workout) {
        return planningService.createWorkout(workout);
    }

    @GetMapping(GET_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout getWorkoutById(
            @ApiParam(value = "")
            @PathVariable String id ){
        return dataDisplayService.getWorkoutById(id);
    }

    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "")
    public String getWorkoutName(
            @ApiParam(value = "")
            @PathVariable String id ){
        return dataDisplayService.getWorkoutName(id);
    }

    @GetMapping(GET_SOURCE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "")
    public WorkoutComplex getSourceWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id ){
        return dataDisplayService.getSourceWorkoutComplex(id);
    }

    @GetMapping(GET_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public List<Exercise> getExercises(
            @ApiParam(value = "")
            @PathVariable String id ){
        return dataDisplayService.getExercises(id);
    }

    @GetMapping(GET_NAMES_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getExercisesNames(
            @ApiParam(value = "")
            @PathVariable String id ){
        return dataDisplayService.getWorkoutsExercisesNames(id);
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public List<ScheduledWorkout> getScheduledWorkouts(
            @ApiParam(value = "")
            @PathVariable String id ){
        return dataDisplayService.getScheduledWorkouts(id);
    }

    @PostMapping(ADD_LIST_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public Boolean addListExercises(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody List<Exercise> exerciseList ){
        return planningService.addListExercises(id, exerciseList);
    }

    @PostMapping(DEL_LIST_OF_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public Boolean delListExercises(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody List<String> exerciseIdList ){
        return planningService.delListExercises(id, exerciseIdList);
    }

    @PostMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "")
    public Workout setWorkoutName(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String name ){
        return planningService.setNameWorkout(id, name);
    }

    @GetMapping(DELETE_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout deleteWorkout(
            @ApiParam(value = "")
            @PathVariable String id ){
        return planningService.deleteWorkout(id);
    }

}
