package com.game.MentalBattle.game.lightSide.magic.water;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.lightSide.magic.Element;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import com.game.MentalBattle.game.lightSide.magic.fire.DragonBreath;
import com.game.MentalBattle.game.lightSide.magic.fire.FlamingWhip;
import com.game.MentalBattle.game.lightSide.magic.fire.MeteorRain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("waterBean")
public class Water implements Element {
    AnnotationConfigApplicationContext context;
    private MagicSkill skill1;
    private MagicSkill skill2;
    private MagicSkill skill3;

    public void setWaterMagic(){
        context = new AnnotationConfigApplicationContext(Config.class);
        skill1 = context.getBean("floodBean", Flood.class);
        skill2 = context.getBean("healingSpringBean", HealingSpring.class);
        skill3 = context.getBean("iceSpikeBean", IceSpike.class);
    }

    @Override
    public MagicSkill getSkill1() {
        return skill1;
    }

    @Override
    public MagicSkill getSkill2() {
        return skill2;
    }

    @Override
    public MagicSkill getSkill3() {
        return skill3;
    }
}
