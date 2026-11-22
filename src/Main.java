import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.carregarDadosIniciais();
        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = (int) lerNumero("Opcao: ");
            System.out.println();
            executar(opcao, biblioteca);
            System.out.println();
        }
        System.out.println("Sistema encerrado.");
    }

    private static void exibirMenu() {
        System.out.println("===== BIBLIOTECA PUBLICA =====");
        System.out.println("1  - Cadastrar livro");
        System.out.println("2  - Buscar livro por ISBN");
        System.out.println("3  - Remover livro");
        System.out.println("4  - Listar acervo (percursos da arvore)");
        System.out.println("5  - Emprestar livro");
        System.out.println("6  - Devolver livro");
        System.out.println("7  - Repor proximo livro da pilha");
        System.out.println("8  - Listar fila de reservas");
        System.out.println("9  - Listar pilha de devolucoes");
        System.out.println("10 - Buscar reserva por ISBN");
        System.out.println("0  - Sair");
    }

    private static void executar(int opcao, Biblioteca biblioteca) {
        switch (opcao) {
            case 1:
                long isbn = lerNumero("ISBN: ");
                String titulo = lerTexto("Titulo: ");
                String autor = lerTexto("Autor: ");
                System.out.println(biblioteca.cadastrar(isbn, titulo, autor));
                break;
            case 2:
                Livro livro = biblioteca.buscar(lerNumero("ISBN: "));
                System.out.println(livro == null ? "Livro nao encontrado." : livro);
                break;
            case 3:
                System.out.println(biblioteca.remover(lerNumero("ISBN: ")));
                break;
            case 4:
                biblioteca.getAcervo().percursos();
                break;
            case 5:
                long isbnEmprestimo = lerNumero("ISBN: ");
                String usuario = lerTexto("Nome do usuario: ");
                System.out.println(biblioteca.emprestar(isbnEmprestimo, usuario));
                break;
            case 6:
                System.out.println(biblioteca.devolver(lerNumero("ISBN: ")));
                break;
            case 7:
                System.out.println(biblioteca.reporProximo());
                break;
            case 8:
                biblioteca.getReservas().listar();
                break;
            case 9:
                biblioteca.getDevolucoes().listar();
                break;
            case 10:
                Reserva reserva = biblioteca.getReservas().buscar(lerNumero("ISBN: "));
                System.out.println(reserva == null ? "Nenhuma reserva para esse ISBN." : reserva);
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static long lerNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Long.parseLong(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        String texto = "";
        while (texto.isEmpty()) {
            System.out.print(mensagem);
            texto = entrada.nextLine().trim();
        }
        return texto;
    }
}
