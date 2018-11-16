package com.example.vargasok.sajatszamologep;

import android.widget.Toast;

public class MathManager {

    MainActivity main;

    public double DoMath(Operations op) {

        Operations prevOp = main.getPrevOp();
        double prevResult = main.getPrevResult();

        double result = 0;

        double input = main.Input();
        if (prevOp == null) {
            main.setPrevOp(op);
            if (!main.getJustOutputted())
                return(input);
            else
                return(prevResult);
        }

        switch (prevOp) {
            case Addition:
                result = (prevResult + input);
                break;

            case Subtraction:
                result = (prevResult - input);
                break;

            case Multiplication:
                result = (prevResult * input);
                break;

            case Division:
                if (input == 0) {
                    Toast.makeText(main.getApplicationContext(), "Nullával nem lehet osztani.", Toast.LENGTH_SHORT).show();
                    main.ResetCalculator();
                    return 0;
                }
                result = (prevResult / input);
                break;
        }
        main.setPrevOp(op);
        main.setANS(prevResult);

        return result;
    }

    public MathManager(MainActivity _main) {
        main = _main;
    }
}
