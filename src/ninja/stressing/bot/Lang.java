package ninja.stressing.bot;

public enum Lang {

    ERR_NO_PERMISSION("You do not have permission to execute this command....");

    private String content;

    Lang(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

}
