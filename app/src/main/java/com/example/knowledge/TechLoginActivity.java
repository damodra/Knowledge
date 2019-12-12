package com.example.knowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TechLoginActivity extends AppCompatActivity {
    EditText et_tech_id,et_tech_pass;
    Button btn_tech_login,btn_tech_reg;
    private Toolbar mtoolbar;
    CheckBox ckTeacherPassword;
    static  MyDB myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_login);

        myDB=new MyDB(this);

        mtoolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Teacher Login");

        et_tech_id=findViewById(R.id.et_tech_id);
        et_tech_pass=findViewById(R.id.et_tech_password);
        btn_tech_login=findViewById(R.id.btn_teacher_login);
        btn_tech_reg=findViewById(R.id.btn_teacher_register);
        ckTeacherPassword=findViewById(R.id.ck_teacher_password);
        TextView tv_tech_sign=findViewById(R.id.tv_tech_sign);
        tv_tech_sign.setPaintFlags(tv_tech_sign.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        btn_tech_reg.setOnClickListener(v->{
                Intent i=new Intent(TechLoginActivity.this,TechRegisterActivity.class);
                startActivity(i);
        });

        ckTeacherPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    et_tech_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    et_tech_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btn_tech_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=et_tech_id.getText().toString().trim();
                String password=et_tech_pass.getText().toString().trim();
                Cursor c=TechLoginActivity.myDB.empLoginCheck(email);
                c.moveToFirst();
                if(c==null)
                {
                    Toast.makeText(TechLoginActivity.this, "Invalid"+email, Toast.LENGTH_SHORT).show();
                    et_tech_id.setText("");
                    et_tech_pass.setText("");
                }
                else
                {
                    String name2=c.getString(0);
                    String pass2=c.getString(1);
                    if(password.equals(pass2))
                    {
                        Intent intent=new Intent(TechLoginActivity.this,HomeStudActivity.class);
                       // intent.putExtra("name",name2);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(TechLoginActivity.this, "Invalid"+email, Toast.LENGTH_SHORT).show();
                    }
                    et_tech_id.setText("");
                    et_tech_pass.setText("");
                }

            }
        });






//    public void loginTecher(View v)
//    {
//            String name=et_tech_id.getText().toString().trim();
//             String password=et_tech_pass.getText().toString().trim();
//        Cursor c=TechLoginActivity.myDB.empLoginCheck(name);
//        c.moveToFirst();
//        if(c==null)
//        {
//            Toast.makeText(TechLoginActivity.this, "Invalid"+name, Toast.LENGTH_SHORT).show();
//            et_tech_id.setText("");
//            et_tech_pass.setText("");
//        }
//        else
//        {
//            String name2=c.getString(0);
//            String pass2=c.getString(1);
//            if(password.equals(pass2))
//            {
//                Intent intent=new Intent(TechLoginActivity.this,HomeStudActivity.class);
//                intent.putExtra("name",name2);
//                startActivity(intent);
//            }
//            else
//            {
//                Toast.makeText(TechLoginActivity.this, "Invalid"+name, Toast.LENGTH_SHORT).show();
//            }
//            et_tech_id.setText("");
//            et_tech_pass.setText("");
//        }


    }
}
