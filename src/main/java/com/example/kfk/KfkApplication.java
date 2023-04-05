package com.example.kfk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KfkApplication {

    public static void main(String[] args) throws InterruptedException {
        var ctx = SpringApplication.run(KfkApplication.class, args);
        var smsClient = ctx.getBean(SmsClient.class);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000 * 3);

            var sms = new Sms("+7900800706" + i, "Hello World № " + i);

            smsClient.sendSms(sms);
        }
    }
}
