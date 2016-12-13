package rodriguescaio.com.clientes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rodriguescaio.com.clientes.R;
import rodriguescaio.com.clientes.modelo.Cliente;

/**
 * Created by angot on 22/11/2016.
 */

public class ListaTodosAdaptador extends ArrayAdapter<Cliente> {

    private Context context;
    private List<Cliente> lista = null;

    public ListaTodosAdaptador(Context context, List<Cliente> lista) {
        super(context, 0, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Cliente cliente = lista.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.activity_listatodos2, null);

        TextView nome  = (TextView) view.findViewById(R.id.nome);
        nome.setText(cliente.getNome());

        TextView email  = (TextView) view.findViewById(R.id.email);
        email.setText(cliente.getEmail());

        TextView cpf  = (TextView) view.findViewById(R.id.cpf);
        cpf.setText(cliente.getCpf()+"");

        return view;
    }
}
