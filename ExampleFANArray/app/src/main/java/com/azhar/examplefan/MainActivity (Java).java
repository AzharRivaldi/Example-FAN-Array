package com.azhar.examplefan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    MainAdapter mainAdapter;
    ProgressDialog progressDialog;
    List<ModelMain> modelMain = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvMain = findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setHasFixedSize(true);

        getData();
    }

    private void getData() {
        progressDialog.show();
        AndroidNetworking.get("https://raw.githubusercontent.com/Oclemy/SampleJSON/338d9585/spacecrafts.json")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                progressDialog.dismiss();
                                ModelMain data = new ModelMain();
                                JSONObject jsonObject = response.getJSONObject(i);
                                data.setName(jsonObject.getString("name"));
                                data.setPropellant(jsonObject.getString("propellant"));
                                data.setDestination(jsonObject.getString("destination"));
                                modelMain.add(data);
                                mainAdapter = new MainAdapter(modelMain);
                                rvMain.setAdapter(mainAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, "gagal menampilkan data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
