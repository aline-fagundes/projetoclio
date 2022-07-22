package br.com.passaporteclio.exception;

public class ExceptionResponseDto {

	private String campo;
	private String erro;

	public ExceptionResponseDto(String campo, String erro) {
			this.campo = campo;
			this.erro = erro;
		}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
