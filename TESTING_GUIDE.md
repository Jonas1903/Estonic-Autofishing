# Testing Guide for Estonic Autofishing Mod

This guide explains how to test all features of the Estonic Autofishing mod.

## Prerequisites

Before testing, ensure you have:
- Built the mod successfully (see BUILD_INSTRUCTIONS.md)
- Minecraft 1.21 installed with Fabric Loader
- Fabric API 0.100.1+1.21 installed in your mods folder

## Installation for Testing

1. Copy `build/libs/estonic-autofishing-1.0.0.jar` to `.minecraft/mods/`
2. Launch Minecraft with the Fabric 1.21 profile
3. Create or join a world with water nearby

## Test Cases

### Test 1: Mod Loading

**Objective:** Verify the mod loads correctly.

**Steps:**
1. Launch Minecraft
2. Check the game logs (`.minecraft/logs/latest.log`)
3. Look for: `[estonic-autofishing] Initializing Estonic Autofishing Mod`
4. Look for: `[estonic-autofishing] Estonic Autofishing Mod initialized (default: OFF, use /autofish toggle to enable)`

**Expected Result:** 
- Mod loads without errors
- Both initialization messages appear in the log

### Test 2: Toggle Command - Enable

**Objective:** Test the `/autofish toggle` command to enable the mod.

**Steps:**
1. Join a world
2. Open chat (press `T`)
3. Type `/autofish toggle` and press Enter

**Expected Result:**
- Message appears: `[Autofish] Autofishing is now ON` (green "ON")
- No errors in chat or logs

### Test 3: Toggle Command - Disable

**Objective:** Test toggling the mod off.

**Steps:**
1. With autofishing enabled (from Test 2)
2. Open chat and type `/autofish toggle` again

**Expected Result:**
- Message appears: `[Autofish] Autofishing is now OFF` (red "OFF")
- No errors in chat or logs

### Test 4: Fishing Detection - Disabled State

**Objective:** Verify the mod doesn't activate when disabled.

**Steps:**
1. Ensure autofishing is OFF (use `/autofish toggle` if needed)
2. Equip a fishing rod
3. Cast the rod into water
4. Wait for a fish to bite (look for bobber dipping and "Reel it in!" message)

**Expected Result:**
- Nothing happens automatically
- You must manually right-click to reel in the fish
- Logs should NOT show "Fishing cue detected!"

### Test 5: Fishing Detection - Enabled State

**Objective:** Test automatic fishing when enabled.

**Steps:**
1. Enable autofishing with `/autofish toggle`
2. Equip a fishing rod in your main hand
3. Cast the rod into water
4. Wait for a fish to bite

**Expected Result:**
- When "Reel it in!" appears, the mod automatically:
  - Reels in the rod (you should see the fish/item caught)
  - Waits a brief moment (50-200ms)
  - Recasts the rod automatically
- Check logs for "Fishing cue detected!" message
- Your crosshair should move slightly (subtle camera adjustment)

### Test 6: Leather Boots Handling

**Objective:** Test automatic leather boots usage.

**Steps:**
1. Enable autofishing with `/autofish toggle`
2. Place leather boots in any hotbar slot (slots 1-9)
3. Equip a fishing rod and cast it
4. Wait for a fish to bite

**Expected Result:**
- After reeling in the fish:
  - The mod switches to the leather boots slot
  - Right-clicks with the boots (you may see them in hand briefly)
  - Switches back to the fishing rod
  - Recasts the rod
- Check logs for:
  - "Found leather boots in slot X, using them..."
  - "Used leather boots"
  - "Switched back to fishing rod"

### Test 7: Crosshair Randomization

**Objective:** Verify anti-AFK crosshair movement.

**Steps:**
1. Enable autofishing
2. Position your view at a specific angle
3. Cast and catch several fish
4. Observe the crosshair position after each catch

**Expected Result:**
- Your camera/crosshair moves slightly between catches
- Movement is subtle (±1 degree yaw, ±0.75 degree pitch)
- Not the same movement each time

### Test 8: Debounce/Cooldown

**Objective:** Test that the 1-second cooldown prevents spam.

**Steps:**
1. Enable autofishing
2. Watch the logs while fishing
3. Note the timestamps of "Fishing cue detected!" messages

**Expected Result:**
- Messages appear at least 1 second apart
- No duplicate triggers for the same fishing event

### Test 9: Fishing Rod Requirement

**Objective:** Verify the mod only works with a fishing rod equipped.

**Steps:**
1. Enable autofishing with `/autofish toggle`
2. Unequip the fishing rod (hold a different item)
3. Wait or trigger a "Reel it in!" message somehow

**Expected Result:**
- Nothing happens when you're not holding a fishing rod
- The mod only activates when a fishing rod is in main hand or offhand

### Test 10: Off-Hand Fishing

**Objective:** Test fishing with rod in off-hand.

**Steps:**
1. Enable autofishing
2. Place fishing rod in off-hand (F key by default)
3. Cast and catch fish

**Expected Result:**
- Works the same as main-hand fishing
- Automatically reels in and recasts

### Test 11: Chat Message Detection

**Objective:** Test detection via chat messages (some servers use chat).

**Steps:**
1. Enable autofishing
2. In creative mode or using commands, try to simulate a "Reel it in!" chat message
3. Type in chat: `/tellraw @s {"text":"Reel it in!"}`

**Expected Result:**
- The detector should pick up the message
- Check logs for "Fishing cue detected!"

### Test 12: Multiple Fishing Sessions

**Objective:** Test extended use and state persistence.

**Steps:**
1. Enable autofishing
2. Fish for 5-10 minutes
3. Disable and re-enable autofishing during the session
4. Continue fishing

**Expected Result:**
- Mod works consistently throughout
- Toggle state changes immediately
- No errors or crashes during extended use
- Memory doesn't leak (check F3 debug screen)

## Performance Testing

### Test 13: Performance Impact

**Objective:** Measure the mod's performance impact.

**Steps:**
1. Press F3 to open debug overlay
2. Note the FPS without the mod
3. Enable autofishing and fish continuously
4. Monitor FPS and memory usage

**Expected Result:**
- Minimal FPS impact (< 5% decrease)
- No significant memory increase over time
- No lag spikes when detecting fishing cues

## Edge Cases

### Test 14: No Water Available

**Objective:** Test behavior when fishing fails.

**Steps:**
1. Enable autofishing
2. Cast fishing rod on land or in shallow water
3. Observe behavior

**Expected Result:**
- Mod doesn't cause errors
- May recast automatically if it thinks a fish was caught
- No crashes or exceptions

### Test 15: Rapid Toggle

**Objective:** Test rapid enabling/disabling.

**Steps:**
1. Quickly toggle autofishing on and off 10 times
2. Check for any errors or state inconsistencies

**Expected Result:**
- Each toggle works correctly
- State changes properly each time
- No errors in logs

## Logging and Debugging

To enable detailed logging, check `.minecraft/logs/latest.log` for:
- `[estonic-autofishing]` messages
- Any errors or exceptions
- Detection confirmations
- Action confirmations

## Reporting Issues

If any test fails, please report:
1. Test case number and name
2. Steps to reproduce
3. Expected vs actual behavior
4. Minecraft version and mod list
5. Relevant log excerpts
6. Screenshots or video if applicable

## Success Criteria

All tests should pass for a successful release:
- ✓ Mod loads correctly
- ✓ Toggle command works
- ✓ State management works (ON/OFF)
- ✓ Fishing detection works when enabled
- ✓ Fishing doesn't activate when disabled
- ✓ Automatic reel-in and recast works
- ✓ Leather boots handling works
- ✓ Crosshair randomization works
- ✓ Debounce prevents spam
- ✓ Only works with fishing rod equipped
- ✓ No performance issues
- ✓ No crashes or errors
