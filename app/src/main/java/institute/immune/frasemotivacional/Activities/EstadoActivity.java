package institute.immune.frasemotivacional.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import institute.immune.frasemotivacional.Class.Usuario;
import institute.immune.frasemotivacional.R;

public class EstadoActivity extends AppCompatActivity {

    Button logOutBT, fotoBt, camera;
    TextView tituloEstado;
    Spinner estadosanimo;

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
        tituloEstado = findViewById(R.id.textNombre);
        camera = findViewById(R.id.botonCamara);
    }

    private void setListeners() {
        logOutBT.setOnClickListener(logOutListener);
        camera.setOnClickListener(getCameraListener);
    }
    private void setTitulo() {
    }


    public View.OnClickListener getCameraListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, 1);
        }
    };

    public View.OnClickListener logOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }
    };

    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        if (requestcode == 1 && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            extra.get("data");
            CameraFargment blankFragment_camera = new CameraFargment();
            blankFragment_camera.setArguments(extra);


            getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, blankFragment_camera).commit();
        }
    }
}