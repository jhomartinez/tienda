<%-- 
    Document   : productosFormulario
    Created on : 21/05/2020, 9:45:10 p. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%@page import="clases.Producto"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        String accion = "Adicionar ";
        Producto producto = new Producto();
        if (request.getParameter("codigo") == null) {
            //no llega id entonces va a adicionar
        } else {
            //llega id entonces va a modificar o eliminar segun la opcion
            accion = request.getParameter("accion");
            producto = new Producto(request.getParameter("codigo"));
        }


%>
<h3><%= accion.toUpperCase()%> PRODUCTO</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=productoActualizar.jsp">
    <div class="container">
        <div class="row">
            <div class="col-md-8 px-3">
                <table border="0">
                    <tr><th>Codigo(*)</th><td><input type="text" name="codigo" value="<%=producto.getCodigo()%>" required></td></tr>   
                    <tr><th>Nombre (*)</th><td><input type="text" name="nombre" maxlength="30" value="<%=producto.getNombre()%>" placeholder="nombre del producto" required></td></tr>    
                    <tr><th>Descripcion</th><td><input type="text" name="descripcion" value="<%=producto.getDescripcion()%>"></td></tr>    
                    <%--int valorUnitarioCompra=Integer.parseInt(producto.getValorUnitarioCompra());--%>
                    <tr><th>Valor unitario de compra(*)</th><td><input type="text" name="volorUnitarioCompra" value="<%=producto.getValorUnitarioCompra()%>" required></td></tr>    
                    <tr><th>Valor unitario de venta(*)</th><td><input type="text" name="volorUnitarioVenta" value="<%=producto.getValorUnitarioVenta()%>" required></td></tr>    
                    <tr><th>Stock(*)</th><td><input type="int" name="stock" value="<%=producto.getStock()%>" required></td></tr>    
                    <tr><th>Stock Minimo(*)</th><td><input type="numero" name="stockMinimo" value="<%=producto.getStockMinimo()%>" required></td></tr>    
                    <tr><th>Stock Maximo(*)</th><td><input type="numero" name="stockMaximo" value="<%=producto.getStockMaximo()%>" required></td></tr>    
                    <tr><th>Codigo de barras(*)</th><td><input type="text" name="codigoDeBarras" value="<%=producto.getCodigoDeBarras()%>" required></td></tr>    
                    <tr><th>Productos Perecederos(*)</th><td><input type="boolean" name="productosPerecederos" value="<%=producto.getProductosPerecederos()%>" required></td></tr>    
                    <tr><th>Productos con Descuento(*)</th><td><input type="boolean" name="productosEnDescuento" value="<%=producto.getProductosEnDescuento()%>" required></td></tr>    
                    <tr><th>Foto</th><td><input type="file" name="foto" id="archivo" value="<%=producto.getFoto()%>" onChange="actualizarFoto()" placeholder="Foto del producto"></td></tr> 
                </table>
                <input type="hidden" name="codigo" value="<%=producto.getCodigo()%>">
                <input type="submit" name="accion" value="<%=accion%>">
            </div>
            <div class="col-6 col-md-4">
                <img src="documentos/fotos/<%=producto.getFoto()%> " class="rounded float-right"  height="200" width="200" id="foto"/>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    function actualizarFoto() {
        alert(document.formulario.foto.value);
        var archivo = document.getElementById("archivo").files[0];
        var reader = new FileReader();
        reader.readAsDataURL(archivo);
        reader.onloadend = function () {
            document.getElementById("foto").src = reader.result;

        }
    }
</script>

<%
    }
%>
