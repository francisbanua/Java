package banua.classi;

import java.time.LocalDateTime;

public class Movimento {
    private String descrizione;
    private float importo;
    private LocalDateTime data;

    public Movimento(String descrizione, float importo){
        this.descrizione = descrizione;
        this.importo = importo;
        this.data = LocalDateTime.now();
    }

    public float getImporto() {
        return importo;
    }

    public void setImporto(float importo) {
        this.importo = importo;
    }

    @Override
    public String toString() {
        return data + "     " + importo + "     " + descrizione;
    }
}