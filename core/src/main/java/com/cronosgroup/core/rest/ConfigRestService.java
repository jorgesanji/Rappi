package com.cronosgroup.core.rest;

import android.content.Context;

/**
 * Created by jorgesanmartin on 15/10/15.
 */
public interface ConfigRestService {

    enum Mode {
        DEVELOP("dev"),
        PRODUCTION("prod"),
        QA("qa");

        private final String mode;

        Mode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }
    }

    Context getContext();

    String getUrlByMode(Mode type);

    String getCurrentUrl();

    Mode getCurrentMode();
}
