package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import classe.Leitor;
import classe.Livro;
import classe.Pessoa;


public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_LEITORES = 10;
        final int MAX_LIVROS = 30;
        int opcao, qtdLeitoresCadastrados = 0, qtdLivrosCadastrados = 0;
        ArrayList<Leitor> leitores = new ArrayList<Leitor>();
        ArrayList<Livro> livros = new ArrayList<Livro>();
        Scanner in = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar leitor");
            System.out.println("2 - Listar leitores cadastrados");
            System.out.println("3 - Localizar leitor pela matrícula");
            System.out.println("4 - Cadastrar livro");
            System.out.println("5 - Listar livros");
            System.out.println("6 - Emprestar livro");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, saio do cadastro
                if (qtdLeitoresCadastrados == MAX_LEITORES) {
                    System.out.println("\nNão há espaço para cadastrar novos leitores.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("Insira seu nome:");
                String nome = scanner.next();
                System.out.println("Insira seu cpf:");
                String cpf = scanner.next();

                Pessoa pessoa = new Pessoa(nome, cpf);

                String pessoanome = pessoa.getNome();
                String pessoacpf = pessoa.getCpf();
        
                //Cadastre seu leitor aqui
                
                Leitor leitor = new Leitor(pessoanome, pessoacpf);
                
                leitor.setMatricula(pessoacpf);

                leitores.add(leitor);

                qtdLeitoresCadastrados += 1;

                System.out.println("\nLeitor cadastrado com sucesso.");
                voltarMenu(in);
           
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdLeitoresCadastrados == 0) {
                    System.out.println("\nNão há leitores cadastrados cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                for (Leitor leitor : leitores) {
                    String leitornome = leitor.getNome();
                    String leitormatricula = leitor.getMatricula();
                    System.out.println("=====================");
                    System.out.printf(leitornome);
                    System.out.printf("\n" + leitormatricula);
                    System.out.println("\n=====================");
                }
                
                voltarMenu(in);
            } else if (opcao == 3) {

                System.out.println("Digite a matricula do leitor:");
                String matricula = scanner.next();

                for (Leitor leitor : leitores) {
                    String leitormatricula = leitor.getMatricula();
                    String leitornome = leitor.getNome();
                    
                    if (matricula.equals(leitormatricula)){
                        System.out.println("=====================");
                        System.out.printf("nome: " + leitornome  );
                        System.out.println("\nmatricula: " + matricula);
                        System.out.println("\n=====================");
                    } else{
                        System.out.println("Nenhum leitor com essa matricula");
                    }
                }

            } else if (opcao == 4) {

                // Se não tem mais espaço no vetor, saio do cadastro
                if (qtdLivrosCadastrados == MAX_LIVROS) {
                    System.out.println("\nNão há espaço para cadastrar novos livros.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("Insira o nome do livro:");
                String titulo = scanner.next();
                System.out.println("Qual o autor do livro:");
                String autor = scanner.next();
                System.out.println("Quantas paginas tem o livro:");
                int paginas = scanner.nextInt();

                Livro livro = new Livro(titulo, autor, paginas);
                
                String tituloLivro = livro.getTitulo();
                String autorLivro = livro.getAutor();
                int paginasLivro = livro.getPaginas();

                livros.add(livro);

                qtdLivrosCadastrados += 1;

                //Cadastre seu livro aqui

            } else if (opcao == 5) {

                // Se não tem ninguém livros no vetor, caio fora
                if (qtdLivrosCadastrados == 0) {
                    System.out.println("\nNão há livros cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                for (Livro livro : livros) {
                    //String autorLivro= livro.getAutor();
                    String tituloLivro = livro.getTitulo();
                    //int paginasLivro = livro.getPaginas();

                    System.out.println("===============");
                    System.out.println("Titulo do livro: " + tituloLivro);
                    System.out.println("===============");
                    System.out.println("Quantida de livros cadastrados: " + qtdLivrosCadastrados);
                    System.out.println("===============");
                } 
                      
                // Exiba os livros aqui

                voltarMenu(in);

            } else if (opcao == 6) {

                System.out.println("Digite a matricula do leitor:");
                String matricula = scanner.next();
                              
                for (Leitor leitor : leitores) {
                    String leitormatricula = leitor.getMatricula();
                    String leitornome = leitor.getNome();
                    
                    if (matricula.equals(leitormatricula)){
                        System.out.println("Digite o livro a ser retirado:");
                        String titulo = scanner.next();
                        for (Livro livro : livros) {
                            String livroCadastrado = livro.getTitulo();
                            boolean livroEmprestado = livro.getEmprestado();
                           if (!(livroCadastrado.equals(titulo)) || (livroEmprestado)){
                                System.out.println("Livro emprestado a outro leitor ou inexistente!");
                           } else{
                            leitor.emprestarLivro(livro);
                            livro.setEmprestado(true);
                            System.out.println("=================");
                            System.out.printf("Nome do leitor: " + leitornome);
                            System.out.printf("\nNumero da matricula: " + leitormatricula);
                            System.out.println("\nLivro retirado: " + livroCadastrado);
                           }
                        }
                       
                    } else{
                        System.out.println("Nenhum leitor com essa matricula");
                    }
                }               

                //Se não tem leitor cadastrado, não tem como prosseguir com o empréstimo
                //Se não tem livro cadastrado, não tem como prosseguir com o empréstimo
                //Peça ao usuário para selecionar um livro, se o atributo emprestado for verdadeiro
                //inforque que não pode emprestar esse livro, se tiver falso, selecione o leitor e
                //atribua o livro ao leitor.

                //Exiba os dados do leitor e o livro que foi emprestado pra ele.

                voltarMenu(in);
            }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}