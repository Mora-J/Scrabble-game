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
}
