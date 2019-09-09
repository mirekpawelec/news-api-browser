package pl.pawelec.newsbrowser.network.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("pl.pawelec.newsbrowser.network")
@PropertySource(value = "classpath:application.yml")
public class NetworkConfiguration {
}
