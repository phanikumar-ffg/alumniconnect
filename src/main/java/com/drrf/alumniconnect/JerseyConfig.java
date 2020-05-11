package com.drrf.alumniconnect;

import com.drrf.alumniconnect.controller.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		
		register(LoginController.class);
		register(ForgotPasswordController.class);
		register(JobInformationController.class);
		register(HelpHistoryController.class);
		register(ContentManagementController.class);
	}

}
