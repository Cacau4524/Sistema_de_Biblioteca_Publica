public class NoPilha {
    Livro livro;
    NoPilha abaixo;

    public NoPilha(Livro livro) {
        this.livro = livro;
        this.abaixo = null;
    }
}
