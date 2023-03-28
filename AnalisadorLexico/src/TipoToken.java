
public enum TipoToken {
	
	//Elencando os tipos de Token possíveis
	
	PCDec, PCProg, PCInt, PCReal, PCLer, PCImprimir, PCSe, PCEntao, PCEnqto, PCIni, PCFim,
	OpAritMult, OpAritDiv, OpAritSoma, OpAritSub,
	OpRelMenor, OpRelMenorIgual, OpRelMaior, OpRelMaiorIgual, OpRelIgual, OpRelDif,
	OpBoolE, OpBoolOu,
	Delim,
	Atrib,
	AbrePar, FechaPar,
	Var,
	NumInt,
	NumReal,
	Cadeia,
	NaoPertence, // Quando não cai em nenhuma das anteriores
	NULL // Pra alertar o fim de arquivo
	
}//enum