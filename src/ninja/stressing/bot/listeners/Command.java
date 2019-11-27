package ninja.stressing.bot.listeners;

import net.dv8tion.jda.core.entities.Role;

public interface Command {

    char COMMAND_PREFIX = '!';
    String description();
    String correctArgs();
    Role requiredRole();

}
