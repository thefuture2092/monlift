/**
 * 
 */
package test.didia.monlift;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.didia.monlift.entities.Car;
import me.didia.monlift.entities.Lift;
import me.didia.monlift.entities.User;
import me.didia.monlift.exceptions.AuthentificationErrorException;
import me.didia.monlift.exceptions.DuplicateValueException;
import me.didia.monlift.managers.LiftManager;
import me.didia.monlift.managers.UserManager;
import me.didia.monlift.requests.CreateCarRequest;
import me.didia.monlift.requests.CreateLiftRequest;
import me.didia.monlift.requests.PromoteUserRequest;
import me.didia.monlift.requests.CreateUserRequest;
import me.didia.monlift.securities.AuthentificationManager;
import me.didia.monlift.securities.Session;

/**
 * @author didia
 * 
 * This class is a test helper which provides Mock object obtained from the datastore
 */
public class MockFactory {
	public static final String MOCK_CAR_NAME = "Volvo new model";
	public static final String MOCK_CAR_DESCRIPTION = "5 seat, imatriculation 483 CKR, Blue";
	public static final String MOCK_CAR_MATRICULE = "483 CKR";
	
	public static final String MOCK_USER_FIRSTNAME = "John";
	public static final String MOCK_USER_LASTNAME = "Doe";
	public static final String MOCK_USER_NUMBER = "15819999999";
	public static final String MOCK_USER_PASSWORD = "xyz123kgh";
	
	public static final String MOCK_LIFT_FROM = "Québec";
	public static final String MOCK_LIFT_TO = "Montréal";
	public static final double MOCK_LIFT_PRICE = 15.0;
	public static final String MOCK_LIFT_MEETING_PLACE = "Pavillon DesjarDins, Université Laval";
	public static final int MOCK_LIFT_TOTAL_PLACE = 4;
	
	private static User m_userInstance;
	
	private static Lift m_lift;
	

	

	public static CreateCarRequest getCreateCarRequest() {
		CreateCarRequest request = new CreateCarRequest();
		request.setName(MOCK_CAR_NAME);
		request.setDescription(MOCK_CAR_DESCRIPTION);
		request.setMatricule(MOCK_CAR_MATRICULE);
		
		return request;
	}
	
	public static CreateUserRequest getRegisterUserRequest() {
		return getRegisterRequest(MOCK_USER_FIRSTNAME, MOCK_USER_LASTNAME, 
				                  MOCK_USER_PASSWORD, MOCK_USER_NUMBER);
		
	}
	
	public static List<CreateUserRequest> getMultipleRegisterRequest(int number) {
		
		ArrayList<CreateUserRequest> requests = new ArrayList<CreateUserRequest>(number);
		
		CreateUserRequest request = getRegisterUserRequest();
		requests.add(request);
		for(int i=0; i<number; i++)
		{
			String firstname = request.getFirstname() + "-"+ i;
			String lastname = request.getLastname() + "-" + i;
			String password = request.getPassword() + "-" + i;
			String phone = request.getPhone() + "-" + i;
			CreateUserRequest aRequest = getRegisterRequest(firstname, lastname,
														  password, phone);
			requests.add(aRequest);
			
		}
		
		return requests;
	}
	
	private static CreateUserRequest getRegisterRequest(String p_firstname, 
													  String p_lastname, 
													  String p_password, 
													  String p_phone) {
		CreateUserRequest request = new CreateUserRequest();
		request.setFirstname(p_firstname);
		request.setLastname(p_lastname);
		request.setPassword(p_password);
		request.setPhone(p_phone);
		String email = request.getFirstname() + "." + request.getLastname() + "@example.com";
		request.setEmail(email);
		
		return request;
	}
	

	
	public static CreateLiftRequest getCreateLiftRequest() {

		Calendar.getInstance().add(Calendar.DAY_OF_MONTH, 1);
		return getCreateLiftRequest(MOCK_LIFT_FROM, MOCK_LIFT_TO, 
								  MOCK_LIFT_MEETING_PLACE, MOCK_LIFT_PRICE,
								  MOCK_LIFT_TOTAL_PLACE, Calendar.getInstance().getTime());

	}
	
	public static List<CreateLiftRequest> getMultipleCreateLiftRequest(int number) {

		ArrayList<CreateLiftRequest> requests = new ArrayList<CreateLiftRequest>(number);
		
		CreateLiftRequest request = getCreateLiftRequest();
		
		requests.add(request);
		
		for(int i=0; i<number; i++)
		{
			request = getCreateLiftRequest();
			Calendar.getInstance().add(Calendar.DAY_OF_MONTH, 1*i);
			request.setTime(Calendar.getInstance().getTime());
			
			requests.add(request);
			
		}
		
		
		return requests;
	}
	
	public static PromoteUserRequest getPromoteUserRequest() {
		PromoteUserRequest request = new PromoteUserRequest("Zuckerberg");
		request.validate();
		return request;

	}
	
	private static CreateLiftRequest getCreateLiftRequest(
														  String p_from,
														  String p_to,
														  String p_meetingPlace,
														  double p_price,
														  int p_totalPlace,
														  Date p_date ) {
		
		CreateLiftRequest request = new CreateLiftRequest();
		request.setFrom(p_from);
		request.setTo(p_to);
		request.setMeetingPlace(p_meetingPlace);
		request.setPrice(p_price);
		request.setTotalPlace(p_totalPlace);
		request.setTime(p_date);
		
		return request;
	}
	
	
	public static ArrayList<Lift> getMultipleLifts() {
		
		return null;
	}
}
