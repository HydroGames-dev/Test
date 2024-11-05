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
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockDropItemEvent
 *  org.bukkit.inventory.ItemStack
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FortuneIncreaser
implements VisibleAbility,
Listener {
    private final Map<Player, Collection<ItemStack>> blocks = new HashMap<Player, Collection<ItemStack>>();

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your care and mastery in the art of extracting minerals results in a much higher yield from ores than other creatures.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Careful Miner", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:fortune_increaser");
    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void onBlockDropItem(BlockDropItemEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        AbilityRegister.runForAbility((Entity)event.getPlayer(), (Key)this.getKey(), () -> {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>(this.blocks.getOrDefault(event.getPlayer(), List.of()));
            ArrayList<ItemStack> otherItems = new ArrayList<ItemStack>();
            for (Item item : event.getItems()) {
                otherItems.add(item.getItemStack());
            }
            event.getItems().clear();
            for (ItemStack itemStack2 : otherItems) {
                for (int itemIndex = 0; itemIndex < items.size(); ++itemIndex) {
                    ItemStack item = (ItemStack)items.get(itemIndex);
                    if (item.getAmount() == 0) continue;
                    if (itemStack2.getType().equals((Object)item.getType()) && itemStack2.getItemMeta().equals((Object)item.getItemMeta())) {
                        int amount = item.getAmount() - itemStack2.getAmount();
                        item.setAmount(Math.max(0, amount));
                    }
                    items.set(itemIndex, item);
                }
            }
            items.addAll(otherItems);
            items.removeIf(itemStack -> itemStack.getAmount() <= 0);
            for (ItemStack itemStack3 : items) {
                event.getItems().add(event.getBlock().getWorld().dropItem(event.getBlock().getLocation().clone().add(0.5, 0.0, 0.5), itemStack3));
            }
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        ItemStack i = event.getPlayer().getInventory().getItemInMainHand().clone();
        if (i.getItemMeta() == null) {
            return;
        }
        i.addUnsafeEnchantment(OriginsFantasy.getNMSInvoker().getFortuneEnchantment(), i.getEnchantmentLevel(OriginsFantasy.getNMSInvoker().getFortuneEnchantment()) + 2);
        this.blocks.put(event.getPlayer(), event.getBlock().getDrops(i, (Entity)event.getPlayer()));
    }
}

