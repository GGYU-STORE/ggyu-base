package com.ggyu.base.global.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JsonUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    // public static Gson GSON = new Gson();
    public static Gson GSON = getBaseBuilder().create();
    public static Gson TYPED_GSON = getTypedBuilder().create();

    public static String toJson(Object obj) {
        return TYPED_GSON.toJson(obj);
    }

    public static GsonBuilder getBaseBuilder() {
        return new GsonBuilder().setPrettyPrinting()
                .setDateFormat(DATE_FORMAT)
                .setLenient()
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE);
    }


    public static GsonBuilder getTypedBuilder() {
        return getBaseBuilder()
                .registerTypeAdapter(LocalDateTime.class, new GsonDateConverter.LocalDateTimeConverter())
                .registerTypeAdapter(LocalDate.class, new GsonDateConverter.LocalDateConverter())
                .registerTypeAdapter(LocalTime.class, new GsonDateConverter.LocalTimeConverter());
    }
}
