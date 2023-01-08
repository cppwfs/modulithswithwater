package io.spring.waterlevel.welldata;

import org.springframework.stereotype.Service;

@Service
public class WellDataService {

    public void complete(WellWaterData waterData) {
        System.out.println("Did Something: " + waterData);
    }
}
