<%-- 
    Document   : diaNoLaboralActualizar
    Created on : 23/05/2020, 11:22:46 a. m.
    Author     : root
--%>

<%@page import="clases.DiaNoLaboral"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        DiaNoLaboral diaNoLaboral = new DiaNoLaboral();
        switch (request.getParameter("accion")) {
        case "Adicionar":
         
        diaNoLaboral.setNit(request.getParameter("nit"));
        diaNoLaboral.setDia(request.getParameter("dia"));
        diaNoLaboral.setMes(request.getParameter("mes"));
        diaNoLaboral.setNombre(request.getParameter("nombre"));
        diaNoLaboral.guardar();
        break;  
                  
        
        
       case "Modificar":
        diaNoLaboral.setId(request.getParameter("id"));      
        diaNoLaboral.setNit(request.getParameter("nit"));
        diaNoLaboral.setDia(request.getParameter("dia"));
        diaNoLaboral.setMes(request.getParameter("mes"));
        diaNoLaboral.setNombre(request.getParameter("nombre"));
        diaNoLaboral.modificar();
        break;
        
        case "Eliminar":
        
        diaNoLaboral.setId(request.getParameter("id"));
        diaNoLaboral.eliminar();
        break;
        
    }
                 
        //response.sendRedirect("principal.jsp?CONTENIDO=diaNoLaboral.jsp");*/
    }
    
%>
<script type="text/javascript">
    location = "principal.jsp?CONTENIDO=diaNoLaboral.jsp";
</script>