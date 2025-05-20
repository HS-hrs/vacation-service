package HS_hrs.vacation_service.Client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface UserFeignClient {

}