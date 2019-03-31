package com.isagiongo.tutorialdevsjava.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	private final ResponseMessage m200 = simpleMessage(200, "Requisição executada com sucesso");
	private final ResponseMessage m201 = simpleMessage (201, "Recurso criado");
	private final ResponseMessage m202put = simpleMessage (202, "Atualizado com sucesso");
	private final ResponseMessage m204del = simpleMessage (204, "Excluído com sucesso");
	private final ResponseMessage m403 = simpleMessage (403, "Não autorizado");
	private final ResponseMessage m404 = simpleMessage (404, "Não encontrado");
	private final ResponseMessage m422 = simpleMessage (422, "Erro de validação");
	private final ResponseMessage m500 = simpleMessage (500, "Erro inesperado");

	
	
	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m403, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m202put, m403, m404, m422, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
				
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.isagiongo.tutorialdevsjava")).build()
				.apiInfo(metaData());

	}

	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("API REST com Spring, JPA, H2")
				.description("API REST utilizando Spring Boot 2, Hibernate, JPA e H2").version("1.0.0")
				.license("Devs JavaGirls").licenseUrl("https://www.meetup.com/pt-BR/Devs-Java-Girl/").build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
