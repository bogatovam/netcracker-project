package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.Measurement;
import com.netcracker.model.documents.MeasurementsOfExercise;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.netcracker.controller.ControllersPaths.ExerciseController.*;

@RestController
public class ExerciseController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_EXERCISE)
    @ApiOperation(value = "Create exercise")
    public Exercise createExercise(
            @ApiParam(value = "Exercise")
            @RequestBody Exercise exercise) {
        return planningService.createExercise(exercise);
    }

    @GetMapping(GET_ALL_EXERCISES)
    @ApiOperation(value = "Return all Exercise")
    public List<Exercise> getAllExercise() {
        return dataDisplayService.getAllExercise();
    }

    @GetMapping(GET_ALL_NAMES_OF_EXERCISES)
    @ApiOperation(value = "Return all exercises names")
    public Map<String, String> getAllNamesOfExercises() {
        return dataDisplayService.getAllNamesOfExercises();
    }

    @GetMapping(GET_EXERCISE_BY_ID)
    @ApiOperation(value = "Return exercise by id")
    public Exercise getExerciseByUd(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getExercise(id);
    }


    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "Return exercises name by id")
    public String getNameOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getNameOfExercise(id);
    }

    @GetMapping(GET_DESCRIPTION_BY_ID)
    @ApiOperation(value = "Return exercises description by id")
    public String getDescriptionOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getDescriptionOfExercise(id);
    }

    @GetMapping(GET_MEASURES_BY_ID)
    @ApiOperation(value = "Return exercises list measures by id")
    public List<String> getMeasuresOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getMeasuresOfExercise(id);
    }

    @GetMapping(GET_ALL_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Return measurements of exercise by id")
    public List<MeasurementsOfExercise> getMeasurementOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getMeasurementsOfExercise(id);
    }

    @GetMapping(GET_ALL_MEASUREMENT_WITH_DATE_BY_ID)
    @ApiOperation(value = "Return measurements with date of exercise by id")
    public Map<Date, MeasurementsOfExercise> getMeasurementOfExerciseWithDate(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getMeasurementsOfExerciseWithDate(id);
    }

    @GetMapping(GET_LAST_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Return last measurement of exercise by id")
    public MeasurementsOfExercise getLastMeasurementOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getLastMeasurementOfExercise(id);
    }

    @PostMapping(ADD_MEASUREMENTS_GROUP_BY_ID)
    @ApiOperation(value = "Add MeasurementsOfExercise( group measurements) to exercise by id")
    public MeasurementsOfExercise addMeasurementsOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "MeasurementsOfExercise")
            @RequestBody MeasurementsOfExercise measurement) {
        return planningService.addExerciseMeasurement(id, measurement);
    }

    @PostMapping(ADD_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Add measurement to exercise by id")
    public Measurement addMeasurementOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "MeasurementsOfExercise id")
            @PathVariable String mid,
            @ApiParam(value = "Measurement")
            @RequestBody Measurement measurement) {
        return planningService.addMeasurementOfExercise(id, mid, measurement);
    }

    @GetMapping(DEL_MEASUREMENTS_GROUP_BY_ID)
    @ApiOperation(value = "Del MeasurementsOfExercise( group measurements) from exercise by id")
    public MeasurementsOfExercise delMeasurementsOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "MeasurementsOfExercise id")
            @PathVariable String mid) {
        return planningService.delExerciseMeasurement(id, mid);
    }

    @GetMapping(DEL_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Del measurement from exercise by id")
    public Measurement delMeasurementOfExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "MeasurementsOfExercise id")
            @PathVariable String mid,
            @ApiParam(value = "Measurements number")
            @PathVariable String num) {
        return planningService.delMeasurementOfExercise(id, mid, num);
    }

    @PostMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "Set name to exercise by id")
    public String setExerciseName(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "Exercises name")
            @RequestBody String name) {
        return planningService.setExerciseName(id, name);
    }

    @PostMapping(SET_DESCRIPTION_BY_ID)
    @ApiOperation(value = "Set description to exercise by id")
    public String setDescriptionName(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "Exercises description")
            @RequestBody String name) {
        return planningService.setExerciseName(id, name);
    }

    @GetMapping(DELETE_EXERCISE_BY_ID)
    @ApiOperation(value = "Delete exercise by id")
    public Exercise deleteExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return planningService.deleteExercise(id);
    }
}
