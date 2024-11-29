package io.github.team7849;

import io.github.team7849.Commands.Command;
import io.github.team7849.Commands.CommandContext;
import io.github.team7849.Commands.SlashCommands.HelloCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class CommandManager {
    private final ArrayList<CommandData> commandsData = new ArrayList<>();
    private final ArrayList<Command> commands = new ArrayList<>();

    private final HashMap<String, Command> commandMap = new HashMap<>();

    public CommandManager() {
        registerCommand(new HelloCommand());

        for (Command command : commands) {
            commandMap.put(command.getName(), command);
        }
    }

    public ArrayList<CommandData> getCommandsData() {
        return this.commandsData;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public void handle(SlashCommandInteractionEvent event) {
        Command command = commandMap.get(event.getName());

        if (command == null) {
            event.reply("Command not found!").queue();
            return;
        }

        List<OptionData> options = List.of(command.getOptions());
        CommandContext ctx = new CommandContext(event, options);

        command.handle(ctx);
    }

    private void registerCommand(Command command) {
        this.commands.add(command);

        this.commandsData.add(
                CommandContext.createSlashCommand(command, command.getOptions()));
    }

}
