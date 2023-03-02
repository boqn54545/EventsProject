package com.example.EventsProject.Enums;

public enum Interest {
    SPORTS("Sports"),
    MUSIC("Music"),
    FOOD("Food"),
    TRAVEL("Travel"),
    ART("Art"),
    TECHNOLOGY("Technology"),
    FASHION("Fashion"),
    OUTDOORS("Outdoors");

    private final String Interests;

    Interest(String Interest) {
        this.Interests = Interest;
    }

    public String getInterests() {
        return Interests;
    }
}

