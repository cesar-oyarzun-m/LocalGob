package moofwd.towns.square.fragments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import moofwd.towns.square.MoofwdTownActivity;
import moofwd.towns.square.R;
import moofwd.towns.square.model.ILocation;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

/**
 * Crime Tip Fragment
 * 
 * @author Cesar Oyarzun
 * 
 */
public class CrimeTipFragment extends Fragment implements ILocation {

	private static final String IMAGE = "image";
	private static final String LOCATION_FRAGMENT = "Location_fragment";
	private String addressText;
	private View inflate;
	private ImageView photoImageView;
	private Bitmap imageBitmap;
	private File fileImage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.fragment_crime_tip, container, false);
		Spinner spinner = (Spinner) inflate.findViewById(R.id.crimeTypeComboBox);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.crime_type_array, R.layout.drop_down_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		final LinearLayout personalInfoLayout = (LinearLayout) inflate.findViewById(R.id.personalInfoLayout);
		CheckBox anonymousButton = (CheckBox) inflate.findViewById(R.id.anonymous);
		anonymousButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				boolean checked = ((CheckBox) view).isChecked();
				if (checked) {
					personalInfoLayout.setVisibility(View.GONE);
				} else {
					personalInfoLayout.setVisibility(View.VISIBLE);
				}
			}
		});
		ImageView locationButton = (ImageView) inflate.findViewById(R.id.locationButton);
		locationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				Fragment fragment = fm.findFragmentById(R.id.content_frame);
				if (fragment == null) {
					fragment = new LocationFragment();
					FragmentTransaction transaction = fm.beginTransaction();
					transaction.add(R.id.content_frame, fragment).addToBackStack(LOCATION_FRAGMENT).commit();
				} else {
					fragment = new LocationFragment();
					FragmentTransaction transaction = fm.beginTransaction();
					transaction.replace(R.id.content_frame, fragment).addToBackStack(LOCATION_FRAGMENT).commit();
				}

			}
		});

		MoofwdTownActivity activity = (MoofwdTownActivity) getActivity();
		activity.resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		activity.getSupportActionBar().setTitle(R.string.crime_tip);
		activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);

		photoImageView = (ImageView) inflate.findViewById(R.id.photo_icon);
		final RelativeLayout photoLayout = (RelativeLayout) inflate.findViewById(R.id.photoLayout);
		registerForContextMenu(photoLayout);
		photoLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				photoLayout.showContextMenu();
			}
		});
		
		if (savedInstanceState != null) {
			Bitmap bitMap = (Bitmap) savedInstanceState.get(IMAGE);
			this.photoImageView.setImageBitmap(bitMap);
			this.imageBitmap = bitMap;
		} else if (this.imageBitmap != null) {
			this.photoImageView.setImageBitmap(this.imageBitmap);

		} else {
			this.photoImageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.camera_icon));
		}

		return inflate;
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.tell_us_image_menu, menu);
		if(this.imageBitmap!=null){
			 menu.findItem(R.id.menu_item_delete).setVisible(true);
		}else{
			 menu.findItem(R.id.menu_item_delete).setVisible(false);
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@TargetApi(14)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			MoofwdTownActivity activity = (MoofwdTownActivity) getActivity();
			activity.getSupportFragmentManager().popBackStack();
			activity.resetActionBar(false, DrawerLayout.LOCK_MODE_UNLOCKED);
			activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			activity.getSupportActionBar().setTitle(R.string.tell_us);
			this.addressText = null;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				activity.getSupportActionBar().setHomeButtonEnabled(true);
				activity.getSupportActionBar().setIcon(R.drawable.top_header_menu_icon);
				activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		switch (transit) {
		case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
			if (enter) {
				return AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_bottom);
			} else {
				return AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_out_bottom);
			}
		default:
			if (enter) {
				return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
			} else {
				return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
			}
		}
	}

	@Override
	public void passLocation(String location) {
		addressText = location;
	}

	@Override
	public void onResume() {
		super.onResume();
		EditText addressTextView = (EditText) inflate.findViewById(R.id.address);
		if (this.addressText != null && !"".equals(this.addressText)) {
			addressTextView.setText(this.addressText.toString());
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
			try {
				ExifInterface exif = new ExifInterface(fileImage.getAbsolutePath());
				int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
				Matrix matrix = new Matrix();
				if (orientation == 6) {
					matrix.postRotate(90);
				} else if (orientation == 3) {
					matrix.postRotate(180);
				} else if (orientation == 8) {
					matrix.postRotate(270);
				}
				Bitmap bmp = BitmapFactory.decodeFile(fileImage.getAbsolutePath());
				Bitmap bmpRotated = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
				imageBitmap = ThumbnailUtils.extractThumbnail(bmpRotated, metrics.widthPixels / 2, metrics.heightPixels / 3);
				photoImageView.setImageBitmap(imageBitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
			try {
				Uri uri = data.getData();
				Bitmap bmp = Media.getBitmap(getActivity().getContentResolver(), uri);
				imageBitmap = ThumbnailUtils.extractThumbnail(bmp, metrics.widthPixels / 2, metrics.heightPixels / 3);
				photoImageView.setImageBitmap(imageBitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(IMAGE, imageBitmap);
		super.onSaveInstanceState(outState);
	}
}
