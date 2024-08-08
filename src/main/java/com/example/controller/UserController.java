package com.example.controller;

import com.example.Exception.UserNotFoundException;
import com.example.config.JwtGeneratorImpl;
import com.example.model.Alarm;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hybridity/api/sessions")
public class UserController {

@Autowired
    private JwtGeneratorImpl jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUserName() == null || user.getPassword() == null) {
                throw new UserNotFoundException();
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/restricted")
    public List<Alarm> getRestrictedMessage() {
        Alarm alarm = new Alarm();
        List<Alarm> list = new ArrayList<Alarm>();
        alarm.setAlarmStatus("ON");
        alarm.setAlarmId(267);
        list.add(alarm);
        return list;
    }
}