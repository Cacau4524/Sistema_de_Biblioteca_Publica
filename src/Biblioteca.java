public class Biblioteca {
    private ArvoreAcervo acervo;
    private FilaReservas reservas;
    private PilhaDevolucoes devolucoes;

    public Biblioteca() {
        this.acervo = new ArvoreAcervo();
        this.reservas = new FilaReservas();
        this.devolucoes = new PilhaDevolucoes();
    }

    public ArvoreAcervo getAcervo() {
        return acervo;
    }

    public FilaReservas getReservas() {
        return reservas;
    }

    public PilhaDevolucoes getDevolucoes() {
        return devolucoes;
    }

    public String cadastrar(long isbn, String titulo, String autor) {
        if (acervo.inserir(new Livro(isbn, titulo, autor))) {
            return "Livro cadastrado com sucesso.";
        }
        return "Ja existe um livro com esse ISBN.";
    }

    public Livro buscar(long isbn) {
        return acervo.buscar(isbn);
    }

    public String remover(long isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro nao encontrado.";
        }
        if (!livro.isDisponivel()) {
            return "O livro esta emprestado ou aguardando reposicao e nao pode ser removido.";
        }
        acervo.excluir(isbn);
        return "Livro removido do acervo.";
    }

    public String emprestar(long isbn, String usuario) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro nao encontrado.";
        }
        if (livro.isDisponivel()) {
            livro.emprestarPara(usuario);
            return "Livro emprestado para " + usuario + ".";
        }
        if (usuario.equalsIgnoreCase(livro.getEmprestadoPara())) {
            return usuario + " ja esta com esse livro emprestado.";
        }
        if (reservas.existe(isbn, usuario)) {
            return usuario + " ja possui reserva para esse livro.";
        }
        reservas.inserir(new Reserva(isbn, usuario));
        return "Livro indisponivel. Reserva registrada para " + usuario + " (posicao " + reservas.getTamanho() + " na fila geral).";
    }

    public String devolver(long isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro nao encontrado.";
        }
        if (livro.isDisponivel()) {
            return "Esse livro nao esta emprestado.";
        }
        if (devolucoes.buscar(isbn) != null) {
            return "Esse livro ja foi devolvido e aguarda reposicao.";
        }
        livro.registrarDevolucao();
        devolucoes.inserir(livro);
        return "Devolucao registrada. O livro aguarda reposicao na prateleira.";
    }

    public String reporProximo() {
        Livro livro = devolucoes.remover();
        if (livro == null) {
            return "Nenhuma devolucao aguardando reposicao.";
        }
        Reserva reserva = reservas.removerPorIsbn(livro.getIsbn());
        if (reserva != null) {
            livro.emprestarPara(reserva.getUsuario());
            return "Livro \"" + livro.getTitulo() + "\" separado para " + reserva.getUsuario() + ", primeiro da fila de reservas.";
        }
        livro.colocarNaPrateleira();
        return "Livro \"" + livro.getTitulo() + "\" reposto na prateleira e disponivel.";
    }

    public void carregarDadosIniciais() {
        cadastrar(9788535911664L, "Dom Casmurro", "Machado de Assis");
        cadastrar(9788535902778L, "O Cortico", "Aluisio Azevedo");
        cadastrar(9788535914849L, "Capitaes da Areia", "Jorge Amado");
        cadastrar(9788501000001L, "Vidas Secas", "Graciliano Ramos");
        cadastrar(9788520926710L, "Grande Sertao: Veredas", "Guimaraes Rosa");
        cadastrar(9788535920000L, "A Hora da Estrela", "Clarice Lispector");
        cadastrar(9788525406958L, "Memorias Postumas de Bras Cubas", "Machado de Assis");
    }
}
