package ve.edu.ucab.models;

public class Jugador {

    private String alias;
    private String correoElectronico;

    private int scoreTotal;
    private int scoreInGame;

    private int cantidadDePalabras;
    private int cantidadPalabrasColocadas = 0;
    private final Ficha[] atril;

    private String tiempoJugado;

    private int horasJugadasInGame;
    private int minutosJugadosInGame;
    private int segundosJugadosInGame;

    private int horasJugadas;
    private int minutosJugados;
    private int segundosJugados;


    public Jugador() {
        this.atril = new Ficha[7];
    }

    public Jugador(String alias,String correoElectronico, int scoreTotal, int horasJugadas, int minutosJugados, int segundosJugados, int cantidadDePalabras) {
        this.alias = alias;
        this.atril = new Ficha[7];
        this.correoElectronico = correoElectronico;
        this.scoreInGame = 0;
        this.tiempoJugado = "0h 0m 0s";

        //datos de usuario
        this.scoreTotal = scoreTotal;
        this.horasJugadas = horasJugadas;
        this.minutosJugados = minutosJugados;
        this.segundosJugados = segundosJugados;
        this.cantidadDePalabras = cantidadDePalabras;


    }

    public int getCantidadPalabrasColocadas() {
        return cantidadPalabrasColocadas;
    }

    public void setCantidadPalabrasColocadas(int cantidadPalabrasColocadas) {
        this.cantidadPalabrasColocadas = cantidadPalabrasColocadas;
    }

    public Ficha[] getAtril() {
        return atril;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public int getSegundosJugados() {
        return segundosJugados;
    }

    public void setSegundosJugados(int segundosJugados) {
        this.segundosJugados = segundosJugados;
    }

    public int getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(int horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public String getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(String tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public int getCantidadDePalabras() {
        return cantidadDePalabras;
    }

    public void setCantidadDePalabras(int cantidadDePalabras) {
        this.cantidadDePalabras = cantidadDePalabras;
    }

    public int getScoreInGame() {
        return scoreInGame;
    }

    public void setScoreInGame(int scoreInGame) {
        this.scoreInGame = scoreInGame;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Ficha[] clonarFichas() {
        Ficha[] clon = new Ficha[atril.length];
        for (int i = 0; i < atril.length; i++) {
            clon[i] = atril[i].clone();
        }
        return clon;
    }

    public boolean atrilIsEmpty() {
        for(Ficha ficha : atril) {
            if(ficha != null) {
                return false;
            }
        }
        return true;
    }

    public void setAtril(Ficha[] fichas) {
        for (int i = 0; i < fichas.length; i++) {
            if (fichas[i] != null) {
                this.atril[i] = fichas[i].clone();
            }
        }
    }

    public void addToScore(int scoreTotal) {
        this.scoreInGame += scoreTotal;
    }

    public void addToCantidadPalabrasColocadas(int cantidadPalabras) {
        this.cantidadPalabrasColocadas += cantidadPalabras;
    }

}
