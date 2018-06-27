package fr.dracoctix.dev.warnings.warnings;

import org.bukkit.entity.Player;

import java.util.Date;

public class Warning {
    private Player joueur;
    private Player moderator;
    private Date start;
    private int days;
    private int points;
    private String description;
    private String justification;

    public Warning(Player joueur, Player moderator, int days, int points, String description, String justification) {
        this.joueur = joueur;
        this.moderator = moderator;
        this.days = days;
        this.points = points;
        this.description = description;
        this.justification = justification;

        start = new Date();
    }
}
