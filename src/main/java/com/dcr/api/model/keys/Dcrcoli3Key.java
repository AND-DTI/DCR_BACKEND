package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class Dcrcoli3Key {
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String dcre;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer numsubcomp;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer numcomp;
}
