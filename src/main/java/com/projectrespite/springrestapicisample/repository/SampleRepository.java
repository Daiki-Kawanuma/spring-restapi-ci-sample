package com.projectrespite.springrestapicisample.repository;

import com.projectrespite.springrestapicisample.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<UserEntity, Integer> {

}
