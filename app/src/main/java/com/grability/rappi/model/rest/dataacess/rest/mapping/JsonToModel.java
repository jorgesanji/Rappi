package com.grability.rappi.model.rest.dataacess.rest.mapping;

import android.util.Log;

import com.cronosgroup.core.rest.RestError;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.grability.rappi.utils.AsyncLoader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgesanmartin on 1/8/16.
 */
public class JsonToModel {

    public interface Listener<T ,E> {
        void mappingFinished(T response);

        void errorMagpping(E error);
    }

    private Gson gson;

    public JsonToModel(Gson gson) {
        this.gson = gson;
    }

    // Mapping Json to Model


    public String mappingToJson(Object object) {
        String result = "";
        try {
            result = getGson().toJson(object);
        } catch (JsonSyntaxException exception) {
            Log.d("mappingToStringJson", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingToStringJson", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingToStringJson", "Error mapping list");
        } finally {
            return result;
        }
    }

    public String mappingToStringJson(List jsonObject) {
        String result = "";
        try {
            result = getGson().toJson(jsonObject);
        } catch (JsonSyntaxException exception) {
            Log.d("mappingToStringJson", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingToStringJson", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingToStringJson", "Error mapping list");
        } finally {
            return result;
        }
    }

    public List mappingFromStrinToStringList(String jsonObject) {

        final Type type = new ListParameterizedType(String.class);

        List result = new ArrayList();
        try {
            result = getGson().fromJson(jsonObject, type);
        } catch (JsonSyntaxException exception) {
            Log.d("mappingToStringJson", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingToStringJson", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingToStringJson", "Error mapping list");
        } finally {
            return result;
        }
    }

    public Object mappingJson(JsonElement jsonObject, Class clazz) {
        Object result = null;
        try {
            result = getGson().fromJson(jsonObject, clazz);
        } catch (JsonSyntaxException exception) {
            Log.d("mappingJsonr", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingJson", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingJson", "Error mapping list");
        } finally {
            return result;
        }
    }

    public Object mappingJson(JsonObject jsonObject, Class clazz) {
        Object result = null;
        try {
            result = getGson().fromJson(jsonObject, clazz);
        } catch (JsonSyntaxException exception) {
            Log.d("mappingJson", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingJson", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingJson", "Error mapping list");
        } finally {
            return result;
        }
    }

    public Object mappingJson(String stringJson, Class clazz) {
        Object result = null;
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(stringJson);
            result = mappingJson(jsonObject, clazz);
        } catch (JsonSyntaxException exception) {
            Log.d("Gson error", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingListInBackground", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingListInBackground", "Error mapping list");
        } finally {
            return result;
        }
    }

    public Object mappingJson(String stringJson, String rootKey, Class clazz) {
        Object result = null;
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(stringJson);
            result = mappingJson(jsonObject.get(rootKey), clazz);
        } catch (JsonSyntaxException exception) {
            Log.d("Gson error", "Mapping error");
        } catch (JsonParseException exception2) {
            Log.d("mappingListInBackground", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingListInBackground", "Error mapping list");
        } finally {
            return result;
        }
    }

    public Object mappingJson(String json, String rootKey, Type type) {
        Object result = null;
        JsonParser jsonParser = new JsonParser();
        try {
            JsonObject jsonObject = (JsonObject) jsonParser.parse(json);
            result = getGson().fromJson((rootKey != null) ? jsonObject.getAsJsonArray(rootKey) : jsonObject, type);
        } catch (JsonSyntaxException exception) {
            Log.d("mappingListInBackground", "Error mapping list");
        } catch (JsonParseException exception2) {
            Log.d("mappingListInBackground", "Error mapping list");
        } catch (ClassCastException exception2) {
            Log.d("mappingListInBackground", "Error mapping list");
        } finally {
            return result;
        }
    }

    public void mappingJsonInBackground(final String jsonObject, final Class clazz, final Listener callback) {

        AsyncLoader loader = new AsyncLoader() {
            @Override
            public Object doInBackground() {
                return mappingJson(jsonObject, clazz);
            }

            @Override
            public void postProcess(Object result) {
                if (result != null) {
                    callback.mappingFinished(result);
                } else {
                    callback.errorMagpping(RestError.getMappingError());
                }
            }
        };

        loader.start();
    }

    public void mappingJsonInBackground(final JsonObject jsonObject, final Class clazz, final Listener callback) {

        AsyncLoader loader = new AsyncLoader() {
            @Override
            public Object doInBackground() {
                return mappingJson(jsonObject, clazz);
            }

            @Override
            public void postProcess(Object result) {
                if (result != null) {
                    callback.mappingFinished(result);
                } else {
                    callback.errorMagpping(RestError.getMappingError());
                }
            }
        };

        loader.start();
    }

    public <T> void mappingListInBackground(final String json, final String rootPath, final Class<T> model, final Listener callback) {

        final Type type = new ListParameterizedType(model);

        AsyncLoader loader = new AsyncLoader() {
            @Override
            public Object doInBackground() {
                return mappingJson(json, rootPath, type);
            }

            @Override
            public void postProcess(Object result) {
                if (callback != null) {
                    if (result != null) {
                        callback.mappingFinished(result);
                    } else {
                        callback.errorMagpping(RestError.getMappingError());
                    }
                }
            }
        };

        loader.start();
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public static class StringDeserializer implements JsonDeserializer<String> {
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            String response = json.toString();
            if (json.isJsonObject()) {
                return response;
            }

            return response.replace("\"", "");
        }
    }

    private static class ListParameterizedType implements ParameterizedType {

        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

    }
}
