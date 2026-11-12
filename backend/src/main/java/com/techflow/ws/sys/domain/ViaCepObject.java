package com.techflow.ws.sys.domain;

import org.springframework.web.client.RestTemplate;

public class ViaCepObject {
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private Integer cityId;
	private Integer stateId;
	private String ddd;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public ViaCepObject getCep(String cep) {
		 RestTemplate restTemplate = new RestTemplate();
	     String fooResourceUrl = "https://viacep.com.br/ws/" + cep.replaceAll("[^0-9]", "") + "/json/";
	     ViaCepObject address = restTemplate.getForObject(fooResourceUrl, ViaCepObject.class);
	     
	     this.bairro = address.getBairro();
	     this.cep = address.getCep();
	     this.logradouro = address.getLogradouro();
	     this.complemento = address.getComplemento();
	     this.localidade = address.getLocalidade();
	     this.ibge = address.getIbge();
	     this.gia = address.getGia();
	     this.uf = address.getUf();
	     this.ddd = address.getDdd();
	     
	     return this;

	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	
}
