package moofwd.towns.square.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model singleton class
 * @author Cesar Oyarzun
 *
 */
public class Model {
	private static Model model=null;
	
	public static List<Info> listAllMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			 add(new AlertVO("Alert:Be on the look out for a 2003 Black Honda Civic.", "is simply dummy text of the printing and typesetting industry", ""));
//			 add(new AlertVO("Alert:Be on the look out for a 2003 Black Honda Civic.", "is simply dummy text", ""));
	        add(new EventVO("Bristol Township Administrative Offices Closed on Friday, July 4.", "1 Franklin Town Blvd Apt 609", "MAR 05"));
	        add(new EventVO("Bristol Township Administrative Offices Closed on Friday, July 4.", "1 Franklin Town Blvd Apt 609", "DEC 17"));
	        add(new NewsVO("Bristol Township Administrative Offices Closed on Friday, July 4.", "1 Franklin Town Blvd Apt 609"));
	       
		}
	};
	
	public static List<Info> listNewsMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
	        add(new NewsVO("Bristol Township Administrative Offices Closed on Friday, July 4.", "address2"));
	    }
	};
	
	public static List<Info> listEventsMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new EventVO("Bristol Township Administrative Offices Closed on Friday, July 4.", "1 Franklin Town Blvd Apt 609", "MAR 05"));
	        add(new EventVO("Bristol Township Administrative Offices Closed on Friday, July 4.", "1 Franklin Town Blvd Apt 609", "DEC 17"));
	    }
	};
	
	public static List<SubmissionVO> listSubmissionMockup = new ArrayList<SubmissionVO>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new SubmissionVO("Pothole Reported", "1 Franklin Town Blvd Apt 609", "1:00 PM - 07/14/2014",true));
	        add(new SubmissionVO("Crime Tip Reported", "1 Franklin Town Blvd Apt 609", "8:00 AM - 02/24/2014",false));
	        add(new SubmissionVO("Road Kill Reported", "1 Franklin Town Blvd Apt 609", "8:00 AM - 02/24/2014",false));
	    }
	};
	
	public static List<Info> listPublicSafetyMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new PublicSafetyVO("View Police Blotter"));
	        add(new PublicSafetyVO("Fire Safety and Fire Tips"));
	        add(new PublicSafetyVO("Report a Crime"));
	    }
	};
	
	public static List<Info> listDepartmentMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new DepartmentVO("Parks and Recreation"));
	        add(new DepartmentVO("Fire Department"));
	        add(new DepartmentVO("Police Department"));
	        add(new DepartmentVO("Public Works"));
	        add(new DepartmentVO("Trash and Recycling"));
	        add(new DepartmentVO("Water and Sewer"));
	        add(new DepartmentVO("Tax Office"));
	        add(new DepartmentVO("Licenses and Inspections"));
	    }
	};
	
	public static List<Info> listTellUsPoliceMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new TellUsVO("Suspicious Activity"));
	        add(new TellUsVO("Crime Tip"));
	    }
	};
	
	public static List<Info> listTellUsStreetMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new TellUsVO("Report Pot Hole"));
	        add(new TellUsVO("Report Traffic Light Out"));
	        add(new TellUsVO("Report Road Kill"));
	    }
	};
	public static List<Info> listTellUsOthersMockup = new ArrayList<Info>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new TellUsVO("Report Graffiti"));
	        add(new TellUsVO("Report Neighbordhood Nuisances"));
	        add(new TellUsVO("Report Other Issues"));
	    }
	};
	
	
	public static List<PoliceBlotterVO> listPoliceBlotterMockup = new ArrayList<PoliceBlotterVO>() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new PoliceBlotterVO("Arrest", "12:00 PM 2500 Block of Bath Road,Bristol,PA"));
	        add(new PoliceBlotterVO("Theft", "8:00 AM block bristol Pike1,Bristol,PA"));
	        add(new PoliceBlotterVO("Arrest", "12:00 PM 2500 Block of Bath Road,Bristol,PA"));
	    }
	};
	
	/**
	 * Get Model Instance
	 * @return {@link Model}
	 */
	public static Model getInstance(){
		if(model==null){
			model=new Model();
		}
		return model;
	}
	
}
