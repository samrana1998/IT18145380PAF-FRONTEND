package sponsorItemModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartAPI
 */
@WebServlet("/sponsorAPI")
public class sponsorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    sponsor sponObj = new sponsor();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public sponsorAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = sponObj.addsponsorItem(request.getParameter("name"), 
				request.getParameter("company"), 
				request.getParameter("project")); 
				response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 { 
				 String[] p = param.split("=");
				 map.put(p[0], p[1]); 
			 } 
		} 
		catch (Exception e) 
		{ 
			
		} 
			return map; 
		}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output = sponObj.updatesponsorItems(paras.get("hidItemIDSave").toString(), 
		paras.get("name").toString(), 
		paras.get("company").toString(), 
		paras.get("project").toString()); 
		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output = sponObj.deletesponsorItems(paras.get("id").toString()); 
		response.getWriter().write(output); 
	}

}
