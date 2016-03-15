package controller;

import java.util.List;

import javax.ws.rs.client.*;
import javax.ws.rs.client.Invocation.*;
import javax.ws.rs.core.*;


import model.ebola;
public class GeoIPClient
{
    public static void main(String[] args)
    {
        try
        {
            Client client = ClientBuilder.newClient();
            
            
//            WebTarget target = client.target("http://freegeoip.net/xml/74.125.31.18");
            System.out.println("jiji");
            WebTarget target = client.target("https://ebola-outbreak.p.mashape.com/cases");
            Builder builder = target.request(MediaType.APPLICATION_JSON).header("X-Mashape-Key", "1g4fw4E4JImshe9NzUBvJWkE72Isp1tJIo8jsnVfzHeNYD0GTK");
            String eee = builder.get(String.class);
            System.out.println(eee);
    		List<ebola> e = builder.get(new GenericType<List<ebola>>(){});
    		
    		int temp = 0;
    		
    		for(ebola ee:e){
//    			System.out.println(ee.getDeaths());
//    			System.out.println(ee.getDate());
//    			System.out.println(ee.getStatus());
//    			System.out.println(ee.getCases());
//    			System.out.println("case increase:" + (Integer.parseInt(ee.getCases())-temp));
    			
    			
    			for (int i = 0;i < (Integer.parseInt(ee.getCases())-temp)/10; i++){
    				System.out.print("*");
    			}
    			System.out.println();  
    			System.out.println(ee.getDate() + "case increase:" + (Integer.parseInt(ee.getCases())-temp));     
    			temp=Integer.parseInt(ee.getCases());
    		}
    		
    		
            
//            HttpResponse<JsonNode> response = Unirest.get("https://ebola-outbreak.p.mashape.com/cases")
//            		.header("X-Mashape-Key", "1g4fw4E4JImshe9NzUBvJWkE72Isp1tJIo8jsnVfzHeNYD0GTK")
//            		.asJson();
//            Builder builder = target.request(MediaType.APPLICATION_XML);
//            GeoIP geoip = builder.get(GeoIP.class);
//            System.out.println(geoip.getCountryName());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
}
