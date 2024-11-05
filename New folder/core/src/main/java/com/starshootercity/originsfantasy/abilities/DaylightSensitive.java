/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.MaterialTags
 *  com.destroystokyo.paper.event.server.ServerTickEndEvent
 *  com.starshootercity.OriginSwapper$LineData
 *  com.starshootercity.OriginSwapper$LineData$LineComponent
 *  com.starshootercity.OriginSwapper$LineData$LineComponent$LineType
 *  com.starshootercity.OriginsReborn
 *  com.starshootercity.abilities.AbilityRegister
 *  com.starshootercity.abilities.VisibleAbility
 *  net.kyori.adventure.key.Key
 *  org.bukkit.Bukkit
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 */
package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.MaterialTags;
import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.OriginSwapper;
import com.starshootercity.OriginsReborn;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class DaylightSensitive
implements VisibleAbility,
Listener {
    @NotNull
    public Key getKey() {
        return Key.key((String)"fantasyorigins:daylight_sensitive");
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor((String)"Your greatest weakness is daylight, which causes you to burst into flames.", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @NotNull
    public List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor((String)"Daylight Sensitive", (OriginSwapper.LineData.LineComponent.LineType)OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent ignored) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            AbilityRegister.runForAbility((Entity)player, (Key)this.getKey(), () -> {
                Block block = player.getWorld().getHighestBlockAt(player.getLocation());
                while (MaterialTags.GLASS.isTagged(block) || MaterialTags.GLASS_PANES.isTagged(block) && (double)block.getY() >= player.getLocation().getY()) {
                    block = block.getRelative(BlockFace.DOWN);
                }
                boolean height = (double)block.getY() < player.getLocation().getY();
                String overworld = OriginsReborn.getInstance().getConfig().getString("worlds.world");
                if (overworld == null) {
                    overworld = "world";
                    OriginsReborn.getInstance().getConfig().set("worlds.world", (Object)"world");
                    OriginsReborn.getInstance().saveConfig();
                }
                boolean isInOverworld = player.getWorld() == Bukkit.getWorld((String)overworld);
                boolean day = player.getWorld().isDayTime();
                if (height && isInOverworld && day && !player.isInWaterOrRainOrBubbleColumn()) {
                    player.setFireTicks(Math.max(player.getFireTicks(), 60));
                }
            });
        }
    }
}

