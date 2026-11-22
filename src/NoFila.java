public class NoFila {
    Reserva reserva;
    NoFila proximo;

    public NoFila(Reserva reserva) {
        this.reserva = reserva;
        this.proximo = null;
    }
}
