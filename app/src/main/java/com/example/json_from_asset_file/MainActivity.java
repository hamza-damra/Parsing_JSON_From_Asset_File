package com.example.json_from_asset_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // 1- Widgets
    RecyclerView recyclerView;

    // 2- ArrayList for persons names and emails and phone numbers
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3- RecyclerView Configuration
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // 4- Getting JSON
        try {
            JSONObject root = new JSONObject(Objects.requireNonNull(loadJsonFromAsset()));
            JSONArray userArray = root.getJSONArray("users");

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userDetails = userArray.getJSONObject(i);
                names.add(userDetails.getString("name"));
                emailIds.add(userDetails.getString("email"));

                // Fetching the "contact" object from the current user
                JSONObject contact = userDetails.getJSONObject("contact");
                mobileNumbers.add(contact.getString("mobile"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Calling the custom adapter to send the reference and data to an adapter
        CustomAdapter adapter = new CustomAdapter(MainActivity.this, names, emailIds, mobileNumbers);
        recyclerView.setAdapter(adapter);
    }

    // Method For Loading JSON From Asset
    private String loadJsonFromAsset() {
        String json = null;
        try {
            InputStream in = getAssets().open("users_list.json");
            int size = in.available();
            byte[] buffer = new byte[size];
            int result = in.read(buffer);
            in.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
