<%-- 
    Document   : horarioActualizar
    Created on : 19/05/2020, 10:37:10 a. m.
    Author     : root
--%>

<%@page import="clases.Establecimiento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="clases.Persona"%>
<%@page import="clases.Horario"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        System.out.println("accion[" + request.getParameter("accion") + "]");
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        Horario horario = new Horario();
        Establecimiento tienda = (Establecimiento) sesion.getAttribute("tienda");
        System.out.println("diaLunes:[" + request.getParameter("diaLunes") + "]");
        System.out.println("diaMartes:[" + request.getParameter("diaMartes") + "]");
        switch (request.getParameter("accion")) {
            case "Adicionar":
                horario.setNit(tienda.getNit());
                horario.setHoraApertura(request.getParameter("horaApertura"));
                horario.setHoraCierre(request.getParameter("horaCierre"));
                System.out.println("opcion:[" + request.getParameter("selectHorario") + "]");
                String seleccion = request.getParameter("selectHorario");
                switch (seleccion) {
                    case "general":
                        horario.setDia("true");
                        horario.setTipoHorario("general");
                        horario.guardar();
                        break;
                    case "finSemana":
                        horario.setDia("true");
                        horario.setTipoHorario("finSemana");
                        horario.guardar();
                        break;
                    case "especial":
                        List listaDeDias = new ArrayList();
                        String lunes = request.getParameter("diaLunes");
                        String martes = request.getParameter("diaMartes");
                        String miercoles = request.getParameter("diaMiercoles");
                        String jueves = request.getParameter("diaJueves");
                        String viernes = request.getParameter("diaViernes");
                        String sabado = request.getParameter("diaSabado");
                        String domingo = request.getParameter("diaDomingo");
                        System.out.println("diaLunes:[" + lunes + "]");
                        System.out.println("diaMartes:[" + martes + "]");
                        System.out.println("diaMiercoles[" + miercoles + "]");
                        System.out.println("diaJueves:[" + jueves + "]");
                        System.out.println("diaViernes:[" + viernes + "]");
                        System.out.println("diaSabado:[" + sabado + "]");
                        System.out.println("diaDomingo:[" + domingo + "]");
                        if (lunes != null) {
                            listaDeDias.add("L");
                        }
                        if (martes != null) {
                            listaDeDias.add("M");
                        }
                        if (miercoles != null) {
                            listaDeDias.add("Mi");
                        }
                        if (jueves != null) {
                            listaDeDias.add("J");
                        }
                        if (viernes != null) {
                            listaDeDias.add("V");
                        }
                        if (sabado != null) {
                            listaDeDias.add("S");
                        }
                        if (domingo != null) {
                            listaDeDias.add("D");
                        }
                        System.out.println("LLego hasta el comienzo de la lista");
                        System.out.println("Tamanio de lista:" + listaDeDias.size());
                        //vamos a guardar haciendo un ciclo for de la lista anterior de dias
                        for (int i = 0; i < listaDeDias.size(); i++) {
                            //voy a guardar cada dia en un registro de horario
                            horario.setNit(tienda.getNit());
                            String dia = listaDeDias.get(i).toString();
                            horario.setDia(dia);
                            horario.setHoraApertura(request.getParameter("horaApertura"));
                            horario.setHoraCierre(request.getParameter("horaCierre"));
                            horario.setTipoHorario("especial");
                            horario.toString();
                            horario.guardar();
                        }
                        break;
                }
                break;

            case "Modificar":
                horario.setId(request.getParameter("id"));
                horario.setHoraApertura(request.getParameter("horaApertura"));
                horario.setHoraCierre(request.getParameter("horaCierre"));
                seleccion = request.getParameter("selectHorario");
                switch (seleccion) {
                    case "general":
                        horario.setDia("true");
                        horario.setTipoHorario("general");
                        horario.modificar();
                        break;
                    case "finSemana":
                        horario.setDia("true");
                        horario.setTipoHorario("finSemana");
                        horario.modificar();
                        break;
                    case "especial":
                        List listaDeDias = new ArrayList();
                        horario.eliminar();
                        String lunes = request.getParameter("diaLunes");
                        String martes = request.getParameter("diaMartes");
                        String miercoles = request.getParameter("diaMiercoles");
                        String jueves = request.getParameter("diaJueves");
                        String viernes = request.getParameter("diaViernes");
                        String sabado = request.getParameter("diaSabado");
                        String domingo = request.getParameter("diaDomingo");
                        if (lunes != null) {
                            listaDeDias.add("L");
                        }
                        if (martes != null) {
                            listaDeDias.add("M");
                        }
                        if (miercoles != null) {
                            listaDeDias.add("Mi");
                        }
                        if (jueves != null) {
                            listaDeDias.add("J");
                        }
                        if (viernes != null) {
                            listaDeDias.add("V");
                        }
                        if (sabado != null) {
                            listaDeDias.add("S");
                        }
                        if (domingo != null) {
                            listaDeDias.add("D");
                        }
                        //vamos a guardar haciendo un ciclo for de la lista anterior de dias
                        for (int i = 0; i < listaDeDias.size(); i++) {
                            //voy a guardar cada dia en un registro de horario
                            horario.setNit(tienda.getNit());
                            String dia = listaDeDias.get(i).toString();
                            horario.setDia(dia);
                            horario.setTipoHorario("especial");
                            horario.guardar();
                        }
                        break;
                }
                break;

            case "Eliminar":
                horario.setId(request.getParameter("id"));
                horario.eliminar();
                break;

        }
    }
%>
<script type="text/javascript">
    window.location = "principal.jsp?CONTENIDO=horario.jsp";
</script>