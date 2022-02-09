import java.security.spec.EncodedKeySpec;

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
}
