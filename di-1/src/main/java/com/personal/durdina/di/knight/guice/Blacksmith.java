package com.personal.durdina.di.knight.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class Blacksmith<T extends Weapon> implements Provider<T> {

    private T weapon;

    @Inject
    public Blacksmith(T weapon) {
        this.weapon = weapon;
    }

    @Override
    public T get() {
        return make();
    }

    @SuppressWarnings("unchecked")
    protected T make() {
        try {
            return (T) weapon.clone();
        } catch (Exception e) {
            throw new UnsupportedOperationException(weapon.getClass() + " does not implement cloning");
        }
    }

    @Override
    public String toString() {
        return "Smith of " + weapon + "s";
    }

}
