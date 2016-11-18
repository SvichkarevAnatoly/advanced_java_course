package edu.technopolis.homework;

import edu.technopolis.homework.pokemon.Pikachu;
import edu.technopolis.homework.pokemon.Pokemon;
import edu.technopolis.homework.pokemon.Squirtle;

public class Main {
    public static void main(String[] args) {
        final Pokemon pikachu = new Pikachu();
        final Trainer ash = new Trainer(pikachu, "Ash");

        final Pokemon squirtle = new Squirtle();
        final Trainer misty = new Trainer(squirtle, "Misty");

        final Battlefield battlefield = new Battlefield(ash, misty);
        battlefield.battle();
    }
}
