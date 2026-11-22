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

    public boolean excluir(long isbn) {
        if (buscar(isbn) == null) {
            return false;
        }
        raiz = excluirNo(raiz, isbn);
        return true;
    }

    private NoArvore excluirNo(NoArvore atual, long isbn) {
        if (atual == null) {
            return null;
        }
        if (isbn < atual.livro.getIsbn()) {
            atual.esquerda = excluirNo(atual.esquerda, isbn);
        } else if (isbn > atual.livro.getIsbn()) {
            atual.direita = excluirNo(atual.direita, isbn);
        } else {
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }
            NoArvore sucessor = menorNo(atual.direita);
            atual.livro = sucessor.livro;
            atual.direita = excluirNo(atual.direita, sucessor.livro.getIsbn());
        }
        return atual;
    }

    private NoArvore menorNo(NoArvore atual) {
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public void percursos() {
        if (raiz == null) {
            System.out.println("Acervo vazio.");
            return;
        }
        System.out.println("--- In-ordem ---");
        emOrdem(raiz);
        System.out.println("--- Pre-ordem ---");
        preOrdem(raiz);
        System.out.println("--- Pos-ordem ---");
        posOrdem(raiz);
    }

    private void emOrdem(NoArvore atual) {
        if (atual != null) {
            emOrdem(atual.esquerda);
            System.out.println(atual.livro);
            emOrdem(atual.direita);
        }
    }

    private void preOrdem(NoArvore atual) {
        if (atual != null) {
            System.out.println(atual.livro);
            preOrdem(atual.esquerda);
            preOrdem(atual.direita);
        }
    }

    private void posOrdem(NoArvore atual) {
        if (atual != null) {
            posOrdem(atual.esquerda);
            posOrdem(atual.direita);
            System.out.println(atual.livro);
        }
    }
}
