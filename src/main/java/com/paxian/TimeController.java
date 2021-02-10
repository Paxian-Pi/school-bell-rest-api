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

    @GetMapping("/")
    public List<TimeData> getAll() {
        return timeRepository.findAll();
    }

    @PostMapping("/")
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
    public Optional<TimeData> getById(@PathVariable("id") String id) {
        return timeRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") TimeData id){
        this.timeRepository.delete(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
