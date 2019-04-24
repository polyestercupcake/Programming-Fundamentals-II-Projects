package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Circuit;

/**
 * Servlet implementation class Controller.
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request the request
	 * @param response the response
	 * @throws ServletException if something goes wrong
	 * @throws IOException if something goes wrong in input/output
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {
		Circuit myCircuit = new Circuit();
		if (request.getParameter("txtSubmit") != null) {
			if (request.getParameter("txtVoltage").isEmpty() 
					&& !request.getParameter("txtAmperage").isEmpty() 
					&& !request.getParameter("txtResistance").isEmpty()) {
				myCircuit.setAmperage(Double.parseDouble(request.getParameter("txtAmperage")));
				myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));
				myCircuit.calcualteVoltage();
				request.setAttribute("message", "The voltage is " + myCircuit.getVoltage());
			} else if (request.getParameter("txtAmperage").isEmpty() 
					&& !request.getParameter("txtVoltage").isEmpty() 
					&& !request.getParameter("txtResistance").isEmpty()) {
				myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
				myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));
				myCircuit.calcualteAmperage();
				request.setAttribute("message", "The amperage is " + myCircuit.getAmperage());
			} else if (request.getParameter("txtResistance").isEmpty() 
					&& !request.getParameter("txtVoltage").isEmpty() 
					&& !request.getParameter("txtAmperage").isEmpty()) {
				myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
				myCircuit.setAmperage(Double.parseDouble(request.getParameter("txtAmperage")));
				myCircuit.calcualteResistance();
				request.setAttribute("message", "The resistance is " 
				+ myCircuit.getResistance());
			} else {
				request.setAttribute("message", "Please read the directions carefully "
						+ "and execute the program accordingly.");
			}
		}
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request the request
	 * @param response the response
	 * @throws ServletException if something goes wrong
	 * @throws IOException if something goes wrong in input/output
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
