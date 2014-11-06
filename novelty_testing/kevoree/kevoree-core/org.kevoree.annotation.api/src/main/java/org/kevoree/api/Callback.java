package org.kevoree.api;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 19/11/2013
 * Time: 00:16
 */
public interface Callback<T> {

    /**
     *
     * @param servicePath path of the port who answered the call
     *                    (can be null if the channel used do not implement this feature)
     * @param chanName name of the channel who forwarded the answer
     * @param result content of the answer
     */
    public void onSuccess(String servicePath, String chanName, T result);

    /**
     *
     * @param exception
     */
    public void onError(Throwable exception);

}
