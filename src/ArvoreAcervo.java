public class ArvoreAcervo {
    private NoArvore raiz;

    public ArvoreAcervo() {
        this.raiz = null;
    }

    public boolean inserir(Livro livro) {
        if (buscar(livro.getIsbn()) != null) {
            return false;
        }
        raiz = inserirNo(raiz, livro);
        return true;
    }

    private NoArvore inserirNo(NoArvore atual, Livro livro) {
        if (atual == null) {
            return new NoArvore(livro);
        }
        if (livro.getIsbn() < atual.livro.getIsbn()) {
            atual.esquerda = inserirNo(atual.esquerda, livro);
        } else {
            atual.direita = inserirNo(atual.direita, livro);
        }
        return atual;
    }

    public Livro buscar(long isbn) {
        return buscarNo(raiz, isbn);
    }

    private Livro buscarNo(NoArvore atual, long isbn) {
        if (atual == null) {
            return null;
        }
        if (isbn == atual.livro.getIsbn()) {
            return atual.livro;
        }
        if (isbn < atual.livro.getIsbn()) {
            return buscarNo(atual.esquerda, isbn);
        }
        return buscarNo(atual.direita, isbn);
    }
}
