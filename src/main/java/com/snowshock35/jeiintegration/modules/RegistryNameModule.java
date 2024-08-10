package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryNameModule implements TooltipModule {
    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for registry name. E.g. minecraft:stone")
            .translation("config.jeiintegration.tooltips.registryNameTooltipMode")
            .defineEnum("registryNameTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        Component registryTooltip = Component.translatable("tooltip.jeiintegration.registryName", ForgeRegistries.ITEMS.getKey(e.getItemStack().getItem()))
                                             .withStyle(ChatFormatting.DARK_GRAY);
        e.getToolTip().add(registryTooltip);
    }
}
