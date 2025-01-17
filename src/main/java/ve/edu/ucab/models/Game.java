package ve.edu.ucab.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {
    private Jugador[] jugadores;
    private final BolsaFichas bolsaFichas;
    private final Board board;
    private int turnoActual;
    private Jugador jugadorActual;
    private ArrayList<int[]> indiceFichasPuestas;
    private final PalabraExtractor palabraExtractor = new PalabraExtractor();
    private boolean esPrimeraJugada;
    private int contadorPases = 0;
    private boolean estaFinalizada = false;

    public Game() {
        this.indiceFichasPuestas = new ArrayList<>();

        jugadores = new Jugador[2];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Jugador();
        }

        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = new Random().nextInt(2);
        jugadorActual = jugadores[turnoActual];
        esPrimeraJugada = true;
    }

    public Game(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.indiceFichasPuestas = new ArrayList<>();
        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = new Random().nextInt(2);
        jugadorActual = jugadores[turnoActual];
        esPrimeraJugada = true;

    }

    public void actualizarAtrilJugadores() {
        for(Jugador j : jugadores) {
            j.rellenarFichas(bolsaFichas);
        }
    }

    public void ponerFicha(int i, int j, Ficha ficha) {
        this.board.getCasillas()[i][j].setFicha(ficha);
        indiceFichasPuestas.add(new int[]{i, j});
    }

    public void quitarFicha(int i, int j) {
        Ficha ficha = board.getCasillas()[i][j].getFicha();
        jugadorActual.addToAtril(ficha);
        board.getCasillas()[i][j].setFicha(new Ficha());
        removerIndiceFichas(i, j);
    }

    private void removerIndiceFichas(int i, int j) {
        for (int k = 0; k < indiceFichasPuestas.size(); k++) {
            int[] indices = indiceFichasPuestas.get(k);
            if (indices[0] == i && indices[1] == j) {
                indiceFichasPuestas.remove(k);
                break;
            }
        }
    }

    public boolean confirmarJugada(){
        if (indiceFichasPuestas.isEmpty()) {
            System.out.println("Usted hizo una jugada inválida!");
            return false;
        } else if (verificarPosicionValida(indiceFichasPuestas) && palabraExtractor.verificarPalabrasFormadas(indiceFichasPuestas, board, jugadorActual)) {
            siguienteTurno();
            return true;
        }else{
            System.out.println("Usted hizo una jugada inválida!");
            return false;
        }
    }

    private void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.length;
        jugadorActual = jugadores[turnoActual];
        fijarCasillas(indiceFichasPuestas);
        indiceFichasPuestas = new ArrayList<>();
        contadorPases = 0;
        if(esPrimeraJugada) {
            esPrimeraJugada = false;
        }
    }

    public void pasarTurno(){
        JsonUtil.guardarPartidaPendiente(this);
        if (!esPrimeraJugada) {
            turnoActual = (turnoActual + 1) % jugadores.length;
            jugadorActual = jugadores[turnoActual];
            contadorPases++;
        }
    }

    private void fijarCasillas(ArrayList<int[]> indices){
        for (int[] indice : indices) {
            board.getCasillas()[indice[0]][indice[1]].setMovable(false);
        }
    }

    public boolean esPartidaTerminada() {
       if((jugadorActual.atrilIsEmpty() && bolsaFichas.isEmpty()) || contadorPases == 3){
           estaFinalizada = true;
            return true;
       } else {
           return false;
       }
    }

    public int calcularGanador() {
        int mayor = 0;
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if(jugador.getScoreInGame() > mayor){
                mayor = jugador.getScoreInGame();
                ganador = jugador;
            }

        }
        return Arrays.asList(jugadores).indexOf(ganador);
    }

    private boolean verificarPosicionValida(ArrayList<int[]> indices) {
        return verificarIndicesValidos(indices) && (verificarFichasConectadas(indices) || verificarPrimeraJugada(indices));
    }

    private boolean verificarPrimeraJugada(ArrayList<int[]> indices) {
        if (esPrimeraJugada) {
            for (int[] indice : indices) {
                if (indice[0] == 7 && indice[1] == 7) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarIndicesValidos(ArrayList<int[]> indices) {
        indices.removeIf(Objects::isNull);
        if (indices.isEmpty()){
            return false;
        }

        int i = indices.getFirst()[0];
        int j = indices.getFirst()[1];

        for(int[] indice : indices) {
            if (indice[0] != i && indice[1] != j) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarFichasConectadas(ArrayList<int[]> indices) {
        indices.removeIf(Objects::isNull);
        if (indices.isEmpty()) {
            return false;
        }

        for (int[] indice : indices) {
            int fila = indice[0];
            int columna = indice[1];

            // Verificar casillas adyacentes
            if ((fila > 0 && !board.getCasillas()[fila - 1][columna].isMovable()) ||
                    (fila < 14 && !board.getCasillas()[fila + 1][columna].isMovable()) ||
                    (columna > 0 && !board.getCasillas()[fila][columna - 1].isMovable()) ||
                    (columna < 14 && !board.getCasillas()[fila][columna + 1].isMovable())) {
                return true;
            }
        }
        return false;
    }

    public String getClaveJugadores() {
        return Arrays.stream(jugadores).map(Jugador::getAlias).sorted().collect(Collectors.joining("_"));
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Board getBoard() {
        return board;
    }

    public BolsaFichas getBolsaFichas() {
        return bolsaFichas;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public ArrayList<int[]> getIndiceFichasPuestas() {
        indiceFichasPuestas.removeIf(Objects::isNull);
        if (indiceFichasPuestas.isEmpty()) {
            return null;
        }
        return indiceFichasPuestas;
    }

    public void setIndiceFichasPuestas(ArrayList<int[]> indiceFichasPuestas) {
        this.indiceFichasPuestas = indiceFichasPuestas;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public boolean isEsPrimeraJugada() {
        return esPrimeraJugada;
    }

    public void setEsPrimeraJugada(boolean esPrimeraJugada) {
        this.esPrimeraJugada = esPrimeraJugada;
    }

    public int getContadorPases() {
        return contadorPases;
    }

    public void setContadorPases(int contadorPases) {
        this.contadorPases = contadorPases;
    }

    public boolean isEstaFinalizada() {
        return estaFinalizada;
    }

    public void setEstaFinalizada(boolean estaFinalizada) {
        this.estaFinalizada = estaFinalizada;
    }
}