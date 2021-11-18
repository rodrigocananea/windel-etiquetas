package info.evoluti.etiquetas.models;

/**
 *
 * @author Administrador
 */
public class ModelEtqProd {

    private Integer id;
    private String codBarras;
    private String nome;    
    private Double valor;
    private String empresaNome;
    private String empresaTelefone;

    public ModelEtqProd(Integer id, String codBarras, String nome, Double valor, String empresaNome, String empresaTelefone) {
        this.id = id;
        this.codBarras = codBarras;
        this.nome = nome;
        this.valor = valor;
        this.empresaNome = empresaNome;
        this.empresaTelefone = empresaTelefone;
    } 
    
    public ModelEtqProd() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the codBarras
     */
    public String getCodBarras() {
        return codBarras;
    }

    /**
     * @param codBarras the codBarras to set
     */
    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getEmpresaTelefone() {
        return empresaTelefone;
    }

    public void setEmpresaTelefone(String empresaTelefone) {
        this.empresaTelefone = empresaTelefone;
    }

    
}
