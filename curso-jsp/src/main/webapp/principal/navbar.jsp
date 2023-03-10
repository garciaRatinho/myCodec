<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<nav class="navbar header-navbar pcoded-header">
              <div class="navbar-wrapper">
                  <div class="navbar-logo">
                      <a class="mobile-menu waves-effect waves-light" id="mobile-collapse" href="#!">
                          <i class="ti-menu"></i>
                      </a>
                      <div class="mobile-search waves-effect waves-light">
                          <div class="header-search">
                              <div class="main-search morphsearch-search">
                                  <div class="input-group">
                                      <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                      <input type="text" class="form-control" placeholder="Enter Keyword">
                                      <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <a href="index.html">
                          <img width="30px" class="img-fluid" src="<%= request.getContextPath() %>/assets/images/lactea.PNG" alt="Theme-Logo" />
                      </a>
                      <a class="mobile-options waves-effect waves-light">
                          <i class="ti-more"></i>
                      </a>
                  </div>
                
                  <div class="navbar-container container-fluid">
                      <ul class="nav-left">
                          <li>
                              <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                          </li>
                          <li class="header-search">
                              <div class="main-search morphsearch-search">
                                  <div class="input-group">
                                      <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                      <input type="text" class="form-control">
                                      <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                                  </div>
                              </div>
                          </li>
                          <li>
                              <a href="#!" onclick="javascript:toggleFullScreen()" class="waves-effect waves-light">
                                  <i class="ti-fullscreen"></i>
                              </a>
                          </li>
                      </ul>
                      <ul class="nav-right">
                          <li class="header-notification">
                              <a href="#!" class="waves-effect waves-light">
                                  <i class="ti-bell"></i>
                                  <span class="badge bg-c-red"></span>
                              </a>
                              <ul class="show-notification">
                                  <li>
                                      <h6>Notifica??es</h6>
                                      <label class="label label-danger">Not?cias</label>
                                  </li>
                                  <li class="waves-effect waves-light">
                                      <div class="media">
                                          <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/assets/images/gar.jpg" alt="Generic placeholder image">
                                          <div class="media-body">
                                              <h5 class="notification-user"><%= session.getAttribute("usuario") %></h5>
                                              <p class="notification-msg">At? quando este problema de falta de sistema web.</p>
                                              <span class="notification-time">30 minutes ago</span>
                                          </div>
                                      </div>
                                  </li>
                                  <li class="waves-effect waves-light">
                                      <div class="media">
                                          <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/assets/images/ga.jpg" alt="Generic placeholder image">
                                          <div class="media-body">
                                              <h5 class="notification-user">Garcia Marcos Ratinho</h5>
                                              <p class="notification-msg">Encontramos solu??o com sistemas para seus neg?cios.</p>
                                              <span class="notification-time">Esteve online a 30min</span>
                                          </div>
                                      </div>
                                  </li>
                                  <li class="waves-effect waves-light">
                                      <div class="media">
                                          <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/assets/images/li.jpg" alt="Generic placeholder image">
                                          <div class="media-body">
                                              <h5 class="notification-user">L?dia Manzambi Victor David</h5>
                                              <p class="notification-msg">Nos encontramos na quarta as 14h para acertarmos.</p>
                                              <span class="notification-time">Esteve online a 30min</span>
                                          </div>
                                      </div>
                                  </li>
                              </ul>
                          </li>
                          
                          <li class="user-profile header-notification">
                              <a href="#!" class="waves-effect waves-light">
                                 
                                  <span><%= session.getAttribute("usuario") %></span>
                                  <i class="ti-angle-down"></i>
                              </a>
                              
                              <ul class="show-notification profile-notification">
                              
                               <!--
                                  <li class="waves-effect waves-light">
                                      <a href="#!">
                                          <i class="ti-settings"></i> Settings
                                      </a>
                                  </li>
                                  
                                  <li class="waves-effect waves-light">
                                      <a href="user-profile.html">
                                          <i class="ti-user"></i> Profile
                                      </a>
                                  </li>
                                  <li class="waves-effect waves-light">
                                      <a href="email-inbox.html">
                                          <i class="ti-email"></i> Minhas Menssagens
                                      </a>
                                  </li>
                                  <li class="waves-effect waves-light">
                                      <a href="auth-lock-screen.html">
                                          <i class="ti-lock"></i> Lock Screen
                                      </a>
                                  </li>
                                   -->
                                  <li class="waves-effect waves-light">
                                      <a href="<%=request.getContextPath()%>/Loginservlet?acao=Logout">
                                          <i class="ti-layout-sidebar-left"></i> Sair
                                      </a>
                                  </li>
                              </ul>
                          </li>
                      </ul>
                  </div>
              </div>
          </nav>