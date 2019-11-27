package ninja.stressing.bot.listeners.events;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ninja.stressing.bot.utils.Utils;

public class Join extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        e.getGuild().getController().addSingleRoleToMember(e.getMember(), e.getGuild().getRolesByName("Unverified", true).get(0)).queue();
        Utils.sendDirectMessage(e.getMember().getUser(), "Welcome to " + e.getGuild().getName() + ", before you start enjoying your time here please go into #verify and follow the instructions...", null);
    }
}
