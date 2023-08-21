package com.example.marcadorespersonalizadosgooglemapsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng location = new LatLng(-1.0132324611463537, -79.46683507936685);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title("Mi marcador de prueba")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        Marker marker = googleMap.addMarker(markerOptions);
        marker.setTag(new PlaceInfo("Toque marinero", location,
                "https://lh5.googleusercontent.com/p/AF1QipOIZM1ItRvCjXSe7az_yWxopaVTdcZRc-qpOPXJ=w408-h544-k-no", "Horarios: 7 a.m. - 1 p.m.", "place_id_aquí", 4.5));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

        googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(LayoutInflater.from(this)));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });
    }

    public interface PlaceDetailsService {
        @GET("details/json")
        Call<PlaceDetailsResponse> getPlaceDetails(
                @Query("fields") String fields,
                @Query("place_id") String placeId,
                @Query("key") String apiKey
        );
    }

    private void fetchPlaceDetails(Marker marker) {
        PlaceInfo placeInfo = (PlaceInfo) marker.getTag();
        if (placeInfo != null) {
            String placeId = placeInfo.getPlaceId();
            String apiKey = "KEYAPI";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/place/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PlaceDetailsService service = retrofit.create(PlaceDetailsService.class);
            Call<PlaceDetailsResponse> call = service.getPlaceDetails(
                    "name,rating,formatted_phone_number",
                    placeId,
                    apiKey
            );

            call.enqueue(new Callback<PlaceDetailsResponse>() {
                @Override
                public void onResponse(Call<PlaceDetailsResponse> call, Response<PlaceDetailsResponse> response) {
                    if (response.isSuccessful()) {
                        PlaceDetails details = response.body().getResult();
                        if (details != null) {
                            marker.setTitle(details.getName());
                            marker.setSnippet("Rating: " + details.getRating() +
                                    "\nPhone: " + details.getFormattedPhoneNumber());
                            marker.showInfoWindow();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PlaceDetailsResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View customView;

        public CustomInfoWindowAdapter(LayoutInflater inflater) {
            customView = inflater.inflate(R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            PlaceInfo placeInfo = (PlaceInfo) marker.getTag();
            if (placeInfo != null) {
                TextView placeNameTextView = customView.findViewById(R.id.placeNameTextView);
                TextView placeDetailsTextView = customView.findViewById(R.id.placeDetailsTextView);
                ImageView placeLogoImageView = customView.findViewById(R.id.placeLogoImageView);

                placeNameTextView.setText(placeInfo.getName());
                placeDetailsTextView.setText("Rating: " + placeInfo.getRating() +
                        "\nPhone: " + placeInfo.getFormattedPhoneNumber());

                Glide.with(customView.getContext())
                        .load(placeInfo.getLogoUrl())
                        .into(placeLogoImageView);

                return customView;
            }
            return null;
        }
    }

    class PlaceDetailsResponse {
        private PlaceDetails result;

        public PlaceDetails getResult() {
            return result;
        }
    }
}



