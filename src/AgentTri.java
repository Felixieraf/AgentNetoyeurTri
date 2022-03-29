import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

public class AgentTri extends  Objet{
    protected final static double PAS =3;
    protected final static double PROB_CHGT_DIRECTION=0.1;
    protected Dechet charge;
    protected Dechet cible;
    private double vitesseX;
    private double vitesseY;
    protected boolean occupe=false;

    protected void Normaliser(){
        vitesseX= cible.posX -posX;
        vitesseY= cible.posY-posY;
        double longueur=Math.sqrt(vitesseX*vitesseX+vitesseY*vitesseY);
        vitesseX/=longueur;
        vitesseY/=longueur;

    }
    public AgentTri(double _posX,double _posY){
        posX=_posX;
        posY=_posY;
        cibleAleatoire();

    }
    private void cibleAleatoire(){
        vitesseX=(Environnement.getInstance().generateur.nextDouble()*Environnement.getInstance().getLargeur());
        vitesseY=(Environnement.getInstance().generateur.nextDouble()*Environnement.getInstance().getHauteur());
        double longueur=Math.sqrt(vitesseX*vitesseX+vitesseY*vitesseY);
        vitesseX/=longueur;
        vitesseY/=longueur;
    }
    public boolean estCharge(){
        return charge!=null;
    }

    public void MiseAjourPosition() {
        posX += PAS * vitesseX;
        posY += PAS * vitesseY;
        double largeur = Environnement.getInstance().getLargeur();
        double hauteur = Environnement.getInstance().getHauteur();
        if (posX < 0) {
            posX = 0;
        } else if (posX > largeur) {
            posX = largeur;
        }
        if (posY < 0) {
            posY = 0;
        } else if (posY > hauteur) {
            posY = hauteur;
        }
    }
    protected void MiseAjourDirection(ArrayList<Dechet> dechets){
        //Ou aller?
        ArrayList<Dechet> dansZone=new ArrayList<>();
        dansZone.addAll(dechets);
        Predicate<Dechet> condition = d -> Distance(d)<d.ZoneInfluence();
        dansZone.removeIf(condition);
        Collections.sort(dansZone,(Dechet d1, Dechet d2)->(Distance(d1)<Distance(d2)?-1:1));
        Dechet but=null;
        if(cible!=null){
            dansZone.removeIf(d->(d.type !=cible.type));
        }
        if(!dansZone.isEmpty()){
            cible= dansZone.get(0);
          /*vitesseX=but.posX -posX;
            vitesseY=but.posY-posY;*/
            Normaliser();
        }
        //Avont un but?
        if(occupe && cible== null){
            occupe=false;
        }
        if(cible==null ||occupe){
            //Déplacement aléatoire
            if(Environnement.getInstance().generateur.nextDouble()<PROB_CHGT_DIRECTION){
                cibleAleatoire();
            }
        }
        else{
            //Aller au but

            //but atteint
            //A corriger
            if(Distance(cible)<=20){
                System.out.println("but atteint");
                Environnement.getInstance().PrendreDechet(cible);
                charge=cible;
                cible=null;
                occupe=false;
            }
            else{

                Normaliser();
            }

        }
    }


}
