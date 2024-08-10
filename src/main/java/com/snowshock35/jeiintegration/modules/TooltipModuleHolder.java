package com.snowshock35.jeiintegration.modules;

import com.snowshock35.jeiintegration.config.OptionState;
import net.minecraftforge.common.ForgeConfigSpec;

public record TooltipModuleHolder(TooltipModule definition, ForgeConfigSpec.ConfigValue<OptionState> config) {
}
