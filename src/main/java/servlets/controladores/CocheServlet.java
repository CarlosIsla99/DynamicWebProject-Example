package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.dal.DaoCoche;
import servlets.dal.DaoCocheMemoria;
import servlets.modelos.Coche;

import java.io.IOException;

@WebServlet("/admin/coche")
public class CocheServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final DaoCoche DAO = DaoCocheMemoria.getInstancia();

    public CocheServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		Coche coche = DAO.obtenerPorId(id);
		
		request.setAttribute("coche", coche);
		request.getRequestDispatcher("/WEB-INF/vistas/coche.jsp").forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
