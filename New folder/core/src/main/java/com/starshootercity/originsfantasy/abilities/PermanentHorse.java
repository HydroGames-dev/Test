/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.event.server.ServerTickEndEvent
 *  com.starshootercity.Origin
 *  com.starshootercity.OriginSwapper
 *  com.starshootercity.OriginSwapper$BooleanPDT
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  com.starshootercity.events.PlayerSwapOriginEvent
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Horse
 *  org.bukkit.entity.Horse$Style
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event$Result
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.PlayerDeathEvent
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.player.PlayerBedEnterEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.Origin;
import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.events.PlayerSwapOriginEvent;
import com.starshootercity.originsfantasy.FantasyEntityDismountEvent;
import com.starshootercity.originsfantasy.FantasyEntityMountEvent;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PermanentHorse
implements VisibleAbility,
Listener {
    private final NamespacedKey key = new NamespacedKey((Plugin)OriginsFantasy.getInstance(), "mount-key");

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"You are half horse, half human.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Half Horse", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:permanent_horse");
    }

    @EventHandler
    public void onEntityDismount(FantasyEntityDismountEvent event) {
        if (event.getEntity().isDead()) {
            return;
        }
        AbilityRegister.runForAbility((Entity)event.getEntity(), (Key)this.getKey(), () -> {
            event.setCancelled(true);
            Entity vehicle = event.getDismounted().getVehicle();
            if (vehicle != null) {
                vehicle.removePassenger(event.getDismounted());
            }
        });
    }

    @EventHandler
    public void onEntityMount(FantasyEntityMountEvent event) {
        AbilityRegister.runForAbility((Entity)event.getEntity(), (Key)this.getKey(), () -> {
            if (!((String)event.getMount().getPersistentDataContainer().getOrDefault(this.key, PersistentDataType.STRING, (Object)"")).equals(event.getEntity().getUniqueId().toString())) {
                Entity vehicle;
                event.setCancelled(true);
                if (List.of((Object)EntityType.BOAT, (Object)EntityType.CHEST_BOAT, (Object)EntityType.MINECART).contains(event.getMount().getType()) && (vehicle = event.getEntity().getVehicle()) != null) {
                    event.getMount().addPassenger(vehicle);
                }
            }
        });
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        if (event.getTickNumber() % 20 != 0) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isDead()) continue;
            AbilityRegister.runForAbility((Entity)player, (Key)this.getKey(), () -> {
                if (player.getVehicle() != null) {
                    return;
                }
                Horse horse = (Horse)player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                AttributeInstance jump = horse.getAttribute(OriginsFantasy.getNMSInvoker().getGenericJumpStrengthAttribute());
                AttributeInstance speed = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                Origin origin = OriginSwapper.getOrigin((Player)player);
                if (origin != null) {
                    if (origin.hasAbility(Key.key((String)"fantasyorigins:super_jump")) && jump != null) {
                        jump.setBaseValue(1.0);
                    }
                    if (origin.hasAbility(Key.key((String)"fantasyorigins:increased_speed")) && speed != null) {
                        speed.setBaseValue(0.4);
                    }
                }
                horse.getPersistentDataContainer().set(this.key, PersistentDataType.STRING, (Object)player.getUniqueId().toString());
                horse.setTamed(true);
                horse.setStyle(Horse.Style.NONE);
                ItemStack saddle = new ItemStack(Material.SADDLE);
                ItemMeta meta = saddle.getItemMeta();
                meta.getPersistentDataContainer().set(this.key, (PersistentDataType)OriginSwapper.BooleanPDT.BOOLEAN, (Object)true);
                saddle.setItemMeta(meta);
                horse.getInventory().setSaddle(saddle);
                horse.addPassenger((Entity)player);
            });
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(this.key)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        AbilityRegister.runForAbility((Entity)event.getEntity(), (Key)this.getKey(), () -> {
            Entity vehicle = event.getEntity().getVehicle();
            if (vehicle != null && vehicle.getPersistentDataContainer().has(this.key)) {
                vehicle.remove();
            }
        });
    }

    @EventHandler
    public void onPlayerSwapOrigin(PlayerSwapOriginEvent event) {
        Entity vehicle = event.getPlayer().getVehicle();
        if (vehicle != null && vehicle.getPersistentDataContainer().has(this.key)) {
            vehicle.remove();
        }
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        AbilityRegister.runForAbility((Entity)event.getPlayer(), (Key)this.getKey(), () -> event.setUseBed(Event.Result.DENY));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity().getPersistentDataContainer().has(this.key)) {
            for (Entity entity : event.getEntity().getPassengers()) {
                if (!(entity instanceof LivingEntity)) continue;
                LivingEntity livingEntity = (LivingEntity)entity;
                OriginsFantasy.getNMSInvoker().transferDamageEvent(livingEntity, event);
            }
            event.setCancelled(true);
        }
    }
}

