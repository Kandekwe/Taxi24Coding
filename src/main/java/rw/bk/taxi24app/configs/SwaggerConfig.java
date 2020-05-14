
package rw.bk.taxi24app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * swagger enabler
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
  @Autowired
  private Environment environment;
  
  public SwaggerConfig() {}
  
  @Bean
  public Docket api()
  {
    boolean swaggerswitch = true;
    
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("rw.bk.taxi24app.controllers"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo()).enable(swaggerswitch);
  }

  private ApiInfo apiInfo()
  {
    ApiInfo apiInfo = new ApiInfo("Taxi 24 APIs", 
            "Bank of Kigali APIs for taxi24app", "v1.0.0", "Terms of service",
            "support@bk.rw", "License of API", "#");
    

    return apiInfo;
  }
}