<%-- 
    Document   : comprasFormulario
    Created on : 29/05/2020, 6:05:33 p. m.
    Author     : root
--%>

<%@page import="clases.Producto"%>
<%@page import="clases.FacturaDetalle"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        String accion = "Adicionar ";
        FacturaDetalle compra = new FacturaDetalle();
        Producto producto = new Producto();
        if (request.getParameter("id") == null) {
            //no llega id entonces va a adicionar
        } else {
            //llega id entonces va a modificar o eliminar segun la opcion
            accion = request.getParameter("accion");
            compra = new FacturaDetalle(request.getParameter("id"));
        
        }


%>
<h3><%= accion.toUpperCase()%> FACTURA DE COMPRA </h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=comprasActualizar.jsp">
    <div class="container">
        <div class="row">
            <div class="col-md-8 px-3">
                <table border="0">
                    <tr><th>Fecha(*)</th><td><input type="time" name="fecha"  required></td></tr>   
                    <tr><th>Numero de factura  (*)</th><td><input type="text" name="numeroFactura" maxlength="20"  placeholder="numero de la factura" required></td></tr>    
                    <input type="checkbox">Proveedor <select name="proveedor" onChange="submit()"><option>Seleccione Proveedor</option><%=FacturaDetalle.getListaEnOptions("facturaDetalle")%></select><br>
                    </table>
                    <table border="0">
                    <tr><th>Codigo del Producto*)</th><td><input type="text" name="codigoProducto" value="<%=compra.getCodigoProducto()%>" required></td></tr>    
                    <tr><th>Nombre del Producto(*)</th><td><input type="text" name="nombre" value="<%=producto.getNombre()%>" required></td></tr>    
                    <tr><th>Cantidad(*)</th><td><input type="text" name="cantidad" value="<%=compra.getCantidad()%>" required></td></tr>    
                    <tr><th>Unidad(*)</th><td><input type="text" name="unidad" required></td></tr>    
                    <tr><th>Costo Unitario*)</th><td><input type="text" name="valorUnitarioCompra" value="<%=producto.getValorUnitarioCompra()%>" required></td></tr>    
                    <tr><th>Descuento(*)</th><td><input type="text" name="descuento" value="<%=compra.getDescuento()%>" required></td></tr>    
                    <%--<tr><th>Subtotal(*)</th><td><input type="text" name="productosPerecederos" value="<%=compra.getCantidad()* producto.getValorUnitarioCompra()-compra.getDescuento%>" required></td></tr>   
                    <tr><th>Iva(*)</th><td><input type="text" name="productosEnDescuento" value="<%=producto.getProductosEnDescuento()%>" required></td></tr>    
                    <tr><th>Total</th><td><input type="text" name="total" id="archivo" value="total" ></td></tr> 
                </table>
                <input type="hidden" name="codigo" value="<%=compra.getId()%>">
                <input type="submit" name="accion" value="<%=accion%>">
            </div>
            <div class="col-6 col-md-4">
                <img src="documentos/fotos/<%=compra.getId()%> " class="rounded float-right"  height="200" width="200" id="foto"/>--%> 
            </div>
        </div>
    </div>
</form>

<%
    }
%>
