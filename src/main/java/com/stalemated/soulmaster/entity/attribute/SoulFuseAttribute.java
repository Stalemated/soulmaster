package com.stalemated.soulmaster.entity.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.stalemated.soulmaster.Soulmaster.MOD_ID;

public class SoulFuseAttribute {

    public static EntityAttribute SOUL_FUSE_MODIFIER = createAttribute("soul_fuse_modifier", 100.0, 100.0, 1024.0);

    public static void registerAttributes() {
        register("soul_fuse_modifier", SOUL_FUSE_MODIFIER);
    }

    public static EntityAttribute register(String id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, id), attribute);
    }

    private static EntityAttribute createAttribute(final String name, double base, double min, double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true);
    }
}
