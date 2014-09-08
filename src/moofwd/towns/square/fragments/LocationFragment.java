package moofwd.towns.square.fragments;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import moofwd.towns.square.R;
import moofwd.towns.square.model.ILocation;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Location GPS Fragment
 * 
 * @author Cesar Oyarzun
 * 
 */

public class LocationFragment extends SupportMapFragment implements ILocation {

	private LatLng latLang = null;
	private StringBuffer addressBuffer = new StringBuffer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@TargetApi(9)
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		// getting GPS status
		boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (!isNetworkEnabled && !isGPSEnabled) {
			Toast.makeText(getActivity(), R.string.gps_disabled, Toast.LENGTH_SHORT).show();
		} else {
			// Get a handle to the Map Fragment
			final GoogleMap map = getMap();
			map.setMyLocationEnabled(true);
			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setPowerRequirement(Criteria.POWER_HIGH);
			locationManager.requestSingleUpdate(criteria, new LocationListener() {
				@Override
				public void onLocationChanged(Location location) {
					double latitude = location.getLatitude();
					double longitude = location.getLongitude();
					latLang=new LatLng(location.getLatitude(), location.getLongitude());
					if (Geocoder.isPresent()) {
						Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
						try {
							List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
							String address = addresses.get(0).getAddressLine(0);
							String city = addresses.get(0).getAddressLine(1);
							addressBuffer.append(address + " " + city );
						} catch (IOException e) {
							e.printStackTrace();
						}
						map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, 17));
						map.addMarker(new MarkerOptions().title(getActivity().getString(R.string.current_location)).position(latLang));
					} else {
						Toast.makeText(getActivity(), "GEO DECODER NOT PRESENT!", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onProviderDisabled(String provider) {
				}

				@Override
				public void onProviderEnabled(String provider) {
				}

				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
				}
			}, null);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getActivity().getSupportFragmentManager().popBackStack();
			passLocation(null);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void passLocation(String location) {
		Fragment findFragmentCrimeByTag = getActivity().getSupportFragmentManager().findFragmentByTag(TellUsFragment.CRIME_TIP);
		Fragment findFragmentSusActivityByTag = getActivity().getSupportFragmentManager().findFragmentByTag(TellUsFragment.TELL_US_REPORT);

		if (findFragmentCrimeByTag != null) {
			ILocation fragmentCrimTip = (ILocation) findFragmentCrimeByTag;
			fragmentCrimTip.passLocation(this.addressBuffer.toString());
		} 
		else if (findFragmentSusActivityByTag != null) {
			ILocation susActivityFragment = (ILocation) findFragmentSusActivityByTag;
			susActivityFragment.passLocation(this.addressBuffer.toString());
		}

	}

}
