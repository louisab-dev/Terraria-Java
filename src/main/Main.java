package main;

import javax.swing.*;

import achievements.Achievement;
import achievements.AchievementManager;
import achievements.FenetreAchievement;

import java.awt.*;
import java.awt.event.*;

public class Main {
    
    private JFrame frame;
    private JPanel panel;
    private JButton startButton;
    private JButton quitButton;
    private JButton achievementsButton;

    // Selecteur de texture
    private JComboBox<String> textureSelector;

    // Selecteur de mode de jeu
    private JComboBox<String> modeSelector;

    public Main() {
        
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1200);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Boutons
        JPanel boutons = new JPanel();
        boutons.setLayout(new GridLayout(0, 1));
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        achievementsButton = new JButton("Achievements");
        boutons.add(achievementsButton);
        boutons.add(startButton);
        boutons.add(quitButton);


        // Choix des textures
        JPanel texturePanel = new JPanel();
        texturePanel.setLayout(new FlowLayout());
        JLabel textureLabel = new JLabel("Texture : ");
        textureSelector = new JComboBox<String>();
        textureSelector.addItem("classique");
        textureSelector.addItem("simple");
        texturePanel.add(textureLabel);
        texturePanel.add(textureSelector);

        // Choix du mode de jeu
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new FlowLayout());
        JLabel modeLabel = new JLabel("Mode de jeu : ");
        modeSelector = new JComboBox<String>();
        modeSelector.addItem("classique");
        modeSelector.addItem("decouverte");
        modePanel.add(modeLabel);
        modePanel.add(modeSelector);


        // Ajout des composants
        panel.add(boutons, BorderLayout.SOUTH);
        panel.add(texturePanel, BorderLayout.CENTER);
        panel.add(modePanel, BorderLayout.NORTH);

        // Panel
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        // Click sur le bouton start
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // On récupère le mode de jeu
                String mode = modeSelector.getSelectedItem().toString();

                // On récupère la texture
                String texture = textureSelector.getSelectedItem().toString();

                // On lance le jeu
                Game.main(new String[] {mode, texture});

                // On ferme la fenêtre
                frame.dispose();

            }
        });

        // Click sur le bouton quit
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Click sur le bouton achievements
        achievementsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FenetreAchievement(AchievementManager.getAchievementsToDisplay());
            }
        });

    }

    public static void main(String[] args) {
        new Main();
    }

}
