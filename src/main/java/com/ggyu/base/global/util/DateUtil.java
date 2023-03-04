package com.ggyu.base.global.util;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

/**
 * 날짜 관련 유틸 클래스
 *
 */
public class DateUtil {
    public static final DateTimeFormatter SERIALIZATION_LOCAL_TIME;
    public static final DateTimeFormatter SERIALIZATION_LOCAL_DATE_TIME;
    public static final DateTimeFormatter DESERIALIZATION_LOCAL_TIME;
    public static final DateTimeFormatter DESERIALIZATION_LOCAL_DATE_TIME;

    static {
        SERIALIZATION_LOCAL_TIME = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .optionalStart()
                .appendFraction(NANO_OF_SECOND, 3, 9, true)
                // .toFormatter(ResolverStyle.STRICT, null);
                .toFormatter(Locale.getDefault());
    }

    static {
        SERIALIZATION_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(ISO_LOCAL_DATE)
                .appendLiteral('T')
                .append(SERIALIZATION_LOCAL_TIME)
                .toFormatter(Locale.getDefault());
    }

    static {
        DESERIALIZATION_LOCAL_TIME = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .optionalStart()
                .appendFraction(NANO_OF_SECOND, 0, 9, true)
                // .toFormatter(ResolverStyle.STRICT, null);
                .toFormatter(Locale.getDefault());
    }

    static {
        DESERIALIZATION_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(ISO_LOCAL_DATE)
                .appendLiteral('T')
                .append(DESERIALIZATION_LOCAL_TIME)
                .toFormatter(Locale.getDefault());
    }

    /**
     * LocalDateTime 타입을 Date 타입으로 변환한다.
     *
     * @param date
     * @return
     */
    public static Date toDate(final LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 타입을 LocalDateTime 타입으로 변환한다.
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * start와 end 차이를 초단위로 반환한다.
     *
     * @param dateTimeStrStart
     * @param dateTimeStrEnd
     * @param form
     * @return
     */
    public static Long getDuration(final String dateTimeStrStart, final String dateTimeStrEnd, final FORM form) {
        if (StringUtils.isEmpty(dateTimeStrStart) || StringUtils.isEmpty(dateTimeStrEnd) || form == null) {
            return null;
        }
        final LocalDateTime start = parseDateTime(dateTimeStrStart, form);
        final LocalDateTime end = parseDateTime(dateTimeStrEnd, form);
        return ChronoUnit.SECONDS.between(start, end);
    }

    /**
     * start와 end 차이를 초단위로 반환한다.
     *
     * @param start
     * @param end
     * @return
     */
    public static long getDuration(final LocalDateTime start, final LocalDateTime end) {
        return ChronoUnit.SECONDS.between(start, end);
    }

    /**
     * start와 end 차이를 밀리초단위로 반환한다.
     *
     * @param start
     * @param end
     * @return
     */
    public static long getDurationByMillis(final LocalDateTime start, final LocalDateTime end) {
        return ChronoUnit.MILLIS.between(start, end);
    }

    /**
     * LocalDateTime 과 Date 객체가 같은지 비교한다.
     *
     * @param localDateTime
     * @param date
     * @return
     */
    public static boolean isEqual(final LocalDateTime localDateTime, final Date date) {
        return localDateTime.equals(toLocalDateTime(date));
    }

    ;

    /**
     * LocalDateTime 과 Date 객체가 같은지 초단위로 변환해서 비교한다. (millisecond 부분은 무시하고 비교한다.)
     *
     * @param localDateTime
     * @param date
     * @return
     */
    public static boolean isEqualBySecondUnit(final LocalDateTime localDateTime, final Date date) {
        return ChronoUnit.SECONDS.between(localDateTime, toLocalDateTime(date)) == 0;
    }

    /**
     * LocalDateTime 에 밀리세컨드 만큼의 시간을 더한 LocalDateTime 객체를 반환한다.
     *
     * @param localDateTime
     * @param millis
     * @return
     */
    public static LocalDateTime plus(final LocalDateTime localDateTime, final long millis) {
        return localDateTime.plus(millis, ChronoField.MILLI_OF_DAY.getBaseUnit());
    }

    /**
     * LocalDateTime에 하루를 더해서 반환한다.
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getNextDay(final LocalDateTime localDateTime) {
        return localDateTime.plusDays(1L);
    }

    /**
     * 해당날의 시작 시간정보를 포함한 일시 정보로 변환한다.
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime getStartOfDay(final LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * 해당날의 마지막 시간정보를 포함한 일시 정보로 변환한다.
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime getEndOfDay(final LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 날짜 형식의 문자열을 파싱한다.
     *
     * @param dateStr
     * @param form
     * @return
     */
    public static LocalDate parseDate(final String dateStr, final FORM form) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        if (dateStr.length() != form.getPattern().length()) {
            throw new RuntimeException(String.format("%s > %s", dateStr, form.getPattern()));
        }

        return LocalDate.parse(dateStr, form.getDateTimeFormatter());
    }

    /**
     * 일시 형식의 문자열을 파싱한다.
     *
     * @param dateTimeStr
     * @param form
     * @return
     */
    public static LocalDateTime parseDateTime(final String dateTimeStr, final FORM form) {
        if (StringUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        if (dateTimeStr.length() != form.getPattern().length()) {
            throw new RuntimeException(String.format("%s > %s", dateTimeStr, form.getPattern()));
        }

        return LocalDateTime.parse(dateTimeStr, form.getDateTimeFormatter());
    }

    public static String staticHourFormat(final LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return FORM.TIME_FLATTEN.format(date);
    }

    public static String staticDateFormat(final LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return FORM.DATE_FLATTEN.format(date);
    }

    public static String staticDateHourFormat(final LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return FORM.DATE_FLATTEN_HMS.format(date);
    }

    /**
     * 오늘 날짜를 통계포맷으로 반환한다.
     *
     * @return
     */
    public static String getFormattedStaticDateOfToday() {
        return staticDateFormat(LocalDateTime.now());
    }

    /**
     * 포맷 문자열
     */
    @Getter
    public enum FORM {
        DATE_BASIC(PATTERNS.DATE_BASIC),
        DATETIME_BASIC(PATTERNS.DATETIME_BASIC),
        DATETIME_DB_DEFAULT(PATTERNS.DATETIME_DB_DEFAULT),
        DATE_MONTH(PATTERNS.DATE_MONTH),
        DATE_DETAIL(PATTERNS.DATE_DETAIL),
        DATE_FLATTEN(PATTERNS.DATE_FLATTEN),
        DATE_FLATTEN_MONTH(PATTERNS.DATE_FLATTEN_MONTH),
        DATE_FLATTEN_HMS(PATTERNS.DATE_FLATTEN_HMS),
        DATE_FLATTEN_DETAIL(PATTERNS.DATE_FLATTEN_DETAIL),
        DATE_ELASTICSEARCH(PATTERNS.DATE_ELASTICSEARCH),
        TIME_BASIC(PATTERNS.TIME_BASIC),
        TIME_BASIC_WITH_SECOND(PATTERNS.TIME_BASIC_WITH_SECOND),
        TIME_FLATTEN(PATTERNS.TIME_FLATTEN),
        TIME_FLATTEN_WITH_SECOND(PATTERNS.TIME_FLATTEN_WITH_SECOND),
        TIME_H(PATTERNS.TIME_H),
        DAY_KOREAN(PATTERNS.DAY_KOREAN, Locale.KOREAN),
        DATE_DB_LONG(PATTERNS.DATE_DB_LONG),
        DATE_YEAR(PATTERNS.DATE_YEAR),
        ;

        private String pattern;
        private DateTimeFormatter dateTimeFormatter;

        FORM(String pattern) {
            this.pattern = pattern;
            this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        }

        FORM(String pattern, Locale locale) {
            this.pattern = pattern;
            this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, locale);
        }

        public String getPattern() {
            return this.pattern;
        }

        public DateTimeFormatter getDateTimeFormatter() {
            return this.dateTimeFormatter;
        }

        public String format(final Date date) {
            if (date == null) {
                return null;
            }
            return dateTimeFormatter.format(toLocalDateTime(date));
        }

        public String format(final LocalDate date) {
            if (date == null) {
                return null;
            }
            return dateTimeFormatter.format(date);
        }

        public String format(final LocalDateTime date) {
            if (date == null) {
                return null;
            }
            return dateTimeFormatter.format(date);
        }

        public static class PATTERNS {
            public static final String DATE_BASIC = "yyyy-MM-dd";
            public static final String DATETIME_BASIC = "yyyy-MM-dd HH:mm:ss";
            public static final String DATETIME_DB_DEFAULT = "yyyyMMddHHmmss";
            public static final String DATE_MONTH = "yyyy-MM";
            public static final String DATE_DETAIL = "yyyy-MM-dd HH:mm:ss.SSS";
            public static final String DATE_FLATTEN = "yyyyMMdd";
            public static final String DATE_FLATTEN_MONTH = "yyyyMM";
            public static final String DATE_FLATTEN_HMS = "yyyyMMddHHmmss";
            public static final String DATE_FLATTEN_DETAIL = "yyyyMMddHHmmssSSS";
            public static final String DATE_ELASTICSEARCH = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
            public static final String TIME_BASIC = "HH:mm";
            public static final String TIME_BASIC_WITH_SECOND = "HH:mm:ss";
            public static final String TIME_FLATTEN = "HHmm";
            public static final String TIME_FLATTEN_WITH_SECOND = "HHmmss";
            public static final String TIME_H = "H";
            public static final String DAY_KOREAN = "yyyy-MM-dd (E)";
            public static final String DATE_DB_LONG = "yyyyMMddHHmmssSSSSSS";
            public static final String DATE_YEAR = "yyyy";
        }
    }
}
