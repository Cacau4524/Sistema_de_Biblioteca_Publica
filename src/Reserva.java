public class Reserva {
    private long isbn;
    private String usuario;

    public Reserva(long isbn, String usuario) {
        this.isbn = isbn;
        this.usuario = usuario;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "ISBN " + isbn + " | Usuario: " + usuario;
    }
}
