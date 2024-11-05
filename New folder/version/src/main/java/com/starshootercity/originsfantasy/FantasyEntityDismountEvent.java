package com.starshootercity.originsfantasy;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class FantasyEntityDismountEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Entity dismounted;
    private final boolean isCancellable;

    public FantasyEntityDismountEvent(@NotNull Entity what, @NotNull Entity dismounted) {
        this(what, dismounted, true);
    }

    public FantasyEntityDismountEvent(@NotNull Entity what, @NotNull Entity dismounted, boolean isCancellable) {
        super(what);
        this.dismounted = dismounted;
        this.isCancellable = isCancellable;
    }

    @NotNull
    public Entity getDismounted() {
        return this.dismounted;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancel) {
        if (cancel && !this.isCancellable) {
            return;
        }
        this.cancelled = cancel;
    }

    public boolean isCancellable() {
        return this.isCancellable;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

