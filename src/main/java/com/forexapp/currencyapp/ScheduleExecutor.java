package com.forexapp.currencyapp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Generated;

import com.forexapp.database.Database;

public class ScheduleExecutor extends TimerTask{

	int timertask;
	Date d;
	
	public ScheduleExecutor(int time)
	{
		this.timertask=time;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BigDecimal r;
		ArrayList<String> arr=new ArrayList<String>();
		d=new Date();
		System.out.println("Current Time-->>" + d.getTime());
		
	GetConversion conversion=new GetConversion();
		
		String response= conversion.getresponse();
		boolean sucess=false;
			
		if(response!=conversion.error)
		{
			sucess=conversion.parseGson(response);
			System.out.println("Value-->>" + sucess);
		}
	if(sucess)
	{
		System.out.println("Get Rate-->>" + conversion.rate.getRate());
		//r=Double.valueOf(conversion.rate.getRate());
		r= new BigDecimal(conversion.rate.getRate());
		
			try {
				arr=Database.getDB().gettokens(r);
				System.out.println("Value of array-->>" + arr);
				
				GcmSender gcm=new GcmSender();
				boolean t=gcm.sendalert(arr);
				if(t)
				{
					System.out.println("Alert Delivered Sucessfully");
				}
				else
				{
					System.out.println("Alert Failed");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
		
		
		
	}
	
	
	

}
