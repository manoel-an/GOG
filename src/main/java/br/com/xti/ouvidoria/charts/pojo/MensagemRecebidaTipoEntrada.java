package br.com.xti.ouvidoria.charts.pojo;

public class MensagemRecebidaTipoEntrada extends AbstractChartResults implements IResultadoGrafico {
	
	private String tipo;
	
	private Integer quantidadeMsgs;
	
	private Double porcentagem;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidadeMsgs() {
		return quantidadeMsgs;
	}

	public void setQuantidadeMsgs(Integer quantidadeMsgs) {
		this.quantidadeMsgs = quantidadeMsgs;
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	@Override
	public int getQuantidade() {
		return 0;
	}

}
