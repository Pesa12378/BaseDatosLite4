package com.example.basedatoslite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2, et3, et4, et5, et6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
    }
    public void  limpiarCampos(View view) {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
    }

    public void altaID(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ID1 = et1.getText().toString();
        String Nombre = et2.getText().toString();
        String edad = et3.getText().toString();
        String direccion = et4.getText().toString();
        String telefono = et5.getText().toString();
        String adeudo = et6.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("ID1", ID1);
        registro.put("Nombre", Nombre);
        registro.put("edad", edad);
        registro.put("direccion", direccion);
        registro.put("telefono", telefono);
        registro.put("adeudo", adeudo);

        bd.insert("clientes", null, registro);
        bd.close();
        limpiarCampos(v);
        Toast.makeText(this, "Se cargaron los datos del cliente", Toast.LENGTH_SHORT).show();
    }
    public void consultaporID(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ID1 = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select ID1,nombre,edad,direccion,telefono,adeudo from clientes where ID1=" + ID1, null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
            et5.setText(fila.getString(4));
            et6.setText(fila.getString(5));
        } else
            Toast.makeText(this, "No existe un cliente con dicho código", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void consultaporTelefono(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String telefono = et5.getText().toString();
        Cursor fila = bd.rawQuery(
                "select ID1,nombre,edad,direccion,telefono,adeudo from clientes where telefono=" + telefono, null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
            et5.setText(fila.getString(4));
            et6.setText(fila.getString(5));
        } else
            Toast.makeText(this, "No existe un cliente con dicho telefono",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }
    public void bajaporID(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ID1 = et1.getText().toString();
        int cant = bd.delete("clientes", "ID1 =" + ID1, null);
        bd.close();
        limpiarCampos(v);

        if (cant == 1)
            Toast.makeText(this, "Se borró el cliente con dicho código",  Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un cliente con dicho código", Toast.LENGTH_SHORT).show();
    }
    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ID1 = et1.getText().toString();
        String Nombre = et2.getText().toString();
        String edad = et3.getText().toString();
        String direccion = et4.getText().toString();
        String telefono = et5.getText().toString();
        String adeudo = et6.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("ID1", ID1);
        registro.put("Nombre", Nombre);
        registro.put("edad", edad);
        registro.put("descripcion", direccion);
        registro.put("telefono", telefono);
        registro.put("adeudo", adeudo);

        int cant = bd.update("clientes", registro, "ID1=" + ID1, null);
        bd.close();

        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado",
                    Toast.LENGTH_SHORT).show();

    }
    }
