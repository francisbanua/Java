package banua.classi;

public class Banca {
    private final static int MAX_CONTI = 100;
    private ContoCorrente[] conti;
    private int n_conti;

    public Banca(){
        conti = new ContoCorrente[MAX_CONTI];
        n_conti = 0;
    }

    public void creaConto(String nome, String cognome){
        ContoCorrente c = new ContoCorrente(nome, cognome);
        conti[n_conti] = c;
        n_conti++;
    }

    public ContoCorrente[] ricercaPerCognome(String cognome){
        int n_selezionati = 0;
        for (int i = 0; i < n_conti; i++){
            if (cognome.equals(conti[i].getCognome())){
                n_selezionati++;
            }
        }
        ContoCorrente[] temp = new ContoCorrente[n_selezionati];
        int j = 0;
        for (int i = 0; i < n_conti; i++) {
            if (cognome.equals(conti[i].getCognome())){
                temp[j] = conti[i];
                j++;
            }
        }
        return temp;
    }
}