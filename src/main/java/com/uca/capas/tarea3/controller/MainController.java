package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/ingresar")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index");
		return mav;
	}
	
	@RequestMapping("/parametros1")
	public ModelAndView parametros1(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("nombres");
		String apellido = request.getParameter("apellidos");
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		String lugar_nacimiento = request.getParameter("lugar_nacimiento");
		String colegio = request.getParameter("colegio");
		String tel_fijo = request.getParameter("tel_fijo");
		String tel_movil = request.getParameter("tel_movil");
		
		List<String> errores = new ArrayList<>();
		
		Date bDay= new SimpleDateFormat("yyyy-MM-DD").parse(fecha_nacimiento);
		Date mDay= new SimpleDateFormat("DD/MM/yyyy").parse("01/01/2003");
		
		if(nombre.length()>26 || nombre.isEmpty()) {
			errores.add("El campo nombres debe de tener como mínimo 1 carácter y máximo 25 caracteres");
		}
		if(apellido.length()>26 || apellido.isEmpty()) {
			errores.add("El campo apellidos debe de tener como mínimo 1 carácter y máximo 25 caracteres");
		}
		else if(bDay.after(mDay)) {
			errores.add("La fecha de nacimiento no puede ser mayor al 1 de enero de 2003");
		}
		if(lugar_nacimiento.length()>26 || lugar_nacimiento.isEmpty()) {
			errores.add("El campo lugar de nacimiento debe de tener como mínimo 1 carácter y máximo 25 caracteres");
		}
		if(colegio.length()>101 || colegio.isEmpty()) {
			errores.add("El campo intituto o colegio de procedencia debe de tener como mínimo 1 carácter y máximo 100 caracteres");
		}
		
		try {
			int i = Integer.parseInt(tel_fijo);
			if(tel_fijo.length() != 8 ) {
				errores.add("El campo telefono debe de tener 8 números exactamente");
			}
		}
		catch(NumberFormatException nfe){
			errores.add("El campo telefono fijo debe de tener 8 números exactamente");
		}
		
		try {
			int j = Integer.parseInt(tel_movil);
			if(tel_movil.length() != 8) {
				errores.add("El campo telefono movil debe de tener 8 números exactamente");
			}
		}
		catch(NumberFormatException nfe){
			errores.add("El campo telefono movil debe de tener 8 números exactamente");
		}
		
		mav.addObject("errores",errores);
		
		if(errores.isEmpty()) {
			mav.setViewName("/resultado");
		}
		else {
			mav.setViewName("/errores");
		}
		
		return mav;
	}

}
