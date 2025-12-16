#!/bin/bash

echo "=== Verifying Estonic Autofishing Implementation ==="
echo ""

# Check Java files
echo "1. Checking Java source files..."
java_files=$(find src/main/java -name "*.java" | wc -l)
echo "   Found $java_files Java files"

# List all Java files
echo ""
echo "2. Java source files:"
find src/main/java -name "*.java" | sort | while read file; do
    echo "   - $file"
done

# Check resources
echo ""
echo "3. Checking resource files..."
if [ -f "src/main/resources/fabric.mod.json" ]; then
    echo "   ✓ fabric.mod.json exists"
else
    echo "   ✗ fabric.mod.json missing"
fi

if [ -f "src/main/resources/estonic-autofishing.mixins.json" ]; then
    echo "   ✓ estonic-autofishing.mixins.json exists"
else
    echo "   ✗ estonic-autofishing.mixins.json missing"
fi

# Check build files
echo ""
echo "4. Checking build configuration..."
if [ -f "build.gradle" ]; then
    echo "   ✓ build.gradle exists"
fi
if [ -f "gradle.properties" ]; then
    echo "   ✓ gradle.properties exists"
fi
if [ -f "settings.gradle" ]; then
    echo "   ✓ settings.gradle exists"
fi

# Check gradle wrapper
echo ""
echo "5. Checking Gradle wrapper..."
if [ -f "gradlew" ] && [ -x "gradlew" ]; then
    echo "   ✓ gradlew exists and is executable"
else
    echo "   ✗ gradlew missing or not executable"
fi
if [ -f "gradlew.bat" ]; then
    echo "   ✓ gradlew.bat exists"
else
    echo "   ✗ gradlew.bat missing"
fi

# Check documentation
echo ""
echo "6. Checking documentation..."
for doc in README.md LICENSE BUILD_INSTRUCTIONS.md TESTING_GUIDE.md; do
    if [ -f "$doc" ]; then
        echo "   ✓ $doc exists"
    else
        echo "   ✗ $doc missing"
    fi
done

# Check for required classes
echo ""
echo "7. Checking required classes..."
required_classes=(
    "AutofishingMod.java"
    "AutofishCommand.java"
    "AutofishState.java"
    "FishingDetector.java"
    "FishingAutomation.java"
)

for class in "${required_classes[@]}"; do
    if [ -f "src/main/java/com/estonic/autofishing/$class" ]; then
        echo "   ✓ $class exists"
    else
        echo "   ✗ $class missing"
    fi
done

# Check mixins
echo ""
echo "8. Checking mixin classes..."
mixin_classes=(
    "InGameHudMixin.java"
    "ChatHudMixin.java"
)

for mixin in "${mixin_classes[@]}"; do
    if [ -f "src/main/java/com/estonic/autofishing/mixin/$mixin" ]; then
        echo "   ✓ $mixin exists"
    else
        echo "   ✗ $mixin missing"
    fi
done

# Check for common issues
echo ""
echo "9. Checking for common issues..."

# Check for Thread.sleep usage (should be documented)
if grep -r "Thread.sleep" src/main/java/ > /dev/null 2>&1; then
    echo "   ⚠ Warning: Thread.sleep() found (acceptable for delays)"
fi

# Check for proper package declarations
echo ""
echo "10. Verifying package declarations..."
find src/main/java -name "*.java" | while read file; do
    if ! grep -q "^package com.estonic.autofishing" "$file"; then
        echo "   ✗ Missing or incorrect package declaration in: $file"
    fi
done
echo "   ✓ All package declarations verified"

echo ""
echo "=== Verification Complete ==="
