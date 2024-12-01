import java.util.Scanner;
import java.util.Arrays;

public class Tablero {
    private final Ficha[][] tablero;
    private int filaCentral;
    private int columnaCentral;

    public Ficha[][] getTablero() {
        return tablero;
    }

    public void resaltarPosicion(int fila, int columna) {
        Ficha ficha = tablero[fila][columna];
        String letra = ficha.getLetra();
        if (letra.length() == 1){
            ficha.setSimbolo("\033[30;43m[" + letra + " ]\033[0m");
        }else{
            ficha.setSimbolo("\033[30;43m[" + letra + "]\033[0m");
        }

    }

    public void desresaltarPosicion(int fila, int columna) {
        String simbolo = tablero[fila][columna].getSimbolo();
        String simboloSinANSI = simbolo.replaceAll("\033\\[[;\\d]*m", "");
        tablero[fila][columna].setSimbolo(simboloSinANSI);

        if(!tablero[fila][columna].getLetra().equals("  ")){
            simbolo = "\033[1;30;107m"+simboloSinANSI+"\033[0m";
            tablero[fila][columna].setSimbolo(simbolo);
        }

    }

    public Tablero() {
        this.tablero = new Ficha[15][15];
        for (int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero.length; j++){
                this.tablero[i][j] = new Ficha();
            }
        }
        this.filaCentral = (tablero.length - 1)/2;
        this.columnaCentral = (tablero[0].length - 1)/2;
    }

    public Tablero(Tablero original) {
        this.tablero = new Ficha[original.getTablero().length][original.getTablero()[0].length];
        for (int i = 0; i < original.getTablero().length; i++) {
            for(int j = 0; j < original.getTablero().length; j++){
                this.tablero[i][j] = original.getTablero()[i][j].clone();
            }
        }
        this.filaCentral = original.getFilaCentral();
        this.columnaCentral = original.getColumnaCentral();
    }


    public boolean validarFichaPosicion(int fila, int columna) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length) {
            return tablero[fila][columna].getLetra().equals("  ");
        }
        return false;
    }

    private boolean asignarComodin(Ficha ficha) {
        String[] fichasValidas = new String[]{"A", "B", "C", "CH", "D", "E", "F", "G", "H", "I",
                "J", "L", "LL", "M", "N", "Ñ", "O", "P", "Q", "R", "RR", "S", "T", "U", "V", "X", "Y", "Z"};

        Scanner scanner = new Scanner(System.in);
        boolean letraValida = false;

        if (ficha.getLetra().equals("#")) {
            while (!letraValida) {
                System.out.println("Ingrese en que Ficha quiere convertir su comodin: (recuerde que tambien estan LL, CH, RR)");
                System.out.println("Ingrese 9 para cancelar");
                String fichaIngresada = scanner.nextLine().toUpperCase().trim();
                if (fichaIngresada.equals("9")) {
                    return false;
                }
                if (esFichaValida(fichaIngresada, fichasValidas)) {
                    System.out.println("La ficha " + fichaIngresada + " es válida.");
                    ficha.setLetra(fichaIngresada);
                    letraValida = true;

                } else {
                    System.out.println("La ficha " + fichaIngresada + " no es válida. Inténtelo nuevamente.");
                }

            }
            return true;
        }
        return true;
    }

    private static boolean esFichaValida(String ficha, String[] fichasValidas) {
        return Arrays.asList(fichasValidas).contains(ficha);
    }

    public boolean colocarPrimeraFicha(Ficha ficha) {
        if (validarFichaPosicion(7, 7) && ficha != null && asignarComodin(ficha)) {
            this.tablero[7][7] = ficha;
            return true;
        }else {
            return false;
        }
    }

    public boolean colocarFicha(int fila, int columna, Ficha ficha) {
        boolean hayFichaAdyacente = (fila > 0 && !tablero[fila - 1][columna].getLetra().equals("  ")) ||
                (fila < tablero.length - 1 && !tablero[fila + 1][columna].getLetra().equals("  ")) ||
                (columna > 0 && !tablero[fila][columna - 1].getLetra().equals("  ")) ||
                (columna < tablero[0].length - 1 && !tablero[fila][columna + 1].getLetra().equals("  "));

        if (validarFichaPosicion(fila, columna) && hayFichaAdyacente && ficha != null && asignarComodin(ficha)) {
            this.tablero[fila][columna] = ficha;
            return true;
        }else {
            return false;
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("\t");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.print("\n");
        }
    }


    public int getFilaCentral() {
        return filaCentral;
    }

    public void setFilaCentral(int filaCentral) {
        this.filaCentral = filaCentral;
    }

    public int getColumnaCentral() {
        return columnaCentral;
    }

    public void setColumnaCentral(int columnaCentral) {
        this.columnaCentral = columnaCentral;
    }
}
