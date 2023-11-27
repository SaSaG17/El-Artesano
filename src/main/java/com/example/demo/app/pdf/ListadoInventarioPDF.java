package com.example.demo.app.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.app.Entity.Plato;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("plato-list")
public class ListadoInventarioPDF extends AbstractPdfView{

	private static final String[] header = { "ID", "Nombre", "Descripción", "Precio"};
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Plato> listadoPlatos = (List<Plato>) model.get("platos");

		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20,20,40,20);
		document.open();

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		celda = new PdfPCell(new Phrase("MENU DEL ARTESANO"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(255, 217, 169));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable inventario = new PdfPTable(4);
		
		for (int i = 0; i < header.length; i++) {
			inventario.addCell(header[i]);
		}

		listadoPlatos.forEach(plato -> {

			inventario.addCell(plato.getId().toString());
			inventario.addCell(plato.getNombre());
			inventario.addCell(plato.getDescripcion());
			inventario.addCell(String.valueOf(plato.getPrecio()));
		});

		document.add(tablaTitulo);
		document.add(inventario);
	}

}
