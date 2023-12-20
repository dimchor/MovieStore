package com.mycompany.moviestore;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;


public class MovieStoreStatisticsWindow extends MovieStoreWindow {
    private JLabel numberOfMoviesLabel;
    private JLabel meanMovieDurationLabel;
    private JLabel mostPopularGenreLabel;
    private JLabel numberOfMoviesWithMostPopularGenreLabel; // rather long name, to be honest...
    private JLabel oldestMovieLabel;
    private JLabel newestMovieLabel;

    private JPanel panel;

    public MovieStoreStatisticsWindow(MovieStoreLogic logic) {
        super("Statistics", 480, 160);
        getFrame().setLayout(new FlowLayout());
        
        Statistics statistics = logic.makeStatistics();

        numberOfMoviesLabel = new JLabel("Number of movies: " + statistics.numberOfMovies);
        meanMovieDurationLabel = new JLabel("Mean movie duration: " + statistics.meanMovieDuration);
        mostPopularGenreLabel = new JLabel("Most popular genre: " + statistics.mostPopularGenre);
        numberOfMoviesWithMostPopularGenreLabel = new JLabel("Number of movies with most popular genre: " + statistics.numberOfMoviesWithMostGenreCategory);
        oldestMovieLabel = new JLabel("Oldest movie: " + statistics.titleOldestMovie + " (" + statistics.yearOldestMovie + ")");
        newestMovieLabel = new JLabel("Newest movie: " + statistics.titleNewestName + " (" + statistics.yearNewestMovie + ")");

        panel = new JPanel();

        panel.add(numberOfMoviesLabel);
        panel.add(meanMovieDurationLabel);
        panel.add(mostPopularGenreLabel);
        panel.add(numberOfMoviesWithMostPopularGenreLabel);
        panel.add(oldestMovieLabel);
        panel.add(newestMovieLabel);

        getFrame().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        getFrame().setVisible(true);
    }

    protected void onExit() {
        getFrame().dispose();
    }
}
