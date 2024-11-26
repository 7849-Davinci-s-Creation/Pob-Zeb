package io.github.team7849.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.sharding.ShardManager;
import java.util.List;

public record CommandContext(MessageReceivedEvent event, List<String> args) {

    public Guild getGuild() {
        return this.event().getGuild();
    }

    public JDA getJDA() {
        return this.event.getJDA();
    }

    public TextChannel getChannel() {
        return this.event().getChannel().asTextChannel();
    }

    public Message getMessage() {
        return this.event().getMessage();
    }

    public User getAuthor() {
        return this.event().getAuthor();
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

    public static SlashCommandData createSlashCommand(Command command, OptionData... options) {
        return Commands.slash(command.getName(), command.getHelp()).addOptions(options);
    }
}