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
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AttributeModifierAbility;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.jetbrains.annotations.NotNull;

public class SmallBody
implements VisibleAbility,
AttributeModifierAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You're only one block tall, half as tall as a regular human.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Small Body", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:small_body");
    }

    @NotNull
    public Attribute getAttribute() {
        return OriginsFantasy.getNMSInvoker().getGenericScaleAttribute();
    }

    public double getAmount() {
        return -0.5;
    }

    public AttributeModifier.Operation getOperation() {
        return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
    }
}

