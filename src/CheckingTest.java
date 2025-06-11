import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class CheckingTest {

    @Test
    @DisplayName("Diary at creation should be unlocked")
    public void CheckIfDiaryIsUnlocked(){
        String userName = "wale";
        String password = "1234";
        Diary diary1 = new Diary(userName,password);
        assertFalse(diary1.isLocked());
    }

    @Test
    public void CheckIfDiaryIsLocked(){
        String userName = "wale";
        String password = "1234";
        Diary diary1 = new Diary(userName,password);
        diary1.lockDiary();
       assertTrue(diary1.isLocked());
    }

    @Test
    public void CheckIfDiaryCanBeLockedAndUnlocked(){
        String userName = "wale";
        String password = "1234";
        Diary diary1 = new Diary(userName,password);
        diary1.lockDiary();
        assertTrue(diary1.isLocked());

        diary1.unlockDiary("1234");
        assertFalse(diary1.isLocked());
    }

    @Test
    @DisplayName("Locked diary for wrong password")
    public void ShouldThrowExceptionIfWrongPassword(){
        String userName = "wale";
        String password = "1234";
        Diary diary1 = new Diary(userName,password);
        diary1.lockDiary();
        assertThrows(IllegalArgumentException.class,
                () -> diary1.unlockDiary("1235"));
    }

      @Test
    public void ThreeEntryShouldExistInEntries(){
          Diary DonaldDiary = new Diary("Donald", "1234");
          DonaldDiary.createEntry("Monday","Today was a great day.I met an old Friend and we talked a lot");
          DonaldDiary.createEntry("Tuesday","I had breakfast,Then I went to the mall to buy glue");
          DonaldDiary.createEntry("Wednesday","I bought a car today and I drove to the museum");
          DonaldDiary.lockDiary();
          DonaldDiary.unlockDiary("1234");
          assertEquals(3,DonaldDiary.getEntries().size());

      }



}