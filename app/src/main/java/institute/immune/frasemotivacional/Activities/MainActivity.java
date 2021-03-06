package institute.immune.frasemotivacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import institute.immune.frasemotivacional.Class.MyOpenHelper;
import institute.immune.frasemotivacional.Class.Usuario;
import institute.immune.frasemotivacional.R;

public class MainActivity extends AppCompatActivity {
    private MyOpenHelper db;
    private Usuario usuario;

    private EditText nombreInput;
    private ImageButton registrarBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindings();
        setListeners();
        if (db.getInicioSesion() != null){
            startActivity(new Intent(this, EstadoActivity.class));
        }
        if (nombreInput.getText().toString().equals("")){
            registrarBt.setEnabled(false);
        }
    }

    private void bindings() {
        db = new MyOpenHelper(this);
        try {
            db.bufferReader(getResources().openRawResource(R.raw.json_database));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        nombreInput = findViewById(R.id.nombreInput);
        registrarBt = findViewById(R.id.logInBt);
    }

    private void setListeners() {
        nombreInput.setOnKeyListener(inputListener);
        registrarBt.setOnClickListener(registrarListener);
    }

    public View.OnKeyListener inputListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (!nombreInput.getText().toString().equals("")) {
                registrarBt.setEnabled(true);
                registrarBt.setTooltipText(String.valueOf(R.string.accesoCorrecto));
            } else {
                registrarBt.setEnabled(false);
                registrarBt.setTooltipText(String.valueOf(R.string.errorInput));
            }
            return false;
        }
    };

    public View.OnClickListener registrarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            usuario = db.searchByNombre(nombreInput.getText().toString());
            if (usuario == null){
                usuario = db.crearUsuario(nombreInput.getText().toString());
            }
            db.setFalseIS();
            db.setInicioSesion(usuario.getId_usuario(), true);

            startActivity(new Intent(view.getContext(), EstadoActivity.class));
        }
    };
}