/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginsAddon
 *  com.starshootercity.abilities.Ability
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 */
package com.starshootercity.originsfantasy;

import com.starshootercity.OriginsAddon;
import com.starshootercity.abilities.Ability;
import com.starshootercity.originsfantasy.FantasyNMSInvoker;
import com.starshootercity.originsfantasy.FantasyNMSInvokerV1_21;
import com.starshootercity.originsfantasy.FantasyNMSInvokerV1_21_1;
import com.starshootercity.originsfantasy.abilities.AllayMaster;
import com.starshootercity.originsfantasy.abilities.ArrowEffectBooster;
import com.starshootercity.originsfantasy.abilities.BardicIntuition;
import com.starshootercity.originsfantasy.abilities.BowBurst;
import com.starshootercity.originsfantasy.abilities.BreathStorer;
import com.starshootercity.originsfantasy.abilities.Chime;
import com.starshootercity.originsfantasy.abilities.DaylightSensitive;
import com.starshootercity.originsfantasy.abilities.DoubleHealth;
import com.starshootercity.originsfantasy.abilities.DragonFireball;
import com.starshootercity.originsfantasy.abilities.Elegy;
import com.starshootercity.originsfantasy.abilities.EndBoost;
import com.starshootercity.originsfantasy.abilities.EndCrystalHealing;
import com.starshootercity.originsfantasy.abilities.FortuneIncreaser;
import com.starshootercity.originsfantasy.abilities.HeavyBlow;
import com.starshootercity.originsfantasy.abilities.IncreasedArrowDamage;
import com.starshootercity.originsfantasy.abilities.IncreasedArrowSpeed;
import com.starshootercity.originsfantasy.abilities.IncreasedSpeed;
import com.starshootercity.originsfantasy.abilities.InfiniteHaste;
import com.starshootercity.originsfantasy.abilities.InfiniteNightVision;
import com.starshootercity.originsfantasy.abilities.LargeBody;
import com.starshootercity.originsfantasy.abilities.Leeching;
import com.starshootercity.originsfantasy.abilities.MagicResistance;
import com.starshootercity.originsfantasy.abilities.MoonStrength;
import com.starshootercity.originsfantasy.abilities.NaturalArmor;
import com.starshootercity.originsfantasy.abilities.NoteBlockPower;
import com.starshootercity.originsfantasy.abilities.OceanWish;
import com.starshootercity.originsfantasy.abilities.OceansGrace;
import com.starshootercity.originsfantasy.abilities.PerfectShot;
import com.starshootercity.originsfantasy.abilities.PermanentHorse;
import com.starshootercity.originsfantasy.abilities.PoorShot;
import com.starshootercity.originsfantasy.abilities.SmallBody;
import com.starshootercity.originsfantasy.abilities.StrongSkin;
import com.starshootercity.originsfantasy.abilities.Stronger;
import com.starshootercity.originsfantasy.abilities.SuperJump;
import com.starshootercity.originsfantasy.abilities.UndeadAlly;
import com.starshootercity.originsfantasy.abilities.VampiricTransformation;
import com.starshootercity.originsfantasy.abilities.WaterSensitive;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class OriginsFantasy
extends OriginsAddon {
    private static FantasyNMSInvoker nmsInvoker;

    @NotNull
    public String getNamespace() {
        return "fantasyorigins";
    }

    @NotNull
    public List<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<Ability>(List.of((Object[])new Ability[]{new AllayMaster(), new ArrowEffectBooster(), new BardicIntuition(), new BowBurst(), new BreathStorer(), new Chime(), new DoubleHealth(), new DragonFireball(), new Elegy(), new EndCrystalHealing(), new EndBoost(), new FortuneIncreaser(), new IncreasedArrowDamage(), new IncreasedArrowSpeed(), new HeavyBlow(), HeavyBlow.IncreasedCooldown.increasedCooldown, HeavyBlow.IncreasedDamage.increasedDamage, EndBoost.EndHealth.endHealth, EndBoost.EndStrength.endStrength, new IncreasedSpeed(), new InfiniteHaste(), new InfiniteNightVision(), new OceanWish(), OceanWish.LandWeakness.landWeakness, OceanWish.LandHealth.landHealth, OceanWish.LandSlowness.landSlowness, new MagicResistance(), new MoonStrength(), new NaturalArmor(), new NoteBlockPower(), new PerfectShot(), new PermanentHorse(), new PoorShot(), new StrongSkin(), new SuperJump(), new OceansGrace(), OceansGrace.WaterHealth.waterHealth, OceansGrace.WaterStrength.waterStrength, new VampiricTransformation(), new DaylightSensitive(), new WaterSensitive(), new Leeching(), new Stronger(), new UndeadAlly()}));
        if (nmsInvoker.getGenericScaleAttribute() != null) {
            abilities.add((Ability)new LargeBody());
            abilities.add((Ability)new SmallBody());
        }
        return abilities;
    }

    private static void initializeNMSInvoker() {
        nmsInvoker = switch (Bukkit.getMinecraftVersion()) {
            case "1.21" -> new FantasyNMSInvokerV1_21();
            case "1.21.1" -> new FantasyNMSInvokerV1_21_1();
            default -> throw new IllegalStateException("Unexpected version: " + Bukkit.getMinecraftVersion() + " only versions 1.20 - 1.20.6 are supported");
        };
        Bukkit.getPluginManager().registerEvents((Listener)OriginsFantasy.getNMSInvoker(), (Plugin)OriginsFantasy.getInstance());
    }

    public static FantasyNMSInvoker getNMSInvoker() {
        return nmsInvoker;
    }

    public void onRegister() {
        OriginsFantasy.initializeNMSInvoker();
        this.saveDefaultConfig();
        if (!this.getConfig().contains("arrow-speed-multiplier")) {
            this.getConfig().set("arrow-speed-multiplier", (Object)2);
            this.getConfig().set("arrow-damage-increase", (Object)2);
            this.getConfig().setComments("arrow-speed-multiplier", List.of((Object)"Amount to multiply arrow speed by when using the Increased Arrow Speed ability"));
            this.getConfig().setComments("arrow-damage-increase", List.of((Object)"Amount to increase arrow damage by when using the Increased Arrow Damage ability"));
            this.saveConfig();
        }
    }
}

