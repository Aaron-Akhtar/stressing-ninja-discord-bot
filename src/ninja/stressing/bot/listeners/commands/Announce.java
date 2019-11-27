package ninja.stressing.bot.listeners.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ninja.stressing.bot.listeners.Command;

public class Announce extends ListenerAdapter implements Command {

    private JDA jda;

    public Announce(JDA jda) {
        this.jda = jda;
    }

    @Override
    public String description() {
        return "Used to make announcements...";
    }

    @Override
    public String correctArgs() {
        return "!announce <announcement>";
    }

    @Override
    public Role requiredRole() {
        return jda.getRoleById(649089157850726413L);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Command.COMMAND_PREFIX + "announce")){
            e.getMessage().delete().queue();
            if (e.getMember().getRoles().contains(requiredRole())){
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(e.getGuild().getName() + " - Announcement");
                eb.setDescription(sb.toString());
                eb.setFooter("Developed by Stressing Ninja - Copyright (c) 2019, All Rights Reserved.", "https://stressing.ninja/sp/assets/images/avatars/avt.png");
                e.getChannel().sendMessage("@everyone").queue();
                e.getChannel().sendMessage(eb.build()).queue();
            }
        }
    }

}
