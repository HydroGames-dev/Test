/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.event.server.ServerTickEndEvent
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class InfiniteNightVision
implements VisibleAbility,
Listener {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You can see in the dark after generations of evolution.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Dark Eyes", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:infinite_night_vision");
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        if (event.getTickNumber() % 15 != 0) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            AbilityRegister.runForAbility((Entity)player, (Key)this.getKey(), () -> player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 240, 0)));
        }
    }
}

