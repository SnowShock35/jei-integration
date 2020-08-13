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
import org.lwjgl.glfw.GLFW;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TooltipEventHandler {

  private Config.Client config = Config.CLIENT;

  private static boolean isDebugMode() {
    return Minecraft.getInstance().gameSettings.advancedItemTooltips;
  }

  private static boolean isShiftKeyDown() {
    return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) ||
      InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
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
    int burnTime = net.minecraftforge.common.ForgeHooks.getBurnTime(itemStack);
    if (burnTime > 0) {
      ITextComponent burnTooltip = new TranslationTextComponent("tooltip.jeiintegration.burnTime")
        .append(new StringTextComponent(" " + decimalFormat.format(burnTime) + " "))
        .append(new TranslationTextComponent("tooltip.jeiintegration.burnTime.suffix"))
        .mergeStyle(TextFormatting.DARK_GRAY);

      if (Objects.equals(config.burnTimeTooltipMode.get(), "enabled")) {
        e.getToolTip().add(burnTooltip);
      } else if (
        Objects.equals(config.burnTimeTooltipMode.get(), "onShift")
          && isShiftKeyDown()
      ) {
        e.getToolTip().add(burnTooltip);
      } else if (
        Objects.equals(config.burnTimeTooltipMode.get(), "onDebug")
          && isDebugMode()
      ) {
        e.getToolTip().add(burnTooltip);
      } else if (
        Objects.equals(config.burnTimeTooltipMode.get(), "onShiftAndDebug")
          && isShiftKeyDown()
          && isDebugMode()
      ) {
        e.getToolTip().add(burnTooltip);
      }
    }

    // Tooltip - Durability
    int maxDamage = itemStack.getMaxDamage();
    int currentDamage = maxDamage - itemStack.getDamage();
    if (maxDamage > 0) {
      ITextComponent durabilityTooltip = new TranslationTextComponent("tooltip.jeiintegration.durability")
        .append(new StringTextComponent(" " + currentDamage + "/" + maxDamage))
        .mergeStyle(TextFormatting.DARK_GRAY);

      if (Objects.equals(config.durabilityTooltipMode.get(), "enabled")) {
        e.getToolTip().add(1, durabilityTooltip);
      } else if (
        Objects.equals(config.durabilityTooltipMode.get(), "onShift")
          && isShiftKeyDown()
      ) {
        e.getToolTip().add(1, durabilityTooltip);
      } else if (
        Objects.equals(config.durabilityTooltipMode.get(), "onDebug")
          && isDebugMode()
      ) {
        e.getToolTip().add(1, durabilityTooltip);
      } else if (
        Objects.equals(config.durabilityTooltipMode.get(), "onShiftAndDebug")
          && isShiftKeyDown()
          && isDebugMode()
      ) {
        e.getToolTip().add(1, durabilityTooltip);
      }
    }

    // Tooltip - Hunger / Saturation
    if (item.isFood()) {
      int healVal = item.getFood().getHealing();
      float satVal = healVal * (item.getFood().getSaturation() * 2);

      ITextComponent foodTooltip = new TranslationTextComponent("tooltip.jeiintegration.hunger")
        .append(new StringTextComponent(" " + healVal + " "))
        .append(new TranslationTextComponent("tooltip.jeiintegration.saturation"))
        .append(new StringTextComponent(" " + satVal))
        .mergeStyle(TextFormatting.DARK_GRAY);

      if (Objects.equals(config.foodTooltipMode.get(), "enabled")) {
        e.getToolTip().add(foodTooltip);
      } else if (
        Objects.equals(config.foodTooltipMode.get(), "onShift")
          && isShiftKeyDown()
      ) {
        e.getToolTip().add(foodTooltip);
      } else if (
        Objects.equals(config.foodTooltipMode.get(), "onDebug")
          && isDebugMode()
      ) {
        e.getToolTip().add(foodTooltip);
      } else if (
        Objects.equals(config.foodTooltipMode.get(), "onShiftAndDebug")
          && isShiftKeyDown()
          && isDebugMode()
      ) {
        e.getToolTip().add(foodTooltip);
      }
    }

    // Tooltip - NBT Data
    CompoundNBT nbtData = item.getShareTag(itemStack);
    if (nbtData != null) {
      ITextComponent nbtTooltip = new TranslationTextComponent("tooltip.jeiintegration.nbtTagData")
        .append(new StringTextComponent(" " + nbtData))
        .mergeStyle(TextFormatting.DARK_GRAY);

      if (Objects.equals(config.nbtTooltipMode.get(), "enabled")) {
        e.getToolTip().add(nbtTooltip);
      } else if (
        Objects.equals(config.nbtTooltipMode.get(), "onShift")
          && isShiftKeyDown()
      ) {
        e.getToolTip().add(nbtTooltip);
      } else if (
        Objects.equals(config.nbtTooltipMode.get(), "onDebug")
          && isDebugMode()
      ) {
        e.getToolTip().add(nbtTooltip);
      } else if (
        Objects.equals(config.nbtTooltipMode.get(), "onShiftAndDebug")
          && isShiftKeyDown()
          && isDebugMode()
      ) {
        e.getToolTip().add(nbtTooltip);
      }
    }


    // Tooltip - Registry Name
    ITextComponent registryTooltip = new TranslationTextComponent("tooltip.jeiintegration.registryName")
      .append(new StringTextComponent(" " + item.getRegistryName()))
      .mergeStyle(TextFormatting.DARK_GRAY);

    if (Objects.equals(config.registryNameTooltipMode.get(), "enabled")) {
      e.getToolTip().add(registryTooltip);
    } else if (
      Objects.equals(config.registryNameTooltipMode.get(), "onShift")
        && isShiftKeyDown()
    ) {
      e.getToolTip().add(registryTooltip);
    } else if (
      Objects.equals(config.registryNameTooltipMode.get(), "onDebug")
        && isDebugMode()
    ) {
      e.getToolTip().add(registryTooltip);
    } else if (
      Objects.equals(config.registryNameTooltipMode.get(), "onShiftAndDebug")
        && isShiftKeyDown()
        && isDebugMode()
    ) {
      e.getToolTip().add(registryTooltip);
    }


    // Tooltip - Max Stack Size
    int stackSize = e.getItemStack().getMaxStackSize();
    if (stackSize > 0) {
      ITextComponent stackSizeTooltip = new TranslationTextComponent("tooltip.jeiintegration.maxStackSize")
        .append(new StringTextComponent(" " + itemStack.getMaxStackSize()))
        .mergeStyle(TextFormatting.DARK_GRAY);

      if (Objects.equals(config.maxStackSizeTooltipMode.get(), "enabled")) {
        e.getToolTip().add(stackSizeTooltip);
      } else if (
        Objects.equals(config.maxStackSizeTooltipMode.get(), "onShift")
          && isShiftKeyDown()
      ) {
        e.getToolTip().add(stackSizeTooltip);
      } else if (
        Objects.equals(config.maxStackSizeTooltipMode.get(), "onDebug")
          && isDebugMode()
      ) {
        e.getToolTip().add(stackSizeTooltip);
      } else if (
        Objects.equals(config.maxStackSizeTooltipMode.get(), "onShiftAndDebug")
          && isShiftKeyDown()
          && isDebugMode()
      ) {
        e.getToolTip().add(stackSizeTooltip);
      }
    }

    // Tooltip - Tags
    if (item.getTags().size() > 0) {
      ITextComponent tagsTooltip = new TranslationTextComponent("tooltip.jeiintegration.tags")
        .mergeStyle(TextFormatting.DARK_GRAY);

      List<ITextComponent> tags = new ArrayList<ITextComponent>();

      for (ResourceLocation tag : item.getTags()) {
        tags.add(new StringTextComponent("    " + tag).mergeStyle(TextFormatting.DARK_GRAY));
      }

      if (Objects.equals(config.tagsTooltipMode.get(), "enabled")) {
        e.getToolTip().add(tagsTooltip);
        e.getToolTip().addAll(tags);
      } else if (
        Objects.equals(config.tagsTooltipMode.get(), "onShift")
          && isShiftKeyDown()
      ) {
        e.getToolTip().add(tagsTooltip);
        e.getToolTip().addAll(tags);
      } else if (
        Objects.equals(config.tagsTooltipMode.get(), "onDebug")
          && isDebugMode()
      ) {
        e.getToolTip().add(tagsTooltip);
        e.getToolTip().addAll(tags);
      } else if (
        Objects.equals(config.tagsTooltipMode.get(), "onShiftAndDebug")
          && isShiftKeyDown()
          && isDebugMode()
      ) {
        e.getToolTip().add(tagsTooltip);
        e.getToolTip().addAll(tags);
      }
    }

    // Tooltip - Translation Key
    ITextComponent translationKeyTooltip = new TranslationTextComponent("tooltip.jeiintegration.translationKey")
      .append(new StringTextComponent(" " + itemStack.getTranslationKey()))
      .mergeStyle(TextFormatting.DARK_GRAY);

    if (Objects.equals(config.translationKeyTooltipMode.get(), "enabled")) {
      e.getToolTip().add(translationKeyTooltip);
    } else if (
      Objects.equals(config.translationKeyTooltipMode.get(), "onShift")
        && isShiftKeyDown()
    ) {
      e.getToolTip().add(translationKeyTooltip);
    } else if (
      Objects.equals(config.translationKeyTooltipMode.get(), "onDebug")
        && isDebugMode()
    ) {
      e.getToolTip().add(translationKeyTooltip);
    } else if (
      Objects.equals(config.translationKeyTooltipMode.get(), "onShiftAndDebug")
        && isShiftKeyDown()
        && isDebugMode()
    ) {
      e.getToolTip().add(translationKeyTooltip);
    }
  }
}
