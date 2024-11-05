/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Material
 *  org.bukkit.entity.AbstractArrow$PickupStatus
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BowBurst
implements VisibleAbility,
Listener {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"By casting a spell on any regular arrow, you can instantly shoot 3 arrows at once using only one, but this disables your bow for 7 seconds.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Bow Burst", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:bow_burst");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isLeftClick()) {
            return;
        }
        if (event.getItem() == null || event.getItem().getType() != Material.BOW) {
            return;
        }
        if (event.getPlayer().getCooldown(Material.BOW) > 0) {
            return;
        }
        AbilityRegister.runForAbility((Entity)event.getPlayer(), (Key)this.getKey(), () -> {
            if (event.getPlayer().getInventory().contains(Material.ARROW)) {
                for (ItemStack item : event.getPlayer().getInventory()) {
                    if (item == null || item.getType() != Material.ARROW) continue;
                    item.setAmount(item.getAmount() - 1);
                    break;
                }
                event.getPlayer().setCooldown(Material.BOW, 140);
                Arrow a1 = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
                Arrow a2 = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
                Arrow a3 = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
                OriginsFantasy.getNMSInvoker().launchArrow((Entity)a1, (Entity)event.getPlayer(), 0.0f, 3.0f, 15.0f);
                OriginsFantasy.getNMSInvoker().launchArrow((Entity)a1, (Entity)event.getPlayer(), 0.0f, 3.0f, 0.0f);
                OriginsFantasy.getNMSInvoker().launchArrow((Entity)a1, (Entity)event.getPlayer(), 0.0f, 3.0f, 15.0f);
                a1.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
                a2.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
                a3.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
            }
        });
    }
}

