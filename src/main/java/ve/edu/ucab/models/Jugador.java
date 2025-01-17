package ve.edu.ucab.models;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

/**
 * Representa un jugador en el juego de mesa.
 */
public class Jugador {

    /**
     * Alias del jugador.
     */
    private String alias;

    /**
     * Correo electrónico del jugador.
     */
    private String correoElectronico;


    /**
     * Puntaje total del jugador.
     */
    private int scoreTotal;

    /**
     * Puntaje actual del jugador en el juego.
     */
    private int scoreInGame;


    /**
     * Cantidad total de palabras colocadas por el jugador.
     */
    private int cantidadDePalabras;

    /**
     * Cantidad de palabras colocadas por el jugador en el juego actual.
     */
    private int cantidadPalabrasColocadas = 0;

    /**
     * Atril de fichas del jugador.
     */
    private Ficha[] atril;

    /**
     * Tiempo total jugado por el jugador.
     */
    private String tiempoJugado;

    /**
     * Horas jugadas por el jugador.
     */
    private int horasJugadas;

    /**
     * Minutos jugados por el jugador.
     */
    private int minutosJugados;

    /**
     * Segundos jugados por el jugador.
     */
    private int segundosJugados;

    /**
     * Constructor por defecto que inicializa un jugador con el alias "Invitado".
     */
    public Jugador() {
        this.atril = new Ficha[7];
        this.alias = "Invitado";
        this.scoreInGame = 0;
    }

    /**
     * Constructor que inicializa un jugador con el alias proporcionado.
     *
     * @param alias El alias del jugador.
     */
    public Jugador(String alias) {
        this.atril = new Ficha[7];
        this.alias = alias;
        this.scoreInGame = 0;
    }

    /**
     * Constructor que inicializa un jugador con todos los datos proporcionados.
     *
     * @param alias              El alias del jugador.
     * @param correoElectronico  El correo electrónico del jugador.
     * @param scoreTotal         El puntaje total del jugador.
     * @param horasJugadas       Las horas jugadas por el jugador.
     * @param minutosJugados     Los minutos jugados por el jugador.
     * @param segundosJugados    Los segundos jugados por el jugador.
     * @param cantidadDePalabras La cantidad total de palabras colocadas por el jugador.
     */
    public Jugador(String alias, String correoElectronico, int scoreTotal, int horasJugadas, int minutosJugados, int segundosJugados, int cantidadDePalabras) {
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

    public Jugador(Usuario usuario) {
        this.alias = usuario.getAlias();
        this.atril = new Ficha[7];
        this.correoElectronico = usuario.getCorreoElectronico();
        this.scoreInGame = 0;
        this.tiempoJugado = "0h 0m 0s";

        //datos de usuario
        this.scoreTotal = usuario.getScoreTotal();
        this.horasJugadas = usuario.getHorasJugadas();
        this.minutosJugados = usuario.getMinutosJugados();
        this.segundosJugados = usuario.getSegundosJugados();
        this.cantidadDePalabras = usuario.getCantidadDePalabras();

    }

    /**
     * Obtiene la cantidad de palabras colocadas por el jugador en el juego actual.
     *
     * @return La cantidad de palabras colocadas.
     */
    public int getCantidadPalabrasColocadas() {
        return cantidadPalabrasColocadas;
    }

    /**
     * Establece la cantidad de palabras colocadas por el jugador en el juego actual.
     *
     * @param cantidadPalabrasColocadas La nueva cantidad de palabras colocadas.
     */
    public void setCantidadPalabrasColocadas(int cantidadPalabrasColocadas) {
        this.cantidadPalabrasColocadas = cantidadPalabrasColocadas;
    }

    /**
     * Obtiene el atril de fichas del jugador.
     *
     * @return El atril de fichas.
     */
    public Ficha[] getAtril() {
        return atril;
    }

    /**
     * Añade una ficha al atril del jugador.
     *
     * @param ficha La ficha a añadir.
     */
    public void addToAtril(Ficha ficha) {
        for (int i = 0; i < atril.length; i++) {
            if (atril[i] == null) {
                atril[i] = ficha;
                break;
            }
        }
    }

    /**
     * Rellena el atril del jugador con fichas de la bolsa.
     *
     * @param bolsaFichas La bolsa de fichas.
     */
    public void rellenarFichas(BolsaFichas bolsaFichas) {
        for (int i = 0; i < 7; i++) {
            if (atril[i] == null && !bolsaFichas.getListaFichas().isEmpty()) {
                int randomIndex = (int) (Math.random() * (bolsaFichas.getListaFichas().size() - 1));
                atril[i] = bolsaFichas.getListaFichas().get(randomIndex).clone();
                bolsaFichas.getListaFichas().remove(randomIndex);
            }
        }
    }


    /**
     * Obtiene los minutos jugados por el jugador.
     *
     * @return Los minutos jugados.
     */
    public int getMinutosJugados() {
        return minutosJugados;
    }

    /**
     * Establece los minutos jugados por el jugador.
     *
     * @param minutosJugados Los nuevos minutos jugados.
     */
    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    /**
     * Establece los minutos jugados por el jugador.
     *
     * @param minutosJugados Los nuevos minutos jugados.
     */
    public void addMinutosJugados(int minutosJugados) {
        this.minutosJugados += minutosJugados;
    }

    /**
     * Obtiene los segundos jugados por el jugador.
     *
     * @return Los segundos jugados.
     */
    public int getSegundosJugados() {
        return segundosJugados;
    }

    /**
     * Establece los segundos jugados por el jugador.
     *
     * @param segundosJugados Los nuevos segundos jugados.
     */
    public void setSegundosJugados(int segundosJugados) {
        this.segundosJugados = segundosJugados;
    }

    /**
     * Establece los segundos jugados por el jugador.
     *
     * @param segundosJugados Los nuevos segundos jugados.
     */
    public void addSegundosJugados(int segundosJugados) {
        this.segundosJugados += segundosJugados;
    }

    /**
     * Obtiene las horas jugadas por el jugador.
     *
     * @return Las horas jugadas.
     */
    public int getHorasJugadas() {
        return horasJugadas;
    }

    /**
     * Establece las horas jugadas por el jugador.
     *
     * @param horasJugadas Las nuevas horas jugadas.
     */
    public void setHorasJugadas(int horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    /**
     * Establece las horas jugadas por el jugador.
     *
     * @param horasJugadas Las nuevas horas jugadas.
     */
    public void addHorasJugadas(int horasJugadas) {
        this.horasJugadas += horasJugadas;
    }

    /**
     * Obtiene el tiempo total jugado por el jugador.
     *
     * @return El tiempo jugado.
     */
    public String getTiempoJugado() {
        ajustarTiempo();
        tiempoJugado = String.format("%02d:%02d:%02d", horasJugadas, minutosJugados, segundosJugados);
        return tiempoJugado;
    }

    public void ajustarTiempo(){
        int totalSegundos = horasJugadas * 3600 + minutosJugados * 60 + segundosJugados;

        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        horasJugadas = horas;
        minutosJugados = minutos;
        segundosJugados = segundos;
    }


    /**
     * Establece el tiempo total jugado por el jugador.
     *
     * @param tiempoJugado El nuevo tiempo jugado.
     */
    public void setTiempoJugado(String tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    /**
     * Obtiene la cantidad total de palabras colocadas por el jugador.
     *
     * @return La cantidad de palabras.
     */
    public int getCantidadDePalabras() {
        return cantidadDePalabras;
    }

    /**
     * Establece la cantidad total de palabras colocadas por el jugador.
     *
     * @param cantidadDePalabras La nueva cantidad de palabras.
     */
    public void setCantidadDePalabras(int cantidadDePalabras) {
        this.cantidadDePalabras = cantidadDePalabras;
    }

    /**
     * Obtiene el puntaje actual del jugador en el juego.
     *
     * @return El puntaje en el juego.
     */
    public int getScoreInGame() {
        return scoreInGame;
    }

    /**
     * Establece el puntaje actual del jugador en el juego.
     *
     * @param scoreInGame El nuevo puntaje en el juego.
     */
    public void setScoreInGame(int scoreInGame) {
        this.scoreInGame = scoreInGame;
    }

    /**
     * Obtiene el puntaje total del jugador.
     *
     * @return El puntaje total.
     */
    public int getScoreTotal() {
        return scoreTotal;
    }

    /**
     * Establece el puntaje total del jugador.
     *
     * @param scoreTotal El nuevo puntaje total.
     */
    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    /**
     * Obtiene el correo electrónico del jugador.
     *
     * @return El correo electrónico.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del jugador.
     *
     * @param correoElectronico El nuevo correo electrónico.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el alias del jugador.
     *
     * @return El alias del jugador.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Establece el alias del jugador.
     *
     * @param alias El nuevo alias del jugador.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Clona las fichas del atril del jugador.
     *
     * @return Un array con las fichas clonadas.
     */
    public Ficha[] clonarFichas() {
        Ficha[] clon = new Ficha[atril.length];
        for (int i = 0; i < atril.length; i++) {
            clon[i] = atril[i].clone();
        }
        return clon;
    }

    /**
     * Verifica si el atril del jugador está vacío.
     *
     * @return true si el atril está vacío, false en caso contrario.
     */
    public boolean atrilIsEmpty() {
        for(Ficha ficha : atril) {
            if(ficha != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Establece las fichas del atril del jugador.
     *
     * @param fichas El array de fichas a establecer en el atril.
     */
    public void setAtril(Ficha[] fichas) {
        for (int i = 0; i < fichas.length; i++) {
            if (fichas[i] != null) {
                this.atril[i] = fichas[i].clone();
            }
        }
    }

    /**
     * Añade una cantidad al puntaje actual del jugador en el juego.
     *
     * @param scoreTotal La cantidad a añadir al puntaje.
     */
    public void addToScore(int scoreTotal) {
        this.scoreInGame += scoreTotal;
    }

    /**
     * Añade una cantidad a la cantidad de palabras colocadas por el jugador.
     *
     * @param cantidadPalabras La cantidad a añadir.
     */
    public void addToCantidadPalabrasColocadas(int cantidadPalabras) {
        this.cantidadPalabrasColocadas += cantidadPalabras;
    }

    /**
     * Muestra el atril del jugador en la consola.
     */
    public void mostrarAtrilEnConsola() {
        StringBuilder atril = new StringBuilder();
        for (Ficha ficha : this.atril) {
            if (ficha != null) {
                atril.append("[").append(ficha).append("]");
            } else {
                atril.append(" __ ");
            }
        }
        System.out.println(atril.toString());
    }

    /**
     * Reinicia el jugador, vaciando su atril y reseteando su puntaje en el juego.
     */
    public void reiniciarJugador(){
        this.atril = new Ficha[7];
        this.scoreInGame = 0;
    }

    /**
     * Añade una cantidad a la cantidad de palabras colocadas totales.
     *
     * @param cantidadPalabras La cantidad a añadir.
     */
    public void addToCantidadPalabras(int cantidadPalabras) {
        this.cantidadDePalabras += cantidadPalabras;
    }
}
