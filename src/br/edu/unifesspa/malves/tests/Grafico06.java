package br.edu.unifesspa.malves.tests;

import java.util.HashMap;

import org.jfree.ui.RefineryUtilities;

import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.transportnetwork.DRABF;
import br.edu.unifesspa.malves.transportnetwork.DRACF;
import br.edu.unifesspa.malves.transportnetwork.FemtoBB;
import br.edu.unifesspa.malves.transportnetwork.FemtoCB;
import br.edu.unifesspa.malves.util.GraficoLinha;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-06-12
 *
 */
public class Grafico06 {

	public Grafico06(){

		HashMap<String, double[]> tco = new HashMap<String, double[]>();

		//DRA-CF
		DRACF dracf = null;
		double[] tcoDRACF = new double[Environment.densidadeDeUsuarios.length];

		//DRA-BF
		DRABF drabf = null;
		double[] tcoDRABF = new double[Environment.densidadeDeUsuarios.length];

		//Femto-CB
		FemtoCB femtocb = null;
		double[] tcoFemtoCB = new double[Environment.densidadeDeUsuarios.length];

		//Femto-BB
		FemtoBB femtobb = null;
		double[] tcoFemtoBB = new double[Environment.densidadeDeUsuarios.length];

		for (int i=0; i<Environment.densidadeDeUsuarios.length; i++){		
			dracf = new DRACF(Environment.defaultSolarIrradiance, Environment.densidadeDeUsuarios[i]);
			tcoDRACF[i] = (dracf.estatisticas[2]/(Environment.densidadeDeUsuarios[i]*Environment.area))/Environment.anos.length;
			
			drabf = new DRABF(Environment.defaultSolarIrradiance, Environment.densidadeDeUsuarios[i]);
			tcoDRABF[i] = (drabf.estatisticas[2]/(Environment.densidadeDeUsuarios[i]*Environment.area))/Environment.anos.length;

			femtocb = new FemtoCB(Environment.defaultSolarIrradiance, Environment.densidadeDeUsuarios[i]);
			tcoFemtoCB[i] = (femtocb.estatisticas[2]/(Environment.densidadeDeUsuarios[i]*Environment.area))/Environment.anos.length;

			femtobb = new FemtoBB(Environment.defaultSolarIrradiance, Environment.densidadeDeUsuarios[i]);
			tcoFemtoBB[i] = (femtobb.estatisticas[2]/(Environment.densidadeDeUsuarios[i]*Environment.area))/Environment.anos.length;
		}
		
		tco.put("DRA-CF", tcoDRACF);
		tco.put("DRA-BF", tcoDRABF);
		tco.put("Femto-CB", tcoFemtoCB);
		tco.put("Femto-BB", tcoFemtoBB);
		
		double[] escalaX = {816.890595009597, 5200.0};
		double[] escalaY = {45.0,110.0};

		GraficoLinha demo2 = new GraficoLinha(this.getClass().getSimpleName(), 
				"", 
				"Densidade de Usuário (ρ) [Usuários/km²]", 
				"Custo por usuário [Reais/usuário/ano]", 
				tco, 
				Environment.densidadeDeUsuarios,
				escalaX,
				escalaY);
		demo2.pack();
		RefineryUtilities.centerFrameOnScreen(demo2);
		demo2.setVisible(true);
	}
}