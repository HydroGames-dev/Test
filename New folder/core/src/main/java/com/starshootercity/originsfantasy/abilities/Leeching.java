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
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDeathEvent
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class Leeching
implements VisibleAbility,
Listener {
    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:leeching");
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Upon killing a mob or player, you sap a portion of its health, healing you.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Leeching", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }
        AbilityRegister.runForAbility((Entity)event.getEntity().getKiller(), (Key)this.getKey(), () -> {
            AttributeInstance mH = event.getEntity().getKiller().getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (mH == null) {
                return;
            }
            double maxHealth = mH.getValue();
            AttributeInstance mobMH = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (mobMH == null) {
                return;
            }
            double mobMaxHealth = mobMH.getValue();
            event.getEntity().getKiller().setHealth(Math.min(maxHealth, event.getEntity().getKiller().getHealth() + mobMaxHealth / 5.0));
        });
    }
}

