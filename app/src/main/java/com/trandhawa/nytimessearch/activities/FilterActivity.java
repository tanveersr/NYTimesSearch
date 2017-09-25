package com.trandhawa.nytimessearch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.trandhawa.nytimessearch.R;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterActivity extends AppCompatActivity {

    DatePicker beginDatePicker;
    Spinner sortOrderSpinner;
    CheckBox checkBoxArts;
    CheckBox checkBoxFashion;
    CheckBox checkboxSports;
    Button btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        beginDatePicker = (DatePicker) findViewById(R.id.datePicker2);
        sortOrderSpinner = (Spinner) findViewById(R.id.spinner);
        checkBoxArts = (CheckBox) findViewById(R.id.checkBox);
        checkBoxFashion = (CheckBox) findViewById(R.id.checkBox2);
        checkboxSports = (CheckBox) findViewById(R.id.checkBox3);
        btnFilter = (Button) findViewById(R.id.button);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent responseIntent = new Intent();

                int   day  = beginDatePicker.getDayOfMonth();
                int   month= beginDatePicker.getMonth();
                int   year = beginDatePicker.getYear()-1900;

                SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String beginDate = mySimpleDateFormat.format(new Date(year, month, day));

                responseIntent.putExtra("beginDate", beginDate);
                responseIntent.putExtra("sortOrder", sortOrderSpinner.getSelectedItem().toString());

                String newsDesk = "news_desk:(";

                String strToEncode="";

                if(checkBoxArts.isChecked()) {
                    strToEncode += "\""+checkBoxArts.getText()+"\" ";
                }

                if(checkBoxFashion.isChecked()) {
                    strToEncode += "\""+checkBoxFashion.getText()+"\" ";
                }

                if(checkboxSports.isChecked()) {
                    strToEncode += "\""+checkboxSports.getText()+"\"";
                }

                String encodedString = URLEncoder.encode(strToEncode);
//                encodedString.replaceAll("\\+","%20");

                newsDesk = newsDesk + encodedString + ")";

                responseIntent.putExtra("newsDesk", newsDesk);

                setResult(RESULT_OK, responseIntent);
                finish();
            }
        });
    }
}
