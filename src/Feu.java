import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Feu {
    private String couleur;
    private final Lock verrou = new ReentrantLock();
    public  String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public  void changerCouleur() {
        if (couleur.equals("rouge")) {
            couleur = "vert";
        } else {
            couleur = "rouge";
        }
        notifyAll();
    }    public void run() {
            while(couleur.equals("vert")){

            }
            try {
                System.out.println("Le feu est " + couleur);
                Thread.sleep(2000);
                changerCouleur();
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
                notify();
            }

        }
    }


