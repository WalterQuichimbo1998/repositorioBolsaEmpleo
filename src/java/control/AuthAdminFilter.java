/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controller.HojaVidaEstudiante;
import controller.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthAdminFilter implements Filter {

    private FilterConfig configuration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.configuration = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, NullPointerException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;

        if (((HttpServletRequest) request).getSession().getAttribute(AccesoBean.USER_KEY) == null) {
            System.out.println("Bloqueando Acceso..");
            ((HttpServletResponse) response).sendRedirect("../index.xhtml");

        } else {
            Usuario u = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
            HojaVidaEstudiante hv = (HojaVidaEstudiante) ((HttpServletRequest) request).getSession().getAttribute("hojaVida");
            String[] ruta = httpRequest.getRequestURI().split("/");
            String r1 = "pagina";
            if (ruta.length <=2) {
                if("ESTUDIANTE".equals(u.getIdRol().getRol()) && hv!=null){
                     r1 = "estudiante";
                }else if("EMPRESA".equals(u.getIdRol().getRol())){
                    r1 = "empleador";
                }else if("ADMINISTRADOR".equals(u.getIdRol().getRol())){
                    r1 = "administrador";
                }else{
                r1 = "estudiante2";
                }
            } else {
                r1 = ruta[2];
            }
            if ("ESTUDIANTE".equals(u.getIdRol().getRol()) && !"estudiante".equals(r1) && hv!=null) {
                ((HttpServletResponse) response).sendRedirect("../estudiante/Estudiante.xhtml");

            }else if ("ESTUDIANTE".equals(u.getIdRol().getRol()) && !"estudiante2".equals(r1) && hv==null) {
                ((HttpServletResponse) response).sendRedirect("../estudiante2/Estudiante_D.xhtml");
            } else if ("EMPRESA".equals(u.getIdRol().getRol()) && !"empleador".equals(r1)) {
                ((HttpServletResponse) response).sendRedirect("../empleador/Empleador.xhtml");
            } else if ("ADMINISTRADOR".equals(u.getIdRol().getRol()) && !"administrador".equals(r1)) {
                System.out.println("ruta 1: " + r1);
                ((HttpServletResponse) response).sendRedirect("../administrador/administrador.xhtml");
            } else {
                chain.doFilter(request, response);
            }

        }
    }

    @Override
    public void destroy() {
        configuration = null;
    }
}
