package io.spring.waterlevel.alerts;


import io.spring.waterlevel.notifier.AlertStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@EnableConfigurationProperties(AlertProperties.class)
public class AlertService {


    private static final Log log = LogFactory.getLog(AlertService.class);

    AlertProperties alertProperties;
    public AlertService(AlertProperties alertProperties) {
        this.alertProperties = alertProperties;
    }

    @ApplicationModuleListener
    void on(AlertStatus event) {
        sendAlert(event);
    }

    void sendAlert(AlertStatus event) {
        if(alertProperties.getPhoneNumber().equals("1770xxxxxxx") || alertProperties.getSmsKey().equals("somethingsomething")) {
            log.info("Send Request For Text Alert was requested but phone number or smsKey was not setup.");
            return;
        }
        log.info("Sending Alert");
        try {
            StringBuilder builder = new StringBuilder("https://textbelt.com/text");
            builder.append("?phone=");
            builder.append(URLEncoder.encode(this.alertProperties.getPhoneNumber(), StandardCharsets.UTF_8.toString()));
            builder.append("&message=");
            builder.append(URLEncoder.encode("Here is your kayaking report: \n" + event.getAlertResult(), StandardCharsets.UTF_8.toString()));
            builder.append("&key=");
            builder.append(URLEncoder.encode(this.alertProperties.getSmsKey(), StandardCharsets.UTF_8.toString()));
            RestTemplate rest = new RestTemplate();
            log.debug(rest.getForObject(builder.toString(),String.class));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
