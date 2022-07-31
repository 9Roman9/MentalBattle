package com.game.MentalBattle.controllers;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import com.game.MentalBattle.game.lightSide.equipment.bows.Crossbow;
import com.game.MentalBattle.game.lightSide.equipment.bows.LegendaryBow;
import com.game.MentalBattle.game.lightSide.equipment.bows.WoodenBow;
import com.game.MentalBattle.game.lightSide.equipment.clothes.Cloth;
import com.game.MentalBattle.game.lightSide.equipment.clothes.ProtectiveShield;
import com.game.MentalBattle.game.lightSide.equipment.clothes.RomeMail;
import com.game.MentalBattle.game.lightSide.equipment.clothes.ShogunArmor;
import com.game.MentalBattle.game.lightSide.equipment.hammers.BlacksmithHammer;
import com.game.MentalBattle.game.lightSide.equipment.hammers.Mace;
import com.game.MentalBattle.game.lightSide.equipment.hammers.ThunderHammer;
import com.game.MentalBattle.game.lightSide.equipment.swords.AncientSword;
import com.game.MentalBattle.game.lightSide.equipment.swords.Dagger;
import com.game.MentalBattle.game.lightSide.equipment.swords.Katana;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerMarket {
    AnnotationConfigApplicationContext context;
    @Autowired
    private ControllerFarmer controllerFarmer;
    @Autowired
    private MeteorRain meteorRain;
    @Autowired
    private DragonBreath dragonBreath;
    @Autowired
    private FlamingWhip flamingWhip;
    @Autowired
    private Flood flood;
    @Autowired
    private HealingSpring healingSpring;
    @Autowired
    private IceSpike iceSpike;
    @Autowired
    private Earthquake earthquake;
    @Autowired
    private LeafProtection leafProtection;
    @Autowired
    private Liana liana;
    @Autowired
    private Tornado tornado;
    @Autowired
    private MysticFog mysticFog;
    @Autowired
    private BurningWind burningWind;
    @Autowired
    private WoodenBow woodenBow;
    @Autowired
    private Crossbow crossbow;
    @Autowired
    private LegendaryBow legendaryBow;
    @Autowired
    private AncientSword ancientSword;
    @Autowired
    private Dagger dagger;
    @Autowired
    private Katana katana;
    @Autowired
    private BlacksmithHammer blacksmithHammer;
    @Autowired
    private Mace mace;
    @Autowired
    private ThunderHammer thunderHammer;
    @Autowired
    private RomeMail romeMail;
    @Autowired
    private ShogunArmor shogunArmor;
    @Autowired
    private ProtectiveShield protectiveShield;
    private int money;
    private List<MagicSkill> purchasedMagic = new ArrayList<>();
    private List<Weapon> purchasedWeapon = new ArrayList<>();
    private List<Cloth> purchasedCloth = new ArrayList<>();

    @GetMapping("/market")
    public String market(Model model){
        context = new AnnotationConfigApplicationContext(Config.class);
        money = controllerFarmer.getMoney();
        model.addAttribute("money",money);
        return "/market/market";
    }

    @GetMapping("/merchant1")
    public String merchant1(Model model){
        model.addAttribute("money",money);
        return "/market/merchant1";
    }

    @GetMapping("/merchant2")
    public String merchant2(Model model){
        model.addAttribute("money",money);
        return "/market/merchant2";
    }

    @GetMapping("/merchant3")
    public String merchant3(Model model){
        model.addAttribute("money",money);
        return "/market/merchant3";
    }

    @GetMapping("/products")
    public String products(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        return "/market/products";
    }

    @GetMapping("/magic")
    public String magic(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        return "/market/magic";
    }

    @GetMapping("/weapon")
    public String weapon(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        return "/market/weapon";
    }

    @GetMapping("/finishShopping")
    public String finishShopping(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        return "/market/finishShopping";
    }

    @GetMapping("/fire")
    public String fire(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        int priceMeteorRain = meteorRain.getPrice();
        int damageMeteorRain = meteorRain.getDamage();
        int impactVolumeMeteorRain = meteorRain.getImpactVolume();
        model.addAttribute("priceMeteorRain",priceMeteorRain);
        model.addAttribute("damageMeteorRain",damageMeteorRain);
        model.addAttribute("impactVolumeMeteorRain",impactVolumeMeteorRain);
        model.addAttribute("descriptionMeteorRain","Метеоритний дощ");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Метеоритний дощ")){
                model.addAttribute("descriptionMeteorRain","");
                break;
            }
        }
        int priceDragonBreath = dragonBreath.getPrice();
        int damageDragonBreath = dragonBreath.getDamage();
        int impactVolumeDragonBreath = dragonBreath.getImpactVolume();
        model.addAttribute("priceDragonBreath",priceDragonBreath);
        model.addAttribute("damageDragonBreath",damageDragonBreath);
        model.addAttribute("impactVolumeDragonBreath",impactVolumeDragonBreath);
        model.addAttribute("descriptionDragonBreath","Подих дракона");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Подих дракона")){
                model.addAttribute("descriptionDragonBreath","");
                break;
            }
        }
        int priceFlamingWhip = flamingWhip.getPrice();
        int damageFlamingWhip = flamingWhip.getDamage();
        int impactVolumeFlamingWhip = flamingWhip.getImpactVolume();
        model.addAttribute("priceFlamingWhip",priceFlamingWhip);
        model.addAttribute("damageFlamingWhip",damageFlamingWhip);
        model.addAttribute("impactVolumeFlamingWhip",impactVolumeFlamingWhip);
        model.addAttribute("descriptionFlamingWhip","Палаючий батіг");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Палаючий батіг")){
                model.addAttribute("descriptionFlamingWhip","");
                break;
            }
        }
        model.addAttribute("money",money);
        return "/market/fire";
    }

    @RequestMapping(value = "makePurchase", method = RequestMethod.POST)
    public String makePurchase(Model model, @RequestParam(value = "name") String name){
        String target = "/market/products";
        switch (name) {
            case "meteorRain":
                if (money>=meteorRain.getPrice()) {
                    money -= meteorRain.getPrice();
                    purchasedMagic.add(meteorRain);
                }
                else target = notEnoughMoney(model);
                break;
            case "dragonBreath":
                if (money>=dragonBreath.getPrice()) {
                    money -= dragonBreath.getPrice();
                    purchasedMagic.add(dragonBreath);
                }
                else target = notEnoughMoney(model);
                break;
            case "flamingWhip":
                if (money>=flamingWhip.getPrice()) {
                    money -= flamingWhip.getPrice();
                    purchasedMagic.add(flamingWhip);
                }
                else target = notEnoughMoney(model);
                break;
            case "flood":
                if (money>=flood.getPrice()) {
                    money -= flood.getPrice();
                    purchasedMagic.add(flood);
                }
                else target = notEnoughMoney(model);
                break;
            case "healingSpring":
                if (money>=healingSpring.getPrice()) {
                    money -= healingSpring.getPrice();
                    purchasedMagic.add(healingSpring);
                }
                else target = notEnoughMoney(model);
                break;
            case "iceSpike":
                if (money>=iceSpike.getPrice()) {
                    money -= iceSpike.getPrice();
                    purchasedMagic.add(iceSpike);
                }
                else target = notEnoughMoney(model);
                break;
            case "earthquake":
                if (money>=earthquake.getPrice()) {
                    money -= earthquake.getPrice();
                    purchasedMagic.add(earthquake);
                }
                else target = notEnoughMoney(model);
                break;
            case "leafProtection":
                if (money>=leafProtection.getPrice()) {
                    money -= leafProtection.getPrice();
                    purchasedMagic.add(leafProtection);
                }
                else target = notEnoughMoney(model);
                break;
            case "liana":
                if (money>=liana.getPrice()) {
                    money -= liana.getPrice();
                    purchasedMagic.add(liana);
                }
                else target = notEnoughMoney(model);
                break;
            case "tornado":
                if (money>=tornado.getPrice()) {
                    money -= tornado.getPrice();
                    purchasedMagic.add(tornado);
                }
                else target = notEnoughMoney(model);
                break;
            case "mysticFog":
                if (money>=mysticFog.getPrice()) {
                    money -= mysticFog.getPrice();
                    purchasedMagic.add(mysticFog);
                }
                else target = notEnoughMoney(model);
                break;
            case "burningWind":
                if (money>=burningWind.getPrice()) {
                    money -= burningWind.getPrice();
                    purchasedMagic.add(burningWind);
                }
                else target = notEnoughMoney(model);
                break;
            case "woodenBow":
                if (money>=woodenBow.getPrice()) {
                    money -= woodenBow.getPrice();
                    purchasedWeapon.add(woodenBow);
                }
                else target = notEnoughMoney(model);
                break;
            case "crossbow":
                if (money>=crossbow.getPrice()) {
                    money -= crossbow.getPrice();
                    purchasedWeapon.add(crossbow);
                }
                else target = notEnoughMoney(model);
                break;
            case "legendaryBow":
                if (money>=legendaryBow.getPrice()) {
                    money -= legendaryBow.getPrice();
                    purchasedWeapon.add(legendaryBow);
                }
                else target = notEnoughMoney(model);
                break;
            case "ancientSword":
                if (money>=ancientSword.getPrice()) {
                    money -= ancientSword.getPrice();
                    purchasedWeapon.add(ancientSword);
                }
                else target = notEnoughMoney(model);
                break;
            case "dagger":
                if (money>=dagger.getPrice()) {
                    money -= dagger.getPrice();
                    purchasedWeapon.add(dagger);
                }
                else target = notEnoughMoney(model);
                break;
            case "katana":
                if (money>=katana.getPrice()) {
                    money -= katana.getPrice();
                    purchasedWeapon.add(katana);
                }
                else target = notEnoughMoney(model);
                break;
            case "blacksmithHammer":
                if (money>=blacksmithHammer.getPrice()) {
                    money -= blacksmithHammer.getPrice();
                    purchasedWeapon.add(blacksmithHammer);
                }
                else target = notEnoughMoney(model);
                break;
            case "mace":
                if (money>=mace.getPrice()) {
                    money -= mace.getPrice();
                    purchasedWeapon.add(mace);
                }
                else target = notEnoughMoney(model);
                break;
            case "thunderHammer":
                if (money>=thunderHammer.getPrice()) {
                    money -= thunderHammer.getPrice();
                    purchasedWeapon.add(thunderHammer);
                }
                else target = notEnoughMoney(model);
                break;
            case "romeMail":
                if (money>=romeMail.getPrice()) {
                    money -= romeMail.getPrice();
                    purchasedCloth.add(romeMail);
                }
                else target = notEnoughMoney(model);
                break;
            case "shogunArmor":
                if (money>=shogunArmor.getPrice()) {
                    money -= shogunArmor.getPrice();
                    purchasedCloth.add(shogunArmor);
                }
                else target = notEnoughMoney(model);
                break;
            case "protectiveShield":
                if (money>=protectiveShield.getPrice()) {
                    money -= protectiveShield.getPrice();
                    purchasedCloth.add(protectiveShield);
                }
                else target = notEnoughMoney(model);
                break;
            default:
                return target;
        }
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        return target;
    }

    @GetMapping("/water")
    public String water(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        int priceFlood = flood.getPrice();
        int damageFlood = flood.getDamage();
        int impactVolumeFlood = flood.getImpactVolume();
        model.addAttribute("priceFlood",priceFlood);
        model.addAttribute("damageFlood",damageFlood);
        model.addAttribute("impactVolumeFlood",impactVolumeFlood);
        model.addAttribute("descriptionFlood","Шторм");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Шторм")){
                model.addAttribute("descriptionFlood","");
                break;
            }
        }
        int priceHealingSpring = healingSpring.getPrice();
        int damageHealingSpring = healingSpring.getDamage();
        int impactVolumeHealingSpring = healingSpring.getImpactVolume();
        model.addAttribute("priceHealingSpring",priceHealingSpring);
        model.addAttribute("damageHealingSpring",damageHealingSpring);
        model.addAttribute("impactVolumeHealingSpring",impactVolumeHealingSpring);
        model.addAttribute("descriptionHealingSpring","Цілюще джерело");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Цілюще джерело")){
                model.addAttribute("descriptionHealingSpring","");
                break;
            }
        }
        int priceIceSpike = iceSpike.getPrice();
        int damageIceSpike = iceSpike.getDamage();
        int impactVolumeIceSpike = iceSpike.getImpactVolume();
        model.addAttribute("priceIceSpike",priceIceSpike);
        model.addAttribute("damageIceSpike",damageIceSpike);
        model.addAttribute("impactVolumeIceSpike",impactVolumeIceSpike);
        model.addAttribute("descriptionIceSpike","Льодяний шип");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Льодяний шип")){
                model.addAttribute("descriptionIceSpike","");
                break;
            }
        }
        model.addAttribute("money",money);
        return "/market/water";
    }

    @GetMapping("/earth")
    public String earth(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        int priceEarthquake = earthquake.getPrice();
        int damageEarthquake = earthquake.getDamage();
        int impactVolumeEarthquake = earthquake.getImpactVolume();
        model.addAttribute("priceEarthquake",priceEarthquake);
        model.addAttribute("damageEarthquake",damageEarthquake);
        model.addAttribute("impactVolumeEarthquake",impactVolumeEarthquake);
        model.addAttribute("descriptionEarthquake","Землетрус");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Землетрус")){
                model.addAttribute("descriptionEarthquake","");
                break;
            }
        }
        int priceLeafProtection = leafProtection.getPrice();
        int damageLeafProtection = leafProtection.getDamage();
        int impactVolumeLeafProtection = leafProtection.getImpactVolume();
        model.addAttribute("priceLeafProtection",priceLeafProtection);
        model.addAttribute("damageLeafProtection",damageLeafProtection);
        model.addAttribute("impactVolumeLeafProtection",impactVolumeLeafProtection);
        model.addAttribute("descriptionLeafProtection","Листовий оберіг");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Листовий оберіг")){
                model.addAttribute("descriptionLeafProtection","");
                break;
            }
        }
        int priceLiana = liana.getPrice();
        int damageLiana = liana.getDamage();
        int impactVolumeLiana = liana.getImpactVolume();
        model.addAttribute("priceLiana",priceLiana);
        model.addAttribute("damageLiana",damageLiana);
        model.addAttribute("impactVolumeLiana",impactVolumeLiana);
        model.addAttribute("descriptionLiana","Разюча ліана");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Разюча ліана")){
                model.addAttribute("descriptionLiana","");
                break;
            }
        }
        model.addAttribute("money",money);
        return "/market/earth";
    }

    @GetMapping("/air")
    public String air(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        int priceTornado = tornado.getPrice();
        int damageTornado = tornado.getDamage();
        int impactVolumeTornado = tornado.getImpactVolume();
        model.addAttribute("priceTornado",priceTornado);
        model.addAttribute("damageTornado",damageTornado);
        model.addAttribute("impactVolumeTornado",impactVolumeTornado);
        model.addAttribute("descriptionTornado","Торнадо");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Торнадо")){
                model.addAttribute("descriptionTornado","");
                break;
            }
        }
        int priceMysticFog = mysticFog.getPrice();
        int damageMysticFog = mysticFog.getDamage();
        int impactVolumeMysticFog = mysticFog.getImpactVolume();
        model.addAttribute("priceMysticFog",priceMysticFog);
        model.addAttribute("damageMysticFog",damageMysticFog);
        model.addAttribute("impactVolumeMysticFog",impactVolumeMysticFog);
        model.addAttribute("descriptionMysticFog","Містичний туман");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Містичний туман")){
                model.addAttribute("descriptionMysticFog","");
                break;
            }
        }
        int priceBurningWind = burningWind.getPrice();
        int damageBurningWind = burningWind.getDamage();
        int impactVolumeBurningWind = burningWind.getImpactVolume();
        model.addAttribute("priceBurningWind",priceBurningWind);
        model.addAttribute("damageBurningWind",damageBurningWind);
        model.addAttribute("impactVolumeBurningWind",impactVolumeBurningWind);
        model.addAttribute("descriptionBurningWind","Пекучий вітер");
        for (var v : purchasedMagic) {
            if (v.getDescription().equals("Пекучий вітер")){
                model.addAttribute("descriptionBurningWind","");
                break;
            }
        }
        model.addAttribute("money",money);
        return "/market/air";
    }

    @GetMapping("/bows")
    public String bows(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        for (var v : purchasedWeapon) {
            if (v.getDescription().equals("Дерев'яний лук")||
                    v.getDescription().equals("Арбалет")||
                    v.getDescription().equals("Легендарний лук")||
                    v.getDescription().equals("Стародавній меч")||
                    v.getDescription().equals("Кинжал")||
                    v.getDescription().equals("Катана")||
                    v.getDescription().equals("Ковальський молот")||
                    v.getDescription().equals("Булава")||
                    v.getDescription().equals("Молот Грому")){
                model.addAttribute("descriptionWoodenBow","");
                model.addAttribute("descriptionCrossbow","");
                model.addAttribute("descriptionLegendaryBow","");
                return "/market/bows";
            }
        }
        int priceWoodenBow = woodenBow.getPrice();
        int damageWoodenBow = woodenBow.getDamage();
        model.addAttribute("priceWoodenBow",priceWoodenBow);
        model.addAttribute("damageWoodenBow",damageWoodenBow);
        model.addAttribute("descriptionWoodenBow","Дерев'яний лук");
        int priceCrossbow = crossbow.getPrice();
        int damageCrossbow = crossbow.getDamage();
        model.addAttribute("priceCrossbow",priceCrossbow);
        model.addAttribute("damageCrossbow",damageCrossbow);
        model.addAttribute("descriptionCrossbow","Арбалет");
        int priceLegendaryBow = legendaryBow.getPrice();
        int damageLegendaryBow = legendaryBow.getDamage();
        model.addAttribute("priceLegendaryBow",priceLegendaryBow);
        model.addAttribute("damageLegendaryBow",damageLegendaryBow);
        model.addAttribute("descriptionLegendaryBow","Легендарний лук");
        return "/market/bows";
    }

    @GetMapping("/swords")
    public String swords(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        for (var v : purchasedWeapon) {
            if (v.getDescription().equals("Дерев'яний лук")||
                    v.getDescription().equals("Арбалет")||
                    v.getDescription().equals("Легендарний лук")||
                    v.getDescription().equals("Стародавній меч")||
                    v.getDescription().equals("Кинжал")||
                    v.getDescription().equals("Катана")||
                    v.getDescription().equals("Ковальський молот")||
                    v.getDescription().equals("Булава")||
                    v.getDescription().equals("Молот Грому")){
                model.addAttribute("descriptionAncientSword","");
                model.addAttribute("descriptionDagger","");
                model.addAttribute("descriptionKatana","");
                return "/market/swords";
            }
        }
        int priceAncientSword = ancientSword.getPrice();
        int damageAncientSword = ancientSword.getDamage();
        model.addAttribute("priceAncientSword",priceAncientSword);
        model.addAttribute("damageAncientSword",damageAncientSword);
        model.addAttribute("descriptionAncientSword","Стародавній меч");
        int priceDagger = dagger.getPrice();
        int damageDagger = dagger.getDamage();
        model.addAttribute("priceDagger",priceDagger);
        model.addAttribute("damageDagger",damageDagger);
        model.addAttribute("descriptionDagger","Кинжал");
        int priceKatana = katana.getPrice();
        int damageKatana = katana.getDamage();
        model.addAttribute("priceKatana",priceKatana);
        model.addAttribute("damageKatana",damageKatana);
        model.addAttribute("descriptionKatana","Катана");
        return "/market/swords";
    }

    @GetMapping("/hammers")
    public String hammers(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        for (var v : purchasedWeapon) {
            if (v.getDescription().equals("Дерев'яний лук")||
                    v.getDescription().equals("Арбалет")||
                    v.getDescription().equals("Легендарний лук")||
                    v.getDescription().equals("Стародавній меч")||
                    v.getDescription().equals("Кинжал")||
                    v.getDescription().equals("Катана")||
                    v.getDescription().equals("Ковальський молот")||
                    v.getDescription().equals("Булава")||
                    v.getDescription().equals("Молот Грому")){
                model.addAttribute("descriptionBlacksmithHammer","");
                model.addAttribute("descriptionMace","");
                model.addAttribute("descriptionThunderHammer","");
                return "/market/hammers";
            }
        }
        int priceBlacksmithHammer = blacksmithHammer.getPrice();
        int damageBlacksmithHammer = blacksmithHammer.getDamage();
        model.addAttribute("priceBlacksmithHammer",priceBlacksmithHammer);
        model.addAttribute("damageBlacksmithHammer",damageBlacksmithHammer);
        model.addAttribute("descriptionBlacksmithHammer","Ковальський молот");
        int priceMace = mace.getPrice();
        int damageMace = mace.getDamage();
        model.addAttribute("priceMace",priceMace);
        model.addAttribute("damageMace",damageMace);
        model.addAttribute("descriptionMace","Булава");
        int priceThunderHammer = thunderHammer.getPrice();
        int damageThunderHammer = thunderHammer.getDamage();
        model.addAttribute("priceThunderHammer",priceThunderHammer);
        model.addAttribute("damageThunderHammer",damageThunderHammer);
        model.addAttribute("descriptionThunderHammer","Молот Грому");
        return "/market/hammers";
    }

    @GetMapping("/equipment")
    public String equipment(Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        for (var v : purchasedCloth) {
            if (v.getDescription().equals("Римські обладунки")||v.getDescription().equals("Обладунки Сьогуна")||v.getDescription().equals("Силове поле")){
                model.addAttribute("descriptionRomeMail","");
                model.addAttribute("descriptionShogunArmor","");
                model.addAttribute("descriptionProtectiveShield","");
                return "/market/equipment";
            }
        }
        int priceRomeMail = romeMail.getPrice();
        int protectionValueRomeMail = romeMail.getProtectionValue();
        model.addAttribute("priceRomeMail",priceRomeMail);
        model.addAttribute("protectionValueRomeMail",protectionValueRomeMail);
        model.addAttribute("descriptionRomeMail","Римські обладунки");
        int priceShogunArmor = shogunArmor.getPrice();
        int protectionValueShogunArmor = shogunArmor.getProtectionValue();
        model.addAttribute("priceShogunArmor",priceShogunArmor);
        model.addAttribute("protectionValueShogunArmor",protectionValueShogunArmor);
        model.addAttribute("descriptionShogunArmor","Обладунки Сьогуна");
        int priceProtectiveShield = protectiveShield.getPrice();
        int protectionValueProtectiveShield = protectiveShield.getProtectionValue();
        model.addAttribute("priceProtectiveShield",priceProtectiveShield);
        model.addAttribute("protectionValueProtectiveShield",protectionValueProtectiveShield);
        model.addAttribute("descriptionProtectiveShield","Силове поле");
        return "/market/equipment";
    }

    public String notEnoughMoney (Model model){
        for (int i = 0; i < purchasedMagic.size(); i++){
            model.addAttribute("magic".concat(String.valueOf(i+1)),purchasedMagic.get(i).getDescription());
        }
        model.addAttribute("money",money);
        return "/market/notEnoughMoney";
    }
}
