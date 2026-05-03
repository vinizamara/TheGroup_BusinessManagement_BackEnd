import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private int idAgenda;
    private String nome;
    private LocalDate dataCriacao;
    private List<Compromisso> compromissos;

    public Agenda(int idAgenda, String nome) {
        this.idAgenda = idAgenda;
        this.nome = nome;
        this.dataCriacao = LocalDate.now();
        this.compromissos = new ArrayList<>();
    }

    public void adicionarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        System.out.println("Compromisso adicionado à agenda.");
    }

    public void removerCompromisso(int idCompromisso) {
        boolean removido = compromissos.removeIf(
                compromisso -> compromisso.getIdCompromisso() == idCompromisso
        );

        if (removido) {
            System.out.println("Compromisso removido da agenda.");
        } else {
            System.out.println("Compromisso não encontrado na agenda.");
        }
    }

    public List<Compromisso> listarCompromissos() {
        return compromissos;
    }

    public List<Compromisso> listarPendentes() {
        List<Compromisso> pendentes = new ArrayList<>();

        for (Compromisso compromisso : compromissos) {
            if ("Pendente".equalsIgnoreCase(compromisso.getStatusCompromisso())) {
                pendentes.add(compromisso);
            }
        }

        return pendentes;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(List<Compromisso> compromissos) {
        this.compromissos = compromissos;
    }
}
