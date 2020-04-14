package com.drrf.alumniconnect;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.drrf.alumniconnect.controller.ForgotPasswordController;
import com.drrf.alumniconnect.controller.LoginController;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		
		register(LoginController.class);
		register(ForgotPasswordController.class);
	}

}
