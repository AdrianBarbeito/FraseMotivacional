package institute.immune.frasemotivacional.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import institute.immune.frasemotivacional.R;

public class EstadoActivity extends AppCompatActivity {
    ImageButton logOutBT, fotoBt;
    TextView tituloEstado;
    Spinner estadosAnimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);

        bindings();
        setListeners();
    }

    private void bindings() {
    }

    private void setListeners() {
        logOutBT.setOnClickListener(logOutListener);
    }

    public View.OnClickListener logOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }
    };
}