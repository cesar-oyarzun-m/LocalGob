package moofwd.towns.square.adapter;

import java.util.List;

import moofwd.towns.square.R;
import moofwd.towns.square.model.AlertVO;
import moofwd.towns.square.model.EventVO;
import moofwd.towns.square.model.Info;
import moofwd.towns.square.model.NewsVO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * List All News Events Adapter
 * 
 * @author Cesar Oyarzun
 * 
 */
public class ListAllAdapter extends ArrayAdapter<Info> {
	private static final String EMPTY = "";
	private List<Info> listInfo = null;
	private LayoutInflater mInflater;
	private Context context;
	private static final int ALERTYPE = 0;
	private static final int NEWSTYPE = 1;
	private static final int EVENTTYPE = 2;

	
	static class ViewHolder {
		TextView txtTitle;
		TextView txtDesc;
		TextView txtMonth;
		TextView txtDate;
		LinearLayout linearLayoutDate;
		LinearLayout linearLayoutItem;
	}

	public ListAllAdapter(Context context, int resource, List<Info> objects) {
		super(context, resource, objects);
		this.listInfo = objects;
		this.context = context;

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Info info = listInfo.get(position);
		int viewType = this.getItemViewType(position);
		View vi = convertView;          
		ViewHolder holder = null;
		if (vi == null) {
		//The view is not a recycled one: we have to inflate
		vi = mInflater.inflate(R.layout.list_news_events_row, parent, false);
		holder = new ViewHolder();
		holder.linearLayoutItem = (LinearLayout) vi.findViewById(R.id.itemLayout);
		
		holder.txtTitle = (TextView) vi.findViewById(R.id.title);
		holder.txtDesc = (TextView) vi.findViewById(R.id.desc);
		holder.txtDate = (TextView) vi.findViewById(R.id.date);
		holder.txtMonth = (TextView) vi.findViewById(R.id.month);
		holder.linearLayoutDate=(LinearLayout) vi.findViewById(R.id.dateLayout);
		vi.setTag(holder);
		} else {
		// View recycled !
		// no need to inflate
		// no need to findViews by id
		holder = (ViewHolder) vi.getTag();
		}
		
		switch (viewType) {
		case ALERTYPE:
			holder.txtTitle.setText(info.getTitle());
			holder.txtDesc.setText(info.getDesc());
			holder.txtMonth.setText(EMPTY);
			holder.txtDate.setText(EMPTY);
			holder.txtTitle.setTextColor(this.context.getResources().getColorStateList(R.color.text_alert_list_simple_selector));
			holder.linearLayoutDate.getLayoutParams().width=0;
			break;
		case NEWSTYPE:
			holder.txtTitle.setText(info.getTitle());
			holder.txtDesc.setText(info.getDesc());
			holder.txtMonth.setText(EMPTY);
			holder.txtDate.setText(EMPTY);
			holder.linearLayoutDate.getLayoutParams().width=0;
			break;
		case EVENTTYPE:
			holder.txtTitle.setText(info.getTitle());
			holder.txtDesc.setText(info.getDesc());
			String date = ((EventVO) info).getDate();
			String[] split = date.split(" ");
			if (split.length == 2) {
				holder.txtMonth.setText(split[0]);
				holder.txtDate.setText(split[1]);
			}
			break;
		default:
			break;
		}
		return vi;
	}

	@Override
	public int getItemViewType(int position) {
		Info info = listInfo.get(position);
		if (info instanceof EventVO) {
			return EVENTTYPE;
		} else if (info instanceof AlertVO) {
			return ALERTYPE;
		} else if (info instanceof NewsVO) {
			return NEWSTYPE;
		}
		return 0;
	}
}
