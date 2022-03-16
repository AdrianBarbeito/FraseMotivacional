package institute.immune.frasemotivacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import institute.immune.frasemotivacional.Class.Estado;
import institute.immune.frasemotivacional.Class.MyOpenHelper;
import institute.immune.frasemotivacional.Class.SpinAdapter;
import institute.immune.frasemotivacional.Class.Usuario;
import institute.immune.frasemotivacional.R;

public class EstadoActivity extends AppCompatActivity {
    private MyOpenHelper db;
    private Usuario usuario;
    private SpinAdapter adapter;
    private ArrayList<Estado> estadoList;
    private Estado estadoSelected;

    private Button logOutBT, camaraBt;
    private TextView tituloEstado;
    private Spinner estadosAnimo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);

        bindings();
        setTitulo();
        createSpinner();
        setListeners();
    }

    private void bindings() {
        db = new MyOpenHelper(this);

        tituloEstado = findViewById(R.id.textNombre);
        logOutBT = findViewById(R.id.backToLoginBT);
        estadosAnimo = findViewById(R.id.spinner);
        camaraBt = findViewById(R.id.botonCamara);

        usuario = db.getInicioSesion();
        estadoList = db.getMoods();
    }

    private void setListeners() {
        logOutBT.setOnClickListener(logOutListener);
        camaraBt.setOnClickListener(getCameraListener);
    }

    private void setTitulo() {
        tituloEstado.setText("Welcome" + " " + usuario.getNombre());
    }

    private void createSpinner() {
        adapter = new SpinAdapter(this, android.R.layout.simple_spinner_item, estadoList);

        estadosAnimo.setAdapter(adapter);

        estadosAnimo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                estadoSelected = adapter.getItem(position);
                //camaraBt.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //camaraBt.setEnabled(false);
            }
        });
    }

    public View.OnClickListener logOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db.setInicioSesion(usuario.getId_usuario(), false);
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }
    };


    public View.OnClickListener getCameraListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, 1);
        }
    };

    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        if (requestcode == 1 && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            extra.get("data");
            Intent intent = new Intent(this, CamaraActivity.class);
            intent.putExtra("image", extra);
            intent.putExtra("id_estado", estadoSelected.getId_estado());
            startActivity(intent);
        }
    }
}