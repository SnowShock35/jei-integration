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
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.Level;
import org.lwjgl.glfw.GLFW;

import java.text.DecimalFormat;
import java.util.*;

public class TooltipEventHandler {

    private Config.Client config = Config.CLIENT;

    private static boolean isDebugMode() {
        return Minecraft.getInstance().gameSettings.advancedItemTooltips;
    }

    private static boolean isShiftKeyDown() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) ||
                InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    private void registerTooltip(ItemTooltipEvent e, ITextComponent tooltip, String configOption) {
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

    private void registerTooltips(ItemTooltipEvent e, Collection<ITextComponent> tooltips, String configValue) {
        for (ITextComponent tooltip : tooltips) {
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
            burnTime = net.minecraftforge.common.ForgeHooks.getBurnTime(itemStack);
        } catch (Exception ex) {
            JEIIntegration.logger.log(Level.WARN, "):\n\nSomething went wrong!");
        }

        if (burnTime > 0) {
            ITextComponent burnTooltip = new TranslationTextComponent("tooltip.jeiintegration.burnTime")
                    .append(new StringTextComponent(" " + decimalFormat.format(burnTime) + " "))
                    .append(new TranslationTextComponent("tooltip.jeiintegration.burnTime.suffix"))
                    .mergeStyle(TextFormatting.DARK_GRAY);

            registerTooltip(e, burnTooltip, config.burnTimeTooltipMode.get());
        }

        // Tooltip - Durability
        int maxDamage = itemStack.getMaxDamage();
        int currentDamage = maxDamage - itemStack.getDamage();
        if (maxDamage > 0) {
            ITextComponent durabilityTooltip = new TranslationTextComponent("tooltip.jeiintegration.durability")
                    .append(new StringTextComponent(" " + currentDamage + "/" + maxDamage))
                    .mergeStyle(TextFormatting.DARK_GRAY);

            registerTooltip(e, durabilityTooltip, config.durabilityTooltipMode.get());
        }

        // Tooltip - Hunger / Saturation
        if (item.isFood()) {
            int healVal = Objects.requireNonNull(item.getFood()).getHealing();
            float satVal = healVal * (item.getFood().getSaturation() * 2);

            ITextComponent foodTooltip = new TranslationTextComponent("tooltip.jeiintegration.hunger")
                    .append(new StringTextComponent(" " + healVal + " "))
                    .append(new TranslationTextComponent("tooltip.jeiintegration.saturation"))
                    .append(new StringTextComponent(" " + decimalFormat.format(satVal)))
                    .mergeStyle(TextFormatting.DARK_GRAY);

            registerTooltip(e, foodTooltip, config.foodTooltipMode.get());
        }

        // Tooltip - NBT Data
        CompoundNBT nbtData = item.getShareTag(itemStack);
        if (nbtData != null) {
            ITextComponent nbtTooltip = new TranslationTextComponent("tooltip.jeiintegration.nbtTagData")
                    .append(new StringTextComponent(" " + nbtData))
                    .mergeStyle(TextFormatting.DARK_GRAY);

            registerTooltip(e, nbtTooltip, config.nbtTooltipMode.get());
        }

        // Tooltip - Registry Name
        ITextComponent registryTooltip = new TranslationTextComponent("tooltip.jeiintegration.registryName")
                .append(new StringTextComponent(" " + item.getRegistryName()))
                .mergeStyle(TextFormatting.DARK_GRAY);

        registerTooltip(e, registryTooltip, config.registryNameTooltipMode.get());


        // Tooltip - Max Stack Size
        int stackSize = e.getItemStack().getMaxStackSize();
        if (stackSize > 0) {
            ITextComponent stackSizeTooltip = new TranslationTextComponent("tooltip.jeiintegration.maxStackSize")
                    .append(new StringTextComponent(" " + itemStack.getMaxStackSize()))
                    .mergeStyle(TextFormatting.DARK_GRAY);

            registerTooltip(e, stackSizeTooltip, config.maxStackSizeTooltipMode.get());
        }

        // Tooltip - Tags
        if (item.getTags().size() > 0) {
            ITextComponent tagsTooltip = new TranslationTextComponent("tooltip.jeiintegration.tags")
                    .mergeStyle(TextFormatting.DARK_GRAY);

            Set<ITextComponent> tags = new HashSet<ITextComponent>();

            for (ResourceLocation tag : item.getTags()) {
                tags.add(new StringTextComponent("    " + tag).mergeStyle(TextFormatting.DARK_GRAY));
            }

            registerTooltip(e, tagsTooltip, config.tagsTooltipMode.get());
            registerTooltips(e, tags, config.tagsTooltipMode.get());
        }

        // Tooltip - Translation Key
        ITextComponent translationKeyTooltip = new TranslationTextComponent("tooltip.jeiintegration.translationKey")
                .append(new StringTextComponent(" " + itemStack.getTranslationKey()))
                .mergeStyle(TextFormatting.DARK_GRAY);

        registerTooltip(e, translationKeyTooltip, config.translationKeyTooltipMode.get());
    }
}
