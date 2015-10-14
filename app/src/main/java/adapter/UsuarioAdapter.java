package adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dreyna.dbmodelo.R;

import java.util.List;

import modelo.Usuario;

/**
 * Created by DReyna on 18/05/2015.
 */
public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private List<Usuario> lista;


    public UsuarioAdapter(Context context, List<Usuario> model){
        this.context = context;
        this.lista = model;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Usuario usuario = lista.get(position);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.usuarios, null);
        }
        TextView txtNom = (TextView) view.findViewById(R.id.usuario_lista_nombre);
        txtNom.setText(usuario.getNombres());
        return view;
    }
}
