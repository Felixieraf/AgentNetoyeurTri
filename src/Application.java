import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        JFrame fenetre=new JFrame();
        fenetre.setTitle("Simulation evacuation ");
        fenetre.setSize(600,400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        TriJpanel panel=new TriJpanel();
        fenetre.setContentPane(panel);
        fenetre.setVisible(true);
        panel.Lancer();
    }
}
