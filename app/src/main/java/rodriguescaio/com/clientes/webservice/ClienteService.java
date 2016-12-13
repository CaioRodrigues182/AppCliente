package rodriguescaio.com.clientes.webservice;


import java.util.List;

import rodriguescaio.com.clientes.modelo.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rodriguescaio.com.clientes.modelo.Cliente;

public interface ClienteService {

    @GET("clientes")
    Call<List<Cliente>> todos();

    @GET("clientes/{id}")
    Call<Cliente> getCliente(@Path("id") int id);

    @POST("clientes")
    Call<Cliente> criar(@Body Cliente cliente);

    @DELETE("clientes/{id}")
    Call<Void> deletar(@Path("id") int id);

    @PUT("clientes/{id}")
    Call<Void> atualizar(@Path("id") int id, @Body Cliente cliente);



}
