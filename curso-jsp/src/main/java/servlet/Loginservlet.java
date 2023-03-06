package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import dao.DAOlogin;
import modelos.ModeloLogin;


@WebServlet(urlPatterns = {"/principal/Loginservlet", "/Loginservlet"}) /*.Mapeamento.*/
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private DAOlogin dao = new DAOlogin();
   private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
   
    public Loginservlet() {
        super();
       
    }

	/*....Recebe dados pela url por parameto....*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("Logout")) {
			request.getSession().invalidate();/*invalida a sess�o*/
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
		doPost(request, response);
		}
	}
            /*Recebe dados enviados pelo formulario*/
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
	    String senha = request.getParameter("senha");
	    String url = request.getParameter("url");
	   
	   try {
		
	  if (login != null && !login.isEmpty() && senha !=null && !senha.isEmpty()) {
	    	 
	    	 ModeloLogin modeloLogin = new ModeloLogin();
	    	 modeloLogin.setLogin(login);
	 	     modeloLogin.setSenha(senha);
	 	     
	 	     if (DAOlogin.validarAutenticar(modeloLogin)) {
	 	    	
	 	    	 modeloLogin = daoUsuarioRepository.consultarUsuarioLogado(login);
	 	    	 
	 	    	request.getSession().setAttribute("usuario", modeloLogin.getLogin());
	 	    	request.getSession().setAttribute("perfil", modeloLogin.getPerfil());
	 	    	
	 	    	request.getSession().setAttribute("imagemUser", modeloLogin.getFotouser());
	 	    	
				if (url == null || url.equals("null") ) {
					
					url = "principal/principal.jsp";
				}
	 	    	RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
				
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Informe login e senha corretamente");
					redirecionar.forward(request, response);
				}
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe login e senha corretamente");
			redirecionar.forward(request, response);
		}
	    } catch (Exception e) {
			e.printStackTrace();/*P�gina de erro*/
			RequestDispatcher redirecionar = request.getRequestDispatcher("PaginaDeErro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		     
	}
}
