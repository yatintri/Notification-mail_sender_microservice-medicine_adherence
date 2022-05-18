package com.example.notification_service.mail;

import com.example.notification_service.service.EmailService;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendemail")
    public ResponseEntity<String>sendEmail(@RequestParam EmailRequest request){

        Response response=emailService.sendEmail(request);
        if(response.getStatusCode()==200||response.getStatusCode()==202)
            return new ResponseEntity<>("send successfully",HttpStatus.OK);
        return new ResponseEntity<>("failed to sent", HttpStatus.NOT_FOUND);
    }

}
