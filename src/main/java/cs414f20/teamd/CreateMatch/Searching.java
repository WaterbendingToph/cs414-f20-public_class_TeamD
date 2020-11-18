package cs414f20.teamd.CreateMatch;

import cs414f20.teamd.DatabaseConnection.Database;

public class Searching {
    private String current;
    private boolean searching;

    public Searching(String current) {
        this.current = current;
        setSearching(); 
    }

    public String getCurrent() {
        return current;
    }

    public boolean getSearching(){
        return searching;
    }

    private void setSearching(){
        searching = Database.setSearching();
    }
}
