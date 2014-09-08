package moofwd.towns.square.adapter;

import java.util.List;

import moofwd.towns.square.R;
import moofwd.towns.square.model.SubmissionVO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Submission Adapter
 * @author Cesar Oyarzun
 *
 */

public class SubmissionAdapter extends ArrayAdapter<SubmissionVO> {
	private List<SubmissionVO> listInfo = null;
	private LayoutInflater mInflater;
	private Context context;
	static class ViewHolder {
		TextView txtTitle;
		TextView txtDesc;
		TextView txtDate;
		ImageView imageViewStatus;
		TextView txtReport;
	}
	

	public SubmissionAdapter(Context context, int resource, List<SubmissionVO> objects) {
		super(context, resource, objects);
		this.listInfo = objects;
		this.context = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SubmissionVO info = listInfo.get(position);
		ViewHolder holder = null;
		View rootView = convertView;
		if (rootView == null) {
			rootView = mInflater.inflate(R.layout.list_submission_row, parent, false);
			holder = new ViewHolder();
			holder.txtTitle=(TextView) rootView.findViewById(R.id.title);
			holder.txtDesc=(TextView) rootView.findViewById(R.id.address);
			holder.txtDate=(TextView) rootView.findViewById(R.id.date);
			holder.imageViewStatus=(ImageView) rootView.findViewById(R.id.imageStatus);
			holder.txtReport=(TextView) rootView.findViewById(R.id.reportText);
			rootView.setTag(holder);
		}else{
			holder = (ViewHolder) rootView.getTag();
		}
		holder.txtTitle.setText(info.getTitle());
		holder.txtDesc.setText(info.getDesc());
		holder.txtDate.setText(info.getDate());
		if(info.isStatus()){
			holder.imageViewStatus.setImageDrawable(this.context.getResources().getDrawable(R.drawable.submission_open_icon));
			holder.txtReport.setText(R.string.report_open);
			holder.txtReport.setTextColor(this.context.getResources().getColor(R.color.green));
		}else{
			holder.imageViewStatus.setImageDrawable(this.context.getResources().getDrawable(R.drawable.submission_closed_icon));
			holder.txtReport.setText(R.string.report_closed);
			holder.txtReport.setTextColor(this.context.getResources().getColor(R.color.red));
		}
		return rootView;
	}
}
