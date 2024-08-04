package com.example.sqlitenuevo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sqlitenuevo.adapter.UsuarioAdapter;
import com.example.sqlitenuevo.dao.UsuarioDao;
import com.example.sqlitenuevo.model.Usuario;
import com.example.sqlitenuevo.util.Mensajes;

import java.util.List;

public class ListUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{
    private ListView lista1;
    private List<Usuario> listaList;
    private UsuarioAdapter adapter;
    private UsuarioDao usuarioDAO;
    private int idposi;
    private AlertDialog alertDialog, alertconfirm;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_usuario);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        usuarioDAO = new UsuarioDao(this);
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
        int id = item.getItemId();
        if (id == R.id.action_menu_guardar) {
            startActivity(new Intent(this, UsuarioActivity.class));
            finish();
        }
        if(id==R.id.action_menu_salir1){
            finish();
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