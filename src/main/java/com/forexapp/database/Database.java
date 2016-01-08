package com.forexapp.database;

import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import org.h2.tools.DeleteDbFiles;

import com.forexapp.currencyapp.ReturnValues;
import com.forexapp.currencyapp.ScheduleExecutor;

public class Database {
	
	private static final String DB_DRIVER = "org.h2.Driver";
    //private static final String DB_CONNECTION = "jdbc:h2:~/test";
    
	//private static final String DB_CONNECTION ="jdbc:h2:~/db/test;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE";
	private static final String DB_CONNECTION ="jdbc:h2:file:${OPENSHIFT_DATA_DIR}test;MODE=MYSQL";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    private static Database db=null;
    
    private Database() throws SQLException
    {
    	initializedb();
    	
    	
    }
     
    public static Database getDB() throws SQLException
    {
    	if(db==null)
    	{
    		db=new Database();
    	}
    	
    	return db;
    }
    
    public void initializedb() throws SQLException
    {
    	Connection connection = getDBConnection();
          Statement stmt = null;
          DeleteDbFiles.execute("~", "test", true);


          try {
              connection.setAutoCommit(true);

              stmt = connection.createStatement();
              
              DatabaseMetaData dbm = connection.getMetaData();

              ResultSet tables = dbm.getTables(null, null, "RegisteredDevice", null);
              if (tables.next()) {
                // Table exists
              }
              else {
                  stmt.execute("CREATE TABLE RegisteredDevice(Token varchar(255) PRIMARY KEY, Rate DECIMAL(15, 2))");
              }
              
              
               stmt.close();
              connection.commit();
          } catch (SQLException e) {
              System.out.println("Exception Message " + e.getLocalizedMessage());
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              connection.close();
          }
      }
    
    
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public Map getRegisteredTableDate()
    {
    	Connection connection = getDBConnection();
    	Map<String,BigDecimal> registerdata=new HashMap<String,BigDecimal>();
   	 
    	   
   	 PreparedStatement selectPreparedStatement = null;
   	 String SelectQuery = "select * from RegisteredDevice";
   	  try {
             connection.setAutoCommit(true);
             selectPreparedStatement=connection.prepareStatement(SelectQuery);
             ResultSet  rs = selectPreparedStatement.executeQuery();   
                        
             while (rs.next()) {
            	 registerdata.put(rs.getString("token"), rs.getBigDecimal("rate"));
             
             }
             
             
             
             selectPreparedStatement.close();
             connection.commit();
   	
   	  	}
   	  catch(Exception e)
   	  {
   		  e.printStackTrace();
   	  }
   	  
   	  return registerdata;
    }
    
    
    public ArrayList <String> gettokens(BigDecimal rate)
    {
    	ArrayList<String> res=new ArrayList<String>();
    	 Connection connection = getDBConnection();
    	 
    	 
   
    	 PreparedStatement selectPreparedStatement = null;
    	 String SelectQuery = "select token from RegisteredDevice where rate >" + "(?)";
    	  try {
              connection.setAutoCommit(true);
              selectPreparedStatement=connection.prepareStatement(SelectQuery);
              selectPreparedStatement.setBigDecimal(1, rate);
             // selectPreparedStatement.setDouble(2, rate);
              ResultSet rs = selectPreparedStatement.executeQuery();
              while (rs.next()) {
            	  res.add(rs.getString("token"));
               }
               selectPreparedStatement.close();
              
               connection.commit();
    	  }
    	  catch(Exception e)
    	  {
    		  e.printStackTrace();
    	  }
    	return res;
    }
    
    
    
    public String insertentry(String token,BigDecimal rate) throws SQLException
    {
    	String ret;
    	 Connection connection = getDBConnection();
         PreparedStatement insertPreparedStatement = null;
         PreparedStatement selectPreparedStatement = null;
         PreparedStatement updatePreparedStatement = null;



         String InsertQuery = "INSERT INTO RegisteredDevice" + "(token, rate) values" + "(?,?)";
         String SelectQuery = "select * from RegisteredDevice where token=" + "(?)";
         String updateQuery = "Update RegisteredDevice set rate=" + "(?)" + "where token=" + "(?)";
         try {
             connection.setAutoCommit(true);
            
             selectPreparedStatement = connection.prepareStatement(SelectQuery);
             selectPreparedStatement.setString(1, token);
             ResultSet rs = selectPreparedStatement.executeQuery();
             
             
             if(rs.next())
             {
                 selectPreparedStatement.close();
                 updatePreparedStatement=connection.prepareStatement(updateQuery);
                 updatePreparedStatement.setBigDecimal(1, rate);
                 updatePreparedStatement.setString(2, token);
                 updatePreparedStatement.executeUpdate();
                 updatePreparedStatement.close();
            	 ret=ReturnValues.TokenExist;

                 
                 
             }
             else
             {
             insertPreparedStatement = connection.prepareStatement(InsertQuery);
             insertPreparedStatement.setString(1, token);
             insertPreparedStatement.setBigDecimal(2, rate);
             insertPreparedStatement.executeUpdate();
             insertPreparedStatement.close();
             System.out.println("H2 Database inserted through PreparedStatement");
             ret=ReturnValues.TokenInserted;
            
             }
             
          /*   while (rs.next()) {
                // System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
            	 System.out.println(" Token "+rs.getString("token") + " Rate " + rs.getInt("rate"));
             }*/
            
             connection.commit();
         } catch (SQLException e) {
        	 ret=ReturnValues.DBError;
             System.out.println("Exception Message " + e.getLocalizedMessage());
         } catch (Exception e) {
        	 ret=ReturnValues.DBError;
             e.printStackTrace();
         } finally {
             connection.close();
         }
         return ret;
     }
    
    
   public static void main(String[] args) throws Exception {
        try {
            // delete the H2 database named 'test' in the user home directory
            DeleteDbFiles.execute("~", "test", true);
            Database db=new Database();
            String tok="dfUv8HR7VW8:APA91bHn1-N3OYJDCfqq2-maIk7LH7IxCz8v6ImdV9WnMtkx0CbNDsHDBtLo1WiueF693p8XiHR0fodDhed1-cRWT0UIpp2urmdAX-h_FZZoF1V-tyebjxxPJn-INC1ZC7ZWSjFDD3UN";
           BigDecimal b=new BigDecimal(12.1);
           String t= db.insertentry(tok,b);
           System.out.println("return Values are-->>" + t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
