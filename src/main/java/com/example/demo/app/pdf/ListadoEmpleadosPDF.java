package com.example.demo.app.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.app.Entity.Trabajador;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("trabajador-list")
public class ListadoEmpleadosPDF extends AbstractPdfView{
	
	private static final String[] header = { "Tipo", "Documento", "Primer Apellido", "Segundo Apellido",
			"Primer Nombre", "Segundo Nombre", "Correo", "Teléfono"};

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Trabajador> listadoEmpleados = (List<Trabajador>) model.get("trabajadores");

		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20,20,40,20);
		document.open();

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		celda = new PdfPCell(new Phrase("LISTADO EMPLEADOS"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(255, 217, 169));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablaEmpleados = new PdfPTable(8);
		
		for (int i = 0; i < header.length; i++) {
			tablaEmpleados.addCell(header[i]);
		}

		listadoEmpleados.forEach(trabajador -> {

			tablaEmpleados.addCell(trabajador.getTipoDocumento());
			tablaEmpleados.addCell(trabajador.getId().toString());
			tablaEmpleados.addCell(trabajador.getPrimerApellido());
			tablaEmpleados.addCell(trabajador.getSegundoApellido());
			tablaEmpleados.addCell(trabajador.getPrimerNombre());
			tablaEmpleados.addCell(trabajador.getSegundoNombre());
			tablaEmpleados.addCell(trabajador.getCorreo());
			tablaEmpleados.addCell(trabajador.getTelefono());
		});

		document.add(tablaTitulo);
		document.add(tablaEmpleados);
	}

}
