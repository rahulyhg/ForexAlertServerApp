package com.forexapp.gcm;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forexapp.currencyapp.ReturnValues;
import com.forexapp.currencyapp.ScheduleExecutor;
import com.forexapp.database.Database;

/**
 * Servlet implementation class RegisterRequest
 */
@WebServlet("/RegisterRequest")
public class RegisterRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() throws ServletException
    {
    	Timer t=new Timer();
    	ScheduleExecutor st=new ScheduleExecutor(10);
		//Time interval set to 15 minutes
    	t.schedule(st, 0, 900000);
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Temp
		
		String token="";
		String rate="";
		token=request.getParameter("token");
		rate=request.getParameter("rate");
		
		String res="";
		PrintWriter pw = response.getWriter() ;
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			if(!(token.isEmpty()) && !(rate.isEmpty()))
			{
				BigDecimal r=new BigDecimal(rate);
				res=Database.getDB().insertentry(token, r);
			}
				
			else
			{
				res="Token and Rate values are mandatory";
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath()).append("ID-->>").append(token).append("Rate-->>").append(rate);
		
		try {
			Map <String,BigDecimal>registerdata = Database.getDB().getRegisteredTableDate();
			
			pw.println("<html><table border=1><tr>"); 

			pw.println("<th>Token</th>");
			pw.println("<th>Rate</th></tr><tr>");
			
			for (Map.Entry<String, BigDecimal> entry : registerdata.entrySet()) {
				pw.println("<th>"+ entry.getKey() +"</th>");
				pw.println("<th>"+ entry.getValue() +"</th>");
				pw.println("<tr></tr>");
			}
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		pw.println("</tr></table></html>"); 
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath()).append(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
