package edu.technopolis.homework;

import edu.technopolis.homework.pokemon.Pokemon;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Trainer {
    private final List<Pokemon> pokemons;
    private final String name;
    private String slogan;
    private final Random random;

    public Trainer(List<Pokemon> pokemons, String name) {
        this.pokemons = pokemons;
        this.name = name;
        random = new Random();
    }

    public Trainer(Pokemon pokemon, String name) {
        pokemons = Collections.singletonList(pokemon);
        this.name = name;
        random = new Random();
    }

    public Trainer(Pokemon pokemon, String name, Random random) {
        pokemons = Collections.singletonList(pokemon);
        this.name = name;
        this.random = random;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return name;
    }

    public void introduce() {
        if (slogan != null) {
            System.out.println(slogan);
        } else {
            System.out.println("I'm " + name);
        }
    }

    public Pokemon choose() {
        final int pokemonIndex = random.nextInt(pokemons.size());
        final Pokemon pokemon = pokemons.get(pokemonIndex);
        System.out.println(name + ": " + pokemon + ", I choose you!");
        return pokemon;
    }

    public Command giveCommand() {
        return Command.values()[random.nextInt(4)];
    }
}
