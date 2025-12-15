# Installation and Import Guide

This document explains how to use the Estonic Autofishing mod distribution package.

## Package Contents

The `Estonic-Autofishing-v1.0.0.zip` file contains a complete Fabric mod project ready for:
- **Players**: Building and using the mod in Minecraft
- **Developers**: Importing into IntelliJ IDEA for development

## For Players: Building and Using the Mod

### Prerequisites

1. **Java 21 or higher**
   ```bash
   # Check your Java version
   java -version
   # Should show version 21.x.x or higher
   ```

2. **Minecraft 1.21 with Fabric Loader**
   - Install Fabric Loader from https://fabricmc.net/use/
   - Download Fabric API from https://modrinth.com/mod/fabric-api

### Steps to Build the Mod

1. **Extract the ZIP file**
   ```bash
   unzip Estonic-Autofishing-v1.0.0.zip
   cd Estonic-Autofishing
   ```

2. **Build the mod**
   ```bash
   # On Linux/macOS:
   ./gradlew build
   
   # On Windows:
   gradlew.bat build
   ```
   
   Note: The first build will download dependencies and may take several minutes.

3. **Locate the compiled mod**
   ```
   build/libs/estonic-autofishing-1.0.0.jar
   ```

4. **Install the mod**
   - Copy `estonic-autofishing-1.0.0.jar` to your `.minecraft/mods` folder
   - Also ensure `fabric-api-x.x.x.jar` is in the mods folder
   - Launch Minecraft with the Fabric profile

### Quick Player Installation

If you just want to use the mod without building:
1. Download a pre-built JAR from the releases page (when available)
2. Place it in your `.minecraft/mods` folder
3. Ensure Fabric Loader and Fabric API are installed
4. Launch Minecraft

## For Developers: Importing into IntelliJ IDEA

### Method 1: Import Gradle Project (Recommended)

1. **Open IntelliJ IDEA**

2. **Import the project**
   - File → Open
   - Navigate to the extracted `Estonic-Autofishing` folder
   - Select the folder and click "OK"

3. **Wait for Gradle sync**
   - IntelliJ will automatically detect it's a Gradle project
   - The first sync will download Minecraft, Fabric, and all dependencies
   - This may take 10-20 minutes depending on your internet connection

4. **Configure the JDK**
   - File → Project Structure → Project
   - Set SDK to Java 21 or higher
   - Set Language level to "21 - Sealed types, etc."

5. **Run the mod in development**
   - Open the Gradle tool window: View → Tool Windows → Gradle
   - Navigate to: estonic-autofishing → Tasks → fabric → runClient
   - Double-click to launch Minecraft with the mod

### Method 2: Import Maven Project

1. **Open IntelliJ IDEA**

2. **Import as Maven project**
   - File → Open
   - Select the `pom.xml` file in the extracted folder
   - Choose "Open as Project"

3. **Wait for Maven to sync**
   - Maven will download dependencies

Note: Gradle is the primary build system. Maven support is provided for compatibility but Gradle is recommended for Fabric development.

## Project Structure Overview

```
Estonic-Autofishing/
│
├── src/                        # Source code
│   └── main/
│       ├── java/              # Java source files
│       │   └── com/estonic/autofishing/
│       │       ├── AutofishingMod.java        # Main mod class
│       │       ├── FishingDetector.java       # Detection logic
│       │       ├── FishingAutomation.java     # Automation logic
│       │       └── mixin/                     # Mixins for Minecraft hooks
│       │
│       └── resources/         # Resource files
│           ├── fabric.mod.json                # Mod metadata
│           ├── estonic-autofishing.mixins.json # Mixin config
│           └── assets/
│               └── estonic-autofishing/
│                   └── icon.png               # Mod icon
│
├── build.gradle               # Gradle build configuration (PRIMARY)
├── pom.xml                    # Maven configuration (OPTIONAL)
├── gradle.properties          # Project properties
├── settings.gradle            # Gradle settings
│
├── gradle/                    # Gradle wrapper
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew                    # Gradle wrapper script (Linux/Mac)
├── gradlew.bat                # Gradle wrapper script (Windows)
│
├── LICENSE                    # MIT License
├── README.md                  # Main documentation
├── SETUP.md                   # Development setup guide
├── QUICKSTART.md              # Quick start guide
├── INSTALLATION.md            # This file
└── package.sh                 # Distribution packaging script
```

## Building from Command Line

### Build the mod
```bash
./gradlew build
```

### Run in development mode
```bash
./gradlew runClient
```

### Generate Minecraft sources (for IDE navigation)
```bash
./gradlew genSources
```

### Clean build artifacts
```bash
./gradlew clean
```

### Build and package for distribution
```bash
./package.sh
```

## Troubleshooting

### Problem: Gradle sync fails

**Solution 1: Check Java version**
```bash
java -version  # Must be 21 or higher
```

**Solution 2: Clear Gradle cache**
```bash
./gradlew clean --refresh-dependencies
```

**Solution 3: Delete cached files**
```bash
rm -rf .gradle build
./gradlew build
```

### Problem: IntelliJ can't find Minecraft classes

**Solution: Generate sources**
```bash
./gradlew genSources
```
Then in IntelliJ: File → Invalidate Caches → Invalidate and Restart

### Problem: Build fails with "Could not resolve dependencies"

**Solution: Check internet connection**
- Gradle needs to download Minecraft and Fabric from the internet
- Ensure you have a stable internet connection
- Try disabling VPN if applicable
- Wait a few minutes and try again

### Problem: "Task 'wrapper' not found"

**Solution: Wrapper already exists**
- The Gradle wrapper is already included in the ZIP
- Just run `./gradlew build` directly
- No need to run `gradle wrapper`

## System Requirements

### For Building/Development
- **OS**: Windows, macOS, or Linux
- **RAM**: 4GB minimum, 8GB recommended
- **Disk**: 5GB free space (for Minecraft + dependencies)
- **Internet**: Required for first build (to download dependencies)
- **Java**: JDK 21 or higher

### For Running the Mod
- **Minecraft**: Java Edition 1.21
- **Fabric Loader**: 0.15.11 or higher
- **Fabric API**: 0.100.1+1.21 or higher
- **Java**: JRE 21 or higher

## Next Steps

After successful installation:

1. **Read the documentation**
   - README.md - Overview and features
   - QUICKSTART.md - Quick start for players
   - SETUP.md - Detailed development guide

2. **Test the mod**
   - Run `./gradlew runClient` to test in development
   - Or build and install in your Minecraft instance

3. **Start developing** (for developers)
   - Explore the code in `src/main/java`
   - Make changes and test with `runClient`
   - Build with `./gradlew build`

## Support

- **Issues**: https://github.com/Jonas1903/Estonic-Autofishing/issues
- **Fabric Docs**: https://fabricmc.net/wiki/
- **Fabric Discord**: https://discord.gg/v6v4pMv

---

**Happy Modding!** 🎣
