package com.game.MentalBattle.controllers;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.MentalBattleApplication;
import com.game.MentalBattle.game.darkSide.Wizard;
import com.game.MentalBattle.game.darkSide.skills.*;
import com.game.MentalBattle.game.lightSide.Hero;
import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import com.game.MentalBattle.game.lightSide.equipment.clothes.Cloth;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import com.game.MentalBattle.game.lightSide.magic.air.BurningWind;
import com.game.MentalBattle.game.lightSide.magic.air.MysticFog;
import com.game.MentalBattle.game.lightSide.magic.air.Tornado;
import com.game.MentalBattle.game.lightSide.magic.earth.Earthquake;
import com.game.MentalBattle.game.lightSide.magic.earth.LeafProtection;
import com.game.MentalBattle.game.lightSide.magic.earth.Liana;
import com.game.MentalBattle.game.lightSide.magic.fire.DragonBreath;
import com.game.MentalBattle.game.lightSide.magic.fire.FlamingWhip;
import com.game.MentalBattle.game.lightSide.magic.fire.MeteorRain;
import com.game.MentalBattle.game.lightSide.magic.water.Flood;
import com.game.MentalBattle.game.lightSide.magic.water.HealingSpring;
import com.game.MentalBattle.game.lightSide.magic.water.IceSpike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ControllerBattle {
    AnnotationConfigApplicationContext context;
    @Autowired
    private ControllerMarket controllerMarket;
    private Cloth cloth;
    private Weapon weapon;
    private List<MagicSkill> magic = new ArrayList<>();
    @Autowired
    private Wizard wizard;
    @Autowired
    private MeteorRain meteorRain;
    @Autowired
    private DragonBreath dragonBreath;
    @Autowired
    FlamingWhip flamingWhip;
    @Autowired
    Flood flood;
    @Autowired
    HealingSpring healingSpring;
    @Autowired
    IceSpike iceSpike;
    @Autowired
    Earthquake earthquake;
    @Autowired
    LeafProtection leafProtection;
    @Autowired
    Liana liana;
    @Autowired
    Tornado tornado;
    @Autowired
    MysticFog mysticFog;
    @Autowired
    BurningWind burningWind;
    @Autowired
    private Shot shot;
    @Autowired
    private LightningCarousel lightning;
    @Autowired
    private Falling falling;
    @Autowired
    private BlackFire blackFire;
    @Autowired
    private BloodSucker bloodSucker;
    @Autowired
    private Hero hero;
    private int heroLife;
    private int wizardLife;
    private String previousSkill;
    private Map<MagicSkill,Integer> currentEffects = new HashMap<>();
    private int crucialEffect;
    private int stunEffect;
    private int defenceEffect;
    private int increaseAttackEffect;
    private int decreaseAttackEffect;
    private boolean skip = false;
    private MentalBattleApplication mentalBattleApplication;

    @GetMapping("/nearMountain")
    public String nearMountain(Model model){
        context = new AnnotationConfigApplicationContext(Config.class);
        wizard.setContext();
        if (!controllerMarket.getPurchasedCloth().isEmpty())
        cloth = controllerMarket.getPurchasedCloth().get(0);
        if (!controllerMarket.getPurchasedWeapon().isEmpty())
        weapon = controllerMarket.getPurchasedWeapon().get(0);
        for (var v : controllerMarket.getPurchasedMagic()){
            magic.add(v);
        }
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
        }
        return "battle/nearMountain";
    }

    @GetMapping("/topMountain1")
    public String topMountain1(Model model){
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
        }
        return "battle/topMountain1";
    }

    @GetMapping("/topMountain2")
    public String topMountain2(Model model){
        wizardLife = wizard.getLife();
        heroLife = hero.getLife();
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
        }
        return "battle/topMountain2";
    }

    @GetMapping("/stepLight")
    public String stepLight(Model model){
        crucialEffect = 0;
        stunEffect = 0;
        defenceEffect = 0;
        increaseAttackEffect = 0;
        if (heroLife <= 0) return "battle/lose";
        updateEffects();
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        if (weapon!=null) {
            model.addAttribute("weapon",weapon.getDescription());
            if (weapon.getDescriptionEng().equals("crossbow")||
                    weapon.getDescriptionEng().equals("legendaryBow")||
                    weapon.getDescriptionEng().equals("woodenBow")){
                crucialEffect+=10;
                model.addAttribute("weaponRef","bowsAtBattle");}
            else if (weapon.getDescriptionEng().equals("ancientSword")||
                    weapon.getDescriptionEng().equals("dagger")||
                    weapon.getDescriptionEng().equals("katana"))
                model.addAttribute("weaponRef","swordsAtBattle");
            else if (weapon.getDescriptionEng().equals("blacksmithHammer")||
                    weapon.getDescriptionEng().equals("mace")||
                    weapon.getDescriptionEng().equals("thunderHammer")){
                stunEffect+=10;
                model.addAttribute("weaponRef","hammersAtBattle");}
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue()+hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        for (int i = 0; i < magic.size(); i++){
            if (magic.get(i).getRecharge()!=0) magic.get(i).setRecharge(magic.get(i).getRecharge()-1);
            if (magic.get(i).getRecharge()==0){
                model.addAttribute("magicName".concat(String.valueOf(i+1)),magic.get(i).getDescription());
                model.addAttribute("magicRef".concat(String.valueOf(i+1)),magic.get(i).getDescriptionEng());
                model.addAttribute("magicHint".concat(String.valueOf(i+1)),findHint(magic.get(i).getDescriptionEng()));
            }
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()){
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact()==Impact.CRUCIAL) crucialEffect+=magicSkill.getImpactVolume();
            else if (magicSkill.getImpact()==Impact.STUN) stunEffect+=magicSkill.getImpactVolume();
            else if (magicSkill.getImpact()==Impact.DEFENCE_PLUS) defenceEffect+=magicSkill.getImpactVolume();
            else if (magicSkill.getImpact()==Impact.ATTACK_PLUS) increaseAttackEffect+=magicSkill.getImpactVolume();
        }
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = true;
        return "battle/stepLight";
    }

    @GetMapping("/bowsAtBattle")
    public String bowsAtBattle(Model model){
        previousSkill = "";
        boolean usedDarkProtectSkill = wizard.checkProtectSkill();
        if (usedDarkProtectSkill) {
            model.addAttribute("invisibility","?????????? ???????????? ?????????? ?????????? ???????? ???????????? ???????????????????? ????????????????, ?????????? ???????? ???? ???????? ?????????????? ???????????? ?? ?? ?????????? ??????????????????????, ???? ???? ???????? ??????????, ???? ?????????? ?????? ????????????, ???????????????? ???????? ?????????? ????????. ?????????? ??????????, ???????? ?????????? ?????? ?????????????? ???????? ?????????????? ??????????????, ?????????????????? ?? ??????????????. ?????????? ?????????? ???? ???????? ?????? ?????????? ???????????? ?? ???? ???????? ?? ?????????? ?????????? ??'?????????????????? ???????????? ?? ?????????????? ?????????????? ??????????????????.");
            model.addAttribute("damage",0);
        } else {
            int damage = weapon.getRealDamage();
            damage = findFinalDamageWeapon("bows",damage);
            wizardLife = wizardLife - damage;
            model.addAttribute("invisibility","");
            model.addAttribute("damage",damage);
        }
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue()+hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/bowsAtBattle";
    }

    @GetMapping("/swordsAtBattle")
    public String swordsAtBattle(Model model){
        previousSkill = "";
        boolean usedDarkProtectSkill = wizard.checkProtectSkill();
        if (usedDarkProtectSkill) {
            model.addAttribute("invisibility","?????????? ???????????? ?????????? ?????????? ???????? ???????????? ???????????????????? ????????????????, ?????????? ???????? ???? ???????? ?????????????? ???????????? ?? ?? ?????????? ??????????????????????, ???? ???? ???????? ??????????, ???? ?????????? ?????? ????????????, ???????????????? ???????? ?????????? ????????. ?????????? ??????????, ???????? ?????????? ?????? ?????????????? ???????? ?????????????? ??????????????, ?????????????????? ?? ??????????????. ?????????? ?????????? ???? ???????? ?????? ?????????? ???????????? ?? ???? ???????? ?? ?????????? ?????????? ??'?????????????????? ???????????? ?? ?????????????? ?????????????? ??????????????????.");
            model.addAttribute("damage",0);
        } else {
            int damage = weapon.getRealDamage();
            damage = findFinalDamageWeapon("swords",damage);
            wizardLife = wizardLife - damage;
            model.addAttribute("invisibility","");
            model.addAttribute("damage",damage);
        }
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue()+hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/swordsAtBattle";
    }

    @GetMapping("/hammersAtBattle")
    public String hammersAtBattle(Model model){
        previousSkill = "";
        boolean usedDarkProtectSkill = wizard.checkProtectSkill();
        if (usedDarkProtectSkill) {
            model.addAttribute("invisibility","?????????? ???????????? ?????????? ?????????? ???????? ???????????? ???????????????????? ????????????????, ?????????? ???????? ???? ???????? ?????????????? ???????????? ?? ?? ?????????? ??????????????????????, ???? ???? ???????? ??????????, ???? ?????????? ?????? ????????????, ???????????????? ???????? ?????????? ????????. ?????????? ??????????, ???????? ?????????? ?????? ?????????????? ???????? ?????????????? ??????????????, ?????????????????? ?? ??????????????. ?????????? ?????????? ???? ???????? ?????? ?????????? ???????????? ?? ???? ???????? ?? ?????????? ?????????? ??'?????????????????? ???????????? ?? ?????????????? ?????????????? ??????????????????.");
            model.addAttribute("damage",0);
        } else {
            int damage = weapon.getRealDamage();
            damage = findFinalDamageWeapon("hammers",damage);
            wizardLife = wizardLife - damage;
            model.addAttribute("invisibility","");
            model.addAttribute("damage",damage);
        }
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        if (checkStun())
            model.addAttribute("nextDarkSkill","stun");
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/hammersAtBattle";
    }

    @GetMapping("/meteorRain")
    public String meteorRain(Model model){
        int damage = meteorRain.calculateRealDamage();
        int impactValueVolume = meteorRain.getImpactVolume();
        wizardLife = wizardLife - damage - impactValueVolume;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
                model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
                model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
                model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "meteorRain";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/meteorRain";
    }

    @GetMapping("/dragonBreath")
    public String dragonBreath(Model model){
        int damage = dragonBreath.calculateRealDamage();
        int impactValueVolume = dragonBreath.getImpactVolume();
        wizardLife = wizardLife - damage - impactValueVolume;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "dragonBreath";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/dragonBreath";
    }

    @GetMapping("/flamingWhip")
    public String flamingWhip(Model model){
        int damage = flamingWhip.calculateRealDamage();
        int impactValueVolume = flamingWhip.getImpactVolume();
        wizardLife = wizardLife - damage;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        currentEffects.put(flamingWhip,3);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "flamingWhip";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/flamingWhip";
    }

    @GetMapping("/flood")
    public String flood(Model model){
        int damage = flood.calculateRealDamage();
        int impactValueVolume = flood.getImpactVolume();
        wizardLife = wizardLife - damage - impactValueVolume;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "flood";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/flood";
    }

    @GetMapping("/healingSpring")
    public String healingSpring(Model model){
        int damage = healingSpring.calculateRealDamage();
        int impactValueVolume = healingSpring.getImpactVolume();
        wizardLife -= damage;
        heroLife += impactValueVolume;
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "healingSpring";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/healingSpring";
    }

    @GetMapping("/iceSpike")
    public String iceSpike(Model model){
        int damage = iceSpike.calculateRealDamage();
        int impactValueVolume = iceSpike.getImpactVolume();
        wizardLife = wizardLife - damage;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        currentEffects.put(iceSpike,3);
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "iceSpike";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/iceSpike";
    }

    @GetMapping("/earthquake")
    public String earthquake(Model model){
        int damage = earthquake.calculateRealDamage();
        wizardLife = wizardLife - damage;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",earthquake.getImpactVolume());
        stunEffect+=earthquake.getImpactVolume();
        currentEffects.put(earthquake,3);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "earthquake";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/earthquake";
    }

    @GetMapping("/leafProtection")
    public String leafProtection(Model model){
        int damage = leafProtection.calculateRealDamage();
        wizardLife = wizardLife - damage;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        int impactValueVolume = leafProtection.getImpactVolume();
        model.addAttribute("impactValueVolume",impactValueVolume);
        currentEffects.put(leafProtection,2);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "leafProtection";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/leafProtection";
    }

    @GetMapping("/liana")
    public String liana(Model model){
        int damage = liana.calculateRealDamage();
        int impactValueVolume = liana.getImpactVolume();
        wizardLife = wizardLife - damage;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        currentEffects.put(liana,3);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "liana";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/liana";
    }

    @GetMapping("/tornado")
    public String tornado(Model model){
        int damage = tornado.calculateRealDamage();
        int impactValueVolume = tornado.getImpactVolume();
        wizardLife = wizardLife - damage - impactValueVolume;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "tornado";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/tornado";
    }

    @GetMapping("/mysticFog")
    public String mysticFog(Model model){
        int damage = mysticFog.calculateRealDamage();
        int impactValueVolume = mysticFog.getImpactVolume();
        wizardLife -= damage;
        heroLife += impactValueVolume;
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "mysticFog";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/mysticFog";
    }

    @GetMapping("/burningWind")
    public String burningWind(Model model){
        int damage = burningWind.calculateRealDamage();
        int impactValueVolume = burningWind.getImpactVolume();
        wizardLife = wizardLife - damage;
        model.addAttribute("invisibility","");
        model.addAttribute("damage",damage);
        model.addAttribute("impactValueVolume",impactValueVolume);
        currentEffects.put(burningWind,3);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        DarkSkill nextDarkSkill = wizard.findNextDarkSkill();
        model.addAttribute("nextDarkSkill",nextDarkSkill.getName());
        previousSkill = "burningWind";
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        skip = false;
        return "battle/burningWind";
    }

    @GetMapping("/shot")
    public String shot(Model model){
        decreaseAttackEffect = 0;
        defenceEffect = 0;
        if (wizardLife <= 0) return "battle/win";
        for (var v : magic) {
            if (v.getDescriptionEng().equals(previousSkill)&&!skip) v.setRecharge(10);
        }
        int damage = shot.calculateRealDamage();
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()) {
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.ATTACK_MINUS)){
                decreaseAttackEffect += magicSkill.getImpactVolume();
                damage -= damage*magicSkill.getImpactVolume()/100;
            } else if (magicSkill.getImpact().equals(Impact.DEFENCE_PLUS)){
                defenceEffect += magicSkill.getImpactVolume();
                if (!controllerMarket.getPurchasedCloth().isEmpty())
                    damage -= (cloth.getProtectionValue()+hero.getDefaultDefence())*defenceEffect/100;
                else damage -= hero.getDefaultDefence()*defenceEffect/100;
            }
        }
        if (!controllerMarket.getPurchasedCloth().isEmpty())
            damage = damage - cloth.getProtectionValue() - hero.getDefaultDefence();
        else damage -= hero.getDefaultDefence();
        if (damage<0) damage = 0;
        heroLife -= damage;
        model.addAttribute("damage",damage);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++) {
                model.addAttribute("magic".concat(String.valueOf(i + 1)), magic.get(i).getDescription());
                model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Dots"), ":");
                model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Time"), magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        return "battle/shot";
    }

    @GetMapping("/lightning")
    public String lightning(Model model){
        decreaseAttackEffect = 0;
        defenceEffect = 0;
        if (wizardLife <= 0) return "battle/win";
        for (var v : magic) {
            if (v.getDescriptionEng().equals(previousSkill)&&!skip) v.setRecharge(10);
        }
        int damage = lightning.calculateRealDamage();
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()) {
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.ATTACK_MINUS)){
                decreaseAttackEffect += magicSkill.getImpactVolume();
                damage -= damage*magicSkill.getImpactVolume()/100;
            }
            else if (magicSkill.getImpact().equals(Impact.DEFENCE_PLUS)){
                defenceEffect += magicSkill.getImpactVolume();
                if (!controllerMarket.getPurchasedCloth().isEmpty())
                    damage -= (cloth.getProtectionValue()+hero.getDefaultDefence())*defenceEffect/100;
                else damage -= hero.getDefaultDefence()*defenceEffect/100;
            }
        }
        if (!controllerMarket.getPurchasedCloth().isEmpty())
            damage = damage - cloth.getProtectionValue() - hero.getDefaultDefence();
        else damage -= hero.getDefaultDefence();
        heroLife -= damage;
        model.addAttribute("damage",damage);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++) {
            model.addAttribute("magic".concat(String.valueOf(i + 1)), magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Dots"), ":");
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Time"), magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        return "battle/lightning";
    }

    @GetMapping("/falling")
    public String falling(Model model){
        decreaseAttackEffect = 0;
        defenceEffect = 0;
        if (wizardLife <= 0) return "battle/win";
        for (var v : magic) {
            if (v.getDescriptionEng().equals(previousSkill)&&!skip) v.setRecharge(10);
        }
        int damage = falling.calculateRealDamage();
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()) {
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.ATTACK_MINUS)){
                decreaseAttackEffect += magicSkill.getImpactVolume();
                damage -= damage*magicSkill.getImpactVolume()/100;
            }
            else if (magicSkill.getImpact().equals(Impact.DEFENCE_PLUS)){
                defenceEffect += magicSkill.getImpactVolume();
                if (!controllerMarket.getPurchasedCloth().isEmpty())
                    damage -= (cloth.getProtectionValue()+hero.getDefaultDefence())*defenceEffect/100;
                else damage -= hero.getDefaultDefence()*defenceEffect/100;
            }
        }
        if (!controllerMarket.getPurchasedCloth().isEmpty())
            damage = damage - cloth.getProtectionValue() - hero.getDefaultDefence();
        else damage -= hero.getDefaultDefence();
        heroLife -= damage;
        model.addAttribute("damage",damage);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++) {
            model.addAttribute("magic".concat(String.valueOf(i + 1)), magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Dots"), ":");
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Time"), magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        return "battle/falling";
    }

    @GetMapping("/blackFire")
    public String blackFire(Model model){
        decreaseAttackEffect = 0;
        defenceEffect = 0;
        if (wizardLife <= 0) return "battle/win";
        for (var v : magic) {
            if (v.getDescriptionEng().equals(previousSkill)&&!skip) v.setRecharge(10);
        }
        int damage = blackFire.calculateRealDamage();
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()) {
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.ATTACK_MINUS)){
                decreaseAttackEffect += magicSkill.getImpactVolume();
                damage -= damage*magicSkill.getImpactVolume()/100;
            }
            else if (magicSkill.getImpact().equals(Impact.DEFENCE_PLUS)){
                defenceEffect += magicSkill.getImpactVolume();
                if (!controllerMarket.getPurchasedCloth().isEmpty())
                    damage -= (cloth.getProtectionValue()+hero.getDefaultDefence())*defenceEffect/100;
                else damage -= hero.getDefaultDefence()*defenceEffect/100;
            }
        }
        if (!controllerMarket.getPurchasedCloth().isEmpty())
            damage = damage - cloth.getProtectionValue() - hero.getDefaultDefence();
        else damage -= hero.getDefaultDefence();
        heroLife -= damage;
        model.addAttribute("damage",damage);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++) {
            model.addAttribute("magic".concat(String.valueOf(i + 1)), magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Dots"), ":");
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Time"), magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        return "battle/blackFire";
    }

    @GetMapping("/bloodSucker")
    public String bloodSucker(Model model){
        decreaseAttackEffect = 0;
        if (wizardLife <= 0) return "battle/win";
        for (var v : magic) {
            if (v.getDescriptionEng().equals(previousSkill)&&!skip) v.setRecharge(10);
        }
        int damage = bloodSucker.calculateRealDamage();
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()) {
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.ATTACK_MINUS))
                decreaseAttackEffect += magicSkill.getImpactVolume();
        }
        heroLife -= damage;
        wizardLife += damage;
        model.addAttribute("damage",damage);
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++) {
            model.addAttribute("magic".concat(String.valueOf(i + 1)), magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Dots"), ":");
            model.addAttribute("magic".concat(String.valueOf(i + 1)).concat("Time"), magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        return "battle/bloodSucker";
    }

    public int findFinalDamageWeapon(String weapon, int damage){
        int crucial = 0;
        int percent = 0;
        int addDamage = 0;
        if (weapon.equals("bows")) crucial+=10;
        if (weapon.equals("swords")) addDamage+=20;
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()){
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.CRUCIAL))
                crucial+=magicSkill.getImpactVolume();
            else if (magicSkill.getImpact().equals(Impact.ATTACK_PLUS))
                percent += magicSkill.getImpactVolume();
        }
        int number = new Random().nextInt(100);
        if (number>crucial) crucial = 1;
        else crucial = 2;
        return ((damage+addDamage) + (damage+addDamage)*percent/100)*crucial;
    }

    public boolean checkStun(){
        int stun = 0;
        if (weapon.getDescriptionEng().equals("blacksmithHammer")||
        weapon.getDescriptionEng().equals("mace")||
        weapon.getDescriptionEng().equals("thunderHammer"))
            stun+=10;
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()){
            MagicSkill magicSkill = magicSkillIterator.next();
            if (magicSkill.getImpact().equals(Impact.STUN))
                stun+=magicSkill.getImpactVolume();
        }
        int number = new Random().nextInt(100);
        if (number<stun) return true;
        else return false;
    }

    @GetMapping("/stun")
    public String stun(Model model){
        model.addAttribute("heroLife",heroLife);
        model.addAttribute("wizardLife",wizardLife);
        for (int i = 0; i < magic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),magic.get(i).getDescription());
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Dots"),":");
            model.addAttribute("magic".concat(String.valueOf(i+1)).concat("Time"),magic.get(i).getRecharge());
        }
        if (cloth!=null) model.addAttribute("equipmentProtection",cloth.getProtectionValue() + hero.getDefaultDefence());
        else model.addAttribute("equipmentProtection",hero.getDefaultDefence());
        model.addAttribute("crucialEffect",crucialEffect);
        model.addAttribute("stunEffect",stunEffect);
        model.addAttribute("defenceEffect",defenceEffect);
        model.addAttribute("increaseAttackEffect",increaseAttackEffect);
        model.addAttribute("decreaseAttackEffect",decreaseAttackEffect);
        return "battle/stun";
    }

    public void updateEffects(){
        List<MagicSkill> skillsToRemove = new ArrayList<>();
        Iterator<MagicSkill> magicSkillIterator = currentEffects.keySet().iterator();
        while (magicSkillIterator.hasNext()){
            MagicSkill magicSkill = magicSkillIterator.next();
            if (currentEffects.get(magicSkill)==0)
                skillsToRemove.add(magicSkill);
            else currentEffects.put(magicSkill,currentEffects.get(magicSkill)-1);
        }
        for (var s : skillsToRemove) currentEffects.remove(s);
    }

    public String findHint(String magic){
        String hint = "";
        if (magic.equals("meteorRain")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(meteorRain.getDamage())).concat(" ????. ?????????????????? ???????????? ???????????????? ?? ?????????????? ").concat(String.valueOf(meteorRain.getImpactVolume())).concat(" ????.");
        else if (magic.equals("dragonBreath")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(dragonBreath.getDamage())).concat(" ????. ?????????????????? ???????????? ???????????????? ?? ?????????????? ").concat(String.valueOf(dragonBreath.getImpactVolume())).concat(" ????.");
        else if (magic.equals("flamingWhip")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(flamingWhip.getDamage())).concat(" ????. ???????????????? ???????? ?????????? ???? ").concat(String.valueOf(flamingWhip.getImpactVolume())).concat("%.");
        else if (magic.equals("flood")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(flood.getDamage())).concat(" ????. ?????????????????? ???????????? ???????????????? ?? ?????????????? ").concat(String.valueOf(flood.getImpactVolume())).concat(" ????.");
        else if (magic.equals("healingSpring")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(flood.getDamage())).concat(" ????. ?????????????????? ????????????'?? ???? ").concat(String.valueOf(healingSpring.getImpactVolume())).concat(" ????.");
        else if (magic.equals("iceSpike")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(iceSpike.getDamage())).concat(" ????. ???????????????? ???????? ?????????? ???? ").concat(String.valueOf(iceSpike.getImpactVolume())).concat("%.");
        else if (magic.equals("earthQuake")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(earthquake.getDamage())).concat(" ????. ???????????????? ?????????????????????? ?????????????????????????? ???? ").concat(String.valueOf(earthquake.getImpactVolume())).concat("%.");
        else if (magic.equals("leafProtection")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(leafProtection.getDamage())).concat(" ????. ?????????????? ???????????? ???? ").concat(String.valueOf(leafProtection.getImpactVolume())).concat("%.");
        else if (magic.equals("liana")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(liana.getDamage())).concat(" ????. ???????????????? ?????????????????????? ???????????????????? ?????????? ???? ").concat(String.valueOf(liana.getImpactVolume())).concat("%.");
        else if (magic.equals("tornado")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(tornado.getDamage())).concat(" ????. ?????????????????? ???????????? ???????????????? ?? ?????????????? ").concat(String.valueOf(tornado.getImpactVolume())).concat(" ????.");
        else if (magic.equals("mysticFog")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(mysticFog.getDamage())).concat(" ????. ?????????????????? ????????????'?? ???? ").concat(String.valueOf(mysticFog.getImpactVolume())).concat(" ????.");
        else if (magic.equals("burningWind")) hint = "???????????? ???????????????? ???? ".concat(String.valueOf(burningWind.getDamage())).concat(" ????. ?????????????? ?????????? ???????????? ???? ").concat(String.valueOf(burningWind.getImpactVolume())).concat("%.");
        return hint;
    }

    @GetMapping("finishGame")
    public void finishGame(){
        MentalBattleApplication.closeApp();
    }
}