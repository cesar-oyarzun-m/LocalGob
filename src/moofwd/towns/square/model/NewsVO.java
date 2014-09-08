package moofwd.towns.square.model;

/**
 * News VO
 * @author Cesar Oyarzun
 *
 */

public class NewsVO implements Info {

	private String mTitle;
	private String mDesc;
	
	public NewsVO(String title,String desc){
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
