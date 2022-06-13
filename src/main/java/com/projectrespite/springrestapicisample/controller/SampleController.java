package com.projectrespite.springrestapicisample.controller;


import com.projectrespite.springrestapicisample.model.PersonResponse;
import com.projectrespite.springrestapicisample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {

    @Autowired
    SampleService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> getPerson(){
        return service.getPersonList();
    }
}
