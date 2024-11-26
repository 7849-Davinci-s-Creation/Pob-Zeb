package io.github.team7849;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.ArrayList;

public final class CommandManager {
    private final ArrayList<CommandData> commands;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    public ArrayList<CommandData> getCommands() {
        return this.commands;
    }

}
