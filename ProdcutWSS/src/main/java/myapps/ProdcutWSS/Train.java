package myapps.ProdcutWSS;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Train {

    private String numTrain;
    private String villeDepart;
    private String villeArrivee;
    private int heureDepart;

    public Train() {
        // constructeur par défaut requis par JAXB
    }

    public Train(String numTrain, String villeDepart, String villeArrivee, int heureDepart) {
        this.numTrain = numTrain;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.heureDepart = heureDepart;
    }

    public String getNumTrain() {
        return numTrain;
    }

    public void setNumTrain(String numTrain) {
        this.numTrain = numTrain;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public int getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(int heureDepart) {
        this.heureDepart = heureDepart;
    }

    @Override
    public String toString() {
        return "Train " + numTrain + " " + villeDepart + " " + villeArrivee + " " + heureDepart;
    }
}
