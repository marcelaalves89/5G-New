package br.edu.unifesspa.malves.transportnetwork;

import br.edu.unifesspa.malves.backhaul.PON;
import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.Femto;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-07-12
 *
 */
public class FemtoBB extends FemtoPVBased {
	
	/**
	 * Super constructor call and initializing values
	 * 
	 * @param incidenciaSolar
	 * @param densidadeDeUsuarios
	 */
	public FemtoBB(double incidenciaSolar, double densidadeDeUsuarios){
		super(incidenciaSolar, densidadeDeUsuarios);
		this.name = "Macro+Femto-BB Architecture";
		this.getConsumoMacro();
		this.getConsumoFemto();
		this.getPotenciaDeGeracao();
		this.getTCO();
		this.getEstatisticas();
		//this.debug();
	}

	/**
	 * Calculating the Power Consumption of Femto-BB Only (KWH)
	 */
	public void getConsumoFemto(){		
		double[][] temp = Util.getProductByInteger(super.numeroDeFemtos, Femto.power);
		double temp2 = 0;
		for (int i=0; i<temp.length; i++){
			if (super.numeroDeFemtos[i][0] != 0)
				temp2 = Environment.numeroMaximoPredios*PON.numOfONUPerBuilding*(PON.onuPower+(PON.oltPower/(Femto.numOfWaveLengthsPerFemto*PON.numOfWaveLenghtsPerTWDM)));
			this.potenciaFemtoOnly[i][0] = temp[i][0] + temp2;			
		}		
		Util.converterEmKWH(this.potenciaFemtoOnly);
		this.potenciaTotal = Util.getSoma(this.potenciaMacroOnly,this.potenciaFemtoOnly);
	}

	/**
	 * Print values for Debug 
	 */
	public void debug(){
		
	}
}