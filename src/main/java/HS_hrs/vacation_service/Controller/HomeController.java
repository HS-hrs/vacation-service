package HS_hrs.vacation_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import lombok.extern.slf4j.Slf4j;

@Slf4j     
@RestController
@RequestMapping("/vacation")
public class HomeController {

  Environment env;

  public HomeController(Environment env) {
    this.env = env;
  } 

    @GetMapping
    public String home() {
        return "vacation 서비스입니다.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
      log.info("Server port={}", request.getServerPort());
      return String.format("Check from Server running at port %s", env.getProperty("local.server.port"));
    } 
}