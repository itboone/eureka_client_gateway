package com.bo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication // 集成注解:@SpringBootApplication@EnableDiscoveryClient@EnableCircuitBreaker
public class EurekaClientGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientGatewayApplication.class, args);
	}
}
