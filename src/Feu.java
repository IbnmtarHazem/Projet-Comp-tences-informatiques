public class Feu {
    private String couleur="rouge";

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void changerCouleur() {
        if (couleur.equals("rouge")) {
            couleur = "vert";
        } else {
            couleur = "rouge";
        }
    }    public void run() {

            try {
                System.out.println("Le feu est " + couleur);
                Thread.sleep(2000);
                changerCouleur();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


