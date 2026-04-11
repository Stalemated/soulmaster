package com.stalemated.soulmaster.mixin;

import com.stalemated.soulmaster.entity.attribute.SoulFuseAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellSchools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerEntity.class, priority = 15000)
public abstract class PlayerEntityMixin {

    @Inject(method = "createPlayerAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", at = @At("RETURN"))
    private static void addAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(SoulFuseAttribute.SOUL_FUSE_MODIFIER);
    }

    @Inject(method = "attack", at = @At("TAIL"))
    private void onAttackSoulFuse(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object) this;

        if (target instanceof LivingEntity livingTarget && !player.getWorld().isClient()) {
            EntityAttributeInstance soulFuse = player.getAttributeInstance(SoulFuseAttribute.SOUL_FUSE_MODIFIER);

            if (soulFuse != null && soulFuse.getValue() != 100.0) {
                float soulBonus = (float) ((soulFuse.getValue() - 100) / 100f);
                float soulPower = (float) player.getAttributeValue(SpellSchools.SOUL.attribute);
                float magicDamage = Math.max(0.1f, soulBonus * soulPower);

                DamageSource soulSource = player.getDamageSources().magic(); // fallback
                livingTarget.timeUntilRegen = 0;
                livingTarget.damage(SpellDamageSource.create(SpellSchools.SOUL, player), magicDamage);
            }
        }
    }
}