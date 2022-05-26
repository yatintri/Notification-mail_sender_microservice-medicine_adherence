package com.example.notification_service.service;

import com.example.notification_service.Pojos.Notificationmessage;
import com.google.firebase.messaging.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Notificationservice {

    @Autowired
    FirebaseMessaging firebaseMessaging;

    @RabbitListener(queues = "notification")
    public void sendnotification(Notificationmessage notificationmessage) throws FirebaseMessagingException {
        String token = notificationmessage.getFcmToken();
        Notification notification = null;
        if (notificationmessage.getTitle().equals("caretaker")) {
            notification = Notification
                    .builder()
                    .setTitle(notificationmessage.getTitle())
                    .setImage("https://acfb-103-225-204-60.ngrok.io/upload/static/images/" + notificationmessage.getImageUrl())
                    .setBody(notificationmessage.getBody()).build();
        } else {
            notification = Notification
                    .builder()
                    .setTitle(notificationmessage.getTitle())

                    .setBody("Time to take medicine : " + notificationmessage.getBody()).build();
        }

        Message message = Message
                .builder()
                .setToken(token)
                .setApnsConfig(ApnsConfig.builder().putCustomData("myimage", "https://acfb-103-225-204-60.ngrok.io/upload/static/images/" + notificationmessage.getImageUrl()).setAps(Aps.builder().putCustomData("myimage", "https://acfb-103-225-204-60.ngrok.io/upload/static/images/" + notificationmessage.getImageUrl()).setMutableContent(true).build()).build())
                .setNotification(notification)
                .putAllData(new HashMap<>())
                .build();

        firebaseMessaging.send(message);

    }

}
