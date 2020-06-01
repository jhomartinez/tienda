<%-- 
    Document   : productoActualizar
    Created on : 22/05/2020, 12:45:04 p. m.
    Author     : root
--%>

<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page import="java.io.File"%>
<%@page import="clases.Producto"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
         //subir archivos
          String rutaActual = getServletContext().getRealPath("/");
        File destinoPropuesta = new File(rutaActual + "/documentos/fotos/");
        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024, destinoPropuesta);
         ServletFileUpload upload = new ServletFileUpload(factory);
     
        File archivo;//variable donde se va a subir la informacion

        ServletRequestContext origen = new ServletRequestContext(request);
        Map<String, String> variables = new HashMap();
        List elementosFormulario = upload.parseRequest(origen);
        Iterator iterador = elementosFormulario.iterator();
        variables.put("foto", "");
        while (iterador.hasNext()) {
            FileItem elemento = (FileItem) iterador.next();
            if (elemento.isFormField()) {
                variables.put(elemento.getFieldName(), elemento.getString());
            } else {

                //System.out.println(elemento.getFieldName());
                // out.println(elemento.getName()+": "+elemento.getName());
                if (!elemento.getName().equals("")) {
                    int ubicacionPunto = elemento.getName().lastIndexOf(".");
                    String extension = elemento.getName().substring(ubicacionPunto);//con el punto
                    System.out.println("extension:" + extension);
                    String nombreArchivo = variables.get("producto") + "_" + variables.get("codigo") + extension;
                    
                    // out.println("****"+elemento.getName()+"<br>");
                }
            }
        }

        //fin subir archivos

        Producto producto=new Producto();
        switch(request.getParameter("accion")){
            case "Adicionar ":
        producto.setCodigo(request.getParameter("codigo"));
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setValorUnitarioCompra(request.getParameter("valorUnitarioCompra"));
        producto.setValorUnitarioVenta(request.getParameter("valorUnitarioVenta"));
        producto.setStock(request.getParameter("stock"));
        producto.setStockMinimo(request.getParameter("stockMinimo"));
        producto.setStockMaximo(request.getParameter("stockMaximo"));
        producto.setCodigoDeBarras(request.getParameter("codigoDeBarras"));
        producto.setProductosEnDescuento(request.getParameter("productosEnDescuento"));
        producto.setProductosPerecederos(request.getParameter("productosPerecederos"));
        producto.setFoto(request.getParameter("foto"));
        producto.guardar();
        break;
        
        case"Modificar":
        producto.setCodigo(request.getParameter("codigo"));
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setValorUnitarioCompra(request.getParameter("valorUnitarioCompra"));
        producto.setValorUnitarioVenta(request.getParameter("valorUnitarioVenta"));
        producto.setStock(request.getParameter("stock"));
        producto.setStockMinimo(request.getParameter("stockMinimo"));
        producto.setStockMaximo(request.getParameter("stockMaximo"));
        producto.setCodigoDeBarras(request.getParameter("codigoDeBarras"));
        producto.setProductosEnDescuento(request.getParameter("productosEnDescuento"));
        producto.setProductosPerecederos(request.getParameter("productosPerecederos"));
        producto.setFoto(request.getParameter("foto"));
        producto.modificar();
        break;
        
        case "Eliminar":
        //System.out.println("parametro Id:"+request.getParameter("id"));
        producto.setCodigo(request.getParameter("codigo"));
        producto.eliminar();
        break;
        
    }
        
       //response.sendRedirect("principal.jsp?CONTENIDO=producto.jsp");
    }
    
%>
<script type="text/javascript">
    window.location = "principal.jsp?CONTENIDO=producto.jsp";
</script>
