import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String description;
    public Note(){}
    public Note(String title, String description){
        this.title = title;
        this.description = description;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String toString() {
        return "Title: " + title + "\nDescription: " + description;
    }
}
