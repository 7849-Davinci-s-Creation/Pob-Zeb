package io.github.team7849.Commands.SlashCommands;

import io.github.team7849.Commands.Command;
import io.github.team7849.Commands.CommandContext;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class HelloCommand implements Command {

    @Override
    public void handle(CommandContext ctx) {
        ctx.event().reply("Hello " + ctx.getAuthor().getAsMention()).queue();
    }

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getHelp() {
        return "Says hello to you!";
    }

    @Override
    public String getType() {
        return "misc";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{};
    }
}
