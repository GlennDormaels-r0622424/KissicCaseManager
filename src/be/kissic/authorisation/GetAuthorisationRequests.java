package be.kissic.authorisation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/GetAuthorisationRequest")
public class GetAuthorisationRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/kissic")
	private DataSource ds;
	
    public GetAuthorisationRequests() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String requestid_str = request.getParameter("requestid");
		Long requestid = null;
		try {
			requestid = Long.parseLong(requestid_str);
		} catch(NumberFormatException e) {
			System.out.println("Unable to parse requestId" + e.getMessage());
		}
		JSONObject resultObj = new JSONObject();
		JSONArray authorisations = new JSONArray();
		
		Connection con = null;
		AuthorisationDto authorisation = new AuthorisationDto();
		
		
		try {
			con = ds.getConnection();
			
			
			authorisations = authorisation.getAuthorisationRequest(con,requestid);
			
			resultObj.put("message", "Successfull Retrieving Requests");
			resultObj.put("success", true);
			resultObj.put("data", authorisations);
		}catch(SQLException ex) {
			System.out.println("Unable to get Requests" + ex.getMessage());
			resultObj.put("message", "ERROR: " + ex.getMessage());
			resultObj.put("success", false);
		}finally {
			try {
				con.close();
			}catch(SQLException ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
		
		String jsonString = resultObj.toJSONString();
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
