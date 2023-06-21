package Model;

public class BasicFile {
    private String name;
    private String path;
    private final String type;
    private String size;
    private final String dateCreated;
    private String dateModified;
    private int layer;


    public BasicFile(String name, String type, String size, String dateCreated, String dateModified) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void rename(String name){
        this.name = name;
    }

    public void move(String path){
        this.path = path;
    }

    public void updateSize(String size){
        this.size = size;
    }

    public void modify(String date){
        dateModified = date;
    }
}
