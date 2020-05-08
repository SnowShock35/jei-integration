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

package com.snowshock35.jeiintegration.config;

import com.snowshock35.jeiintegration.JEIIntegration;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class JEIIntegrationModConfigGui extends GuiConfig {
    public JEIIntegrationModConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), JEIIntegration.MOD_ID, false, false, I18n.format("config.jeiintegration.title"));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<IConfigElement>();

        Config config = JEIIntegration.config;
        if (config != null) {
            ConfigCategory categoryHandlers = config.getConfig().getCategory(Config.CATEGORY_HANDLERS);
            configElements.add(new ConfigElement(categoryHandlers));

            ConfigCategory categoryTooltips = config.getConfig().getCategory(Config.CATEGORY_TOOLTIPS);
            configElements.add(new ConfigElement(categoryTooltips));

            ConfigCategory categoryMiscellaneous = config.getConfig().getCategory(Config.CATEGORY_MISCELLANEOUS);
            configElements.add(new ConfigElement(categoryMiscellaneous));
        }

        return configElements;

    }
}
