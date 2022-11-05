package banua.classi;

public class ContoCorrente {
    private String nome;
    private String cognome;
    private float saldo;
    private Movimento[] movimenti;
    private int n_movimenti;
    private static final int MAX_MOVIMENTI = 100;

    public ContoCorrente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.saldo = 0;
        this.movimenti = new Movimento[MAX_MOVIMENTI];
        this.n_movimenti = 0;
    }

    public boolean Importo(boolean b, Movimento m) {
        if (b) {
            movimenti[n_movimenti] = m;
            saldo += movimenti[n_movimenti].getImporto();
            n_movimenti++;
            return true;
        }
        if (m.getImporto() > saldo)
            return false;
        movimenti[n_movimenti] = m;
        movimenti[n_movimenti].setImporto(-m.getImporto());
        saldo += m.getImporto();
        n_movimenti++;
        return true;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public float getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        String mov = "Data                              Importo   Descrizione";
        for (int i = 0; i < n_movimenti; i++) {
            mov += "\n" + movimenti[i].toString();
        }
        return mov;
    }
}