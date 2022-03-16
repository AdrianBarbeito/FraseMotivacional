package institute.immune.frasemotivacional.Class;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper{

    private SQLiteDatabase db;
    private static final String scriptUsuario = "CREATE TABLE usuario (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, id_estado INTEGER, inicio_sesion INTEGER NOT NULL, FOREIGN KEY (id_estado) REFERENCES estado (id_estado))";
    private static final String scriptEstado = "CREATE TABLE estado (id_estado INTEGER PRIMARY KEY AUTOINCREMENT, estado TEXT NOT NULL)";
    private static final String scriptFrase = "CREATE TABLE frase (id_frase INTEGER PRIMARY KEY AUTOINCREMENT, frase TEXT NOT NULL, id_estado INTEGER NOT NULL, fecha_uso TEXT, FOREIGN KEY (id_estado) REFERENCES estado (id_estado))";

    public MyOpenHelper (Context context) {
        super(context, "FraseMotivacionalDB.SQLite", null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(scriptEstado);
        sqLiteDatabase.execSQL(scriptUsuario);
        sqLiteDatabase.execSQL(scriptFrase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @SuppressLint("Range")
    public Usuario crearUsuario(String nombre) {
        ContentValues cv = new ContentValues();

        cv.put("nombre", nombre);
        cv.put("inicio_sesion", 0);
        db.insert("usuario", null, cv);

        String[] args = new String[]{
                nombre
        };
        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE nombre = ?", args);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            @SuppressLint("Range") Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getInt(cursor.getColumnIndex("id_estado")),
                    cursor.getInt(cursor.getColumnIndex("inicio_sesion")));
            cursor.close();
            return usuario;
        }else{
            return null;
        }
    }


    @SuppressLint("Range")
    public Usuario searchByNombre(String nombre){
        String[] args = new String[]{
                nombre
        };
        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE nombre = ?", args);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            @SuppressLint("Range") Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getInt(cursor.getColumnIndex("id_estado")),
                    cursor.getInt(cursor.getColumnIndex("inicio_sesion")));
            cursor.close();

            return usuario;
        }else{
            return null;
        }
    }

    @SuppressLint("Range")
    public Estado searchByIdEstado(Integer id){
        String[] args = new String[]{
                id.toString()
        };
        Cursor cursor = db.rawQuery("SELECT * FROM estado WHERE id_estado = ?", args);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            @SuppressLint("Range") Estado estado = new Estado(cursor.getInt(cursor.getColumnIndex("id_estado")),
                    cursor.getString(cursor.getColumnIndex("estado")));
            cursor.close();

            return estado;
        }else{
            return null;
        }
    }

    @SuppressLint("Range")
    public Usuario getInicioSesion(){
        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE inicio_sesion = 1", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            @SuppressLint("Range") Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getInt(cursor.getColumnIndex("id_estado")),
                    cursor.getInt(cursor.getColumnIndex("inicio_sesion")));
            cursor.close();

            return usuario;
        }else{
            return null;
        }
    }

    public void setInicioSesion(Integer id, boolean activo){
        ContentValues cv = new ContentValues();
        String[] args = new String[]{
                String.valueOf(id)
        };

        if (activo){
            cv.put("inicio_sesion", 1);
        }else{
            cv.put("inicio_sesion", 0);
        }

        db.update("usuario", cv, "id_usuario = ?", args);
    }

    public void setFalseIS(){
        ContentValues cv = new ContentValues();
        cv.put("inicio_sesion", 0);

        db.update("usuario", cv, null, null);
    }

    public Frase getFraseSetDate(Integer id_estado){
        String[] id = new String[]{
                String.valueOf(id_estado)
        };
        Cursor cursor = db.rawQuery("SELECT * FROM frase WHERE date(fecha_uso) >= date('0000/00/00 00:00:01') & id_estado = ? ORDER BY date(fecha_uso) DESC LIMIT 1", id);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") Frase frase = new Frase(cursor.getInt(cursor.getColumnIndex("id_frase")),
                    cursor.getString(cursor.getColumnIndex("frase")),
                    cursor.getInt(cursor.getColumnIndex("id_estado")),
                    cursor.getString(cursor.getColumnIndex("fecha_uso")));

            cursor.close();

            ContentValues cv = new ContentValues();
            String[] args = new String[]{
                    String.valueOf(frase.getId_frase())
            };
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            cv.put("fecha_uso", formatter.format(LocalDateTime.now()));
            db.update("frase", cv, "id_frase = ?", args);

            return frase;
        } else{
            return null;
        }
    }

    public ArrayList<Estado> getMoods(){
        ArrayList<Estado> moodList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM estado", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                @SuppressLint("Range") Estado estado = new Estado(cursor.getInt(cursor.getColumnIndex("id_estado")),
                        cursor.getString(cursor.getColumnIndex("estado")));

                moodList.add(estado);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return moodList;
    }

    public void bufferReader(InputStream is) throws IOException, JSONException {
        //Necesito crearla aqui por q si no cada vez q cargue la pagina de login va a cargar la base de datos
        //MainActivity mainActivity = new MainActivity();
        //InputStream is = mainActivity.getResources().openRawResource(R.raw.json_database);
        if (showEstados() == null || showFrases() == null){
            InputStreamReader streamReader = new InputStreamReader(is);
            BufferedReader rd = new BufferedReader(streamReader);
            String line;
            while ((line = rd.readLine()) != null) {
                createDb(line);
            }
        }
    }


    private void createDb(String line) throws JSONException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        ContentValues cv = new ContentValues();
        JSONObject json = new JSONObject(line);
        JSONObject database = json.getJSONObject("dataBase");

        JSONArray jsonArray = database.getJSONArray("estados");
        for (int var = 0; var <= (jsonArray.length() -1); var++){
            JSONObject linea = jsonArray.getJSONObject(var);
            cv.put("estado", linea.getString("estado"));
            db.insert("estado", null, cv);
            cv.clear();
        }

        jsonArray = database.getJSONArray("frases");
        for (int var = 0; var <= (jsonArray.length() -1); var++){
            JSONObject linea = jsonArray.getJSONObject(var);
            cv.put("frase", linea.getString("frase"));
            cv.put("id_estado", linea.getString("id_estado"));
            cv.put("fecha_uso", formatter.format(LocalDateTime.now()));
            db.insert("frase", null, cv);
            cv.clear();
        }
    }

    @SuppressLint("Range")
    public ArrayList<Frase> showFrases(){
        ArrayList<Frase> fraseList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM frase", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Frase frase = new Frase(cursor.getInt(cursor.getColumnIndex("id_frase")),
                        cursor.getString(cursor.getColumnIndex("frase")),
                        cursor.getInt(cursor.getColumnIndex("id_estado")),
                        cursor.getString(cursor.getColumnIndex("fecha_uso")));
                fraseList.add(frase);

            } while (cursor.moveToNext());
            cursor.close();

            return fraseList;

        } else{
            return null;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Estado> showEstados(){
        ArrayList<Estado> estadoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM estado", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Estado estado = new Estado(cursor.getInt(cursor.getColumnIndex("id_estado")),
                        cursor.getString(cursor.getColumnIndex("estado")));
                estadoList.add(estado);

            } while (cursor.moveToNext());
            cursor.close();

            return estadoList;

        } else{
            return null;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Usuario> showUsuarios(){
        ArrayList<Usuario> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM usuario", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Usuario user = new Usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")),
                        cursor.getString(cursor.getColumnIndex("nombre")),
                        cursor.getInt(cursor.getColumnIndex("id_estado")),
                        cursor.getInt(cursor.getColumnIndex("inicio_sesion")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return userList;
    }
}
