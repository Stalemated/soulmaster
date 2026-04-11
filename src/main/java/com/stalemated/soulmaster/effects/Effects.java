package com.stalemated.soulmaster.effects;

import com.stalemated.soulmaster.entity.attribute.SoulFuseAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.effect.*;
import net.spell_power.api.SpellPower;
import net.spell_power.api.SpellSchools;

import static com.stalemated.soulmaster.Soulmaster.MOD_ID;
import static com.stalemated.soulmaster.Soulmaster.config;

public class Effects {

    public static StatusEffect SOUL_OVERFLOW = new SoulOverflowEffect(StatusEffectCategory.BENEFICIAL, 0x70e0e6);

    public static StatusEffect SOUL_BARQ_ESNA = new SoulBarqEsnaEffect(StatusEffectCategory.HARMFUL, 0x8db4fe)
            .setVulnerability(SpellSchools.SOUL, new SpellPower.Vulnerability(
                    config.value.soul_barq_esna_soul_damage_vulnerability,
                    config.value.soul_barq_esna_critical_chance_bonus,
                    config.value.soul_barq_esna_critical_damage_bonus
            ));

    public static void register() {
        SOUL_OVERFLOW
                .addAttributeModifier(SoulFuseAttribute.SOUL_FUSE_MODIFIER, "3f1ff4db-d7a0-47cf-b5a8-16cdfc0982c6",
                        config.value.soul_overflow_soul_fuse_multiply, EntityAttributeModifier.Operation.MULTIPLY_BASE)
                .addAttributeModifier(SpellSchools.SOUL.attribute, "d05b2145-bf68-4523-9708-38e513741476",
                        config.value.soul_overflow_soul_power_multiply, EntityAttributeModifier.Operation.MULTIPLY_BASE);
        SOUL_BARQ_ESNA
                .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "3e9a5cd1-55b3-4f50-89bf-14efc6d7668c",
                        -config.value.soul_barq_esna_attack_damage_reduction ,EntityAttributeModifier.Operation.MULTIPLY_BASE);

        Synchronized.configure(SOUL_BARQ_ESNA,true);
        Synchronized.configure(SOUL_OVERFLOW,true);

        Registry.register(Registries.STATUS_EFFECT, 7272, new Identifier(MOD_ID, "soul_barq_esna").toString(), SOUL_BARQ_ESNA);
        Registry.register(Registries.STATUS_EFFECT, 7273, new Identifier(MOD_ID, "soul_overflow").toString(), SOUL_OVERFLOW);
    }
}