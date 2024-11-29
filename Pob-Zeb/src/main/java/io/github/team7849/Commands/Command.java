package io.github.team7849.Commands;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public interface Command {
    /**
     * What the command does at time of execution.
     *
     * @param ctx CommandContext object for channel command was executed in, and the
     *            arguments given when invoked.
     * @see CommandContext
     */
    void handle(CommandContext ctx);

    /**
     * Name of command.
     *
     * @return the name of specified ICommand type.
     */
    String getName();

    /**
     * Help command description.
     *
     * @return Description of help of specified ICommand type.
     */
    String getHelp();

    /**
     * Type of command grouping.
     *
     * @return The category the specified ICommand type falls under.
     */
    String getType();

    OptionData[] getOptions();

    /**
     * Other names for command.
     *
     * @return List of aliases of specified ICommand type.
     */
    default List<String> getAlisases() {
        return List.of();
    }
}
