package io.github.team7849;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Listener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    private final CommandManager commandManager = new CommandManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // register commands
        event.getJDA().updateCommands().addCommands(commandManager.getCommands()).queue();

        LOGGER.info("                                             *#####%    %#####,               \n" +
                "                                            #% ###% #%%# %### %#              \n" +
                "                                            #% %%.#,%##(,# %% %#              \n" +
                "                                      ###%##% %##( %#  #% ###% ###%##%        \n" +
                "                                     #*/### #(, %#%      ### *%# ###(/#       \n" +
                "                                     #% ####% #%## ##  %#.%### %#### %#       \n" +
                "                                      *##%%##%    ## %% ##    %##%%##*        \n" +
                "                                      *######%    ## %% ##    #######*        \n" +
                "                                     #% ####% #%## ##  %# %### %#### %#       \n" +
                "                                     #*(### #(*.%#%      ### ,%# ###(/#       \n" +
                "                                      ##%%%#% #%%/ %#  #% (%%# ##%%%#%        \n" +
                "                                            #% %%.#,%###.# %% ##              \n" +
                "                                            #% ###% #%%# %### %#              \n" +
                "                                             *#####%    %#####* ");
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

}
