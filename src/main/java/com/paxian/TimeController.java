package com.paxian;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TimeController {

    Logger logger = LoggerFactory.getLogger(TimeController.class);     //go to application properties

    public TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all data", notes = "Get all the resource data!", response = TimeController.class)
    public List<TimeData> getAll() {
        return timeRepository.findAll();
    }

    @PostMapping("/")
    @ApiOperation(value = "Update resource", notes = "Add data to the existing resource!", response = TimeController.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(@RequestBody TimeData timeData) {
        this.timeRepository.save(timeData);
        return new ResponseEntity<>("Data created!", HttpStatus.CREATED);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putData(@RequestBody TimeData timeData) {
        this.timeRepository.insert(timeData);
        return new ResponseEntity<>("Data inserted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Data by 'id'", notes = "Provide an id to look up specific data!", response = TimeController.class)
    public Optional<TimeData> getById(@ApiParam(value = "ID value for the data you need to retrieve!", required = true) @PathVariable("id") String id) {
        return timeRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete data by 'id'", notes = "Provide an id to delete a specific data!", response = TimeController.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@ApiParam(value = "ID value for the data you need to delete!", required = true) @PathVariable("id") TimeData id){
        this.timeRepository.delete(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
