import java.time.LocalDate;
import java.util.List;

public class RelatorioContatos implements Exportavel {

    private int idRelatorio;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int quantidadeContatos;
    private LocalDate dataGeracao;
    private List<Cliente> clientes;

    public RelatorioContatos(int idRelatorio, LocalDate dataInicio, LocalDate dataFim, List<Cliente> clientes) {
        this.idRelatorio = idRelatorio;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.clientes = clientes;
        this.dataGeracao = LocalDate.now();
        this.quantidadeContatos = calcularQuantidadeContatos();
    }

    public void gerarRelatorio(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadeContatos = calcularQuantidadeContatos();
        this.dataGeracao = LocalDate.now();

        System.out.println("Relatório de contatos gerado com sucesso.");
    }

    public int calcularQuantidadeContatos() {
        int quantidade = 0;

        for (Cliente cliente : clientes) {
            LocalDate dataCadastro = cliente.getDataCadastro();

            boolean dentroDoPeriodo =
                    (dataCadastro.isEqual(dataInicio) || dataCadastro.isAfter(dataInicio))
                            && (dataCadastro.isEqual(dataFim) || dataCadastro.isBefore(dataFim));

            if (dentroDoPeriodo) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public void exibirRelatorio() {
        System.out.println("Relatório de Contatos");
        System.out.println("Data inicial: " + dataInicio);
        System.out.println("Data final: " + dataFim);
        System.out.println("Quantidade de contatos: " + quantidadeContatos);
        System.out.println("Data de geração: " + dataGeracao);
    }

    @Override
    public String exportar(String formato) {
        return "Relatório de contatos exportado no formato: " + formato;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getQuantidadeContatos() {
        return quantidadeContatos;
    }

    public void setQuantidadeContatos(int quantidadeContatos) {
        this.quantidadeContatos = quantidadeContatos;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
