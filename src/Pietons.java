import static java.lang.Math.*;

public class Pietons extends Thread {
    private boolean enMovementP;
    private double axeXP;
    private double axeYP;

    public Pietons(boolean enMovementP, double axeXP, double axeYP) {
        this.enMovementP = enMovementP;
        this.axeXP = axeXP;
        this.axeYP = axeYP;
    }

    public Pietons() {
        this.enMovementP = false;  // Par défaut, le piéton ne bouge pas
        this.axeXP = 0;
        this.axeYP = 0;
    }

    public boolean isEnMovementP() {
        return enMovementP;
    }

    public double getAxeXP() {
        return axeXP;
    }

    public double getAxeYP() {
        return axeYP;
    }

    // Vérifie si le piéton peut se déplacer en fonction du feu
    public boolean testerMovement(Feu feu) {
        if (feu.getCouleur().equalsIgnoreCase("rouge")) {  // Si le feu est rouge, le piéton peut traverser
            return this.enMovementP = true;
        } else {
            return this.enMovementP = false;
        }
    }

    // Calcul de l'angle entre deux piétons
    public double calculerAnglePP(Pietons autrePieton) {
        double dx = autrePieton.getAxeXP() - this.axeXP;
        double dy = autrePieton.getAxeYP() - this.axeYP;
        return atan2(dy, dx);
    }

    // Calcul de la distance entre deux piétons
    public double calculerDistancePP(Pietons autrePieton) {
        double dx = this.axeXP - autrePieton.getAxeXP();
        double dy = this.axeYP - autrePieton.getAxeYP();
        return sqrt(pow(dx, 2) + pow(dy, 2));
    }

    // Vérifie si le piéton doit s'arrêter en fonction d'un véhicule
    public boolean verifierArretVV(Vehicules vehicule, double distanceP, double angleP) {
        double distancePP = calculerDistanceVV(vehicule);  // Distance entre piéton et véhicule
        double angleVV = toDegrees(calculerAngleVV(vehicule));  // Angle entre piéton et véhicule

        if (distancePP <= distanceP && abs(angleVV) <= angleP) {
            return this.enMovementP = false;  // Si le véhicule est trop proche, le piéton doit s'arrêter
        } else {
            return this.enMovementP = true;  // Sinon, le piéton peut continuer à bouger
        }
    }

    // Calcul de la distance entre le piéton et le véhicule
    public double calculerDistanceVV(Vehicules vehicule) {
        double dx = this.axeXP - vehicule.getAxeXV();
        double dy = this.axeYP - vehicule.getAxeYV();
        return sqrt(pow(dx, 2) + pow(dy, 2));
    }

    // Calcul de l'angle entre le piéton et le véhicule
    public double calculerAngleVV(Vehicules vehicule) {
        double dx = vehicule.getAxeXV() - this.axeXP;
        double dy = vehicule.getAxeYV() - this.axeYP;
        return atan2(dy, dx);
    }

    // Méthode run() pour gérer le mouvement du piéton
    @Override
    public void run() {
        while (true) {
            synchronized (this) {  // Synchronisation pour l'utilisation de wait() et notify()
                if (enMovementP) {
                    this.axeXP += 1;
                    this.axeYP += 1;
                    System.out.println("Le piéton traverse. Position : (" + this.axeXP + ", " + this.axeYP + ")");
                } else {
                    System.out.println("Le piéton est arrêté.");
                }

                try {
                    Thread.sleep(1000);  // Pause d'une seconde
                    wait();  // Attente pour changer l'état du mouvement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Notifie le piéton de reprendre son mouvement
    public synchronized void reprendreMouvement() {
        notify();
    }
}
