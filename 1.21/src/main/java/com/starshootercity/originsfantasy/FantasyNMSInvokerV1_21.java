package com.starshootercity.originsfantasy;

import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.AbstractProjectile;
import org.bukkit.craftbukkit.entity.CraftAllay;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.entity.EntityMountEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FantasyNMSInvokerV1_21 extends FantasyNMSInvoker {
    @Override
    public void launchArrow(Entity projectile, Entity entity, float roll, float force, float divergence) {
        ((AbstractProjectile) projectile).getHandle().shootFromRotation(((CraftEntity) entity).getHandle(), entity.getPitch(), entity.getYaw(), roll, force, divergence);
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public void transferDamageEvent(LivingEntity entity, EntityDamageEvent event) {
        entity.damage(event.getDamage(), event.getDamageSource());
    }

    @Override
    public void boostArrow(Arrow arrow) {
        if (arrow.getBasePotionType() == null) return;
        for (PotionEffect effect : arrow.getBasePotionType().getPotionEffects()) {
                PotionEffect currentEffect = arrow.getCustomEffects().stream()
                .filter(e -> e.getType().equals(effect.getType()))
                .findFirst()
                .orElse(null);
                if (currentEffect == null) {
                    arrow.addCustomEffect(effect.withDuration(effect.getDuration()-20).withAmplifier(effect.getAmplifier()), true);
                } else {
                    int currentAmplifier = (currentEffect != null) ? currentEffect.getAmplifier() : -1;
                    int duration = (effect.isShorterThan(currentEffect)) ? currentEffect.getDuration(): effect.getDuration();
                    if (currentAmplifier < 2) {
                        arrow.addCustomEffect(
                            effect.withDuration(duration).withAmplifier(currentAmplifier + 1), true
                        );
                    }
                }
            }
        }

    @Override
    public @Nullable Attribute getGenericScaleAttribute() {
        return Attribute.GENERIC_SCALE;
    }

    @Override
    public @NotNull Attribute getGenericJumpStrengthAttribute() {
        return Attribute.GENERIC_JUMP_STRENGTH;
    }

    @Override
    public boolean duplicateAllay(Allay allay) {
        if (allay.getDuplicationCooldown() > 0) return false;
        allay.duplicateAllay();
        ((CraftWorld) allay.getWorld()).getHandle().broadcastEntityEvent(((CraftAllay) allay).getHandle(), (byte) 18);
        return true;
    }

    @Override
    public @NotNull Enchantment getFortuneEnchantment() {
        return Enchantment.FORTUNE;
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