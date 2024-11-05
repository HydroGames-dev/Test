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

public class OceanWish
implements VisibleAbility,
MultiAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your natural habitat is the ocean, so you're much weaker when you're not in the water.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Ocean Wish", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:ocean_wish");
    }

    public List<Ability> getAbilities() {
        return List.of((Object)LandSlowness.landSlowness, (Object)LandHealth.landHealth, (Object)LandWeakness.landWeakness);
    }

    public static class LandSlowness
    implements AttributeModifierAbility {
        public static LandSlowness landSlowness = new LandSlowness();

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:land_slowness");
        }

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_MOVEMENT_SPEED;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            if (player.isInWaterOrRainOrBubbleColumn()) {
                return 0.0;
            }
            return -0.4;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }
    }

    public static class LandHealth
    implements AttributeModifierAbility {
        public static LandHealth landHealth = new LandHealth();

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:land_health");
        }

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_MAX_HEALTH;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            if (player.isInWaterOrRainOrBubbleColumn()) {
                return 0.0;
            }
            return -12.0;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.ADD_NUMBER;
        }
    }

    public static class LandWeakness
    implements AttributeModifierAbility {
        public static LandWeakness landWeakness = new LandWeakness();

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_ATTACK_DAMAGE;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            return -0.4;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:land_weakness");
        }
    }
}

