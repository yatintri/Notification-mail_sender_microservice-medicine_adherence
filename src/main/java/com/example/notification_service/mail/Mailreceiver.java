package com.example.notification_service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Mailreceiver {

    @Autowired
    private JavaMailSender javaMailSender;
    Logger logger = LoggerFactory.getLogger(Mailreceiver.class);

    @RabbitListener(queues = "mail")
    @Async
    public void receive(MailInfo mailInfo) {

        String message1  = "Hello "+mailInfo.getSender()+" has sent you request to be a caretaker . If not registered kindly signup into our application if not downloaded click on below link to down application . "+
                 "https://play.google.com/store/apps/details?id=com.animesafar.dinterviewkit";
    logger.info(Thread.currentThread().getName());
     System.out.println("received");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailInfo.getReceiverMail());
            message.setSubject("Caretaker request");
            message.setText(message1);
            javaMailSender.send(message);



    }


}
