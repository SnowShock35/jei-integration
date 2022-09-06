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

package com.snowshock35.jeiintegration.config;

import com.snowshock35.jeiintegration.JEIIntegration;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
  static final String CATEGORY_HANDLERS = "Handler Settings";
  static final String CATEGORY_TOOLTIPS = "Tooltip Settings";
  static final String CATEGORY_MISCELLANEOUS = "Miscellaneous Settings";
  
  public enum Mode {
    DISABLED, ENABLED, ON_SHIFT, ON_DEBUG, ON_SHIFT_AND_DEBUG
  }

  private static final Mode defaultBurnTimeTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultDurabilityTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultEnchantabilityTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultFoodTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultMaxStackSizeTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultNbtTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultRegistryNameTooltipMode = Mode.DISABLED;
  private static final Mode defaultTagsTooltipMode = Mode.ON_DEBUG;
  private static final Mode defaultTranslationKeyTooltipMode = Mode.ON_DEBUG;

  public static class Client {
    public final EnumValue<Mode> burnTimeTooltipMode;
    public final EnumValue<Mode> durabilityTooltipMode;
    public final EnumValue<Mode> enchantabilityTooltipMode;
    public final EnumValue<Mode> foodTooltipMode;
    public final EnumValue<Mode> maxStackSizeTooltipMode;
    public final EnumValue<Mode> nbtTooltipMode;
    public final EnumValue<Mode> registryNameTooltipMode;
    public final EnumValue<Mode> tagsTooltipMode;
    public final EnumValue<Mode> translationKeyTooltipMode;

    Client(ForgeConfigSpec.Builder builder) {
      builder.comment(CATEGORY_HANDLERS)
        .comment(" Handler Options")
        .push("handler_options");

      builder.pop();

      builder.comment(CATEGORY_MISCELLANEOUS)
        .comment(" Miscellaneous Options")
        .push("misc_options");

      builder.pop();

      builder.comment(CATEGORY_TOOLTIPS)
        .comment(" Tooltip Options")
        .push("tooltip_options");

      burnTimeTooltipMode = builder
        .comment(" Configure tooltip for burn time.")
        .translation("config.jeiintegration.tooltips.burnTimeTooltipMode")
        .defineEnum("burnTimeTooltipMode", defaultBurnTimeTooltipMode);

      durabilityTooltipMode = builder
        .comment(" Configure tooltip for durability.")
        .translation("config.jeiintegration.tooltips.durabilityTooltipMode")
        .defineEnum("durabilityTooltipMode", defaultDurabilityTooltipMode);

      enchantabilityTooltipMode = builder
        .comment(" Configure tooltip for enchantability")
        .translation("config.jeiintegration.tooltips.enchantabilityTooltipMode")
        .defineEnum("enchantabilityTooltipMode", defaultEnchantabilityTooltipMode);

      foodTooltipMode = builder
        .comment(" Configure tooltip for hunger and saturation.")
        .translation("config.jeiintegration.tooltips.foodTooltipMode")
        .defineEnum("foodTooltipMode", defaultFoodTooltipMode);

      maxStackSizeTooltipMode = builder
        .comment(" Configure tooltip for max stack size.")
        .translation("config.jeiintegration.tooltips.maxStackSizeTooltipMode")
        .defineEnum("maxStackSizeTooltipMode", defaultMaxStackSizeTooltipMode);

      nbtTooltipMode = builder
        .comment(" Configure tooltip for NBT data.")
        .translation("config.jeiintegration.tooltips.nbtTooltipMode")
        .defineEnum("nbtTooltipMode", defaultNbtTooltipMode);

      registryNameTooltipMode = builder
        .comment(" Configure tooltip for registry name. E.g. minecraft:stone")
        .translation("config.jeiintegration.tooltips.registryNameTooltipMode")
        .defineEnum("registryNameTooltipMode", defaultRegistryNameTooltipMode);

      tagsTooltipMode = builder
        .comment(" Configure tooltip for tags. E.g. forge:ingot, minecraft:planks")
        .translation("config.jeiintegration.tooltips.tagsTooltipMode")
        .defineEnum("tagsTooltipMode", defaultTagsTooltipMode);

      translationKeyTooltipMode = builder
        .comment(" Configure tooltip for translation key. E.g. block.minecraft.stone")
        .translation("config.jeiintegration.tooltips.translationKeyTooltipMode")
        .defineEnum("translationKeyTooltipMode", defaultTranslationKeyTooltipMode);

      builder.pop();
    }
  }

  public static final ForgeConfigSpec clientSpec;
  public static final Config.Client CLIENT;
  static {
    final Pair<Config.Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config.Client::new);
    clientSpec = specPair.getRight();
    CLIENT = specPair.getLeft();
  }

  @SubscribeEvent
  public static void onLoad(final ModConfigEvent.Loading configEvent) {
    JEIIntegration.LOGGER.debug("Loaded JEI Integration config file {}", configEvent.getConfig().getFileName());
  }

  @SubscribeEvent
  public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    JEIIntegration.LOGGER.debug("JEI Integration config just got changed on the file system!");
  }
}