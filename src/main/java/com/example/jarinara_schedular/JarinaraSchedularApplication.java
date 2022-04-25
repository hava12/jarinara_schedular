package com.example.jarinara_schedular;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 1. 총 4개의 설정 클래스를 실행시키며, 스프링배치의 모든 초기화 및 실행 구성이 이루어진다.
// 2. 스프링 부트 배치의 자동 설정 클래스가 실행됨으로 빈으로 등록된 모든 Job을 검색해서 초기화와 동시에 Job을 수행하도록 구성됨
@EnableBatchProcessing // 스프링 배치를 활성화시키기 위한 어노테이션
public class JarinaraSchedularApplication {

	public static void main(String[] args) {
		SpringApplication.run(JarinaraSchedularApplication.class, args);
	}

}
