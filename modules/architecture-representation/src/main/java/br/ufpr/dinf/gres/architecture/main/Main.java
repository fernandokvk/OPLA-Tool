package br.ufpr.dinf.gres.architecture.main;

import br.ufpr.dinf.gres.architecture.builders.ArchitectureBuilderPapyrus;
import br.ufpr.dinf.gres.architecture.exceptions.ModelIncompleteException;
import br.ufpr.dinf.gres.architecture.exceptions.ModelNotFoundException;
import br.ufpr.dinf.gres.architecture.exceptions.SMartyProfileNotAppliedToModelExcepetion;
import br.ufpr.dinf.gres.architecture.representation.Architecture;

public class Main {

    /**
     * @param args
     * @throws Exception
     * @throws ModelIncompleteException
     * @throws ModelNotFoundException
     * @throws SMartyProfileNotAppliedToModelExcepetion
     */
    public static void main(String[] args) throws Exception {


//		if((args.length != 2) || ("".equals(args[0].trim()))){
//			System.out.println("\tUsage:");
//			System.out.println("\t\t java -jar arq.java <path_to_model.uml> <model_output_name>");
//			System.exit(0);
//		}

        long init = System.currentTimeMillis();

        String path = "/Users/elf/mestrado/sourcesMestrado/br.ufpr.dinf.gres.arquitetura/src/test/java/resources/agmfinal/agm.uml";
        Architecture a = new ArchitectureBuilderPapyrus().create(path);

        GenerateArchitecture generate = new GenerateArchitecture();
        generate.generate(a, "testeGiovani123");

        System.out.println("Working....");

        long end = System.currentTimeMillis();
        System.out.println("Time:" + (end - init) + " Millis");


    }

}
