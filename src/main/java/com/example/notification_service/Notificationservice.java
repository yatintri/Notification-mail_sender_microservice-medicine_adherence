package com.example.notification_service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Component
public class Notificationservice {

    @Autowired
    FirebaseMessaging firebaseMessaging;

    @RabbitListener(queues = "notification")
    public void sendnotification(com.example.user_service.pojos.Notificationmessage notificationmessage) throws FirebaseMessagingException {
    System.out.println("Received");
    String token = notificationmessage.getFcmToken();
        Notification notification = null;
        System.out.println(notificationmessage.getImageUrl());

    if(notificationmessage.getTitle().equals("caretaker")){
       notification =  Notification
                .builder()
                .setTitle(notificationmessage.getTitle())
                .setImage("https://0a76-103-248-123-87.ngrok.io/upload/static/images/"+notificationmessage.getImageUrl())
                .setBody("Taken Medicine "+ notificationmessage.getBody()).build();
    }else{
       notification = Notification
                .builder()
                .setTitle(notificationmessage.getTitle())

                .setBody("Time to take medicine : "+notificationmessage.getBody()).build();
    }

    Message message = Message
            .builder()
            .setToken(token)
            .setNotification(notification)
            .putAllData(new HashMap<>())
            .build();

    firebaseMessaging.send(message);

}

}
