package Diary_App;

import Diary_App.Exceptions.NoDiaryException;
import Diary_App.Exceptions.UserNotFound;

import java.util.ArrayList;
import java.util.List;

public class Diaries {
    private final List<Diary> diaries;

    public Diaries(){
        this.diaries = new ArrayList<>();
    }
    public int count() {
        return diaries.size();
    }
    public void add(String username,String password){
        Diary diary = new Diary(username,password);
        diaries.add(diary);
    }
    public Diary findByUsername(String username){
        if(diaries.isEmpty())throw new NoDiaryException("No diary in Diaries");
        return diaries.stream()
                .filter(diary -> diary.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new UserNotFound("Diary with username does not exist"));
    }
    public void delete(String username, String password) {
        Diary diary = findByUsername(username);
        diary.verifyPassword(password);
        diaries.remove(diary);
    }
}
