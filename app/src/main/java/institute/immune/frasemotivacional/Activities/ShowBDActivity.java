package institute.immune.frasemotivacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import institute.immune.frasemotivacional.Class.Estado;
import institute.immune.frasemotivacional.Class.Frase;
import institute.immune.frasemotivacional.Class.MyOpenHelper;
import institute.immune.frasemotivacional.Class.Usuario;
import institute.immune.frasemotivacional.R;

public class ShowBDActivity extends AppCompatActivity {
    private TextView listaUsuarios;
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bdactivity);

        bindings();
        listaUsuarios.setMovementMethod(new ScrollingMovementMethod());
        Intent intent = getIntent();
        String comper = intent.getStringExtra("show");
        if (comper.equalsIgnoreCase("usuarios")){
            mostrarUsuarios(db.showUsuarios());
        } else if (comper.equalsIgnoreCase("estados")){
            mostrarEstados(db.showEstados());
        } else if (comper.equalsIgnoreCase("frases")){
            mostrarFrases(db.showFrases());
        }
    }

    private void bindings() {
        db = new MyOpenHelper(this);
        listaUsuarios = findViewById(R.id.listaUsuarios);
    }

    private void mostrarUsuarios(ArrayList<Usuario> array){
        String usuarios = "";
        for (Usuario user : array){
            usuarios += user.getId_usuario().toString() + " " + user.getNombre() + " " + user.getId_estado() + " " + user.getInicio_sesion() + "\n";
        }
        listaUsuarios.setText(usuarios);
    }

    private void mostrarEstados(ArrayList<Estado> array){
        String estados = "";
        for (Estado estado : array){
            estados += estado.getId_estado().toString() + " " + estado.getEstado() + "\n";
        }
        listaUsuarios.setText(estados);
    }

    private void mostrarFrases(ArrayList<Frase> array){
        String frases = "";
        for (Frase frase : array){
            frases += frase.getId_frase().toString() + " " + frase.getFrase() + " " + frase.getId_estado() + " " + frase.getFecha_uso() + "\n";
        }
        listaUsuarios.setText(frases);
    }
}