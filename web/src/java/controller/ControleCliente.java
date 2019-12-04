package controller;

import model.bean.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;

@WebServlet(name = "ControleCliente", urlPatterns = {"/ControleCliente"})
public class ControleCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("acao").contains("cadastrar_cli")) {
            Cliente c = new Cliente();
            c.setNome(request.getParameter("nome"));
            c.setTelefone(request.getParameter("telefone"));
            c.setValorUltimaVenda(Float.parseFloat(request.getParameter("valor")));
            c.setEndereco(request.getParameter("endereco"));
            c.setCep(request.getParameter("cep"));
            c.setBairro(request.getParameter("bairro"));
            try {
                ClienteDAO dao = new ClienteDAO();
                String resultado = dao.inserir(c);
                request.setAttribute("mensagem", resultado);
            } catch (SQLException e) {
                if (e.getErrorCode() == 0) {
                    request.setAttribute("mensagem", "Não foi possível se comunicar com o banco de dados!");
                }
            }
            RequestDispatcher redireciona = request.getRequestDispatcher("view/mensagem.jsp");
            redireciona.forward(request, response);
        }

      

       

       

        if (request.getParameter("acao").contains("listar_cli")) {
            try {
                ClienteDAO dao = new ClienteDAO();
                List clientes = dao.listar();
                if (clientes.isEmpty()) {
                    request.setAttribute("mensagem", "Não há registros para serem exibidos!");
                    RequestDispatcher redireciona = request.getRequestDispatcher("cadastrar_cli.jsp");
                    redireciona.forward(request, response);
                } else {
                    request.setAttribute("listaClientes", clientes);
                    RequestDispatcher redireciona = request.getRequestDispatcher("view/listagem_cli.jsp");
                    redireciona.forward(request, response);
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == 0) {
                    request.setAttribute("mensagem", "Não foi possível se comunicar com o banco de dados!");
                    RequestDispatcher redireciona = request.getRequestDispatcher("view/mensagem.jsp");
                    redireciona.forward(request, response);
                }
            }
        }

      
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
