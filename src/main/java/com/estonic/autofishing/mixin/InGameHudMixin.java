package com.estonic.autofishing.mixin;

import com.estonic.autofishing.FishingDetector;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to intercept HUD text rendering to detect fishing cues
 */
@Mixin(InGameHud.class)
public class InGameHudMixin {
    
    @Inject(method = "renderOverlay", at = @At("HEAD"))
    private void onRenderOverlay(DrawContext context, Text text, CallbackInfo ci) {
        FishingDetector.checkForFishingCue(context, text);
    }
}
