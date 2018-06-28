package fr.dracoctix.dev.minewarnings.warnings;

import org.bukkit.entity.Player;

import java.util.Date;

public class Warning {
    private Player player;
    private Player moderator;
    private Date start;
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

        start = new Date();
    }

    public Warning(Player player, Player moderator, int days, int points, String description) {
        this.player = player;
        this.moderator = moderator;
        this.days = days;
        this.points = points;
        this.description = description;

        start = new Date();
        justification = null;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
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
