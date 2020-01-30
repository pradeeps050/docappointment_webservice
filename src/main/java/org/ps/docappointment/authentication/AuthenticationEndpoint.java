package org.ps.docappointment.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ps.docappointment.database.DBConnection;
import org.ps.docappointment.model.Doctor;

@Path("/authentication")
public class AuthenticationEndpoint {
	
	@GET
    @Secured
    @Produces({MediaType.APPLICATION_JSON})
    public List<Doctor> getDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "SELECT * from  doctorsdetail";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Doctor doctor = null;
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			if (result != null) {
				while(result.next()) {
					doctor = new Doctor();
					doctor.setDocId(result.getInt("doc_id"));
					doctor.setName(result.getString("name"));
					doctor.setSpecialist(result.getString("specialist"));
					doctor.setTiming(result.getString("timing"));
					doctor.setVenue(result.getString("venue"));
					doctor.setFee(result.getInt("fee"));
					doctors.add(doctor);
				}
			} else {
				System.out.println("Got NULL ResultSet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
		return doctors;
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			System.out.println(username + "|| " + password);
			// Authenticate the user using the credentials provided
			authenticate(username, password);
			// Issue a token for the user
			String token = issueToken(username);
			// Return the token on the response
			Response response = Response.ok(token).build();
			//response.o
			System.out.println(response.toString());
			return token;
		} catch (Exception e) {
			return "FORBIDDEN";//Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
		String query = "SELECT id from  patient where FirstName = ? and Password = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			result = statement.executeQuery();
			if (result != null) {
				result.next();
				System.out.println("User id :::: " + result.getInt("id"));
			} else {
				System.out.println("Got NULL ResultSet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	return "qwertyuiopasdfghjklzxcvbnm";
    } 

}
