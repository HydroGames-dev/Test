package com.starshootercity.originsfantasy;

import com.starshootercity.OriginsAddon;
import com.starshootercity.abilities.Ability;
import com.starshootercity.originsfantasy.abilities.*;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OriginsFantasy extends OriginsAddon {

    @Override
    public @NotNull String getNamespace() {
        return "fantasyorigins";
    }

    @Override
    public @NotNull List<Ability> getAbilities() {
        List<Ability> abilities = new ArrayList<>(List.of(
                new AllayMaster(),
                new ArrowEffectBooster(),
                new BardicIntuition(),
                new BowBurst(),
                new BreathStorer(),
                new Chime(),
                new DoubleHealth(),
                new DragonFireball(),
                new Elegy(),
                new EndCrystalHealing(),
                new EndBoost(),
                new FortuneIncreaser(),
                new IncreasedArrowDamage(),
                new IncreasedArrowSpeed(),
                new HeavyBlow(),
                HeavyBlow.IncreasedCooldown.increasedCooldown,
                HeavyBlow.IncreasedDamage.increasedDamage,
                EndBoost.EndHealth.endHealth,
                EndBoost.EndStrength.endStrength,
                new IncreasedSpeed(),
                new InfiniteHaste(),
                new InfiniteNightVision(),
                new OceanWish(),
                OceanWish.LandWeakness.landWeakness,
                OceanWish.LandHealth.landHealth,
                OceanWish.LandSlowness.landSlowness,
                new MagicResistance(),
                new MoonStrength(),
                new NaturalArmor(),
                new NoteBlockPower(),
                new PerfectShot(),
                new PermanentHorse(),
                new PoorShot(),
                new StrongSkin(),
                new SuperJump(),
                new OceansGrace(),
                OceansGrace.WaterHealth.waterHealth,
                OceansGrace.WaterStrength.waterStrength,
                new VampiricTransformation(),
                new DaylightSensitive(),
                new WaterSensitive(),
                new Leeching(),
                new Stronger(),
                new UndeadAlly()
        ));
        if (nmsInvoker.getGenericScaleAttribute() != null) {
            abilities.add(new LargeBody());
            abilities.add(new SmallBody());
        }
        return abilities;
    }

    private static FantasyNMSInvoker nmsInvoker;

    private static void initializeNMSInvoker() {
        nmsInvoker = switch (Bukkit.getMinecraftVersion()) {
            case "1.21" -> new FantasyNMSInvokerV1_21();
            default -> throw new IllegalStateException("Unexpected version: " + Bukkit.getMinecraftVersion() + " only versions 1.20 - 1.20.6 are supported");
        };
        Bukkit.getPluginManager().registerEvents(getNMSInvoker(), getInstance());
    }

    public static FantasyNMSInvoker getNMSInvoker() {
        return nmsInvoker;
    }

    @Override
    public void onRegister() {
        initializeNMSInvoker();
    }
}