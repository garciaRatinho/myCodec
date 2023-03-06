package servlet;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.ModelTelefone;
import modelos.ModeloLogin;


@WebServlet("/ServletTelefone")
public class ServletTelefone extends servletGeneriUtil {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
       
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
   
	public ServletTelefone() {
        
		
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			
			
			String acao = request.getParameter("acao");
			
			if(acao !=null && !acao.isEmpty() && acao.equals("excluir")) {
				
				String idfone = request.getParameter("id");
				
				daoTelefoneRepository.deleteFone(Long.parseLong(idfone));
				
				String userpai= request.getParameter("userpai");
				
				ModeloLogin modeloLogin = daoUsuarioRepository.consultarUsuarioID(Long.parseLong(userpai));
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modeloLogin.getId());
				
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("msg", "Telefone Excluido com sucesso!");
				request.setAttribute("modeloLogin", modeloLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				 return;
				 
			 }
			
			String iduser = request.getParameter("iduser");
				
				if (iduser != null && !iduser.isEmpty()) {
						
						ModeloLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(Long.parseLong(iduser));
					
						List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
						request.setAttribute("modelTelefones", modelTelefones);
						
						request.setAttribute("modelLogin", modelLogin);
						request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
				
				}else {
					 List<ModeloLogin> modeloLogins = daoUsuarioRepository.listaDeConsultarUsuario(super.getUserLogado(request));
				     request.setAttribute("modeloLogins", modeloLogins);
				     request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
					 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			if(daoTelefoneRepository.existeFone(numero, Long.valueOf(usuario_pai_id))) {
				
		
				ModelTelefone modelTelefone = new ModelTelefone();
				
				modelTelefone.setNumero(numero);
				modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultarUsuarioID(Long.parseLong(usuario_pai_id)));
				modelTelefone.setUsuario_cad_id(super.getUserLogadoObjet(request));
				
				daoTelefoneRepository.gravaTelefone(modelTelefone);
				
				
			
			}else {
				
				request.setAttribute("msg", "Telefone ja existe");
			}
			
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(Long.parseLong(usuario_pai_id));
				
				ModeloLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(Long.parseLong(usuario_pai_id));
				
				request.setAttribute("modeloLogin", modelLogin);
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("msg", "Salvo com sucesso");
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		 }
		
	}

}
