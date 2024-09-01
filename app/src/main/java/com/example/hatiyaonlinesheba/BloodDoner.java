package com.example.hatiyaonlinesheba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BloodDoner extends AppCompatActivity {

    ListView listView;
    LinearLayout newAccount;
    HashMap<String, String> hashMap;
    ArrayList< HashMap<String, String> > arrayList = new ArrayList<>();
    ProgressBar progressBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_doner);;
        listView = findViewById(R.id.listView);
        newAccount = findViewById(R.id.newAccount);
        progressBar2 = findViewById(R.id.progressBar2);
        //=======================================================





        //=========================
        AddData();


        //=============================================================================
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------------------------
                Dialog dialog = new Dialog(BloodDoner.this, R.style.InternetDialog);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.setContentView(R.layout.doner_from);
                //----------------------
                EditText edName = dialog.findViewById(R.id.edName);
                EditText edBlood = dialog.findViewById(R.id.edBlood);
                EditText edLbd = dialog.findViewById(R.id.edLbd);
                EditText edMobile = dialog.findViewById(R.id.edMobile);
                EditText edEmail = dialog.findViewById(R.id.edEmail);
                EditText edAddress = dialog.findViewById(R.id.edAddress);
                LinearLayout Dsign_up = dialog.findViewById(R.id.Dsign_up);
                ProgressBar progressBar = dialog.findViewById(R.id.progressBar);

                Dsign_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (edName.length()>0 && edBlood.length()>0 && edBlood.length()<=4 && edMobile.length()>0){
                            //---------------------------

                            String name = edName.getText().toString();
                            String blood = edBlood.getText().toString();
                            String lbd = edLbd.getText().toString();
                            String mobile = edMobile.getText().toString();
                            String email = edEmail.getText().toString();
                            String address = edAddress.getText().toString();
                            String url = "https://sakib71.000webhostapp.com/apps/b_doner_data_insert.php?name=" + name +
                                    "&blood=" + blood + "&lbd=" + lbd + "&mobile=" + mobile + "&email=" + email + "&address=" + address ;

                            RequestQueue queue = Volley.newRequestQueue(BloodDoner.this);
                            progressBar.setVisibility(View.VISIBLE);
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            //----------------------
                                            progressBar.setVisibility(View.GONE);
                                            dialog.dismiss();
                                            new AlertDialog.Builder(BloodDoner.this)
                                                    .setTitle("Successful")
                                                    .setMessage(response.toString())
                                                    .show();

                                            AddData();

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //---------------------------
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(BloodDoner.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            queue.add(stringRequest);
                            //---------------------------

                        }else {
                            new AlertDialog.Builder(BloodDoner.this)
                                    .setMessage("নাম, নাম্বার এবং রক্তের গ্রুপ এর ঘর সঠিকভাবে পূরণ করুন।")
                                    .show();
                        }
                        //-------------

                    }
                });
                dialog.show();

            }
        });














    }//on create bundle end==================================

    //create a listView helper Adapter=======================
    public class myAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.doner_item, null);
            //
            LinearLayout bloodItem = myView.findViewById(R.id.bloodItem);
            ImageView iconImg = myView.findViewById(R.id.iconImg);
            TextView Dname = myView.findViewById(R.id.Dname);
            TextView bloodGroup = myView.findViewById(R.id.bloodGroup);
            TextView lastDonet = myView.findViewById(R.id.lastDonet);
            TextView address = myView.findViewById(R.id.address);
            LinearLayout dataEdit = myView.findViewById(R.id.dataEdit);
            TextView updateBtn = myView.findViewById(R.id.updateBtn);
            TextView deleteBtn = myView.findViewById(R.id.deleteBtn);

            //
            hashMap = arrayList.get(position);
            //
            String Name = hashMap.get("name");
            String Blood = hashMap.get("blood");
            String Lbd = hashMap.get("lbd");
            String Mobile = hashMap.get("mobile");
            String Email = hashMap.get("email");
            String Address = hashMap.get("address");
            String ID = hashMap.get("id");

            //
            Dname.setText("N - " + Name);
            bloodGroup.setText("Blood Group: [ "+Blood+" ]");
            lastDonet.setText("L.B.D - " + Lbd);
            address.setText("A - " + Address);

            //update data from database
            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    //----------------------------
                    Dialog dialog = new Dialog(BloodDoner.this, R.style.InternetDialog);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    dialog.setContentView(R.layout.doner_from);
                    //----------------------
                    EditText edName = dialog.findViewById(R.id.edName);
                    EditText edBlood = dialog.findViewById(R.id.edBlood);
                    EditText edLbd = dialog.findViewById(R.id.edLbd);
                    EditText edMobile = dialog.findViewById(R.id.edMobile);
                    EditText edEmail = dialog.findViewById(R.id.edEmail);
                    EditText edAddress = dialog.findViewById(R.id.edAddress);
                    LinearLayout Dsign_up = dialog.findViewById(R.id.Dsign_up);
                    ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
                    ProgressBar progressBar2 = dialog.findViewById(R.id.progressBar);

                    Dsign_up.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (edName.length()>0 && edBlood.length()>0 && edBlood.length()<=4 && edMobile.length()>0){
                                //---------------------------

                                String name = edName.getText().toString();
                                String blood = edBlood.getText().toString();
                                String lbd = edLbd.getText().toString();
                                String mobile = edMobile.getText().toString();
                                String email = edEmail.getText().toString();
                                String address = edAddress.getText().toString();
                                String url = "https://sakib71.000webhostapp.com/apps/update_data.php?name=" + name +
                                        "&blood=" + blood + "&lbd=" + lbd + "&mobile=" + mobile + "&email=" + email + "&address=" + address + "&id=" + ID ;

                                RequestQueue queue = Volley.newRequestQueue(BloodDoner.this);
                                progressBar.setVisibility(View.VISIBLE);
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                //----------------------
                                                progressBar.setVisibility(View.GONE);
                                                dialog.dismiss();
                                                new AlertDialog.Builder(BloodDoner.this)
                                                        .setTitle("Successful")
                                                        .setMessage(response.toString())
                                                        .show();

                                                AddData();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //---------------------------
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(BloodDoner.this, error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(stringRequest);
                                //---------------------------

                            }else {
                                new AlertDialog.Builder(BloodDoner.this)
                                        .setMessage("নাম, নাম্বার এবং রক্তের গ্রুপ এর ঘর সঠিকভাবে পূরণ করুন।")
                                        .show();
                            }
                            //-------------

                        }
                    });
                    dialog.show();


                }
            });

            //delete data from database
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    String url = "https://sakib71.000webhostapp.com/apps/delete_data.php?id=" + ID;

                    progressBar2.setVisibility(View.VISIBLE);
                    RequestQueue queue = Volley.newRequestQueue(BloodDoner.this);
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar2.setVisibility(View.GONE);
                                    new AlertDialog.Builder(BloodDoner.this)
                                            .setTitle("Server Response")
                                            .setMessage(response)
                                            .show();

                                    AddData();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar2.setVisibility(View.GONE);
                            Toast.makeText(BloodDoner.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(request);
                }
            });

            //new activity
            bloodItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //------------------

                    Doner_Details.name = Name;
                    Doner_Details.blood = Blood;
                    Doner_Details.lbd = Lbd;
                    Doner_Details.mobile = Mobile;
                    Doner_Details.email = Email;
                    Doner_Details.address = Address;
                    startActivity(new Intent(BloodDoner.this, Doner_Details.class));

                }
            });


            return myView;
        }
    }



    //mathod--- Data load from mysql database===========================================
    private void AddData(){
        //--------------------------
        arrayList = new ArrayList<>();
        String url = "https://sakib71.000webhostapp.com/apps/b_doner_data_get.php";
        RequestQueue queue = Volley.newRequestQueue(BloodDoner.this);
        progressBar2.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //---------------------------
                        progressBar2.setVisibility(View.GONE);

                        for (int i=0; i<response.length(); i++){

                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String blood = jsonObject.getString("blood");
                                String lbd = jsonObject.getString("lbd");
                                String mobile = jsonObject.getString("mobile");
                                String email = jsonObject.getString("email");
                                String address = jsonObject.getString("address");
                                String id = jsonObject.getString("id");

                                hashMap = new HashMap<>();
                                hashMap.put("name", name);
                                hashMap.put("blood", blood);
                                hashMap.put("lbd", lbd);
                                hashMap.put("mobile", mobile);
                                hashMap.put("email", email);
                                hashMap.put("address", address);
                                hashMap.put("id", id);
                                arrayList.add(hashMap);


                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        //------------------
                        if (arrayList.size()>0){
                            myAdapter adaptar = new myAdapter();
                            listView.setAdapter(adaptar);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //--------------------------
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(BloodDoner.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);

    }






}