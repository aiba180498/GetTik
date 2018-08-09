package example.sunny.functioncheck;

import example.sunny.functioncheck.Models.ServerApi;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.43.92:8000/api/";

    public static ServerApi getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ServerApi.class);
    }
}
