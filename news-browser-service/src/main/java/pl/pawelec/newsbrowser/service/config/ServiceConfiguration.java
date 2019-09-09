package pl.pawelec.newsbrowser.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("pl.pawelec.newsbrowser.service")
@PropertySource(value = "classpath:application.yml")
public class ServiceConfiguration {
}
