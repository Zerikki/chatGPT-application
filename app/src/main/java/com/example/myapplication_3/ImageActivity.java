package com.example.myapplication_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ResourceBundle;

import okhttp3.*;


public class ImageActivity extends AppCompatActivity  {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference apiKey = db.document("APIKEY");



    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String API_URL = "https://api.openai.com/v1/images/generations";
    private final String API_KEY = String.valueOf(apiKey);

    public EditText promptEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        promptEditText = findViewById(R.id.textInputEditText);

        Button generateBouton = findViewById(R.id.button);
        generateBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateImages();
            }
        });
    }

    public void generateImages() {
        OkHttpClient client = new OkHttpClient();

        String prompt = promptEditText.getText().toString();;
        int n = 1;
        String size = "1024x1024";

        String json = "{\n" +
                "    \"prompt\": \"" + prompt + "\",\n" +
                "    \"n\": " + n + ",\n" +
                "    \"size\": \"" + size + "\"\n" +
                "}";

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String responseBody = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject imageObject = jsonArray.getJSONObject(0);
                    String imageUrl = imageObject.getString("url");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageView imageView = findViewById(R.id.image_view);
                            Picasso.get().load(imageUrl).into(imageView);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

}
