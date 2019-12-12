package com.example.knowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudLoginActivity extends AppCompatActivity implements View.OnClickListener{
        EditText et_stud_id,et_stud_pass;
        Button btn_stud_login;
        CheckBox CKBox;
    private Toolbar mtoolbar;
    Intent c;
    ProgressDialog loadingbar;
    RequestQueue rq;

    final String url = "https://api.myjson.com/bins/1drva2";
    String a,b;

    int idFLAG=-1,passFLAG=-1,status=-1,both=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_login);

        mtoolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Student Login");

        et_stud_id=findViewById(R.id.et_student_id);
        et_stud_pass=findViewById(R.id.et_student_password);
        btn_stud_login=findViewById(R.id.btn_student_login);
        CKBox=findViewById(R.id.ck_student_password);
        btn_stud_login.setOnClickListener(this);
        loadingbar=new ProgressDialog(this);
        TextView tvStudentSignup=findViewById(R.id.tv_student_signup);
        tvStudentSignup.setPaintFlags(tvStudentSignup.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        rq = Volley.newRequestQueue(this);

        CKBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    et_stud_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    et_stud_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_student_login) {
            a = et_stud_id.getText().toString().trim();
            b = et_stud_pass.getText().toString().trim();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Student");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (a.equals(jsonObject.getString("id")) && b.equals(jsonObject.getString("password"))) {
                                    status = 1;
                                    break;
                                } else if (!a.equals(jsonObject.getString("id")) && b.equals(jsonObject.getString("password"))) {
                                    idFLAG = 1;
                                    break;
                                } else if (a.equals(jsonObject.getString("id")) && !b.equals(jsonObject.getString("password"))) {
                                    passFLAG = 1;
                                    break;
                                } else if (!a.equals(jsonObject.getString("id")) && !b.equals(jsonObject.getString("password"))) {
                                    both = 1;

                                }
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudLoginActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
                rq.add(jsonObjectRequest);




                if (status == 1) {
                    c = new Intent(StudLoginActivity.this, HomeStudActivity.class);
                    startActivity(c);
                    loadingbar.dismiss();
                } else if (idFLAG == 1) {
                    Toast.makeText(this, "invalid id", Toast.LENGTH_SHORT).show();
                } else if (passFLAG == 1) {
                    Toast.makeText(this, "invalid password", Toast.LENGTH_SHORT).show();
                } else if (both == 1) {
                    Toast.makeText(this, "invalid id/password", Toast.LENGTH_SHORT).show();
                }



            }
        }

}
