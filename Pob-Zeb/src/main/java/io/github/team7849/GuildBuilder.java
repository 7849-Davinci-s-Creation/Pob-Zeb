package io.github.team7849;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.awt.*;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Objects;

// Creates needed channels / roles for a discord server upon bot joining
public final class GuildBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuildBuilder.class);

    private final Guild guild;

    private final HashMap<String, ColorRole> emojiToRoleMap = new HashMap<>();

    public GuildBuilder(Guild guild) {
        this.guild = guild;

        emojiToRoleMap.put("red", new ColorRole("red", Color.RED, "\uD83D\uDFE5"));
        emojiToRoleMap.put("blue", new ColorRole("blue", Color.BLUE, "\uD83D\uDFE6"));
        emojiToRoleMap.put("green", new ColorRole("green", Color.GREEN, "\uD83D\uDFE9"));
    }

    public void buildGuild() {
        LOGGER.info("Building guild");

        LOGGER.info("Checking for color roles. ");
        // see if color roles exist
        for (Role role : guild.getRoles()) {

            if (emojiToRoleMap.get(role.getName()) != null && Objects.equals(role.getColor(), emojiToRoleMap.get(role.getName()).color)) {
                emojiToRoleMap.get(role.getName()).exists = true;
            }

        }

        // for what color roles don't exist loop through and create them
        for (String key : emojiToRoleMap.keySet()) {
            if (!emojiToRoleMap.get(key).exists) {
                LOGGER.info("Creating Role: {} ", emojiToRoleMap.get(key).name);
                guild.createRole().setName(key).setColor(emojiToRoleMap.get(key).color).queue();
            }
        }

        LOGGER.info("Checking for color-picker channel. ");
        // see if color picker channel exists
        boolean hasColorChannel = false;
        for (TextChannel channel : guild.getTextChannels()) {
            if (channel.getName().equals("color-picker")) {
                hasColorChannel = true;
                break;
            }
        }

        if (!hasColorChannel) {
            LOGGER.info("Creating color-picker channel. ");

            final Role everyoneRole = guild.getPublicRole();
            guild.createTextChannel("color-picker").addPermissionOverride(
                    everyoneRole,
                    EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_ADD_REACTION), // Allowed
                    EnumSet.of(Permission.MESSAGE_SEND, Permission.MANAGE_CHANNEL)  // Denied
            ).queue();

        }

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Color picker");
        embed.setDescription("React to this message with the color you want!");

        guild.getTextChannelsByName("color-picker", true).forEach(textChannel -> textChannel.sendMessageEmbeds(embed.build()).queue(
                message -> {
                    for (String key : emojiToRoleMap.keySet()) {
                        message.addReaction(Emoji.fromUnicode(emojiToRoleMap.get(key).code)).queue();
                    }
                }
        ));

    }

    private static class ColorRole {
        private final String name;
        private final Color color;
        private final String code;

        private boolean exists;

        public ColorRole(String name, Color color, String code) {
            this.name = name;
            this.color = color;
            this.code = code;
        }

        private boolean roleExists() {
            return exists;
        }

        public String getName() {
            return name;
        }

        public Color getColor() {
            return color;
        }
    }
}
