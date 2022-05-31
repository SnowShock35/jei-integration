/*
 * MIT License
 *
 * Copyright (c) 2020 SnowShock35
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.snowshock35.jeiintegration;

import com.snowshock35.jeiintegration.config.Config;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.Level;
import org.lwjgl.glfw.GLFW;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.minecraftforge.common.ForgeHooks.getBurnTime;

public class TooltipEventHandler {

    private Config.Client config = Config.CLIENT;

    private static boolean isDebugMode() {
        return Minecraft.getInstance().options.advancedItemTooltips;
    }

    private static boolean isShiftKeyDown() {
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) ||
                InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    private void registerTooltip(ItemTooltipEvent e, Component tooltip, String configOption) {
        boolean isEnabled = false;

        if (Objects.equals(configOption, "enabled")) {
            isEnabled = true;
        } else if (
                Objects.equals(configOption, "onShift")
                        && isShiftKeyDown()
        ) {
            isEnabled = true;
        } else if (
                Objects.equals(configOption, "onDebug")
                        && isDebugMode()
        ) {
            isEnabled = true;
        } else if (
                Objects.equals(configOption, "onShiftAndDebug")
                        && isShiftKeyDown()
                        && isDebugMode()
        ) {
            isEnabled = true;
        }
        if (isEnabled) {
            e.getToolTip().add(tooltip);
        }
    }

    private void registerTooltips(ItemTooltipEvent e, Collection<Component> tooltips, String configValue) {
        for (Component tooltip : tooltips) {
            registerTooltip(e, tooltip, configValue);
        }
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent e) {

        // Set number formatting to display large numbers more clearly
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        // Retrieve the ItemStack and Item
        ItemStack itemStack = e.getItemStack();
        Item item = itemStack.getItem();

        // If item stack empty do nothing
        if (e.getItemStack().isEmpty()) {
            return;
        }

        // Tooltip - Burn Time
        int burnTime = 0;
        try {
            burnTime = getBurnTime(itemStack, RecipeType.SMELTING);
        } catch (Exception ex) {
            JEIIntegration.logger.log(Level.WARN, "):\n\nSomething went wrong!");
        }

        if (burnTime > 0) {
            Component burnTooltip = new TranslatableComponent("tooltip.jeiintegration.burnTime")
                    .append(new TextComponent(" " + decimalFormat.format(burnTime) + " "))
                    .append(new TranslatableComponent("tooltip.jeiintegration.burnTime.suffix"))
                    .withStyle(ChatFormatting.DARK_GRAY);

            registerTooltip(e, burnTooltip, config.burnTimeTooltipMode.get());
        }

        // Tooltip - Durability
        int maxDamage = itemStack.getMaxDamage();
        int currentDamage = maxDamage - itemStack.getDamageValue();
        if (maxDamage > 0) {
            Component durabilityTooltip = new TranslatableComponent("tooltip.jeiintegration.durability")
                    .append(new TextComponent(" " + currentDamage + "/" + maxDamage))
                    .withStyle(ChatFormatting.DARK_GRAY);

            registerTooltip(e, durabilityTooltip, config.durabilityTooltipMode.get());
        }

        // Tooltip - Hunger / Saturation
        if (item.isEdible() && item.getFoodProperties() != null) {
            int healVal = item.getFoodProperties().getNutrition();
            float satVal = healVal * (item.getFoodProperties().getSaturationModifier() * 2);

            Component foodTooltip = new TranslatableComponent("tooltip.jeiintegration.hunger")
                    .append(new TextComponent(" " + healVal + " "))
                    .append(new TranslatableComponent("tooltip.jeiintegration.saturation"))
                    .append(new TextComponent(" " + decimalFormat.format(satVal)))
                    .withStyle(ChatFormatting.DARK_GRAY);

            registerTooltip(e, foodTooltip, config.foodTooltipMode.get());
        }

        // Tooltip - NBT Data
        CompoundTag nbtData = item.getShareTag(itemStack);
        if (nbtData != null) {
            Component nbtTooltip = new TranslatableComponent("tooltip.jeiintegration.nbtTagData")
                    .append(new TextComponent(" " + nbtData))
                    .withStyle(ChatFormatting.DARK_GRAY);

            registerTooltip(e, nbtTooltip, config.nbtTooltipMode.get());
        }

        // Tooltip - Registry Name
        Component registryTooltip = new TranslatableComponent("tooltip.jeiintegration.registryName")
                .append(new TextComponent(" " + item.getRegistryName()))
                .withStyle(ChatFormatting.DARK_GRAY);

        registerTooltip(e, registryTooltip, config.registryNameTooltipMode.get());


        // Tooltip - Max Stack Size
        int stackSize = e.getItemStack().getMaxStackSize();
        if (stackSize > 0) {
            Component stackSizeTooltip = new TranslatableComponent("tooltip.jeiintegration.maxStackSize")
                    .append(new TextComponent(" " + itemStack.getMaxStackSize()))
                    .withStyle(ChatFormatting.DARK_GRAY);

            registerTooltip(e, stackSizeTooltip, config.maxStackSizeTooltipMode.get());
        }

        // Tooltip - Tags
        if (itemStack.getTags().toList().size() > 0) {
            Component tagsTooltip = new TranslatableComponent("tooltip.jeiintegration.tags")
                    .withStyle(ChatFormatting.DARK_GRAY);

            Set<Component> tags = new HashSet<>();

            for (ResourceLocation tag : itemStack.getTags().map(TagKey::location).toList()) {
                tags.add(new TextComponent("    " + tag).withStyle(ChatFormatting.DARK_GRAY));
            }

            registerTooltip(e, tagsTooltip, config.tagsTooltipMode.get());
            registerTooltips(e, tags, config.tagsTooltipMode.get());
        }

        // Tooltip - Translation Key
        Component translationKeyTooltip = new TranslatableComponent("tooltip.jeiintegration.translationKey")
                .append(new TextComponent(" " + itemStack.getDescriptionId()))
                .withStyle(ChatFormatting.DARK_GRAY);

        registerTooltip(e, translationKeyTooltip, config.translationKeyTooltipMode.get());
    }
}
