package Diary_App;

import Diary_App.Exceptions.DiaryIsLockedException;
import Diary_App.Exceptions.NoEntriesException;
import Diary_App.Exceptions.WrongPasswordException;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private boolean isLocked;
    private List<Entry> entries;
    private boolean doesEntryExist;


    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLocked = false;
        this.entries = new ArrayList<>();
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void unlockDiary(String password){
        verifyPassword(password);
        this.isLocked = false;
    }
    public void lockDiary(){
        this.isLocked = true;
    }
    public int count() {
        return entries.size();
    }
    public String getUsername() {
        return username;
    }
    public void createEntry(String title, String body) {
        CheckIfLocked();
        Entry entry = new Entry(entries.size()+1,title, body);
        entries.add(entry);
    }
    public Entry findEntryById(int id){
       CheckIfLocked();
       if(entries.isEmpty())throw new NoEntriesException("No entry in Diary");
       return entries.stream()
               .filter(entry -> entry.getId()==id)
               .findFirst()
               .orElseThrow(()->new IllegalArgumentException("Entry does not exist"));
    }

    public void deleteEntry(int id){
        CheckIfLocked();
        Entry entry = findEntryById(id);
        entries.remove(entry);

        for(int i = 0 ; i < entries.size() ; i++) {
            entries.get(i).setId(i + 1);
        }
    }

     public void updateEntry(int id, String title, String body){
        CheckIfLocked();
        Entry entry = findEntryById(id);
        entry.setTitle(title);
        entry.setBody(body);
    }
    public void viewEntriesInDiary(){
        CheckIfLocked();
        if(entries.isEmpty())throw new NoEntriesException("No entry in Diary");
        entries.forEach(System.out::println);
    }


    private void CheckIfLocked() {
        if(isLocked) throw new DiaryIsLockedException("diary is Locked");
    }

    public void verifyPassword(String password){
        if(!password.equals(this.password)) throw new WrongPasswordException("Wrong password");
    }
}
