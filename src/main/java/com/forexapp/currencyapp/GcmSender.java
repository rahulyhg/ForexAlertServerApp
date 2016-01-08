package com.forexapp.currencyapp;



import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

// NOTE:
// This class emulates a server for the purposes of this sample,
// but it's not meant to serve as an example for a production app server.
// This class should also not be included in the client (Android) applicaiton
// since it includes the server's API key. For information on GCM server
// implementaion see: https://developers.google.com/cloud-messaging/server
public class GcmSender {

    public static final String API_KEY = "AIzaSyD4K_acTpbhHdSUYkPO4EaTfbozIjBnsAI";
    
    JSONObject jGcmData;
    JSONObject jData;
    public static final String MESSAGE = "Rate Alert";
    
    
    public boolean sendalert(ArrayList arr)
    {
    	boolean res=false;
    	try
    	{
    	jGcmData = new JSONObject();
    	jData = new JSONObject();
    	jData.put("message", MESSAGE);
    	
    	jGcmData.put("registration_ids", arr);
    	// jGcmData.put("to", arr.get(0));
    	jGcmData.put("data", jData);

        // Create connection to send GCM Message request.
        URL url = new URL("https://android.googleapis.com/gcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "key=" + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Send GCM message content.
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(jGcmData.toString().getBytes());

        // Read GCM response.
        InputStream inputStream = conn.getInputStream();
        String resp = IOUtils.toString(inputStream);
        System.out.println(resp);
        System.out.println("Check your device/emulator for notification or logcat for " +
                "confirmation of the receipt of the GCM message.");
        res=true;
    	
    	}
    	catch(Exception e)
    	{
    		 System.out.println("Unable to send GCM message.");
             System.out.println("Please ensure that API_KEY has been replaced by the server " +
                     "API key, and that the device's registration token is correct (if specified).");
             e.printStackTrace();
    	}
    	return res;
    }
    

   

}

