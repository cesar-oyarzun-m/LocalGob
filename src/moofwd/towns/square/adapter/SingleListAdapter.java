package moofwd.towns.square.adapter;

import java.util.List;

import moofwd.towns.square.R;
import moofwd.towns.square.model.Info;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Single List Adapter
 * @author Cesar Oyarzun
 *
 */

public class SingleListAdapter extends ArrayAdapter<Info> {
	private List<Info> listInfo = null;
	private LayoutInflater mInflater;
	

	public SingleListAdapter(Context context, int resource, List<Info> objects) {
		super(context, resource, objects);
		this.listInfo = objects;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Info info = listInfo.get(position);
		View rootView = mInflater.inflate(R.layout.list_simple_row, null, true);
		TextView txtTitle = (TextView) rootView.findViewById(R.id.title);
		txtTitle.setText(info.getTitle());
		return rootView;
	}
}
