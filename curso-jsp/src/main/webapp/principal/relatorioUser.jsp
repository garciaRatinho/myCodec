<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

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
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">RELATORIO USUARIO</h4>
														
														<form class="form-material" action="<%= request.getContextPath()%>/servletControlUsuario?acao=imprimirRelatorioUser" method="get" id = "formUser">
                                                            
                                                            <input type="hidden" name="acao" value="imprimirRelatorioUser">
															<div class="form-row align-items-center">
																<div class="col-auto">
																	<label class="sr-only" for="dataInicial">Data Inicial</label>
																	<input value="${dataInicial }" type="text" class="form-control mb-2" id="dataInicial" name="dataInicial">
																</div>
																
																<div class="col-auto">
																	<label class="sr-only" for="dataFinal">Data Final</label>
																	<div class="input-group mb-2">
																		<input value="${dataFinal }" type="text" class="form-control" id="dataFinal"name = "dataFinal">	
																	</div>
																</div>
																
												
																<div class="col-auto">
																	<button type="submit" class="btn btn-primary mb-2">Imprimir Relatorio</button>
																</div>
															</div>

														</form>
														
															 <div style="height: 300px; overflow: scroll;">
															 
												               <table class="table" id="tabelaview">
													              
													              <thead>
														              <tr>
														                <th scope="col">ID</th>
														                <th scope="col">Nome</th>
														            </tr>
													              </thead>
														             
														              <tbody>
															              <c:forEach items="${listaUser}" var='ml'>
																              
																                 <tr>
																	              <td><c:out value='${ml.id}'></c:out></td>
																		          <td><c:out value='${ml.nome}'></c:out></td>
																		         </tr>
																		         
																		         <c:forEach items="${ml.telefones}" var="fone">
																		         
																		         <tr>
																			        <td/>
																			         <td style="font-size: 10px"><c:out value="${fone.numero}"></c:out></td>
																		         </tr>
																		         </c:forEach>
															              </c:forEach>
															              
															              
														              </tbody>
												               </table>
											                 </div>
														
														
													</div>
												</div>
											</div>
										</div>

									</div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
    <jsp:include page="javascriptArquivos.jsp"></jsp:include>
    
    <script type="text/javascript">
    
    $(function(){
    	
        $("#dataInicial").datepicker({
        	dateFormat: 'dd/mm/yy',
        	dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
        	dayNamesMin: ['D','S','T','Q','Q','S','S'],
        	dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b'],
            monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nexText: 'Pr�ximo',
            prevText: 'Anterior'
            
        	
        });	
        	
        });
    
    $(function(){
    	
        $("#dataFinal").datepicker({
        	dateFormat: 'dd/mm/yy',
        	dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
        	dayNamesMin: ['D','S','T','Q','Q','S','S'],
        	dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b'],
            monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nexText: 'Pr�ximo',
            prevText: 'Anterior'
            
        	
        });	
        	
        });
    
    </script>
   
</body>

</html>
