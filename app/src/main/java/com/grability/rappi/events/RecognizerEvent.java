package com.grability.rappi.events;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RecognizerEvent {
    private final List<String> result;
    private final boolean error;

    public RecognizerEvent(List<String> result, boolean error) {
        this.result = result;
        this.error = error;
    }

    public List<String> getResult() {
        return result;
    }

    public boolean hasError() {
        return error;
    }
}
