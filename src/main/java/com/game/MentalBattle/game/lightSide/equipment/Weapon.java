package com.game.MentalBattle.game.lightSide.equipment;

import com.game.MentalBattle.game.lightSide.Impact;

public interface Weapon {
    int getPrice();
    int getDamage();
    Impact getImpact();
    String getDescription();
    String getDescriptionEng();
    int getRealDamage();
}
