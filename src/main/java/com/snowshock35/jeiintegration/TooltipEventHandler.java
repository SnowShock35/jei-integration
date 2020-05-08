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
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TooltipEventHandler {

    private Config config = JEIIntegration.config;

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent e) {

        ItemStack itemStack = e.getItemStack();
        Item item = itemStack.getItem();

        int burnTime = TileEntityFurnace.getItemBurnTime(itemStack);
        if (burnTime > 0 && !isEmptyItemStack(e)) {
            if (Objects.equals(config.getBurnTimeTooltipMode(), "enabled")) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.burnTime") + " " + burnTime + " " + I18n.format("tooltip.jeiintegration.burnTime.suffix"));
            } else if (Objects.equals(config.getBurnTimeTooltipMode(), "onShift") && isShiftKeyDown()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.burnTime") + " " + burnTime + " " + I18n.format("tooltip.jeiintegration.burnTime.suffix"));
            } else if (Objects.equals(config.getBurnTimeTooltipMode(), "onDebug") && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.burnTime") + " " + burnTime + " " + I18n.format("tooltip.jeiintegration.burnTime.suffix"));
            } else if (Objects.equals(config.getBurnTimeTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.burnTime") + " " + burnTime + " " + I18n.format("tooltip.jeiintegration.burnTime.suffix"));
            }
        }

        int maxDamage = itemStack.getMaxDamage();
        int currentDamage = maxDamage - itemStack.getItemDamage();
        if (maxDamage > 0 && !isEmptyItemStack(e)) {
            if (Objects.equals(config.getDurabilityTooltipMode(), "enabled")) {
                e.getToolTip().add(1, TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.durability") + " " + currentDamage + "/" + maxDamage);
            } else if (Objects.equals(config.getDurabilityTooltipMode(), "onShift") && isShiftKeyDown()) {
                e.getToolTip().add(1, TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.durability") + " " + currentDamage + "/" + maxDamage);
            } else if (Objects.equals(config.getDurabilityTooltipMode(), "onDebug") && isDebugMode()) {
                e.getToolTip().add(1, TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.durability") + " " + currentDamage + "/" + maxDamage);
            } else if (Objects.equals(config.getDurabilityTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                e.getToolTip().add(1, TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.durability") + " " + currentDamage + "/" + maxDamage);
            }
        }

        int metadata = itemStack.getMetadata();
        if (!isEmptyItemStack(e)) {
            if (Objects.equals(config.getMetadataTooltipMode(), "enabled")) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.metadata") + " " + metadata);
            } else if (Objects.equals(config.getMetadataTooltipMode(), "onShift") && isShiftKeyDown()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.metadata") + " " + metadata);
            } else if (Objects.equals(config.getMetadataTooltipMode(), "onDebug") && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.metadata") + " " + metadata);
            } else if (Objects.equals(config.getMetadataTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.metadata") + " " + metadata);
            }
        }

        NBTTagCompound nbtData = item.getNBTShareTag(itemStack);
        if (item.getNBTShareTag(itemStack) != null && !isEmptyItemStack(e)) {
          if (Objects.equals(config.getNbtTooltipMode(), "enabled")) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.nbtTagData") + " " + nbtData);
          } else if (Objects.equals(config.getNbtTooltipMode(), "onShift") && isShiftKeyDown()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.nbtTagData") + " " + nbtData);
          } else if (Objects.equals(config.getNbtTooltipMode(), "onDebug") && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.nbtTagData") + " " + nbtData);
          } else if (Objects.equals(config.getNbtTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.nbtTagData") + " " + nbtData);
          }
        }

        if (!isEmptyItemStack(e)) {
            if (Objects.equals(config.getRegistryNameTooltipMode(), "enabled")) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.registryName") + " " + item.getRegistryName());
            } else if (Objects.equals(config.getRegistryNameTooltipMode(), "onShift") && isShiftKeyDown()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.registryName") + " " + item.getRegistryName());
            } else if (Objects.equals(config.getRegistryNameTooltipMode(), "onDebug") && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.registryName") + " " + item.getRegistryName());
            } else if (Objects.equals(config.getRegistryNameTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.registryName") + " " + item.getRegistryName());
            }
        }

        int stackSize = e.getItemStack().getMaxStackSize();
        if (stackSize > 0 && !isEmptyItemStack(e)) {
            if (Objects.equals(config.getMaxStackSizeTooltipMode(), "enabled")) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + itemStack.getMaxStackSize());
            } else if (Objects.equals(config.getMaxStackSizeTooltipMode(), "onShift") && isShiftKeyDown()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + itemStack.getMaxStackSize());
            } else if (Objects.equals(config.getMaxStackSizeTooltipMode(), "onDebug") && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + itemStack.getMaxStackSize());
            } else if (Objects.equals(config.getMaxStackSizeTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + itemStack.getMaxStackSize());
            }
        }

        if (!isEmptyItemStack(e)) {
            if (Objects.equals(config.getOreDictEntriesTooltipMode(), "enabled")) {
                genOreDictTooltip(e);
            } else if (Objects.equals(config.getOreDictEntriesTooltipMode(), "onShift") && isShiftKeyDown()) {
                genOreDictTooltip(e);
            } else if (Objects.equals(config.getOreDictEntriesTooltipMode(), "onDebug") && isDebugMode()) {
                genOreDictTooltip(e);
            } else if (Objects.equals(config.getOreDictEntriesTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                genOreDictTooltip(e);
            }
        }

        if (!isEmptyItemStack(e)) {
            if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "enabled")) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + itemStack.getTranslationKey());
            } else if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "onShift") && isShiftKeyDown()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + itemStack.getTranslationKey());
            } else if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "onDebug") && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + itemStack.getTranslationKey());
            } else if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
                e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + itemStack.getTranslationKey());
            }
        }
    }

    private static boolean isEmptyItemStack(ItemTooltipEvent e) {
        return e.getItemStack().isEmpty();
    }

    private static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    private static boolean isDebugMode() {
        return Minecraft.getMinecraft().gameSettings.advancedItemTooltips;
    }

    private static void genOreDictTooltip(ItemTooltipEvent e) {
        List<String> names = new ArrayList<String>();
        for (int id : OreDictionary.getOreIDs(e.getItemStack())) {
            String name = OreDictionary.getOreName(id);
            if (!names.contains(name)) {
                names.add("  " + name);
            } else {
                names.add("  " + TextFormatting.DARK_GRAY + name);
            }
        }
        Collections.sort(names);
        if (!names.isEmpty()) {
            e.getToolTip().add(I18n.format("tooltip.jeiintegration.oreDict"));
            e.getToolTip().addAll(names);
        }
    }
}
