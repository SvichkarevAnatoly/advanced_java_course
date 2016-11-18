package edu.technopolis.homework;

import edu.technopolis.homework.pokemon.Pokemon;

public class Battlefield {
    private Trainer trainer1;
    private Trainer trainer2;

    public Battlefield(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
    }

    public void battle() {
        intro();

        final Pokemon pokemon1 = trainer1.choose();
        final Pokemon pokemon2 = trainer2.choose();
        System.out.println();

        int i = 1;
        while (pokemon1.isAlive() && pokemon2.isAlive()) {
            round(pokemon1, pokemon2, i);
            i++;
        }

        showBattleResult(pokemon1, pokemon2);
    }

    private void round(Pokemon pokemon1, Pokemon pokemon2, int i) {
        System.out.println("Round " + i + ":");
        final Command command1 = trainer1.giveCommand();
        final Command command2 = trainer2.giveCommand();

        System.out.println(trainer1 + ": " + pokemon1 + ", " + command1 + "!");
        System.out.println(trainer2 + ": " + pokemon2 + ", " + command2 + "!");

        round(pokemon1, pokemon2, command1, command2);
        System.out.println();
    }

    private void intro() {
        trainer1.introduce();
        trainer2.introduce();

        System.out.println("\nLet's battle begin!");
        System.out.println("Choose your pokemon.\n");
    }

    private void showBattleResult(Pokemon pokemon1, Pokemon pokemon2) {
        if (!pokemon1.isAlive() && !pokemon2.isAlive()) {
            System.out.println("It's draw! Wow!");
        } else {
            final String winner = pokemon1.isAlive() ? trainer1.toString() : trainer2.toString();
            System.out.println(winner + ": I am the best pokemon trainer ever!");
        }
    }

    private void round(Pokemon pokemon1, Pokemon pokemon2,
                       Command c1, Command c2) {
        int attack;

        switch (c1) {
            case ATTACK:
            case SUPER_ATTACK:
                attack = c1 == Command.ATTACK ? pokemon1.attack() : pokemon1.superAttack();
                int damage;
                if (c2 == Command.DEFENCE) {
                    damage = pokemon2.defence(attack);
                } else {
                    damage = pokemon2.receiveDamage(attack);
                }
                System.out.println(pokemon2 + " get " + damage + " damage. Health = " + pokemon2.getHealth());

                if (c2 == Command.ATTACK || c2 == Command.SUPER_ATTACK) {
                    attack = c2 == Command.ATTACK ? pokemon2.attack() : pokemon2.superAttack();
                    damage = pokemon1.receiveDamage(attack);
                    System.out.println(pokemon1 + " get " + damage + " damage. Health = " + pokemon1.getHealth());
                }
                break;
            case DEFENCE:
                if (c2 == Command.ATTACK || c2 == Command.SUPER_ATTACK) {
                    attack = c2 == Command.ATTACK ? pokemon2.attack() : pokemon2.superAttack();
                    damage = pokemon1.defence(attack);
                    System.out.println(pokemon1 + " get " + damage + " damage. Health = " + pokemon1.getHealth());
                }
                break;
            case NOTHING:
                if (c2 == Command.ATTACK || c2 == Command.SUPER_ATTACK) {
                    attack = c2 == Command.ATTACK ? pokemon2.attack() : pokemon2.superAttack();
                    damage = pokemon1.receiveDamage(attack);
                    System.out.println(pokemon1 + " get " + damage + " damage. Health = " + pokemon1.getHealth());
                }
                break;
        }
    }
}
