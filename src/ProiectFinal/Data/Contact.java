package ProiectFinal.Data;

import ProiectFinal.Exceptii.Exceptii;
import java.io.Serializable;
import java.time.LocalDate;

public class Contact implements Serializable {

    public String nume;
    public String prenume;
    public LocalDate data_nastere;
    public NrTel telefon;
    public String numar;

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public LocalDate getData_nastere() {
        return data_nastere;
    }

    public NrTel getTelefon() {
        return telefon;
    }

    public Contact(String nume, String prenume, LocalDate data_nastere, String numar) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.numar = numar;

        if (nume.length() < 2 || prenume.length() < 2) {
            Exceptii.ExceptieNumeScurt();
        } else {
            if (numar.startsWith("07") || numar.startsWith("03") || numar.startsWith("02")) {

                if (numar.startsWith("07")) {
                    telefon = new NrMobil(numar);
                }
                if (numar.startsWith("02") || numar.startsWith("03")) {
                    telefon = new NrFix(numar);
                }
            } else {
                Exceptii.ExceptieNumarNuIncepeCu();
            }
        }
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " " + data_nastere + "  " + telefon;
    }

    @Override
    public boolean equals(Object c) {
        if (((Contact) c).nume.equalsIgnoreCase(nume) && ((Contact) c).prenume.equalsIgnoreCase(prenume) && ((Contact) c).telefon.equals(telefon) && ((Contact) c).data_nastere.equals(data_nastere)) {
            return true;
        }
        return false;
    }
}
