package DataTypes;

public class Dividendos {
    private boolean estanAsignados;
    private float dividendoLocal;
    private float dividendoEmpate;
    private float dividendoVisita;

    public Dividendos() {
        this.estanAsignados = false;
        this.dividendoLocal = 0;
        this.dividendoVisita = 0;
        this.dividendoEmpate = 0;
    }

    public Dividendos(float divL, float divV, float divEm) {
        this.estanAsignados = true;
        this.dividendoLocal = divL;
        this.dividendoVisita = divV;
        this.dividendoEmpate = divEm;
    }

    public boolean getEstanAsignados() {
        return this.estanAsignados;
    }

    public float getDividendoLocal() {
        return dividendoLocal;
    }
    public float getDividendoEmpate() {
        return dividendoEmpate;
    }
    public float getDividendoVisita() {
        return dividendoVisita;
    }

    public void setEstanAsignados(boolean e){
        estanAsignados=e;
    }

    public void setDividendoLocal(float l){
       dividendoLocal=l;
    }

    public void setDividendoVisita(float v){
       dividendoVisita=v;
    }

    public void setDividendoEmpate(float e){
       dividendoEmpate=e;
    }

    @Override
    public String toString() {
        return this.estanAsignados+"-"+this.dividendoLocal+"-"+this.dividendoEmpate+"-"+this.dividendoVisita;
    }

}