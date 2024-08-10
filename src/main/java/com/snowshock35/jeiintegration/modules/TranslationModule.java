package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TranslationModule implements TooltipModule {

    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for translation key. E.g. block.minecraft.stone")
            .translation("config.jeiintegration.tooltips.translationKeyTooltipMode")
            .defineEnum("translationKeyTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        Component translationKeyTooltip = Component.translatable("tooltip.jeiintegration.translationKey", e.getItemStack().getDescriptionId())
                                                   .withStyle(ChatFormatting.DARK_GRAY);
        e.getToolTip().add(translationKeyTooltip);
    }
}
