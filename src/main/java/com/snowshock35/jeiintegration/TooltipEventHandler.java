package com.snowshock35.jeiintegration;

import com.mojang.blaze3d.platform.InputConstants;
import com.snowshock35.jeiintegration.config.OptionState;
import com.snowshock35.jeiintegration.modules.TooltipModuleHolder;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class TooltipEventHandler {
    private final TooltipModuleManager tooltipModuleManager;

    public TooltipEventHandler(TooltipModuleManager tooltipModuleManager) {
        this.tooltipModuleManager = tooltipModuleManager;
    }


    private static boolean isDebugMode() {
        return Minecraft.getInstance().options.advancedItemTooltips;
    }

    private static boolean isShiftKeyDown() {
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) ||
            InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    private static boolean isEnabled(ForgeConfigSpec.ConfigValue<OptionState> configValue) {
        OptionState configOption = configValue.get();
        boolean isEnabled = false;

        if (configOption == OptionState.ENABLED) {
            isEnabled = true;
        } else if (configOption == OptionState.ON_SHIFT && isShiftKeyDown()) {
            isEnabled = true;
        } else if (configOption == OptionState.ON_DEBUG && isDebugMode()) {
            isEnabled = true;
        } else if (configOption == OptionState.ON_SHIFT_AND_DEBUG && isShiftKeyDown() && isDebugMode()) {
            isEnabled = true;
        }
        return isEnabled;
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent e) {
        if (e.getItemStack().isEmpty()) {
            return;
        }

        for (TooltipModuleHolder module : tooltipModuleManager.getEnabledModules()) {
            if (isEnabled(module.config())) {
                module.definition().apply(e);
            }
        }
    }
}
