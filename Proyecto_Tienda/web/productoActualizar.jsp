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
      System.out.println("codigo[" + request.getParameter("codigo") + "]");
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
         //subir archivos
        String rutaActual = getServletContext().getRealPath("/");
        File destinoFoto = new File(rutaActual + "/documentos/fotos/");
        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024, destinoFoto);
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
                    String nombreArchivo = variables.get("nombre") + "_" + variables.get("codigo") + extension;
                    if (elemento.getFieldName().equals("foto"));
                        destinoFoto = new File(rutaActual + "/documentos/fotos/");
                    
                    archivo = new File(elemento.getFieldName());
                    elemento.write(new File(destinoFoto, nombreArchivo));
                    variables.put(elemento.getFieldName(), nombreArchivo);
                    System.out.println("****"+elemento.getName()+"<br>");
                }
            }
        }

        Producto producto=new Producto();
        System.out.println("accion[" + variables.get("accion") + "]");
        switch(variables.get("accion")){
            case "Adicionar":
        producto.setCodigo(variables.get("codigo"));
        producto.setNombre(variables.get("nombre"));
        producto.setDescripcion(variables.get("descripcion"));
        producto.setValorUnitarioCompra(variables.get("valorUnitarioCompra"));
        producto.setValorUnitarioVenta(variables.get("valorUnitarioVenta"));
        producto.setStock(variables.get("stock"));
        producto.setStockMinimo(variables.get("stockMinimo"));
        producto.setStockMaximo(variables.get("stockMaximo"));
        producto.setCodigoDeBarras(variables.get("codigoDeBarras"));
        if (request.getParameter("productosEnDescuento") == null)
            producto.setProductosEnDescuento("0");
        else
            producto.setProductosEnDescuento("1");
        if (request.getParameter("productosPerecederos") == null)
            producto.setProductosPerecederos("0");
        else
            producto.setProductosPerecederos("1");
        producto.setFoto(variables.get("foto"));
        producto.guardar();
        break;
        
       case "Modificar":
        producto.setCodigo(variables.get("codigo"));
        producto.setNombre(variables.get("nombre"));
        producto.setDescripcion(variables.get("descripcion"));
        producto.setValorUnitarioCompra(variables.get("valorUnitarioCompra"));
        producto.setValorUnitarioVenta(variables.get("valorUnitarioVenta"));
        producto.setStock(variables.get("stock"));
        producto.setStockMinimo(variables.get("stockMinimo"));
        producto.setStockMaximo(variables.get("stockMaximo"));
        producto.setCodigoDeBarras(variables.get("codigoDeBarras"));
        if (request.getParameter("productosEnDescuento") == null) {
                producto.setProductosEnDescuento("0");
            } else {
                producto.setProductosEnDescuento("1");
            }
            if (request.getParameter("productosPerecederos") == null) {
                producto.setProductosPerecederos("0");
            } else {
                producto.setProductosPerecederos("1");
            }
            if (!variables.get("foto").equals("")) {
                producto.setFoto(variables.get("foto"));
            }
            producto.setFoto(variables.get("foto"));
            producto.modificar();
        break;
        
        case "Eliminar":
        //System.out.println("parametro Id:"+request.getParameter("id"));
        producto.setCodigo(variables.get("codigo"));
        producto.eliminar();
        break;
}
        
       //response.sendRedirect("principal.jsp?CONTENIDO=producto.jsp");
    }
    
%>
<script type="text/javascript">
    window.location = "principal.jsp?CONTENIDO=producto.jsp";
</script>
