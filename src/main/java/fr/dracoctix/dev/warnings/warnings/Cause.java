package fr.dracoctix.dev.warnings.warnings;

public class Cause {
    private String id;
    private int points;
    private int expirationTime;
    private String description;

    public Cause(String id, int points, int expirationTime, String description) {
        this.id = id;
        this.points = points;
        this.expirationTime = expirationTime;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
