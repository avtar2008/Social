package main.services.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest/*")
public class MyApplication extends ResourceConfig {

	public MyApplication() {
		packages("main.services.*");
//		property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, false);
//		property("jersey.config.jsonFeature", true);
	}
	
	
}
