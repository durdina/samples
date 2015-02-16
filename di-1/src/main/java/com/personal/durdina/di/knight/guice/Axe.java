package com.personal.durdina.di.knight.guice;

import com.google.inject.Inject;

public class Axe extends Weapon {

    @Override
    public double weight() {
        return 48.0;
    }

    @Inject
    public Axe() {
    }

    @Override
    public String toString() {
        return "axe";
    }

}
