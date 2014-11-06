package fr.inria.diverse.noveltytesting;

import fr.inria.diverse.noveltytesting.model.Interface;
import fr.inria.diverse.noveltytesting.model.Method;
import fr.inria.diverse.noveltytesting.model.MethodOutput;
import fr.inria.diverse.noveltytesting.model.Population;
import fr.inria.diverse.noveltytesting.noveltyengine.NoveltyEngine;
import fr.inria.diverse.noveltytesting.noveltyengine.NoveltyEngineImpl;
import fr.inria.diverse.noveltytesting.visitor.FileOutputVisitor;
import org.kevoree.annotation.*;
import org.kevoree.api.Callback;
import org.kevoree.api.Port;

import java.util.HashMap;
import java.util.Map;

@ComponentType
public class FuncTester {

    @Param(defaultValue = "2")
    private int populationSize;

    @Param(defaultValue = "2")
    private int genCycles;

    @Output
    private Port inverse;

    @Output
    private Port echo;

    @Output
    private Port sum;

    @Output
    private Port concat;

    private Population pop;

    @Start
    public void start() throws Exception {
        NoveltyEngine engine = new NoveltyEngineImpl();
        engine.setExclusionPattern("__hx_");
        pop = engine.generatePopulation("fr.inria.diverse.noveltytesting.samples.Functions", populationSize);
        engine.generateData(pop);

        pop.accept(new FileOutputVisitor());

        for (Interface i : pop.getInterfaces()) {
            for (Method m : i.getMethods()) {
                switch (m.getName()) {
                    case "inverse":
                        inverse.call(m.getParametersValue(), new MethodCallback(m));
                        break;

                    case "echo":
                        echo.call(m.getParametersValue(), new MethodCallback(m));
                        break;

                    case "sum":
                        sum.call(m.getParametersValue(), new MethodCallback(m));
                        break;

                    case "concat":
                        concat.call(m.getParametersValue(), new MethodCallback(m));
                        break;
                }
            }
        }



//        for (int i=0; i < this.genCycles; i++) {
//            engine.executeMethods(pop);
//            pop.accept(visitor);
//            engine.evaluate(pop);
//            engine.geneticProcess(pop);
//            pop.accept(visitor);
//            engine.generateData(pop);
//        }
    }

    @Input
    public void cmdInput(String cmd) {
        if (cmd != null && cmd.equals("write")) {
            System.out.println("Writing logs...");
            pop.accept(new FileOutputVisitor());
        }
    }
}