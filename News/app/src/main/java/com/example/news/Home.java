package com.example.news;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.news.Model.Articles;
import com.example.news.Model.Headlines;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Recycle recycle;
    FloatingActionButton Floating;
    Profile pro;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    EditText query;
    Button Search;
    Adapter adapter;
    SearchView searchView = null;
    SearchView.OnQueryTextListener queryTextListener;
    SwipeRefreshLayout swipeRefreshLayout;
    final String apikey="9c952917eb4c4d20ac26b29cd474bc07";
    List<Articles> articles = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_home);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.hello);
       swipeRefreshLayout=findViewById(R.id.swipe);
        navigationView=findViewById(R.id.navigation);
     progressBar=findViewById(R.id.simpleProgressBar);
     progressBar.setVisibility(ProgressBar.VISIBLE);
      query=findViewById(R.id.euery);
        final String country =getCountry();
      Search=findViewById(R.id.search);
      Search.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             if (!query.getText().toString().equals(""))
             {
                 swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                     @Override
                     public void onRefresh() {
                         retrieve(query.getText().toString(),"",apikey);
                     }
                 });
                 retrieve(query.getText().toString(),"",apikey);
             }else {
                 swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                     @Override
                     public void onRefresh() {
                         retrieve("",country,apikey);
                     }
                 });

                 retrieve("",country,apikey);
             }


              //  Toast.makeText(Home.this, Query, Toast.LENGTH_SHORT).show();
          }
      });
        recyclerView=findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

     // final String country =getCountry();
        retrieve("",country,apikey);
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieve("",country,apikey);
            }
        });
        Floating = findViewById(R.id.floating);
        Floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Media.class);
                startActivity(intent);
                //  Toast.makeText(Home.this, "hey", Toast.LENGTH_SHORT).show();
            }
        });
//        setSupportActionBar(toolbar);
        getSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
       // navigationView.setNavigationItemSelectedListener(this);
    }

    /*private void prepareMovieData() {
      Recycle recycle = new Recycle(R.drawable.news,"Lorem Ipsum","\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\n");
      ReList.add(recycle);
         recycle = new Recycle(R.drawable.news,"Lorem Ipsum","\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\n");
        ReList.add(recycle);
        recycle = new Recycle(R.drawable.news,"Lorem Ipsum","\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\n");
        ReList.add(recycle);
         recycle = new Recycle(R.drawable.news,"Lorem Ipsum","\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\n");
        ReList.add(recycle);
        recyclerAdpater.notifyDataSetChanged();
    }*/

    private void getSupportActionBar(Toolbar toolbar) {
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId())
        {
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(getApplicationContext(), Profile.class);
                // intent.putExtra("SELECTEDVALUE", 2);//1 for fragament A use 2 for fragment B
                // startActivity(intent);


                break;
            case R.id.mm:
                Toast.makeText(this, "Contactus", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "Aboutus", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(Home.this,"You clicked yes 1button",Toast.LENGTH_LONG).show();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(Home.this, "hey", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }*/
    private void retrieve(String Query,String country,String apikey){
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = Client.getRetrofit().create(ApiInterface.class);
        Call<Headlines> call;
        if (!query.getText().toString().equals(""))
        {
            call = apiInterface.geteeverything(Query,apikey);
        }
        else {
            call = apiInterface.getHeadlines(country,apikey);
        }

         //call = apiInterface.getHeadlines(country,apikey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null)
                {
                    swipeRefreshLayout.setRefreshing(true);
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter = new Adapter(Home.this,articles);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e("hellp00",response.body().getTotalResults());
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Home.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private  String getCountry(){
        Locale locale =Locale.getDefault();
        String country =locale.getCountry();
        return  country.toLowerCase();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId())
        {
            case R.id.profile:
                pro=new Profile();
                navigationView(pro);
               Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(getApplicationContext(), Profile.class);
                // intent.putExtra("SELECTEDVALUE", 2);//1 for fragament A use 2 for fragment B
                // startActivity(intent);

                break;
            case R.id.mm:
                Toast.makeText(this, "Contactus", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "Aboutus", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(Home.this,"You clicked yes 1button",Toast.LENGTH_LONG).show();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(Home.this, "hey", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
     drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigationView(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}
