package ninja.stressing.bot.listeners.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ninja.stressing.bot.listeners.Command;

@SuppressWarnings("Duplicates")
public class Rules extends ListenerAdapter implements Command {

    private JDA jda;

    public Rules(JDA jda) {
        this.jda = jda;
    }

    @Override
    public String description() {
        return "Used to display our server rules.";
    }

    @Override
    public String correctArgs() {
        return "!rules <everyone(optional)>";
    }

    @Override
    public Role requiredRole() {
        return jda.getRoleById(649089372590833664L);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(Command.COMMAND_PREFIX + "rules")){
            if (args.length == 2 && args[1].equalsIgnoreCase("everyone") && e.getMember().getRoles().contains(requiredRole())){
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(e.getGuild().getName() + " - Rules");
                eb.setDescription("Please follow all our rules to avoid punishment...");
                eb.addField("Do not speak about anything remotely illegal!", "Punishment: Instant Ban", false);
                eb.addField("Do not disrespect staff!", "Punishment: Instant Ban / Mute (Depending on severity)", false);
                eb.addField("Do not advertise any competitors of Stressing Ninja!", "Punishment: Instant Ban", false);
                eb.addField("Follow Discord's Terms of Service and Guidelines at all times!", "Punishment: Instant Ban\nDiscord Terms: https://discordapp.com/terms\nDiscord Guidelines: https://discordapp.com/guidelines", false);
                eb.setFooter("Developed by Stressing Ninja - Copyright (c) 2019, All Rights Reserved.", "https://stressing.ninja/sp/assets/images/avatars/avt.png");
                e.getChannel().sendMessage("@everyone").queue();
                e.getChannel().sendMessage(eb.build()).queue();
            }else{
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(e.getGuild().getName() + " - Rules");
                eb.setDescription("Please follow all our rules to avoid punishment...");
                eb.addField("Do not speak about anything remotely illegal!", "Punishment: Instant Ban", false);
                eb.addField("Do not disrespect staff!", "Punishment: Instant Ban / Mute (Depending on severity)", false);
                eb.addField("Do not advertise any competitors of Stressing Ninja!", "Punishment: Instant Ban", false);
                eb.addField("Follow Discord's Terms of Service and Guidelines at all times!", "Punishment: Instant Ban\nDiscord Terms: https://discordapp.com/terms\nDiscord Guidelines: https://discordapp.com/guidelines", false);
                eb.setFooter("Developed by Stressing Ninja - Copyright (c) 2019, All Rights Reserved.", "https://stressing.ninja/sp/assets/images/avatars/avt.png");
                e.getChannel().sendMessage(eb.build()).queue();
            }
        }
    }
}
