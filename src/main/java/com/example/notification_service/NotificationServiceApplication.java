package com.example.notification_service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.HashMap;

@SpringBootApplication
@EnableScheduling
public class NotificationServiceApplication {
	@Autowired
	FirebaseMessaging firebaseMessaging;

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions
				.builder()
				.setCredentials(googleCredentials)
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
		return FirebaseMessaging.getInstance(app);
	}
	//@Scheduled(fixedRate = 15000)
	public void send() throws FirebaseMessagingException {
		Notification notification = null;
		String token = "eSopD9D5TsOozFw3yILXCL:APA91bEd0g-Uu4Ho1yx6Ye0akuhoFJ4pWP7ZZ_ZVKV4bpGL5tF7uPYDer84TyBD_k0Uzqdjdtue9_FU383WjReFqi611QqcJ_5KdADy3CYXk2FxAY88yk-pN5YjPwYEJz8QWD_EFDEOh";

		notification = Notification
				.builder()
				.setTitle("caretaker")
				.setImage("https://75ae-103-225-204-62.ngrok.io/upload/static/images/nimbu.jpg")
				.setBody("").build();
		Message message = Message
				.builder()
				.setToken(token)
				.setApnsConfig(ApnsConfig.builder().putCustomData("myimage", "https://75ae-103-225-204-62.ngrok.io/upload/static/images/nimbu.jpg").setAps(Aps.builder().putCustomData("myimage", "https://75ae-103-225-204-62.ngrok.io/upload/static/images/nimbu.jpg").setMutableContent(true).build()).build())
				.setNotification(notification)
				.putAllData(new HashMap<>())
				.build();

		firebaseMessaging.send(message);
	}

}
