package com.estonic.autofishing;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutofishingMod implements ClientModInitializer {
    public static final String MOD_ID = "estonic-autofishing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static KeyBinding toggleKeyBinding;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Estonic Autofishing Mod");
        
        // Register keybinding for toggle (F8)
        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.estonic-autofishing.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F8,
            "category.estonic-autofishing"
        ));
        
        // Register tick event for keybind checking and fishing automation
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Check for toggle keybind press
            while (toggleKeyBinding.wasPressed()) {
                AutofishState.toggle(client);
            }
            
            // Run fishing automation if enabled
            if (AutofishState.isEnabled() && client.player != null && FishingDetector.shouldAutoFish()) {
                FishingAutomation.performFishingAction(client);
            }
        });
        
        // Register message event for fishing cue detection
        FishingDetector.registerMessageListener();
        
        LOGGER.info("Estonic Autofishing Mod initialized (default: OFF, press F8 to enable)");
    }
}
