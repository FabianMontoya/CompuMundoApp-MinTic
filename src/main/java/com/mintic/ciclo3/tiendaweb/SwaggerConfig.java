package com.mintic.ciclo3.tiendaweb;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mintic.ciclo3.tiendaweb"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Tienda Web API",
				"SWAGGER documentation of TIenda Web API",
				"1.0",
				"https://tiendavirtual.com/terms",
				new Contact("Fabian Dario Montoya", "https://tiendavirtual.com", "apis@tiendavirtual.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
	
}
