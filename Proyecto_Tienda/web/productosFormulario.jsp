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
        String accion = "Adicionar";
        Producto producto = new Producto();
        if (request.getParameter("codigo") == null) {
            //no llega id entonces va a adicionar
        } else {
            //llega id entonces va a modificar o eliminar segun la opcion
            accion = request.getParameter("accion");
            producto = new Producto(request.getParameter("codigo"));
        }
%>
<h3><%= accion.toUpperCase()%><b> PRODUCTO</b></h3><br/>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=productoActualizar.jsp" enctype="multipart/form-data">
    <div class="container">
        <div class="row">
            <div class="col-md-8 px-3">
                <table border="0">
                    <tr><th>Codigo(*)</th><td><input type="text" name="codigo" value="<%=producto.getCodigo() %>" required></td></tr>   
                    <tr><th>Nombre (*)</th><td><input type="text" name="nombre" maxlength="30" value="<%=producto.getNombre()%>" placeholder="nombre del producto" required></td></tr>    
                    <tr><th>Descripcion</th><td><input type="text" name="descripcion" value="<%=producto.getDescripcion()%>"></td></tr>    
                    <tr><th>Valor unitario de compra(*)</th><td><input type="text" name="valorUnitarioCompra" value="<%=producto.getValorUnitarioCompra()%>" required></td></tr>    
                    <tr><th>Valor unitario de venta(*)</th><td><input type="text" name="valorUnitarioVenta" value="<%=producto.getValorUnitarioVenta()%>" required></td></tr>    
                    <tr><th>Stock(*)</th><td><input type="text" name="stock" value="<%=producto.getStock()%>" required></td></tr>    
                    <tr><th>Stock Minimo(*)</th><td><input type="text" name="stockMinimo" value="<%=producto.getStockMinimo()%>" required></td></tr>    
                    <tr><th>Stock Maximo(*)</th><td><input type="text" name="stockMaximo" value="<%=producto.getStockMaximo()%>" required></td></tr>    
                    <tr><th>Codigo de barras(*)</th><td><input type="text" name="codigoDeBarras" value="<%=producto.getCodigoDeBarras()%>" required></td></tr>    
                </table><br/>
                    <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="productosEnDescuento" name="productosEnDescuento" value="<%=producto.getProductosEnDescuento()%>" >
                    <label class="form-check-label" for="productosEnDescuento"><b>Producto En Descuento</></b></label>
                   </div>
                    <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="productosPerecederos" name="productosPerecederos" value="<%=producto.getProductosPerecederos()%>" >
                    <label class="form-check-label" for="productosPerecederos"><b>Producto Perecedero</></b></label>
                   </div>
                    <tr><th>Foto</th><td><input type="file" name="foto" id="archivo" value="<%=producto.getFoto()%>" onChange="actualizarFoto()" placeholder="Foto del producto"></td></tr>
                           
             
            </div>
                    
            <div class="col-6 col-md-4">
                <img src="documentos/fotos/<%=producto.getFoto()%> " class="rounded float-right"  height="200" width="200" id="foto"/><br/>
            </div>
            
        </div>
            <button type="submit" class="btn btn-primary" name="accion" value="<%=accion%>"><%=accion%></button> 
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
