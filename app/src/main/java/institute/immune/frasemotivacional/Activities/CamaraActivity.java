package institute.immune.frasemotivacional.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import institute.immune.frasemotivacional.Class.Estado;
import institute.immune.frasemotivacional.Class.Frase;
import institute.immune.frasemotivacional.Class.MyOpenHelper;
import institute.immune.frasemotivacional.Class.Usuario;
import institute.immune.frasemotivacional.R;

public class CamaraActivity extends AppCompatActivity {
    private MyOpenHelper db;
    private Usuario usuario;
    private Estado estado;
    private Frase frase;

    private TextView nombreTV, fraseTV;
    private ImageView imagenCapturada;


    public CamaraActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        bindings();
        generar_foto();
    }

    private void bindings(){
        db = new MyOpenHelper(this);

        nombreTV = findViewById(R.id.nombreTV);
        imagenCapturada = findViewById(R.id.imagenCapturadaIV);
        fraseTV = findViewById(R.id.fraseTV);

        usuario = db.getInicioSesion();
        estado = db.searchByIdEstado(getIntent().getIntExtra("id_estado", 0));
    }

    public void generar_foto(){
        nombreTV.setText(usuario.getNombre());

        Bundle extras = getIntent().getBundleExtra("image");
        Bitmap bitmap = (Bitmap) extras.get("data");
        imagenCapturada.setImageBitmap(bitmap);

        frase = db.getFraseSetDate(estado.getId_estado());
        if (frase != null){
            fraseTV.setText(frase.getFrase());
        } else {
            fraseTV.setText("Sorry we had a problem with your phrase");
        }
    }
}
