package institute.immune.frasemotivacional.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import institute.immune.frasemotivacional.Class.Usuario;
import institute.immune.frasemotivacional.R;

public class EstadoActivity extends AppCompatActivity {
    Usuario usuario;

    Button logOutBT, fotoBt;
    TextView tituloEstado;
    Spinner estadosAnimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);

        bindings();
        setListeners();
        setTitulo();
    }

    private void bindings() {
        logOutBT = findViewById(R.id.backToLoginBT);
    }

    private void setListeners() {
        logOutBT.setOnClickListener(logOutListener);
    }

    private void setTitulo() {
        tituloEstado.setText(R.string.hola + usuario.getNombre());
    }

    public View.OnClickListener logOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }
    };
}