package com.starshootercity.originsfantasy;

import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public abstract class FantasyNMSInvoker
implements Listener {
    public abstract void launchArrow(Entity var1, Entity var2, float var3, float var4, float var5);

    public abstract void transferDamageEvent(LivingEntity var1, EntityDamageEvent var2);

    public abstract void boostArrow(Arrow var1);

    public abstract Attribute getGenericScaleAttribute();

    @NotNull
    public abstract Attribute getGenericJumpStrengthAttribute();

    public abstract boolean duplicateAllay(Allay var1);

    @NotNull
    public abstract Enchantment getFortuneEnchantment();
}

