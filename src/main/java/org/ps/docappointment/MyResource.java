package org.ps.docappointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ps.docappointment.authentication.Secured;
import org.ps.docappointment.database.DBConnection;
import org.ps.docappointment.model.User;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        return "Got it!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
    	System.out.println("User Info ::: " + user.toString());
    	String SQL_INSERT = "INSERT INTO patient (FirstName, LastName, Mobile, Email, Password) VALUES (?,?,?,?,?)";
    	int row = 0;
    	Connection connection = DBConnection.getConnection();
    	PreparedStatement preparedStatement = null;
    	try {
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getMobile());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			row = preparedStatement.executeUpdate();
			System.out.println("Row inserted >> " + row);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
		    if (preparedStatement != null) {
		        try {
		            preparedStatement.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}
    	System.out.println(row);
    	return user;
    }
    
   
}
