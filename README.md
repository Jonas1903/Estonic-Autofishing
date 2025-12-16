# Estonic Autofishing

A Fabric mod for Minecraft Java 1.21 that automatically detects fishing cues and performs fishing actions with randomized crosshair movement to mimic human behavior.

## Features

- **Keybind Toggle**: Press F8 to enable or disable the mod (default: OFF)
- **Automatic Detection**: Detects the green exclamation mark and "Rell it in!" text in the overlay/action bar
- **Smart Automation**: Automatically pulls in and recasts the fishing rod
- **Leather Boots Support**: Automatically uses leather boots in hotbar when catching fish, then returns to fishing
- **Anti-Cheat Protection**: Randomizes crosshair movement to mimic human actions
- **Lightweight**: Minimal performance impact using Fabric API event hooks

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
4. Press **F8** to enable autofishing (default is OFF)
5. Hold a fishing rod and start fishing
6. The mod will automatically reel in and recast when it detects the "Rell it in!" message

**Note**: The mod starts disabled by default. You must press **F8** to enable it.

## Controls

- **F8** - Toggle autofishing ON/OFF

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

## How It Works

1. **Toggle**: Press F8 to enable/disable (starts OFF by default, shows HUD status message)

2. **Detection**: When enabled, the mod listens for overlay/action bar messages containing:
   - The text "Rell it in!" (case-insensitive)
   - Uses Fabric API's ClientReceiveMessageEvents

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

4. **Anti-Cheat Evasion**: 
   - Random delays between actions (50-200ms)
   - Randomized crosshair movement (±1.5 degrees)
   - Cooldown period to prevent spam
   - Only runs when toggled ON with F8

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
