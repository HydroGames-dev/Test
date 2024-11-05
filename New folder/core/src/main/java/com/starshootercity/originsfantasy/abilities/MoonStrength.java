/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AttributeModifierAbility
 *  com.starshootercity.abilities.VisibleAbility
 *  io.papermc.paper.world.MoonPhase
 *  net.kyori.adventure.key.Key
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.entity.Player
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AttributeModifierAbility;
import com.starshootercity.abilities.VisibleAbility;
import io.papermc.paper.world.MoonPhase;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoonStrength
implements VisibleAbility,
AttributeModifierAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You're a worshipper of the moon, so on nights with a full moon you're stronger than normal.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Moon's Blessing", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:moon_strength");
    }

    @NotNull
    public Attribute getAttribute() {
        return Attribute.GENERIC_ATTACK_DAMAGE;
    }

    public double getAmount() {
        return 0.0;
    }

    public double getChangedAmount(Player player) {
        if (!player.getWorld().isDayTime() && player.getWorld().getMoonPhase() == MoonPhase.FULL_MOON) {
            return 2.4;
        }
        return 0.0;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull AttributeModifier.Operation getOperation() {
        return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
    }
}

