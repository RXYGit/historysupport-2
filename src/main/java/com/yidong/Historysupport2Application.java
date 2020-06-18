package com.yidong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * 外呼历史轨迹查询接口
 */
@SpringBootApplication
@EnableAsync
public class Historysupport2Application {

	public static void main(String[] args) {
		SpringApplication.run(Historysupport2Application.class, args);
	}

}
