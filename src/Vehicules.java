import static java.lang.Math.*;

public class Vehicules {



    private boolean enMovement;
    double axeXV;
    double axeYV;

    public Vehicules(boolean enMovement, double axeXV, double axeYV) {
        this.enMovement = enMovement;
        this.axeXV = axeXV;
        this.axeYV = axeYV;
        this.enMovement=false;


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

    public void verifierFeu(Feu feu) {
        if (feu.getCouleur().equals("Vert"))
            this.enMovement = true;
        else
            this.enMovement = false;

    }
    public double calculerDistance(Pietons pieton) {
        double dx = this.axeXV - pieton.getAxeXP();
        double dy = this.axeYV - pieton.getAxeYP();
        return sqrt(pow(dx,2) + pow(dy,2));
    }

    public double calculerAngle(Pietons pieton) {
        double dx = pieton.getAxeXP() - this.axeXV;
        double dy = pieton.getAxeYP() - this.axeYV;
        return atan2(dy, dx);
    }

    public boolean verifierArret(Pietons pieton,double distanceSeuil,double angleSeuil) {
        double distance = calculerDistance(pieton);
        double angle =toDegrees(calculerAngle(pieton)); // Convertir l'angle en degrés

        if (distance <= distanceSeuil && abs(angle) <= angleSeuil) {
            return this.enMovement = true;
        } else {
            return this.enMovement = false;

        }
    }
    public void run() {
        while (enMovement){
            this.axeXV+=1;
            this.axeYV+=1;
            System.out.println( " se déplace.");
            try {
                Thread.sleep(1000); // Simule un délai d'une seconde entre chaque déplacement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

