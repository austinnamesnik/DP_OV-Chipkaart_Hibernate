package domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger_id;
    @ManyToMany(mappedBy = "ovChipkaart")
    private List<Product> product;

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, double saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger;
        product = new ArrayList<>();
    }

    public OVChipkaart() {
    }

    public void addProduct(Product product) {
        this.product.add(product);
        product.getOvChipkaart().add(this);
    }

    public void removeProduct(Product product) {
        this.product.remove(product);
        product.getOvChipkaart().remove(this);
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(Reiziger reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        String a = "OVChipkaart{" + "\n" +
                "kaart_nummer=" + kaart_nummer + "\n" +
                ", geldig_tot=" + geldig_tot + "\n" +
                ", klasse=" + klasse + "\n" +
                ", saldo=" + saldo + "\n" +
                ", reiziger_id=" + reiziger_id.getReiziger_id() + "\n" +
                ", producten=\n";
        for (Product p : product) {
            a += p.getProduct_nummer() + ", ";
        }
        if (product.size() != 0) {
            return a.substring(0, a.length()-2);
        }
        return a;
    }
}
