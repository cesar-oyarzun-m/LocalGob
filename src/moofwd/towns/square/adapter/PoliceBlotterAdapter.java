package moofwd.towns.square.adapter;

import java.util.List;

import moofwd.towns.square.R;
import moofwd.towns.square.model.PoliceBlotterVO;
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

public class PoliceBlotterAdapter extends ArrayAdapter<PoliceBlotterVO> {
	private List<PoliceBlotterVO> listPoliceBlotter = null;
	private LayoutInflater mInflater;
	

	public PoliceBlotterAdapter(Context context, int resource, List<PoliceBlotterVO> objects) {
		super(context, resource, objects);
		this.listPoliceBlotter = objects;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PoliceBlotterVO policeBlotterVO = listPoliceBlotter.get(position);
		View rootView = mInflater.inflate(R.layout.list_police_blotter_row, null, true);
		TextView txtTitle = (TextView) rootView.findViewById(R.id.title);
		txtTitle.setText(policeBlotterVO.getTitle());
		TextView txtDesc = (TextView) rootView.findViewById(R.id.desc);
		txtDesc.setText(policeBlotterVO.getDesc());
		return rootView;
	}
}
