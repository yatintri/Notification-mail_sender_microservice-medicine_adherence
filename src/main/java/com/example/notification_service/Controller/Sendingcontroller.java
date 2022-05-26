package com.example.notification_service.Controller;


import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/send")
public class Sendingcontroller {



    @GetMapping(value = "/noti")
    public String sendnoti() throws FirebaseMessagingException {

    return "OK";

    }

}
