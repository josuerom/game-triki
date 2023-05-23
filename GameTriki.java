/**
 *  author:
    ──▀ █▀▀█ █▀▀ █──█ █▀▀ █▀▀█ █▀▀█ █▀▄▀█ 
    ──█ █──█ ▀▀█ █──█ █▀▀ █▄▄▀ █──█ █─▀─█ 
    █▄█ ▀▀▀▀ ▀▀▀ ─▀▀▀ ▀▀▀ ▀─▀▀ ▀▀▀▀ ▀───▀
 *  created: 23/05/23 00:23
**/
import java.util.Scanner;

public class GameTriki {
    private char[][] tablero;
    private char jugador_uno = 'X';
    private char jugador_dos = '0';
    private int jugador_uno_partidas = 0;
    private int jugador_dos_partidas = 0;
    private int turno = 1;

    private static Scanner sc = new Scanner(System.in);

    public TrikiNewball() {
        tablero = new char[3][3];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    private void imprimirEstadoDelJuego() {
        System.out.println("\nEstado actual del tablero:\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println("\n-------------\n");
        }
    }

    private boolean tableroEstaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean jugadorUnoGano() {
        for (int i = 0; i < 3; i++) {
            // Verificar filas con X
            if (tablero[i][0] == jugador_uno && tablero[i][1] == jugador_uno && tablero[i][2] == jugador_uno) {
                return true;
            }
            // Verificar columnas con X
            if (tablero[0][i] == jugador_uno && tablero[1][i] == jugador_uno && tablero[2][i] == jugador_uno) {
                return true;
            }
        }
        // Verificar diagonales con X
        if ((tablero[0][0] == jugador_uno && tablero[1][1] == jugador_uno && tablero[2][2] == jugador_uno) ||
            (tablero[0][2] == jugador_uno && tablero[1][1] == jugador_uno && tablero[2][0] == jugador_uno)) {
            return true;
        }
        return false;
    }

    private boolean jugadorDosGano() {
        for (int i = 0; i < 3; i++) {
            // Verificar filas con O
            if (tablero[i][0] == jugador_dos && tablero[i][1] == jugador_dos && tablero[i][2] == jugador_dos) {
                return true;
            }
            // Verificar columnas con O
            if (tablero[0][i] == jugador_dos && tablero[1][i] == jugador_dos && tablero[2][i] == jugador_dos) {
                return true;
            }
        }
        // Verificar diagonales con O
        if ((tablero[0][0] == jugador_dos && tablero[1][1] == jugador_dos && tablero[2][2] == jugador_dos) ||
            (tablero[0][2] == jugador_dos && tablero[1][1] == jugador_dos && tablero[2][0] == jugador_dos)) {
            return true;
        }
        return false;
    }

    public void imprimirMarcador() {
        System.out.printf("\nEl jugador #1 lleva %d partidas ganadas\nMientras que el jugador #2 lleva %d partidas ganadas.\n", jugador_uno_partidas, jugador_dos_partidas);
    }

    public void gameMenu() {
        System.out.print("\nEstimado usuario, ¿que desea realizar?, presione:\n1. Para jugar otra partida\n2. Para imprimir el marcador actual\n3. Para salir\nPor favor digite su opcion -> ");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1: playGame();
                break;
            case 2: imprimirMarcador();
                    gameMenu();
                break;
            default:
                System.out.println("\nChao, hasta la proxima!");
                break;
        }
    }

    public void playGame() {
        while (true) {
            System.out.print("Turno del jugador #" + turno + ": ");
            int fila = sc.nextInt();
            int columna = sc.nextInt();

            if ((fila>=1 && fila<=3) && (columna>=1 && columna<=3) && tablero[fila-1][columna-1]=='-') {
                if (turno == 1) {
                    tablero[fila-1][columna-1] = jugador_uno;
                    turno = 2;
                } else {
                    tablero[fila-1][columna-1] = jugador_dos;
                    turno = 1;
                }
                imprimirEstadoDelJuego();
                if (jugadorUnoGano()) {
                    System.out.println("\nEL JUGADOR 1 HA GANADO LA PARTIDA!");
                    jugador_uno_partidas++;
                    break;
                } else if (jugadorDosGano()) {
                    System.out.println("\nEL JUGADOR 2 HA GANADO LA PARTIDA!");
                    jugador_dos_partidas++;
                    break;
                } else if (tableroEstaLleno()) {
                    System.out.println("\nEl tablero ha quedado sin espacios disponibles");
                    break;
                }
            } else {
                System.out.println("\n!Ups su movimiento es invalido! intentelo de nuevo.\n");
            }
        }
        turno = 1;
        inicializarTablero();
        gameMenu();

        // Cierro el hilo del objeto de lectura Scanner y de salida System.out
        sc.close(); System.out.close();
    }

    public static void main(String[] args) {
        TrikiNewball gm = new TrikiNewball();
        gm.playGame();
    }
}
