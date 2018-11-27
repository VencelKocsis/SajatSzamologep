package com.example.vargasok.sajatszamologep;

import android.widget.Toast;



public class MathManager {

    MainActivity main;


    public double DoMath(Operations op) {

        Operations prevOp = main.getPrevOp();
        double prevResult = main.getPrevResult();
        szamrendszerek szamrendszer=main.getNumsys();
        double ered;




        double input = main.Input();
        if (prevOp == null) {
            main.setPrevOp(op);
            if (!main.getJustOutputted())
                return(input);
           else
                return(prevResult);
        }
        main.setPrevOp(op);


        switch(szamrendszer){
            case decimal:
        switch (prevOp) {
            case Addition:
                return(prevResult + input);

            case Subtraction:
                return(prevResult - input);

            case Multiplication:
                return(prevResult * input);

            case Division:
                if (input == 0) {
                    Toast.makeText(main.getApplicationContext(), "Nullával nem lehet osztani.", Toast.LENGTH_SHORT).show();
                    main.ResetCalculator();
                    return 0;
                }
                return(prevResult / input);
        }break;

            case binary:

                prevResult=frombinary(prevResult);
                input=frombinary(input);
                switch(prevOp){

                    case Addition:

                    ered =prevResult+input;
                        return(tobinary((int)ered));

                        } break;


        }

        return prevResult;
    }

    public MathManager(MainActivity _main) {
        main = _main;
    }



    public double tobinary(int convertible){

        String szam="";
        while (convertible>0){szam=szam+convertible%2;convertible=convertible/2;}

        if( szam.isEmpty()==true){  Toast.makeText(main, "Kérem adjon meg megfelelő értéket.", Toast.LENGTH_SHORT).show(); return(0);
        }
        else{
            return(Double.parseDouble(new StringBuilder(szam).reverse().toString()));}
    }


    public double frombinary(double convertible){

        char[] convertibleR = Double.toString(convertible).toCharArray();
        int i =convertibleR.length;
        while (i>0){if(convertibleR[i-1]=='1'){convertible=convertible+Math.pow(2,convertibleR.length-i);}i--;}

        return (convertible);



    }

}
