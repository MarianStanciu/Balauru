package com.example.copieyoutube;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName , editSurname, editMark, editTextId ;
    Button btnAdaugaDate;
    Button btnVeziDate;
    Button btnviewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb =new DatabaseHelper(this);
        btnAdaugaDate = (Button)findViewById(R.id.button2) ;

        editName = (EditText)findViewById(R.id.editName);
        editSurname = (EditText)findViewById(R.id.editSurname);
        editMark = (EditText)findViewById(R.id.editMarks);
        editTextId = (EditText) findViewById(R.id.editTextId);
        btnVeziDate= (Button)findViewById(R.id.veziDate) ;
        btnviewUpdate = (Button) findViewById(R.id.buttonUpdate) ;
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void viewAll(){
        btnVeziDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Cursor res = myDb.getAllData();
                       if (res.getCount() == 0){
                           showMessage("Eroare", "nimic de afisat, nu exista date");
                           return;
                       }
                       StringBuffer buffer = new StringBuffer();
                       while (res.moveToNext()){
                        buffer.append("ID:" +res.getString(0)+ "\n");
                           buffer.append("Name:" +res.getString(1)+ "\n");
                           buffer.append("Surname:" +res.getString(2)+ "\n");
                           buffer.append("Marks:" +res.getString(3)+ "\n\n");
                       }
                       showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void DeleteData()  {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Date sterse", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Date nesterse", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(), editName.getText().toString(), editSurname.getText().toString(),editMark.getText().toString());
                    if(isUpdate=true){
                        Toast.makeText(MainActivity.this, "Date updatate", Toast.LENGTH_LONG).show();}
                    else
                        Toast.makeText(MainActivity.this, "Date neupdatate", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void AddData(){
        btnAdaugaDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    boolean isInsertsed = myDb.insertData(editName.getText().toString(),editSurname.getText().toString(),editMark.getText().toString());

                    if (isInsertsed==true)
                        Toast.makeText(MainActivity.this, "Date introduse", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Date neintroduse", Toast.LENGTH_LONG).show();
                    }
                }
        );



}}
