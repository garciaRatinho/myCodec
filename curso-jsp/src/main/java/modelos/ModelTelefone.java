package modelos;

import java.io.Serializable;

public class ModelTelefone {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private ModeloLogin usuario_pai_id;
	private ModeloLogin usuario_cad_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public ModeloLogin getUsuario_pai_id() {
		return usuario_pai_id;
	}
	public void setUsuario_pai_id(ModeloLogin usuario_pai_id) {
		this.usuario_pai_id = usuario_pai_id;
	}
	public ModeloLogin getUsuario_cad_id() {
		return usuario_cad_id;
	}
	public void setUsuario_cad_id(ModeloLogin usuario_cad_id) {
		this.usuario_cad_id = usuario_cad_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
