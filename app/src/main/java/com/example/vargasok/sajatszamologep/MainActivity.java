package com.example.vargasok.sajatszamologep;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.InputDevice;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.Scanner;

import static java.lang.Double.toHexString;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    TextView t1;
    Button buttons[] = new Button[22];

    public szamrendszerek szamrendszer= szamrendszerek.decimal;


    double prevInput, prevResult, ANS, dec, bin, hex;
    boolean isBin = false;
    boolean isHex = false;
    boolean isDec = true;
    boolean justOutputted = true;
    boolean firstInput = true;
    boolean ANSPressed = false;
    private Operations prevOp = null;
    private MathManager mathManager = new MathManager(this);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.style_white:
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setTextColor(Color.BLACK);
                }
                return true;

            case R.id.style_black:
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setTextColor(Color.WHITE);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0] = findViewById(R.id.szam0);
        buttons[1] = findViewById(R.id.szam1);
        buttons[2] = findViewById(R.id.szam2);
        buttons[3] = findViewById(R.id.szam3);
        buttons[4] = findViewById(R.id.szam4);
        buttons[5] = findViewById(R.id.szam5);
        buttons[6] = findViewById(R.id.szam6);
        buttons[7] = findViewById(R.id.szam7);
        buttons[8] = findViewById(R.id.szam8);
        buttons[9] = findViewById(R.id.szam9);
        buttons[10] = findViewById(R.id.comma);
        buttons[11] = findViewById(R.id.plussz);
        buttons[12] = findViewById(R.id.minusz);
        buttons[13] = findViewById(R.id.szorzas);
        buttons[14] = findViewById(R.id.osztas);
        buttons[15] = findViewById(R.id.egyenlo);
        buttons[16] = findViewById(R.id.AC);
        buttons[17] = findViewById(R.id.ANS);
        buttons[18] = findViewById(R.id.DEC);
        buttons[19] = findViewById(R.id.BIN);
        buttons[20] = findViewById(R.id.HEX);

        buttons[0].setOnClickListener(this);
        buttons[1].setOnClickListener(this);
        buttons[2].setOnClickListener(this);
        buttons[3].setOnClickListener(this);
        buttons[4].setOnClickListener(this);
        buttons[5].setOnClickListener(this);
        buttons[6].setOnClickListener(this);
        buttons[7].setOnClickListener(this);
        buttons[8].setOnClickListener(this);
        buttons[9].setOnClickListener(this);
        buttons[10].setOnClickListener(this);
        buttons[11].setOnClickListener(this);
        buttons[12].setOnClickListener(this);
        buttons[13].setOnClickListener(this);
        buttons[14].setOnClickListener(this);
        buttons[15].setOnClickListener(this);
        buttons[16].setOnClickListener(this);
        buttons[17].setOnClickListener(this);
        buttons[18].setOnClickListener(this);
        buttons[19].setOnClickListener(this);
        buttons[20].setOnClickListener(this);

        t1 = findViewById(R.id.kijelzo1);
    }

    public void onClick(View v) {
        Button button = (Button) v;

        int buttonId = button.getId();

        String input = t1.getText().toString();

        switch (button.getId()) {
            case R.id.szam0:
                if (input.equals("0"))
                    break;

            case R.id.comma:
                if (input.contains(".")) {
                    StringBuilder sb = new StringBuilder(input);
                    sb.deleteCharAt(input.indexOf("."));
                    t1.setText(sb.toString());
                }

            case R.id.szam1:
            case R.id.szam2:
            case R.id.szam3:
            case R.id.szam4:
            case R.id.szam5:
            case R.id.szam6:
            case R.id.szam7:
            case R.id.szam8:
            case R.id.szam9:

                if (justOutputted && buttonId != R.id.comma) {
                    t1.setText(button.getText().toString());
                    justOutputted = false;
                } else {
                    t1.setText(t1.getText() + button.getText().toString());
                    justOutputted = false;
                }
                return;

            case R.id.plussz:
                Gradient(11);
                Display(mathManager.DoMath(Operations.Addition));
                break;
            case R.id.minusz:
                Gradient(12);
                Display(mathManager.DoMath(Operations.Subtraction));
                break;
            case R.id.szorzas:
                Gradient(13);
                Display(mathManager.DoMath(Operations.Multiplication));
                break;
            case R.id.osztas:
                Gradient(14);
                Display(mathManager.DoMath(Operations.Division));
                break;

            case R.id.egyenlo:
                if (prevOp != null) {
                    Display(mathManager.DoMath(Operations.Equal));
                    Gradient();
                } else
                    Display(Input());

                break;

            case R.id.AC:
                ResetCalculator();
                Gradient();
                break;

            case R.id.ANS:
                ANSPressed = true;
                Display(ANS);
                break;

            case R.id.DEC:
                Gradient(18);
                szamrendszer=szamrendszerek.decimal;
                if (isHex)
                {

                }
                else if (isBin)
                {
                    Display(prevResult = Integer.parseInt(t1.getText().toString(), 2));
                    isBin = false;
                }
                else
                {
                    Display(prevResult);
                    isDec = false;
                }

                break;

            case R.id.BIN:

                szamrendszer=szamrendszerek.binary;

                break;

            case R.id.HEX:
                Gradient(20);
                int prevR = 1;
                prevR = Integer.parseInt(Double.toString(prevResult));
                String hex = Integer.toHexString(prevR);
                Log.i("Hex", hex);
                Display(hex);
                break;
        }
    }

    public void ResetCalculator() {
        ANS = prevResult;
        Display(0);
        firstInput = true;
        prevInput = 0;
        prevOp = null;
        prevResult = 0;
    }

    private  void Display(String s)
    {
        t1.setText(s);
    }

    private void Display(double x) {

        justOutputted = true;

        if(isBin==false)
        {
            prevResult = x;
            isBin = false;
            t1.setText(Double.toString(x));
        }
    //    else
     //   {
     //       DecimalFormat df = new DecimalFormat("###,###,###,###.###############");
    //        t1.setText(df.format(x));
  //      }
        prevResult = x;
    }

    public double Input() {
        String input = t1.getText().toString();


        if (!justOutputted) {
            prevInput = Double.parseDouble(input);
        }
        return prevInput;
    }

    private void Gradient(int id) {
        Gradient();
        buttons[id].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clicked_gradient));
    }

    private void Gradient() {
        buttons[11].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.operation_gradient));
        buttons[12].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.operation_gradient));
        buttons[13].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.operation_gradient));
        buttons[14].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.operation_gradient));
        buttons[18].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.m_gradient));
        buttons[19].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.m_gradient));
        buttons[20].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.m_gradient));
    }

    public Operations getPrevOp() {
        return prevOp;
    }

    public void setPrevOp(Operations op) {
        prevOp = op;
    }

    public double getPrevResult() {
        return prevResult;
    }

    public boolean getJustOutputted() {
        return justOutputted;
    }

public szamrendszerek getNumsys(){return szamrendszer;}







    public double fromhexa(char[] convertible){
        double vegsoertek=0;
        int i =convertible.length;
        while (i>0){switch(convertible[i-1]){
            case 'A':  vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*10);i--;
                break;
            case 'B':vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*11);i--;
                break;
            case 'C':vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*12);i--;
                break;
            case 'D':vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*13);i--;
                break;
            case 'E':vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*14);i--;
                break;
            case 'F':vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*15);i--;
                break;

            default: vegsoertek=vegsoertek+(Math.pow(16,convertible.length-i)*Character.getNumericValue(convertible[i-1]));i--;
                break;
        }




        }
        return(vegsoertek);

    }




    public String  tohexa(int convertible){


        String szam="" ;
        while(convertible>0){

            if((convertible%16)>9 && (convertible%16)<16){
                switch(convertible%16){

                    case 10: szam=szam+"A";
                        break;

                    case 11: szam=szam+"B";
                        break;

                    case 12: szam=szam+"C";
                        break;

                    case 13: szam=szam+"D";
                        break;

                    case 14: szam=szam+"E";
                        break;

                    case 15: szam=szam+"F";
                        break; }
                convertible=convertible/16;}

            else{szam=szam+(convertible%16);
                convertible=convertible/16;
            }


        }



        return(new StringBuilder(szam).reverse().toString());}
}

enum Operations
{
    Addition, Subtraction, Multiplication, Division, Equal
}


 enum szamrendszerek{decimal,binary,hexa}


