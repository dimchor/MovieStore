package com.mycompany.moviestore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class MovieStoreLogic {
    private ArrayList<Movie> movieList;
    private boolean saveFlag;

    public boolean isSaved() {
        return saveFlag;
    }

    public MovieStoreLogic() {
        movieList = new ArrayList<Movie>();
        saveFlag = false;
    }
    
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
        saveFlag = false;
    }

    public void findDuplicate(String title, int year) throws DuplicateMovie {
        for (Movie i : movieList) {
            if (i.title.equals(title) && i.year == year) {
                throw new DuplicateMovie(title, year);
            }
        }
    }

    public void saveToFile(String data) throws IOException {
        String fileName = "movies.txt";
        Scanner sc = new Scanner(data).useDelimiter("A");
        FileWriter wr = new FileWriter(fileName);
        wr.write(data);
        wr.close();
        sc.close();
        saveFlag = true;
    }

    public Statistics makeStatistics() {
        Statistics statistics = new Statistics();
        statistics.numberOfMovies = movieList.size();
        if (statistics.numberOfMovies == 0) {
            return statistics;
        }
        int totalMovieDuration = 0;
        Map<String, Integer> movieGenres = new HashMap<String, Integer>(); // a map that keeps Genre and the number of movies with that genre
        Movie oldestMovie = null;
        Movie newestMovie = null;
        for (Movie i : movieList) {
            totalMovieDuration += i.duration;

            boolean foundGenre = false; // this finds and updates existing genres
            for (Entry<String, Integer> j : movieGenres.entrySet()) {
                if (j.getKey().equals(i.genre)) {
                    foundGenre = true;
                    j.setValue(j.getValue() + 1);
                    break;
                }
            }
            if (!foundGenre) { // if it finds none, then it adds it
                movieGenres.put(i.genre, 1);
            }

            if (oldestMovie == null && newestMovie == null) {
                oldestMovie = i;
                newestMovie = i;
            }
            if (oldestMovie.year > i.year) {
                oldestMovie = i;
            }
            if (newestMovie.year < i.year) {
                newestMovie = i;
            }
        }
        //System.out.println(movieCategories.toString());
        int popularity = 0;
        String mostPopularGenre = new String();
        for (Entry<String, Integer> i : movieGenres.entrySet()) { // last loop find the most popular genre of them all
            if (i.getValue() > popularity) {
                popularity = i.getValue();
                mostPopularGenre = i.getKey();
            }
        }
        statistics.mostPopularGenre = mostPopularGenre;
        statistics.numberOfMoviesWithMostGenreCategory = popularity;
        statistics.titleOldestMovie = oldestMovie.title;
        statistics.yearOldestMovie = oldestMovie.year;
        statistics.titleNewestName = newestMovie.title;
        statistics.yearNewestMovie = newestMovie.year;
        statistics.meanMovieDuration = totalMovieDuration / statistics.numberOfMovies;
        return statistics;
    }

}

class Statistics {
    public int numberOfMovies = 0;
    public int meanMovieDuration = 0;
    public String mostPopularGenre = new String();
    public int numberOfMoviesWithMostGenreCategory = 0; // rather long name, to be honest...
    public String titleOldestMovie = new String();
    public int yearOldestMovie = 0;
    public String titleNewestName = new String();
    public int yearNewestMovie = 0;
}

class DuplicateMovie extends Exception {
    private String title;
    private int year;

    public DuplicateMovie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    @Override
    public String getMessage() {
        return "Duplicate movie \"" + title + " (" + year + ")\".";
    }
    
}
