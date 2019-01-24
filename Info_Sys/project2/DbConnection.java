// Connect to Access Database using JDBC-ODBC Bridge
// Displaying the contents of table S in database S_P_SP.
// Java core packages
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
// Java extension packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class DbConnection extends JFrame {
	public String CustomerN, NumberOcc, SpecialService_ID, Room_ID, Date, CheckedIn, Clean,
					RoomTypeName;
	public JTable rstable;
	ArrayList<String> arrayL;
   // constructor connects to database, queries database,
   // processes results and displays results in window
   public DbConnection(String[] inputInfo, int type)
   
   {
      // connect to database S_P_SP and query database
      try {
         // load database driver class
         Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
         // connect to database
         Connection connection = DriverManager.getConnection( "jdbc:odbc:lodgDb" );
         // create Statement to query database
         Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_UPDATABLE);
         

         //Query for rooms information
         if (type == 0){
        	 ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers RIGHT JOIN "
        	 		+ "(Room LEFT JOIN RoomType ON Room.RoomType_ID = RoomType.RoomType_ID) "
        	 		+ "ON Customers.Customer_ID = Room.Customer_ID"
		        	+ " WHERE Room."+ inputInfo[0] + " = \'"+ inputInfo[1] + "\'");
             //New data processing, saves to string array to be returned
             while(resultSet.next()){
             CustomerN = resultSet.getString("CustomerN");
             NumberOcc = resultSet.getString("NumberOcc");
             SpecialService_ID = resultSet.getString("SpecialService_ID");
             Room_ID = resultSet.getString("Room_ID");
             CheckedIn = resultSet.getString("CheckedIn");
             Clean = resultSet.getString("Clean");
             RoomTypeName = resultSet.getString("RoomTypeName");
             Date = resultSet.getString("Date");
              }
         //Query for Customers Names
         }else if (type == 1){
        	 ResultSet resultSet = statement.executeQuery("SELECT " + inputInfo[0] + " FROM " + inputInfo[1] + " ORDER BY "
        			 + inputInfo[2]);
        	 arrayL = new ArrayList<String>(resultSet.getFetchSize());
        	 int i = 0;
             while(resultSet.next()){
            	 arrayL.add(i, resultSet.getString(1));
            	 i++;
              }
         //Sends room information for checking in guest to room table
         }else if (type == 2){
        	 statement.executeUpdate("UPDATE Room SET Room.CheckedIn = \'Y\', Room.Customer_ID = "+ inputInfo[1] +", Room.RoomType_ID = "+ inputInfo[2] +","
        	 				+ " Room.NumberOcc = "+ inputInfo[3] +", Room.SpecialService_ID = "+ inputInfo[4] +", Room.Date = "+ inputInfo[5]
        	 				+ " WHERE Room.Room_ID = " + inputInfo[0]);
        	 /*popup*/
        	 PopUpWindow popWin = new PopUpWindow(inputInfo[6] + " Successfully Checked Into Room " + inputInfo[0]);
        //Query for adding a new customer to the database
         }else if(type == 3){
        	 ResultSet resultSet = statement.executeQuery("SELECT Customer_ID FROM Customers");
        	 //Selects the last Customer_ID to generate a new one.
        	 int newID = 0;
        	 while(resultSet.next()){
        		 newID = Integer.parseInt(resultSet.getString("Customer_ID"));
        	 }
        	 statement.executeUpdate("INSERT INTO Customers (Customer_ID, CustomerN, CardNumber, ZipCode, PhoneNumber) "
        	 		+ "Values ("+ (newID+1) +", \'" + inputInfo[0] + "\', \'" + inputInfo[1] + "\', \'" + inputInfo[2] 
						+ "\', \'" + inputInfo[3] + "\')");
        	 PopUpWindow popWin = new PopUpWindow("New Customer " + inputInfo[0] + " Added To Database!");
        //Query for creating an array list filled with a selected customers information for editing later.
         }else if (type == 4){
        	 ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers "
        			 + "WHERE Customers.customerN = \'" + inputInfo[0]+ "\'");
        	 arrayL = new ArrayList<String>();
             while(resultSet.next()){
            	 arrayL.add(resultSet.getString(2));
            	 arrayL.add(resultSet.getString(3));
            	 arrayL.add(resultSet.getString(4));
            	 arrayL.add(resultSet.getString(5));
            	 arrayL.add(resultSet.getString(1));
             }
          //Query for updating a customers information.
          }else if(type == 5){
            	 statement.executeUpdate("UPDATE Customers SET Customers.CardNumber = \'"+ inputInfo[1] +"\', "
            			 + "Customers.ZipCode = \'"+ inputInfo[2] +"\', Customers.PhoneNumber = \'"+ inputInfo[3] +"\' "
            			 		+ "WHERE Customers.customerN = \'"+ inputInfo[0] +"\'");
            	 /*popup*/
            	 PopUpWindow popWin = new PopUpWindow("Updated Customer "+ inputInfo[0] +" Information.");
          }else if(type == 6){
            	 statement.executeUpdate("DELETE FROM Customers WHERE Customers.customerN = \'"+ inputInfo[0] +"\' "
            	 		+ "AND Customers.CardNumber = \'"+ inputInfo[1] +"\'");
            	 /*popup*/
            	 PopUpWindow popWin = new PopUpWindow("Deleted Customer "+ inputInfo[0] +" Information.");
         }else if (type == 7){
        	 ResultSet resultSet = statement.executeQuery(inputInfo[0]);
        	 arrayL = new ArrayList<String>();
        	 while(resultSet.next()){
        		 arrayL.add(resultSet.getString(2));
            	 arrayL.add(resultSet.getString(3));
        	 }
         }else if (type == 8){
        	 ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers");
        	 rstable = new JTable(makeTable(resultSet));
         }else if(type == 1000){
        	 //Gets id number to be used to delete all rows that contains that id number
        	 ResultSet rs = statement.executeQuery("SELECT name_id FROM NAMES WHERE Names.FirstName = \'"+ inputInfo[0] +"\'");
        	 int tableKey = 0;
        	 while(rs.next()){
        		 tableKey = rs.getInt("name_id");
        		 
        	 }
        	
        	statement.executeUpdate("DELETE FROM Names WHERE Names.FirstName = \'" + inputInfo[0] 
        			+ "\' AND Names.LastName = \'" + inputInfo[1] + "\'");
        	statement.executeUpdate("DELETE FROM Address WHERE Address.address_id = " + tableKey);
        	statement.executeUpdate("DELETE FROM Email WHERE Email.email_id = " + tableKey);
        	statement.executeUpdate("DELETE FROM PhoneNumber WHERE PhoneNumber.phone_id = " + tableKey);
         }else if (type == 100){
        	 ResultSet rs = statement.executeQuery("SELECT name_id FROM NAMES WHERE Names.FirstName = \'"+ inputInfo[0] 
        			 + "\' AND Names.LastName = \'" + inputInfo[1] +"\'");
        	 int tableKey = 0;
        	 while(rs.next()){
        		 tableKey = rs.getInt("name_id");
        	 }
        	 //statement.executeUpdate("UPDATE Names SET LastName = \'" + inputInfo[1] + "\' WHERE Names.name_id = " + tableKey);
        	 statement.executeUpdate("UPDATE Address SET City = \'"+ inputInfo[2] +"\', Street = \'"+ inputInfo[3] +"\', ZipCode = \'"+ inputInfo[4] +"\', "
        	 		+ "State = \'"+ inputInfo[5] +"\' WHERE Address.address_id = " + tableKey);
        	 statement.executeUpdate("UPDATE Email SET Emails = \'"+ inputInfo[6] +"\' WHERE Email.email_id = " + tableKey);
        	 statement.executeUpdate("UPDATE PhoneNumber SET PhoneNumbers = \'"+ inputInfo[7] +"\' WHERE PhoneNumber.phone_id = " + tableKey);
         }else if(type == 400){
        	 ResultSet resultSet;
        	 if(inputInfo[0] == "Joined"){
        		  resultSet = statement.executeQuery("SELECT Names.FirstName, Names.LastName, "
        		  		+ "Address.Street, Address.City, Address.ZipCode, Address.State, Email.Emails, PhoneNumber.PhoneNumbers "
        		  		+ "FROM PhoneNumber INNER JOIN (Address INNER JOIN (Email INNER JOIN Names ON "
		        		+ "Email.email_id = Names.email_id) ON Address.address_id = Names.address_id) ON PhoneNumber.phone_id = Names.phone_id");
        	 }else{
        		 resultSet = statement.executeQuery("SELECT * FROM " + inputInfo[0]); 
        	 }
        	 
        	 BufferedWriter writer = null;
 			try
 			{
 			    writer = new BufferedWriter( new FileWriter(inputInfo[0] + ".txt"));
 			    
 			    writer.write("\t\tTABLE " + inputInfo[0]);
 			    writer.newLine();
 			    
 	 			ResultSetMetaData metaData = resultSet.getMetaData();
 	 			int numberOfColumns = metaData.getColumnCount();
 	 			for ( int i = 1; i <= numberOfColumns; i++ ){
 	 				writer.write( metaData.getColumnName( i ) + "\t | \t" );
 	 			}
 	 			writer.newLine();

 	 			while ( resultSet.next() ) {
 	 				for ( int i = 1; i <= numberOfColumns; i++ ){
 	 					writer.write( resultSet.getObject( i ) + "\t | \t" );
 	 				}
 	 				writer.newLine();
 	 			}
 	 			writer.write("\t\tEND");
 			}
 			catch ( IOException e1)
 			{}
 			finally
 			{
 			    try
 			    {
 			        if ( writer != null)
 			        writer.close();
 			    }
 			    catch ( IOException e1)
 			    {}
 			}
         }
         
         statement.close();
         connection.close();

      }  // end try

      // detect problems interacting with the database
      catch ( SQLException sqlException ) {
         JOptionPane.showMessageDialog( null,
            sqlException.getMessage(), "Database Error",
            JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }
      // detect problems loading database driver
      catch ( ClassNotFoundException classNotFound ) {
         JOptionPane.showMessageDialog( null,
            classNotFound.getMessage(), "Driver Not Found",
            JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }
   }  // end DbConnection constructor definition
   
   public String[] ProcessResults(){
	   
	   if(CustomerN == null)
		   CustomerN = "NA";
	   if(NumberOcc == null)
		   NumberOcc = "NA";
	   if(SpecialService_ID == null)
		   SpecialService_ID = "NA";
	   if(Room_ID == null)
		   Room_ID = "NA";
	   if(Date == null)
		   Date = "NA";
	   if(CheckedIn == null)
		   CheckedIn = "NA";
	   if(Clean == null)
		   Clean = "NA";
	   if(RoomTypeName == null)
		   RoomTypeName = "NA";
	   return new String[]{CheckedIn, NumberOcc, Clean, CustomerN, RoomTypeName, Date, SpecialService_ID, Room_ID};
	   
   }
   public static DefaultTableModel makeTable(ResultSet rs) throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    
	    return new DefaultTableModel(data, columnNames);

	}
   // launch the application
   public static void main( String args[], String[] inputInfo, int type )
   {
	   
      DbConnection window = new DbConnection(inputInfo, type);
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }
}  // end class DbConnection
