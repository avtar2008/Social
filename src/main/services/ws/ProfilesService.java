package main.services.ws;

import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.services.db.Util.ProfileUtils;
import resources.db.Profile;


@Path("/profiles")
public class ProfilesService {

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("name") String name){
		Profile profile = null;
		if(Pattern.matches("[0-9]*", name)){
			profile = ProfileUtils.GetProfileById(Integer.valueOf(name));
		} else if(Pattern.matches("[a-zA-Z]*", name)){
			profile = ProfileUtils.GetProfileByName(name);
		}
			
		return profile;
	}
	
	@POST
	@Path("/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void postProfile(Profile profile){
		
		ProfileUtils.SaveProfile(profile);
		
	}
	
	@DELETE
	@Path("/{name}")
	public void deleteProfile(@PathParam("name") String name){
		
		ProfileUtils.deleteProfile(name);
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putProfile(Profile profile){
		
		ProfileUtils.updateProfile(profile);
		
		
	}
}
