package com.personal.durdina.di.knight.guice;

import com.google.inject.Inject;

public class Blacksmith<T> {

    private T material;

    @Inject
    public Blacksmith(T material) {
        this.material = material;
    }

    public Arm<T> produce() {
        return new Arm<T>();
    }

    @Override
    public String toString() {
        return "Wrapper of " + material;
    }

}
