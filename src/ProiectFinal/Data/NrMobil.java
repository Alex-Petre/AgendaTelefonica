package ProiectFinal.Data;
import ProiectFinal.Exceptii.Exceptii;
import java.io.Serializable;

public class NrMobil extends NrTel implements Serializable {
     public NrMobil(String numar_telefon) {
        super(numar_telefon);
    }

    @Override
    public boolean validareNumar(String numar) {
        if (numar.length() == 10) {
            return true;
        } 
        else {
            Exceptii.ExceptieNumarNuAre10Cifre();
        }
         return false;
    }    
}
