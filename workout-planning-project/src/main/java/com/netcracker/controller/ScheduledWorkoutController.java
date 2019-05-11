package com.netcracker.controller;

import com.netcracker.model.documents.*;
import com.netcracker.services.api.DemoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.netcracker.controller.ControllersPaths.ScheduledWorkoutController.*;

@RestController
public class ScheduledWorkoutController {
    @Autowired
    DemoService demoService;

    @PostMapping(CREATE_SCHEDULED_WORKOUT)
    @ApiOperation(value = "")
    public ScheduledWorkout createScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody ScheduledWorkout scheduledWorkout) {
        return demoService.createScheduledWorkout(id,scheduledWorkout);
    }

    @GetMapping(GET_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ScheduledWorkout getScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getScheduledWorkout(id);
    }

    @GetMapping(GET_SOURCE_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout getSourceScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getSourceScheduledWorkout(id);
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUTS)
    @ApiOperation(value = "")
    public List<ScheduledWorkout> getAllScheduledWorkouts() {
        return demoService.getAllScheduledWorkouts();
    }

    @GetMapping(GET_NAME_SOURCE_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public String getNameSourceWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getNameSourceWorkout(id);
    }

    @GetMapping(GET_SCHEDULED_DATE_BY_ID)
    @ApiOperation(value = "")
    public Date getDateScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getDateScheduledWorkout(id);
    }

    @GetMapping(GET_STATUS_BY_ID)
    @ApiOperation(value = "")
    public String getStatusScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getStatusScheduledWorkout(id);
    }

    @GetMapping(GET_ALL_CURRENT_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public List<Exercise> getAllCurrentExercises(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getAllCurrentExercises(id);
    }

    @GetMapping(GET_EXERCISES_MEASUREMENTS_BY_ID)
    @ApiOperation(value = "")
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(
            @ApiParam(value = "")
            @PathVariable String id) {
        return demoService.getExercisesMeasurements(id);
    }

    @GetMapping(GET_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public Map<Exercise, MeasurementsOfExercise> getExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid) {
        return demoService.getExerciseMeasurement(id);
    }

    @GetMapping(GET_INFORMATION_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getInformation(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid) {
        return demoService.getScheduledWorkoutInformation(id);
    }

    @PostMapping(SET_STATUS_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> setStatus(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String status) {
        return demoService.setStatusScheduledWorkout(id, status);
    }

    @PostMapping(ADD_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise addExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @RequestBody Measurement measurement) {
        return demoService.addExerciseMeasurement(id, eid, measurement);
    }

    @PostMapping(UPDATE_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise updateExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @PathVariable String num,
            @ApiParam(value = "")
            @RequestBody Measurement measurement) {
        return demoService.updateExerciseMeasurement(id, eid, num, measurement);
    }

    @PostMapping(DEL_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise delExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @RequestBody Measurement measurement) {
        return demoService.delExerciseMeasurement(id, eid, measurement);
    }

    @GetMapping(DELETE_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ScheduledWorkout deleteScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String swid) {
        return demoService.deleteScheduledWorkout(id, swid);
    }
}
