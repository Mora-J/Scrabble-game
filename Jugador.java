public class Jugador {
    private final String alias;
    private int scoreTotal;
    private String tiempoJugado;
    private Ficha[] fichas = new Ficha[7];

    public Ficha[] getFichas() {
        return fichas;
    }

    public void mostrarFichas() {
        StringBuilder atril = new StringBuilder();
        for (Ficha ficha : this.fichas) {
            if (ficha != null) {
                atril.append(ficha);
            } else {
                atril.append(" __ ");
            }
        }
        System.out.println(atril.toString());
    }

    public void jugarPrimeraFicha(Tablero tablero, int indexFichas) {
        if (tablero.colocarPrimeraFicha(fichas[indexFichas])) {
            fichas[indexFichas] = null;
        }
    }

    public void jugarFichas(Tablero tablero, int fila, int columna, int indexFichas) {
        if (tablero.colocarFicha(fila, columna, fichas[indexFichas])) {
            fichas[indexFichas] = null;
        }else System.out.println("No puedes colocar una ficha en esta posicion");
    }


    public void rellenarFichas(BolsaFichas bolsaFichas) {
        for (int i = 0; i < 7; i++) {
            int randomIndex = (int) (Math.random() * (bolsaFichas.getListaFichas().size() - 1));
            if (fichas[i] == null && !bolsaFichas.getListaFichas().isEmpty()) {
                fichas[i] = bolsaFichas.getListaFichas().get(randomIndex);
                bolsaFichas.getListaFichas().remove(randomIndex);
            }
        }
    }

    public Jugador(String alias) {
        this.alias = alias;
        this.scoreTotal = 0;
        this.tiempoJugado = "0";
    }

    public void setFichas(Ficha[] fichas) {
        this.fichas = fichas;
    }

    public String getAlias() {
        return alias;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void addToScoreTotal(int scoreTotal) {
        this.scoreTotal += scoreTotal;
    }

    public String getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(String tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }
}



