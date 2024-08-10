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
import com.snowshock35.jeiintegration.modules.TooltipModule;
import com.snowshock35.jeiintegration.modules.TooltipModuleHolder;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Config {
    private static final String CATEGORY_HANDLERS = "Handler Settings";
    private static final String CATEGORY_TOOLTIPS = "Tooltip Settings";
    private static final String CATEGORY_MISCELLANEOUS = "Miscellaneous Settings";

    private final List<TooltipModuleHolder> modules = new ArrayList<>();
    private final List<TooltipModuleHolder> enabledModules = new ArrayList<>();

    public Config(ForgeConfigSpec.Builder builder, List<TooltipModule> modules) {
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
                   "disabled, enabled, on_shift, on_debug or on_shift_and_debug")
               .push("tooltip_options");

        for (TooltipModule module : modules) {
            TooltipModuleHolder moduleHolder = new TooltipModuleHolder(module, module.createConfigValue(builder));
            this.modules.add(moduleHolder);
        }

        builder.pop();
    }

    public List<TooltipModuleHolder> getModules() {
        return Collections.unmodifiableList(modules);
    }

    public List<TooltipModuleHolder> getEnabledModules() {
        return Collections.unmodifiableList(enabledModules);
    }

    public void updateCache() {
        enabledModules.clear();
        for (TooltipModuleHolder module : modules) {
            if (module.config().get() != OptionState.DISABLED) {
                enabledModules.add(module);
            }
        }
    }

    @SubscribeEvent
    public void onLoad(final ModConfigEvent.Loading configEvent) {
        JEIIntegration.logger.debug("Loaded JEI Integration config file {}", configEvent.getConfig().getFileName());
        updateCache();
    }

    @SubscribeEvent
    public void onFileChange(final ModConfigEvent.Reloading configEvent) {
        JEIIntegration.logger.debug("JEI Integration config just got changed on the file system!");
        updateCache();
    }
}