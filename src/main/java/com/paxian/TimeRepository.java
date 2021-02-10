package com.paxian;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeRepository extends MongoRepository<TimeData, String> { }
