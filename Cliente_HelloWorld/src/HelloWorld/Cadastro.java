package HelloWorld;

/**
 * Classe auxiliar que guarda as informações de Cadastro, seja
 * de uma vaga ou de um currículo.
 * É utilizada nas listas para guardar as informações mais facilmente.
 */
public class Cadastro {
    private String nome;
    private String contato;
    private String area;
    private String cargaHoraria;
    private String salario;
    private InterfaceCli referenciaCli;

    public Cadastro () {
        
    }
    
    /**
     * Método construtor da classe para Cadastro de curriculo.Pega os parâmetros de entrada
 e os coloca nos campos do objeto.
     * 
     * @param nome
     * @param contato
     * @param area
     * @param cargaHoraria
     * @param salario 
     * @param cliente
     */    
    public Cadastro (String nome, String contato, String area, String cargaHoraria, String salario, InterfaceCli cliente) {
        this.nome=nome;
        this.contato=contato;
        this.area=area;
        this.cargaHoraria=cargaHoraria;
        this.salario=salario;
        this.referenciaCli=cliente;
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
     * @return the contato
     */
    public String getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(String contato) {
        this.contato = contato;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the cargaHoraria
     */
    public String getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return the salario
     */
    public String getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(String salario) {
        this.salario = salario;
    }

    /**
     * @return the referenciaCli
     */
    public InterfaceCli getReferenciaCli() {
        return referenciaCli;
    }

    /**
     * @param referenciaCli the referenciaCli to set
     */
    public void setReferenciaCli(InterfaceCli referenciaCli) {
        this.referenciaCli = referenciaCli;
    }

}
