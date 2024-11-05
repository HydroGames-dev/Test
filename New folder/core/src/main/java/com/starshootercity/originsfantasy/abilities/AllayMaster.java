package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AllayMaster
implements VisibleAbility,
Listener {
    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your musical aura allows you to breed allays without playing music.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Allay Master", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:allay_master");
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        AbilityRegister.runForAbility((Entity)event.getPlayer(), (Key)this.getKey(), () -> {
            Entity patt0$temp = event.getRightClicked();
            if (patt0$temp instanceof Allay) {
                Allay allay = (Allay)patt0$temp;
                ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
                if (item.getType() != Material.AMETHYST_SHARD) {
                    return;
                }
                if (!OriginsFantasy.getNMSInvoker().duplicateAllay(allay)) {
                    return;
                }
                event.setCancelled(true);
                item.setAmount(item.getAmount() - 1);
                if (event.getHand() == EquipmentSlot.HAND) {
                    event.getPlayer().swingMainHand();
                } else {
                    event.getPlayer().swingOffHand();
                }
                event.getPlayer().getInventory().setItem(event.getHand(), item);
            }
        });
    }
}

