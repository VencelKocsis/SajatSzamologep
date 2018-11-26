package com.example.vargasok.sajatszamologep;

public class BaseManager {

    private static Base currentBase = Base.Decimal;

    private MainActivity main;

    public String toBinary(double x) {
        String y = Long.toBinaryString(Double.doubleToRawLongBits(x));
        if (y.startsWith(Integer.toString(0)))
            y = "-" + y.substring(1);

        return y;
    }

    public Base getCurrentBase() {
        return currentBase;
    }

    public void setCurrentBase(Base base) {
        currentBase = base;
    }

    public BaseManager(MainActivity _main) {
        main = _main;
    }
}

enum Base {
    Decimal, Binary, Hexadecimal, Octal
}
