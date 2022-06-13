package com.projectrespite.springrestapicisample.service;

import com.projectrespite.springrestapicisample.model.PersonEntity;
import com.projectrespite.springrestapicisample.model.PersonResponse;
import com.projectrespite.springrestapicisample.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleService {

    @Autowired
    SampleRepository repository;

    public List<PersonResponse> getPersonList(){

        var entityList = repository.findAll();
        List<PersonResponse> responseList = new ArrayList<PersonResponse>();

        entityList.forEach(entity -> {
            PersonResponse response = new PersonResponse();
            response.setId(entity.getId());
            response.setAge(entity.getAge());
            response.setFullName(entity.getName());
            response.setFirstName(entity.getName().split(" ")[0]);
            response.setLastName(entity.getName().split(" ")[1]);
            responseList.add(response);
        });

        return responseList;
    }
}
