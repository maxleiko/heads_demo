package fr.inria.diverse.noveltytesting;

import fr.inria.diverse.noveltytesting.model.Method;
import fr.inria.diverse.noveltytesting.model.MethodOutput;
import org.kevoree.api.Callback;

/**
 * Created by leiko on 05/11/14.
 */
public class MethodCallback implements Callback {

    private Method method;

    public MethodCallback(Method m) {
        this.method = m;
    }

    @Override
    public void onSuccess(String servicePath, String chanName, Object result) {
        MethodOutput output = new MethodOutput();
        output.setReturnVal(result);
        System.out.println("answer "+result+" ("+chanName+" <-> "+servicePath+")");
        method.addMethodOutput(chanName, output);
    }

    @Override
    public void onError(Throwable exception) {
        // TODO handle error ?
    }
}
