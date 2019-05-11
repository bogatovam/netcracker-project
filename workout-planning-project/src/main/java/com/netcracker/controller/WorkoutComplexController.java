package com.netcracker.controller;

import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.services.api.DemoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

import static com.netcracker.controller.ControllersPaths.WorkoutComplexController.*;

@RestController
public class WorkoutComplexController {
    @Autowired
    DemoService demoService;

    @PostMapping(CREATE_WORKOUT_COMPLEX)
    @ApiOperation(value = "Create workout complex")
    public WorkoutComplex createWorkoutComplex(
            @ApiParam(value = "")
            @RequestBody WorkoutComplex workoutComplex){
        return demoService.createWorkoutComplex(workoutComplex);
    }

    @GetMapping(GET_ALL_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "")
    public List<WorkoutComplex> getAllWorkoutComplex(){
        return demoService.getAllWorkoutComplex();
    }

    @GetMapping(GET_ALL_NAMES_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "")
    public Map<String, String> getAllNamesWorkoutComplex(){
        return demoService.getAllNamesWorkoutComplex();
    }

    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "")
    public String getNameWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id){
        return demoService.getNameWorkoutComplex(id);
    }

    @GetMapping(GET_WORKOUTS_BY_ID)
    @ApiOperation(value = "")
    public List<Workout> getWorkouts(
            @ApiParam(value = "")
            @PathVariable String id){
        return demoService.getWorkouts(id);
    }

    @GetMapping(GET_NAMES_OF_WORKOUTS_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getWorkoutsNames(
            @ApiParam(value = "")
            @PathVariable String id){
        return demoService.getWorkoutsNames(id);
    }

    @PostMapping(ADD_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout addWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody Workout workout){
        return demoService.addWorkout(id, workout);
    }

    @GetMapping(DEL_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout delWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String wid){
        return demoService.delWorkout(id, wid);
    }

    @PostMapping(SET_NAME_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout setNameWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String wid,
            @ApiParam(value = "")
            @RequestBody String name){
        return demoService.setNameWorkout(id, wid, name);
    }

    @PostMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "")
    public WorkoutComplex setNameWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String name){
        return demoService.setNameWorkoutComplex(id, name);
    }

    @GetMapping(DELETE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "")
    public WorkoutComplex setNameWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id){
        return demoService.deleteWorkoutComplex(id);
    }


}
