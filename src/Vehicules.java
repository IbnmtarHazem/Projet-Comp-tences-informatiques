import static java.lang.Math.*;

public class Vehicules implements Runnable {

    private boolean enMovement;
    double axeXV;
    double axeYV;

    public Vehicules(boolean enMovement, double axeXV, double axeYV) {
        this.enMovement = enMovement;
        this.axeXV = axeXV;
        this.axeYV = axeYV;
        this.enMovement = false;
    }

    public Vehicules() {
        this.enMovement = false;
    }

    public boolean getEnmovement() {
        return this.enMovement;
    }

    public boolean isEnMovement() {
        return enMovement;
    }

    public void setEnMovement(boolean enMovement) {
        this.enMovement = enMovement;
    }

    public double getAxeXV() {
        return axeXV;
    }

    public void setAxeXV(double axeXV) {
        this.axeXV = axeXV;
    }

    public double getAxeYV() {
        return axeYV;
    }

    public void setAxeYV(double axeYV) {
        this.axeYV = axeYV;
    }

    // Vérifie si le feu est vert ou rouge
    public void verifierFeu(Feu feu) {
        if (feu.getCouleur().equals("Vert")) {
            this.enMovement = true;
        } else {
            this.enMovement = false;
        }
    }

    // Calcul de la distance avec un piéton
    public double calculerDistance(Pietons pieton) {
        double dx = this.axeXV - pieton.getAxeXP();
        double dy = this.axeYV - pieton.getAxeYP();
        return sqrt(pow(dx, 2) + pow(dy, 2));
    }

    // Calcul de l'angle avec un piéton
    public double calculerAngle(Pietons pieton) {
        double dx = pieton.getAxeXP() - this.axeXV;
        double dy = pieton.getAxeYP() - this.axeYV;
        return atan2(dy, dx);
    }

    // Vérifie si le véhicule doit s'arrêter en fonction d'un piéton
    public boolean verifierArret(Pietons pieton, double distanceSeuil, double angleSeuil) {
        double distance = calculerDistance(pieton);
        double angle = toDegrees(calculerAngle(pieton)); // Convertir l'angle en degrés

        if (distance <= distanceSeuil && abs(angle) <= angleSeuil) {
            return this.enMovement = true;
        } else {
            return this.enMovement = false;
        }
    }

    // Calcul de la distance entre deux véhicules
    public double calculerDistanceVV(Vehicules autreVehicule) {
        double dx = this.axeXV - autreVehicule.getAxeXV();
        double dy = this.axeYV - autreVehicule.getAxeYV();
        return sqrt(pow(dx, 2) + pow(dy, 2));
    }

    // Calcul de l'angle entre deux véhicules
    public double calculerAngleVV(Vehicules autreVehicule) {
        double dx = autreVehicule.getAxeXV() - this.axeXV;
        double dy = autreVehicule.getAxeYV() - this.axeYV;
        return atan2(dy, dx);
    }

    // Vérifie si le véhicule doit s'arrêter en fonction d'un autre véhicule
    public boolean verifierArretVV(Vehicules autreVehicule, double distanceSeuil, double angleSeuil) {
        double distanceVV = calculerDistanceVV(autreVehicule);
        double angleVV = toDegrees(calculerAngleVV(autreVehicule));  // Convertir en degrés

        if (distanceVV <= distanceSeuil && abs(angleVV) <= angleSeuil) {
            return this.enMovement = true;
        } else {
            return this.enMovement = false;
        }
    }

    // Méthode run() pour gérer le mouvement du véhicule
    @Override
    public void run() {
        while (true) {
            synchronized (this) {  // Utiliser synchronized pour wait() et notify()
                if (enMovement) {
                    this.axeXV += 1;
                    this.axeYV += 1;
                    System.out.println("Véhicule se déplace. Position : (" + this.axeXV + ", " + this.axeYV + ")");
                } else {
                    System.out.println("Véhicule arrêté.");
                }

                try {
                    Thread.sleep(1000);  // Pause de 1 seconde
                    wait();  // Attendre que l'état du mouvement change
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Notifie le véhicule pour reprendre son mouvement
    public synchronized void reprendreMouvement() {
        notify();
    }
}
