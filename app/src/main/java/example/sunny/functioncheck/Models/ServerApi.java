package example.sunny.functioncheck.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {

    @GET("Blog")
    Call<List<DummyModel>> getDummyModels();


}
