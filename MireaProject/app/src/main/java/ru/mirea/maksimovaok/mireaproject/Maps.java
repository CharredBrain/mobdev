package ru.mirea.maksimovaok.mireaproject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.Arrays;

import android.Manifest;

import ru.mirea.maksimovaok.mireaproject.databinding.FragmentMapsBinding;

public class Maps extends Fragment {

    private MapView mapView;
    private FragmentMapsBinding binding;

    private String mParam1;
    private String mParam2;

    private boolean isWork = false;
    private final int REQUEST_PERMISSION_CODE = 204;


    public Maps() {
        // Required empty public constructor
    }

    public static Maps newInstance(String param1, String param2) {
        Maps fragment = new Maps();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);

        mapView = binding.mapView;

        String[] permissions = {
                android.Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_FINE_LOCATION
        };

        if (
                Arrays.stream(permissions)
                        .map(elem -> ContextCompat.checkSelfPermission(getContext(), elem))
                        .allMatch(elem -> elem == PackageManager.PERMISSION_GRANTED)
        ) {
            isWork = true;
        } else {
            requestPermissions(permissions, REQUEST_PERMISSION_CODE);
        }

        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);
        MyLocationNewOverlay locationNewOverlay = new MyLocationNewOverlay(new
                GpsMyLocationProvider(getContext()),mapView);
        locationNewOverlay.enableMyLocation();
        mapView.getOverlays().add(locationNewOverlay);
        CompassOverlay compassOverlay = new CompassOverlay(getContext(), new
                InternalCompassOrientationProvider(getContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);
        final Context context = this.getContext();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.getOverlays().add(scaleBarOverlay);

        Marker marker1 = new Marker(mapView);
        marker1.setPosition(new GeoPoint(55.359744, 36.176648));
        marker1.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getContext(),"Here's where I'll be this summer",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker1);
        marker1.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker1.setTitle("Some s***hole");

        Marker marker2 = new Marker(mapView);
        marker2.setPosition(new GeoPoint(-79.069361, 53.233406));
        marker2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getContext(),"Here's where I want to go",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker2);
        marker2.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker2.setTitle("Hopes and Dreams");

        Marker marker3 = new Marker(mapView);
        marker3.setPosition(new GeoPoint(31.032501, -114.861188));
        marker3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getContext(),"Kurva bober!",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker3);
        marker3.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker3.setTitle("Kurva");

        binding.handshake.setOnClickListener(v -> {
            mapController.setZoom(20.0);
            GeoPoint startPoint1 = new GeoPoint(new GeoPoint(55.794302, 37.701582));
            mapController.setCenter(startPoint1);
        });

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getContext(),
                PreferenceManager.getDefaultSharedPreferences(getContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getContext(),
                PreferenceManager.getDefaultSharedPreferences(getContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }

}