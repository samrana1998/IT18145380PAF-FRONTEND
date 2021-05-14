package sponsorItemModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class sponsor {
	//A common method to connect to the DB
		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
		 
				//database server name, username and password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo", "root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			return con; 
		} 
		
		//Add function
		public String addsponsorItem(String name , String company, String project) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for inserting.";
				} 
				// create a prepared statement
				String query = " insert into users(`id`,`name`,`company`,`project`)"+" values (?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, name); 
				preparedStmt.setString(3, company); 
				preparedStmt.setString(4, project); 
				// execute the statement
				preparedStmt.execute(); 
				String newsponsor = getsponsorItem(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newsponsor + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error!  adding Items.\"}"; 
				System.err.println(e.getMessage());  
			} 
		 return output; 
		 }
		
	//retrieve function
	public String getsponsorItem() { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading.";
			} 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>item</th>" 
					+"<th>category</th><th>quantity</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
 
	 
			String query = "select * from users"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String id = Integer.toString(rs.getInt("id")); 
				String name = rs.getString("name"); 
				String company = rs.getString("company"); 
				String project = rs.getString("project");  
				
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + id
						 + "'>" + name + "</td>";
				 output += "<td>" + company + "</td>"; 
				 output += "<td>" + project + "</td>";  
			
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='"
						 + id + "'>" + "</td></tr>";
			
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) // check errors
		{ 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 	}

	//Update function
	public String updatesponsorItems(String id, String name, String company, String project){ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating.";
			 } 
			 // create a prepared statement
			 String query = "UPDATE users SET name=?,company=?,project=? WHERE id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, company); 
			 preparedStmt.setString(3, project); 
			 preparedStmt.setInt(4, Integer.parseInt(id)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newsponsor = getsponsorItem(); 
		 	 output = "{\"status\":\"success\", \"data\": \"" + 
		 			newsponsor + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error!  updating details.\"}";  
				System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 }

	//Delete function
	public String deletesponsorItems(String id) { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting.";
			} 
			// create a prepared statement
			String query = "delete from users where id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			String newsponsor = getsponsorItem(); 
			output = "{\"status\":\"success\", \"data\": \"" + newsponsor + "\"}";  
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error! While deleting sponsor Items.\"}";  
			System.err.println(e.getMessage()); 
		} 
		return output; 
		} 
}