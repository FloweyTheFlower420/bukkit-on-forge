package com.floweytf.forgebukkit.mixins;

import com.floweytf.forgebukkit.access.AbstractHorseEntityMixinAccess;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorseEntity.class)
public class AbstractHorseEntityMixin implements AbstractHorseEntityMixinAccess {
    public int maxDomestication = 100;

    @Shadow
    protected int temper;

    @Inject(method = "increaseTemper(I)I", at = @At("HEAD"), cancellable = true)
    private void increaseTemper(int p_110198_1_, CallbackInfoReturnable<Integer> cir) {
        int j = MathHelper.clamp(temper + p_110198_1_, 0, maxDomestication);

        temper = j;
        cir.setReturnValue(j);
    }

    @Override
    public int getMaxDomestication() {
        return maxDomestication;
    }

    @Override
    public void setMaxDomestication(int i) {
        maxDomestication = i;
    }
}
