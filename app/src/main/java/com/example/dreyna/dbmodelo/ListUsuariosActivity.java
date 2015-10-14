package com.example.dreyna.dbmodelo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.UsuarioAdapter;
import dao.DBHelper;
import dao.UsuarioDAO;
import modelo.Usuario;
import util.Mensajes;


public class ListUsuariosActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista1;
    private List<Usuario> listaList;
    private UsuarioAdapter adapter;
    private UsuarioDAO usuarioDAO;
    private int idposi;
    private AlertDialog alertDialog, alertconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        usuarioDAO = new UsuarioDAO(this);
        listaList = usuarioDAO.listarUsuarios();
        adapter = new UsuarioAdapter(this,listaList);

        lista1 = (ListView) findViewById(R.id.lvUsuarios);
        lista1.setAdapter(adapter);

        lista1.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_usuarios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_guardar) {
            startActivity(new Intent(this, UsuarioActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listaList.get(idposi).get_id();
        switch (which){
            case 0:
                Intent intent = new Intent(this, UsuarioActivity.class);
                intent.putExtra("USUARIO_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                    break;
            case DialogInterface.BUTTON_POSITIVE:
                    listaList.remove(idposi);
                    usuarioDAO.eliminarUsuario(id);
                    lista1.invalidateViews();
                    break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertconfirm.dismiss();break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposi = position;
        alertDialog.show();
    }


}
