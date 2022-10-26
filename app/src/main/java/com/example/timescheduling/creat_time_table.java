package com.example.timescheduling;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.timetable.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class creat_time_table extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9, cl10;
    private ProgressDialog progressDialog;
    Button created;
    String bach;
    Spinner spinner,spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_time_table);

        spinner=findViewById(R.id.spinner_bach);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.bach,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner1=findViewById(R.id.spinner_day);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.days,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        created=findViewById(R.id.table_created);

        cl1=findViewById(R.id.col1);
        cl2=findViewById(R.id.col2);
        cl3=findViewById(R.id.col3);
        cl4=findViewById(R.id.col4);
        cl5=findViewById(R.id.col5);
        cl6=findViewById(R.id.col6);
        cl7=findViewById(R.id.col7);
        cl8=findViewById(R.id.col8);
        cl9=findViewById(R.id.col9);
        cl10=findViewById(R.id.col10);

        created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=spinner.getSelectedItemPosition();
                if (pos==1){
                    created1st_yearf2020();
                }
//                else if(pos==2){
//                    createdadminf2019();
//                }
            }

        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            bach =parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), bach, Toast.LENGTH_SHORT).show();

//            String spinner_rol=spinner1.getSelectedItem().toString();

//            switch (spinner_rol){
//                case "Student":
//                    spinner.setVisibility(View.VISIBLE);
//                    ETbatch.setHint("Your Batch F2019");
//                    ETbatch.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
//                    break;
//                /*case "Lecturer":
//                    spinner.setVisibility(View.VISIBLE);
//                    ETbatch.setHint("Employee ID");
//                    ETbatch.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
//                    break;*/
//                case "Lecturer":
//                    spinner.setVisibility(View.INVISIBLE);
//                    ETbatch.setHint("Employee ID");
//                    ETbatch.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
//                    break;
//            }
        }
        if (position==0){
            ((TextView) view).setTextColor(ContextCompat.getColor(this,R.color.textGray));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void created1st_yearf2020(){
        final String day =spinner1.getSelectedItem().toString();
        final String time1=cl1.getText().toString().trim();
        final String time2=cl2.getText().toString();
        final String time3=cl3.getText().toString().trim();
        final String time4=cl4.getText().toString().trim();
        final String time5=cl5.getText().toString().trim();
        final String time6=cl6.getText().toString().trim();
        final String time7=cl7.getText().toString().trim();
        final String time8=cl8.getText().toString().trim();
        final String time9=cl9.getText().toString().trim();
        final String time10=cl10.getText().toString().trim();

        String type="created";

        if(TextUtils.isEmpty(day)) {
            Toast.makeText(creat_time_table.this, "enter the day", Toast.LENGTH_SHORT).show();
        } else {
                    /*progressDialog.setMessage("Registering User....");
                    progressDialog.show();*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_CREAT_TIME_TABLE,


                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                if (!jsonObject.getBoolean("error")){
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), creat_time_table.class));
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //when use this "progressDialog.hide();" app was crashed
                    //progressDialog.hide();
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params=new HashMap<>();
                    params.put("day",day);
                    params.put("8.00-9.00",time1);
                    params.put("9.00-10.00", time2);
                    params.put("10.00-11.00",time3);
                    params.put("11.00-12.00",time4);
                    params.put("12.00-1.00",time5);
                    params.put("13.00-14.00",time6);
                    params.put("14.00-15.00", time7);
                    params.put("15.00-16.00",time8);
                    params.put("16.00-17.00",time9);
                    params.put("17.00-18.00",time10);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(stringRequest);



        }


    }






    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
