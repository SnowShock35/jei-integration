package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class MaxStackSizeModule implements TooltipModule {
    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for max stack size.")
            .translation("config.jeiintegration.tooltips.maxStackSizeTooltipMode")
            .defineEnum("maxStackSizeTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        int stackSize = e.getItemStack().getMaxStackSize();
        if (stackSize > 0) {
            Component stackSizeTooltip = Component.translatable("tooltip.jeiintegration.maxStackSize", stackSize)
                                                  .withStyle(ChatFormatting.DARK_GRAY);
            e.getToolTip().add(stackSizeTooltip);
        }
    }
}
