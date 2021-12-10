package tk.knowitallgn.ToS.DataTypes;

import java.io.Serializable;

public class PlayerFile implements Serializable{

	private static final long serialVersionUID = 8358597393093918859L;
	private Boolean hasRead = false;
	private Boolean hasAgreed = false;

	public PlayerFile setRead(Boolean read){
		hasRead = read;
		return this;
	}
	
	public Boolean hasRead(){
		return hasRead;
	}
	
	public PlayerFile setAgreed(Boolean agreed){
		hasAgreed = agreed;
		return this;
	}
	
	public Boolean hasAgreed(){
		return hasAgreed;
	}
}
