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
 *  org.bukkit.World$Environment
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
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EndBoost
implements VisibleAbility,
MultiAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your natural habitat is the end, so you have more health and are stronger when you are there.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"End Inhabitant", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:end_boost");
    }

    public List<Ability> getAbilities() {
        return List.of((Object)EndStrength.endStrength, (Object)EndHealth.endHealth);
    }

    public static class EndStrength
    implements AttributeModifierAbility {
        public static EndStrength endStrength = new EndStrength();

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_ATTACK_DAMAGE;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            return player.getWorld().getEnvironment() == World.Environment.THE_END ? 1.8 : 0.0;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:end_strength");
        }
    }

    public static class EndHealth
    implements AttributeModifierAbility {
        public static EndHealth endHealth = new EndHealth();

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:end_health");
        }

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_MAX_HEALTH;
        }

        public double getAmount() {
            return 0.0;
        }

        public double getChangedAmount(Player player) {
            return player.getWorld().getEnvironment() == World.Environment.THE_END ? 20.0 : 0.0;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.ADD_NUMBER;
        }
    }
}

