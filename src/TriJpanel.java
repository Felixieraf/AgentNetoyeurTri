import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.TimerTask;
import java.util.Timer;

public class TriJpanel extends JPanel implements PropertyChangeListener, MouseListener {
    Timer timer;
    boolean enCours=false;
    TimerTask tache;
    Environnement env;
    public TriJpanel(){
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
    }
    pubic void Lancer(){
        env=Environnement.getInstance();
        env.Initaliser(50,30,getWidth(),getHeight(),3);
        env.AjouterChangeListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(enCours){
            timer.cancel();
            timer=null;
            enCours=false;
        }
        else{
            timer=new Timer();
            tache=new TimerTask() {
                @Override
                public void run() {
                    env.MiseAjour();
                }
            };
            timer.scheduleAtFixedRate(tache,0,10);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    this.repaint();
    int agentsCharge=0;
    for(AgentTri a: env.agents){
        if(a.estCharge()){
            agentsCharge++;
        }
    }
    System.out.println(env.dechets.size() +"-"+agentsCharge);
    }
}
