# Estonic Autofishing

A Fabric mod for Minecraft Java 1.21 that automatically detects fishing cues and performs fishing actions with randomized crosshair movement to mimic human behavior.

## Features

- **Toggle Command**: Use `/autofish toggle` to enable or disable the mod (default: OFF)
- **Automatic Detection**: Detects the green exclamation mark and "Reel it in!" text
- **Smart Automation**: Automatically pulls in and recasts the fishing rod
- **Leather Boots Support**: Automatically uses leather boots in hotbar when catching fish, then returns to fishing
- **Anti-Cheat Protection**: Randomizes crosshair movement to mimic human actions
- **Lightweight**: Minimal performance impact using efficient rendering hooks

## Requirements

- Minecraft Java Edition 1.21
- Fabric Loader 0.15.11 or higher
- Fabric API 0.100.1+1.21 or higher
- Java 21 or higher

## Installation

### For Players

1. Download the latest `.jar` file from the releases page
2. Install Fabric Loader for Minecraft 1.21
3. Place the downloaded `.jar` file in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

### For Developers

1. Clone this repository
2. Open the project in IntelliJ IDEA or your preferred IDE
3. Run `./gradlew build` to build the mod
4. The compiled mod will be in `build/libs/`

## Building from Source

```bash
# Clone the repository
git clone https://github.com/Jonas1903/Estonic-Autofishing.git
cd Estonic-Autofishing

# Build the mod
./gradlew build

# The built mod will be at build/libs/estonic-autofishing-1.0.0.jar
```

## Usage

1. Install the mod in your `.minecraft/mods` folder
2. Launch Minecraft with Fabric
3. Join a world or server
4. Use `/autofish toggle` to enable autofishing (default is OFF)
5. Hold a fishing rod and start fishing
6. The mod will automatically reel in and recast when it detects the "Reel it in!" message

**Note**: The mod starts disabled by default. You must use `/autofish toggle` to enable it.

## Development Setup

### Prerequisites

- JDK 21 or higher
- Gradle 8.8 (included via wrapper)

### Setting up Java 21

Ensure you have Java 21 installed. You can check your Java version with:

```bash
java -version
```

If you need to install Java 21, download it from [Adoptium](https://adoptium.net/) (Eclipse Temurin) or use your system's package manager.

For Gradle to use Java 21, you can either:
1. Set `JAVA_HOME` environment variable to point to your Java 21 installation
2. Or let Gradle auto-detect it if Java 21 is your default version

### Import into IntelliJ IDEA

1. Open IntelliJ IDEA
2. File → Open → Select the project directory
3. Wait for Gradle to sync
4. Ensure IntelliJ is using Java 21:
   - File → Project Structure → Project SDK → Select Java 21
   - File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JVM → Select Java 21
5. Run the `runClient` Gradle task to test the mod

### Project Structure

```
Estonic-Autofishing/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/estonic/autofishing/
│       │       ├── AutofishingMod.java      # Main mod initializer
│       │       ├── AutofishCommand.java     # Command registration
│       │       ├── AutofishState.java       # Toggle state management
│       │       ├── FishingDetector.java     # Fishing cue detection
│       │       ├── FishingAutomation.java   # Automation logic
│       │       └── mixin/
│       │           ├── InGameHudMixin.java  # HUD rendering hook
│       │           └── ChatHudMixin.java    # Chat message hook
│       └── resources/
│           ├── fabric.mod.json              # Mod metadata
│           ├── estonic-autofishing.mixins.json # Mixin configuration
│           └── assets/
│               └── estonic-autofishing/
│                   └── icon.png             # Mod icon
├── build.gradle                             # Build configuration
├── gradle.properties                        # Project properties
├── settings.gradle                          # Gradle settings
└── LICENSE                                  # MIT License
```

## How It Works

1. **Toggle**: Use `/autofish toggle` command to enable/disable (starts OFF by default)

2. **Detection**: When enabled, the mod uses Mixin to hook into Minecraft's rendering system, monitoring for:
   - The text "Reel it in!" appearing on screen
   - Green exclamation mark particles (visual fishing cue)

3. **Automation**: When a cue is detected and autofishing is enabled:
   - Right-clicks to pull in the fishing rod
   - Waits 50-100ms (randomized)
   - If leather boots are in hotbar:
     - Switches to leather boots slot
     - Right-clicks with boots
     - Waits 100-200ms (randomized)
     - Switches back to fishing rod
   - Slightly adjusts crosshair position (-1 to +1 degrees)
   - Right-clicks again to recast the rod

3. **Anti-Cheat Evasion**: 
   - Random delays between actions (50-200ms)
   - Randomized crosshair movement (±1.5 degrees)
   - Cooldown period to prevent spam
   - Only runs when toggled ON with `/autofish toggle`

## Commands

- `/autofish toggle` - Toggles autofishing ON/OFF (default: OFF)
  - Shows feedback message with current state
  - State persists during the game session

## Configuration

The mod starts disabled by default for safety. Enable it using `/autofish toggle` when you're ready to fish.

Future versions may include:
- Persistent configuration file
- Adjustable delay ranges
- Customizable detection sensitivity

## Compatibility

- **Minecraft**: 1.21
- **Fabric Loader**: 0.15.11+
- **Java**: 21+

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Disclaimer

This mod is for educational purposes. Use at your own risk. Some servers may prohibit automation mods. Always check server rules before using.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.
