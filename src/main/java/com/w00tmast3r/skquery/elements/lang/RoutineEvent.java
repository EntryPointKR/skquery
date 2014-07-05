package com.w00tmast3r.skquery.elements.lang;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RoutineEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final String cause;
    private Object[] params;

    public RoutineEvent(String cause, Object[] params) {
        if(cause == null) {
            this.cause = "";
            return;
        }
        this.cause = cause;
        this.params = params;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getCause() {
        return cause;
    }

    public Object[] getParams(){
        return params;
    }
}
