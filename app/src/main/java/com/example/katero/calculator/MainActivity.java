package com.example.katero.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends Activity implements TextView.OnEditorActionListener,View.OnClickListener {

    private EditText billAmmountEdit;
    private Button upButton;
    private Button downButton;
    private TextView percentView;
    private  TextView tipView;
    private TextView totalView;

    private float tipPercent=.10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

               //kanw sundeseis me ta widgets
        billAmmountEdit=(EditText)findViewById(R.id.bill_ammountEdit); //to poso tou logariasmou
        percentView=(TextView)findViewById(R.id.percentText); //to poso %
        upButton=(Button)findViewById(R.id.upbtn);
        downButton=(Button)findViewById(R.id.downbtn);
        tipView=(TextView)findViewById(R.id.tip_Text); //to poso twn tips
        totalView=(TextView)findViewById(R.id.TotalView); //to sunolo

        //vazw listeners
        billAmmountEdit.setOnEditorActionListener(this);
        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);

        calculateTheTotal();
    }

    public void calculateTheTotal(){

        //pairnei to logariasmo
        String billAmmountString=billAmmountEdit.getText().toString(); //pairnei ton arithmo kai tn kanei string
        float billAmmount;
        if(billAmmountString.equals("")){
            billAmmount=0;
        }else {
            billAmmount=Float.parseFloat(billAmmountString); //ton metatrepei se float
        }

        float tip=billAmmount*tipPercent; //upologizei to tip me basi to logariasmo kai to poso %
        float total=billAmmount+tip; //upologizei to sunolo

        NumberFormat currency=NumberFormat.getCurrencyInstance();
        tipView.setText(currency.format(tip)); //ftiaxnei to view twn tips
        totalView.setText(currency.format(total));

        NumberFormat percent=NumberFormat.getPercentInstance();
        percentView.setText(percent.format(tipPercent));

    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.downbtn:
                tipPercent=tipPercent-0.01f;
                calculateTheTotal();
                break;
            case R.id.upbtn:
                tipPercent=tipPercent+0.01f;
                calculateTheTotal();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateTheTotal();
        return false;
    }
}
