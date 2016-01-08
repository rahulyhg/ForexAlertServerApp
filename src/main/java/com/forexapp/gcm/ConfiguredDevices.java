package com.forexapp.gcm;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forexapp.currencyapp.RegisteredDevicesDetails;
import com.forexapp.database.Database;

/**
 * Servlet implementation class ConfiguredDevices
 */
public class ConfiguredDevices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfiguredDevices() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter() ;

		response.getWriter().append("Currently Registered Devices at Forex Alert Server are ");
		
		
		Map<String, BigDecimal> registerdata=new HashMap<String, BigDecimal>();
		
		List<RegisteredDevicesDetails> list=new ArrayList<RegisteredDevicesDetails>();
		
		try {
			registerdata = Database.getDB().getRegisteredTableDate();
	if(!(registerdata.isEmpty()))
		{
		for (Map.Entry<String, BigDecimal> entry : registerdata.entrySet()) {
			
			RegisteredDevicesDetails rd=new RegisteredDevicesDetails();
			rd.setToken(entry.getKey());
			rd.setRate(entry.getValue());
			
			list.add(rd);
			
				
			
		}
		
		request.getSession(true).setAttribute("list",list);
		
		RequestDispatcher rdis= request.getRequestDispatcher("/index.jsp");
		rdis.forward(request, response);
		return;
		
		
		}
		else
		{
			request.getSession(true).setAttribute("error","No Data");
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
