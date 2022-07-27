package com.game.MentalBattle.game.equipment;

import com.game.MentalBattle.game.Impact;

public interface Weapon {
    int getPrice();
    int getDamage();
    Impact getImpact();
    int getImpactVolume();
}
