import banua.classi.Banca;
import banua.classi.ContoCorrente;
import banua.classi.Movimento;
import java.util.Scanner;

public class Main {
    public static void conto(ContoCorrente c) {
        Scanner In = new Scanner(System.in);
        int scelta = -1;
        while (scelta != 0) {
            System.out.println("[1] deposita     [2] preleva     [3] stampa movimento     [4] stampa saldo    [0] esci");
            scelta = In.nextInt();
            switch (scelta) {
                case 1:
                    System.out.println("Inserisci quantita' da depositare: ");
                    float importo = In.nextFloat();
                    System.out.println("Inserisci descrizione: ");
                    String descrizione = In.next();
                    Movimento m = new Movimento(descrizione, importo);
                    c.Importo(true, m);
                    System.out.println("Deposito avvenuto con successo");
                    break;
                case 2:
                    System.out.println("Inserisci quantita' da prelevare: ");
                    importo = In.nextFloat();
                    System.out.println("Inserisci descrizione: ");
                    descrizione = In.next();
                    m = new Movimento(descrizione, importo);
                    if (!c.Importo(false, m))
                        System.out.println("Errore prelievo");
                    else
                        System.out.println("Prelievo avvenuto con successo");
                    break;
                case 3:
                    System.out.println(c);
                    break;
                case 4:
                    System.out.println("Saldo: " + c.getSaldo());
                    break;
                case 0:
                    System.out.println("Sei uscito");
                    break;
                default:
                    System.out.println("Scelta non valida");
            }

        }
    }

    public static void main(String[] args) {
        Scanner In = new Scanner(System.in);
        Banca b = new Banca();
        int scelta = -1;
        while (scelta != 0) {
            System.out.println("[1] crea conto     [2] modifica conto     [0] esci");
            scelta = In.nextInt();
            switch (scelta) {
                case 1:
                    System.out.println("Inserisci nome: ");
                    String nome = In.next();
                    System.out.println("Inserisci cognome: ");
                    String cognome = In.next();
                    b.creaConto(nome, cognome);
                    break;
                case 2:
                    System.out.println("Inserisci cognome: ");
                    cognome = In.next();
                    ContoCorrente[] temp = b.ricercaPerCognome(cognome);
                    for (int i = 0; i < temp.length; i++) {
                        System.out.println((i + 1) +". " + temp[i].getNome() + " " + temp[i].getCognome());
                    }
                    int iConto;
                    do {
                        System.out.println("Inserisci scelta: ");
                        iConto = In.nextInt();
                    } while (iConto < 1 || iConto > temp.length);
                    ContoCorrente c = temp[iConto - 1];
                    conto(c);
                case 0:
                    System.out.println("Sei uscito");
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }
    }
}