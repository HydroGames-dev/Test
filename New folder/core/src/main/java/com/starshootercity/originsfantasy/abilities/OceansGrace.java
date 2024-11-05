/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.Ability
 *  com.starshootercity.abilities.AttributeModifierAbility
 *  com.starshootercity.abilities.MultiAbility
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.entity.Player
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.Ability;
import com.starshootercity.abilities.AttributeModifierAbility;
import com.starshootercity.abilities.MultiAbility;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OceansGrace
implements VisibleAbility,
MultiAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You are a part of the water, so you have extra health and deal extra damage when in water or rain.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Ocean's Grace", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:oceans_grace");
    }

    public List<Ability> getAbilities() {
        return List.of((Object)WaterStrength.waterStrength, (Object)WaterHealth.waterHealth);
    }

    public static class WaterStrength
    implements AttributeModifierAbility {
        public static WaterStrength waterStrength = new WaterStrength();

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:water_strength");
        }

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_ATTACK_DAMAGE;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            if (player.isInWaterOrRainOrBubbleColumn()) {
                return 1.4;
            }
            return 0.0;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }
    }

    public static class WaterHealth
    implements AttributeModifierAbility {
        public static WaterHealth waterHealth = new WaterHealth();

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_MAX_HEALTH;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            if (player.isInWaterOrRainOrBubbleColumn()) {
                return 4.0;
            }
            return 0.0;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.ADD_NUMBER;
        }

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:water_health");
        }
    }
}

