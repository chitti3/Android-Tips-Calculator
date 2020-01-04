package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {
    EditText Fna,Lna,Mn,Email,Pass,Cpass;
    Spinner distspin,subspin;
    String dob;
    Interface anInterface;
    TextView tdob;
    TextView edob;
    LoginInter loginInter;
    Button Reg;
    private int dyear,dmonth,day;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        distspin=findViewById(R.id.dist);
        subspin=findViewById(R.id.sub);
        tdob=findViewById(R.id.dob);
        edob=findViewById(R.id.date);
progressBar=findViewById(R.id.simpleProgressBar);
progressBar.setVisibility(ProgressBar.VISIBLE);
        tdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dyear = c.get(Calendar.YEAR);
                dmonth = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edob.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                        dob=dayOfMonth+"-"+(month+1)+"-"+year;
                    }
                }, dyear, dmonth, day);
                datePickerDialog.show();

            }
        });

        anInterface =LClient.getRetrofit().create(Interface.class);
        Call<List<Spojo>> call = anInterface.spinner();
        call.enqueue(new Callback<List<Spojo>>() {
            @Override
            public void onResponse(Call<List<Spojo>> call, Response<List<Spojo>> response) {
                List<Spojo> res=response.body();
                List<String> listSpinner = new ArrayList<String>();
                listSpinner.add(0,"Choose District");
                for (int i=0;i<res.size();i++)
                {

                    listSpinner.add(res.get(i).getDist());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                distspin.setAdapter(adapter);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Spojo>> call, Throwable t) {

            }
        });

        distspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    subspin.setVisibility(View.GONE);
                }
                else
                if(position==1)
                {
                    tutsubspinner();
                }else if(position==2)
                {
                    tirusubspinner();
                }
                else if(position==3)
                {
                    cspinner();
                }
                else if(position==4)
                {
                    coispinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Reg=findViewById(R.id.reg);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();

            }
        });

        }
    private void coispinner() {
        anInterface=LClient.getRetrofit().create(Interface.class);
        Call<List<Spojo>> coisub = anInterface.coisub();
        coisub.enqueue(new Callback<List<Spojo>>() {
            @Override
            public void onResponse(Call<List<Spojo>> call, Response<List<Spojo>> response) {
                List<Spojo> hey = response.body();
                List<String> coipinner = new ArrayList<String>();
                for (int i=0;i<hey.size();i++)
                {
                    coipinner.add(hey.get(i).getCoisub());
                }
                ArrayAdapter<String> sadapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, coipinner);
                sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subspin.setVisibility(View.VISIBLE);
                subspin.setAdapter(sadapter);
            }

            @Override
            public void onFailure(Call<List<Spojo>> call, Throwable t) {

            }
        });

    }

    private void cspinner() {
        anInterface=LClient.getRetrofit().create(Interface.class);
        Call<List<Spojo>> csub=anInterface.csub();
        csub.enqueue(new Callback<List<Spojo>>() {
            @Override
            public void onResponse(Call<List<Spojo>> call, Response<List<Spojo>> response) {
                List<Spojo> csub=response.body();
                List<String> cpinner = new ArrayList<String>();
                for (int i=0;i<csub.size();i++)
                {
                    cpinner.add(csub.get(i).getCsub());
                }
                ArrayAdapter<String> sadapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, cpinner);
                sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subspin.setVisibility(View.VISIBLE);
                subspin.setAdapter(sadapter);

            }

            @Override
            public void onFailure(Call<List<Spojo>> call, Throwable t) {

            }
        });
    }

    private void tirusubspinner() {
        anInterface=LClient.getRetrofit().create(Interface.class);
        Call<List<Spojo>> tisub = anInterface.tisub();
        tisub.enqueue(new Callback<List<Spojo>>() {
            @Override
            public void onResponse(Call<List<Spojo>> call, Response<List<Spojo>> response) {
                List<Spojo> tisub = response.body();
                List<String> subpinner = new ArrayList<String>();

                for (int i=0;i<tisub.size();i++)
                {

                    subpinner.add(tisub.get(i).getSti());
                }
                ArrayAdapter<String> sadapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, subpinner);
                sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subspin.setVisibility(View.VISIBLE);
                subspin.setAdapter(sadapter);

            }

            @Override
            public void onFailure(Call<List<Spojo>> call, Throwable t) {

            }
        });
    }

    private void tutsubspinner() {
        anInterface=LClient.getRetrofit().create(Interface.class);
        Call<List<Spojo>> sub=anInterface.ssub();
        sub.enqueue(new Callback<List<Spojo>>() {
            @Override
            public void onResponse(Call<List<Spojo>> call, Response<List<Spojo>> response) {
                List<Spojo> subb=response.body();
                List<String> subSpinner = new ArrayList<String>();
                for (int i=0;i<subb.size();i++)
                {
                    subSpinner.add(subb.get(i).getSub());
                }
                ArrayAdapter<String> sadapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, subSpinner);
                sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subspin.setVisibility(View.VISIBLE);
                subspin.setAdapter(sadapter);

            }

            @Override
            public void onFailure(Call<List<Spojo>> call, Throwable t) {

            }
        });

    }
    private void register() {
        Fna=findViewById(R.id.fn);
        Lna=findViewById(R.id.ln);
        Mn=findViewById(R.id.mn);
        Email=findViewById(R.id.email);
        Pass=findViewById(R.id.pass);
        Cpass=findViewById(R.id.cpass);
        final String mobileno=Mn.getText().toString().trim();
        //int mobile =Integer.parseInt(Mobile);
        String fname=Fna.getText().toString().trim();
        String lname=Lna.getText().toString().trim();
        String email=Email.getText().toString().trim();
        // int email = Integer.parseInt(EEmail);
        String password=Pass.getText().toString().trim();
        // int hey =Integer.parseInt(Password);
        String Cpassword=Cpass.getText().toString().trim();
        if(fname.isEmpty())
        {
            Fna.setError("Please Fill");
        }else if(lname.isEmpty())
        {
            Lna.setError("Please Fill");
        }else if(mobileno.isEmpty())
        {
            Mn.setError("PleaseFill");
        }else if(Mn.getText().toString().length()>10)
        {
            Mn.setError("no 10 more Digit");
        }else if (edob.getText().toString().trim().isEmpty())
        {
            edob.setError("Plese choose");
        }
        else if(email.isEmpty())
        {
            Email.setError("Please Fill");
        }
        else if(password.isEmpty() )
        {
            Pass.setError("Please Fill");
        } else if( password.length() <5)
        {
            Pass.setError("Need 5 Character");
        }
        else if(Cpassword.isEmpty())
        {
            Cpass.setError("Please Fill");
        }
        else if( Cpassword.length() <5)
        {
            Cpass.setError("Need 5 Character");
        }else if(!password.equals(Cpassword))
        {
            Toast.makeText(this, "Doesnt Match", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String district = distspin.getSelectedItem().toString();
            // int dis =Integer.parseInt(text);
            // int dob=Integer.parseInt(Dob);
            String sub = subspin.getSelectedItem().toString();
            //int Subd =Integer.parseInt(sub);
            loginInter =LClient.getRetrofit().create(LoginInter.class);
            Call<ResponseBody> call = loginInter.saveNote(fname,lname,mobileno,dob,district,sub,email,password);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(Signup.this,"Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this,MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Signup.this, t.toString(), Toast.LENGTH_SHORT).show();                    }
            });
               }
    }


}
