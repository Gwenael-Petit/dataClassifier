package fr.groupeh6.sae.model.utils;

public interface Observer {
	
	public void update(Subject subj);
    public void update(Subject subj, Object data);

}
