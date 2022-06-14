package com.projectrespite.springrestapicisample.service;

import com.projectrespite.springrestapicisample.model.UserResponse;
import com.projectrespite.springrestapicisample.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleService {

    @Autowired
    SampleRepository repository;

    public List<UserResponse> getUserList(){

        var entityList = repository.findAll();
        List<UserResponse> responseList = new ArrayList<UserResponse>();

        entityList.forEach(entity -> {
            UserResponse response = new UserResponse();
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
