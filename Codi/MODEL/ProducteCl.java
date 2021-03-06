package MODEL;


/** DECLARACIÓ DE VARIABLES, GETTERS I SETTERS DE LA CLASE PRODUCTE */

public class ProducteCl {
	
	private String element;
	private String unitats;
	private String base;
	private String iva;
	private String total;
	private String idProducte;
	
	public ProducteCl(String element, String unitats, String base, String iva, String total, String idProducte) {
		
		this.element = element;
		this.unitats = unitats;
		this.base = base;
		this.iva = iva;
		this.total = total;
		this.idProducte = idProducte;
		
	}

	public void setElement(String element) {
		
		this.element = element;
		
	}

	public void setUnitats(String unitats) {
		
		this.unitats = unitats;
		
	}

	public void setBase(String base) {
		
		this.base = base;
		
	}

	public void setIva(String iva) {
		
		this.iva = iva;
		
	}

	public void setTotal(String total) {
		
		this.total = total;
		
	}

	public void setIdProducte(String idProducte) {
		
		this.idProducte = idProducte;
		
	}

	public String getElement() {
		
		return element;
		
	}

	public String getUnitats() {
		
		return unitats;
		
	}

	public String getBase() {
		
		return base;
		
	}

	public String getIva() {
		
		return iva;
		
	}

	public String getTotal() {
		
		return total;
		
	}

	public String getIdProducte() {
		
		return idProducte;
		
	}
	
}
