import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TesteSistema {

    public static void main(String[] args) {

        System.out.println("===== TESTE DO SISTEMA AUCTUS LICITACOES =====");

        // 1. Criando o administrador
        Administrador administrador = new Administrador(
                1,
                "Gestor Auctus",
                "admin@auctus.com",
                "123456",
                "Administrador"
        );

        System.out.println("\n===== TESTE DE AUTENTICACAO =====");

        boolean loginValido = administrador.autenticar("admin@auctus.com", "123456");
        boolean loginInvalido = administrador.autenticar("admin@auctus.com", "senhaerrada");

        System.out.println("Login valido: " + loginValido);
        System.out.println("Login invalido: " + loginInvalido);

        administrador.acessarPainel();


        // 2. Criando e cadastrando clientes
        System.out.println("\n===== TESTE DE CADASTRO DE CLIENTES =====");

        Cliente cliente1 = new Cliente(
                1,
                "Empresa Alfa LTDA",
                "Joao Silva",
                "12345678000199",
                "Comercio",
                true,
                "Site institucional",
                "(11) 99999-9999",
                "contato@empresa-alfa.com",
                "Cliente interessado em participar de pregoes eletronicos."
        );

        Cliente cliente2 = new Cliente(
                2,
                "Beta Servicos ME",
                "Maria Oliveira",
                "98765432000188",
                "Servicos",
                false,
                "WhatsApp",
                "(11) 98888-8888",
                "contato@beta-servicos.com",
                "Cliente ainda nao participou de licitacoes."
        );

        administrador.cadastrarCliente(cliente1);
        administrador.cadastrarCliente(cliente2);

        cliente1.cadastrarCliente();
        cliente2.cadastrarCliente();

        System.out.println("\nDetalhes do cliente 1:");
        cliente1.visualizarDetalhes();

        System.out.println("\nValidacao de CNPJ do cliente 1:");
        System.out.println("CNPJ valido: " + cliente1.validarCnpj(cliente1.getCnpj()));


        // 3. Editando cliente
        System.out.println("\n===== TESTE DE EDICAO DE CLIENTE =====");

        cliente1.atualizarCliente(
                "Empresa Alfa Atualizada LTDA",
                "Joao Silva Santos",
                "(11) 97777-7777",
                "novoemail@empresa-alfa.com"
        );

        cliente1.visualizarDetalhes();


        // 4. Criando tipos de compromisso
        System.out.println("\n===== TESTE DE TIPO DE COMPROMISSO =====");

        TipoCompromisso tipoPregao = new TipoCompromisso(
                1,
                "Pregao Eletronico",
                "Compromisso relacionado a participacao em pregao eletronico."
        );

        TipoCompromisso tipoReuniao = new TipoCompromisso(
                2,
                "Reuniao",
                "Compromisso relacionado a reuniao com cliente."
        );

        tipoPregao.cadastrarTipo();
        tipoPregao.visualizarTipo();

        tipoReuniao.cadastrarTipo();
        tipoReuniao.visualizarTipo();


        // 5. Criando compromissos
        System.out.println("\n===== TESTE DE CADASTRO DE COMPROMISSOS =====");

        Compromisso compromisso1 = new Compromisso(
                1,
                "Pregao da Empresa Alfa",
                "Pregao 001/2026",
                "Acompanhar abertura do pregao eletronico.",
                LocalDate.now().plusDays(2),
                LocalTime.of(10, 30),
                "Pendente",
                3,
                tipoPregao
        );

        Compromisso compromisso2 = new Compromisso(
                2,
                "Reuniao com Beta Servicos",
                "Nao se aplica",
                "Reuniao para explicar o funcionamento de licitacoes.",
                LocalDate.now().plusDays(7),
                LocalTime.of(14, 0),
                "Pendente",
                2,
                tipoReuniao
        );

        administrador.cadastrarCompromisso(compromisso1);
        administrador.cadastrarCompromisso(compromisso2);

        compromisso1.cadastrarCompromisso();
        compromisso2.cadastrarCompromisso();

        System.out.println("\nDetalhes do compromisso 1:");
        compromisso1.visualizarCompromisso();

        System.out.println("\nDetalhes do compromisso 2:");
        compromisso2.visualizarCompromisso();


        // 6. Testando alerta de compromisso
        System.out.println("\n===== TESTE DE ALERTA DE AGENDA =====");

        System.out.println(compromisso1.gerarAlerta());
        System.out.println(compromisso2.gerarAlerta());


        // 7. Listando compromissos pendentes
        System.out.println("\n===== TESTE DE LISTAGEM DE COMPROMISSOS PENDENTES =====");

        List<Compromisso> pendentes = administrador.listarCompromissosPendentes();

        for (Compromisso compromisso : pendentes) {
            System.out.println("- " + compromisso.getTitulo() + " | Data: " + compromisso.getData());
        }


        // 8. Marcando compromisso como concluido
        System.out.println("\n===== TESTE DE CONCLUSAO DE COMPROMISSO =====");

        compromisso1.marcarComoConcluido();
        compromisso1.visualizarCompromisso();

        System.out.println("\nCompromissos pendentes apos conclusao do compromisso 1:");

        List<Compromisso> pendentesAtualizados = administrador.listarCompromissosPendentes();

        for (Compromisso compromisso : pendentesAtualizados) {
            System.out.println("- " + compromisso.getTitulo() + " | Status: " + compromisso.getStatusCompromisso());
        }


        // 9. Testando classe de associacao RegistroCompromissoCliente
        System.out.println("\n===== TESTE DE REGISTRO ENTRE CLIENTE E COMPROMISSO =====");

        RegistroCompromissoCliente registro = new RegistroCompromissoCliente(
                1,
                cliente1,
                compromisso1,
                "Cliente deve enviar documentos antes da data do pregao.",
                "Documentacao pendente"
        );

        registro.registrarVinculo();
        registro.visualizarRegistro();

        System.out.println("\nAtualizando situacao do registro:");
        registro.atualizarSituacao("Documentacao recebida");
        registro.visualizarRegistro();


        // 10. Gerando relatorio de contatos
        System.out.println("\n===== TESTE DE RELATORIO DE CONTATOS =====");

        RelatorioContatos relatorio = administrador.gerarRelatorioContatos(
                LocalDate.now().minusDays(30),
                LocalDate.now().plusDays(1)
        );

        relatorio.exibirRelatorio();


        // 11. Testando exportacao do relatorio
        System.out.println("\n===== TESTE DE EXPORTACAO =====");

        String resultadoExportacao = relatorio.exportar("Excel");
        System.out.println(resultadoExportacao);


        // 12. Excluindo cliente e compromisso
        System.out.println("\n===== TESTE DE EXCLUSAO =====");

        administrador.excluirCliente(2);
        administrador.excluirCompromisso(2);

        System.out.println("\nClientes restantes:");
        for (Cliente cliente : administrador.getClientes()) {
            System.out.println("- " + cliente.getNomeEmpresa());
        }

        System.out.println("\nCompromissos restantes:");
        for (Compromisso compromisso : administrador.getAgenda().listarCompromissos()) {
            System.out.println("- " + compromisso.getTitulo());
        }

        System.out.println("\n===== FIM DOS TESTES =====");
    }
}
