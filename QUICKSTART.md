# Quick Start Guide

This guide will help you get the Estonic Autofishing mod running in just a few minutes.

## For Players (Using the Mod)

### Step 1: Install Prerequisites

1. **Install Minecraft Java Edition 1.21**
   - You need the official Minecraft Java Edition
   - Version 1.21 specifically

2. **Install Fabric Loader**
   - Go to https://fabricmc.net/use/
   - Download the Fabric installer for Minecraft 1.21
   - Run the installer and select "Install client"

3. **Download Fabric API**
   - Visit https://modrinth.com/mod/fabric-api/versions
   - Download version 0.100.1+1.21 or later for Minecraft 1.21
   - Save it to your `.minecraft/mods` folder

### Step 2: Install the Mod

1. **Locate your Minecraft folder**
   - Windows: `%appdata%\.minecraft`
   - macOS: `~/Library/Application Support/minecraft`
   - Linux: `~/.minecraft`

2. **Copy the mod file**
   - Copy `estonic-autofishing-1.0.0.jar` to the `mods` folder
   - If the `mods` folder doesn't exist, create it

3. **Launch Minecraft**
   - Open the Minecraft Launcher
   - Select the "fabric-loader-1.21" profile
   - Click "Play"

### Step 3: Verify Installation

1. In the Minecraft main menu, click "Mods"
2. Look for "Estonic Autofishing" in the list
3. If you see it, the mod is installed correctly!

### Step 4: Use the Mod

1. **Join a world or server**
   - Single-player or multiplayer works
   - Note: Some servers may not allow automation mods

2. **Start fishing**
   - Find a body of water
   - Equip a fishing rod (main hand or off-hand)
   - Cast the rod (right-click)

3. **Let the mod work**
   - When you get a bite, the text "Reel it in!" will appear
   - The mod will automatically:
     - Pull in the fishing rod
     - Slightly adjust your crosshair (to look natural)
     - Cast the rod again

4. **Enjoy automated fishing!**
   - Just stand near water with your fishing rod
   - The mod handles the rest

## For Developers (Building the Mod)

### Quick Build

```bash
# Clone the repository
git clone https://github.com/Jonas1903/Estonic-Autofishing.git
cd Estonic-Autofishing

# Build the mod (first time may take a while)
./gradlew build

# The mod JAR will be at:
# build/libs/estonic-autofishing-1.0.0.jar
```

### Run in Development Mode

```bash
# Run Minecraft with the mod loaded
./gradlew runClient
```

For detailed development setup, see [SETUP.md](SETUP.md).

## Troubleshooting

### Problem: Mod doesn't appear in Mods menu

**Solutions:**
- Ensure you're using the Fabric profile in the launcher
- Check that Fabric Loader is installed for version 1.21
- Verify the mod file is in the `mods` folder
- Make sure you have Fabric API installed

### Problem: Minecraft crashes on startup

**Solutions:**
- Check you have Java 21 or higher installed
- Ensure Fabric API version matches Minecraft 1.21
- Remove other mods to test for conflicts
- Check the crash log in `logs/latest.log`

### Problem: Mod doesn't auto-fish

**Solutions:**
- Make sure you're holding a fishing rod
- Check that the bobber is in water
- Look for the "Reel it in!" text (yellow)
- Check the log file for detection messages

### Problem: Server kicks me for automation

**Solutions:**
- This mod may be detected by anti-cheat plugins
- Only use on servers that allow automation mods
- Consider using in single-player only
- Check server rules before using

## Tips and Best Practices

1. **AFK Fishing**
   - Stand safely on land near water
   - Ensure you won't be attacked by mobs
   - Consider using in a protected area

2. **Performance**
   - The mod is lightweight and shouldn't affect FPS
   - If you experience lag, try closing other programs

3. **Compatibility**
   - Works with most Fabric mods
   - May conflict with other fishing mods
   - Tested with vanilla fishing mechanics

4. **Server Use**
   - Always check server rules first
   - Some servers ban automation mods
   - Use responsibly

## Need More Help?

- **Full documentation**: See [README.md](README.md)
- **Development guide**: See [SETUP.md](SETUP.md)
- **Report issues**: https://github.com/Jonas1903/Estonic-Autofishing/issues
- **Fabric support**: https://fabricmc.net/wiki/

## Version Information

- **Mod Version**: 1.0.0
- **Minecraft Version**: 1.21
- **Fabric Loader**: 0.15.11+
- **Fabric API**: 0.100.1+1.21
- **Java Version**: 21+

---

**Enjoy automated fishing!** 🎣
