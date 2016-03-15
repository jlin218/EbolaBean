package controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.ebola;


@WebServlet("/ebola/hi")
public class EbolaServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		 System.out.println("sididi3d");
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response){
		try
        {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
            Client client = ClientBuilder.newClient();
            
            System.out.println("sididid");
            WebTarget target = client.target("https://ebola-outbreak.p.mashape.com/cases");
            Builder builder = target.request(MediaType.APPLICATION_JSON).header("X-Mashape-Key", "1g4fw4E4JImshe9NzUBvJWkE72Isp1tJIo8jsnVfzHeNYD0GTK");
            String eee = builder.get(String.class);
            System.out.println(eee);
    		List<ebola> e = builder.get(new GenericType<List<ebola>>(){});
    		
    	
    		int temp = 0;
    		Map<String, Integer> map = new TreeMap<String, Integer>();
    		Map<String, ?> config = null;
			JsonBuilderFactory factory = Json.createBuilderFactory(config);
    		JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
    		JsonArray value = null;
    				
			for(ebola ee:e){	
    			int cases = Integer.parseInt(ee.getCases());
//    			System.out.println();  
//    			System.out.println(ee.getDate() + "case increase:" + (Integer.parseInt(ee.getCases())-temp));     
    			
    			
    			temp=Integer.parseInt(ee.getCases());
    			map.put(ee.getDate().substring(0,10),cases);
    			arrayBuilder.add(factory.createObjectBuilder()
    					.add("date",ee.getDate().substring(0,10))
    					.add("cases", cases));

    			System.out.println("99");
    			
//    			request.setAttribute("map", map);
    		
    		}
			System.out.println("heihiehieehi");

//			System.out.println("heihiehieehi"+arrayBuilder.build().toString());

			out.write(arrayBuilder.build().toString());
			out.close();
			return;
//    		RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
//			rd.forward(request, response);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
	}
}
