package com.personal.durdina.di.knight.guice;

public class Sword extends Weapon {

    public double weight() {
        return 20.5;
    }

    @Override
    public String toString() {
        return "sword";
    }

}
