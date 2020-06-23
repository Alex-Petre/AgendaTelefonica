package ProiectFinal.Exceptii;

public class Exceptii {

    public static void ExceptieContactExistent() {
        throw new RuntimeException("contactul exista!");
    }

    public static void ExceptieContactNegasit() {
        throw new RuntimeException("Contactul nu se gaseste in lista de contacte");
    }

    public static void ExceptieNumeScurt() {
        throw new RuntimeException("NUMELE SAU PRENUMELE PREA SCURT!");
    }

    public static void ExceptieNumarNuIncepeCu() {
        throw new RuntimeException("Numarul nu incepe cu 07,02 sau 03!");
    }

    public static void ExceptieNumarNuAre10Cifre() {
        throw new RuntimeException("Numaru nu are 10 cifre...");
    }
    
    public static void ExceptieNumarIncorect() {
        throw new RuntimeException("Numar incorect!");
    }
}
