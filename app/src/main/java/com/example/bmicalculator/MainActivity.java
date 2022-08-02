package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
     RadioGroup gender;
     RadioButton male,female;
     EditText Age,feet,inches,weight;
     TextView msg;
     Button calculate;
     LinearLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(feet.getText().toString().equals("") ||inches.getText().toString().equals("")||Age.getText().toString().equals("")|| weight.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    double bmi = calculateBmi();
                    int age1 = Integer.parseInt(Age.getText().toString());
                    if (age1 >= 18)
                        displayBmi(bmi);
                    else
                        displayGuidance(bmi);
                }
            }
        });

    }

    private void displayGuidance(double bmi) {
        DecimalFormat df=new DecimalFormat("0.00");
        String bmiRes=df.format(bmi);
        String fullRes;
        if(male.isChecked())
        {
            fullRes=bmiRes+" - As you are under 18 , please consult a doctor for healthy range\n for boys";
        }
        else if(female.isChecked())
        {
            fullRes=bmiRes+" - As you are under 18 , please consult a doctor for healthy range \nfor girls";
        }
        else{
            fullRes=bmiRes+" - As you are under 18 , please consult a doctor for healthy range";
        }
       Snackbar snackbar= Snackbar.make(parent,fullRes,Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        View snackBarView = snackbar.getView();
        TextView tv= (TextView) snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setMaxLines(3);
        snackbar.show();
    }

    private double calculateBmi() {

        int feet1=Integer.parseInt(feet.getText().toString());
        int inches1=Integer.parseInt(inches.getText().toString());
        int weight1=Integer.parseInt(weight.getText().toString());

        int totalInches=(feet1 *12)+inches1;
        double heightInMeters=totalInches*0.0254;
        double bmi=weight1/(heightInMeters*heightInMeters);

      return bmi;

    }
    private void displayBmi(double bmi)
    {
        DecimalFormat df=new DecimalFormat("0.00");
        String bmiRes=df.format(bmi);
        String fullRes;
        if(bmi<18.5)
        {
            fullRes=bmiRes+" - You are underweight";
        }
        else if(bmi>25)
        {
            fullRes=bmiRes+" - You are overweight";
        }
        else
        {
            fullRes=bmiRes+" - You are healthy";
        }
        Snackbar.make(parent,fullRes,Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    private void initViews()
    {
        gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        Age=findViewById(R.id.age);
        feet=findViewById(R.id.feet);
        inches=findViewById(R.id.inches);
        weight=findViewById(R.id.weight);
        msg=findViewById(R.id.msg);
        calculate=findViewById(R.id.cal);
        parent=findViewById(R.id.parent1);
    }
}