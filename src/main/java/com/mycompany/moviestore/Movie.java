package com.mycompany.moviestore;

public class Movie {
    public int code;
    public String title;
    public int year;
    public int duration;
    public String genre;
    
    public String plotSummary;
    public String language;
    public String location;
    
    public Movie(int code, String title, int year, int duration, String genre, String plotSummary, String language, String location) {
        this.code = code;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.genre = genre;
        this.plotSummary = plotSummary;
        this.language = language;
        this.location = location;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")\n  Code: " + code + ", Duration (minutes): " + duration + ", Genre: " + genre + ", Plot Summary: " + plotSummary + ", Language: " + language + ", Location: " + location + '\n';
    }
}
