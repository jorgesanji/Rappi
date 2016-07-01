package com.grability.rappi.model.dataacess.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public final class RestFeed {

    @SerializedName("author")
    public final Author author;

    @SerializedName("entry")
    public final RestEntry entry[];

    @SerializedName("updated")
    public final Updated updated;

    @SerializedName("rights")
    public final Rights rights;

    @SerializedName("title")
    public final Title title;

    @SerializedName("icon")
    public final Icon icon;

    @SerializedName("link")
    public final Link link[];

    @SerializedName("id")
    public final Id id;

    public RestFeed(Author author, RestEntry[] entry, Updated updated, Rights rights, Title title, Icon icon, Link[] link, Id id){
        this.author = author;
        this.entry = entry;
        this.updated = updated;
        this.rights = rights;
        this.title = title;
        this.icon = icon;
        this.link = link;
        this.id = id;
    }

    public static final class Author {

        @SerializedName("name")
        public final Name name;

        @SerializedName("uri")
        public final Uri uri;

        public Author(Name name, Uri uri){
            this.name = name;
            this.uri = uri;
        }

        public static final class Name {

            @SerializedName("label")
            public final String label;

            public Name(String label){
                this.label = label;
            }
        }

        public static final class Uri {

            @SerializedName("label")
            public final String label;

            public Uri(String label){
                this.label = label;
            }
        }
    }



    public static final class Updated {

        @SerializedName("label")
        public final String label;

        public Updated(String label){
            this.label = label;
        }
    }

    public static final class Rights {

        @SerializedName("label")
        public final String label;

        public Rights(String label){
            this.label = label;
        }
    }

    public static final class Title {

        @SerializedName("label")
        public final String label;

        public Title(String label){
            this.label = label;
        }
    }

    public static final class Icon {

        @SerializedName("label")
        public final String label;

        public Icon(String label){
            this.label = label;
        }
    }

    public static final class Link {

        @SerializedName("attributes")
        public final Attributes attributes;

        public Link(Attributes attributes){
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("rel")
            public final String rel;

            @SerializedName("href")
            public final String href;

            public Attributes(String rel, String href){
                this.rel = rel;
                this.href = href;
            }
        }
    }

    public static final class Id {

        @SerializedName("label")
        public final String label;

        public Id(String label){
            this.label = label;
        }
    }

    public Author getAuthor() {
        return author;
    }

    public RestEntry[] getEntry() {
        return entry;
    }

    public Updated getUpdated() {
        return updated;
    }

    public Rights getRights() {
        return rights;
    }

    public Title getTitle() {
        return title;
    }

    public Icon getIcon() {
        return icon;
    }

    public Link[] getLink() {
        return link;
    }

    public Id getId() {
        return id;
    }
}