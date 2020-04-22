package br.ufpr.dinf.gres.core.jmetal4.metrics.concernDrivenMetrics.concernDiffusion;

import br.ufpr.dinf.gres.architecture.representation.Architecture;
import br.ufpr.dinf.gres.core.jmetal4.metrics.ObjectiveFunctionImplementation;

public class CDAI extends ObjectiveFunctionImplementation {

    public CDAI(Architecture architecture) {
        super(architecture);
        double sumCDAI = 0.0;

        CDAIConcerns cdai = new CDAIConcerns(architecture);
        for (CDAIResult c : cdai.getResults()) {
            sumCDAI += c.getElements().size();
        }
        this.setResults(sumCDAI);
    }

}