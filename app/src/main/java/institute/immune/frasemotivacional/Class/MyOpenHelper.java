package institute.immune.frasemotivacional.Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper{

    private SQLiteDatabase db;
    private static final String scriptUsuario = "CREATE TABLE user (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, id_estado INTEGER)";
    //Poner en el estado AUTOINCREMENT para q se empareje con el del usuario?
    private static final String scriptEstado = "CREATE TABLE estado (id_estado INTEGER PRIMARY KEY AUTOINCREMENT, estado TEXT)";
    //Como enlazar las FOREIGN KEY y si hay tipo DATE en SQLite?
    private static final String scriptFrase = "CREATE TABLE frase (id_frase INTEGER PRIMARY KEY, frase TEXT NOT NULL, id_estado INTEGER NOT NULL, fecha_uso)";

    public MyOpenHelper (Context context) {
        super(context, "FraseMotivacionalDB.SQLite", null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*sqLiteDatabase.execSQL(scriptUsuario);
        sqLiteDatabase.execSQL(scriptEstado);
        sqLiteDatabase.execSQL(scriptFrase);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void crearUsuario(String nombre) {
    }

    /*public Integer findByName(String nombre) {
        if (){

        } else {
            return -1;
        }
    }*/
}
