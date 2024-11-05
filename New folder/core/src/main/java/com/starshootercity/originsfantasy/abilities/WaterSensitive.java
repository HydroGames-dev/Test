/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.event.server.ServerTickEndEvent
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.OriginsReborn
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 */
package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.OriginSwapper;
import com.starshootercity.OriginsReborn;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class WaterSensitive
implements VisibleAbility,
Listener {
    private final Map<Player, Integer> lastWaterDamagedMap = new HashMap<Player, Integer>();

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:water_sensitive");
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You are hurt by water as its current drains your power.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Water Sensitive", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Bukkit.getCurrentTick() - this.lastWaterDamagedMap.getOrDefault(player, Bukkit.getCurrentTick() - 20) < 20) continue;
            AbilityRegister.runForAbility((Entity)player, (Key)this.getKey(), () -> {
                if (player.isInWaterOrRainOrBubbleColumn() || OriginsReborn.getNMSInvoker().wasTouchingWater(player)) {
                    OriginsReborn.getNMSInvoker().dealFreezeDamage((LivingEntity)player, 1);
                    this.lastWaterDamagedMap.put(player, Bukkit.getCurrentTick());
                }
            });
        }
    }
}

