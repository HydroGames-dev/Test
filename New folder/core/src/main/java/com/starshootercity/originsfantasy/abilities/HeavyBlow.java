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
import org.jetbrains.annotations.NotNull;

public class HeavyBlow
implements VisibleAbility,
MultiAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your attacks are stronger than humans, but you have a longer attack cooldown.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Heavy Blow", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:heavy_blow");
    }

    public List<Ability> getAbilities() {
        return List.of((Object)IncreasedDamage.increasedDamage, (Object)IncreasedCooldown.increasedCooldown);
    }

    public static class IncreasedDamage
    implements AttributeModifierAbility {
        public static IncreasedDamage increasedDamage = new IncreasedDamage();

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_ATTACK_DAMAGE;
        }

        public double getAmount() {
            return 1.2;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:increased_damage");
        }
    }

    public static class IncreasedCooldown
    implements AttributeModifierAbility {
        public static IncreasedCooldown increasedCooldown = new IncreasedCooldown();

        @NotNull
        public Attribute getAttribute() {
            return Attribute.GENERIC_ATTACK_SPEED;
        }

        public double getAmount() {
            return -0.4;
        }

        public // Could not load outer class - annotation placement on inner may be incorrect
         @NotNull AttributeModifier.Operation getOperation() {
            return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }

        @NotNull
        public Key getKey() {
            return Key.key((String)"fantasyorigins:increased_cooldown");
        }
    }
}

