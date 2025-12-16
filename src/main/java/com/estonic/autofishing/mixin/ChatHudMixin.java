package com.estonic.autofishing.mixin;

import com.estonic.autofishing.AutofishState;
import com.estonic.autofishing.FishingDetector;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to intercept chat messages to detect fishing cues
 * Some servers may send fishing notifications via chat
 */
@Mixin(ChatHud.class)
public class ChatHudMixin {
    
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
    private void onAddMessage(Text message, CallbackInfo ci) {
        // Only check if autofishing is enabled
        if (AutofishState.isEnabled()) {
            FishingDetector.checkForFishingCue(message);
        }
    }
}
