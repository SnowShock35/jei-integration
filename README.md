[![JEI Integration Logo](https://content.blamesnow.co.uk/mods/jeiintegration/1024_1024.png)](https://minecraft.curseforce.com/projects/jei-integration/)
# JEI Integration - Minecraft Mod
_Providing Integrations & Tooltips_

[![Build Status](https://img.shields.io/travis/SnowShock35/JEIIntegration/master.svg?style=flat-square)](https://travis-ci.org/SnowShock35/JEIIntegration) [![license](https://img.shields.io/github/license/SnowShock35/JEIIntegration.svg?style=flat-square)](https://github.com/SnowShock35/JEIIntegration/blob/master/LICENSE) [![Minecraft CurseForge Minecraft Versions](http://cf.way2muchnoise.eu/versions/jei-integration.svg)](https://minecraft.curseforge.com/projects/jei-integration/)
## Description
#### Introduction

JEI Integrations is an addon for Just Enough Items (JEI) that was originally called NEI Integration and was maintained by Tonius. However, since his absence the mod has yet to have been updated to support later versions of Minecraft. So, here it is. The mod adds helpful, highly-configurable tooltips, additional JEI recipe handlers for mods that yet to have native support and a few other helpful tools.

#### Key Features

JEI Integration is an ever growing mod adding various helpful tools for the user. However, here is a small list to highlight it's key features available to use...

* Configurable Tooltips for debug and useful information
* Additional JEI handlers for mods that have yet to add there own
* Data dumping various information like biome, potion and dimension id's

#### Tooltips

Tooltips are one of the main features of JEI Integration. They provide useful information to the user in the form of a tooltip (obvious, I know). This tooltip is displayed in addition to the item name when hovering over an item in game. There are various types of information that will be displayed. Below I've detailed what each of them are.

**Burn Time** adds a tooltip value to any burnable material in the game giving you the time it burns for in ticks (20 ticks = 1 second). This also tells you whether an item is burnable or not so it can be handy for knowing that too since there are a lot more items in modded minecraft.

~~**Fluid Registry Information** adds a tooltip value to any fluid in the game giving you specific details about said fluid like it's temperature (in kelvin), viscosity, luminosity and density.~~

**Internal Name** adds a tooltip to every item stack in the game telling you the item's registry name. This tooltips is already available using the `advancedItemTooltip` option however, you can configure this one more precisely to your preference.

**Max Stack Size** is a fairly straight forward tooltip telling you the maximum stack size of the current item stack you're hovering over. Self explanatory, right?

**Ore Dictionary Entries** is one of the more complex tooltips spanning across multiple lines. It lists all of the current item stacks ore dictionary entries if it should have any. e.g. Iron Ingot will display ingotIron`.

**Unlocalized Name** adds a tooltip to every item stack in the game telling you the item's unlocalized name. This good when doing any sort of development or debugging should you need it.

#### Configuration

One of this mods best assets is the high configurability that it offers. Allowing you to control precisely how you want the mod to work in your modded Minecraft environment. 

You're able to precisely control the tooltips, handlers and any other miscellaneous options the mod has to offer. These can all be configured from within the configuration file located at `./minecraft/config/jeiintegration.cfg` or more easily via the in-game configuration menu accessible through the mods button on the main menu.

Tooltips themselves can be configured with various modes to suit your taste. Currently there are four modes they're...
                    
* **Disabled** - The tooltip is not displayed
* **Enabled** - The tooltip is displayed
* **On Shift** - The tooltip is only displayed while the user is holding the left or right shift key on the keyboard
* **On Debug** - The tooltip is only displayed while the `advancedItemTooltips` option is enabled (F3+H)
* **On Shift & Debug** - The tooltip is only displayed while the previous two conditions are true (On Shift & On Debug)

> **Disclaimer:** By default all parts of this mod are disabled. To enable them please use the in game config GUI by clicking Mods button (on the main menu), selecting JEI Integrations then clicking the config button.

***

## Server Hosting

_Did you know that we're now partnered with [CreeperHost](https://creeper.host)?_

Thanks to the wonderful people of [CreeperHost](https://creeper.host), you can now purchase a server from them with the following discount code, getting 10% off of your first month. Great for hosting the most up to date version of our mod packs. Click the banner below to get your server.

[![CreeperHost Discount](https://content.blamesnow.co.uk/CREEPER_HOST.png)](http://partners.creeper.host/r/snowshock35xbe)

***

## Twitch Community

_Wanting to get involved? Join the conversation._

Our Twitch community is a place to socialize with both our developers as well as other community members such as yourself. You'll get to see and join in with the conversation. Oh, and did we mention that we hold occasional giveaways and polls? That's right! The community is free to join and you can do so by clicking the banner below or clicking the following link to [Join the Conversation](https://invite.twitch.tv/RordanShovelTooth).

[![Twitch Community](https://content.blamesnow.co.uk/Twitch_Large.jpeg)](https://invite.twitch.tv/RordanShovelTooth)

***

## Installation | Issues & Issue Reporting
### Installation

**_Information regarding pack installation_**

This mod can be installed via the Twitch Desktop App. It's available for download at [https://app.twitch.tv/overview/](https://app.twitch.tv/overview/). If you need assistants installing mods there's a help guide available [here](https://help.twitch.tv/customer/en/portal/articles/2744534-game-profiles#AddingMods).

### Issues & Issue Reporting

**_If you encounter crashes, bugs or want to make a suggestion, please make sure to do so by clicking on the `Issues` tab at the top of the page or by clicking the following link to [report an issue](https://github.com/snowshock35/jeiintegration/issues/)_**

If you report an issue please make sure to follow the guidelines listed on the repository. [Contributing Guidelines](https://github.com/snowshock35/jeiintegration/blob/master/.github/CONTRIBUTING.md).

***

## Useful Links

_Make sure to follow our lead developer, SnowShock35, on social media for the latest updates and news about mods and other exciting projects._

* [CurseForge Projects](https://minecraft.curseforge.com/members/snowshock35/projects)
* [GitHub](https://github.com/SnowShock35)
* [Player.me](https://player.me/SnowShock35)
* [Reddit](https://reddit.com/u/SnowShock35)
* [Twitch](https://twitch.tv/SnowShock35)
* [Twitter](https://twitter.com/SnowShock35)
* [Website](https://SnowShock35.com)
* [YouTube](https://youtube.com/user/XSnowShockX35)

***

## Changelogs

Changelogs can be found on the Minecraft CurseForge projects page. To see a list of them click the following link. [View all changelogs](https://minecraft.curseforge.com/projects/jei-integration/files/)

***

## Credits

* [SnowShock35](https://twitter.com/SnowShock35) - Project Owner, Author, Artist
* [Tonius](https://twitter.com/ToniusMods) - Former Author

**Copyright © 2017 SnowShock35**

***