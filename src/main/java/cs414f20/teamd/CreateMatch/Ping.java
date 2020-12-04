package cs414f20.teamd.CreateMatch;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cs414f20.teamd.DatabaseConnection.Database;

public class Ping {
    private String current;
    private String gameID;
    private Timestamp date;
    private String[] players;
    private boolean isNewMatchCreated;

    public Ping(String current, String[] players, String date) {
        this.current = current;
        this.players = players;
        this.date = parseDate(date);
        this.gameID = "";
        this.isNewMatchCreated = pingForNewMatch();
    }

    private java.util.Date subtractOneDay(java.util.Date day){
        GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(day);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
    }

    private Timestamp parseDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Timestamp sqlDate = new java.sql.Timestamp(subtractOneDay(formatter.parse(date)).getTime());
            return sqlDate;
        } catch (ParseException e) {
            System.out.print("An incorrect date has been passed!");
            return null;
        }
    }

    private boolean pingForNewMatch(){
        String gameID = Database.pingNewMatch(current, players, date);
        if(gameID.length() != 0){
            this.gameID = gameID;
            return true;
        }
        return false;
    }

    public String getCurrent() {
        return current;
    }

    public String[] getPlayers() {
        return players;
    }

    public boolean getIsNewMatchCreated(){
        return isNewMatchCreated;
    }

    public String getGameID() {
        return gameID;
    }

    public String getDate() {
        return date.toString();
    }
}
