package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.samgres.api.services.TrafficService;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
    @Autowired
    TrafficService trafficService;

    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public String getEvents(@RequestHeader String token) {
        return trafficService.getEvents(token);
    }

    @RequestMapping(value = "/getBorderDelays", method = RequestMethod.GET)
    public String getBorderDelays(@RequestHeader String token) {
        return trafficService.getBorderDelays(token);
    }

    @RequestMapping(value = "/getStorms", method = RequestMethod.GET)
    public String getStorms(@RequestHeader String token) {
        return trafficService.getStorms(token);
    }
}
