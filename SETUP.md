# Development Setup Guide

## Prerequisites

1. **Java Development Kit (JDK) 21 or higher**
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [AdoptOpenJDK](https://adoptopenjdk.net/)
   - Verify installation: `java -version`

2. **IntelliJ IDEA** (recommended) or any Java IDE
   - Download Community Edition from [JetBrains](https://www.jetbrains.com/idea/download/)

## Setting Up the Development Environment

### Method 1: IntelliJ IDEA (Recommended)

1. **Open the Project**
   ```
   File → Open → Select the Estonic-Autofishing directory
   ```

2. **Wait for Gradle Sync**
   - IntelliJ will automatically detect the Gradle project
   - Wait for dependencies to download (first time may take a while)

3. **Configure JDK**
   ```
   File → Project Structure → Project Settings → Project
   - SDK: Select Java 21 or higher
   - Language level: 21
   ```

4. **Run the Mod in Development**
   - Open the Gradle tool window (View → Tool Windows → Gradle)
   - Navigate to: Tasks → fabric → runClient
   - Double-click to run
   - Minecraft will launch with the mod loaded

### Method 2: Command Line

1. **Build the Mod**
   ```bash
   ./gradlew build
   ```

2. **Run in Development**
   ```bash
   ./gradlew runClient
   ```

3. **Clean Build**
   ```bash
   ./gradlew clean build
   ```

## Project Structure Explained

```
src/main/java/com/estonic/autofishing/
├── AutofishingMod.java          # Main entry point - registers client tick events
├── FishingDetector.java         # Detects "Reel it in!" text and fishing cues
├── FishingAutomation.java       # Handles automated fishing actions
└── mixin/
    ├── InGameHudMixin.java      # Hooks into HUD rendering
    └── ChatHudMixin.java        # Hooks into chat messages

src/main/resources/
├── fabric.mod.json              # Mod metadata and configuration
├── estonic-autofishing.mixins.json  # Mixin configuration
└── assets/estonic-autofishing/
    └── icon.png                 # Mod icon (128x128)
```

## Testing the Mod

1. **Launch Test Client**
   ```bash
   ./gradlew runClient
   ```

2. **In-Game Testing**
   - Create a new world or join a server
   - Find a body of water
   - Equip a fishing rod
   - Cast the rod and wait for the "Reel it in!" message
   - The mod should automatically pull in and recast

3. **Debug Logging**
   - Check the game log for messages like:
     ```
     [Estonic Autofishing] Fishing cue detected!
     [Estonic Autofishing] Fishing action performed
     ```

## Building for Distribution

1. **Build the JAR**
   ```bash
   ./gradlew build
   ```

2. **Locate the Built Mod**
   ```
   build/libs/estonic-autofishing-1.0.0.jar
   ```

3. **Test the Built Mod**
   - Copy the JAR to your `.minecraft/mods` folder
   - Launch Minecraft with Fabric Loader
   - Verify the mod appears in the mods list

## Common Issues

### Issue: Gradle Build Fails

**Solution**: Ensure you have Java 21+ installed
```bash
java -version  # Should show 21 or higher
```

### Issue: Minecraft Won't Launch

**Solution**: 
1. Check that Fabric Loader is installed for Minecraft 1.21
2. Ensure Fabric API is in your mods folder
3. Check the crash log in `logs/latest.log`

### Issue: Mod Doesn't Detect Fishing Cues

**Solution**:
1. Ensure you're using a fishing rod
2. Check the game log for detection messages
3. Verify the mod is loaded in the mods menu

## Making Changes

1. **Edit the Code**
   - Make your changes in IntelliJ IDEA or your preferred editor

2. **Rebuild**
   ```bash
   ./gradlew build
   ```

3. **Test**
   ```bash
   ./gradlew runClient
   ```

## Useful Gradle Tasks

```bash
./gradlew clean          # Clean build artifacts
./gradlew build          # Build the mod
./gradlew runClient      # Run Minecraft client with mod
./gradlew runServer      # Run Minecraft server with mod
./gradlew genSources     # Generate Minecraft source code
```

## Additional Resources

- [Fabric Wiki](https://fabricmc.net/wiki/)
- [Fabric API Documentation](https://fabricmc.net/wiki/documentation:start)
- [Minecraft Mappings (Yarn)](https://fabricmc.net/wiki/tutorial:mappings)
- [Mixin Documentation](https://github.com/SpongePowered/Mixin/wiki)

## Need Help?

- Check the [Issues](https://github.com/Jonas1903/Estonic-Autofishing/issues) page
- Read the [Fabric documentation](https://fabricmc.net/wiki/)
- Ask in the [Fabric Discord](https://discord.gg/v6v4pMv)
