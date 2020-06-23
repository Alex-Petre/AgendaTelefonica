package ProiectFinal.Data;
import ProiectFinal.Exceptii.Exceptii;
import java.io.Serializable;

public abstract class NrTel implements Comparable, Serializable  {
    
    String numar_telefon;    

    public NrTel(String numar_telefon) {
        if (validareNumar(numar_telefon) == true) {
            this.numar_telefon = numar_telefon;
        } 
        else 
        {
            Exceptii.ExceptieNumarIncorect();
        }
    }
    
    //metoda abstracata validareNumar ,rescrisa in subclase
    public abstract boolean validareNumar(String numar);

    @Override
    public int compareTo(Object t) {
        int diferenta=((NrTel)t).numar_telefon.compareToIgnoreCase(numar_telefon);
        return diferenta;
    }

    @Override
    public boolean equals(Object o) {     
        return (numar_telefon == null ? ((NrTel) o).numar_telefon == null : numar_telefon.equals(((NrTel) o).numar_telefon));
    }

    @Override
    public String toString() {
        return numar_telefon;
    }        
}
