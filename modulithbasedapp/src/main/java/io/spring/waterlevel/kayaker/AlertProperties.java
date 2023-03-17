package io.spring.waterlevel.kayaker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("io.spring")
public class AlertProperties {

    private String smsKey = "somethingsomething";
    private String phoneNumber = "1770xxxxxxx";

    public String getSmsKey() {
        return smsKey;
    }

    public void setSmsKey(String smsKey) {
        this.smsKey = smsKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
