package domein;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reiziger {
    @Id
    @Column(name = "reiziger_id")
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    @OneToMany(mappedBy = "reiziger_id")
    private List<OVChipkaart> ovChips;
    @OneToOne(mappedBy = "reiziger_id")
    private Adres adres;

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        ovChips = new ArrayList<>();
    }

    public Reiziger() {
    }

    public void addOvChip(OVChipkaart ovChipkaart) {
        this.ovChips.add(ovChipkaart);
    }

    public void removeOvChip(OVChipkaart ovChipkaart) {
        this.ovChips.remove(ovChipkaart);
    }

    public int getReiziger_id() {
        return id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.id = reiziger_id;
    }

    public String getNaam() {
        if (tussenvoegsel == null) {
            return voorletters + " " + achternaam;
        }
        return voorletters + " " + tussenvoegsel + " " + achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }



    @Override
    public String toString() {
        String a = "De naam van de reiziger is: " + getNaam() + "\n" +
                "Deze reiziger is geboren op: " + geboortedatum.toString() + "\n" +
                "Het ID van deze reiziger is: " + id + "\n" +
                "De reiziger heeft deze OVChipkaarten: ";
        for (OVChipkaart chip : this.ovChips) {
            a += chip.getKaart_nummer() + " ";
        }
        a += "\n";
        return a;
    }
}
