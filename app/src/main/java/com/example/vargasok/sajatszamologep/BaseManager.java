package com.example.vargasok.sajatszamologep;

public class BaseManager {

    private static Base currentBase = Base.Decimal;

    private MainActivity mainActivity;

    public Double toBinary(double x) {
        return Double.parseDouble(Long.toBinaryString(Double.doubleToLongBits(x)));
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
    Decimal, Binary, Heximal, Octal
}
