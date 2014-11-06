package org.kevoree.core.impl;

import org.kevoree.api.telemetry.TelemetryEvent;

/**
 * Created by duke on 8/7/14.
 */
public class TelemetryEventImpl implements TelemetryEvent {

    private String origin;
    private String message;
    private Type type;
    private String stack;
    private Long timestamp;

    public static TelemetryEvent build(String origin, Type type, String message, String stack) {
        TelemetryEventImpl e = new TelemetryEventImpl();
        e.origin = origin;
        e.type = type;
        e.message = message;
        e.stack = stack;
        e.timestamp = getTime();
        return e;
    }

    // HACK BEGIN: ensures timestamp uniqueness with a microsecond precision.
    private static final long ONE_THOUSAND = 1000L;
    private static long lastTime = 0L;
    private synchronized static long getTime() {
        long current = System.currentTimeMillis() * ONE_THOUSAND;
        if(current > lastTime) {
            lastTime = current;
        } else {
            lastTime++;
        }
        return lastTime;
    }
    // HACK END

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Long timestamp() {
        return timestamp;
    }

    @Override
    public String origin() {
        return origin;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String stack() {
        return stack;
    }

    @Override
    public String toJSON() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("\"origin\":\"");
        builder.append(origin);
        builder.append("\",\n");
        builder.append("\"type\":\"");
        builder.append(type);
        builder.append("\",\n");
        builder.append("\"message\":");
        if(message.length() > 0) {
            boolean asString = message.charAt(0) != '{' && message.charAt(0) != '[';
            if (asString) {
                builder.append("\"");
                builder.append(message.replace("\n", "\\n").replace("\t", "\\t").replace("\"", "\\\""));
                builder.append("\"");
            } else {
                builder.append(message.replace("\n", "\\n").replace("\t", "\\t"));
            }
            builder.append(",\n");
        } else {
            builder.append("\"\",\n");
        }
        builder.append("\"timestamp\":\"");
        builder.append(timestamp);
        builder.append("\",\n");
        builder.append("\"stack\":\"");
        if(stack.length() > 0) {
            builder.append(stack.replace("\n", "\\n").replace("\t", "\\t").replace("\"", "\\\""));
        }
        builder.append("\"\n");
        builder.append("}\n");
        return builder.toString();
    }
}
