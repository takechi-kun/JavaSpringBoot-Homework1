package com.training.platform.controllers;

import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/index1")
    public List<User> index1() {
        return userService.findAllByJpqlQuery();
    }

    @GetMapping(value = "/index2")
    public List<User> index2(@RequestParam String active, @RequestParam String city) {
        return userService.findAllByJpqlParamsQuery(Integer.parseInt(active), city);
    }

    /*@GetMapping(value = "/index3")
    public List<User> index3(@RequestParam String age) {
        String[] text = age.split(",");
        List<Integer> ageList = new ArrayList<Integer>();
        for (String number : text) {
            ageList.add(Integer.parseInt(number));
        }
        return userService.findByAgeIn(ageList);
    }*/

    /*@GetMapping(value = "/index3")
    public List<User> index3(@RequestParam(name = "age") List<Integer> ages) {
        return userService.findByAgeIn(ages);
    }*/

    @GetMapping(value = "/index3")
    public List<User> index3(@RequestBody Map<String, Object> dataInput) {
        return userService.findByAgeIn((List<Integer>) dataInput.get("age"));
    }


    @GetMapping(value = "/index4")
    public List<User> index4(@RequestParam String city, @RequestParam Integer active, @RequestParam Integer age) {
        return userService.findByCityAndActiveAndAge(city, active , age);
    }

}
