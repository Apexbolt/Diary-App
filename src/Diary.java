import java.util.ArrayList;
import java.util.List;

public class Diary {
   public String username;
   private final String password;
   public boolean isLocked;
   private List<Entry> entries;

    public String getPassword() {
        if(isLocked){                    //can't view password if locked
            throw new IllegalStateException("You cannot view password");
        }
        return password;
    }
    public String getUsername() {
        return username;
    }

    public List<Entry> getEntries() {
        if(isLocked){
            throw new IllegalStateException("Enter Diary password to access entries");
        }
        return entries;
    }

    public Diary(String username, String password){
        this.username = username;
        this.password = password;
        this.isLocked = false;//Diary is not locked at creation   // initially !isLocked
        this.entries = new ArrayList<>();  //for a new diary entries are made by default but
        System.out.println("This is "+username+"'s diary");
    }

    public void unlockDiary(String password){
        if (!password.equals(this.password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        this.isLocked = false;
        System.out.println("Diary has been unlocked");
    }

    public void lockDiary(){
        this.isLocked = true;
        System.out.println("Diary is locked");
        }

    public boolean isLocked(){
        return this.isLocked;
    }

    public void createEntry(String title, String body){
        if (isLocked) {
            throw new IllegalStateException("Diary is locked. Unlock to create an entry.");
        }
        Entry entry = new Entry(entries.size()+1,title,body);
        entries.add(entry);
        System.out.println("Entry "+ entry.getId() +" has been created at "+entry.getDateCreated());
    }

    public void deleteEntry(int id){
        if(isLocked){
            throw new IllegalStateException("Diary is locked. Unlock to delete an entry");
        }
        if (entries.isEmpty()){
            throw new IllegalStateException("No entries to delete");
        }
            //if entry does not exist
          Entry entry = findEntryById(id);
           {
               if (entry == null){
                   throw new IllegalStateException("Entry not found");
           }
           entries.remove(entry);
       }
    }

    public Entry findEntryById(int id){
        for (Entry entry : entries){
            if (entry.id == id){
                return entry;
            }
        }
        return null;
    }

    public void updateEntry(int id ,String title, String body){
        Entry entry = findEntryById(id);  //passing assigning an object of the same class type (seems like cloning an object)
       if (entry != null){
           entry.title = title;
           entry.body += "\n "+ body;
           System.out.println("Entry has been updated");
       } else {
           System.out.println("Entry not found");
       }
    }

    public void DisplayEntries(){
        if(entries.isEmpty()){
            throw new IllegalStateException("No entries to display");
        }
        for (Entry entry : entries){
            System.out.println("\n"+entry.id +"-> "+entry.title);
            System.out.println(entry.body);
        }
    }

    }


