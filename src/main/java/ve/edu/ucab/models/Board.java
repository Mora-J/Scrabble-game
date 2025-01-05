package ve.edu.ucab.models;

public class Board {
    private Casilla[][] casillas;

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public Board() {
        this.casillas = new Casilla[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.casillas[i][j] = new Casilla();
            }
        }
    }

    public Board(Board board) {
        this.casillas = new Casilla[board.getCasillas().length][board.getCasillas().length];
        for (int i = 0; i < board.getCasillas().length; i++) {
            for (int j = 0; j < board.getCasillas().length; j++) {
                this.casillas[i][j] = board.getCasillas()[i][j].clone();
            }
        }

    }

    public void mostrarTableroEnConsola() {
        for (Casilla[] casilla : casillas) {
            System.out.print("\t");
            for (Casilla value : casilla) {
                System.out.print("["+value.getFicha().toString()+"]");
            }
            System.out.print("\n");
        }
    }
}
