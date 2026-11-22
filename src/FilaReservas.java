public class FilaReservas {
    private NoFila inicio;
    private NoFila fim;
    private int tamanho;

    public FilaReservas() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void inserir(Reserva reserva) {
        NoFila novo = new NoFila(reserva);
        if (fim == null) {
            inicio = novo;
        } else {
            fim.proximo = novo;
        }
        fim = novo;
        tamanho++;
    }

    public Reserva remover() {
        if (inicio == null) {
            return null;
        }
        Reserva removida = inicio.reserva;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return removida;
    }

    public Reserva removerPorIsbn(long isbn) {
        NoFila anterior = null;
        NoFila atual = inicio;
        while (atual != null) {
            if (atual.reserva.getIsbn() == isbn) {
                if (anterior == null) {
                    inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                if (atual == fim) {
                    fim = anterior;
                }
                tamanho--;
                return atual.reserva;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return null;
    }

    public Reserva buscar(long isbn) {
        NoFila atual = inicio;
        while (atual != null) {
            if (atual.reserva.getIsbn() == isbn) {
                return atual.reserva;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean existe(long isbn, String usuario) {
        NoFila atual = inicio;
        while (atual != null) {
            if (atual.reserva.getIsbn() == isbn && atual.reserva.getUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public void listar() {
        if (inicio == null) {
            System.out.println("Fila de reservas vazia.");
            return;
        }
        NoFila atual = inicio;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.reserva);
            atual = atual.proximo;
            posicao++;
        }
    }
}
