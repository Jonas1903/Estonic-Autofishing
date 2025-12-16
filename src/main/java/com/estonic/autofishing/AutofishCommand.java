package com.estonic.autofishing;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

/**
 * Command handler for the /autofish toggle command
 */
public class AutofishCommand {
    
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("autofish")
            .then(literal("toggle")
                .executes(AutofishCommand::toggleAutofish)));
    }

    private static int toggleAutofish(CommandContext<FabricClientCommandSource> context) {
        // Toggle the state
        boolean newState = AutofishState.toggle();
        
        // Send feedback to the player
        String status = newState ? "§aON" : "§cOFF";
        context.getSource().sendFeedback(
            Text.literal("§6[Autofish]§r Autofishing is now " + status)
        );
        
        AutofishingMod.LOGGER.info("Autofishing toggled to: " + (newState ? "ON" : "OFF"));
        
        return 1;
    }
}
