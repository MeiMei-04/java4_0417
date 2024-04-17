<%-- 
    Document   : index
    Created on : Apr 17, 2024, 8:21:35 PM
    Author     : HieuCute
--%>
<%@page import="entity.SanPham" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<SanPham> lstSanPham = (List<SanPham>) request.getAttribute("lstSanPham");
if(lstSanPham != null){
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="/Demo_java4_04172024/sanpham/acction">
            <input type="hidden" name="id" value="${sanPham.getId()}"><br>
            Tên: <input type="text" name="name" value="${sanPham.getTen()}"><br>
            Giá: <input type="text" name="gia" value="${sanPham.getGia()}"><br>
            Số Lượng: <input type="text" name="soluong" value="${sanPham.getSoluong()}"><br>
            <button name="acction" value="add">Thêm</button>
            <button name="acction" value="edit">Sửa</button>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th>Giá</th>
                    <th>Số Lượng</th>
                    <th>Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <%
                for(SanPham sp : lstSanPham){   
                %>
                <tr>
                    <td><%=sp.getId()%></td>
                    <td><%=sp.getTen()%></td>
                    <td><%=sp.getGia()%></td>
                    <td><%=sp.getSoluong()%></td>
                    <td>
                        <a href="/Demo_java4_04172024/sanpham/detail?id=<%=sp.getId()%>">
                            <button>Chi Tiết</button>
                        </a>
                        <button onclick="deleteById(<%=sp.getId()%>)">Xóa</button>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>


    </body>
    <script>
        function deleteById(id) {
            if(confirm("Bạn Chắc Chắn Chưa?")){
                window.location.href = "/Demo_java4_04172024/sanpham/delete?id="+id;
            }
        }
        ;
    </script>
</html>
<%
    }
%>