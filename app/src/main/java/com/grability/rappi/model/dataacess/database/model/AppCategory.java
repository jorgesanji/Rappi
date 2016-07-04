package com.grability.rappi.model.dataacess.database.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by jorgesanmartin on 3/7/16.
 */
public class AppCategory extends RealmObject implements Serializable {

    private String id;
    private String term;
    private String scheme;
    private String label;

    public AppCategory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
