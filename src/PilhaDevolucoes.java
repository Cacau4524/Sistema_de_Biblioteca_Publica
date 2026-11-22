public class PilhaDevolucoes {
    private NoPilha topo;

    public PilhaDevolucoes() {
        this.topo = null;
    }

    public void inserir(Livro livro) {
        NoPilha novo = new NoPilha(livro);
        novo.abaixo = topo;
        topo = novo;
    }

    public Livro remover() {
        if (topo == null) {
            return null;
        }
        Livro removido = topo.livro;
        topo = topo.abaixo;
        return removido;
    }

    public Livro buscar(long isbn) {
        NoPilha atual = topo;
        while (atual != null) {
            if (atual.livro.getIsbn() == isbn) {
                return atual.livro;
            }
            atual = atual.abaixo;
        }
        return null;
    }

    public void listar() {
        if (topo == null) {
            System.out.println("Nenhuma devolucao aguardando reposicao.");
            return;
        }
        NoPilha atual = topo;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.livro);
            atual = atual.abaixo;
            posicao++;
        }
    }
}
