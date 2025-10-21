import java.util.Scanner;

public class BuscaEstudantes {

    static class Estudante {
        int matricula;
        String nome;

        Estudante(int matricula, String nome) {
            this.matricula = matricula;
            this.nome = nome;
        }

        public String toString() {
            return "Matrícula: " + matricula + " | Nome: " + nome;
        }
    }

   
    static void quickSort(Estudante[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(arr, inicio, fim);
            quickSort(arr, inicio, p - 1);
            quickSort(arr, p + 1, fim);
        }
    }

    static int particionar(Estudante[] arr, int inicio, int fim) {
        int pivo = arr[fim].matricula;
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (arr[j].matricula < pivo) {
                i++;
                Estudante temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Estudante temp = arr[i + 1];
        arr[i + 1] = arr[fim];
        arr[fim] = temp;
        return i + 1;
    }

   
    static int comparacoesIterativa = 0;
    static int buscaBinariaIterativa(Estudante[] arr, int matricula) {
        int inicio = 0, fim = arr.length - 1;
        while (inicio <= fim) {
            comparacoesIterativa++;
            int meio = (inicio + fim) / 2;
            if (arr[meio].matricula == matricula) return meio;
            if (arr[meio].matricula < matricula)
                inicio = meio + 1;
            else
                fim = meio - 1;
        }
        return -1;
    }

    
    static int comparacoesRecursiva = 0;
    static int buscaBinariaRecursiva(Estudante[] arr, int inicio, int fim, int matricula) {
        if (inicio > fim) return -1;
        comparacoesRecursiva++;
        int meio = (inicio + fim) / 2;

        if (arr[meio].matricula == matricula) return meio;
        if (arr[meio].matricula < matricula)
            return buscaBinariaRecursiva(arr, meio + 1, fim, matricula);
        else
            return buscaBinariaRecursiva(arr, inicio, meio - 1, matricula);
    }

    
    static int posicaoInsercao(Estudante[] arr, int matricula) {
        int inicio = 0, fim = arr.length - 1;
        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            if (arr[meio].matricula == matricula)
                return meio;
            if (arr[meio].matricula < matricula)
                inicio = meio + 1;
            else
                fim = meio - 1;
        }
        return inicio; 
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos estudantes deseja cadastrar? ");
        int n = sc.nextInt();
        sc.nextLine();

        Estudante[] estudantes = new Estudante[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Matrícula do estudante " + (i + 1) + ": ");
            int matricula = sc.nextInt();
            sc.nextLine();
            System.out.print("Nome do estudante " + (i + 1) + ": ");
            String nome = sc.nextLine();
            estudantes[i] = new Estudante(matricula, nome);
        }

        
        quickSort(estudantes, 0, n - 1);

        System.out.println("\n--- Estudantes ordenados por matrícula ---");
        for (Estudante e : estudantes) System.out.println(e);

        
        System.out.print("\nDigite a matrícula que deseja buscar: ");
        int busca = sc.nextInt();

        int posIter = buscaBinariaIterativa(estudantes, busca);
        int posRec = buscaBinariaRecursiva(estudantes, 0, n - 1, busca);

        if (posIter != -1)
            System.out.println("\n[Iterativa] Estudante encontrado: " + estudantes[posIter]);
        else
            System.out.println("\n[Iterativa] Estudante não encontrado.");

        if (posRec != -1)
            System.out.println("[Recursiva] Estudante encontrado: " + estudantes[posRec]);
        else
            System.out.println("[Recursiva] Estudante não encontrado.");

        System.out.println("\nComparações (Iterativa): " + comparacoesIterativa);
        System.out.println("Comparações (Recursiva): " + comparacoesRecursiva);

        
        int posInsercao = posicaoInsercao(estudantes, busca);
        System.out.println("\nPosição de inserção (para manter ordem): " + posInsercao);

        sc.close();
    }
}
