<%-- 
    Document   : adicionarHorario
    Created on : 22/05/2020, 5:40:00 p. m.
    Author     : root
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="clases.Establecimiento"%>
<%@page import="clases.Horario"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        Establecimiento tienda = (Establecimiento) sesion.getAttribute("tienda");
        String accion = "Adicionar";
        Horario horario = new Horario();
        String options = "";
        Map<String, Boolean> dias = new HashMap<String, Boolean>();
        if (request.getParameter("id") == null) {
            //no llega id entonces va a adicionar
            options = Horario.getListaEnOptions(tienda.getNit());
        } else {
            //llega id entonces va a modificar o eliminar segun la opcion
            accion = request.getParameter("accion");
            horario = new Horario(request.getParameter("id"));            
            if(horario.getTipoHorario().equals("general")){
                dias = Horario.getDiasHorarioGeneral();
                options += "<option value='general' selected>General</option>";
                //verifico que este disponible el horario de fin de semana
                if(!Horario.tieneHorarioTipoFinSemana(tienda.getNit()))
                    options += "<option value='finSemana'>Fin de semana</option>";
                options += "<option value='especial'>Especial</option>";
            } else if (horario.getTipoHorario().equals("finSemana")){
                dias = Horario.getDiasHorarioFinSemana();
                options += "<option value='finSemana' selected>Fin de semana</option>";
                if(!Horario.tieneHorarioTipoGeneral(tienda.getNit()))
                    options += "<option value='general'>General</option>";
                options += "<option value='especial'>Especial</option>";
            } /** En caso de que se tenga que modificar el horario especial
                else if (horario.getTipoHorario().equals("especial")){
                options += "<option value='especial' selected>Especial</option>";
                if(!Horario.tieneHorarioTipoFinSemana(tienda.getNit()))
                    options += "<option value='finSemana'>Fin de semana</option>";
                if(!Horario.tieneHorarioTipoGeneral(tienda.getNit()))
                    options += "<option value='general'>General</option>";
            }**/
        }
        
%>
<h3><%= accion.toUpperCase()%> HORARIO</h3><br>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=horarioActualizar.jsp">
    <div class="container">
        <div class="row">
            <div class=" col form-group">
                <label for="Seleccione horario">Tipo de Horario</label>
                <select class="custom-select" id="selectHorario" name="selectHorario" required >
                    <option value="0">Selecciona una opción</option>
                    <%=options%>
                </select>
                </label>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaLunes" name="diaLunes">
                    <label class="custom-control-label" for="diaLunes">Lunes</label>
                </div>
            </div>
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaMartes" name="diaMartes">
                    <label class="custom-control-label" for="diaMartes">Martes</label>
                </div>
            </div>
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaMiercoles" name="diaMiercoles">
                    <label class="custom-control-label" for="diaMiercoles">Miercoles</label>
                </div>
            </div>
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaJueves" name="diaJueves">
                    <label class="custom-control-label" for="diaJueves">Jueves</label>
                </div>
            </div>
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaViernes" name="diaViernes">
                    <label class="custom-control-label" for="diaViernes">Viernes</label>
                </div>
            </div>    
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaSabado" name="diaSabado">
                    <label class="custom-control-label" for="diaSabado">Sabado</label>
                </div>    
            </div>    
            <div class="col">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="diaDomingo" name="diaDomingo">
                    <label class="custom-control-label" for="diaDomingo">Domingo</label>
                </div>
            </div>
        </div>
        <div class="row my-3">
            <div class="col">
                <label for="horaApertura">Hora de Entrada</label>
                <input type="time" class="form-control" id="horaApertura" name="horaApertura" value="<%=!horario.getHoraApertura().isEmpty() ? horario.getHoraApertura() : "" %>">
            </div>
            <div class="col">
                <label for="HoraCierre">Hora de Salida</label>
                <input type="time" class="form-control" id="horaCierre" name="horaCierre" value="<%=!horario.getHoraCierre().isEmpty() ? horario.getHoraCierre() : "" %>">
            </div>
        </div>
        <input type="hidden" name="id" value="<%=!horario.getId().isEmpty() ? horario.getId() : null %>">
        <div class="row">
            <div class="col">
                <button type="submit" class="btn btn-primary" name="accion" value="<%=accion%>"><%=accion%></button>
            </div>
        </div>
    </div>
    <br>
</form>
<%
    }
%>
<script type="text/javascript">
    document.getElementById("selectHorario").addEventListener("change", seleccionarOpcion);
    function seleccionarOpcion() {
        limpiarCheckBoxes();
        var x = document.getElementById("selectHorario").value;
        if (x == 'general') {
            document.getElementById("diaLunes").checked = true;
            document.getElementById("diaMartes").checked = true;
            document.getElementById("diaMiercoles").checked = true;
            document.getElementById("diaJueves").checked = true;
            document.getElementById("diaViernes").checked = true;
            document.getElementById("diaLunes").disabled = true;
            document.getElementById("diaMartes").disabled = true;
            document.getElementById("diaMiercoles").disabled = true;
            document.getElementById("diaJueves").disabled = true;
            document.getElementById("diaViernes").disabled = true;
            document.getElementById("diaSabado").disabled = true;
            document.getElementById("diaDomingo").disabled = true;
        } else if (x == 'finSemana') {
            document.getElementById("diaLunes").disabled = true;
            document.getElementById("diaMartes").disabled = true;
            document.getElementById("diaMiercoles").disabled = true;
            document.getElementById("diaJueves").disabled = true;
            document.getElementById("diaViernes").disabled = true;
            document.getElementById("diaSabado").disabled = true;
            document.getElementById("diaDomingo").disabled = true;
            document.getElementById("diaSabado").checked = true;
            document.getElementById("diaDomingo").checked = true;
        }
    }
    function limpiarCheckBoxes() {
        document.getElementById("diaLunes").disabled = false;
        document.getElementById("diaMartes").disabled = false;
        document.getElementById("diaMiercoles").disabled = false;
        document.getElementById("diaJueves").disabled = false;
        document.getElementById("diaViernes").disabled = false;
        document.getElementById("diaSabado").disabled = false;
        document.getElementById("diaDomingo").disabled = false;
        document.getElementById("diaLunes").checked = false;
        document.getElementById("diaMartes").checked = false;
        document.getElementById("diaMiercoles").checked = false;
        document.getElementById("diaJueves").checked = false;
        document.getElementById("diaViernes").checked = false;
        document.getElementById("diaSabado").checked = false;
        document.getElementById("diaDomingo").checked = false;
    }
</script>