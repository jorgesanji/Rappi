package com.grability.rappi.model.dataacess.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public final class RestEntry {

    @SerializedName("im:name")
    public final Name name;

    @SerializedName("im:image")
    public final Image image[];

    @SerializedName("summary")
    public final Summary summary;

    @SerializedName("im:price")
    public final Price price;

    @SerializedName("im:contentType")
    public final ContentType contentType;

    @SerializedName("rights")
    public final Rights rights;

    @SerializedName("title")
    public final Title title;

    @SerializedName("link")
    public final Link link;

    @SerializedName("id")
    public final Id id;

    @SerializedName("im:artist")
    public final Artist artist;

    @SerializedName("category")
    public final Category category;

    @SerializedName("im:releaseDate")
    public final ReleaseDate releaseDate;

    public RestEntry(Name name, Image[] image, Summary summary, Price price, ContentType contentType, Rights rights, Title title, Link link, Id id, Artist artist, Category category, ReleaseDate releaseDate) {
        this.name = name;
        this.image = image;
        this.summary = summary;
        this.price = price;
        this.contentType = contentType;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.id = id;
        this.artist = artist;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    public static final class Name {

        @SerializedName("label")
        public final String label;

        public Name(String label) {
            this.label = label;
        }
    }

    public static final class Image {

        @SerializedName("label")
        public final String label;

        @SerializedName("attributes")
        public final Attributes attributes;

        public Image(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("height")
            public final String height;

            public Attributes(String height) {
                this.height = height;
            }
        }
    }

    public static final class Summary {

        @SerializedName("label")
        public final String label;

        public Summary(String label) {
            this.label = label;
        }
    }

    public static final class Price {

        @SerializedName("label")
        public final String label;

        @SerializedName("attributes")
        public final Attributes attributes;

        public Price(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("amount")
            public final String amount;

            @SerializedName("currency")
            public final String currency;

            public Attributes(String amount, String currency) {
                this.amount = amount;
                this.currency = currency;
            }
        }
    }

    public static final class ContentType {

        @SerializedName("attributes")
        public final Attributes attributes;

        public ContentType(Attributes attributes) {
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("term")
            public final String term;

            @SerializedName("label")
            public final String label;

            public Attributes(String term, String label) {
                this.term = term;
                this.label = label;
            }
        }
    }

    public static final class Rights {

        @SerializedName("label")
        public final String label;

        public Rights(String label) {
            this.label = label;
        }
    }

    public static final class Title {

        @SerializedName("label")
        public final String label;

        public Title(String label) {
            this.label = label;
        }
    }

    public static final class Link {

        @SerializedName("attributes")
        public final Attributes attributes;

        public Link(Attributes attributes) {
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("rel")
            public final String rel;

            @SerializedName("type")
            public final String type;

            @SerializedName("href")
            public final String href;

            public Attributes(String rel, String type, String href) {
                this.rel = rel;
                this.type = type;
                this.href = href;
            }
        }
    }

    public static final class Id {

        @SerializedName("label")
        public final String label;

        @SerializedName("attributes")
        public final Attributes attributes;

        public Id(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("im:id")
            public final String id;

            @SerializedName("im:bundleId")
            public final String bundleId;

            public Attributes(String id, String bundleId) {
                this.id = id;
                this.bundleId = bundleId;
            }
        }
    }

    public static final class Artist {

        @SerializedName("label")
        public final String label;

        @SerializedName("attributes")
        public final Attributes attributes;

        public Artist(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("href")
            public final String href;

            public Attributes(String href) {
                this.href = href;
            }
        }
    }

    public static final class Category {

        @SerializedName("attributes")
        public final Attributes attributes;

        public Category(Attributes attributes) {
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("im:id")
            public final String id;

            @SerializedName("term")
            public final String term;

            @SerializedName("scheme")
            public final String scheme;

            @SerializedName("label")
            public final String label;

            public Attributes(String id, String term, String scheme, String label) {
                this.id = id;
                this.term = term;
                this.scheme = scheme;
                this.label = label;
            }
        }
    }

    public static final class ReleaseDate {

        @SerializedName("label")
        public final String label;

        @SerializedName("attributes")
        public final Attributes attributes;

        public ReleaseDate(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public static final class Attributes {

            @SerializedName("label")
            public final String label;

            public Attributes(String label) {
                this.label = label;
            }
        }
    }
}
