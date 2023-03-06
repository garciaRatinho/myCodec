<%@page import="modelos.ModeloLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <jsp:include page="theme-loader.jsp"></jsp:include>

  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
           
          <jsp:include page="navbar.jsp"></jsp:include> 
          
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <jsp:include page="Nav-menu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                  
                  <jsp:include page="page-header.jsp"></jsp:include>
                      
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                           
                                           
                                          <div class="row">
                                            <div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                <div class="card">
                                                
	         <div class="card-block">
	         <h4 class="sub-title">Cadastro do usuario</h4>
                                          
		<form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath()%>/servletControlUsuario" method="post" id = "formUser">
			
			  <input type="hidden" name ="acao" id="acao" value="">
			
			  <div class="form-group form-default form-static-label">
			      <input type="text" name="id" id = "id" class="form-control" readonly="readonly" value="${modeloLogin.id}">
			      <span class="form-bar"></span>
			      <label class="float-label">ID:</label>
			  </div>
			  
			  <div class="form-group form-default input-group mb-4">
			     <div class="input-group-prepend">
			       <c:if test="${modeloLogin.fotouser != '' && modeloLogin.fotouser != null}">
				       <a href="<%= request.getContextPath()%>/servletControlUsuario?acao=downloadFoto&id=${modeloLogin.id}">
				       	 <img alt="imagem user" id="fotoembase64" src="${modeloLogin.fotouser}" width="70px">
				       </a>
			       </c:if>
			       <c:if test="${modeloLogin.fotouser == '' || modeloLogin.fotouser == null}">
			       		<img alt="imagem user" id="fotoembase64" name="fotoembase64" src="assets/images/faq_man.png" width="70px">
			       </c:if>
			     </div>
			      <input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64','fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
			  
			  </div>
			  
			  <div class="form-group form-default form-static-label">
			     <input type="text" name="nome" id="nome" class="form-control"  placeholder="Nome" required="required" value="${modeloLogin.nome}">
			     <span class="form-bar"></span>
			     <label class="float-label">Nome:</label>
			 </div>
			 
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="dataNascimento" id="dataNascimento" class="form-control"  placeholder="" required="required" value="${modeloLogin.dataNascimento}">
			     <span class="form-bar"></span>
			     <label class="float-label">Data de Nascimento:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="rendamensal" id="rendamensal" class="form-control" required="required" value="${modeloLogin.rendamensal}">
			     <span class="form-bar"></span>
			     <label class="float-label">Renda Mensal:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="email" name="email" id="email" class ="form-control" placeholder="E-mail" required="required" autocomplete="off" value="${modeloLogin.email}">
			     <span class="form-bar"></span>
			     <label class="float-label">Email:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
				 <select class="form-control" aria-label="Default select example" name="perfil">
					 <option disabled="disabled" >[Selecione o perfil]</option>
					 <option value="Administrador" <%
					 ModeloLogin modeloLogin = (ModeloLogin)request.getAttribute("modeloLogin");
					 
					 if(modeloLogin !=null && modeloLogin.getPerfil().equals("Administrador")){
						 out.print(" ");
						 out.print("selected=\"selected\"");
						 out.print(" ");
					 } %>>Administrador(a)</option>
					  
					 <option value="Secretaria" <%
					modeloLogin = (ModeloLogin)request.getAttribute("modeloLogin");
					 if(modeloLogin !=null && modeloLogin.getPerfil().equals("Secretaria")){
						 out.print(" ");
						 out.print("selected=\"selected\"");
						 out.print(" ");
					 } %>>Secretário(a)</option>
					 <option value="auxiliar" <%
					modeloLogin = (ModeloLogin)request.getAttribute("modeloLogin");
					 if(modeloLogin !=null && modeloLogin.getPerfil().equals("auxiliar")){
						 out.print(" ");
						 out.print("selected=\"selected\"");
						 out.print(" ");
					 } %>>Auxiliar</option>
				 </select>
			  <span class="form-bar"></span>
			     <label class="float-label">Perfil:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input onblur="pesquisarCep();" type="text" name="cep" id="cep" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.cep}">
			     <span class="form-bar"></span>
			     <label class="float-label">Cep:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="rua" id="rua" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.rua}">
			     <span class="form-bar"></span>
			     <label class="float-label">Rua:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="bairro" id="bairro" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.bairro}">
			     <span class="form-bar"></span>
			     <label class="float-label">Bairro:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="localidade" id="localidade" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.localidade}">
			     <span class="form-bar"></span>
			     <label class="float-label">Localidade:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="provincia" id="provincia" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.provincia}">
			     <span class="form-bar"></span>
			     <label class="float-label">Provincia:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="numero" id="numero" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.numero}">
			     <span class="form-bar"></span>
			     <label class="float-label">Numero:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="login" id="login" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.login}">
			     <span class="form-bar"></span>
			     <label class="float-label">Login:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="password" name="senha" id="senha" placeholder="" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.senha}">
			     <span class="form-bar"></span>
			     <label class="float-label">Senha:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			   <input type="radio" name="sexo" checked="checked" value="MASCULINO" <%
			   
					modeloLogin = (ModeloLogin)request.getAttribute("modeloLogin");
					 if(modeloLogin !=null && modeloLogin.getSexo().equals("MASCULINO")){
						 out.print(" ");
						 out.print("checked=\"checked\"");
						 out.print(" ");
					 }%> >Masculino</>
			   <input type="radio"name="sexo" value="FEMININO" <%
			   
					modeloLogin = (ModeloLogin)request.getAttribute("modeloLogin");
					 if(modeloLogin !=null && modeloLogin.getSexo().equals("FEMININO")){
						 out.print(" ");
						 out.print("checked=\"checked\"");
						 out.print(" ");
					 }%> >Femenino</>
			 </div>
		                               
			<button type ="button" class="btn btn-primary waves-effect waves-light"  onclick="limparForm();">Novo</button>
			<button class="btn btn-success waves-effect waves-light">Salvar</button>
			<button type ="button" class="btn btn-info waves-effect waves-light"  onclick="criarExcluir()">Excluir</button>
			<c:if test="${modeloLogin.id > 0 }">
			  <a href ="<%=request.getContextPath() %>/ServletTelefone?iduser=${modeloLogin.id}" class="btn btn-primary waves-effect waves-light">Telefone</a>
			</c:if>
			<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#ModalUsuario">Pesquisar</button>
          
		    </form>  
      	 </div>
      		 
      		<span id ="msg">${msg}</span>
              
              <div style="height: 300px; overflow: scroll;">
	              <table class="table" id="tabelaview">
		              <thead>
			              <tr>
			                <th scope="col">ID</th>
			                <th scope="col">Nome</th>
			                <th scope="col">Editar</th>
			              </tr>
		              </thead>
		              <tbody>
			              <c:forEach items='${list}' var='ml'>
				              <tr>
					              <td><c:out value='${ml.id}'></c:out></td>
						          <td><c:out value='${ml.nome}'></c:out></td>
						          <td><a class="btn btn-success" href="<%= request.getContextPath()%>/servletControlUsuario?acao=buscarEditar&id=${ml.id}" >Editar</a></td>
					              
				              </tr>
			              </c:forEach>
		              </tbody>
	              </table>
              </div>
              
              <nav arial-label="page navegation example">
                 <ul class="pagination">
                     <%
	                   	int totalPagina = (int) request.getAttribute("totalPagina");
		               for(int p = 0; p<totalPagina; p++){
		                	  String url = request.getContextPath() + "/servletControlUsuario?acao=paginar&pagina=" + (p * 5);
		                	  out.print(" <li class =\"page-item\"><a class=\"page-link\" href=\""+ url +"\">"+(p + 1)+"</a></li>");
		                  }
	                 %> 
                  
                 </ul>
              </nav>
                      
	                   </div>
	                  </div>
	                 </div>
	                </div>
	               </div>
	              </div>
	             </div>
                          <!-- corpo da página -->
                          
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
   
       
   
    <jsp:include page="javascriptArquivos.jsp"></jsp:include>
   
   
   <!-- Modal -->
<div class="modal fade" id="ModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisar usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
	 <div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="pesquisa pelo nome"  aria-label="nome" id ="nomeBusca" aria-describedby="basic-addon2">
		  <div class="input-group-append">
		    <button class="btn btn-success" type="button"  onclick="buscarUsuario()">Buscar</button>
		  </div>
	 </div>
	 
	<div style="height: 300px; overflow: scroll;">
	<table class="table" id = "tabelaResultados">
	
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nome</th>
      <th scope="col">Ver</th>
     
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table>

</div>

<nav arial-label="page navegation example">
<ul class="pagination" id="ulPaginacaoUserAjax">

</ul>
</nav>
<span id = "totalResultados"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
     
      </div>
    </div>
  </div>
</div>

<script type ="text/javascript">

	$("#rendamensal").maskMoney({showSymbol:true, symbol:"Kz ", decimal:",", thousands:"."});
	
		const formatter = new Intl.NumberFormat('pt-PT', {
		 currency : 'PTL',
		 minimumFractionDigits : 2
	});
	
	$("#rendamensal").val(formatter.format($("#rendamensal").val()));
	$("#rendamensal").focus();
	
	var dataNascimento = $("#dataNascimento").val();
	if (dataNascimento){
		var dateFormat = new Date(dataNascimento);
		$("#dataNascimento").val(dateFormat.toLocalDateString('pt-PT', {timeZone: 'UTC'}));
	}
	
	
	$("#nome").focus();
	
	$(function(){
	    	
    $("#dataNascimento").datepicker({
    	dateFormat: 'dd/mm/yy',
    	dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
    	dayNamesMin: ['D','S','T','Q','Q','S','S'],
    	dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
        nexText: 'Próximo',
        prevText: 'Anterior'
        
    	
    });	
    	
    });
    

    $("#numero").keypress(function(event){
    	return /\d/.test(String.fromCharCode(event.keyCode));
    });
    
    $("#cep").keypress(function(event){
    	return /\d/.test(String.fromCharCode(event.keyCode));
    });
    

	function pesquisarCep() {
	    var cep = $("#cep").val();
	    var url = "https://viacep.com.br/ws/" + cep + "/json/?callback=?";
	    $.getJSON(url, function(dados) {
	        if (!("erro" in dados)) {
		    $("#cep").val(dados.cep);
		    $("#rua").val(dados.rua);
	  	    $("#bairro").val(dados.bairro);
		    $("#localidade").val(dados.localidade);
		    $("#provincia").val(dados.provincia);
	        }
	    });
	}
        
    function visualizarImg(fotoembase64,fileFoto){
        
        var preview = document.getElementById(fotoembase64); /*Campo de img do html*/
        var fileUser = document.getElementById(fileFoto).files[0];
        var reader = new FileReader();

        reader.onloadend = function(){
              preview.src= reader.result;/* carrega foto na tela */
            };
            if(fileUser){
                reader.readAsDataURL(fileUser);/* preview da imagem */
                }else{
                     preview.src = '';
                    }
        }

    function verEditar(id){
	 var urlAction = document.getElementById('formUser').action;
	  window.location.href = urlAction + '?acao=buscarEditar&id='+id;
    }

    function buscarUserAjaxPagi(url){
    	var nomeBusca = document.getElementById('nomeBusca').value;
    	var urlAction = document.getElementById('formUser').action;

		$.ajax({
			method: "get",
			url: urlAction, 
			data: url,
			success: function(response, textstatus, xhr) {
				var json = JSON.parse(response);
				$('#tabelaResultados > tbody > tr').remove();
				$("#ulPaginacaoUserAjax >li").remove();
				
				for(var p = 0; p < json.length; p++){
					
					$('#tabelaResultados > tbody').append('<tr><td>'+json[p].id+'</td><td>'+json[p].nome+'</td><td> <button onclick="verEditar('+json[p].id+')" type = "button" class="btn btn-info">Editar</button></td></tr>');
				}

				document.getElementById('totalResultados').textContent ='Resultados: ' + json.length;
				var totalPagina = xhr.getResponseHeader("totalPagina");

					for(var p = 0; p<totalPagina; p++){
    					var url = +'nomeBusca='+nomeBusca+ '&acao=buscarUsuario_ajaxPage&pagina='+ (p * 5);
    					$("#ulPaginacaoUserAjax").append('<li class =\"page-item\"><a class=\"page-link\" href="#" onclick="buscarUserAjaxPagi(\''+ url +'\')">'+(p + 1)+'</a></li>')
    					
    					}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
		});
	}

	function limparForm(){
		console.log("Teste");
		var elementos = document.getElementById("formUser").elements;
		
	    for (p = 0; p<elementos.length; p++){
	
	    	 elementos[p].value = '';
	   	}
	}
	
	function buscarUsuario() {
		    var nomeBusca = document.getElementById('nomeBusca').value;
		    
			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
				
				var urlAction = document.getElementById('formUser').action;
				 
    			$.ajax({
    				method: "get",
    				url: urlAction,
    				data: "nomeBusca=" + nomeBusca + "&acao=buscarUsuario_ajax",
    				success: function(response, textstatus, xhr) {
    					var json = JSON.parse(response);
    					$('#tabelaResultados > tbody > tr').remove();
    					$("#ulPaginacaoUserAjax >li").remove();
    					
    					for(var p = 0; p < json.length; p++){
        					
    						$('#tabelaResultados > tbody').append('<tr><td>'+json[p].id+'</td><td>'+json[p].nome+'</td><td> <button onclick="verEditar('+json[p].id+')" type = "button" class="btn btn-info">Editar</button></td></tr>');
    					}

    					document.getElementById('totalResultados').textContent ='Resultados: ' + json.length;
    					var totalPagina = xhr.getResponseHeader("totalPagina");

	    					for(var p = 0; p<totalPagina; p++){
		    					var url =  'nomeBusca=' +nomeBusca+ '&acao=buscarUsuario_ajaxPage&pagina='+ (p * 5);
	    						$("#ulPaginacaoUserAjax").append('<li class =\"page-item\"><a class=\"page-link\" href="#" onclick="buscarUserAjaxPagi(\''+ url +'\')">'+(p + 1)+'</a></li>')
	        					
	        					}
    				}
    			}).fail(function(xhr, status, errorThrown) {
    				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
    			});
			}
	}
	
	function criarDeletarajax(){

	     if(confirm('Deseja realmente excluir dados_ajax?')){
	
	         var urlAction = document.getElementById("formUser").action;
	         var idUser = document.getElementById('id').value;
	
	         $.ajax({
	
	             method: "get",
	             url: urlAction,
	             data: "id=" + idUser + '&acao=deletarajax',
	             
	             success: function(response){
	
		             limparForm();
		             alert(response);
	              }
	             
	             }).fail(function(xhr,status, errorthrown){
	
	                 alert('Erro ao deletar usuário por id: ' + xhr.responseText);
	
	            });
	         }
	}
	
	function criarExcluir(){
		 
	   if(confirm("Deseja realmente excluir os dados?")){   
			document.getElementById("formUser").method = 'get',
			document.getElementById("acao").value = 'deletar',
			document.getElementById("formUser").submit();

		}
	        
	}

	 
	
</script>
    
</body>

</html>
