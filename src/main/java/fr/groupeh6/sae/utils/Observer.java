package fr.groupeh6.sae.utils;

public interface Observer {
	
	public void update(Subject subj);
    public void update(Subject subj, Object data);

}
