package com.example.crudtestapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudtestapplication.R;
import com.example.crudtestapplication.interfaces.CRUDInterface;
import com.example.crudtestapplication.model.Product;
import com.example.crudtestapplication.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    TextView idText;
    TextView nameText;
    TextView priceText;
    Button editButton;
    Product product;
    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idText=findViewById(R.id.idText);
        nameText=findViewById(R.id.nameText);
        priceText=findViewById(R.id.priceText);
        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEdit();
            }
        });
        long id = getIntent().getExtras().getLong("id");
        getOne(id);

    }


    private void getOne(long id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface=retrofit.create(CRUDInterface.class);
        Call<Product> call = crudInterface.getOne(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                }
                product = response.body();
                idText.setText(String.valueOf(product.getId()));
                nameText.setText(product.getName());
                priceText.setText(String.valueOf(product.getPrice()));
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("id", product.getId());
    }
}