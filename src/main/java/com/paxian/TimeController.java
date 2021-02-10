package com.paxian;

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

    @GetMapping("/timeData")
    public List<TimeData> get() {
        return timeRepository.findAll();
    }

    @GetMapping("/timeData/{id}")
    public Optional<TimeData> getById(@PathVariable("id") String id) {
        return timeRepository.findById(id);
    }

    @PostMapping("/timeData")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> post(@RequestBody TimeData timeData) {
        this.timeRepository.save(timeData);
        return new ResponseEntity<>("Data created!", HttpStatus.CREATED);
    }

    @DeleteMapping("/timeData/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") TimeData id){
        this.timeRepository.delete(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}