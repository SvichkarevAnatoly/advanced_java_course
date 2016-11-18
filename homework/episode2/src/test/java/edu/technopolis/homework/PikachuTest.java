package edu.technopolis.homework;

import edu.technopolis.homework.pokemon.Pikachu;
import edu.technopolis.homework.pokemon.Pokemon;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

public class PikachuTest {
    @Test
    public void create() throws Exception {
        final Pokemon pikachu = new Pikachu();
        assertThat(pikachu.getHealth(), is(100));
        assertThat(pikachu.getAttackPower(), is(40));
        assertThat(pikachu.getDefencePower(), is(10));
    }

    @Test
    public void attackLessAttackPower() throws Exception {
        final Pokemon pikachu = new Pikachu(new Random(0));
        assertThat(pikachu.attack(), lessThan(pikachu.getAttackPower()));
    }

    @Test
    public void superAttackLessDoubleAttackPower() throws Exception {
        final Pokemon pikachu = new Pikachu(new Random(0));
        assertThat(pikachu.superAttack(), lessThan(2 * pikachu.getAttackPower()));
    }

    @Test
    public void defenceEffect() throws Exception {
        final Pokemon pikachu = new Pikachu(new Random(0));
        final int healthBeforeDefence = pikachu.getHealth();

        final int attack = 40;
        pikachu.defence(attack);

        assertThat(pikachu.getHealth(), lessThanOrEqualTo(healthBeforeDefence));
    }

    @Test
    public void isAlive() throws Exception {
        final Pokemon pikachu = new Pikachu(new Random(0));
        assertThat(pikachu.isAlive(), is(true));

        final int weakAttack = 40;
        pikachu.defence(weakAttack);
        assertThat(pikachu.isAlive(), is(true));

        final int deadlyAttack = 1000;
        pikachu.defence(deadlyAttack);
        assertThat(pikachu.isAlive(), is(false));
    }
}
