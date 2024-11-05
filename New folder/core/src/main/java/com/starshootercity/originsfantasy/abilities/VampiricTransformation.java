/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.starshootercity.Origin
 *  com.starshootercity.OriginSwapper
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  com.starshootercity.events.PlayerSwapOriginEvent$SwapReason
 *  net.kyori.adventure.key.Key
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDeathEvent
 */
package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.Origin;
import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import com.starshootercity.events.PlayerSwapOriginEvent;
import com.starshootercity.originsfantasy.OriginsFantasy;
import java.util.List;
import java.util.Random;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class VampiricTransformation
implements VisibleAbility,
Listener {
    private final Random random = new Random();

    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:vampiric_transformation");
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)(OriginsFantasy.getInstance().getConfig().getDouble("vampire-transform-chance", 1.0) >= 1.0 ? "You can transform other players into vampires by killing them." : "You sometimes transform other players into vampires by killing them."), (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Vampiric Transformation", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            if (event.getEntity().getKiller() == null) {
                return;
            }
            AbilityRegister.runForAbility((Entity)event.getEntity().getKiller(), (Key)this.getKey(), () -> {
                if (this.random.nextDouble() <= OriginsFantasy.getInstance().getConfig().getDouble("vampire-transform-chance", 1.0)) {
                    OriginSwapper.setOrigin((Player)player, (Origin)OriginSwapper.getOrigin((Player)player), (PlayerSwapOriginEvent.SwapReason)PlayerSwapOriginEvent.SwapReason.DIED, (boolean)false);
                    player.sendMessage(Component.text((String)"You have transformed into a Vampire!").color((TextColor)NamedTextColor.RED));
                }
            });
        }
    }
}

