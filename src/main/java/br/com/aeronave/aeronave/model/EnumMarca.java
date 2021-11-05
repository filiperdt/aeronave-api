package br.com.aeronave.aeronave.model;

public enum EnumMarca {
	BOMBARDIER, BOEING, AIRBUS, EMBRAER, GE_AVIATION;
	
	@Override
	public String toString() {
		return Character.toUpperCase(name().charAt(0)) + name().toLowerCase().substring(1);
	}
}
