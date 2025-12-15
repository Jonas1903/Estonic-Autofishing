#!/bin/bash

# Package script for Estonic Autofishing Mod
# Creates a distributable ZIP file with the mod and documentation

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

echo "======================================"
echo "Estonic Autofishing Mod - Packager"
echo "======================================"
echo ""

# Clean previous builds
echo "[1/5] Cleaning previous builds..."
rm -rf build/distribution
mkdir -p build/distribution/estonic-autofishing

# Create directory structure
echo "[2/5] Creating distribution structure..."
mkdir -p build/distribution/estonic-autofishing/mod
mkdir -p build/distribution/estonic-autofishing/docs

# Copy mod JAR (if it exists)
if [ -f "build/libs/estonic-autofishing-1.0.0.jar" ]; then
    echo "[3/5] Copying mod JAR..."
    cp build/libs/estonic-autofishing-1.0.0.jar build/distribution/estonic-autofishing/mod/
else
    echo "[3/5] Mod JAR not found. Building first..."
    ./gradlew build
    cp build/libs/estonic-autofishing-1.0.0.jar build/distribution/estonic-autofishing/mod/
fi

# Copy documentation
echo "[4/5] Copying documentation..."
cp README.md build/distribution/estonic-autofishing/
cp LICENSE build/distribution/estonic-autofishing/
cp SETUP.md build/distribution/estonic-autofishing/docs/

# Create installation instructions
cat > build/distribution/estonic-autofishing/INSTALL.txt << 'EOF'
ESTONIC AUTOFISHING MOD - INSTALLATION INSTRUCTIONS
=====================================================

For Players:
------------
1. Ensure you have Minecraft Java Edition 1.21 installed
2. Download and install Fabric Loader from https://fabricmc.net/use/
3. Download Fabric API from https://modrinth.com/mod/fabric-api
4. Copy the following files to your .minecraft/mods folder:
   - fabric-api-x.x.x.jar (download separately)
   - estonic-autofishing-1.0.0.jar (from the mod/ folder)
5. Launch Minecraft with the Fabric profile
6. The mod should appear in the Mods menu

For Developers:
---------------
See docs/SETUP.md for complete development setup instructions.

Requirements:
-------------
- Minecraft Java Edition 1.21
- Fabric Loader 0.15.11+
- Fabric API 0.100.1+1.21
- Java 21+

Usage:
------
1. Equip a fishing rod
2. Cast into water
3. The mod will automatically detect "Reel it in!" and handle fishing
4. Your crosshair will move slightly to mimic human behavior

Support:
--------
For issues and support, visit:
https://github.com/Jonas1903/Estonic-Autofishing/issues

License:
--------
This mod is licensed under the MIT License. See LICENSE file for details.
EOF

# Create the ZIP file
echo "[5/5] Creating distribution ZIP..."
cd build/distribution
zip -r ../estonic-autofishing-1.0.0-distribution.zip estonic-autofishing/
cd "$PROJECT_DIR"

echo ""
echo "======================================"
echo "Distribution package created!"
echo "Location: build/estonic-autofishing-1.0.0-distribution.zip"
echo "======================================"
echo ""
echo "Contents:"
echo "  - Mod JAR: mod/estonic-autofishing-1.0.0.jar"
echo "  - Documentation: README.md, LICENSE, INSTALL.txt"
echo "  - Setup Guide: docs/SETUP.md"
echo ""
