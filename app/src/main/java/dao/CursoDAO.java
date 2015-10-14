package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import modelo.Curso;
import modelo.Usuario;

/**
 * Created by DReyna on 18/05/2015.
 */
public class CursoDAO {
    private DBHelper helper;
    private SQLiteDatabase database;
    public CursoDAO(Context context){
        helper = new DBHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    private Curso AgregarCurso(Cursor cursor){
        Curso curso = new Curso(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Curso._ID)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Curso.NOMBRE_CURSO))
        );
        return curso;
    }
    public List<Curso> listarCurso(){
        Cursor cursor = getDatabase().query(DBHelper.Curso.TABLE,DBHelper.Curso.COLUMNAS, null, null, null, null, null);
        List<Curso> lista = new ArrayList<Curso>();
        while(cursor.moveToNext()){
            Curso modelo = AgregarCurso(cursor);
            lista.add(modelo);
        }
        return lista;
    }
    //curso
    public long modificarCurso(Curso curso){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Curso.NOMBRE_CURSO, curso.getNom_curso());

        if(curso.get_id() != null){
            return database.update(DBHelper.Curso.TABLE, values,
                    "_id = ?", new String[]{curso.get_id().toString()});
        }
        return getDatabase().insert(DBHelper.Curso.TABLE,null,values);
    }
    public boolean eliminarCurso(int id){
        return getDatabase().delete(DBHelper.Curso.TABLE,"id = ?", new String[]{Integer.toString(id)})>0;
    }
    public Curso buscarCursoPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,
                DBHelper.Curso.COLUMNAS, "id = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Curso model = AgregarCurso(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

 //curso
    public void cerrarDB(){
        helper.close();
        database = null;
    }


}
