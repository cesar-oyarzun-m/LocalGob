package moofwd.towns.square.model;

/**
 * Alert VO
 * @author Cesar Oyarzun
 *
 */
public class AlertVO implements Info{

	private String mDate;
	private String mDesc;
	private String mTitle;
	
	public AlertVO(String title,String desc,String date){
		this.mTitle=title;
		this.mDesc=desc;
		this.mDate=date;
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
