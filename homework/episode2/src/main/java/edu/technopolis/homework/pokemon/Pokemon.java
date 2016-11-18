package edu.technopolis.homework.pokemon;

import java.util.Random;

public abstract class Pokemon {
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

    public void defence(int attack){
        final int defence = random.nextInt(defencePower);
        if (attack > defence) {
            health -= attack - defence;
        }
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
}
