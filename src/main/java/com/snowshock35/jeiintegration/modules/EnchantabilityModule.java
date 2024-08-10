package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class EnchantabilityModule implements TooltipModule {
    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for enchantability")
            .translation("config.jeiintegration.tooltips.enchantabilityTooltipMode")
            .defineEnum("enchantabilityTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        int enchantability = e.getItemStack().getEnchantmentValue();
        if (enchantability > 0) {
            Component enchantabilityTooltip = Component.translatable("tooltip.jeiintegration.enchantability", enchantability)
                                                       .withStyle(ChatFormatting.DARK_GRAY);
            e.getToolTip().add(enchantabilityTooltip);
        }
    }
}
