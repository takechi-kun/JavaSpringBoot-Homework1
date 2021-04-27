package com.training.platform.repositories;

import com.training.platform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    //public List<User> findByCityAndActiveAndAge(String city, Integer active, Integer age);
    //public List<User> findByAgeIn(List<Integer> ages);

    @Query(value = "SELECT * FROM users u WHERE u.active = 1 and u.city = 'Bangkok' ",
            nativeQuery = true)
    public List<User> findAllByQuery();

    @Query(value = "SELECT * FROM users u WHERE u.active = ?1 and u.city = ?2 ",
            nativeQuery = true)
    public List<User> findAllByParamsQuery(Integer active,  String city);

    @Query("SELECT u FROM User u WHERE u.active = 1 and u.city = 'Bangkok' ")
    public List<User> findAllByJpqlQuery();

    @Query("SELECT u FROM User u WHERE u.active = ?1 and u.city = ?2 ")
    public List<User> findAllByJpqlParamsQuery(Integer active, String city);

    // new reposotory 1
    @Query("SELECT u FROM User u WHERE u.age in (?1) ")
    public List<User> findByAgeIn(List<Integer> ages);

    // new reposotory 2
    @Query("SELECT u FROM User u WHERE u.city = ?1 and u.active= ?2 and u.age = ?3")
    public List<User> findByCityAndActiveAndAge(String city, Integer active, Integer age);
}
