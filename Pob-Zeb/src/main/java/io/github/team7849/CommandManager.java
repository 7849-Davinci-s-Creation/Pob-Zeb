package io.github.team7849;

import io.github.team7849.Commands.Command;
import io.github.team7849.Commands.CommandContext;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.ArrayList;

public final class CommandManager {
    private final ArrayList<CommandData> commandsData = new ArrayList<>();
    private final ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
    }

    public ArrayList<CommandData> getCommandsData() {
        return this.commandsData;
    }

    private void registerCommand(Command command) {
        this.commands.add(command);

        this.commandsData.add(
                CommandContext.createSlashCommand(command,command.getOptions())
        );
    }

}
