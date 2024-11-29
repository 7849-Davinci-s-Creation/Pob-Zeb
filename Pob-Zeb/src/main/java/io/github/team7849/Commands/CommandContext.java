package io.github.team7849.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.List;

public record CommandContext(SlashCommandInteractionEvent event, List<OptionData> args) {

    public Guild getGuild() {
        return this.event().getGuild();
    }

    public JDA getJDA() {
        return this.event.getJDA();
    }

    public String getCommandName() {
        return this.event().getInteraction().getName();
    }

    public User getAuthor() {
        return this.event().getUser();
    }

    public Member getMember() {
        return this.event().getMember();
    }

    public ShardManager getShardManager() {
        return this.getJDA().getShardManager();
    }

    public User getSelfUser() {
        return this.getJDA().getSelfUser();
    }

    public Member getSelfMember() {
        return this.getGuild().getSelfMember();
    }

    public OptionMapping getOption(String name) {
        return event.getOption(name);
    }

    public static SlashCommandData createSlashCommand(Command command, OptionData... options) {
        return Commands.slash(command.getName(), command.getHelp()).addOptions(options);
    }
}
