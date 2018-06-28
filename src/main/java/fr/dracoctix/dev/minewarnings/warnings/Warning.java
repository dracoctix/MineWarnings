package fr.dracoctix.dev.minewarnings.warnings;

import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Warning {
    private Player player;
    private Player moderator;
    private Calendar start;
    private int days;
    private int points;
    private String description;
    private String justification;

    public Warning(Player player, Player moderator, int days, int points, String description, String justification) {
        this.player = player;
        this.moderator = moderator;
        this.days = days;
        this.points = points;
        this.description = description;
        this.justification = justification;

        start = Calendar.getInstance();
    }

    public Warning(Player player, Player moderator, int days, int points, String description) {
        this.player = player;
        this.moderator = moderator;
        this.days = days;
        this.points = points;
        this.description = description;

        start = Calendar.getInstance();
        justification = null;
    }

    public Calendar getEnd() {
        Calendar end = (Calendar)start.clone();
        end.set(Calendar.DAY_OF_MONTH,start.get(Calendar.DAY_OF_MONTH) + days);
        return end;
    }

    public String startToString() {
        return calendarToString(getStart());
    }

    public String endToString() {
        return calendarToString(getEnd());
    }

    private static String calendarToString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        return sdf.format(calendar.getTime());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getModerator() {
        return moderator;
    }

    public void setModerator(Player moderator) {
        this.moderator = moderator;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
