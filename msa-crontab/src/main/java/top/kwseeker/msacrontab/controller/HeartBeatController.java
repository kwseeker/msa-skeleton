package top.kwseeker.msacrontab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/heartbeat")
public class HeartBeatController {

    @GetMapping
    public String heartBeat() {
        Date date = new Date();
        return "Current time: " + date.toString();
    }
}
