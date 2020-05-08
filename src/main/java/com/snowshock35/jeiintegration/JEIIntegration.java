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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = JEIIntegration.MOD_ID,
        name = JEIIntegration.MOD_NAME,
        version = JEIIntegration.MOD_VERSION,
        dependencies = "required-after:Forge@[12.18.3.2185,)",
        useMetadata = true,
        clientSideOnly = true,
        acceptedMinecraftVersions = "[1.10,)",
        canBeDeactivated = true,
        guiFactory = "com.snowshock35.jeiintegration.config.JEIIntegrationModGuiFactory",
        updateJSON = JEIIntegration.UPDATE_URL

)
public class JEIIntegration {

    public static final String MOD_ID = "jeiintegration";
    public static final String MOD_NAME = "JEI Integration";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String UPDATE_URL = "https://content.blamesnow.co.uk/mods/jeiintegration/update.json";

    public static Config config;
    public static Logger logger;

    @Mod.Instance
    public static JEIIntegration instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();

        config = new Config(e);
        MinecraftForge.EVENT_BUS.register(config);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        TooltipEventHandler tooltipEventHandler = new TooltipEventHandler();
        MinecraftForge.EVENT_BUS.register(tooltipEventHandler);
    }
}
