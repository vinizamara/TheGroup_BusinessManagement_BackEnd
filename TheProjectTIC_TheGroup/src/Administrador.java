import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {

    private String cargo;
    private List<Cliente> clientes;
    private Agenda agenda;
    private List<RelatorioContatos> relatoriosContatos;

    public Administrador(int idUsuario, String nome, String email, String senha, String cargo) {
        super(idUsuario, nome, email, senha);
        this.cargo = cargo;
        this.clientes = new ArrayList<>();
        this.agenda = new Agenda(1, "Agenda Administrativa");
        this.relatoriosContatos = new ArrayList<>();
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void acessarPainel() {
        System.out.println("Acesso ao painel administrativo realizado.");
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado pelo administrador.");
    }

    public void editarCliente(Cliente cliente) {
        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getIdCliente() == cliente.getIdCliente()) {
                clienteAtual.atualizarCliente(
                        cliente.getNomeEmpresa(),
                        cliente.getNomeResponsavel(),
                        cliente.getTelefone(),
                        cliente.getEmail()
                );
                System.out.println("Cliente editado pelo administrador.");
                return;
            }
        }

        System.out.println("Cliente não encontrado.");
    }

    public void excluirCliente(int idCliente) {
        boolean removido = clientes.removeIf(cliente -> cliente.getIdCliente() == idCliente);

        if (removido) {
            System.out.println("Cliente excluído pelo administrador.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void cadastrarCompromisso(Compromisso compromisso) {
        agenda.adicionarCompromisso(compromisso);
        System.out.println("Compromisso cadastrado pelo administrador.");
    }

    public void editarCompromisso(Compromisso compromisso) {
        for (Compromisso compromissoAtual : agenda.listarCompromissos()) {
            if (compromissoAtual.getIdCompromisso() == compromisso.getIdCompromisso()) {
                compromissoAtual.editarCompromisso(
                        compromisso.getTitulo(),
                        compromisso.getPregao(),
                        compromisso.getDescricao(),
                        compromisso.getData(),
                        compromisso.getHorario()
                );
                System.out.println("Compromisso editado pelo administrador.");
                return;
            }
        }

        System.out.println("Compromisso não encontrado.");
    }

    public void excluirCompromisso(int idCompromisso) {
        agenda.removerCompromisso(idCompromisso);
        System.out.println("Compromisso excluído pelo administrador.");
    }

    public List<Compromisso> listarCompromissosPendentes() {
        return agenda.listarPendentes();
    }

    public RelatorioContatos gerarRelatorioContatos(LocalDate dataInicio, LocalDate dataFim) {
        RelatorioContatos relatorio = new RelatorioContatos(
                relatoriosContatos.size() + 1,
                dataInicio,
                dataFim,
                clientes
        );

        relatoriosContatos.add(relatorio);
        return relatorio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public List<RelatorioContatos> getRelatoriosContatos() {
        return relatoriosContatos;
    }
}
