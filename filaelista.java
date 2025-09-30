import java.util.Scanner;

class Node {
    String data;   
    Node next;    

  
    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class FilaEncadeada {
    private Node front;  
    private Node rear;  

   
    public FilaEncadeada() {
        this.front = null;
        this.rear = null;
    }

    
    public void enfileirar(String data) {
        Node newNode = new Node(data);
        
        if (rear == null) {
         
            front = rear = newNode;
        } else {
           
            rear.next = newNode;
            rear = newNode;
        }

        System.out.println("Elemento '" + data + "' enfileirado.");
    }
   
    public void desenfileirar() {
        if (front == null) {
            System.out.println("A fila está vazia.");
            return;
        }

        String removedData = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        System.out.println("Elemento '" + removedData + "' desenfileirado.");
    }

    public void exibirPrimeiro() {
        if (front == null) {
            System.out.println("A fila está vazia.");
        } else {
            System.out.println("O primeiro elemento da fila é: " + front.data);
        }
    }

    public void exibirFila() {
        if (front == null) {
            System.out.println("A fila está vazia.");
            return;
        }

        Node current = front;
        System.out.print("Fila: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class filaelista {
    public static void main(String[] args) {
        FilaEncadeada fila = new FilaEncadeada();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opcao:");
            System.out.println("1. Enfileirar");
            System.out.println("2. Desenfileirar");
            System.out.println("3. Exibir o primeiro elemento");
            System.out.println("4. Exibir a fila");
            System.out.println("5. Sair");

            System.out.print("Digite a opcao: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    System.out.print("Digite o elemento para enfileirar: ");
                    String elemento = scanner.nextLine();
                    fila.enfileirar(elemento);
                }

                case 2 -> fila.desenfileirar();

                case 3 -> fila.exibirPrimeiro();

                case 4 -> fila.exibirFila();

                case 5 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
