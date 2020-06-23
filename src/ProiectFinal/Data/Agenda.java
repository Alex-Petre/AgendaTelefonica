package ProiectFinal.Data;

import ProiectFinal.Exceptii.Exceptii;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Agenda implements Serializable {

    public enum CriteriuOrdonare {
        DUPA_NUME, DUPA_PRENUME, DUPA_VARSTA, DUPA_TELEFON
    }

    public List Contacte = new ArrayList();
    public List y = new ArrayList();

    Predicate<Contact> camp = (Contact x) -> true;
    CriteriuOrdonare camp_curent;

    public Agenda() {
    }

    public void Adauga(String nume, String prenume, String data, String numar) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate data_nastere = LocalDate.parse(data, formatter);
        Contact c = new Contact(nume, prenume, data_nastere, numar);

        if (Contacte.contains(c)) {
            Exceptii.ExceptieContactExistent();
        } else {
            Contacte.add(c);
        }
    }

    public void Stergere(Contact c) {
        if (Contacte.contains(c)) {
            Contacte.remove(c);
        } else {
            Exceptii.ExceptieContactNegasit();
        }
    }

    public void Modifica(String nume, String prenume, String data, String numar, Contact c) {
        if (nume.length() < 2 || prenume.length() < 2) {
            Exceptii.ExceptieNumeScurt();
        }

        if (numar.startsWith("07") || numar.startsWith("03") || numar.startsWith("02")) {
        } else {
            Exceptii.ExceptieNumarNuIncepeCu();
        }

        if (numar.length() != 10) {
            Exceptii.ExceptieNumarNuAre10Cifre();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate data_nastere = LocalDate.parse(data, formatter);
        Contact test = new Contact(nume, prenume, data_nastere, numar);

        if (Contacte.contains(test)) {
            Exceptii.ExceptieContactExistent();
        } else {
            c.nume = nume;
            c.prenume = prenume;
            c.data_nastere = data_nastere;
            c.telefon.numar_telefon = numar;
        }
    }

    public void filtreazaNrFix() {
        camp = (Contact x) -> x.getTelefon().numar_telefon.startsWith("021");
    }

    public void filtreazaNrMobil() {
        camp = (Contact x) -> x.getTelefon().numar_telefon.startsWith("07");
    }

    public void filtreazaNascutiAstazi() {
        camp = (Contact x) -> x.getData_nastere().getDayOfMonth() == (LocalDate.now().getDayOfMonth());
    }

    public void filtreazaPersonalizat(String filtru) {
        camp = (Contact x) -> (x.getNume().contains(filtru) || x.getPrenume().contains(filtru) || x.telefon.toString().contains(filtru));
    }

    public void filtreazaNascutiLunaCurenta() {
        camp = (Contact x) -> x.getData_nastere().getMonth().equals(LocalDate.now().getMonth());
    }

    public void Ordoneaza(CriteriuOrdonare camp_curent) {
        this.camp_curent = camp_curent;
    }

    public List contacte_functie(boolean defaultSearch) {
        if (defaultSearch == true) {// filtrari

            y = (List) Contacte.stream().filter(camp).collect(Collectors.toList());

            return y;
        } else {
            switch (camp_curent) {
                case DUPA_NUME:
                    y = (List) Contacte.stream().filter(camp).sorted(Comparator.comparing(Contact::getNume)).collect(Collectors.toList());
                    break;
                case DUPA_PRENUME:
                    y = (List) Contacte.stream().filter(camp).sorted(Comparator.comparing(Contact::getPrenume)).collect(Collectors.toList());
                    break;
                case DUPA_VARSTA:
                    y = (List) Contacte.stream().filter(camp).sorted(Comparator.comparing(Contact::getData_nastere)).collect(Collectors.toList());
                    break;
                case DUPA_TELEFON:
                    y = (List) Contacte.stream().filter(camp).sorted(Comparator.comparing(Contact::getTelefon)).collect(Collectors.toList());
                    break;
            }
            return y;
        }
    }
}
