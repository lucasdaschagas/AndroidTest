package com.example.crudtestapplication.interfaces;

import com.example.crudtestapplication.dto.ProductDTO;
import com.example.crudtestapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRUDInterface {

    @GET("product")
    Call<List<Product>> getAll();

    @GET("product/{id}")
    Call<Product> getOne(@Path("id") long id);

    @POST("product")
    Call<Product> create(@Body ProductDTO dto);

    @PUT("product/{id}")
    Call<Product> edit(@Path("id") long id, @Body ProductDTO dto);

    @DELETE("product/{id}")
    Call<Product> delete (@Path("id") long id);

}
