package fr.groupeh6.sae.views.listeners;

import fr.groupeh6.sae.controllers.NewPointController;

public abstract class AbstractListener {
	
	protected NewPointController npc;
	protected int i;
	
	public AbstractListener(NewPointController npc, int i) {
		this.npc = npc;
		this.i = i;
	}
	
	public void setValue(int i, String value) {
		npc.setPoint(i, value);
	}

}
