package com.drrf.alumniconnect;

import com.drrf.alumniconnect.controller.RegistrationController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.drrf.alumniconnect.controller.ForgotPasswordController;
import com.drrf.alumniconnect.controller.LoginController;
import com.drrf.alumniconnect.controller.JobInformationController;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		
		register(LoginController.class);
		register(ForgotPasswordController.class);
		register(JobInformationController.class);
		register(RegistrationController.class);
	}

}
