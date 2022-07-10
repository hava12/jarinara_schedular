package com.example.jarinara_schedular.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JarinaraConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job jarinaraJob() {
		return jobBuilderFactory.get("jarinaraJob")
			.start(jarinaraStep())
			.next(jarinaraStep2())
			.build();
	}

	@Bean
	public Step jarinaraStep2() {
		return stepBuilderFactory.get("jarinaraStep2")
			.tasklet(((contribution, chunkContext) -> {

				System.out.println("Hello Spring Batch2");
				return RepeatStatus.FINISHED;
			})).build();
	}

	@Bean
	public Step jarinaraStep() {
		return stepBuilderFactory.get("jarinaraStep")
			.tasklet((contribution, chunkContext) -> {
				System.out.println("Hello Spring Batch");
				return RepeatStatus.FINISHED;
			}).build();

	}
}
