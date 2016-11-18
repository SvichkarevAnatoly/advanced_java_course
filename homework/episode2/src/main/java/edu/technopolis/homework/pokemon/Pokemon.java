package edu.technopolis.homework.pokemon;

import java.util.Random;

public abstract class Pokemon {
    protected String name;
    protected int health = 100;
    protected int attackPower;
    protected int defencePower;

    protected Random random;

    public Pokemon() {
    }

    public Pokemon(Random random) {
        this.random = random;
    }

    public int attack(){
        return random.nextInt(attackPower);
    }

    public int superAttack(){
        return 2 * random.nextInt(attackPower);
    }

    public int defence(int attack){
        final int defence = random.nextInt(defencePower);
        final int damage = attack > defence ? attack - defence : 0;
        if (damage > 0) {
            health -= damage;
            health = health < 0 ? 0 : health;
        }
        return damage;
    }

    public int receiveDamage(int damage) {
        health -= damage;
        health = health < 0 ? 0 : health;
        return damage;
    }

    void nothing(){}

    public boolean isAlive(){
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    @Override
    public String toString() {
        return name;
    }
}
