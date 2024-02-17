import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.awt.image.BufferedImage;

public class SceneManager extends JFrame {
    private JPanel scenePanel;
    private String currentScene;

    public SceneManager() {
        this.currentScene = "MainMenu";
        this.scenePanel = new JPanel();
        add(scenePanel);

        setTitle("Scene Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void loadScene(String sceneName) {
        this.currentScene = sceneName;
        System.out.println("Loading scene: " + sceneName);
        switch (sceneName) {
            case "MainMenu":
                showMainMenu();
                break;
            case "GameScene":
                showGameScene();
                break;
            // Add more cases for other scenes as needed
        }
    }

    private void showMainMenu() {
        // Load and display the main menu image
        displayImage("gamemenu.jpg");
    }

    private void showGameScene() {
        // Load and display the game scene image
        displayImage("game_scene.jpg");
    }

    private void displayImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            scenePanel.removeAll();
            scenePanel.add(imageLabel);
            scenePanel.revalidate();
            scenePanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Current Scene: " + currentScene);
            System.out.println("Enter scene name to load (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting scene manager.");
                break;
            }

            loadScene(input);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        SceneManager sceneManager = new SceneManager();
        sceneManager.run();
    }
}
