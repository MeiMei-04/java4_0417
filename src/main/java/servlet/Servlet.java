/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import dao.SanPhamDao;
import entity.SanPham;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HieuCute
 */
@WebServlet(name = "Servlet",urlPatterns = {"/sanpham/list","/sanpham/delete","/sanpham/detail","/sanpham/acction"})
public class Servlet extends HttpServlet {
    SanPhamDao SanPhamDao;
    List<SanPham> lstSanPham = new ArrayList<>();
    List<String> errors = new ArrayList<>();
    String error = null;
    SanPham sanPham = null;

    @Override
    public void init() throws ServletException {
        super.init();
        SanPhamDao = new SanPhamDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        String uri = req.getRequestURI();
        String method = req.getMethod();
        lstSanPham = SanPhamDao.getAlldata();
        req.setAttribute("lstSanPham", lstSanPham);
        if(uri.contains("/sanpham/list")){
            if(method.equals("GET")){
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
            }
        }else if(uri.contains("/sanpham/delete")){
            if(method.equals("GET")){
                String id = req.getParameter("id");
                if(id==null || id.equals("")){
                    
                }else{
                    sanPham = SanPhamDao.getDataById(Integer.parseInt(id));
                    SanPhamDao.deleteData(sanPham);
                    resp.sendRedirect("/Demo_java4_04172024/sanpham/list");
                    return;
                }
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
            }
        }else if(uri.contains("/sanpham/detail")){
            if(method.equals("GET")){
                String id = req.getParameter("id");
                if(id==null || id.equals("")){
                    
                }else{
                    sanPham = SanPhamDao.getDataById(Integer.parseInt(id));
                    System.out.println(sanPham.getTen());
                    req.setAttribute("sanPham", sanPham);
                }
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
            }
        }else if(uri.contains("/sanpham/acction")){
            if(method.equals("GET")){
                
            }else if(method.equals("POST")){
                String id = req.getParameter("id");
                String name = req.getParameter("name");
                String gia = req.getParameter("gia");
                String soluong = req.getParameter("soluong");
                String acction = req.getParameter("acction");
                if(name == null || name.equals("")){
                    
                }else if(gia == null || gia.equals("")){
                    
                }else if(soluong == null || soluong.equals("")){
                    
                }else{
                    if(acction.equals("add")){
                        sanPham = new SanPham(name, Integer.parseInt(gia), Integer.parseInt(soluong));
                        SanPhamDao.addData(sanPham);
                    }else if(acction.equals("edit")&&id!=null||!id.equals("")){
                        sanPham = new SanPham(Integer.parseInt(id),name, Integer.parseInt(gia), Integer.parseInt(soluong));
                        SanPhamDao.edtData(sanPham);
                    }
                    resp.sendRedirect("/Demo_java4_04172024/sanpham/list");
                    return;
                    
                }
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}
