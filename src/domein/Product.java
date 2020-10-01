package domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    @ManyToMany
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "product_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "kaart_nummer")}
    )
    private List<OVChipkaart> ovChipkaart;

    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        ovChipkaart = new ArrayList<>();
    }

    public Product() {
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart.add(ovChipkaart);
        ovChipkaart.getProduct().add(this);
    }

    public void removeOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart.remove(ovChipkaart);
        ovChipkaart.getProduct().remove(this);
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvChipkaart() {
        return ovChipkaart;
    }

    public void setOvChipkaart(List<OVChipkaart> ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    @Override
    public String toString() {
        String a = "Product{" +
                "product_nummer=" + product_nummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs + "\'" + "ovChipkaarten=" + "\n";
        for (OVChipkaart ov : this.ovChipkaart) {
            a += ov.getKaart_nummer() + ", ";
        }
        String b = a.substring(0, a.length()-2);
        b += "}";
        if (ovChipkaart.size() != 0) {
            return b;
        }
        return a;
    }
}
