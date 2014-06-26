package com.citi.gpa.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.citi.gpa.document.EtsAppStatus;


public interface EtsAppStatusRepository extends MongoRepository<EtsAppStatus, Serializable> {

}