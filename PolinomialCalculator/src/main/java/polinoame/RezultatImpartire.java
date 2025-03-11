package polinoame;

public class RezultatImpartire {
    Polinom cat=new Polinom();
    Polinom rest=new Polinom();

    public RezultatImpartire(Polinom cat, Polinom rest){
        this.cat=cat;
        this.rest=rest;
    }

    public Polinom getCat() {
        return cat;
    }

    public Polinom getRest() {
        return rest;
    }
}
