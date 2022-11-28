package fr.groupeh6.sae.controllers;

import java.io.IOException;

import fr.groupeh6.sae.model.NewPointModel;
import fr.groupeh6.sae.model.TypeNotRegisteredException;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;

public class NewPointController {
	
	private NewPointModel model;
	
	public NewPointController(NewPointModel model) {
		this.model = model;
	}
	
	public void setPoint(int i, String value) {
		AbstractColumn col = model.getType().getNormalizableColumns().get(i);
		if(col.toString().startsWith("Number")) {
			NumberColumn ncol = (NumberColumn) col;
			double val = Double.valueOf(value);
			if(val > ncol.max.doubleValue()) value = ""+ncol.max.doubleValue();
			if(val < ncol.min.doubleValue()) value = ""+ncol.min.doubleValue();
		}
		model.setData(i, value);
	}
	
	public void loadPoint() throws IOException, TypeNotRegisteredException {
		model.loadPoint();
	}
}
