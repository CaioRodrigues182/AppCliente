package rodriguescaio.com.clientes.webservice;


import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servico {

    //URL base do endpoint. Deve sempre terminar com /
    //public static final String API_BASE_URL = "http://192.168.60.2:8080/";
    //public static final String API_BASE_URL = "http://192.168.0.16:8080/";
    //public static final String API_BASE_URL = "http://10.10.40.52:8080/";
    public static final String API_BASE_URL = "http://192.168.0.21:8080/";

    public static <S> S criarServico(Class<S> serviceClass) {

        //Instancia do interceptador das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS);

        httpClient.addInterceptor(loggingInterceptor);

        //Instância do retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);
    }
}