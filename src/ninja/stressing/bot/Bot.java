package ninja.stressing.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import ninja.stressing.bot.listeners.commands.Accept;
import ninja.stressing.bot.listeners.commands.Announce;
import ninja.stressing.bot.listeners.commands.Deny;
import ninja.stressing.bot.listeners.commands.Rules;
import ninja.stressing.bot.listeners.events.Join;
import ninja.stressing.bot.presence.StatusThread;

import javax.security.auth.login.LoginException;

public class Bot {

    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(AccountType.BOT).setToken("token here noob").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        new Thread(new StatusThread(jda)).start();
        eventListeners(jda);
    }

    private static void eventListeners(JDA jda){
        jda.addEventListener(new Accept(jda));
        jda.addEventListener(new Deny(jda));
        jda.addEventListener(new Join());
        jda.addEventListener(new Rules(jda));
        jda.addEventListener(new Announce(jda));
    }

}
