package com.drrf.alumniconnect;

import com.drrf.alumniconnect.controller.RegistrationController;
import com.drrf.alumniconnect.configuration.CorsConfigurationDrf;
import com.drrf.alumniconnect.controller.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		
		register(LoginController.class);
		register(ForgotPasswordController.class);
		register(JobInformationController.class);
		register(RegistrationController.class);
		register(HelpHistoryController.class);
		register(ContentManagementController.class);
		register(CorsConfigurationDrf.class);
        register(CityDetailsController.class);
		register(AdminHelpController.class);
		register(ProfileInformationController.class);

	}

}
