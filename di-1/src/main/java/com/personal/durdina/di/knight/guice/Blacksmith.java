package com.personal.durdina.di.knight.guice;

import com.google.inject.Inject;

public class Blacksmith<T extends Weapon> {

    private T weapon;

    @Inject
    public Blacksmith(T weapon) {
        this.weapon = weapon;
    }

    @SuppressWarnings("unchecked")
    public T make(String name) {
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
