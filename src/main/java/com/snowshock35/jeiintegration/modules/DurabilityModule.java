package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class DurabilityModule implements TooltipModule {
    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for durability.")
            .translation("config.jeiintegration.tooltips.durabilityTooltipMode")
            .defineEnum("durabilityTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        ItemStack itemStack = e.getItemStack();
        int maxDamage = itemStack.getMaxDamage();
        int currentDamage = maxDamage - itemStack.getDamageValue();
        if (maxDamage > 0) {
            Component durabilityTooltip = Component.translatable("tooltip.jeiintegration.durability", currentDamage, maxDamage)
                                                   .withStyle(ChatFormatting.DARK_GRAY);
            e.getToolTip().add(durabilityTooltip);
        }
    }
}
