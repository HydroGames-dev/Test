package com.starshootercity.originsfantasy;

import com.starshootercity.originsfantasy.FantasyEntityDismountEvent;
import com.starshootercity.originsfantasy.FantasyEntityMountEvent;
import com.starshootercity.originsfantasy.FantasyNMSInvoker;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.projectile.EntityArrow;
import net.minecraft.world.entity.projectile.EntityTippedArrow;
import net.minecraft.world.entity.projectile.IProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtil;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftAllay;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

public class FantasyNMSInvokerV1_19
extends FantasyNMSInvoker {
    private final Map<Allay, Integer> allayDuplicationCooldowns = new HashMap<Allay, Integer>();

    @Override
    public void launchArrow(Entity projectile, Entity entity, float roll, float force, float divergence) {
        ((IProjectile)((AbstractProjectile)projectile).getHandle()).a(((CraftEntity)entity).getHandle(), entity.getLocation().getPitch(), entity.getLocation().getYaw(), roll, force, divergence);
    }

    @Override
    public void transferDamageEvent(LivingEntity entity, EntityDamageEvent event) {
        entity.damage(event.getDamage());
    }

    @Override
    public void boostArrow(Arrow arrow) {
        EntityArrow entityArrow = ((CraftArrow)arrow).getHandle();
        if (entityArrow instanceof EntityTippedArrow) {
            EntityTippedArrow a = (EntityTippedArrow)entityArrow;
            for (MobEffect instance : PotionUtil.d((ItemStack)a.l()).a()) {
                a.a(new MobEffect(instance.b(), instance.c(), instance.d() + 1));
            }
        }
    }

    @Override
    @Nullable
    public Attribute getGenericScaleAttribute() {
        return null;
    }

    @Override
    @NotNull
    public Attribute getGenericJumpStrengthAttribute() {
        return Attribute.HORSE_JUMP_STRENGTH;
    }

    @Override
    public boolean duplicateAllay(Allay allay) {
        if (this.allayDuplicationCooldowns.getOrDefault(allay, Bukkit.getCurrentTick()) - Bukkit.getCurrentTick() > 0) {
            return false;
        }
        Allay newAllay = (Allay)allay.getWorld().spawnEntity(allay.getLocation(), EntityType.ALLAY);
        this.allayDuplicationCooldowns.put(allay, Bukkit.getCurrentTick() + 3000);
        this.allayDuplicationCooldowns.put(newAllay, Bukkit.getCurrentTick() + 3000);
        ((CraftWorld)allay.getWorld()).getHandle().a((net.minecraft.world.entity.Entity)((CraftAllay)allay).getHandle(), (byte)18);
        return true;
    }

    @Override
    @NotNull
    public Enchantment getFortuneEnchantment() {
        return Enchantment.LOOT_BONUS_BLOCKS;
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        event.setCancelled(!new FantasyEntityDismountEvent(event.getEntity(), event.getDismounted(), event.isCancellable()).callEvent());
    }

    @EventHandler
    public void onEntityMount(EntityMountEvent event) {
        event.setCancelled(!new FantasyEntityMountEvent(event.getEntity(), event.getMount()).callEvent());
    }
}

