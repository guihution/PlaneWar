package GameFrame;

/*
 * @author Guihution
 * 2020/5/30 11:39
 */
public enum Tip {
    ARMS_LEVEL_UP("武器升级！",1),ENEMY_SPEED_UP("敌机加速！",2);

    private String tip;
    private Integer coed;
    private Tip(String tip,Integer code){
        System.out.println(tip);
        this.tip = tip;
        this.coed = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getCoed() {
        return coed;
    }

    public void setCoed(Integer coed) {
        this.coed = coed;
    }
}
