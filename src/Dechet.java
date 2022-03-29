public class Dechet extends  Objet{
    protected final static double DECROISSANCE=0.6;
    protected int type;
    protected int taille=1;

    public int getType(){return type;}
    public int getTaille(){return  taille;}
    public Dechet(double _posX,double _posY,int _type){
        type=_type;
        posX=_posX;
        posY=_posY;
        }
    public  Dechet(Dechet d){
        type=d.type;
        posX=d.posX;
        posY=d.posY;
    }
    public int ZoneInfluence(){
       return 10+8*(taille-1) ;
    }
    public void AugmenteTaille(){
         taille++;
    }
    public void DiminueTaille(AgentTri at){
        int macharge = taille;
         if (macharge > at.chargeRestante()) {
             //si le déchet est plus gros que le camion
             taille -= at.chargeRestante();
             //il est réduit de la taille du camion
             at.remplir(at.chargeRestante());
             //Et le camion est plein
         } else {
             //Et sinon, je me vide complétemetn dans le camion
             at.remplir(taille);
             taille=0;
         }

    }
    protected double ProbadePrendre(){
        return Math.pow(DECROISSANCE,taille-1);
    }
    
}
