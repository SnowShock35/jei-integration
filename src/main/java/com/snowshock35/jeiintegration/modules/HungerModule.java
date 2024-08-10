package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.text.DecimalFormat;

public class HungerModule implements TooltipModule {
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
            .comment(" Configure tooltip for hunger and saturation.")
            .translation("config.jeiintegration.tooltips.foodTooltipMode")
            .defineEnum("foodTooltipMode", OptionState.DISABLED);
    }

    @Override
    public void apply(ItemTooltipEvent e) {
        FoodProperties foodProperties = e.getItemStack().getFoodProperties(Minecraft.getInstance().player);
        if (e.getItemStack().getItem().isEdible() && foodProperties != null) {
            int healVal = foodProperties.getNutrition();
            float satVal = healVal * (foodProperties.getSaturationModifier() * 2);

            Component foodTooltip = Component.translatable("tooltip.jeiintegration.hunger", healVal)
                                             .append(Component.translatable("tooltip.jeiintegration.saturation", DECIMAL_FORMAT.get().format(satVal)))
                                             .withStyle(ChatFormatting.DARK_GRAY);
            e.getToolTip().add(foodTooltip);
        }

    }
}
