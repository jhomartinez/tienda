<%-- 
    Document   : producto
    Created on : 21/05/2020, 8:10:47 a. m.
    Author     : root
--%>
<%@page import="clases.FacturaDetalle"%>
<%@page import="clases.Producto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
            
            String lista = "";
            Producto[] productos = Producto.getListaEnObjetos(null, null);
            for (int i = 0; i < productos.length; i++) {
                Producto producto = productos[i];
                lista += "<tr>";
                lista += "<td>" + producto.getCodigo() + "</td>";
                lista += "<td>" + producto.getNombre() + "</td>";
                //lista += "<td>" + producto.getDescripcion() + "</td>";
                lista += "<td>" + producto.getValorUnitarioCompra() + "</td>";
                lista += "<td>" + producto.getValorUnitarioVenta() + "</td>";
                lista += "<td>" + producto.getStock() + "</td>";
                lista += "<td>" + producto.getStockMinimo() + "</td>";
                lista += "<td>" + producto.getStockMaximo() + "</td>";
                lista += "<td>" + producto.getCodigoDeBarras() + "</td>";
                 if (producto.getProductosEnDescuento().equals("0")) {
                     lista += "<td>sin descuento</td>";
                }else{
                     if(producto.getProductosEnDescuento().equals("1")){
                     lista += "<td>con descuento</td>";
                }
                }
                  if (producto.getProductosPerecederos().equals("0")) {
                     lista += "<td>no perecederos</td>";
                }else{
                     if(producto.getProductosPerecederos().equals("1")){
                     lista += "<td>perecederos</td>";
                }
                }
                
                lista += "<td><img src='documentos/fotos/" + producto.getFoto() + "' width='50' heigth='50'/></td>";
                lista += "<td>";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Modificar&codigo=" + producto.getCodigo() + " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Eliminar&codigo=" + producto.getCodigo() + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Kardex&codigo=" + producto.getCodigo() + " '>K</a> ";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Lote&codigo=" + producto.getCodigo() + " '>L</a> ";
                lista += "</td>";
                lista += "</tr>";
            }
        

%>

<h3><b>Lista de Productos</b></h3><br/>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=producto.jsp">
<input type="checkbox">Codigo<select name="codigo" onChange="submit()"><option>Seleccione Codigo del producto</option><%=Producto.getListaEnOptions("codigo")%></select><br>
<input type="checkbox">Codigo de barras <select name="codigoDeBarras" onChange="submit()"><option>Seleccione Codigo de barras</option><%=Producto.getListaEnOptions("codigoDeBarras")%></select><br>
<input type="checkbox">Nombre del producto <select name="nombre" onChange="submit()"><option>Seleccione el nombre del producto</option><%=Producto.getListaEnOptions("nombre")%></select><br>
<input type="checkbox">Productos Perecederos <select name="productosPerecederos" onChange="submit()"><option>Seleccione </option><%=Producto.getListaEnOptions("productosPerecederos")%></select><br>
<input type="checkbox">Productos con descuento <select name="productosEnDescuento" onChange="submit()"><option>Seleccione </option><%=Producto.getListaEnOptions("productosEnDescuento")%></select><br>
<input type="checkbox">Estado <select name="estado" onChange="submit()"><option>Seleccione </option><%=Producto.getListaEnOptions("estado")%></select><br> 
<%--<button type="submit" class="btn btn-primary" name="buscar" value="buscar"</button>--%>
</form><br/>
   <table border="1">
    <tr>
        <th>Cod.</th><th>Nombre</th><th>Costo u</th><th>Precio u</th><th>Stock</th><th>Stock Min.</th><th>Stock Max.</th><th>Cod.barras</th><th>Dto</th><th>Perecederos</th> <%--<th>Estado</th>--%><th>Foto</th>
        <th><a href="principal.jsp?CONTENIDO=productosFormulario.jsp">+</a></th>
    </tr>
    <%=lista %>
</table>


<%
    }
%>
