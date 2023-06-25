package classe;

import java.util.ArrayList;

public class Leitor extends Pessoa {
    private String matricula;
    private ArrayList <Livro> livrosEmprestados = new ArrayList<Livro>();

    public void emprestarLivro(Livro livro){
        if (livrosEmprestados.size() > 0 && livro.getEmprestado()){
            System.out.println("Livro ja emprestado");
        } else {
            livrosEmprestados.add(livro);
        }
    }

    public Leitor(String nome, String cpf) {
        super(nome, cpf);
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    

}
