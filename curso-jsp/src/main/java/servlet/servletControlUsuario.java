package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelos.ModeloLogin;

@MultipartConfig
@WebServlet(urlPatterns = { "/servletControlUsuario" })
public class servletControlUsuario extends servletGeneriUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idUser = request.getParameter("id");

				daoUsuarioRepository.excluirUsuario(idUser);

				List<ModeloLogin> list = daoUsuarioRepository.listaDeConsultarUsuario(idUser,
						super.getUserLogado(request));

				request.setAttribute("list", list);
				request.setAttribute("msg", "Usuario exclu�do com sucesso!");
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}

			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idUser = request.getParameter("id");

				daoUsuarioRepository.excluirUsuario(idUser);

				response.getWriter().write("Usu�rio exclu�do com sucesso por ajax!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModeloLogin modeloLogin = daoUsuarioRepository.consultarUsuarioID(id, super.getUserLogado(request));

				List<ModeloLogin> list = daoUsuarioRepository.listaDeConsultarUsuario(super.getUserLogado(request));
				request.setAttribute("list", list);

				request.setAttribute("msg", "Editando usuario....!");
				request.setAttribute("modeloLogin", modeloLogin);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}

			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuario_ajaxPage")) {

				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");

				List<ModeloLogin> dadosJSonUser = daoUsuarioRepository.listaDeConsultarUsuarioOffset(nomeBusca,
						super.getUserLogado(request), Integer.parseInt(pagina));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJSonUser);

				response.addHeader("totalPagina", "" + daoUsuarioRepository
						.listaDeConsultarUsuarioTotalPagina(nomeBusca, super.getUserLogado(request)));
				response.getWriter().write(json);
			}

			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuario_ajax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<ModeloLogin> dadosJSonUser = daoUsuarioRepository.listaDeConsultarUsuario(nomeBusca,
						super.getUserLogado(request));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJSonUser);

				response.addHeader("totalPagina", "" + daoUsuarioRepository
						.listaDeConsultarUsuarioTotalPagina(nomeBusca, super.getUserLogado(request)));
				response.getWriter().write(json);
			}

			// Carregar usu�rio na tela
			
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListarUsuario")) {

				List<ModeloLogin> list = daoUsuarioRepository.listaDeConsultarUsuario(super.getUserLogado(request));

				request.setAttribute("msg", "Usuarios carregados....!");
				request.setAttribute("list", list);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {

				String idUser = request.getParameter("id");

				ModeloLogin modeloLogin = daoUsuarioRepository.consultarUsuarioID(idUser, super.getUserLogado(request));

				if (modeloLogin.getFotouser() != null && !modeloLogin.getFotouser().isEmpty()) {

					response.setHeader("Content-disposition",
							"Attachment;filename=arquivo." + modeloLogin.getExtensaofotouser());
					response.getOutputStream().write(Base64.decodeBase64(modeloLogin.getFotouser()));
				}
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				List<ModeloLogin> List = daoUsuarioRepository
						.listaDeConsultarUsuarioPaginada(this.getUserLogado(request), offset);
				request.setAttribute("list", List);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			else if (acao != null && !acao.isEmpty() 
					&& acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if(dataInicial == null || dataInicial.isEmpty() 
						&& dataFinal == null || dataFinal.isEmpty()) {
					
					request.setAttribute("listaUser", daoUsuarioRepository.
							listaDeConsultarUsuarioRelatorio(super.getUserLogado(request)));
					
				}else {
					
					request.setAttribute("listaUser", daoUsuarioRepository
							.listaDeConsultarUsuarioRelatorio(super.getUserLogado(request), dataInicial, dataFinal));
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/relatorioUser.jsp").forward(request, response);
			}
			
			
			else {

				List<ModeloLogin> list = daoUsuarioRepository.listaDeConsultarUsuario(acao,
						super.getUserLogado(request));
				request.setAttribute("list", list);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("PaginaDeErro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Usuario cadastrado com sucesso!";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String login = request.getParameter("login");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String provincia = request.getParameter("provincia");
			String numero = request.getParameter("numero");
			String dataNascimento = request.getParameter("dataNascimento");
			String rendaMensal = request.getParameter("rendamensal");

			ModeloLogin modeloLogin = new ModeloLogin();

			modeloLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modeloLogin.setNome(nome);
			modeloLogin.setEmail(email);
			modeloLogin.setLogin(login);
			modeloLogin.setSenha(senha);
			modeloLogin.setPerfil(perfil);
			modeloLogin.setSexo(sexo);
			modeloLogin.setCep(cep);
			modeloLogin.setRua(rua);
			modeloLogin.setBairro(bairro);
			modeloLogin.setLocalidade(localidade);
			modeloLogin.setProvincia(provincia);
			modeloLogin.setNumero(numero);
			
			modeloLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento))));
			
			modeloLogin.setRendamensal(
				Double.parseDouble(
					rendaMensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".")
				)
			);

			if (ServletFileUpload.isMultipartContent(request)) {

				Part part = request.getPart("fileFoto"); // pega foto da tela

				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); // Converte imagem para byte
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
							+ Base64.encodeBase64String(foto);
					modeloLogin.setFotouser(imagemBase64);
					modeloLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
				}
			}

			if (daoUsuarioRepository.validarLogin(modeloLogin.getLogin()) && modeloLogin.getId() == null) {
				msg = "Ja existe usu�rio com mesmo login, informe outro login";
			} else {

				if (modeloLogin.isNOVO()) {
					msg = "Usuario gravado com sucesso!";
				} else {

					msg = "Usuario atualizado com sucesso!";
				}

				modeloLogin = daoUsuarioRepository.gravarUsuario(modeloLogin, super.getUserLogado(request));
				List<ModeloLogin> list = daoUsuarioRepository.listaDeConsultarUsuario(super.getUserLogado(request));
				request.setAttribute("list", list);

			}

			request.setAttribute("msg", msg);
			request.setAttribute("modeloLogin", modeloLogin);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("PaginaDeErro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
