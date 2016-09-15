package main.services.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.services.db.Util.LoginUtil;
import resources.db.LoginDetails;


@Path("/login")
public class LoginService {

	@POST
	@Path("/a")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String loginPOST(LoginDetails details){
		
		Boolean result = LoginUtil.saveLoginDetails(details);
			
		return details.getPassWord();
		
		
	}
	
	@GET
	@Path("/a")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String loginGET(LoginDetails details){
		return "sad";
		
	}


}