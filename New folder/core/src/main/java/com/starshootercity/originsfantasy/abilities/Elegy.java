/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AttributeModifierAbility
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.entity.Player
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AttributeModifierAbility;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Elegy
implements VisibleAbility,
AttributeModifierAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You become stronger when at less than 3 hearts.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Elegy", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:elegy");
    }

    @NotNull
    public Attribute getAttribute() {
        return Attribute.GENERIC_ATTACK_DAMAGE;
    }

    public double getAmount() {
        return 0.0;
    }

    public AttributeModifier.Operation getOperation() {
        return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
    }

    public double getChangedAmount(Player player) {
        return player.getHealth() <= 6.0 ? 2.0 : 0.0;
    }
}

