import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Environnement {
    //Gestion du sigleton
    private static Environnement instance;

    public static  Environnement getInstance(){
        if(instance==null)
        {
            instance=new Environnement();

        }
        return instance;
    }
    public void AjouterChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
        support.addPropertyChangeListener(pcl);
    }
    protected Random generateur;
    protected double largeur;
    protected double hauteur;
    protected ArrayList<Dechet>dechets;
    protected ArrayList<AgentTri>agents;
    protected int nbIterations=0;
    private PropertyChangeSupport support;

    private Environnement(){
        dechets=new ArrayList<>();
        agents=new ArrayList<>();
        generateur=new Random();
        support=new PropertyChangeSupport(this);
    }
    public void Initaliser(int _nbDechets,int _nbAgents,double _largeur,double _hauteur, int _nbTypesDechets){
        largeur=_largeur;
        hauteur=_hauteur;
        dechets.clear();
        for(int i=0;i<_nbDechets; i++){
            Dechet dechet=new Dechet(generateur.nextDouble()*largeur, generateur.nextDouble()*hauteur,generateur.nextInt(_nbTypesDechets));
            dechets.add(dechet);
        }
        agents.clear();
        for(int i=0;i<_nbAgents;i++){
            AgentTri agent=new AgentTri(generateur.nextDouble()*largeur,generateur.nextDouble()*hauteur);
            agents.add(agent);
        }
    }
    public double getLargeur(){return largeur;}
    public double getHauteur(){return hauteur;}
    public void PoserDechet(Dechet d){d.AugmenteTaille();}
    public void PrendreDechet(Dechet d, AgentTri at){
            d.DiminueTaille(at);
            if (d.taille==0) {
                dechets.remove(d);
            }
    }
    public void MiseAjour(){
        for(AgentTri agent:agents){
            agent.MiseAjourDirection(dechets);
            agent.MiseAjourPosition();
        }
        support.firePropertyChange("changed",nbIterations,nbIterations+1);
        nbIterations++;
        if(nbIterations % 500==0){
            Collections.reverse(dechets);
        }
    }
}
