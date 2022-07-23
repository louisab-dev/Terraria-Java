package achievements;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class FenetreAchievement {
    
    private JFrame frame;
    private JPanel panel;
    private JButton continueButton;

    private JPanel achievementPanel;

    public FenetreAchievement(List<Achievement> achievements) {
        
        frame = new JFrame("Achievements");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1200);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Boutons
        continueButton = new JButton("Continue");

        // Panel des achievements
        achievementPanel = new JPanel();
        achievementPanel.setLayout(new GridLayout(0, 1));
        for (Achievement a : achievements) {
            JPanel achievement = new JPanel();
            achievement.setLayout(new BorderLayout());
            achievement.add(new JLabel("Nom : " + a.getName()), BorderLayout.NORTH);
            achievement.add(new JLabel("Description : " + a.getDescription()), BorderLayout.CENTER);
            achievement.add(new JLabel("Récompense : " + a.getReward().getItem().getClass().getSimpleName()), BorderLayout.SOUTH);
            achievementPanel.add(achievement);

            // Séparateur
            achievementPanel.add(new JSeparator());
        }

        // Ajout des composants
        panel.add(continueButton, BorderLayout.SOUTH);
        panel.add(achievementPanel, BorderLayout.CENTER);

        // Panel
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        // Click sur le bouton start
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

}
