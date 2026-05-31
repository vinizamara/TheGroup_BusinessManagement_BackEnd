import java.time.LocalDate;

public class RegistroCompromissoCliente {

    private int idRegistro;
    private LocalDate dataRegistro;
    private String observacao;
    private String situacaoClienteNoCompromisso;

    private Cliente cliente;
    private Compromisso compromisso;

    public RegistroCompromissoCliente(int idRegistro, Cliente cliente, Compromisso compromisso,
                                      String observacao, String situacaoClienteNoCompromisso) {
        this.idRegistro = idRegistro;
        this.cliente = cliente;
        this.compromisso = compromisso;
        this.observacao = observacao;
        this.situacaoClienteNoCompromisso = situacaoClienteNoCompromisso;
        this.dataRegistro = LocalDate.now();
    }

    public void registrarVinculo() {
        System.out.println("Vínculo registrado entre cliente e compromisso.");
    }

    public void atualizarSituacao(String situacao) {
        this.situacaoClienteNoCompromisso = situacao;
        System.out.println("Situação atualizada para: " + situacao);
    }

    public void visualizarRegistro() {
        System.out.println("Registro de Compromisso do Cliente");
        System.out.println("ID do registro: " + idRegistro);
        System.out.println("Data do registro: " + dataRegistro);

        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNomeEmpresa());
        }

        if (compromisso != null) {
            System.out.println("Compromisso: " + compromisso.getTitulo());
        }

        System.out.println("Observação: " + observacao);
        System.out.println("Situação do cliente no compromisso: " + situacaoClienteNoCompromisso);
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacaoClienteNoCompromisso() {
        return situacaoClienteNoCompromisso;
    }

    public void setSituacaoClienteNoCompromisso(String situacaoClienteNoCompromisso) {
        this.situacaoClienteNoCompromisso = situacaoClienteNoCompromisso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }
}
