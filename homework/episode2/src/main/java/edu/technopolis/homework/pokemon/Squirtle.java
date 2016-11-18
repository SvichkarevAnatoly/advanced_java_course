package edu.technopolis.homework.pokemon;

import java.util.Random;

public class Squirtle extends Pokemon {
    public Squirtle() {
        init();
        random = new Random();
    }

    public Squirtle(Random random) {
        super(random);
        init();
        this.random = random;
    }

    private void init() {
        name = "Squirtle";
        attackPower = 30;
        defencePower = 30;
    }
}
