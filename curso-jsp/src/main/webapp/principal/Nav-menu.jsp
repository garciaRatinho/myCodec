<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
 <c:set scope="session" var="perfil" value='<%= request.getSession().getAttribute("perfil").toString() %>'></c:set>
    
 <nav class="pcoded-navbar">
                      <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
                      <div class="pcoded-inner-navbar main-menu">
                          <div class="">
               
                              <div class="main-menu-header">
                              
                                 <c:if test="${imagemUser != '' && imagemUser != null}">
                                  <img class="img-80 img-radius" src="${imagemUser}" alt="Imagem de perfil">
                                 </c:if>
                                 
                                 <c:if test="${imagemUser == '' || imagemUser == null}">
                                  <img class="img-80 img-radius" src="assets/images/gar.jpg" alt="Imagem de perfil">
                                 </c:if>
                                 
                                  <div class="user-details">
                                      <span id="more-details"><%= session.getAttribute("usuario") %><i class="fa fa-caret-down"></i></span>
                                  </div>
                              </div>
                              
        
                              <div class="main-menu-content">
                                  <ul>
                                      <li class="more-details">
                                          <!-- <a href="user-profile.html"><i class="ti-user"></i>Visualizar perfil</a> -->
                                          <!-- <a href="#!"><i class="ti-settings"></i>Settings</a> -->
                                          <a href="<%= request.getContextPath() %>/Loginservlet?acao=Logout"><i class="ti-layout-sidebar-left"></i>Sair</a>
                                      </li>
                                  </ul>
                              </div>
                          </div>
                 
                         <!-- <div class="pcoded-navigation-label" data-i18n="nav.category.navigation">Layout</div>  -->
                          <ul class="pcoded-item pcoded-left-item">
                              <li class="active">
                                  <a href="<%= request.getContextPath() %>/principal/principal.jsp"  class="waves-effect waves-dark" style="margin-to:10px;">
                                      <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                                      <span class="pcoded-mtext" data-i18n="nav.dash.main">Inicio</span>
                                      <span class="pcoded-mcaret"></span>
                                  </a>
                              </li>
                              <li class="pcoded-hasmenu">
                                  <a href="javascript:void(0)" class="waves-effect waves-dark">
                                      <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i></span>
                                      <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Cadastro</span>
                                      <span class="pcoded-mcaret"></span>
                                  </a>
                                  <ul class="pcoded-submenu">
                                  
                                      <c:if test="${perfil=='Administrador'}">
                                      <li class=" ">
                                          <a href="<%= request.getContextPath() %>/servletControlUsuario?acao=ListarUsuario" class="waves-effect waves-dark">
                                              <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                              <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Usuário</span>
                                              <span class="pcoded-mcaret"></span>
                                          </a>
                                      </li>
                                      </c:if>
                                      
                
                                  </ul>
                              </li>
                          </ul>
                          <div class="pcoded-navigation-label" data-i18n="nav.category.forms">Relatório</div>
                          <ul class="pcoded-item pcoded-left-item">
                              <li>
                                  <a href="<%= request.getContextPath() %>/principal/relatorioUser.jsp" class="waves-effect waves-dark">
                                      <span class="pcoded-micon"><i class="ti-layers"></i><b>Usuário</b></span>
                                      <span class="pcoded-mtext" data-i18n="nav.form-components.main">Usuário</span>
                                      <span class="pcoded-mcaret"></span>
                                  </a>
                              </li>
                              
                      </div>
                  </nav>