package io.github.team7849.Commands;

import java.awt.*;
import java.util.HashMap;

public final class ColorPickerManager {
    private final HashMap<String, ColorRole> emojiToRoleMap = new HashMap<>();

    public ColorPickerManager() {
        emojiToRoleMap.put("red", ColorPickerManager.ColorRoles.RED);
        emojiToRoleMap.put("blue", ColorPickerManager.ColorRoles.BLUE);
        emojiToRoleMap.put("green", ColorPickerManager.ColorRoles.GREEN);
    }

    public HashMap<String, ColorRole> getEmojiToRoleMap() {
        return this.emojiToRoleMap;
    }

    public static class ColorRole {
        private final String name;
        private final Color color;
        private final String code;

        public boolean exists;

        public ColorRole(String name, Color color, String code) {
            this.name = name;
            this.color = color;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public Color getColor() {
            return color;
        }

        public String getCode() {
            return code;
        }
    }

    private static class ColorRoles {
        private static final ColorRole RED = new ColorPickerManager.ColorRole("red", Color.RED, "\uD83D\uDFE5");
        private static final ColorRole BLUE = new ColorPickerManager.ColorRole("blue", Color.BLUE, "\uD83D\uDFE6");
        private static final ColorRole GREEN = new ColorPickerManager.ColorRole("green", Color.GREEN, "\uD83D\uDFE9");
    }
}
