/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public class IncreasedSpeed
implements VisibleAbility {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"From years of training for race after race, you're much faster than any normal horse.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Dashmaster", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:increased_speed");
    }
}

