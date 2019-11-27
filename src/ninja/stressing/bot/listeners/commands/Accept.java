package ninja.stressing.bot.listeners.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ninja.stressing.bot.Lang;
import ninja.stressing.bot.listeners.Command;
import ninja.stressing.bot.utils.Utils;

public class Accept extends ListenerAdapter implements Command {

    private JDA jda;

    public Accept(JDA jda) {
        this.jda = jda;
    }

    @Override
    public String description() {
        return "Used to accept a user into our discord community.";
    }

    @Override
    public String correctArgs(){
        return "!accept <user-id>";
    }

    @Override
    public Role requiredRole() {
        return jda.getRoleById(649089372590833664L);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Command.COMMAND_PREFIX + "accept")){
            if (e.getMember().getRoles().contains(requiredRole())) {
                if (args.length != 2){
                    e.getChannel().sendMessage("Correct Arguments: " + correctArgs()).queue();
                }else {
                    Member member = e.getGuild().getMemberById(Long.parseLong(args[1]));
                    if (!member.getRoles().contains(jda.getRoleById(649089660055978016L)) && member.getRoles().contains(jda.getRoleById(649090080627097602L))) {
                        e.getGuild().getController().removeSingleRoleFromMember(member, e.getGuild().getRolesByName("Unverified", true).get(0)).queue();
                        e.getGuild().getController().addSingleRoleToMember(member, e.getGuild().getRolesByName("Community", true).get(0)).queue();
                        e.getChannel().sendMessage("Accepted user: " + member.getAsMention()).queue();
                        Utils.sendDirectMessage(member.getUser(), "You have been accepted into " + e.getGuild().getName(), null);
                    } else {
                        e.getChannel().sendMessage("Member already has Community role or does not have the Unverified role...").queue();
                    }
                }
            }else{
                e.getChannel().sendMessage(Lang.ERR_NO_PERMISSION.getContent()).queue();
            }
        }
    }
}
