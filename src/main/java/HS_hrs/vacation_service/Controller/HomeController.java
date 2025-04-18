package HS_hrs.vacation_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation")
public class HomeController {

    @GetMapping
    public String home() {
        return "vacation 서비스입니다.";
    }
}