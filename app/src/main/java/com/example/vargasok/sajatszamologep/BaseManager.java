package com.example.vargasok.sajatszamologep;

import android.util.Log;

public class BaseManager {

    private static Base currentBase = Base.Decimal;

    private MainActivity mainActivity;

    public String toBinary(double x) {
        return Long.toBinaryString(Double.doubleToRawLongBits(x));
    }

    public Base getCurrentBase() {
        return currentBase;
    }

    public void setCurrentBase(Base base) {
        currentBase = base;
    }

    public BaseManager(MainActivity main) {
        mainActivity = main;
    }
}

enum Base {
    Decimal, Binary, Hexadecimal, Octal
}
