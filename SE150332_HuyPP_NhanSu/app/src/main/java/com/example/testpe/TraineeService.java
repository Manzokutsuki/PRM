package com.example.testpe;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TraineeService {
    String EMPLOYEE = "Employee";

    @GET(EMPLOYEE)
    Call<Employee[]> getAllTrainees();

    @GET(EMPLOYEE + "/{id}")
    Call<Employee> getAllTrainees(@Path("id") Object id);

    @POST(EMPLOYEE)
    Call<Employee> createTrainees(@Body Employee employee);

    @PUT(EMPLOYEE + "/{id}")
    Call<Employee> updateTrainees(@Path("id") Object id, @Body Employee employee);

    @DELETE(EMPLOYEE + "/{id}")
    Call<Employee> deleteTrainees(@Path("id") Object id);
}
