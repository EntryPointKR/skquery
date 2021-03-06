package com.w00tmast3r.skquery.util.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;

final class MessagePart {

    ChatColor color = ChatColor.WHITE;
    ChatColor[] styles = {};
    String clickActionName = null, clickActionData = null,
            hoverActionName = null, hoverActionData = null;
    final String text;

    MessagePart(final String text) {
        this.text = text;
    }

    JsonWriter writeJson(JsonWriter json) {
        try {
            json.beginObject().name("text").value(text);
            json.name("color").value(color.name().toLowerCase());
            for (final ChatColor style : styles) {
                json.name(style.name().toLowerCase()).value(true);
            }
            if (clickActionName != null && clickActionData != null) {
                json.name("clickEvent")
                        .beginObject()
                        .name("action").value(clickActionName)
                        .name("value").value(clickActionData)
                        .endObject();
            }
            if (hoverActionName != null && hoverActionData != null) {
                json.name("hoverEvent")
                        .beginObject()
                        .name("action").value(hoverActionName)
                        .name("value").value(hoverActionData)
                        .endObject();
            }
            return json.endObject();
        } catch(Exception e){
            e.printStackTrace();
            return json;
        }
    }

}