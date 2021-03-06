package com.personal.durdina.di.knight.guice;

public abstract class Weapon implements Cloneable {

    public abstract double weight();

    @Override
    public Weapon clone() throws CloneNotSupportedException {
        return (Weapon) super.clone();
    }

}
