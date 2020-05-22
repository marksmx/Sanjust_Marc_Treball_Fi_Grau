package MODEL;

public class ComandaCl {
	String estatComanda;
	String numComanda;
	String idProducte;
	String data;
	String hores;
	String base;
	String iva;
	String total;
	String idEmpresa;

	public ComandaCl (String estatComanda, String numComanda, String idProducte, String data, String hores, String base, String iva, String total, String idEmpresa) {
		this.estatComanda = estatComanda;
		this.numComanda = numComanda;
		this.idProducte = idProducte;
		this.data = data;
		this.hores = hores;
		this.base = base;
		this.iva = iva;
		this.total = total;
		this.idEmpresa = idEmpresa;
	}


	public void setEstatComanda(String estatComanda) {
		this.estatComanda = estatComanda;
	}


	public void setNumComanda(String numComanda) {
		this.numComanda = numComanda;
	}


	public void setIdProducte(String idProducte) {
		this.idProducte = idProducte;
	}


	public void setData(String data) {
		this.data = data;
	}


	public void setHores(String hores) {
		this.hores = hores;
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


	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getEstatComanda() {
		return estatComanda;
	}


	public String getNumComanda() {
		return numComanda;
	}


	public String getIdProducte() {
		return idProducte;
	}


	public String getData() {
		return data;
	}


	public String getHores() {
		return hores;
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


	public String getIdEmpresa() {
		return idEmpresa;
	}


	@Override
	public String toString() {
		return "ComandaCl [estatComanda=" + estatComanda + ", numComanda=" + numComanda + ", idProducte=" + idProducte
				+ ", data=" + data + ", hores=" + hores + ", base=" + base + ", iva=" + iva + ", total=" + total
				+ ", idEmpresa=" + idEmpresa + "]";
	}

}
