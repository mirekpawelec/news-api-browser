package pl.pawelec.newsbrowser.standalone;

import pl.pawelec.newsbrowser.config.ControllerConfiguration;
import pl.pawelec.newsbrowser.network.config.NetworkConfiguration;
import pl.pawelec.newsbrowser.service.config.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ControllerConfiguration.class, ServiceConfiguration.class, NetworkConfiguration.class})
public class NewsBrowserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NewsBrowserApplication.class, args);
	}

}
