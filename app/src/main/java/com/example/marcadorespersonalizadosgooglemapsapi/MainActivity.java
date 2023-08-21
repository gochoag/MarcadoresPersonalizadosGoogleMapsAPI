package com.example.marcadorespersonalizadosgooglemapsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private MapView mapView;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }


    public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private LayoutInflater inflater;

        public CustomInfoWindowAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null; // Utiliza getInfoContents para personalizar el contenido de la ventana
        }

        @Override
        public View getInfoContents(Marker marker) {
            View infoView = inflater.inflate(R.layout.custom_info_window, null);

            ImageView placeLogo = infoView.findViewById(R.id.placeLogo);
            TextView placeName = infoView.findViewById(R.id.placeName);
            TextView placeLocation = infoView.findViewById(R.id.placeLocation);
            TextView placeOpeningHours = infoView.findViewById(R.id.placeOpeningHours);

            PlaceInfo placeInfo = (PlaceInfo) marker.getTag();

            placeName.setText(placeInfo.getName());
            placeLocation.setText(placeInfo.getLocation().toString());
            placeOpeningHours.setText(placeInfo.getOpeningHours());

            Picasso.get().load(placeInfo.getLogoUrl()).into(placeLogo);

            return infoView;
        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Agregar un marcador personalizado
        LatLng location = new LatLng(-1.0131042550509033, -79.46666677949204);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title("Toque marinero")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        Marker marker = googleMap.addMarker(markerOptions);

        marker.setTag(new PlaceInfo("Toque marinero", location,
                "https://lh5.googleusercontent.com/p/AF1QipOIZM1ItRvCjXSe7az_yWxopaVTdcZRc-qpOPXJ=w408-h544-k-no", "Horarios: 7 a.m. - 1 p.m."));

        // Mover la cámara al marcador
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

        // Configurar el adaptador de ventana de información personalizada
        CustomInfoWindowAdapter infoWindowAdapter = new CustomInfoWindowAdapter(getLayoutInflater());
        googleMap.setInfoWindowAdapter(infoWindowAdapter);

        // Agregar un listener para manejar clics en los marcadores
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });
    }





}