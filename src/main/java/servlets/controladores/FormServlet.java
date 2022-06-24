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

@WebServlet("/admin/formulario")
public class FormServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final DaoCoche DaoCoche = DaoCocheMemoria.getInstancia();

    public FormServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String accion = "A�adir";
		
		if (id != null) {
			Coche coche = Globales.DAO.obtenerPorId(Long.parseLong(id));
			accion = "Modificar";
			
			request.setAttribute("coche", coche);
		}

		request.setAttribute("accion", accion);
		request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		System.out.println(1);
		String matricula = request.getParameter("matricula");
		System.out.println(2);
		String marca = request.getParameter("marca");
		System.out.println(3);
		String modelo = request.getParameter("modelo");
		System.out.println(4);
		String color = request.getParameter("color");
		System.out.println(5);
		int potencia = Integer.parseInt(request.getParameter("potencia"));
		System.out.println(6);
		int cilindrada = Integer.parseInt(request.getParameter("cilindrada"));
		System.out.println(7);
		boolean reservado = false;
		System.out.println(8);
		
		String accion = "";
			
		Coche coche = new Coche(null, matricula, marca, modelo, color, potencia, cilindrada, reservado);
		
		try {
			if(id == null || id.trim().length() == 0) {
				Globales.DAO.insertar(coche);
				accion = "a�adido";
			} else {
				coche.setId(Long.parseLong(id));
				reservado = DaoCoche.obtenerReservadoPorId(Long.parseLong(id));
				Globales.DAO.modificar(coche);
				accion = "modificado";
			}
			
			request.setAttribute("alertatexto", "Se ha " + accion + " el registro correctamente");
			request.setAttribute("alertanivel", "success");
			
		} catch (Exception e) {
			request.setAttribute("alertatexto", "La opci�n de " + accion + " no ha funcionado");
			request.setAttribute("alertanivel", "danger");
		}
			
		request.getRequestDispatcher("/admin/coches").forward(request, response);
		
	}
	

}
