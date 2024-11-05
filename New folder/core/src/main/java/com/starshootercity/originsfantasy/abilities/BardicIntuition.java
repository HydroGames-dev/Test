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
 *  org.bukkit.Tag
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.inventory.ItemStack
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import java.util.Random;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BardicIntuition
implements VisibleAbility,
Listener {
    private final Random random = new Random();

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your musical energy will sometimes cause a creeper to drop a music disc, even without a skeleton.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Bardic Intuition", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:bardic_intuition");
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() != EntityType.CREEPER) {
            return;
        }
        if (event.getEntity().getKiller() == null) {
            return;
        }
        if (this.random.nextDouble() > 0.25) {
            return;
        }
        Material disc = (Material)Tag.ITEMS_CREEPER_DROP_MUSIC_DISCS.getValues().stream().toList().get(this.random.nextInt(Tag.ITEMS_CREEPER_DROP_MUSIC_DISCS.getValues().size()));
        AbilityRegister.runForAbility((Entity)event.getEntity().getKiller(), (Key)this.getKey(), () -> event.getDrops().add(new ItemStack(disc)));
    }
}

