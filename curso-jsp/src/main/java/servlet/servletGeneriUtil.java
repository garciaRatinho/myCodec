package servlet;

import java.io.Serializable;
import java.sql.Connection;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import modelos.ModeloLogin;


public class servletGeneriUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();


	public Long getUserLogado(HttpServletRequest request) throws Exception {
		   
		    HttpSession session = request.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado).getId();
	}

	public ModeloLogin getUserLogadoObjet(HttpServletRequest request) throws Exception {
		   
	    HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado);
}


	
}
