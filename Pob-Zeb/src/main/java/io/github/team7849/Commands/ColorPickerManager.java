package io.github.team7849.Commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

public final class ColorPickerManager {

    public ColorPickerManager() {

    }

    public void handleEmojiReaction(MessageReactionAddEvent event) throws InterruptedException {
        final String reactedEmoji = event.getReaction().getEmoji().asUnicode().getAsCodepoints();
        final ColorRole wantedRole = getColor(reactedEmoji);

        if (wantedRole == null) {
            LOGGER.info("NULL");
        } else {
            LOGGER.info("WANTED ROLE: " + wantedRole);
        }

        if (wantedRole == null) {
            LOGGER.error("No color role found for emoji: {}", reactedEmoji);
            return;
        }

        final String wantedRoleName = wantedRole.getName();

        final Role role = event.getGuild().getRolesByName(wantedRoleName, true).stream().findFirst().orElse(null);

        if (role == null) {
            LOGGER.error("No role found for emoji: {}", reactedEmoji);
            return;
        }

        Member user = event.getMember();

        if (user == null) {
            LOGGER.error("No user found for {}:", event.getMember().getEffectiveName());
            return;
        }


        // check the role hierarchy is above the color roles.
        Role botRole = event.getGuild().getSelfMember().getRoles().stream()
                .max(Comparator.comparingInt(Role::getPosition))
                .orElse(null);

        if (botRole == null ||  botRole.getPosition() < role.getPosition()) {
            LOGGER.error("Bot's role is below color roles, please fix the hierarchy.");
            return;
        }

        event.getGuild().addRoleToMember(event.getMember(), role).queue();

        user.getUser().openPrivateChannel().queue(pc -> {
            pc.sendMessageFormat("Added color role %s to your roles!", role.getName()).queue();
        });

        // look for existing color roles and remove them
        ArrayList<String> roles = new ArrayList<>();

        for (String key : EMOJI_ROLE_MAP.keySet()) {
            roles.add(EMOJI_ROLE_MAP.get(key).getName());
        }

        roles.remove(wantedRoleName);

        for (Role r : user.getRoles()) {

            if (!r.getName().equals(wantedRoleName) && roles.contains(r.getName())) {
                event.getGuild().removeRoleFromMember(user, r).queue();
            }

        }

        Thread.sleep(2000);
    }

    private static ColorRole getColor(String reactedEmoji) {
        for (String key : EMOJI_ROLE_MAP.keySet()) {

            if (EMOJI_ROLE_MAP.get(key).getCodePoint().equalsIgnoreCase(reactedEmoji)) {
                return EMOJI_ROLE_MAP.get(key);
            }
        }

        return null;
    }

    // statics
    private static final Logger LOGGER = LoggerFactory.getLogger(ColorPickerManager.class);

    public static final HashMap<String, ColorRole> EMOJI_ROLE_MAP = new HashMap<>();

    static {
        EMOJI_ROLE_MAP.put("red", ColorPickerManager.ColorRoles.RED);
        EMOJI_ROLE_MAP.put("blue", ColorPickerManager.ColorRoles.BLUE);
        EMOJI_ROLE_MAP.put("green", ColorPickerManager.ColorRoles.GREEN);
    }

    public static class ColorRole {
        private final String name;
        private final Color color;
        private final String code;
        private final String codePoint;

        public boolean exists;

        public ColorRole(String name, Color color, String code, String codePoint) {
            this.name = name;
            this.color = color;
            this.code = code;
            this.codePoint = codePoint;
        }

        @Override
        public String toString() {
            return "ColorRole{" +
                    "name='" + name + '\'' +
                    ", color=" + color +
                    ", code='" + code + '\'' +
                    ", codePoint='" + codePoint + '\'' +
                    ", exists=" + exists +
                    '}';
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

        public String getCodePoint() {
            return codePoint;
        }
    }

    private static class ColorRoles {
        private static final ColorRole RED = new ColorPickerManager.ColorRole("red", Color.RED, "\uD83D\uDFE5", "U+1f7e5");
        private static final ColorRole BLUE = new ColorPickerManager.ColorRole("blue", Color.BLUE, "\uD83D\uDFE6", "U+1f7e6");
        private static final ColorRole GREEN = new ColorPickerManager.ColorRole("green", Color.GREEN, "\uD83D\uDFE9", "U+1f7e9");
    }
}
