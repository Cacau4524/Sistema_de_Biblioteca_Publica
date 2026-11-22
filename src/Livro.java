public class Livro {
    private long isbn;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(long isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
