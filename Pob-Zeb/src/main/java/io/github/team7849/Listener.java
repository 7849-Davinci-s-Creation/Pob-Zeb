package io.github.team7849;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public final class Listener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    private final CommandManager commandManager = new CommandManager();

    /**List of discord text channel ids, this is used to check if the bot should even bother doing things if it's not in a designated channel*/
    private final ArrayList<String> acceptableChannel = new ArrayList<>();

    public Listener() {
        acceptableChannel.add(Config.get("BOT_COMMANDS_TEXT_CHANNEL"));
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // register commands
        event.getJDA().updateCommands().addCommands(commandManager.getCommands()).queue();

        LOGGER.info("""
                                                             *#####%    %#####,              \s
                                                            #% ###% #%%# %### %#             \s
                                                            #% %%.#,%##(,# %% %#             \s
                                                      ###%##% %##( %#  #% ###% ###%##%       \s
                                                     #*/### #(, %#%      ### *%# ###(/#      \s
                                                     #% ####% #%## ##  %#.%### %#### %#      \s
                                                      *##%%##%    ## %% ##    %##%%##*       \s
                                                      *######%    ## %% ##    #######*       \s
                                                     #% ####% #%## ##  %# %### %#### %#      \s
                                                     #*(### #(*.%#%      ### ,%# ###(/#      \s
                                                      ##%%%#% #%%/ %#  #% (%%# ##%%%#%       \s
                                                            #% %%.#,%###.# %% ##             \s
                                                            #% ###% #%%# %### %#             \s
                                                             *#####%    %#####* \
                """);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!acceptableChannel.contains(event.getChannelId())) {
            return;
        }


    }

}
