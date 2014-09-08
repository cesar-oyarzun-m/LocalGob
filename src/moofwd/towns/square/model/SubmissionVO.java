package moofwd.towns.square.model;

/**
 * Submission VO
 * @author Cesar Oyarzun
 *
 */
public class SubmissionVO implements Info{

	private String mDate;
	private String mDesc;
	private String mTitle;
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public SubmissionVO(String title,String desc,String date,boolean status){
		this.mTitle=title;
		this.mDesc=desc;
		this.mDate=date;
		this.status=status;
	}
	
	public String getDate() {
		return mDate;
	}
	public void setDate(String date) {
		mDate = date;
	}
	public String getDesc() {
		return mDesc;
	}
	public void setDesc(String desc) {
		mDesc = desc;
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	
	
}
