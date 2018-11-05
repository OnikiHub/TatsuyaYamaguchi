package jp.ac.asojuku.tatsuyayamaguchi;

public class UcData {
    private String osakename;
    private int value;
    private String description;
    private list<>btlist;
    //constructor
    public UcData(String osakename, int value, String description, list btlist){
        this.osakename = osakename;
        this.value = value;
        this.description = description;
        this.btlist = btlist;
    }

    public String getOsakename(){
        return osakename;
    }
    public int getValue(){
        return value;
    }
    public String getDescription(){
        return description;
    }

    public list<> getBtlist() {
        return btlist;
    }
}
