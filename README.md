[![JEI Integration Logo](https://cdn.snowshock35.com/mods/jei-integration/logo_horizontal_dark.png)](https://curseforge.com/minecraft/mc-mods/jei-integration)

# JEI Integration - Minecraft Mod

Providing Integrations & Tooltips

[![Minecraft CurseForge Downloads (Total)](https://cf.way2muchnoise.eu/full_265917_downloads.svg?badge_style=for_the_badge)](https://curseforge.com/minecraft/mc-mods/jei-integration)
[![Minecraft CurseForge Minecraft Versions](https://cf.way2muchnoise.eu/versions/265917.svg?badge_style=for_the_badge)](https://curseforge.com/minecraft/mc-mods/jei-integration)
[![Build Status](https://img.shields.io/travis/snowshock35/jei-integration/master?style=for-the-badge)](https://travis-ci.com/snowshock35/jei-integration)
[![LICENSE](https://img.shields.io/github/license/snowshock35/jei-integration?style=for-the-badge)](https://github.com/snowshock35/jei-integration/blob/master/LICENSE)
[![Discord](https://img.shields.io/discord/284709326189494282?color=7289da&label=Discord&style=for-the-badge)](https://discord.gg/H4FUqrj)

## Description

### Introduction

JEI Integration, the successor to [NEI Integration by Tonius](https://curseforge.com/minecraft/mc-mods/nei-integration), is an addon for Just Enough Items (JEI) for Minecraft 1.10 and above. The mod provides JEI recipe handlers for other mods where otherwise absent. In addition to additional recipe handlers, it adds configurable tooltips which can provide insightful information for pack developers and tech-savvy players.

JEI Integration is still in the development phase meaning some features of the original mod are not present. The configurable tooltips is currently the only implemented feature of the original mod. More coming soon™...

### Tooltips

Tooltips are extremely helpful to pack developers, mod developers and players for providing the player with helpful insights about a particular block/item's traits. By default JEI Integration tooltips come disabled as to allow you, the player or pack maker the control over what is shown and how.

The list of available tooltips is as follows:

#### 1.15

- **Burn Time** - Displays the number of ticks (20 ticks = 1 second) any burnable item in game will burn for.
- **Durability** - Displays the durability of an item where applicable (e.g. tools, weapons, armour, etc). The tooltip shows both total durability and remaining durability.
- **Hunger/Saturation** - Displays both the hunger and saturation values that the particular food item will restore. (As of version 3.1.0)
- **Max Stack Size** - Displays the maximum stack size of an item - usually 64 but in some cases, like ender pearls, it's 16.
- **NBT Data** - Displays all NBT data stored on a block/item e.g. enchantments.
- **Registry Name** - Displays the block/item's internal ID, registry name or namespace name (e.g. minecraft:apple). While already available in the game (using `f3+h`), JEI Integration gives players more options as to how it appears in the tooltip.
- **Translation Key** - Displays the translation key for a given block/item. E.g. block.minecraft.stone (Useful for mod translators)

#### 1.14

- **Burn Time** - Displays the number of ticks (20 ticks = 1 second) any burnable item in game will burn for.
- **Durability** - Displays the durability of an item where applicable (e.g. tools, weapons, armour, etc). The tooltip shows both total durability and remaining durability.
- **Max Stack Size** - Displays the maximum stack size of an item - usually 64 but in some cases, like ender pearls, it's 16.
- **NBT Data** - Displays all NBT data stored on a block/item e.g. enchantments.
- **Registry Name** - Displays the block/item's internal ID, registry name or namespace name (e.g. minecraft:apple). While already available in the game (using `f3+h`), JEI Integration gives players more options as to how it appears in the tooltip.
- **Translation Key** - Displays the translation key for a given block/item. E.g. block.minecraft.stone (Useful for mod translators)

#### 1.12 and below

- **Burn Time** - Displays the number of ticks (20 ticks = 1 second) any burnable item in game will burn for.
- **Durability** - Displays the durability of an item where applicable (e.g. tools, weapons, armour, etc). The tooltip shows both total durability and remaining durability.
- **Max Stack Size** - Displays the maximum stack size of an item - usually 64 but in some cases, like ender pearls, it's 16.
- **Metadata** - Displays an block/item's metadata e.g. orange wool has metadata 1.
- **NBT Data** - Displays all NBT data stored on a block/item e.g. enchantments.
- **Ore Dictionary Entries** - Displays all ore dictionary entries for a given block/item e.g. logWood
- **Registry Name** - Displays the block/item's internal ID, registry name or namespace name (e.g. minecraft:apple). While already available in the game (using `f3+h`), JEI Integration gives players more options as to how it appears in the tooltip.
- **Translation Key** - Displays the translation key for a given block/item. E.g. block.minecraft.stone (Useful for mod translators)

### Configuration

JEI Integration offers a great deal of customisation to the player in the form of configurations. Configurations can be altered via the in-game menu or the configuration file (Stored in `./minecraft/config`).

The mod provides the following options for when a tooltip is displayed for an item:

- **Disabled** - The tooltip is not displayed.
- **Enabled** - The tooltip is displayed all the time.
- **On Shift** - The tooltip is only displayed while the user is holding the left or right shift key on the keyboard.
- **On Debug** - The tooltip is only displayed while the `advancedItemTooltips` option is enabled (`f3+h`).
- **On Shift & Debug** - The tooltip is only displayed while the previous two conditions are true (On Shift & On Debug).

## FAQ

### Can you make this for Minecraft version x?

JEI Integration supports Minecraft 1.10+ that Forge has support for. It will not support snapshots or older versions of the game e.g. 1.7.10.

### Can you make JEI Integration for the Fabric mod loader?

JEI Integration is, and always will, be made for Forge.

### Should I download your mod from other websites?

JEI Integration downloads are only available on CurseForge. Downloads found elsewhere are not legitimate and are most likely dangerous to use.

### Can I use your mod in my modpack?

Of course! Put me in all the packs.

### When are we going to get new features?

Soon™

### I found a bug or want to request a feature, where do I do it?

GitHub. Links at the top of this page

### Why?

Because.

## Release Notes

Release Notes (or changelogs) for JEI Integration are automatically generated using Git commit messages and release notes are coupled with each release found under the [files tab](https://curseforge.com/minecraft/mc-mods/jei-integration/files).

## Join our Discord

Our Discord community is a great place to chat with others and get insight on SnowShock's latest mods and packs. [Join our Discord Server](https://discord.gg/H4FUqrj).

[![Discord Server](https://cdn.snowshock35.com/misc/discord-logo_wordmark_black_sm.png)](https://discord.gg/H4FUqrj)

## Support me on Patreon

Want to support me? Consider becoming a patreon supporter by clicking the banner below.

[![Support me on Patreon](https://cdn.snowshock35.com/misc/patreon_wordmark_black_sm.png)](https://patreon.com/snowshock35)

## Server Hosting

Thanks to the wonderful people at CreeperHost, you can receive **15% off** your first month hosting. Just click the banner below or use the code `snowshock35xbe` to receive your 1 month discount.

[![CreeperHost Discount](https://cdn.snowshock35.com/misc/ch_snowshock35xbe.png)](http://partners.creeper.host/r/snowshock35xbe)

## Credits

- **[SnowShock35](https://linktr.ee/snowshock35)** - Project Owner and Lead Developer
- **[Mrbysco](https://curseforge.com/members/mrbysco/projects)** - Ports to 1.14 and 1.15 in my absence
- **[Tonius](https://curseforge.com/members/tonius11/projects)** - For NEI Integration (inspiring this mod)

---

&copy; 2020 SnowShock35 - Code released under MIT license.
