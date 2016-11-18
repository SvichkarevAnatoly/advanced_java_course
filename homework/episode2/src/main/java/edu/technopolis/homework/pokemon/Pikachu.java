package edu.technopolis.homework.pokemon;

import java.util.Random;

public class Pikachu extends Pokemon {
    public Pikachu() {
        init();
        random = new Random();
    }

    public Pikachu(Random random) {
        super(random);
        init();
        this.random = random;
    }

    private void init() {
        name = "Pikachu";
        attackPower = 40;
        defencePower = 10;
    }
}
