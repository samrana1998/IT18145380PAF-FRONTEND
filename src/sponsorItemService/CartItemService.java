package sponsorItemService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sponsorItemModel.sponsor;

@Path("/Sponsor")

public class CartItemService {
	sponsor c = new sponsor(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCart() 
	{ 
		return c.getsponsorItem();
	}

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("name") String name, 
			@FormParam("company") String company, 
			@FormParam("project") String project) 
	{ 
			String output = c.addsponsorItem(name, company, project);
			return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
		
		//Read the values from the JSON object
		String id = itemObject.get("id").getAsString(); 
		String item = itemObject.get("item").getAsString(); 
		String company = itemObject.get("company").getAsString(); 
		String project = itemObject.get("project").getAsString(); 
		
		String output = c.updatesponsorItems(id, item, company, project);
		
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String itemData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
		//Read the value from the element <itemID>
		String id = doc.select("id").text(); 
		String output = c.deletesponsorItems(id);
		return output; 
	}
}
