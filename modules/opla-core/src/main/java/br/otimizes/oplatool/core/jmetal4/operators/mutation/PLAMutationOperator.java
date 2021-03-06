package br.otimizes.oplatool.core.jmetal4.operators.mutation;

import br.otimizes.oplatool.core.jmetal4.core.Solution;
import br.otimizes.oplatool.architecture.representation.Architecture;
import br.otimizes.oplatool.common.Configuration;
import br.otimizes.oplatool.common.exceptions.JMException;
import br.otimizes.oplatool.core.jmetal4.operators.MutationOperators;
import br.otimizes.oplatool.core.jmetal4.util.PseudoRandom;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PLA feature mutation operator that call another mutation operators included in FeatureMutationOperators enum
 */
public class PLAMutationOperator extends Mutation {

    private static final long serialVersionUID = 9039316729379302747L;
    static Logger LOGGER = LogManager.getLogger(PLAMutationOperator.class.getName());
    private int threshold;
    private int thresholdLc;

    private Double probability = null;
    private List<String> operators;

    public PLAMutationOperator(HashMap<String, Object> parameters, List<String> operators) {
        super(parameters);
        this.operators = operators;

        if (parameters.get("probability") != null) {
            probability = (Double) parameters.get("probability");
        }
    }

    public PLAMutationOperator(Map<String, Object> parameters) {
        super(parameters);
        if (parameters.get("probability") != null) {
            probability = (Double) parameters.get("probability");
        }
    }

    public void doMutation(double probability, Solution solution) throws Exception {
        String scope = "sameComponent"; //allLevels

        int r = PseudoRandom.randInt(0, this.operators.size() - 1);
        HashMap<Integer, String> operatorMap = new HashMap<>();
        for (int i = 0; i < this.operators.size(); i++)
            operatorMap.put(i, this.operators.get(i));
        MutationOperators selectedOperator = MutationOperators.valueOf(operatorMap.get(r));
        selectedOperator.getOperator().execute(parameters_, solution, scope);
    }


    public Object execute(Object object) throws Exception {
        Solution solution = (Solution) object;
        Double probability = (Double) getParameter("probability");

        if (probability == null) {
            Configuration.logger_.severe("FeatureMutation.execute: probability not specified");
            java.lang.Class<String> cls = java.lang.String.class;
            String name = cls.getName();
            throw new JMException("Exception in " + name + ".execute()");
        }
        this.doMutation(this.probability, solution);

        if (!MutationUtils.isValidSolution(((Architecture) solution.getDecisionVariables()[0]))) {
            Architecture clone;
            clone = ((Architecture) solution.getDecisionVariables()[0]).deepClone();
            solution.getDecisionVariables()[0] = clone;
        }

        return solution;
    }

    public List<String> getOperators() {
        return operators;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getThresholdLc() {
        return thresholdLc;
    }

    public void setThresholdLc(int thresholdLc) {
        this.thresholdLc = thresholdLc;
    }
}