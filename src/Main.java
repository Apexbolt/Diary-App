import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Diary DonaldDiary = new Diary("Donald", "1234");

        DonaldDiary.createEntry("Monday","Today was a great day.I met an old Friend and we talked a lot");
        DonaldDiary.createEntry("Tuesday","I had breakfast,Then I went to the mall to buy glue");
        DonaldDiary.createEntry("Wednesday","I bought a car today and I drove to the museum");

         DonaldDiary.deleteEntry(1);

        DonaldDiary.DisplayEntries();


        Diaries shelf1 = new Diaries();
        shelf1.add("Donald","1234");
        shelf1.add("Wale","1234");

        shelf1.delete("Donald","1234");

    }
}



//Check If entry exist and Check Content
//Check how many entry in entries
//