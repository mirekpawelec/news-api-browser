package pl.pawelec.newsbrowser.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("pl.pawelec.newsbrowser.controller")
@PropertySource(value = "classpath:application.yml")
public class ControllerConfiguration {
}