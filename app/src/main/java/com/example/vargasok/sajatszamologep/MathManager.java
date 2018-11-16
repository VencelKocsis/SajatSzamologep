package com.example.vargasok.sajatszamologep;

import android.widget.Toast;

public class MathManager {

    MainActivity main;

    public double DoMath(Operations op) {

        Operations prevOp = main.getPrevOp();
        double prevResult = main.getPrevResult();

        double input = main.Input();
        if (prevOp == null) {
            main.setPrevOp(op);
            if (!main.getJustOutputted())
                return(input);
            else
                return(prevResult);
        }
        main.setPrevOp(op);
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
        }

        return prevResult;
    }

    public MathManager(MainActivity _main) {
        main = _main;
    }
}
