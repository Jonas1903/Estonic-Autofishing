package com.estonic.autofishing.mixin;

import com.estonic.autofishing.AutofishState;
import com.estonic.autofishing.FishingDetector;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to intercept HUD rendering to detect fishing cues
 * Detects overlay messages like "Reel it in!" that appear on screen
 * Hooks into both overlay rendering and action bar messages
 */
@Mixin(InGameHud.class)
public class InGameHudMixin {
    
    /**
     * Hook into the overlay text rendering method to detect fishing cues
     * This catches action bar messages and title overlays
     */
    @Inject(method = "renderOverlay", at = @At("HEAD"))
    private void onRenderOverlay(DrawContext context, Text text, CallbackInfo ci) {
        // Only check if autofishing is enabled
        if (AutofishState.isEnabled()) {
            FishingDetector.checkForFishingCue(text);
        }
    }
    
    /**
     * Also hook into setOverlayMessage to catch action bar messages
     */
    @Inject(method = "setOverlayMessage", at = @At("HEAD"))
    private void onSetOverlayMessage(Text message, boolean tinted, CallbackInfo ci) {
        // Only check if autofishing is enabled
        if (AutofishState.isEnabled()) {
            FishingDetector.checkForFishingCue(message);
        }
    }
}
