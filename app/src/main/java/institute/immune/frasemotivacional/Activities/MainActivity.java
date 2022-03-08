package institute.immune.frasemotivacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import institute.immune.frasemotivacional.R;

public class MainActivity extends AppCompatActivity {
    EditText nombreInput;
    ImageButton registrarBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindings();
        setListeners();
    }

    private void bindings() {

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
            Intent intent = new Intent(view.getContext(), EstadoActivity.class);
            intent.putExtra("nombre", nombreInput.getText().toString());
            startActivity(intent);
        }
    };
}