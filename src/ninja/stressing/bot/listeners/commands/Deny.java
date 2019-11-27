package ninja.stressing.bot.listeners.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ninja.stressing.bot.Lang;
import ninja.stressing.bot.listeners.Command;
import ninja.stressing.bot.utils.Utils;

@SuppressWarnings("All")
public class Deny extends ListenerAdapter implements Command {

    private JDA jda;

    public Deny(JDA jda) {
        this.jda = jda;
    }

    @Override
    public String description() {
        return "Used to deny a user access into our discord community.";
    }

    @Override
    public String correctArgs(){
        return "!deny <user-id> <reason>";
    }

    @Override
    public Role requiredRole() {
        return jda.getRoleById(649089372590833664L);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Command.COMMAND_PREFIX + "deny")){
            if (e.getMember().getRoles().contains(requiredRole())) {
                if (args.length < 3){
                    e.getChannel().sendMessage("Correct Arguments: " + correctArgs()).queue();
                }else {
                    Member member = e.getGuild().getMemberById(Long.parseLong(args[1]));
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                    }
                    if (member.getRoles().contains(jda.getRoleById(649090080627097602L))) {
                        Utils.sendDirectMessage(member.getUser(), "You have been denied from "+e.getGuild().getName()+" for: " + sb.toString() + "..", null);
                        try{Thread.sleep(2000);}catch (InterruptedException ex){}
                        e.getGuild().getController().kick(member, sb.toString()).queue();
                        e.getChannel().sendMessage("Denied user: " + member.getAsMention()).queue();
                    } else {
                        e.getChannel().sendMessage("Member does not have the Unverified role...").queue();
                    }
                }
            }else{
                e.getChannel().sendMessage(Lang.ERR_NO_PERMISSION.getContent()).queue();
            }
        }
    }
}
