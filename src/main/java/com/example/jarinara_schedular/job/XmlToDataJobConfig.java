package com.example.jarinara_schedular.job;

import com.example.jarinara_schedular.dto.XmlDataDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class XmlToDataJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job jarinaraJob() {
		return jobBuilderFactory.get("xmlToDataJob")
			.start(xmlToDtoStep())
			.next(dtoToDataStep())
			.build();
	}

	@Bean
	public Step xmlToDtoStep() {
		return stepBuilderFactory.get("xmlToDtoStep")
				.chunk(10)
				.reader(xmlToDtoItemReader())
				.build();
	}

	@Bean
	public StaxEventItemReader<XmlDataDto> xmlToDtoItemReader(){
		return new StaxEventItemReaderBuilder<XmlDataDto>()
				.name("xmlFileItemReader")
				.resource(new ClassPathResource("/sample_xml_data.xml"))
				.addFragmentRootElements("item")
				.unmarshaller(itemMarshaller())
				.build();
	}

	@Bean
	public XStreamMarshaller itemMarshaller() {
		Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put("item", XmlDataDto.class);
		aliases.put("number", Integer.class);
		aliases.put("data", String.class);
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAliases(aliases);
		return xStreamMarshaller;
	}

	@Bean
	public Step dtoToDataStep() {
		return stepBuilderFactory.get("dtoToDataStep")
			.tasklet((contribution, chunkContext) -> {
				System.out.println("Hello Spring Batch");
				return RepeatStatus.FINISHED;
			}).build();

	}
}
