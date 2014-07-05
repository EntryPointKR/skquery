package com.w00tmast3r.skquery.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.w00tmast3r.skquery.api.PropertyPatterns;
import com.w00tmast3r.skriptaddon.skaddonlib.util.Collect;
import com.w00tmast3r.skriptaddon.skriptplus.util.comphenix.InventorySerialUtils;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;


@PropertyPatterns(
        fromType = "inventory",
        property = "serialized contents"

)
public class ExprInventorySerials extends SimplePropertyExpression<Inventory, String> {
    @Override
    protected String getPropertyName() {
        return "inventory serial";
    }

    @Override
    public String convert(Inventory inventory) {
        return InventorySerialUtils.toBase64(inventory);
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? Collect.asArray(String.class) : null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        String s = delta[0] == null ? "" : (String) delta[0];
        Inventory i = getExpr().getSingle(e);
        i.setContents(InventorySerialUtils.fromBase64(s).getContents());
    }
}