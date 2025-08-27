package Test;

import Diary_App.*;
import Diary_App.Entry;
import Diary_App.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {
    Diary diary;
    @BeforeEach
    public void setUp(){
         diary = new Diary("user","password");
    }
    @Test
    public void newDiaryIsUnlocked(){
       assertFalse(diary.isLocked());
    }
    @Test
    public void newDiaryCanBeLocked(){
        diary.lockDiary();
        assertTrue(diary.isLocked());
    }
    @Test
    public void diaryCanBeLockedAndUnlocked(){
        diary.lockDiary();
        diary.unlockDiary("password");
        assertFalse(diary.isLocked());
    }
    @Test
    public void diaryCannotBeUnlockedWithWrongPassword(){
        diary.lockDiary();
        assertThrows(WrongPasswordException.class, () -> diary.unlockDiary("wrong"));
    }
    @Test
    public void newDiaryIsEmpty(){
       assertEquals(0,diary.count());
    }
    @Test
    public void newNoOfEntriesIsOneWhenICreateNewEntry(){
        diary.createEntry("Monday","I bought a car");
        assertEquals(1,diary.count());
    }
    @Test
    public void newNoOfEntriesIsTwoWhenICreateTwoNewEntries(){
        diary.createEntry("Monday","I bought a car");
        diary.createEntry("Tuesday","I cooked spaghetti");
        assertEquals(2,diary.count());
    }
    @Test
    public void whenICreateEntryICouldFindThatEntry(){
        diary.createEntry("Monday","I bought a car");
        Entry entry = diary.findEntryById(1);
        assertEquals("Monday",entry.getTitle());
        assertEquals("I bought a car",entry.getBody());
    }
    @Test
    public void cannotCreateEntryIfDiaryIsLocked(){
        diary.lockDiary();
        assertThrows(DiaryIsLockedException.class,()->diary.createEntry("Monday","I bought a car"));
    }
    @Test
    public void createTwoEntryAndICouldFindBothEntry() {
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        Entry entry = diary.findEntryById(1);
        assertEquals("Monday", entry.getTitle());
        assertEquals("I bought a car", entry.getBody());
        Entry entry2 = diary.findEntryById(2);
        assertEquals("Tuesday", entry2.getTitle());
        assertEquals("I went driving", entry2.getBody());

    }
        @Test
    public void createTwoEntryAndICouldFindBothEntryAndEntrySizeIsTwo() {
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        Entry entry = diary.findEntryById(1);
        assertEquals("Monday", entry.getTitle());
        assertEquals("I bought a car", entry.getBody());
        Entry entry2 = diary.findEntryById(2);
        assertEquals("Tuesday", entry2.getTitle());
        assertEquals("I went driving", entry2.getBody());
        assertEquals(2,diary.count());
    }
    @Test
    public void ifEntryDoesNotExistAndIFindItShouldThrowError(){
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        assertThrows(IllegalArgumentException.class,()->diary.findEntryById(3));
    }
    @Test
    public void ifEntriesIsEmptyAndIFIndShouldThrowError(){
        assertThrows(NoEntriesException.class,()->diary.findEntryById(3));
    }
    @Test
    public void whenIDeleteEntryFromEntriesEntrySizeShouldReduceByOne(){
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        assertEquals(2,diary.count());
        diary.deleteEntry(2);
        assertEquals(1,diary.count());
    }
    @Test
    public void whenIDeleteEntryItShouldBeThatEntryRemovedFromEntries(){
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        diary.createEntry("Wednesday","I cooked Rice");
        assertEquals(3,diary.count());
        diary.deleteEntry(2);
        Entry entry = diary.findEntryById(1);
        assertEquals("Monday", entry.getTitle());
        assertEquals("I bought a car", entry.getBody());
        Entry entry2 = diary.findEntryById(2);
        assertEquals("Wednesday", entry2.getTitle());
        assertEquals("I cooked Rice", entry2.getBody());
        assertEquals(2,diary.count());

    }
    @Test
    public void canDeleteAllEntriesAndCreateEntry(){
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        diary.createEntry("Wednesday","I cooked Rice");
        assertEquals(3,diary.count());
        diary.deleteEntry(3);
        diary.deleteEntry(2);
        diary.deleteEntry(1);
        assertEquals(0,diary.count());
        diary.createEntry("Monday", "I bought a car");
        assertEquals(1,diary.count());
    }
    @Test
    public void whenIDeleteEntryWhichDoesNotExistItShouldThrowError(){
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        assertThrows(IllegalArgumentException.class,()->diary.deleteEntry(3));
    }
    @Test
    public void ifDiaryIsEmptyAndDeleteEntryShouldThrowError(){
       assertThrows(NoEntriesException.class,()->diary.deleteEntry(1));
    }
    @Test
    public void whenUpdateEntryItShouldReflect(){
        diary.createEntry("Monday", "I bought a car");
        diary.createEntry("Tuesday","I went driving");
        diary.updateEntry(2,"Tuesday--noon","I bought groceries");
        Entry entry2 = diary.findEntryById(2);
        assertEquals("Tuesday--noon", entry2.getTitle());
        assertEquals("I bought groceries", entry2.getBody());

    }
    @Test
    public void cannotUpdateNonExistentEntryShouldThrowError(){
       diary.createEntry("Monday", "I bought a car");
       assertThrows(IllegalArgumentException.class,()-> diary.updateEntry(15,"Tuesday--noon","I bought groceries"));
    }
}
