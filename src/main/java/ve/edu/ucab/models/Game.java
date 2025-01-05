package ve.edu.ucab.models;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private Jugador[] jugadores;
    private final BolsaFichas bolsaFichas;
    private final Board board;
    private int turnoActual;
    private Jugador jugadorActual;
    private ArrayList<int[]> indiceFichasPuestas;

    public Game() {
        this.indiceFichasPuestas = new ArrayList<>();

        jugadores = new Jugador[2];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Jugador();
        }

        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = 0;
        jugadorActual = jugadores[turnoActual];
    }

    public Game(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.indiceFichasPuestas = new ArrayList<>();
        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = 0;
        jugadorActual = jugadores[turnoActual];
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


}
