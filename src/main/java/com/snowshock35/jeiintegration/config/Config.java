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
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

public class Config {
  static final String CATEGORY_HANDLERS = "Handler Settings";
  static final String CATEGORY_TOOLTIPS = "Tooltip Settings";
  static final String CATEGORY_MISCELLANEOUS = "Miscellaneous Settings";

  private static final String defaultBurnTimeTooltipMode = "disabled";
  private static final String defaultDurabilityTooltipMode = "disabled";
  private static final String defaultFoodTooltipMode = "disabled";
  private static final String defaultMaxStackSizeTooltipMode = "disabled";
  private static final String defaultNbtTooltipMode = "disabled";
  private static final String defaultRegistryNameTooltipMode = "disabled";
  private static final String defaultTagsTooltipMode = "disabled";
  private static final String defaultTranslationKeyTooltipMode = "disabled";

  private static final List<String> validOptions = List.of(
    "disabled", "enabled", "onShift", "onDebug", "onShiftAndDebug"
  );

  public static class Client {
    public final ConfigValue<String> burnTimeTooltipMode;
    public final ConfigValue<String> durabilityTooltipMode;
    public final ConfigValue<String> foodTooltipMode;
    public final ConfigValue<String> maxStackSizeTooltipMode;
    public final ConfigValue<String> nbtTooltipMode;
    public final ConfigValue<String> registryNameTooltipMode;
    public final ConfigValue<String> tagsTooltipMode;
    public final ConfigValue<String> translationKeyTooltipMode;

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
        .comment(" Configure the options below to one of the following: " +
          "disabled, enabled, onShift, onDebug or onShiftAndDebug")
        .push("tooltip_options");

      burnTimeTooltipMode = builder
        .comment(" Configure tooltip for burn time.")
        .translation("config.jeiintegration.tooltips.burnTimeTooltipMode")
        .define("burnTimeTooltipMode", defaultBurnTimeTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      durabilityTooltipMode = builder
        .comment(" Configure tooltip for durability.")
        .translation("config.jeiintegration.tooltips.durabilityTooltipMode")
        .define("durabilityTooltipMode", defaultDurabilityTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      foodTooltipMode = builder
        .comment(" Configure tooltip for hunger and saturation.")
        .translation("config.jeiintegration.tooltips.foodTooltipMode")
        .define("foodTooltipMode", defaultFoodTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      maxStackSizeTooltipMode = builder
        .comment(" Configure tooltip for max stack size.")
        .translation("config.jeiintegration.tooltips.maxStackSizeTooltipMode")
        .define("maxStackSizeTooltipMode", defaultMaxStackSizeTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      nbtTooltipMode = builder
        .comment(" Configure tooltip for NBT data.")
        .translation("config.jeiintegration.tooltips.nbtTooltipMode")
        .define("nbtTooltipMode", defaultNbtTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      registryNameTooltipMode = builder
        .comment(" Configure tooltip for registry name. E.g. minecraft:stone")
        .translation("config.jeiintegration.tooltips.registryNameTooltipMode")
        .define("registryNameTooltipMode", defaultRegistryNameTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      tagsTooltipMode = builder
        .comment(" Configure tooltip for tags. E.g. forge:ingot, minecraft:planks")
        .translation("config.jeiintegration.tooltips.tagsTooltipMode")
        .define("tagsTooltipMode", defaultTagsTooltipMode, o -> o instanceof String string && validOptions.contains(string));

      translationKeyTooltipMode = builder
        .comment(" Configure tooltip for translation key. E.g. block.minecraft.stone")
        .translation("config.jeiintegration.tooltips.translationKeyTooltipMode")
        .define("translationKeyTooltipMode", defaultTranslationKeyTooltipMode, o -> o instanceof String string && validOptions.contains(string));

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
    JEIIntegration.logger.debug("Loaded JEI Integration config file {}", configEvent.getConfig().getFileName());
  }

  @SubscribeEvent
  public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    JEIIntegration.logger.debug("JEI Integration config just got changed on the file system!");
  }
}