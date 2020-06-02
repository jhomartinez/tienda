<%-- 
    Document   : diaNoLaboralActualizar
    Created on : 23/05/2020, 11:22:46 a. m.
    Author     : root
--%>

<%@page import="clases.Establecimiento"%>
<%@page import="clases.DiaNoLaboral"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        DiaNoLaboral diaNoLaboral = new DiaNoLaboral();
        Establecimiento e = (Establecimiento) sesion.getAttribute("tienda");
        switch (request.getParameter("accion")) {
        case "Adicionar":
         String fecha = request.getParameter("fecha");
        diaNoLaboral.setNit(e.getNit());
        diaNoLaboral.setDia(fecha.substring(8, 10));
        diaNoLaboral.setMes(fecha.substring(5, 7));
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