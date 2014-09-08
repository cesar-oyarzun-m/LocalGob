package moofwd.towns.square.model;

/**
 * Tell Us VO
 * @author Cesar Oyarzun
 *
 */
public class TellUsVO implements Info{

	private String mDate;
	private String mDesc;
	private String mTitle;

	public TellUsVO(String title,String desc,String date,boolean status){
		this.mTitle=title;
		this.mDesc=desc;
		this.mDate=date;
	}
	
	public TellUsVO(String title){
		this.mTitle=title;
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
