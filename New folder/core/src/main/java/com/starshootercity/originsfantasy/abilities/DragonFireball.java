/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.MaterialTags
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.DragonFireball
 *  org.bukkit.entity.Entity
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.plugin.Plugin
 */
package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.MaterialTags;
import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class DragonFireball
implements VisibleAbility,
Listener {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You can right click whilst holding a sword to launch a dragon's fireball, with a cooldown of 20 seconds.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Dragon's Fireball", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:dragon_fireball");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        AbilityRegister.runForAbility((Entity)event.getPlayer(), (Key)this.getKey(), () -> {
            if (!event.getAction().isRightClick()) {
                return;
            }
            if (event.getClickedBlock() != null) {
                return;
            }
            if (event.getItem() == null) {
                return;
            }
            if (!MaterialTags.SWORDS.isTagged(event.getItem().getType())) {
                return;
            }
            if (event.getPlayer().getCooldown(event.getItem().getType()) > 0) {
                return;
            }
            for (Material material : MaterialTags.SWORDS.getValues()) {
                event.getPlayer().setCooldown(material, 200);
            }
            org.bukkit.entity.DragonFireball fireball = (org.bukkit.entity.DragonFireball)event.getPlayer().launchProjectile(org.bukkit.entity.DragonFireball.class);
            fireball.setShooter(event.getPlayer());
            fireball.setGlowing(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)OriginsFantasy.getInstance(), () -> fireball.setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.2)));
        });
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof org.bukkit.entity.DragonFireball fireball && fireball.getShooter() instanceof Player shooter) {
            if (event.getEntity() == shooter) {
                event.setCancelled(true);
            }
        }
    }
}

