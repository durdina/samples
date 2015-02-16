package com.personal.durdina.di.knight.guice;

import com.google.inject.Inject;

public class Sword extends Weapon {

    @Inject
    public Sword() {
    }

    public double weight() {
        return 20.5;
    }

    @Override
    public String toString() {
        return "sword";
    }

}
