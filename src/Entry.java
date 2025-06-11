import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    public int id;
    public String title;
    public String body;
    public LocalDateTime dateCreated;

    public int getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getBody() {
        return this.body;
    }
    public String getDateCreated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateCreated.format(formatter);
    }

    public Entry(int id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateCreated = LocalDateTime.now();
    }

}
