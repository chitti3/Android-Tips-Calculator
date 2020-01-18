package com.example.news;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {
    private EditText StDate,Endate,search;
    ExampleDialogListener listener;
    private int dyear,dmonth,day;
    private int eyear,emonth,eday;
    String Date,Edate,query;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,null);

        builder.setView(view);
        builder.setTitle("Filter");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 if (search.getText().toString().equals(""))
                 {
                     search.setText("");
                 }else {
                     //   String stdate = StDate.getText().toString();
                     query = search.getText().toString();
                     listener.applytext(Date, Edate, query);
                 }

            }
        });
        StDate=view.findViewById(R.id.sdate);
        StDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                dyear = calendar.get(Calendar.YEAR);
                dmonth = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        Date = year + "-" + (month + 1) + "-" + dayOfMonth;
                    }
                },dyear,dmonth,day);
                datePickerDialog.show();
            }


        });
        Endate=view.findViewById(R.id.edate);
        Endate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cale = Calendar.getInstance();
                eyear=cale.get(Calendar.YEAR);
                emonth=cale.get(Calendar.MONTH);
                eday=cale.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Endate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        Edate = year + "-" + (month + 1) + "-" + dayOfMonth;
                    }
                },eyear,emonth,eday);
                date.show();

            }
        });
        search=view.findViewById(R.id.sear);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(ExampleDialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString()+"must ");
        }
    }

    public interface ExampleDialogListener{
        void applytext(String sdate,String edate,String query);
    }
}
