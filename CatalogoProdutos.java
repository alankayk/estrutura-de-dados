import java.util.Scanner;

public class CatalogoProdutos {

    
    static class Produto {
        int codigo;
        String nome;
        Produto esquerda, direita;

        Produto(int codigo, String nome) {
            this.codigo = codigo;
            this.nome = nome;
        }

        @Override
        public String toString() {
            return "Código: " + codigo + ", Nome: " + nome;
        }
    }

    static Produto raiz = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n===== CATÁLOGO DE PRODUTOS =====");
            System.out.println("1 - Inserir produto");
            System.out.println("2 - Buscar produto por código");
            System.out.println("3 - Listar produtos (ordem crescente)");
            System.out.println("4 - Mostrar produto com maior código");
            System.out.println("5 - Mostrar produto com menor código");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine(); 

            switch (op) {
                case 1 -> {
                    System.out.print("Digite o código do produto: ");
                    int codigo = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();
                    raiz = inserir(raiz, new Produto(codigo, nome));
                    System.out.println(" Produto inserido com sucesso!");
                }

                case 2 -> {
                    System.out.print("Digite o código do produto a buscar: ");
                    int codigoBusca = sc.nextInt();
                    Produto encontrado = buscar(raiz, codigoBusca);
                    if (encontrado != null)
                        System.out.println(" Produto encontrado: " + encontrado);
                    else
                        System.out.println(" Produto não encontrado.");
                }

                case 3 -> {
                    System.out.println("\n Produtos em ordem crescente de código:");
                    listar(raiz);
                }

                case 4 -> {
                    Produto maior = maior(raiz);
                    if (maior != null)
                        System.out.println(" Produto com maior código: " + maior);
                    else
                        System.out.println("Catálogo vazio.");
                }

                case 5 -> {
                    Produto menor = menor(raiz);
                    if (menor != null)
                        System.out.println(" Produto com menor código: " + menor);
                    else
                        System.out.println("Catálogo vazio.");
                }

                case 0 -> System.out.println(" Encerrando o sistema. Até logo!");

                default -> System.out.println(" Opção inválida, tente novamente.");
            }

        } while (op != 0);

        sc.close();
    }

    
    static Produto inserir(Produto atual, Produto novo) {
        if (atual == null) return novo;
        if (novo.codigo < atual.codigo)
            atual.esquerda = inserir(atual.esquerda, novo);
        else if (novo.codigo > atual.codigo)
            atual.direita = inserir(atual.direita, novo);
        else
            System.out.println(" Produto com esse código já existe!");
        return atual;
    }

    static Produto buscar(Produto atual, int codigo) {
        if (atual == null || atual.codigo == codigo) return atual;
        return (codigo < atual.codigo) ? buscar(atual.esquerda, codigo) : buscar(atual.direita, codigo);
    }

    static void listar(Produto atual) {
        if (atual != null) {
            listar(atual.esquerda);
            System.out.println(atual);
            listar(atual.direita);
        }
    }

    static Produto maior(Produto atual) {
        if (atual == null) return null;
        while (atual.direita != null)
            atual = atual.direita;
        return atual;
    }

    static Produto menor(Produto atual) {
        if (atual == null) return null;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }
}
