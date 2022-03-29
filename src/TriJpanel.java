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
    public void Lancer(){
        env=Environnement.getInstance();
        env.Initaliser(20,10,getWidth(),getHeight(),2);
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
            timer.scheduleAtFixedRate(tache,10,100);
            enCours=true;
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
    int agentsCharge=10;
    for(AgentTri a: env.agents){
        if(a.estCharge()){
            agentsCharge++;
        }
    }
    //System.out.println(env.dechets.size() +"-"+agentsCharge);
    }

    public void DessinerAgent(AgentTri agent, Graphics g){
        if(agent.estCharge()){
            g.setColor(Color.GRAY);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.fillRect((int) agent.posX-1,(int)agent.posY-1,5,5);
    }
    public void DessinerDechet(Dechet d, Graphics g){
        Color couleur ;
        switch (d.type){
            case 1:
                couleur=Color.RED;
            case 2:
                couleur=Color.GREEN;
            default:
                couleur=Color.BLUE;
                break;
        }
        g.setColor(couleur);
        g.fillRect((int)d.posX-1,(int)d.posY-1,5,5);
        couleur=new Color(couleur.getRed(),couleur.getGreen(),couleur.getBlue(),50);
        g.setColor(couleur);
        int zone=d.ZoneInfluence();
        g.fillOval((int)d.posX-zone,(int)d.posY-zone,zone*2,zone*2);

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(AgentTri agent:env.agents){
            DessinerAgent(agent,g);
        }
        for(Dechet dechet:env.dechets){
            DessinerDechet(dechet,g);
        }
    }
}
