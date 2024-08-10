package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.JEIIntegration;
import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.apache.logging.log4j.Level;

import java.text.DecimalFormat;

import static net.minecraftforge.common.ForgeHooks.getBurnTime;

public class BurnTimeModule implements TooltipModule {
    private static final ThreadLocal<DecimalFormat> DECIMAL_FORMAT = ThreadLocal.withInitial(() -> {
        // Set number formatting to display large numbers more clearly
        DecimalFormat format = new DecimalFormat("#.##");
        format.setGroupingUsed(true);
        format.setGroupingSize(3);
        return format;
    });

    @Override
    public ForgeConfigSpec.ConfigValue<OptionState> createConfigValue(ForgeConfigSpec.Builder builder) {
        return builder
            .comment(" Configure tooltip for burn time.")
            .translation("config.jeiintegration.tooltips.burnTimeTooltipMode")
            .defineEnum("burnTimeTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        int burnTime = 0;
        try {
            burnTime = getBurnTime(e.getItemStack(), RecipeType.SMELTING);
        } catch (Exception ex) {
            JEIIntegration.logger.log(Level.WARN, "):\n\nSomething went wrong!");
        }

        if (burnTime <= 0) {
            return;
        }

        Component burnTooltip =
            Component.translatable("tooltip.jeiintegration.burnTime", DECIMAL_FORMAT.get().format(burnTime))
                     .withStyle(ChatFormatting.DARK_GRAY);
        e.getToolTip().add(burnTooltip);
    }
}