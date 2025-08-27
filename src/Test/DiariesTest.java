package Test;

import Diary_App.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Diary_App.*;

import static org.junit.jupiter.api.Assertions.*;

public class DiariesTest {
    Diaries diaries;
    @BeforeEach
    public void setUp(){
        diaries = new Diaries();
    }
    @Test
    public void newDiariesShelfShouldBeEmpty(){
      assertEquals(0,diaries.count());
    }
    @Test
    public void whenIAddDiaryNoOfDiariesShouldBeOne(){
        diaries.add("username","password");
        assertEquals(1,diaries.count());
    }
    @Test
    public void whenIAddTwoDiariesShouldBeTwo(){
       diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertEquals(2,diaries.count());
    }
    @Test
    public void whenAddTwoDiariesICanFindBoth(){
       diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertEquals(2,diaries.count());
       Diary diary1 = diaries.findByUsername("username");
       assertEquals("username",diary1.getUsername());
       Diary diary2  = diaries.findByUsername("wale");
       assertEquals("wale",diary2.getUsername());
    }
    @Test
    public void whenIFindInAnEmptyDiariesShelfShouldThrowError(){
        assertThrows(NoDiaryException.class,()->diaries.findByUsername("username"));
    }
    @Test
    public void whenIFindByUsernameThatDoesNotExistShouldThrowError(){
       diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertThrows(UserNotFound.class,()->diaries.findByUsername("user"));
    }
    @Test
    public void whenIDeleteDiaryNoOfDiaryShouldReduceByOne(){
       diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertEquals(2,diaries.count());
       diaries.delete("username","password");
       assertEquals(1,diaries.count());
    }
    @Test
    public void whenIDeleteDiaryThatDoesNotExistShouldThrowError(){
       diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertThrows(UserNotFound.class,()->diaries.delete("user","word"));
    }
    @Test
    public void whenIDeleteDiaryWithWrongPasswordShouldThrowError(){
        diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertThrows(WrongPasswordException.class,()->diaries.delete("wale","qwert"));
    }
    @Test
    public void whenIDeleteAllDiariesShelfShouldBeEmpty(){
       diaries.add("username","password");
       diaries.add("wale","qwerty");
       assertEquals(2,diaries.count());
       diaries.delete("username","password");
       diaries.delete("wale","qwerty");
       assertEquals(0,diaries.count());

    }

}
