package com.example.sqlitenuevo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlitenuevo.dao.UsuarioDao;
import com.example.sqlitenuevo.util.Mensajes;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsuario, edtClave;
    private UsuarioDao usuarioDAO;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtUsuario = (EditText) findViewById(R.id.edt_Usuario);
        edtClave = (EditText) findViewById(R.id.edt_Clave);
        usuarioDAO = new UsuarioDao(this);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
    }

    public void logueo(View view){
        String usuario = edtUsuario.getText().toString();
        String clave = edtClave.getText().toString();
        Toast.makeText(getApplicationContext(),usuario,Toast.LENGTH_LONG).show();
        boolean valida = true;
        if(usuario == null || usuario.equals("")){
            valida = false;
            edtUsuario.setError(getString(R.string.Login_validaUsuario));
        }
        if(clave == null || clave.equals("")){
            valida = false;
            edtClave.setError(getString(R.string.Login_validaClave));
        }
        if(valida){
            if(usuarioDAO.logueoUser(usuario, clave)){
                startActivity(new Intent(this, ListUsuarioActivity.class));
                finish();
            }else{
                Mensajes.Msg(this,getString(R.string.msg_login_incorrecto));
            }
        }
    }
}