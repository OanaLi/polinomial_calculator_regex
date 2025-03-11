package polinoame;

public class Monom {

    private Double coeficient; //protected?
    private Integer putere;

    public Monom(Double coeficient, int putere)
    {
        this.coeficient=coeficient;
        this.putere=putere;
    }

    public Double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(Double coeficient) {
        this.coeficient = coeficient;
    }

    public Integer getPutere() {
        return putere;
    }

    public void setPutere(Integer putere) {
        this.putere = putere;
    }

    @Override
    public String toString() {
        String coeficientS="", putereS="";

        if(coeficient!=0) {
            if (coeficient % 1 == 0)
                coeficientS = Integer.parseInt(String.format("%.0f", coeficient)) + "";
            else
                coeficientS= String.format("%.1f", coeficient);

            if (coeficient > 0)
                coeficientS = "+" + coeficientS;

            if (putere == 1)
                putereS = "x";
            if (putere > 1)
                putereS = "x^" + putere;

            if (coeficientS.equals("+1") && putere != 0)
                coeficientS = "+";
            if (coeficientS.equals("-1") && putere != 0)
                coeficientS = "-";
        }

        return coeficientS+putereS;

    }
}
