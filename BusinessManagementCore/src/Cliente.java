import java.time.LocalDate;

public class Cliente {

    private int idCliente;
    private String nomeEmpresa;
    private String nomeResponsavel;
    private String cnpj;
    private String ramo;
    private boolean participouLicitacoes;
    private String origemContato;
    private String telefone;
    private String email;
    private String observacao;
    private LocalDate dataCadastro;

    public Cliente(int idCliente, String nomeEmpresa, String nomeResponsavel, String cnpj,
                   String ramo, boolean participouLicitacoes, String origemContato,
                   String telefone, String email, String observacao) {
        this.idCliente = idCliente;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeResponsavel = nomeResponsavel;
        this.cnpj = cnpj;
        this.ramo = ramo;
        this.participouLicitacoes = participouLicitacoes;
        this.origemContato = origemContato;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
        this.dataCadastro = LocalDate.now();
    }

    public void cadastrarCliente() {
        System.out.println("Cliente cadastrado: " + nomeEmpresa);
    }

    public void atualizarCliente(String nomeEmpresa, String nomeResponsavel, String telefone, String email) {
        this.nomeEmpresa = nomeEmpresa;
        this.nomeResponsavel = nomeResponsavel;
        this.telefone = telefone;
        this.email = email;
    }

    public void excluirCliente() {
        System.out.println("Cliente excluído: " + nomeEmpresa);
    }

    public void visualizarDetalhes() {
        System.out.println("Empresa: " + nomeEmpresa);
        System.out.println("Responsável: " + nomeResponsavel);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Ramo: " + ramo);
        System.out.println("Já participou de licitações: " + (participouLicitacoes ? "Sim" : "Não"));
        System.out.println("Origem do contato: " + origemContato);
        System.out.println("Telefone: " + telefone);
        System.out.println("E-mail: " + email);
        System.out.println("Observação: " + observacao);
        System.out.println("Data de cadastro: " + dataCadastro);
    }

    public boolean validarCnpj(String cnpj) {
        return cnpj != null && cnpj.length() == 14;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public boolean isParticipouLicitacoes() {
        return participouLicitacoes;
    }

    public void setParticipouLicitacoes(boolean participouLicitacoes) {
        this.participouLicitacoes = participouLicitacoes;
    }

    public String getOrigemContato() {
        return origemContato;
    }

    public void setOrigemContato(String origemContato) {
        this.origemContato = origemContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
