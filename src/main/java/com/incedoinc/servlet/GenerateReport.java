package com.incedoinc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incedoinc.api.RestAPICall;
import com.incedoinc.dao.PatientDao;

@WebServlet(name = "GenerateReport", urlPatterns = { "/generateReport" })
@MultipartConfig
public class GenerateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet Start!!!!!!!!!!!!!!");
		final String json = generateReport(request.getParameter("patentId"));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private String generateReport(final String patientId) {
		
		PatientDao patientData = new PatientDao();
		
		final String patentDetail =   patientData.getPatientData(patientId);
		RestAPICall.generateReport(patentDetail);
		return null;
	}

}
