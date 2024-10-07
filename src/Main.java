//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import static java.lang.Thread.*;
public class Main {
    public static void main(String[] args) {
        Feu feu = new Feu();
        feu.changerCouleur();
        Pietons p=new Pietons(true,12,13);
        Vehicules v=new Vehicules(false,15,15);
        /*System.out.println(p.testerMovement(feu));
        System.out.println(v.calculerDistance(p));
        System.out.println(v.calculerAngle(p));
        System.out.println(v.verifierArret(p,3,180));*/
        Thread threadVehicules=new Thread();
        threadVehicules.start();

    }
}