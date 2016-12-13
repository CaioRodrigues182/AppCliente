package rodriguescaio.com.clientes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;


import rodriguescaio.com.clientes.R;
import rodriguescaio.com.clientes.modelo.Cliente;
import rodriguescaio.com.clientes.webservice.ClienteService;
import rodriguescaio.com.clientes.webservice.Servico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarCliente extends AppCompatActivity {

    Context contexto = this;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_clientes);
        id = getIntent().getIntExtra("id", -1);
        if (id != -1) preencherForm();
    }


    public void cadastrar(View v) {
        Cliente cliente = new Cliente();
        cliente.setNome(((EditText) findViewById(R.id.nome)).getText().toString());
        cliente.setEmail(((EditText) findViewById(R.id.email)).getText().toString());
        cliente.setCpf(((EditText) findViewById(R.id.cpf)).getText().toString());
        if (id == -1)
            criar(cliente);
        else
            atualizar(cliente);
    }

    private void atualizar(Cliente cliente) {
        ClienteService servico = Servico.criarServico(ClienteService.class);
        Call<Void> chamada = servico.atualizar(id,cliente);
        chamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(contexto, "Cliente atualizado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(contexto, Navegacao.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void criar(Cliente cliente) {
        ClienteService servico = Servico.criarServico(ClienteService.class);
        Call<Cliente> chamada = servico.criar(cliente);
        chamada.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.code() == 201) {
                    Toast.makeText(contexto, "Cliente criado", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(contexto, "Já existe um cliente com esse nome!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });
    }

    private void preencherForm(){
        ClienteService servico = Servico.criarServico(ClienteService.class);
        Call<Cliente> chamada = servico.getCliente(id);

        chamada.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Cliente cliente = response.body();
                    ((EditText) findViewById(R.id.nome)).setText(cliente.getNome());
                    ((EditText) findViewById(R.id.email)).setText(cliente.getEmail());
                    ((EditText) findViewById(R.id.cpf)).setText(cliente.getCpf().toString());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });
    }
}
