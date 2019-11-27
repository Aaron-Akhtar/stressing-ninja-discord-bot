package ninja.stressing.bot.utils;

import net.dv8tion.jda.core.entities.MessageEmbed;

public class Utils {

    public static void sendDirectMessage(net.dv8tion.jda.core.entities.User user, String directMessage, MessageEmbed embed){
        user.openPrivateChannel().queue((channel) ->
        {
            if(directMessage != null) {
                channel.sendMessage(directMessage).queue();
            }else{
                channel.sendMessage(embed).queue();
            }
        });
    }

}
