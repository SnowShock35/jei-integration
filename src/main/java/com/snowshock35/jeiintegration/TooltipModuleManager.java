package com.snowshock35.jeiintegration;

import com.snowshock35.jeiintegration.config.Config;
import com.snowshock35.jeiintegration.modules.*;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class TooltipModuleManager {
    private final ForgeConfigSpec spec;
    private final Config config;

    public static TooltipModuleManager create() {
        List<TooltipModule> modules = List.of(new BurnTimeModule(), new DurabilityModule(),
            new EnchantabilityModule(), new HungerModule(), new NbtDataModule(), new RegistryNameModule(),
            new MaxStackSizeModule(), new TagsModule(), new TranslationModule());

        Pair<Config, ForgeConfigSpec> specPair =
            new ForgeConfigSpec.Builder().configure(builder -> new Config(builder, modules));
        return new TooltipModuleManager(specPair.getRight(), specPair.getLeft());
    }

    private TooltipModuleManager(ForgeConfigSpec spec, Config config) {
        this.spec = spec;
        this.config = config;
        this.config.updateCache();
    }

    public ForgeConfigSpec getSpec() {
        return spec;
    }

    public Config getConfig() {
        return config;
    }

    public List<TooltipModuleHolder> getEnabledModules() {
        return config.getEnabledModules();
    }
}
