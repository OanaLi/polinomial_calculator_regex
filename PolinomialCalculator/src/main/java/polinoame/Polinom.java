package polinoame;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom implements Operatii {

    private final HashMap<Integer, Monom> polinom = new HashMap<>();

    public Polinom() {

    }

    public Polinom(String exp) {
        Pattern pattern = Pattern.compile("(([+-]?\\d*)(x(\\^(\\d*))?)?)"); //[+-]?[\d]*[x[\^\d]*]? - php
        Matcher matcher = pattern.matcher(exp);
        while (matcher.find()) {
            double coeficient;
            int putere;

            if (!matcher.group().isEmpty()) {
                if (matcher.group(3) == null) { //daca monomul nu are x
                    coeficient = Double.parseDouble(matcher.group(2));
                    putere = 0;
                } else { //daca monomul are x
                    if (matcher.group(2) == null || matcher.group(2).isEmpty())
                        coeficient = 1;
                    else if (matcher.group(2).equals("+")) coeficient = 1;
                    else if (matcher.group(2).equals("-")) coeficient = -1;
                    else coeficient = Double.parseDouble(matcher.group(2));

                    if (matcher.group(4) == null)  //x=x^1
                        putere = 1;
                    else  //x^...
                        putere = Integer.parseInt(matcher.group(5));
                }
                this.addMonom(new Monom(coeficient, putere));
            }
        }
        //this.afisarePolinom();
    }

    void addMonom(Monom a) {
        polinom.put(a.getPutere(), a); //adauga monom in polinom
    }

    @Override
    public Polinom add(Polinom b) {
        Polinom suma = new Polinom();
        for (Map.Entry<Integer, Monom> entry : this.polinom.entrySet()) {
            suma.addMonom(new Monom(entry.getValue().getCoeficient(), entry.getValue().getPutere()));
        }

        for (Map.Entry<Integer, Monom> entry : b.polinom.entrySet()) {
            Integer putereB = entry.getValue().getPutere();
            Double coeficientB = entry.getValue().getCoeficient();

            if (suma.polinom.containsKey(putereB)) {
                Double coeficientA = suma.polinom.get(putereB).getCoeficient();
                suma.polinom.put(putereB, new Monom(coeficientB + coeficientA, putereB));
            } else
                suma.addMonom(new Monom(coeficientB, putereB));
        }

        //suma.afisarePolinom();
        return suma;
    }

    @Override
    public Polinom substract(Polinom b) {
        Polinom diferenta = new Polinom();
        for (Map.Entry<Integer, Monom> entry : this.polinom.entrySet()) {
            diferenta.addMonom(new Monom(entry.getValue().getCoeficient(), entry.getValue().getPutere()));
        }

        for (Map.Entry<Integer, Monom> entry : b.polinom.entrySet()) {
            Integer putereB = entry.getValue().getPutere();
            Double coeficientB = entry.getValue().getCoeficient();

            if (diferenta.polinom.containsKey(putereB)) {
                Double coeficientA = diferenta.polinom.get(putereB).getCoeficient();
                diferenta.polinom.put(putereB, new Monom(coeficientA - coeficientB, putereB));
            } else
                diferenta.addMonom(new Monom((-1) * coeficientB, putereB));
        }

        //diferenta.afisarePolinom();
        return diferenta;
    }

    @Override
    public Polinom multiply(Polinom p2) {
        Polinom produs = new Polinom();
        for (Map.Entry<Integer, Monom> a : polinom.entrySet()) {
            Integer putereA = a.getValue().getPutere();
            Double coeficientA = a.getValue().getCoeficient();

            for (Map.Entry<Integer, Monom> b : p2.polinom.entrySet()) {
                Integer putereB = b.getValue().getPutere();
                Double coeficientB = b.getValue().getCoeficient();
                if (produs.polinom.containsKey(putereA+putereB)) {
                    Double coef = produs.polinom.get(putereA+putereB).getCoeficient();
                    produs.polinom.put(putereA+putereB, new Monom(coef+coeficientA * coeficientB, putereA+putereB));
                } else
                    produs.addMonom(new Monom(coeficientA * coeficientB, putereA+putereB));
            }
        }
        //produs.afisarePolinom();
        return produs;
    }

    @Override
    public RezultatImpartire divide(Polinom i) {
        if(i.polinomToString().equals("0")){
            return (new RezultatImpartire(new Polinom(), new Polinom()));
        }
        else{
            Polinom cat=new Polinom();
            Polinom rest=this;

            while( !(rest.polinomToString().equals("0")) && rest.degree()>=i.degree() ){
                Polinom t=leadDivision(rest, i);
                cat=cat.add(t);
                rest=rest.substract(t.multiply(i));
            }
            return (new RezultatImpartire(cat, rest));
        }
    }

    @Override
    public Polinom integreaza() {
        Polinom integrala = new Polinom();
        for (Map.Entry<Integer, Monom> entry : this.polinom.entrySet()) {
            Integer putere = entry.getValue().getPutere();
            Double coeficient = entry.getValue().getCoeficient();
            integrala.addMonom(new Monom(coeficient / (putere + 1), putere + 1));
        }
        //integrala.afisarePolinom();
        return integrala;
    }

    @Override
    public Polinom deriveaza() {
        Polinom derivata = new Polinom();
        for (Map.Entry<Integer, Monom> entry : this.polinom.entrySet()) {
            Integer putere = entry.getValue().getPutere();
            Double coeficient = entry.getValue().getCoeficient();
            if (putere >= 1)
                derivata.addMonom(new Monom(coeficient * putere, putere - 1));
        }

        //derivata.afisarePolinom();
        return derivata;
    }

    void afisarePolinom() {
        StringBuilder rez = new StringBuilder();
        for (Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            //System.out.print(entry.getValue());
            rez.insert(0, entry.getValue());
        }
        if(rez.length() == 0)
            rez = new StringBuilder("0");
        System.out.println(rez);
    }


    public String polinomToString() {
        StringBuilder rez = new StringBuilder();
        for (Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            rez.insert(0, entry.getValue());
        }
        if(rez.toString().isEmpty())
            rez = new StringBuilder("0");

        return rez.toString();
    }

    public int degree(){
        int degree=0;
        for (Map.Entry<Integer, Monom> b : this.polinom.entrySet()) {
            if(b.getValue().getCoeficient()!=0) {
                degree = b.getValue().getPutere();
            }
        }
        return degree;
    }

    public Monom getLead(){
        Double coeficient = null;
        int putere = 0;

        for (Map.Entry<Integer, Monom> b : this.polinom.entrySet()) {
            if(b.getValue().getCoeficient()!=0) {
                coeficient = b.getValue().getCoeficient();
                putere = b.getValue().getPutere();
            }
        }
        return new Monom(coeficient, putere);
    }

    public Polinom leadDivision(Polinom dp, Polinom ip){
        Monom d=dp.getLead();
        Monom i=ip.getLead();

        double coeficient;
        int putere;

        coeficient=d.getCoeficient()/i.getCoeficient();
        putere=d.getPutere()-i.getPutere();

        Polinom k=new Polinom();
        k.polinom.put(putere, new Monom(coeficient, putere));
        return k;
    }


}
