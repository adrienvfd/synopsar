package synopsarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebSecurity
public class SynopsarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SynopsarApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);

                reg.addMapping("/summary")
                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders("*")
                        .allowedMethods("POST, DELETE")
                        .allowCredentials(true);

                reg.addMapping("/summary/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders("*")
                        .allowedMethods("DELETE")
                        .allowCredentials(true);
            }
        };
    }
}
