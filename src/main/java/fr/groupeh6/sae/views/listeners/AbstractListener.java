package fr.groupeh6.sae.views.listeners;

import fr.groupeh6.sae.model.NewPointModel;

public abstract class AbstractListener {
	
	protected NewPointModel npc;
	protected int i;
	
	public AbstractListener(NewPointModel npc, int i) {
		this.npc = npc;
		this.i = i;
	}
	
	public void setValue(int i, String value) {
		npc.setData(i, value);
	}

}
