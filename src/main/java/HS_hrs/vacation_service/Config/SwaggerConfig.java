package HS_hrs.vacation_service.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Vacation Service API",
        description = "Vacation Service API 문서",
        version = "v1.0.0"
    )
)

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI api(){
      Server gatewayServer = new Server();
      gatewayServer.setUrl("http://localhost:8080");
      gatewayServer.setDescription("Gateway Server");
      
      return new OpenAPI()
          .components(new Components())
          .servers(List.of(gatewayServer))  // 서버 URL 설정
          .info(new Info()
              .title("Vacation Service API")
              .version("v1.0.0")
              .description("Vacation Service API 문서"));
  }
}
