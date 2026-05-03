import java.time.LocalDate;
import java.time.LocalTime;

public class Compromisso {

    private int idCompromisso;
    private String titulo;
    private String pregao;
    private String descricao;
    private LocalDate data;
    private LocalTime horario;
    private String statusCompromisso;
    private int antecedenciaAlerta;
    private TipoCompromisso tipoCompromisso;

    public Compromisso(int idCompromisso, String titulo, String pregao, String descricao,
                       LocalDate data, LocalTime horario, String statusCompromisso,
                       int antecedenciaAlerta, TipoCompromisso tipoCompromisso) {
        this.idCompromisso = idCompromisso;
        this.titulo = titulo;
        this.pregao = pregao;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.statusCompromisso = statusCompromisso;
        this.antecedenciaAlerta = antecedenciaAlerta;
        this.tipoCompromisso = tipoCompromisso;
    }

    public void cadastrarCompromisso() {
        System.out.println("Compromisso cadastrado: " + titulo);
    }

    public void editarCompromisso(String titulo, String pregao, String descricao,
                                  LocalDate data, LocalTime horario) {
        this.titulo = titulo;
        this.pregao = pregao;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
    }

    public void excluirCompromisso() {
        System.out.println("Compromisso excluído: " + titulo);
    }

    public void visualizarCompromisso() {
        System.out.println("Título: " + titulo);
        System.out.println("Pregão: " + pregao);
        System.out.println("Descrição: " + descricao);
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horario);
        System.out.println("Status: " + statusCompromisso);
        System.out.println("Antecedência do alerta: " + antecedenciaAlerta + " dia(s)");

        if (tipoCompromisso != null) {
            System.out.println("Tipo do compromisso: " + tipoCompromisso.getNome());
        }
    }

    public void marcarComoConcluido() {
        this.statusCompromisso = "Concluído";
    }

    public boolean verificarProximidade() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataAlerta = data.minusDays(antecedenciaAlerta);

        return !hoje.isBefore(dataAlerta) && !hoje.isAfter(data);
    }

    public String gerarAlerta() {
        if (verificarProximidade()) {
            return "Alerta: o compromisso \"" + titulo + "\" está próximo.";
        }

        return "Não há alerta para este compromisso no momento.";
    }

    public int getIdCompromisso() {
        return idCompromisso;
    }

    public void setIdCompromisso(int idCompromisso) {
        this.idCompromisso = idCompromisso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPregao() {
        return pregao;
    }

    public void setPregao(String pregao) {
        this.pregao = pregao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getStatusCompromisso() {
        return statusCompromisso;
    }

    public void setStatusCompromisso(String statusCompromisso) {
        this.statusCompromisso = statusCompromisso;
    }

    public int getAntecedenciaAlerta() {
        return antecedenciaAlerta;
    }

    public void setAntecedenciaAlerta(int antecedenciaAlerta) {
        this.antecedenciaAlerta = antecedenciaAlerta;
    }

    public TipoCompromisso getTipoCompromisso() {
        return tipoCompromisso;
    }

    public void setTipoCompromisso(TipoCompromisso tipoCompromisso) {
        this.tipoCompromisso = tipoCompromisso;
    }
}
