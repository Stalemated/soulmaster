package com.stalemated.soulmaster.mixin;

import com.stalemated.soulmaster.entity.attribute.SoulFuseAttribute;
import net.forcemaster_rpg.ForcemasterClassMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ForcemasterClassMod.class, remap = false)
public class ForcemasterInitMixin {

    @Inject(method = "onInitialize", at = @At("HEAD"))
    private void soulmaster$registerAttributesBeforeConfig(CallbackInfo ci) {
        SoulFuseAttribute.registerAttributes();
    }
}