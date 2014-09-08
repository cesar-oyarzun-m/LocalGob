package moofwd.towns.square.model;

/**
 * News VO
 * @author Cesar Oyarzun
 *
 */

public class PoliceBlotterVO implements Info {

	private String mTitle;
	private String mDesc;
	
	public PoliceBlotterVO(String title,String desc){
		this.mTitle=title;
		this.mDesc=desc;
	}
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public String getDesc() {
		return mDesc;
	}
	public void setDesc(String desc) {
		mDesc = desc;
	}
}
