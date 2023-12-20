package com.mycompany.moviestore;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MovieStoreMainWindow extends MovieStoreWindow {
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JLabel durationLabel;
    private JLabel genreLabel;
    private JLabel plotSummaryLabel;
    private JLabel languageLabel;
    private JLabel locationLabel;

    private JTextField titleTextField;
    private JTextField yearTextField;
    private JTextField durationTextField;
    private JTextField genreTextField;
    private JTextField plotSummaryTextField;
    private JTextField languageTextField;
    private JTextField locationTextField;

    private JPanel titlePanel;
    private JPanel yearPanel;
    private JPanel durationPanel;
    private JPanel genrePanel;
    private JPanel plotSummaryPanel;
    private JPanel languagePanel;
    private JPanel locationPanel;

    private JButton addMovieButton;
    private JButton clearFieldsButton;

    private JPanel buttonsPanel;

    private JTextArea moviesArea;

    private JMenuBar menuBar;

    private JMenu movieStoreMenu;
    private JMenuItem saveMenuItem;
    private JMenuItem statisticsMenuItem;
    private JMenuItem closeMenuItem;
    
    private JMenu aboutMenu;
    private JMenuItem aboutMenuItem;

    private JPanel westPanel;
    private JPanel eastPanel;

    private MovieStoreLogic logic;

    public MovieStoreMainWindow(MovieStoreLogic logic) {
        super("MovieStore", 1280, 720);
        this.logic = logic;
        setUpForm();

        addMovieButton = new JButton("Add movie");
        addMovieButton.addActionListener((ActionEvent e) -> {
            try {
                // empty string and invalid number check
                String title = titleTextField.getText();
                if (title.isEmpty()) {
                    throw new EmptyString(titleLabel.getText());
                }
                int year = Integer.parseInt(yearTextField.getText());
                int duration = Integer.parseInt(durationTextField.getText());
                String genre = genreTextField.getText();
                if (genre.isEmpty()) {
                    throw new EmptyString(genreLabel.getText());
                }
                String plotSummary = plotSummaryTextField.getText();
                if (plotSummary.isEmpty()) {
                    throw new EmptyString(plotSummaryLabel.getText());
                }
                String language = languageTextField.getText();
                if (language.isEmpty()) {
                    throw new EmptyString(languageLabel.getText());
                }
                String location = locationTextField.getText();
                if (location.isEmpty()) {
                    throw new EmptyString(locationLabel.getText());
                }
                // duplication check
                logic.findDuplicate(title, year);
                // there should be no errors here
                Movie movie = new Movie(logic.getMovieList().size() + 1, title, year, duration, genre, plotSummary, language, location);
                moviesArea.append(movie.toString());
                logic.addMovie(movie);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Couldn't parse number. " + ex.getMessage(), "Invalid input", JOptionPane.ERROR_MESSAGE);
            } catch (EmptyString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Empty String", JOptionPane.ERROR_MESSAGE);
            } catch (DuplicateMovie ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Duplicate Movie", JOptionPane.ERROR_MESSAGE);
            }
        });
        clearFieldsButton = new JButton("Clear fields");
        clearFieldsButton.addActionListener((ActionEvent e) -> {
            titleTextField.setText("");
            yearTextField.setText("");
            durationTextField.setText("");
            genreTextField.setText("");
            plotSummaryTextField.setText("");
            languageTextField.setText("");
            locationTextField.setText("");
        });

        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(clearFieldsButton);
        buttonsPanel.add(addMovieButton);

        moviesArea = new JTextArea(0, 50);
        moviesArea.setLineWrap(true);
        moviesArea.setWrapStyleWord(true);

        menuBar = new JMenuBar();
        movieStoreMenu = new JMenu("MovieStore");
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener((ActionEvent e) -> {
            try {
                logic.saveToFile(moviesArea.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Couldn't save file", "File error", JOptionPane.ERROR_MESSAGE);
            }
        });
        statisticsMenuItem = new JMenuItem("Statistics");
        statisticsMenuItem.addActionListener((ActionEvent e) -> {
            new MovieStoreStatisticsWindow(logic);
        });
        closeMenuItem = new JMenuItem("Close");
        closeMenuItem.addActionListener((ActionEvent e) -> {
            onExit();
        });

        movieStoreMenu.add(saveMenuItem);
        movieStoreMenu.add(statisticsMenuItem);
        movieStoreMenu.add(closeMenuItem);

        aboutMenu = new JMenu("About");
        aboutMenuItem = new JMenuItem("About MovieStore");
        aboutMenuItem.addActionListener((ActionEvent e) -> {
            new MovieStoreAboutWindow();
        });

        aboutMenu.add(aboutMenuItem);

        menuBar.add(movieStoreMenu);
        menuBar.add(aboutMenu);
        getFrame().setJMenuBar(menuBar);

        westPanel = new JPanel() {
            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        westPanel.add(titlePanel);
        westPanel.add(yearPanel);
        westPanel.add(durationPanel);
        westPanel.add(genrePanel);
        westPanel.add(plotSummaryPanel);
        westPanel.add(languagePanel);
        westPanel.add(locationPanel);
        westPanel.add(buttonsPanel);
        eastPanel = new JPanel(new FlowLayout());
        eastPanel.add(moviesArea);
        getFrame().setLayout(new GridLayout(0, 2));
        getFrame().add(westPanel);
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
        getFrame().add(eastPanel);
        getFrame().setVisible(true);
    }

    private void setUpForm() {
        titleLabel = new JLabel("Title: ");
        yearLabel = new JLabel("Year released: ");
        durationLabel = new JLabel("Duration (minutes): ");
        genreLabel = new JLabel("Genre: ");
        plotSummaryLabel = new JLabel("Plot summary: ");
        languageLabel = new JLabel("Language: ");
        locationLabel = new JLabel("Location: ");
        
        titleTextField = new JTextField(10);
        yearTextField = new JTextField(10);
        durationTextField = new JTextField(10);
        genreTextField = new JTextField(10);
        plotSummaryTextField = new JTextField(10);
        languageTextField = new JTextField(10);
        locationTextField = new JTextField(10);

        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);
        titlePanel.add(titleTextField);
        yearPanel = new JPanel(new FlowLayout());
        yearPanel.add(yearLabel);
        yearPanel.add(yearTextField);
        durationPanel = new JPanel(new FlowLayout());
        durationPanel.add(durationLabel);
        durationPanel.add(durationTextField);
        genrePanel = new JPanel(new FlowLayout());
        genrePanel.add(genreLabel);
        genrePanel.add(genreTextField);
        plotSummaryPanel = new JPanel(new FlowLayout());
        plotSummaryPanel.add(plotSummaryLabel);
        plotSummaryPanel.add(plotSummaryTextField);
        languagePanel = new JPanel(new FlowLayout());
        languagePanel.add(languageLabel);
        languagePanel.add(languageTextField);
        locationPanel = new JPanel(new FlowLayout());
        locationPanel.add(locationLabel);
        locationPanel.add(locationTextField);
    }

    class EmptyString extends Exception {
        private String name;

        public EmptyString(String name) {
            this.name = name;
        }

        @Override
        public String getMessage() {
            return "No input at \"" + name + "\"";
        }
    }

    protected void onExit() {
        if (!logic.isSaved()) {
            int ans = JOptionPane.showConfirmDialog(null, "You have unsaved changes. Are you sure you want to exit?");
            if (ans == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            return;
        }
        getFrame().dispose();
    }
}
