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
 *  org.bukkit.entity.EnderCrystal
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 */
package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class EndCrystalHealing
implements VisibleAbility,
Listener {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You can regenerate health from nearby End Crystals.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Crystal Healer", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:end_crystal_healing");
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        if (event.getTickNumber() % 5 != 0) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Entity entity : player.getNearbyEntities(48.0, 48.0, 48.0)) {
                if (!(entity instanceof EnderCrystal)) continue;
                EnderCrystal crystal = (EnderCrystal)entity;
                if (entity.getLocation().distance(player.getLocation()) > 12.0) {
                    crystal.setBeamTarget(null);
                    continue;
                }
                AbilityRegister.runForAbility((Entity)player, (Key)this.getKey(), () -> {
                    crystal.setBeamTarget(player.getLocation().clone().subtract(0.0, 1.0, 0.0));
                    player.setHealth(Math.min(20.0, player.getHealth() + 1.0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 600, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20, 1));
                }, () -> crystal.setBeamTarget(null));
            }
        }
    }
}

