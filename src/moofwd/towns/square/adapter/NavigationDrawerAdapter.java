package moofwd.towns.square.adapter;

import moofwd.towns.square.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Navigation Drawer Adapter
 * @author Cesar Oyarzun
 *
 */
public class NavigationDrawerAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] names;
	private final Integer[] images;
	
	public NavigationDrawerAdapter(Activity context, Integer[] images,String[] items) {
		super(context, R.layout.list_drawer_row,items);
		this.context=context;
		this.names=items;
		this.images=images;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_drawer_row, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(names[position]);
		imageView.setImageResource(images[position]);
		return rowView;
	}
}
