package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloLogin implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private double rendamensal;
	private Date dataNascimento;
	private String nome;
	private Long id;
	private String email;
	private String login;
	private String senha;
	private boolean useradmin;
	private String perfil;
	private String sexo;
	private String fotouser;
	private String extensaofotouser;
	private String cep;
	private String rua;
	private String bairro;
	private String localidade;
	private String provincia;
	private String numero;
	
	
	private List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
	
	public void setTelefones(List<ModelTelefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<ModelTelefone> getTelefones() {
		return telefones;
	}
	
	
	public void setRendamensal(double rendamensal) {
		this.rendamensal = rendamensal;
	}
	
	public double getRendamensal() {
		return rendamensal;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFotouser() {
		return fotouser;
	}
	public void setFotouser(String fotouser) {
		this.fotouser = fotouser;
	}
	public String getExtensaofotouser() {
		return extensaofotouser;
	}
	public void setExtensaofotouser(String extensaofotouser) {
		this.extensaofotouser = extensaofotouser;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getSexo() {
		return sexo;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}
	
	
	
	public boolean getUseradmin() {
		return useradmin;
	}



	public boolean isNOVO() {
		if(this.id == null) {
			return true; /*Inserir novo usu�rio*/
		}else if(this.id != null && this.id>0){
			
			return false; /*Atualizar usu�rio*/
		}
		return id == null;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getMostraTelefoneRelatorio() {
		
		String fone = "Telefones:\n";
		
		for (ModelTelefone modelTelefone : telefones) {
			fone += modelTelefone.getNumero() + "\n\n";
			
		}
		return fone;
	}
	
}
