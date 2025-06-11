import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Diaries {
    List<Diary> diaries = new ArrayList<>();

    public void add(String username, String password){
           diaries.add(new Diary(username,password));
    }
   public Diary findByUsername(String username){

        for(Diary diary: diaries){
                if(diary.username.equals(username)){
                    return diary;
              }
        }
        return null;
   }
    public void delete(String username,String password){
        if(diaries.isEmpty()){throw new IllegalStateException("Cannot delete. Diaries is empty");}
         Diary diary = findByUsername(username);
        if(diary == null){throw new IllegalStateException("Cannot delete. Diary not found");}
         if(!diary.getPassword().equals(password)){throw new IllegalArgumentException("Wrong password");}
         diaries.remove(diary);
    }

    public void DisplayDiaries(){
        for(Diary diary: diaries){
            System.out.println(diary.username +"'s diary");
            if(diaries.isEmpty()){
                throw new IllegalStateException("No entries to display");
            }
        }
    }
}
