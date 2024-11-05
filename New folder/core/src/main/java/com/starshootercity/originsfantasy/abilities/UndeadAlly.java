/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  io.papermc.paper.tag.EntityTags
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Keyed
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import io.papermc.paper.tag.EntityTags;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.bukkit.Keyed;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.jetbrains.annotations.NotNull;

public class UndeadAlly
implements VisibleAbility,
Listener {
    private final Map<Player, List<Entity>> attackedEntities = new HashMap<Player, List<Entity>>();

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:undead_ally");
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"As an undead monster, other undead creatures will not attack you unprovoked.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Undead Ally", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @EventHandler
    public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
        LivingEntity livingEntity;
        if (EntityTags.UNDEADS.isTagged((Keyed)event.getEntityType()) && (livingEntity = event.getTarget()) instanceof Player) {
            Player player = (Player)livingEntity;
            AbilityRegister.runForAbility((Entity)player, (Key)this.getKey(), () -> {
                if (!((List)this.attackedEntities.getOrDefault(player, new ArrayList())).contains(event.getEntity())) {
                    event.setCancelled(true);
                }
            });
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Player player;
        Entity entity = event.getDamager();
        if (entity instanceof Player) {
            Player p;
            player = p = (Player)entity;
        } else {
            Projectile projectile;
            entity = event.getDamager();
            if (entity instanceof Projectile && (entity = (projectile = (Projectile)entity).getShooter()) instanceof Player) {
                Player p;
                player = p = (Player)entity;
            } else {
                return;
            }
        }
        List playerHitEntities = this.attackedEntities.getOrDefault(player, new ArrayList());
        playerHitEntities.add(event.getEntity());
        this.attackedEntities.put(player, playerHitEntities);
    }
}

