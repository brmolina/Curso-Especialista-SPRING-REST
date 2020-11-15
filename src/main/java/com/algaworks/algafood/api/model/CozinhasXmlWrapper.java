package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("cozinhas")
public class CozinhasXmlWrapper {

	@JsonProperty("cozinha")
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinha;
}
