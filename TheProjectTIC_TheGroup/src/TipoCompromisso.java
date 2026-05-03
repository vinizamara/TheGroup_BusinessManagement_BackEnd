public class TipoCompromisso {

    private int idTipoCompromisso;
    private String nome;
    private String descricao;

    public TipoCompromisso(int idTipoCompromisso, String nome, String descricao) {
        this.idTipoCompromisso = idTipoCompromisso;
        this.nome = nome;
        this.descricao = descricao;
    }

    public void cadastrarTipo() {
        System.out.println("Tipo de compromisso cadastrado: " + nome);
    }

    public void editarTipo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void excluirTipo() {
        System.out.println("Tipo de compromisso excluído: " + nome);
    }

    public void visualizarTipo() {
        System.out.println("Tipo de compromisso: " + nome);
        System.out.println("Descrição: " + descricao);
    }

    public int getIdTipoCompromisso() {
        return idTipoCompromisso;
    }

    public void setIdTipoCompromisso(int idTipoCompromisso) {
        this.idTipoCompromisso = idTipoCompromisso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
