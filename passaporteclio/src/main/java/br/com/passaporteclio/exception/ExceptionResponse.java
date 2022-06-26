package br.com.passaporteclio.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;


public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private OffsetDateTime timestamp;
	private String mensagem;
	private String descricao;
	
	public ExceptionResponse() {
	
	}
	
	public ExceptionResponse(OffsetDateTime timestamp, String mensagem, String descricao) {
		this.timestamp = timestamp;
		this.mensagem = mensagem;
		this.descricao = descricao;
	}
	public OffsetDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descricao, mensagem, timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionResponse other = (ExceptionResponse) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(mensagem, other.mensagem)
				&& Objects.equals(timestamp, other.timestamp);
	}
}