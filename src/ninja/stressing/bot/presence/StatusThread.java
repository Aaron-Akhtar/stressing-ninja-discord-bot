package ninja.stressing.bot.presence;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;

public class StatusThread implements Runnable{

    private JDA jda;

    public StatusThread(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void run() {
        Thread thread = new Thread("Status Thread"){
            @Override
            public void run(){
                while(true){
                    jda.getPresence().setGame(Game.playing("https://stressing.ninja"));
                    try{Thread.sleep(1000 * 120);} catch (InterruptedException e){}
                    jda.getPresence().setGame(Game.watching("over everyone"));
                    try{Thread.sleep(1000 * 120);} catch (InterruptedException e){}
                }
            }
        };
        thread.start();
    }
}
