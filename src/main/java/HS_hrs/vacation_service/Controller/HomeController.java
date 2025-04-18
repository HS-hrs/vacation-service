package HS_hrs.vacation_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/vacation")
    public String home() {
        return "vacation 서비스입니다.";
    }
}