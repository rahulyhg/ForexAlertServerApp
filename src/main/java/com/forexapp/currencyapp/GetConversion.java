package com.forexapp.currencyapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GetConversion {
	
	String baseurl;
	String query;
	String env;
	String url;
	OutputStreamWriter wr;
	 BufferedReader rd;
	 Rate rate;
	 
	 
	
	  final String error="ERROR";
	 String res=error;
	
	public GetConversion()
	{
		rate=new Rate();
	}
	 
	 public String getresponse()
	 {
		 
		 	 baseurl="http://query.yahooapis.com/v1/public/yql?q=";
			 query="select * from yahoo.finance.xchange where pair in ('USDINR')";
			 env="&format=json&env=store://datatables.org/alltableswithkeys";
			 
			 StringBuffer r=new StringBuffer();
			
			
				try {
					url = baseurl+URLEncoder.encode(query, "UTF-8") +env;
					URL ur=new URL(url);
					URLConnection conn=ur.openConnection();
					conn.setDoOutput(true);
					 wr = new OutputStreamWriter(conn.getOutputStream());
				    wr.flush();
				    
				    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				    
				   
				    while ((res = rd.readLine()) != null) {
				    r.append(res);
				      // System.out.println(res);
				       
				    }
				    
				}
				    catch (Exception e1) {
						// TODO Auto-generated catch block
				    	res=error;
						e1.printStackTrace();
					}
				    return r.toString();
		 
		 
	 }
	 
	 public boolean parseGson(String res)
	 {
		
		 System.out.println("Response is-->>" + res);
		 
		 JsonParser parser = new JsonParser();
		 JsonObject rootObj = parser.parse(res).getAsJsonObject();
		 
		 JsonObject rateObj = rootObj.getAsJsonObject("query")
				    .getAsJsonObject("results").getAsJsonObject("rate");
		 
		 String id=rateObj.get("id").getAsString();
		 String r=rateObj.get("Rate").getAsString();
		 
		 rate.setId(id);
		 rate.setRate(r);

		 System.out.println("Id is-->>" + id);
		 System.out.println("Name-->>" + r);
		 return true;
 
		 
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
	Timer t=new Timer();
	ScheduleExecutor st=new ScheduleExecutor(10);
	t.schedule(st, 0, 5000);
			
	} 
	

		
		
	}
