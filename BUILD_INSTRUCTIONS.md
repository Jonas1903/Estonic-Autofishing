# Build Instructions

This document provides detailed instructions for building the Estonic Autofishing mod.

## Prerequisites

1. **Java 21 or higher** - Required for compilation
2. **Network access** - Required to download dependencies from Maven repositories
3. **Git** (optional) - For cloning the repository

## Verifying Java Installation

Check your Java version:

```bash
java -version
```

You should see output like:
```
openjdk version "21.0.x" ...
```

If you don't have Java 21, download and install it from:
- [Eclipse Temurin](https://adoptium.net/) (recommended)
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)

## Building the Mod

### Step 1: Clone or Download the Repository

```bash
git clone https://github.com/Jonas1903/Estonic-Autofishing.git
cd Estonic-Autofishing
```

Or download and extract the ZIP file from GitHub.

### Step 2: Set Java Home (if needed)

If Java 21 is not your default version, set the `JAVA_HOME` environment variable:

**Linux/macOS:**
```bash
export JAVA_HOME=/path/to/java21
export PATH=$JAVA_HOME/bin:$PATH
```

**Windows (PowerShell):**
```powershell
$env:JAVA_HOME = "C:\Path\To\Java21"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

### Step 3: Build the Mod

Run the Gradle wrapper to build the mod:

**Linux/macOS:**
```bash
./gradlew clean build
```

**Windows:**
```cmd
gradlew.bat clean build
```

The build process will:
1. Download Gradle 8.8 (if not already cached)
2. Download Minecraft 1.21 and dependencies
3. Download Fabric Loader and Fabric API
4. Compile the mod
5. Run any tests
6. Create the JAR file

### Step 4: Locate the Built Mod

After a successful build, the mod JAR will be located at:

```
build/libs/estonic-autofishing-1.0.0.jar
```

There will also be a sources JAR:
```
build/libs/estonic-autofishing-1.0.0-sources.jar
```

## Troubleshooting

### Build Fails with "Could not resolve dependencies"

**Problem:** Network issues or blocked Maven repositories.

**Solution:**
- Check your internet connection
- Verify you can access https://maven.fabricmc.net/
- Try using a VPN if your network blocks the repository
- Clear Gradle cache: `./gradlew clean --refresh-dependencies`

### Build Fails with "Unsupported class file major version"

**Problem:** You're using a Java version that's too old.

**Solution:**
- Install Java 21 or higher
- Set JAVA_HOME to point to Java 21
- Verify with `java -version`

### Build Fails with "Could not find or load main class"

**Problem:** JAVA_HOME is set incorrectly or Java is not properly installed.

**Solution:**
- Reinstall Java 21
- Ensure JAVA_HOME points to the JDK installation directory (not JRE)
- Example: `/usr/lib/jvm/java-21-openjdk` (Linux) or `C:\Program Files\Java\jdk-21` (Windows)

## Testing the Mod

### Option 1: Run in Development Environment

Use the `runClient` Gradle task to launch Minecraft with the mod:

```bash
./gradlew runClient
```

This will:
1. Set up a Minecraft development environment
2. Launch Minecraft with your mod loaded
3. Create a `run/` directory with game files

### Option 2: Install in Minecraft

1. Install Fabric Loader for Minecraft 1.21
2. Copy `build/libs/estonic-autofishing-1.0.0.jar` to your `.minecraft/mods` folder
3. Launch Minecraft with the Fabric profile
4. Join a world and use `/autofish toggle` to enable the mod

## Building for Distribution

To create a release build:

```bash
./gradlew clean build
```

The JAR in `build/libs/` is ready for distribution. Users only need:
- Minecraft 1.21
- Fabric Loader 0.15.11+
- Fabric API 0.100.1+1.21
- Java 21+ (to run Minecraft)

## Continuous Integration

If you're setting up CI/CD, ensure your build environment has:
- Java 21 installed
- Network access to Maven repositories
- At least 2GB of RAM for Gradle (configured in `gradle.properties`)

Example GitHub Actions workflow:

```yaml
name: Build Mod

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up Java 21
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '21'
      - name: Build with Gradle
        run: ./gradlew build
      - name: Upload artifact
        uses: actions/upload-artifact@v3
        with:
          name: mod-jar
          path: build/libs/*.jar
```

## Additional Resources

- [Fabric Wiki](https://fabricmc.net/wiki/)
- [Fabric Loader Documentation](https://fabricmc.net/wiki/documentation:fabric_loader)
- [Gradle Documentation](https://docs.gradle.org/)
