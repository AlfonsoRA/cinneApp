<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<spring:url value="/" var="urlRoot"></spring:url> 
  
    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${urlRoot}">My CineSite</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav"> 
  
            
<!--             autorizacion para usuarios que son editores y gerentes   -->
            <sec:authorize access="hasAnyAuthority('EDITOR', 'GERENTE')">
            	<li><a href="${urlRoot}pelicula/indexPagina?page=0">Peliculas</a></li>  
	          	<li><a href="${urlRoot}horarios/indexPagina?page=0">Horarios</a></li> 
	          	<li><a href="${urlRoot}noticias/indexPagina?page=0">Noticia</a></li>
            </sec:authorize>
            
            
<!--             autorizacion para usuarios que son gerentes   -->
            <sec:authorize access="hasAnyAuthority('GERENTE')">
           		<li><a href="${urlRoot}banners/indexPagina?page=0">Banners</a></li>
           		<li><a href="${urlRoot}usuarios/index">Usuarios</a></li>
            </sec:authorize>
            
            <li><a href="#">Acerca</a></li>
            
<!--             autorizacion para usuarios que son anonimos   -->
            <sec:authorize access="isAnonymous()">
            	<li><a href="${urlRoot}login">Login</a></li>
            </sec:authorize> 
            
<!--             autorizacion para usuarios que no son anonimos   -->
            <sec:authorize access="!isAnonymous()">
            	<li><a href="${urlRoot}admin/logout">Salir</a></li>
            </sec:authorize>
            
                    
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>