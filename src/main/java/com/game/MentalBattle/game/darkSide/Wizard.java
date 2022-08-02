package com.game.MentalBattle.game.darkSide;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.darkSide.skills.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("wizardBean")
public class Wizard {
    AnnotationConfigApplicationContext context;
    @Value("${wizardLife}")
    int life;
    boolean isProtectSkill;
    DarkSkill nextDarkSkill;
    @Autowired
    BlackFire blackFire;
    @Autowired
    BloodSucker bloodSucker;
    @Autowired
    Falling falling;
    @Autowired
    LightningCarousel lightningCarousel;
    @Autowired
    Shot shot;
    public void setContext(){
        context = new AnnotationConfigApplicationContext(Config.class);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean checkProtectSkill(){
        isProtectSkill = false;
        int number = new Random().nextInt(5);
        if (number == 0) isProtectSkill = true;
        return isProtectSkill;
    }

    public DarkSkill findNextDarkSkill(){
        int number = new Random().nextInt(90);
        if (number>=85) nextDarkSkill = bloodSucker;
        else if (number>=80) nextDarkSkill = lightningCarousel;
        else if (number>=72) nextDarkSkill = blackFire;
        else if (number>=60) nextDarkSkill = falling;
        else nextDarkSkill = shot;
        return nextDarkSkill;
    }
}
