package tarea7;

import java.util.Locale;
import java.util.Objects;


public class PolloAsado {
    private String corte;   // Pierna, Muslo, Pechuga, Alas, Entero...
    private String salsa;   // BBQ, Adobada, Chimichurri...
    private double precio;

    public PolloAsado(String corte, String salsa, double precio) {
        this.corte = corte;
        this.salsa = salsa;
        this.precio = precio;
    }

    public String getCorte() { return corte; }
    public String getSalsa() { return salsa; }
    public double getPrecio() { return precio; }

    public void setCorte(String corte) { this.corte = corte; }
    public void setSalsa(String salsa) { this.salsa = salsa; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolloAsado otro = (PolloAsado) o;
        return Double.compare(precio, otro.precio) == 0
                && Objects.equals(corte, otro.corte)
                && Objects.equals(salsa, otro.salsa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corte, salsa, precio);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s (%s) $%.2f", corte, salsa, precio);
    }
}
