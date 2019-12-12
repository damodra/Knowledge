package com.example.knowledge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TechRegisterActivity extends AppCompatActivity {
    Button btnReg,btncancel;
    EditText edtFirst, edtLast, edtMob, edtPass, edtConfPass, edtEmail;

    private Toolbar mtoolbar;
    static  MyDB myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_register);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Teacher Register");

        edtFirst = (EditText) findViewById(R.id.etUserName);
        edtLast = (EditText) findViewById(R.id.etUserLastName);
        edtMob = (EditText) findViewById(R.id.etMobileNO);
        edtPass = (EditText) findViewById(R.id.etPassword);
        edtConfPass = (EditText) findViewById(R.id.etRepassword);
        edtEmail = (EditText) findViewById(R.id.etEmail);

        btnReg = (Button) findViewById(R.id.btn_teacher_login);
        btncancel=findViewById(R.id.btn_teacher_register);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TechRegisterActivity.this,TechLoginActivity.class);
                startActivity(i);
            }
        });





        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtFirst.getText().toString().trim();
                String lname = edtFirst.getText().toString().trim();
                 String mobile = edtMob.getText().toString().trim();
                //int mobile = Integer.parseInt(edtMob.getText().toString().trim());
                String password = edtPass.getText().toString().trim();
                String repassword = edtConfPass.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                boolean result = TechLoginActivity.myDB.insertData(name,lname,mobile,password,repassword,email);

                if (result){
                   // Toast.makeText(TechRegisterActivity.this, "Registered..", Toast.LENGTH_SHORT).show();
                     Toast.makeText(TechRegisterActivity.this,name+ "Registered..", Toast.LENGTH_SHORT).show();
                    edtFirst.setText("");
                    edtLast.setText("");
                    edtMob.setText("");
                    edtEmail.setText("");

                }
                else
                {
                    AlertDialog.Builder ad=new AlertDialog.Builder(TechRegisterActivity.this);
                    ad.setMessage("Wrong input");
                    ad.show();
                }
                edtPass.setText("");
                edtConfPass.setText("");

            }
        });








    }

//    public void saveData(View v) {
//        String fname = edtFirst.getText().toString().trim();
//        String lname = edtFirst.getText().toString().trim();
//       // String mobile = edtMob.getText().toString().trim();
//        int mobile = Integer.parseInt(edtMob.getText().toString().trim());
//        String password = edtPass.getText().toString().trim();
//        String repassword = edtConfPass.getText().toString().trim();
//        String email = edtEmail.getText().toString().trim();
//
//        boolean result = TechLoginActivity.myDB.insertData(fname, lname, mobile, password, repassword, email);
//
//        if (result){
//            Toast.makeText(TechRegisterActivity.this, "Registered..", Toast.LENGTH_SHORT).show();
//           // Toast.makeText(TechRegisterActivity.this,name+ "Registered..", Toast.LENGTH_SHORT).show();
//            edtFirst.setText("");
//            edtLast.setText("");
//            edtMob.setText("");
//            edtEmail.setText("");
//
//        }
//        else
//        {
//            AlertDialog.Builder ad=new AlertDialog.Builder(this);
//            ad.setMessage("Wrong input");
//            ad.show();
//        }
//        edtPass.setText("");
//        edtConfPass.setText("");
//    }



//    public  void goBack(View v)
//    {
//        Intent i=new Intent(TechRegisterActivity.this,TechLoginActivity.class);
//        startActivity(i);
//    }
}

