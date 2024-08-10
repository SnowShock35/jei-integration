package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class NbtDataModule implements TooltipModule {
    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for NBT data.")
            .translation("config.jeiintegration.tooltips.nbtTooltipMode")
            .defineEnum("nbtTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        CompoundTag nbtData = e.getItemStack().getShareTag();
        if (nbtData != null) {
            Component nbtTooltip = Component.translatable("tooltip.jeiintegration.nbtTagData", nbtData)
                                            .withStyle(ChatFormatting.DARK_GRAY);
            e.getToolTip().add(nbtTooltip);
        }
    }
}
