/*
 * MIT License
 *
 * Copyright (c) 2017 SnowShock35
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

        int burnTime = TileEntityFurnace.getItemBurnTime(e.getItemStack());
        if (burnTime > 0) {
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

        if (Objects.equals(config.getInternalNameTooltipMode(), "enabled")) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.internalName") + " " + e.getItemStack().getItem().getRegistryName());
        } else if (Objects.equals(config.getInternalNameTooltipMode(), "onShift") && isShiftKeyDown()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.internalName") + " " + e.getItemStack().getItem().getRegistryName());
        } else if (Objects.equals(config.getInternalNameTooltipMode(), "onDebug") && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.internalName") + " " + e.getItemStack().getItem().getRegistryName());
        } else if (Objects.equals(config.getInternalNameTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.internalName") + " " + e.getItemStack().getItem().getRegistryName());
        }

        if (Objects.equals(config.getMaxStackSizeTooltipMode(), "enabled")) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + e.getItemStack().getMaxStackSize());
        } else if (Objects.equals(config.getMaxStackSizeTooltipMode(), "onShift") && isShiftKeyDown()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + e.getItemStack().getMaxStackSize());
        } else if (Objects.equals(config.getMaxStackSizeTooltipMode(), "onDebug") && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + e.getItemStack().getMaxStackSize());
        } else if (Objects.equals(config.getMaxStackSizeTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.maxStackSize") + " " + e.getItemStack().getMaxStackSize());
        }

        if (Objects.equals(config.getOreDictEntriesTooltipMode(), "enabled")) {
            genOreDictTooltip(e);
        } else if (Objects.equals(config.getOreDictEntriesTooltipMode(), "onShift") && isShiftKeyDown()) {
            genOreDictTooltip(e);
        } else if (Objects.equals(config.getOreDictEntriesTooltipMode(), "onDebug") && isDebugMode()) {
            genOreDictTooltip(e);
        } else if (Objects.equals(config.getOreDictEntriesTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
            genOreDictTooltip(e);
        }

        if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "enabled")) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + e.getItemStack().getUnlocalizedName());
        } else if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "onShift") && isShiftKeyDown()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + e.getItemStack().getUnlocalizedName());
        } else if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "onDebug") && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + e.getItemStack().getUnlocalizedName());
        } else if (Objects.equals(config.getUnlocalizedNameTooltipMode(), "onShiftAndDebug") && isShiftKeyDown() && isDebugMode()) {
            e.getToolTip().add(TextFormatting.DARK_GRAY + I18n.format("tooltip.jeiintegration.unlocalizedName") + " " + e.getItemStack().getUnlocalizedName());
        }
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

    //TODO: Fix the fluid registry tooltips

//    private static void genFluidRegTooltip(ItemTooltipEvent e) {
//        List<String> names = new ArrayList<String>();
//        if (FluidRegistry..isEmptyContainer(evt.itemStack)) {
//            names.add("  " + Utils.translate("tooltip.fluidreg.empty"));
//        } else {
//            FluidStack fluid = Utils.getFluidStack(evt.itemStack);
//            if (fluid != null) {
//                names.add("  " + fluid.getLocalizedName());
//                names.add("  " + fluid.amount + " mB");
//            }
//        }
//        if (!names.isEmpty()) {
//            evt.toolTip.add(Utils.translate("tooltip.fluidreg"));
//            evt.toolTip.addAll(names);
//        }
//    }
}
