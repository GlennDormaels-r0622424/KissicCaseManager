package be.kissic.authorisation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AuthorisationDto {
	private long requestId;
	private String domain;
	private String type;
	private Date date;
	private String postalCode;
	private String officeCode;
	private String assignee;
	private String targetOffice;
	private String motivation;
	
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getTargetOffice() {
		return targetOffice;
	}
	public void setTargetOffice(String targetOffice) {
		this.targetOffice = targetOffice;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("requestId",getRequestId());
		json.put("domain",getDomain());
		json.put("type",getType());
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		if(this.getDate() != null){
			json.put("date", sdfDate.format(this.getDate()));
		}else{
			json.put("date", "NA");
		}
		json.put("date",getDate());
		json.put("postalCode",getPostalCode());
		json.put("officeCode",getOfficeCode());
		json.put("assignee",getAssignee());
		json.put("targetOffice",getTargetOffice());
		json.put("motivation",getMotivation());
		
		return json;
	}
	
	public JSONArray getAuthorisationRequests(Connection con) throws SQLException {
		JSONArray requests = new JSONArray();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from kcm_authorisation_request");
		
		while(rs.next()) {
			AuthorisationDto authorisation = new AuthorisationDto();
			authorisation.setRequestId(rs.getLong("requestid"));
			authorisation.setDomain(rs.getString("domain"));
			authorisation.setType(rs.getString("type"));
			authorisation.setDate(rs.getDate("date"));
			authorisation.setPostalCode(rs.getString("postalcode"));
			authorisation.setOfficeCode(rs.getString("officecode"));
			authorisation.setAssignee(rs.getString("assignee"));
			authorisation.setTargetOffice(rs.getString("targetoffice"));
			authorisation.setMotivation(rs.getString("motivation"));
			
			requests.add(authorisation.toJSON());
		}
		
		rs.close();
		st.close();
		
		return requests;
	}
	
	public JSONArray getAuthorisationRequest(Connection con, long requestid) throws SQLException {
		JSONArray request = new JSONArray();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from kcm_authorisation_request where requestid =" + requestid);
		
		while(rs.next()) {
			AuthorisationDto authorisation = new AuthorisationDto();
			authorisation.setRequestId(rs.getLong("requestid"));
			authorisation.setDomain(rs.getString("domain"));
			authorisation.setType(rs.getString("type"));
			authorisation.setDate(rs.getDate("date"));
			authorisation.setPostalCode(rs.getString("postalcode"));
			authorisation.setOfficeCode(rs.getString("postalcode"));
			authorisation.setAssignee(rs.getString("assignee"));
			authorisation.setTargetOffice(rs.getString("targetoffice"));
			authorisation.setMotivation(rs.getString("motivation"));
			
			request.add(authorisation.toJSON());
		}
		
		rs.close();
		st.close();
		
		return request;
	}
}
