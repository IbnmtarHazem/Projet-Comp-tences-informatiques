import static java.lang.Math.*;

public class Pietons {
    private boolean enMovementP;
    private double axeXP;
    private double axeYP;

    public Pietons(boolean enMovementP, double axeXP, double axeYP) {
        this.enMovementP = enMovementP;
        this.axeXP = axeXP;
        this.axeYP = axeYP;
    }

    public Pietons(){
        this.enMovementP=enMovementP;

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

    public boolean testerMovement(Feu feu){
        if (feu.getCouleur().equals("rouge"))
            return this.enMovementP=true;
        else
            return this.enMovementP= false;
    }
    public void run() {
        while(enMovementP) {
            this.axeXP+=1;
            this.axeYP+=1;
            System.out.println( " traverse.");
            try {
                Thread.sleep(1000); // Simule un délai d'une seconde entre chaque traversée
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    }




