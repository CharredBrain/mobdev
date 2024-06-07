package ru.mirea.maksimovaok.mireaproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ru.mirea.maksimovaok.mireaproject.databinding.FragmentWeatherBinding;

public class Weather extends Fragment {
    private String mParam1;
    private String mParam2;
    private FragmentWeatherBinding binding;

    public Weather() {
        // Required empty public constructor
    }


    public static Weather newInstance(String param1, String param2) {
        Weather fragment = new Weather();
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
        binding = FragmentWeatherBinding.inflate(inflater, container, false);


        binding.updateButton.setOnClickListener(v -> {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if(connectivityManager != null) {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
            if(networkInfo != null && networkInfo.isConnected()) {
                new DownloadPageTask().execute("https://ipinfo.io/json");
            } else {
                Toast.makeText(getContext(), "NO INTERNET",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject responseJson = new JSONObject(result);
                if(responseJson.has("current_weather")) {
                    JSONObject currentWeatherUnits = responseJson.getJSONObject("current_weather_units");
                    JSONObject currentWeather = responseJson.getJSONObject("current_weather");

                    Map<String, Object> weatherData = new HashMap<>();
                    weatherData.put("temperature", currentWeather.getDouble("temperature") +
                            currentWeatherUnits.getString("temperature"));
                    weatherData.put("windspeed", currentWeather.getDouble("windspeed") +
                            currentWeatherUnits.getString("windspeed"));

                    for (Map.Entry<String, Object> entry : weatherData.entrySet()) {
                        String value = entry.getValue().toString();
                        if(entry.getKey().equals("temperature")) {
                            binding.temp.setText(value);
                        } else {
                            binding.windSpeed.setText(value);
                        }
                    }
                } else {
                    String city = responseJson.getString("city");
                    String region = responseJson.getString("region");
                    String country = responseJson.getString("country");
                    String postal = responseJson.getString("postal");
                    String timezone = responseJson.getString("timezone");
                    binding.city.setText(city);
                    binding.reg.setText(region);
                    binding.country.setText(country);
                    binding.postCode.setText(postal);
                    binding.timezone.setText(timezone);

                    String latitudeLongitude = responseJson.getString("loc");
                    String[] parts = latitudeLongitude.split(",");
                    if(parts.length == 2) {
                        float latitude = Float.parseFloat(parts[0].trim());
                        float longitude = Float.parseFloat(parts[1].trim());
                        new DownloadPageTask().execute(String.format(Locale.getDefault(),
                                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true",
                                latitude, longitude));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPreExecute();
        }

        private String downloadIpInfo(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                int responseCode = connection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read = 0;
                    while((read = inputStream.read()) != -1) {
                        bos.write(read);
                    }
                    bos.close();
                    data = bos.toString();
                } else {
                    data = connection.getResponseMessage() + ". Error code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
    }
}