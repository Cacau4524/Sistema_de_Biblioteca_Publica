public class Livro {
    private long isbn;
    private String titulo;
    private String autor;
    private boolean disponivel;
    private String emprestadoPara;

    public Livro(long isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
        this.emprestadoPara = null;
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

    public String getEmprestadoPara() {
        return emprestadoPara;
    }

    public void emprestarPara(String usuario) {
        this.disponivel = false;
        this.emprestadoPara = usuario;
    }

    public void registrarDevolucao() {
        this.disponivel = false;
        this.emprestadoPara = null;
    }

    public void colocarNaPrateleira() {
        this.disponivel = true;
        this.emprestadoPara = null;
    }

    @Override
    public String toString() {
        String situacao;
        if (disponivel) {
            situacao = "Disponivel";
        } else if (emprestadoPara != null) {
            situacao = "Emprestado para " + emprestadoPara;
        } else {
            situacao = "Aguardando reposicao";
        }
        return "ISBN " + isbn + " | " + titulo + " | " + autor + " | " + situacao;
    }
}
