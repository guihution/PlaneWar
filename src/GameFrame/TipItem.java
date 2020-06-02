package GameFrame;

/*
 * @author Guihution
 * 2020/6/1 16:24
 */
public class TipItem {
    private String content;
    private int time = 0;

    public TipItem(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
