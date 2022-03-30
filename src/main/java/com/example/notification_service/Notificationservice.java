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
public void sendnotification(Notificationmessage notificationmessage) throws FirebaseMessagingException {
System.out.println("Received");
    String token = notificationmessage.getFcm_token();
    Notification notification = Notification
            .builder()
            .setTitle(notificationmessage.getMessage())
            .setBody("from caretaker")
            .build();

    Message message = Message
            .builder()
            .setToken(token)
            .setNotification(notification)
            .putAllData(new HashMap<>())
            .build();

    firebaseMessaging.send(message);

}

}
