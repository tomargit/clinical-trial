package com.incedoinc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incedoinc.dao.PatientDao;

@WebServlet(name = "HomeServlet", urlPatterns = { "/home" })
@MultipartConfig
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Start!!!!!!!!!!!!!!");
		PatientDao patient = new PatientDao();
		final List<String> patientIds = patient.getPatientIds();
		
		request.setAttribute("patientIds", patientIds);
		
		final List<String> investigatorIds = patient.getInvestigatorIds();
		
		request.setAttribute("investigatorIds", investigatorIds);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
