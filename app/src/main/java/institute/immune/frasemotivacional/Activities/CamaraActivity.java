package institute.immune.frasemotivacional.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import institute.immune.frasemotivacional.R;

public class CamaraActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_estado);
        imageView = findViewById(R.id.ImageViewFoto);
        image();

    }

    public void image(){
        Bundle extras = getIntent().getBundleExtra("image");
        Bitmap bitmap = (Bitmap) extras.get("data");
        imageView.setImageBitmap(bitmap);
    }
}
