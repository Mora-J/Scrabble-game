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
        ficha.setSimbolo("\033[30;43m[" + letra + "]\033[0m");
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

    public boolean validarFichaPosicion(int fila, int columna) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length) {
            return tablero[fila][columna].getLetra().equals("  ");
        }
        return false;
    }

    public boolean colocarPrimeraFicha(Ficha ficha) {
        if (validarFichaPosicion(7, 7) && ficha != null) {
            this.tablero[7][7] = ficha;
            return true;
        }
        return false;
    }

    public boolean colocarFicha(int fila, int columna, Ficha ficha) {
        boolean hayFichaAdyacente = (fila > 0 && !tablero[fila - 1][columna].getLetra().equals("  ")) ||
                (fila < tablero.length - 1 && !tablero[fila + 1][columna].getLetra().equals("  ")) ||
                (columna > 0 && !tablero[fila][columna - 1].getLetra().equals("  ")) ||
                (columna < tablero[0].length - 1 && !tablero[fila][columna + 1].getLetra().equals("  "));

        if (validarFichaPosicion(fila, columna) && hayFichaAdyacente) {
            this.tablero[fila][columna] = ficha;
            return true;
        }
        return false;
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
}
