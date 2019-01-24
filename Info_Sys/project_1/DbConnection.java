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
public class DbConnection extends JFrame {
    public String firstName, lastName, address, email, phoneNumber, city, zipCode, state;
   // constructor connects to database, queries database,
   // processes results and displays results in window
   public DbConnection(String[] inputInfo, int type)
   {
  
      // connect to database S_P_SP and query database
      try {
         // load database driver class
         Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
         // connect to database
         Connection connection = DriverManager.getConnection(
            "jdbc:odbc:addressBook" );
         // create Statement to query database
         Statement statement = connection.createStatement();
         

         // query database, type 0 = search, 1 = insert, 2 = delete, 3 = update
         if (type == 0){
             ResultSet resultSet = statement.executeQuery("SELECT * FROM PhoneNumber INNER JOIN "
                        + "(Address INNER JOIN (Email INNER JOIN Names ON "
                        + "Email.email_id = Names.email_id) ON "
                        + "Address.address_id = Names.address_id) ON PhoneNumber.phone_id = Names.phone_id "
                        + "WHERE Names."+ inputInfo[0] + " = \'"+ inputInfo[1] + "\'");
             //New data processing, saves to string array to be returned
             while(resultSet.next()){
              firstName = resultSet.getString("FirstName");
              lastName = resultSet.getString("LastName");
              email = resultSet.getString("Emails");
              address = resultSet.getString("Street");
              phoneNumber = resultSet.getString("PhoneNumbers");
              city = resultSet.getString("City");
              zipCode = resultSet.getString("ZipCode");
              state = resultSet.getString("State");
              }
         }else if (type == 1){
             statement.executeUpdate("INSERT INTO Names (FirstName, LastName) Values (\'"+ inputInfo[0]
                        + "\', \'" + inputInfo[1] + "\')");
             //Gets the id number for the new row in the Name table and saves it so it can be used to set the id's of the other new rows in the other tables.
             ResultSet rs = statement.executeQuery("SELECT name_id FROM NAMES WHERE Names.FirstName = \'"+ inputInfo[0] +"\'");
             int tableKey = 0;
             while(rs.next()){
                 tableKey = rs.getInt("name_id");
             }
             statement.executeUpdate("INSERT INTO Address (address_id, City, Street, ZipCode, State) Values ("+ tableKey +", \'"+ inputInfo[2]
                        + "\', \'" + inputInfo[3] + "\', \'" + inputInfo[4] 
                        + "\', \'" + inputInfo[5] + "\')");
            statement.executeUpdate("INSERT INTO Email (email_id, Emails) Values ("+ tableKey +", \'"+ inputInfo[6] + "\')");            
            statement.executeUpdate("INSERT INTO PhoneNumber (phone_id, PhoneNumbers) Values ("+ tableKey +", \'"+ inputInfo[7] + "\')");
            statement.executeUpdate("UPDATE Names SET phone_id = " + tableKey + ", address_id = " + tableKey + ", email_id = " + tableKey
                    + " WHERE Names.FirstName = \'" + inputInfo[0] + "\'");
         }else if(type == 2){
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
         }else if (type == 3){
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
         }else if(type == 4){
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
                 
                 writer.write("TABLE " + inputInfo[0]);
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
                  writer.write("END");
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

        /* StringBuffer results = new StringBuffer();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            for ( int i = 1; i <= numberOfColumns; i++ ){
                results.append( metaData.getColumnName( i )
                    + "\t | \t" );
            }
            results.append( "\n" );
            while ( resultSet.next() ) {
                for ( int i = 1; i <= numberOfColumns; i++ ){
                    results.append( resultSet.getObject( i )
                        + "\t | " );
                }
                results.append( "\n" );
            }*/
         
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
       
       
       return new String[]{firstName, lastName, address, city, state, zipCode, email, phoneNumber};
       
   }
   // launch the application
   public static void main( String args[], String[] inputInfo, int type )
   {
       
      DbConnection window = new DbConnection(inputInfo, type);
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }
}