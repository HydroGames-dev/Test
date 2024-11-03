package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.MaterialTags;
import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.AbilityRegister;
import com.starshootercity.abilities.VisibleAbility;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.DragonFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DragonFireballAbility implements VisibleAbility, Listener {
    @Override
    public @NotNull List<OriginSwapper.LineData.LineComponent> getDescription() {
        return OriginSwapper.LineData.makeLineFor("You can right click whilst holding a sword to launch a dragon's fireball, with a cooldown of 20 seconds.", OriginSwapper.LineData.LineComponent.LineType.DESCRIPTION);
    }

    @Override
    public @NotNull List<OriginSwapper.LineData.LineComponent> getTitle() {
        return OriginSwapper.LineData.makeLineFor("Dragon's Fireball", OriginSwapper.LineData.LineComponent.LineType.TITLE);
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:dragon_fireball");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        AbilityRegister.runForAbility(event.getPlayer(), getKey(), () -> {
            if (event.getItem() == null) return;
            if (!MaterialTags.SWORDS.isTagged(event.getItem().getType())) return;
            if (event.getPlayer().getCooldown(event.getItem().getType()) > 0) return;
            for (Material material : MaterialTags.SWORDS.getValues()) {
                event.getPlayer().setCooldown(material, 300);
            }
            DragonFireball fireball = event.getPlayer().launchProjectile(DragonFireball.class);
            fireball.setGlowing(true);
            fireball.setShooter(event.getPlayer());
        });
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof DragonFireball fireball && fireball.getShooter() instanceof Player shooter) {
            if (event.getEntity() == shooter) {
                event.setCancelled(true);
            }
        }
    }
}
