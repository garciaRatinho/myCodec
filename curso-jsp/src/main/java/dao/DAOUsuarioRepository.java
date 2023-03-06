package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoBancoDados;
import modelos.ModelTelefone;
import modelos.ModeloLogin;

public class DAOUsuarioRepository {

	private Connection connection;	
	
	public DAOUsuarioRepository() {
		
		connection = ConexaoBancoDados.getConnection();
		
	}
	
	public ModeloLogin gravarUsuario(ModeloLogin objeto, Long userLogado) throws Exception {
		if(objeto.isNOVO()) {
			
			/*INSERIR USU�RIO*/
			
		String sql = "INSERT INTO model_login(login,senha,nome,email,usuario_id,perfil,sexo,cep,rua,bairro,localidade,provincia,numero, datanascimento, rendamensal)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement preparSql = connection.prepareStatement(sql);
		preparSql.setString(1, objeto.getLogin());
		preparSql.setString(2, objeto.getSenha());
		preparSql.setString(3, objeto.getNome());
		preparSql.setString(4, objeto.getEmail());
		preparSql.setLong(5, userLogado);
		preparSql.setString(6, objeto.getPerfil());
		preparSql.setString(7, objeto.getSexo());
		
		preparSql.setString(8, objeto.getCep());
		preparSql.setString(9, objeto.getRua());
		preparSql.setString(10, objeto.getBairro());
		preparSql.setString(11, objeto.getLocalidade());
		preparSql.setString(12, objeto.getProvincia());
		preparSql.setString(13, objeto.getNumero());
		preparSql.setDate(14, (Date) objeto.getDataNascimento());
		preparSql.setDouble(15, objeto.getRendamensal());
		
		preparSql.execute();
		connection.commit();
		
			if(objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				
				sql = "update model_login set fotouser =?, extensaofotouser=? where login =?";
				preparSql = connection.prepareStatement(sql);
				
				preparSql.setString(1, objeto.getFotouser());
				preparSql.setString(2, objeto.getExtensaofotouser());
				preparSql.setString(3, objeto.getLogin());
				
				preparSql.execute();
				connection.commit();
			}
		}else {
			
			/*ATUALIZAR USU�RIO*/
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?,perfil=?,sexo=?,cep=?,rua=?,bairro=?,localidade=?,provincia=?,numero=?,datanascimento=?,rendamensal=? WHERE id = "+objeto.getId() +";";
			
			PreparedStatement preparSql = connection.prepareStatement(sql);
			preparSql.setString(1, objeto.getLogin());
			preparSql.setString(2, objeto.getSenha());
			preparSql.setString(3, objeto.getNome());
			preparSql.setString(4, objeto.getEmail());
			preparSql.setString(5, objeto.getPerfil());
			preparSql.setString(6, objeto.getSexo());
			
			preparSql.setString(7, objeto.getCep());
			preparSql.setString(8, objeto.getRua());
			preparSql.setString(9, objeto.getBairro());
			preparSql.setString(10, objeto.getLocalidade());
			preparSql.setString(11, objeto.getProvincia());
			preparSql.setString(12, objeto.getNumero());
			preparSql.setDate(13, (Date) objeto.getDataNascimento());
			preparSql.setDouble(14, objeto.getRendamensal());
			
			
			preparSql.executeUpdate();
			connection.commit();
			
			if(objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				
				sql = "update model_login set fotouser =?, extensaofotouser=? where id =?";
				preparSql = connection.prepareStatement(sql);
				
				preparSql.setString(1, objeto.getFotouser());
				preparSql.setString(2, objeto.getExtensaofotouser());
				preparSql.setLong(3, objeto.getId());
				
				preparSql.execute();
				connection.commit();
				
			}
			
		}
		
		return this.consultarUsuario(objeto.getLogin(), userLogado); 
	}
	
public List<ModeloLogin> listaDeConsultarUsuarioRelatorio (Long userLogado) throws Exception{
		
		//Met�do para carregar usu�rio
		
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id= " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setTelefones(this.listFone(modeloLogin.getId()));
			
			retorno.add(modeloLogin);
			
		}
		return retorno;
	}
	
public List<ModeloLogin> listaDeConsultarUsuarioRelatorio (Long userLogado, String dataInicial, String dataFinal) throws Exception{
	
	//Metodo para carregar usuario
	
	List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
	
	String sql = "select * from model_login where useradmin is false and usuario_id= " + userLogado + " and datanascimento >= ? and datanascimento <= ?";
	
	PreparedStatement statement = connection.prepareStatement(sql);
	
	statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial))));
	statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dataFinal))));
	
	ResultSet resultado = statement.executeQuery();
	
	while(resultado.next()) { /*PERCORRER AS LINHAS DE RESULTADO DO SQL*/
		
		ModeloLogin modeloLogin = new ModeloLogin();
		
		modeloLogin.setEmail(resultado.getString("email"));
		modeloLogin.setId(resultado.getLong("id"));
		modeloLogin.setNome(resultado.getString("nome"));
		modeloLogin.setLogin(resultado.getString("login"));
		modeloLogin.setPerfil(resultado.getString("perfil"));
		modeloLogin.setSexo(resultado.getString("sexo"));
		modeloLogin.setTelefones(this.listFone(modeloLogin.getId()));
		
		retorno.add(modeloLogin);
		
	}
	return retorno;
}


	public List<ModeloLogin> listaDeConsultarUsuario (Long userLogado) throws Exception{
		
		//Met�do para carregar usu�rio
		
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id= " + userLogado + " limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modeloLogin);
			
		}
		return retorno;
	}
	
public List<ModeloLogin> listaDeConsultarUsuarioPaginada(Long userLogado, Integer offset) throws Exception{
		
		//Met�do para carregar usu�rio
		
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id= " + userLogado + " order by nome offset " + offset + " limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modeloLogin);
			
		}
		return retorno;
	}
	
	
	
public int totalPagina(Long userLogado) throws Exception {
		
		String sql = "select count(1) as total from model_login where usuario_id="+userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		
		Double cadastros = resultado.getDouble("total");
		Double porpagina = 5.0;
		Double pagina = cadastros / porpagina;
		Double resto = pagina % 2;
		
		if (resto > 0) {
			
			pagina ++;
		}
		return pagina.intValue();
	}

public int listaDeConsultarUsuarioTotalPagina (String nomeBusca, Long userLogado) throws Exception{
	
	String sql = "select count(1) as total from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=?";
	
	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setString(1, "%" + nomeBusca + "%");
	statement.setLong(2, userLogado);
	ResultSet resultado = statement.executeQuery();
	
	resultado.next();
	
	Double cadastros = resultado.getDouble("total");
	Double porpagina = 5.0;
	Double pagina = cadastros / porpagina;
	Double resto = pagina % 2;
	
	if (resto > 0) {
		
		pagina ++;
	}
	return pagina.intValue();
		
	}

public List<ModeloLogin> listaDeConsultarUsuarioOffset(String nomeBusca, Long userLogado, int offset) throws Exception{
	
	List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
	String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=? offset "+offset+" limit 5";
	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setString(1, "%" + nomeBusca + "%");
	statement.setLong(2, userLogado);
	ResultSet resultado = statement.executeQuery();
	
	while(resultado.next()) {
		
		ModeloLogin modeloLogin = new ModeloLogin();
		
		modeloLogin.setEmail(resultado.getString("email"));
		modeloLogin.setId(resultado.getLong("id"));
		modeloLogin.setNome(resultado.getString("nome"));
		modeloLogin.setLogin(resultado.getString("login"));
		modeloLogin.setPerfil(resultado.getString("perfil"));
		modeloLogin.setSexo(resultado.getString("sexo"));
		
		retorno.add(modeloLogin);
		
	}
	
	
	return retorno;
}

	
	public List<ModeloLogin> listaDeConsultarUsuario (String nomeBusca, Long userLogado) throws Exception{
		
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=? limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nomeBusca + "%");
		statement.setLong(2, userLogado);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modeloLogin);
			
		}
		
		
		return retorno;
	}
	
public ModeloLogin consultarUsuarioLogado(String login) throws Exception{
		
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			modeloLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotouser(resultado.getString("fotouser"));
			
			modeloLogin.setCep(resultado.getString("cep"));
			modeloLogin.setRua(resultado.getString("rua"));
			modeloLogin.setBairro(resultado.getString("bairro"));
			modeloLogin.setLocalidade(resultado.getString("localidade"));
			modeloLogin.setProvincia(resultado.getString("provincia"));
			modeloLogin.setNumero(resultado.getString("numero"));
			/*modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modeloLogin.setRendamensal(resultado.getDouble("rendamensal"));*/
			
		}
		
		return modeloLogin;
	}
	
	
public ModeloLogin consultarUsuario(String login) throws Exception{
		
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from model_login where upper(login) = upper('"+login+"') and useradmin is false;";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotouser(resultado.getString("fotouser"));
			
			modeloLogin.setCep(resultado.getString("cep"));
			modeloLogin.setRua(resultado.getString("rua"));
			modeloLogin.setBairro(resultado.getString("bairro"));
			modeloLogin.setLocalidade(resultado.getString("localidade"));
			modeloLogin.setProvincia(resultado.getString("provincia"));
			modeloLogin.setNumero(resultado.getString("numero"));
			/*modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modeloLogin.setRendamensal(resultado.getDouble("rendamensal"));*/
			
			
		}
		
		return modeloLogin;
	}
	
	public ModeloLogin consultarUsuario(String login, Long userLogado) throws Exception{
		
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from model_login where upper(login) = upper('"+login+"') and useradmin is false and usuario_id="+userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotouser(resultado.getString("fotouser"));
			
			modeloLogin.setCep(resultado.getString("cep"));
			modeloLogin.setRua(resultado.getString("rua"));
			modeloLogin.setBairro(resultado.getString("bairro"));
			modeloLogin.setLocalidade(resultado.getString("localidade"));
			modeloLogin.setProvincia(resultado.getString("provincia"));
			modeloLogin.setNumero(resultado.getString("numero"));
			/*modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modeloLogin.setRendamensal(resultado.getDouble("rendamensal"));*/
			
			
		}
		
		return modeloLogin;
	}
	
public ModeloLogin consultarUsuarioID(String id, Long userLogado) throws Exception{
		
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			modeloLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotouser(resultado.getString("fotouser"));
			modeloLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
			
			modeloLogin.setCep(resultado.getString("cep"));
			modeloLogin.setRua(resultado.getString("rua"));
			modeloLogin.setBairro(resultado.getString("bairro"));
			modeloLogin.setLocalidade(resultado.getString("localidade"));
			modeloLogin.setProvincia(resultado.getString("provincia"));
			modeloLogin.setNumero(resultado.getString("numero"));
			modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modeloLogin.setRendamensal(resultado.getDouble("rendamensal"));
			
			
		}
		
		return modeloLogin;
	}

	public ModeloLogin consultarUsuarioID(Long id) throws Exception{
	
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from model_login where id = ? and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			modeloLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotouser(resultado.getString("fotouser"));
			modeloLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
			
			modeloLogin.setCep(resultado.getString("cep"));
			modeloLogin.setRua(resultado.getString("rua"));
			modeloLogin.setBairro(resultado.getString("bairro"));
			modeloLogin.setLocalidade(resultado.getString("localidade"));
			modeloLogin.setProvincia(resultado.getString("provincia"));
			modeloLogin.setNumero(resultado.getString("numero"));
			/*modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modeloLogin.setRendamensal(resultado.getDouble("rendamensal"));*/
			
			
		}
		
		return modeloLogin;
}

	
	/* Metodo de Valida��o de login*/
	
	public boolean  validarLogin(String login) throws Exception{
		
		String sql = "select count(1)>0 as existe from model_login where upper(login) = upper('"+login+"')";
		
        PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
		
		
	}
	
	public void excluirUsuario(String idUser) throws Exception{
		
		String sql = "DELETE FROM model_login WHERE id =? and useradmin is false;";
		
		PreparedStatement preparSql = connection.prepareStatement(sql);
		
		preparSql.setLong(1, Long.parseLong(idUser));
		
		preparSql.executeUpdate();
		
		connection.commit();
	}
	
	
	public List<modelos.ModelTelefone> listFone(Long idUserPai) throws Exception {
		
		List<modelos.ModelTelefone> retorno = new ArrayList<modelos.ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idUserPai);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(rs.getLong("id"));
			modelTelefone.setNumero(rs.getString("numero"));
			modelTelefone.setUsuario_cad_id(this.consultarUsuarioID(rs.getLong("usuario_cad_id")));
			modelTelefone.setUsuario_pai_id(this.consultarUsuarioID(rs.getLong("usuario_pai_id")));
			
			retorno.add(modelTelefone);
			
		}
		
		return retorno;
	}

	
}
