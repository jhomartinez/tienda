<%-- 
    Document   : comprasActualizar
    Created on : 30/05/2020, 11:14:22 p. m.
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
        FacturaDetalle facturaCompras = new FacturaDetalle();
        Producto producto=new Producto();
        switch (request.getParameter("accion")) {
            case "Adicionar":
                facturaCompras.setCodigoProducto(request.getParameter("codigoProducto"));
                producto.setNombre(request.getParameter("nombre"));
                facturaCompras.setCantidad(request.getParameter("cantidad"));
                //facturaCompras.setUnidad(request.getParameter("unidad"));
                producto.setValorUnitarioCompra(request.getParameter("costoUnitario"));
                facturaCompras.setDescuento(request.getParameter("descuento"));
                //facturaCompras.xxxxx(request.getParameter("subTotal"));
                //facturaCompras.setIva(request.getParameter("iva"));
                //facturaCompras.setTotal(request.getParameter("total"));
                facturaCompras.guardar();
                producto.guardar();
                break;

            case "Modificar":
                facturaCompras.setCodigoProducto(request.getParameter("codigoProducto"));
                producto.setNombre(request.getParameter("nombre"));
                facturaCompras.setCantidad(request.getParameter("cantidad"));
                //facturaCompras.setUnidad(request.getParameter("unidad"));
                producto.setValorUnitarioCompra(request.getParameter("costoUnitario"));
                facturaCompras.setDescuento(request.getParameter("descuento"));
                //facturaCompras.xxxxx(request.getParameter("subTotal"));
                //facturaCompras.setIva(request.getParameter("iva"));
                //facturaCompras.setTotal(request.getParameter("total"));
                facturaCompras.modificar();
                producto.modificar();
                break;
            case "Eliminar":
                //System.out.println("parametro Id:"+request.getParameter("id"));
                facturaCompras.setCodigoProducto(request.getParameter("codigoProducto"));
                facturaCompras.eliminar();
                break;

        }

        //response.sendRedirect("principal.jsp?CONTENIDO=personal.jsp");
    }

%>
<script type="text/javascript">
    window.location = "principal.jsp?CONTENIDO=personal.jsp";
</script>
