package com.android.training.basefeature.utils;

import android.text.TextUtils;

import com.android.training.basefeature.log.LogManager;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author suetming ( suetming.ma@gmail.com )
 */
public class FormatUtil {

    public static final String TAG = FormatUtil.class.getSimpleName();
    /**
     * 十
     **/
    public static final int TEN = 10;
    /**
     * 百
     **/
    public static final int HUNDRED = 100;
    /**
     * 千
     **/
    public static final int THOUSAND = HUNDRED * 10;
    /**
     * 万
     **/
    public static final int TEN_THOUSAND = THOUSAND * 10;
    /**
     * 十万
     **/
    public static final int ONE_HUNDRED_THOUSAND = THOUSAND * 100;
    /**
     * 百万
     **/
    public static final int MILLION = TEN_THOUSAND * 100;
    /**
     * 千万
     **/
    public static final int TEN_MILLION = MILLION * 10;
    /**
     * 亿
     **/
    public static final int ONE_HUNDRED_MILLION = MILLION * 100;
    /**
     * 十亿
     **/
    public static final int BILLION = ONE_HUNDRED_MILLION * 10;
    /**
     * 百亿
     **/
    public static final int TEN_BILLION = BILLION * 10;

    /**
     * 单位：十
     */
    public static final String UNIT_STR_TEN = "十";
    /**
     * 单位：百
     */
    public static final String UNIT_STR_HUNDRED = "百";
    /**
     * 单位：千
     */
    public static final String UNIT_STR_THOUSAND = "千";
    /**
     * 单位：万
     */
    public static final String UNIT_STR_TEN_THOUSAND = "万";
    /**
     * 单位：十万
     */
    public static final String UNIT_STR_ONE_HUNDRED_THOUSAND = "十万";
    /**
     * 单位：百万
     */
    public static final String UNIT_STR_MILLION = "百万";
    /**
     * 单位：千万
     */
    public static final String UNIT_STR_TEN_MILLION = "千万";
    /**
     * 单位：亿
     */
    public static final String UNIT_STR_ONE_HUNDRED_MILLION = "亿";
    /**
     * 单位：十亿
     */
    public static final String UNIT_STR_BILLION = "十亿";
    /**
     * 单位：百亿
     */
    public static final String UNIT_STR_TEN_BILLION = "百亿";
    /**
     * 单位：千亿
     */
    public static final String UNIT_STR_ONE_HUNDRED_BILLION = "千亿";
    /**
     * 单位：万亿
     */
    public static final String UNIT_STR_TEN_THOUSAND_BILLION = "万亿";

    /**
     * 毫秒
     **/
    public static final long TIME_MILLISECOND = 1l;
    /**
     * 秒
     **/
    public static final long TIME_SECOND = TIME_MILLISECOND * 1000;
    /**
     * 分
     **/
    public static final long TIME_MINUTE = TIME_SECOND * 60;
    /**
     * 小时
     **/
    public static final long TIME_HOUR = TIME_MINUTE * 60;
    /**
     * 天
     **/
    public static final long TIME_DAY = TIME_HOUR * 24;
    /**
     * 星期
     **/
    public static final long TIME_WEEK = TIME_DAY * 7;

    public static final Locale DEFAULT_LOCALE = Locale.CHINA;

    /**
     * 分割符
     */
    public static final String DEFAULT_SEPERATOR = "/";

    public static String simpleDateformat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yy年M月d日 HH:mm");
        return format.format(date);
    }

    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static String simpleDateformat2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }

    public static String simpleDateformat3(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String simpleDateformat4(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yy年M月d日 HH:mm:ss");
        return format.format(date);
    }

    public static String simpleDateformat5(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 资产日历里边使用的时候，日历空间存储日历的格式不存储多余的0，如2016-4-1
     *
     * @param date
     * @return
     */
    public static String simpleDateFormat6(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        return format.format(date);
    }

    public static String simpleDateformat6(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yy/M/d HH:mm");
        return format.format(date);
    }

    public static String simpleDateTimeformat(Date date) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String formatLong(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date(time));
    }

    public static String simpleDateTimeformatWithoutTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String formatToHoursAndSecond(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(milliseconds));
    }

    public static String formatToMounthAndDay(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(new Date(milliseconds));
    }

    public static String formatToMounthAndDayChina(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(new Date(milliseconds));
    }

    /**
     * 超过一天显示 ＊月＊日
     * 当天显示13:30
     *
     * @param millisecondes
     * @return
     */
    public static String formaToMonthDayOrToday(long millisecondes) {
        Calendar calendarOpen = Calendar.getInstance();
        calendarOpen.setTimeInMillis(millisecondes);
        int month = calendarOpen.get(Calendar.MONTH) + 1;
        int day = calendarOpen.get(Calendar.DAY_OF_MONTH);

        int hour = calendarOpen.get(Calendar.HOUR_OF_DAY);
        int minute = calendarOpen.get(Calendar.MINUTE);

        if (sameDay(calendarOpen, Calendar.getInstance())) {
            String hourStr = hour + "";
            if (hour < 10) {
                hourStr = "0" + hourStr;
            }

            if (minute < 10) {
                return hourStr + ":" + "0" + minute;
            } else {
                return hourStr + ":" + minute;
            }
        } else {
            return month + "月" + day + "日";
        }

    }

    private static boolean sameDay(Calendar cal1, Calendar cal2) {
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH) + 1;
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);

        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH) + 1;
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;
    }

    public static String formatToHoursAndSecond(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String formatClockTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    public static int formatTimeToYear(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return calendar.get(Calendar.YEAR);
    }

    public static String simpleDateTimeformatWithoutYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutTimeCN(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutTimeCNithoutDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutTimeCNithoutDayAndYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM月");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutTimeCNithoutDayAndYear2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutTimeCNithoutMontnAndYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return format.format(date);
    }

    public static String simpleDateTimeformatWithoutTimeCNithoutDayAndMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年");
        return format.format(date);
    }

    public static String formatHourOfOut(long milliseconds, int timeOut) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = new Date();

            Date d2 = new Date(milliseconds);
            long diff = d1.getTime() - d2.getTime();
            long time = timeOut - diff / 1000 / 60 / 60;
            // long time=timeOut*1000*60*60-diff;
            long year = time / 24 / 30 / 365;
            long mounth = (time - year * 24 * 30 * 365) / 24 / 30;
            long days = (time - year * 24 * 30 * 365) / 24;
            long hours = (time - (days * 24));
            long minutes = (time * 60 - (hours * 60));
            if (year > 0) {
                if (mounth >= 6) {
                    year = year + 1;
                }
                return year + "年";
            } else {
                //				if (mounth > 0) {
                //					if (days >= 15) {
                //						mounth = mounth + 1;
                //					}
                //					return mounth + "个月";
                //				} else {
                if (days > 0) {
                    // if (hours >= 12) {
                    // days = days + 1;
                    // }
                    return days + "天";
                } else {
                    if (hours > 0) {
                        // if (minutes >= 30) {
                        // hours = hours + 1;
                        // }
                        if (hours > 1) {
                            return (hours - 1) + "小时";

                        }
                        return hours + "小时";
                    } else {

                        if (minutes > 0) {
                            return 1 + "小时";
                        }
                    }
                }

                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0 + "小时";
    }

    public static String formatToDayOrHours(long intervalTime) {

        long day = intervalTime / (24 * 60 * 60 * 1000);

        long hour = (intervalTime % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);

        StringBuilder displayTime = new StringBuilder();
        if (day > 0) {
            displayTime.append(day).append("天");

        }
        if (hour > 11) {
            displayTime.append(hour).append("小时");

        }

        return displayTime.toString();

    }

    public static String formatToDayOrHoursFromHours(int hours) {
        String result = "";
        if (hours < 24) {
            result = hours + "小时";
        } else {
            result = hours / 24 + "天";
        }
        return result;

    }

    static public String formatMillis(long val) {

        if (val > 3600000 * 24) {
            return String.format("%1$d天%2$d小时%3d分钟%4$d秒", (val / 3600000 / 24), (val % (3600000 * 24)) / 3600000, (val % 3600000) / 60000, (val % 60000) / 1000);
        } else if (val <= 3600000 * 24 && val > 3600000) {
            return String.format("%1$d小时%2d分钟%3$d秒", val / 3600000, ((val % 3600000) / 60000), ((val % 60000) / 1000));
        } else if (val <= 3600000 && val > 60000) {
            return String.format("%1d分钟%2$d秒", val / 60000, ((val % 60000) / 1000));
        } else if (val <= 60000) {
            return String.format("%1$d秒", (val / 1000));
        }
        return "";
    }

    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; // 天
        if (strDay.startsWith("0")) {
            strDay = strDay.substring(1, strDay.length());
        }
        String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
        String strSecond = second < 10 ? "0" + second : "" + second;// 秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;// 毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        if (day > 0) {
            return strDay + "天" + strHour + "小时";
        } else if (hour > 0) {
            return strHour + "小时" + strMinute + "分钟";
        } else if (minute > 0) {
            return strMinute + "分钟" + strSecond + "秒";
        } else if (second > 0) {
            return strSecond + "秒";
        } else {
            return 0 + "秒";
        }

    }

    public static String formatClockTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        if (day > 0) {
            hour = hour + day * 24;
        }

        String strDay = day < 10 ? "0" + day : "" + day; // 天
        if (strDay.startsWith("0")) {
            strDay = strDay.substring(1, strDay.length());
        }
        String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
        String strSecond = second < 10 ? "0" + second : "" + second;// 秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;// 毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strHour + ":" + strMinute + ":" + strSecond;

    }

    static public String formatMillis2(long val) {

        if (val > 3600000 * 24) {
            return String.format("%1$2d天%2$2d小时%3$2d分", (val / 3600000 / 24), (val % (3600000 * 24)) / 3600000, (val % 3600000) / 60000);
        } else if (val <= 3600000 * 24 && val > 3600000) {
            return String.format("%1$2d小时%2$2d分", val / 3600000, ((val % 3600000) / 60000));
        } else if (val <= 3600000 && val > 60000) {
            return String.format("%1$2d分%2$2d秒", val / 60000, (val % 60000) / 1000);
        } else if (val <= 60000) {
            return String.format("%1$2d秒", (val / 1000));
        }
        return "";
    }

    static public String formatMillis3(long val) {

        if (val > 3600000 * 24) {
            return String.format("%1$2d天%2$2d小时%3$2d分%4$2d秒", (val / 3600000 / 24), (val % (3600000 * 24)) / 3600000, (val % 3600000) / 60000, (val % 60000) / 1000);
        } else if (val <= 3600000 * 24 && val > 3600000) {
            return String.format("%1$2d小时%2$2d分%3$2d秒", val / 3600000, ((val % 3600000) / 60000), ((val % 60000) / 1000));
        } else if (val <= 3600000 && val > 60000) {
            return String.format("%1$2d分%2$2d秒", val / 60000, (val % 60000) / 1000);
        } else if (val <= 60000) {
            return String.format("%1$2d秒", (val / 1000));
        }
        return "";
    }

    static public String formatMillis4(long val) {

        if (val > 3600000 * 24) {
            return String.format("%1$2d天%2$2d小时", (val / 3600000 / 24), (val % (3600000 * 24)) / 3600000, (val % 3600000) / 60000, (val % 60000) / 1000);
        } else if (val <= 3600000 * 24 && val > 3600000) {
            return String.format("%1$2d小时%2$2d分钟", val / 3600000, ((val % 3600000) / 60000), ((val % 60000) / 1000));
        } else if (val <= 3600000 && val > 60000) {
            return String.format("%1$2d分钟", val / 60000, (val % 60000) / 1000);
        } else if (val <= 60000) {
            return String.format("%1$2d秒", (val / 1000));
        }
        return "";
    }

    static public String formatMillisToDays(long val) {

        if (val > 3600000 * 24) {
            return String.format("剩余%1$d天", (val / 3600000 / 24));
        } else {
            return "不到1天";
        }
    }

    static public String getFormatCardNum(String str) {
        String out = "";
        int n = 0;
        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                builder.append(str.charAt(i));
                n++;
                if (n % 4 == 0) {
                    builder.append(" ");
                }
            }
            out = builder.toString();
        }
        return out;
    }

    public static String getCurrencyCommontFormat(double value) {
        String currency;

        // 大于亿元
        if (value >= 100000000) {
            if ((value % 100000000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f亿", value / 100000000);
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f亿", value / 100000000);
            }
        }
        // 大于千万
        else if (value >= 10000000) {
            if ((value % 10000000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f万", value / 10000);
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以上
        else if (value >= 10000) {
            if ((value % 10000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f万", value / 10000);
                if (currency.contains(".00")) {
                    currency = currency.substring(0, currency.length() - 4) + "万＋";
                } else if (currency.endsWith("0万")) {
                    currency = currency.substring(0, currency.length() - 2) + "万";
                }
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        } else if (value >= 1000) {
            currency = String.format(DEFAULT_LOCALE, "%1$.1f万", value / 10000);
        }
        // 1万以内
        else {
            currency = FormatUtil.getFormat2DecimalNoSeperator().format(value) + "元";
            if (currency.endsWith(".00元")) {
                currency = currency.replace(".00元", "元");
            } else if (currency.endsWith(".0元")) {
                currency = currency.replace(".0元", "元");
            }
        }

        return currency;
    }

    public static String getCurrencyCommontFormatNew(double value) {
        String currency;

        // 大于亿元
        if (value >= 100000000) {
            if ((value % 100000000) > 0) {
                currency = getCurrencyString(value / 100000000) + "亿";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f亿", value / 100000000);
            }
        }
        // 大于千万
        else if (value >= 10000000) {
            if ((value % 10000000) > 0) {
                currency = getCurrencyString(value / 10000) + "万";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以上
        else if (value >= 10000) {
            if ((value % 10000) > 0) {
                currency = getCurrencyString(value / 10000) + "万";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以内
        else {
            currency = String.format(DEFAULT_LOCALE, "%1$.0f元", value);
        }

        return currency;
    }

    public static String getCurrencyString(double value) {
        String currency = String.valueOf(value);
        String cu, last = null;
        if (currency.contains(".")) {
            last = currency.subSequence(currency.indexOf("."), currency.length()).toString();
        }
        if (last != null) {
            if (last.length() > 1 && last.length() > 2) {
                cu = currency.subSequence(0, currency.indexOf(".") + 3).toString();
            } else if (last.length() > 1 && last.length() == 2) {
                if (last.equals(".0")) {
                    cu = currency.subSequence(0, currency.indexOf(".")).toString();
                } else {
                    cu = currency.subSequence(0, currency.indexOf(".") + 2).toString();
                }
            } else {
                cu = currency.subSequence(0, currency.indexOf(".")).toString();
            }
        } else {
            cu = currency.subSequence(0, currency.indexOf(".")).toString();
        }

        return cu;
    }

    public static String getCurrencyProbablyFormat(double value) {
        String currency;

        // 大于亿元
        if (value >= 100000000) {
            if ((value % 100000000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f", value / 100000000);
                currency = getCurreny(value / 100000000, currency);
                currency = currency.substring(0, currency.indexOf(".")) + "亿+";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f亿", value / 100000000);
            }
        }
        // 大于千万
        else if (value >= 10000000) {
            if ((value % 10000000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f", value / 10000);
                currency = getCurreny(value / 10000, currency);
                currency = currency.substring(0, currency.indexOf(".")) + "万+";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以上
        else if (value >= 10000) {
            if ((value % 10000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f", value / 10000);
                currency = getCurreny(value / 10000, currency);
                // if (currency.contains(".00")) {
                // currency = currency.substring(0, currency.length())
                // + "万";
                // }
                currency = currency.substring(0, currency.indexOf(".")) + "万+";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以内
        else if (value < 10000 && value > 100) {
            if (String.valueOf(value).contains(".00") || String.valueOf(value).contains(".0")) {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f元", value);
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.1f元", value);
            }
        } else if (value < 10000 && value < 100) {
            if (String.valueOf(value).contains(".00") || String.valueOf(value).contains(".0")) {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f元", value);
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f元", value);
            }
        } else {
            if (String.valueOf(value).contains(".00") || String.valueOf(value).contains(".0")) {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f元", value);
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f元", value);
            }
        }

        return currency;
    }

    public static String getCurrencyTransferFormat(double value) {
        String currency;

        // 大于亿元
        if (value >= 100000000) {
            if ((value % 100000000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f", value / 100000000);
                currency = getCurreny(value / 100000000, currency);
                currency = currency.substring(0, currency.indexOf(".")) + "亿+";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f亿", value / 100000000);
            }
        }
        // 大于千万
        else if (value >= 10000000) {
            if ((value % 10000000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f", value / 10000);
                currency = getCurreny(value / 10000, currency);
                currency = currency.substring(0, currency.indexOf(".")) + "万+";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以上
        else if (value >= 10000) {
            if ((value % 10000) > 0) {
                currency = String.format(DEFAULT_LOCALE, "%1$.2f", value / 10000);
                currency = getCurreny(value / 10000, currency);
                // if (currency.contains(".00")) {
                // currency = currency.substring(0, currency.length())
                // + "万";
                // }
                currency = currency.substring(0, currency.indexOf(".")) + "万+";
            } else {
                currency = String.format(DEFAULT_LOCALE, "%1$.0f万", value / 10000);
            }
        }
        // 1万以内
        else {
            currency = FormatUtil.getFormat2DecimalNoSeperator().format(value) + "元";
        }

        return currency;
    }

    public static String getCurreny(double value, String curreny) {
        double cu = Double.parseDouble(curreny);
        if (cu - value > 0) {
            curreny = String.valueOf(cu);
        }
        return curreny;
    }

    public static String getIntString(String data) {
        String val;
        double real = Double.parseDouble(data);
        if (!data.equals("0.0") && data.contains(".")) {
            Double temp = Double.parseDouble(data.substring(0, data.indexOf(".")).replace(".", ""));
            if (real - temp >= 0.5) {
                val = String.valueOf((int) (temp + 1));
            } else {
                val = String.valueOf((int) (temp + 0));
            }

        } else {
            val = String.valueOf((int) real);
        }
        return val;
    }

    public static String getIntString2(String data) {
        String val;
        double real = Double.parseDouble(data);
        if (!data.equals("0.0") && data.contains(".")) {
            Double temp = Double.parseDouble(data.substring(0, data.indexOf(".")).replace(".", ""));
            if (real - temp >= 0.5) {
                val = String.valueOf((int) (temp + 1));
            } else {
                val = String.valueOf((int) (temp + 0));
            }

        } else {
            val = String.valueOf((int) real);
        }
        return val;
    }

    public static String getMaskMobile(String mobile) {
        String newMobile = mobile;

        if (TextUtils.isEmpty(mobile)) {
            return null;
        }
        if (mobile.length() < 11) {
            return mobile;
        }
        newMobile = mobile.substring(0, 3);
        newMobile += "****";
        newMobile += mobile.substring(7);

        return newMobile;
    }

    /**
     * 使用{@link #getFormat2DecimalWithSeperator()}来本地化，始终使用Locale.CHINA来格式化。
     *
     * @param str
     * @return
     */
    static public String getDecimalFormatWithTwoPoint(String str) {
        DecimalFormat fmt = getFormat2DecimalWithSeperator();
        String outStr = null;
        double d;
        try {
            d = Double.parseDouble(str);
            outStr = fmt.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 使用{@link #getFormatWithNoPoint()}来本地化，使用使用Locale.CHINA来格式化
     *
     * @param str
     * @return
     */
    static public String getDecimalFormatWithNoPoint(String str) {
        DecimalFormat fmt = getFormatWithNoPoint();
        String outStr = null;
        double d;
        try {
            d = Double.parseDouble(str);
            outStr = fmt.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 使用Locale.CHINA 格式化字符串为double,保留两位有效数字。不要修改，需要其他模式，自行增加。
     *
     * @param str
     * @return
     */
    static public String getCurrency(String str) {
        DecimalFormat n = getFormat2DecimalNoSeperator();
        double d;
        String outStr = null;
        try {
            d = Double.parseDouble(str);
            outStr = n.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outStr;
    }

    /**
     * 使用Locale.CHINA 格式化金额，不带货币符号。其实就是始终保留两位小数的人民币数值。<br>
     *
     * @param d
     * @return
     */
    static public String getCurrency(double d) {
        DecimalFormat df = getFormat2DecimalWithSeperator();
        return df.format(d);
    }

    /**
     * 使用Locale.CHINA 格式化double成没有小数位的字符串。
     *
     * @param
     * @return
     */
    static public String getCurrencyWithoutPoint(double d) {
        DecimalFormat df = getFormatWithNoPoint();
        return df.format(d);
    }

    /**
     * 格式化double数据为格式："###,###,###,###.##" 小数点后保留两位，如果double小数点后多余两位，会丢失精度。<br>
     * 例如：1322623.354 转化成 1,322,623.35<br>
     *
     * @param number
     * @return
     */
    public static String formatTo2DecimalWithSeperator(double number) {
        if (number > 0) {
            return getFormat2DecimalWithSeperator().format(number);
        } else {
            return "0.00";
        }
    }

    /**
     * 解析数字字符串为小数点后两位的double,如果源字符串小数点后大于两位，将四舍五入，丢失精度<br>
     * 例如：1,322,623.35456 转化成 1322623.35<br>
     * 如果传入的字符串非法，不能转换成double，则返回-1；<br>
     *
     * @param doubleString
     * @return 返回转化后的double数据，如果解析失败，返回-1
     */
    public static double parseFrom2DecimalWithSeperator(String doubleString) {
        try {
            return getFormat2DecimalWithSeperator().parse(doubleString).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 使用Locale.getDefault()的DecimalFormat<br>
     * 1.使用这个DecimalFormat来格式化double成String<br>
     * 2.使用这个DecimalFromat来解析String成double<br>
     * 备注：<br>
     * <code>同一个数据,使用同一个DecimalFormat可以实现来回转换（format和parse），不用考虑Locale</code><br>
     *
     * @return DecimalFormat
     */
    public static DecimalFormat getFormat2DecimalWithSeperator() {
        DecimalFormat decimalFormat = null;
        NumberFormat format = NumberFormat.getInstance(Locale.CHINA);
        if (format instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) format;
            decimalFormat.applyPattern("###,###,###,##0.00");
        }
        return decimalFormat;
    }

    public static DecimalFormat getFormatIntegerWithSeperator() {
        DecimalFormat decimalFormat = null;
        NumberFormat format = NumberFormat.getInstance(Locale.CHINA);
        if (format instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) format;
            decimalFormat.applyPattern("###,###,###,###");
        }
        return decimalFormat;
    }

    /**
     * 不要修改Pattern,会影响现有代码中所使用到的所有地方。
     *
     * @return
     */
    public static DecimalFormat getFormat2DecimalNoSeperator() {
        DecimalFormat decimalFormat = null;
        NumberFormat format = NumberFormat.getInstance(Locale.CHINA);
        if (format instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) format;
            decimalFormat.applyPattern("###########0.00");
        }
        return decimalFormat;
    }

    /**
     * 始终保留小数点后两位
     *
     * @param number
     * @return
     */
    public static String Format2DecimalNoSeperator(double number) {
        return getFormat2DecimalNoSeperator().format(number);
    }

    /**
     * 不要修改Pattern,格式化成整数
     * <p>
     * 输出结果类型：50,000,000
     *
     * @return
     */
    public static DecimalFormat getFormatWithNoPoint() {
        DecimalFormat decimalFormat = null;
        NumberFormat format = NumberFormat.getInstance(Locale.CHINA);
        if (format instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) format;
            decimalFormat.applyPattern("##,###,###,###,##0");
        }
        return decimalFormat;
    }

    /**
     * 保留最多两位小数位，可能只有一位小数，也可能一位小数也没有。第三位小数四舍五入<br>
     * 例如<br>
     * 999999.453 转化成 999999.45<br>
     * 999999.456 转化成 999999.46<br>
     * 999999.056 转化成 999999.06<br>
     * 999999.106 转化成 999999.11<br>
     * 999999.10 转化成 999999.1<br>
     * 999999.01 转化成 999999.01<br>
     * 999999.00 转化成 999999<br>
     * 999999.0 转化成 999999<br>
     * 999999 转化成 999999<br>
     *
     * @return
     */
    public static DecimalFormat getFormatMaxTwoPointOrNone() {
        NumberFormat nf = NumberFormat.getInstance(Locale.CHINA);
        DecimalFormat decimalFormat = null;
        if (nf instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) nf;
            decimalFormat.applyPattern("############.##");
        }
        return decimalFormat;
    }


    /**
     * 带千分位符，0-2位小数
     *
     * @return
     */
    public static DecimalFormat getFormatMaxTwoPointOrNoneWithSeperator() {
        NumberFormat nf = NumberFormat.getInstance(Locale.CHINA);
        DecimalFormat decimalFormat = null;
        if (nf instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) nf;
            decimalFormat.applyPattern("###,###,###,##0.##");
        }
        return decimalFormat;
    }

    /**
     * 不带千分位符
     *
     * @return
     */
    public static DecimalFormat getFormatInteger() {
        NumberFormat nf = NumberFormat.getInstance(Locale.CHINA);
        DecimalFormat decimalFormat = null;
        if (nf instanceof DecimalFormat) {
            decimalFormat = (DecimalFormat) nf;
            decimalFormat.applyPattern("############");
        }
        return decimalFormat;
    }

    public static String formatLastCardNum(String num) {
        String card = num.substring(num.length() - 4, num.length());
        return card;
    }

    /**
     * 格式化姓名  *明/**明
     *
     * @param name
     * @return
     */
    public static String formatLastName(String name) {
        String card = name.substring(name.length() - 1, name.length());
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < name.length() - 1; i++) {
            builder.append("*");
        }
        builder.append(card);
        return builder.toString();
    }

    /**
     * 返回以“万”为单位的金额字符串,不带千分位符号<br>
     * 最多保留两位小数，小于100的数据将被忽略。<br>
     * <p>
     * <b><code>该格式化器，适合用于数值大于100，并且个位和十位都是0的数据。</code></b>
     * <p>
     * 输入		输出
     * 50000    5万
     * 55000    5.5万
     * 55500    5.55万
     * 1000		0.1万
     * 1100		0.11万
     * <p>
     * 有误差的数值如下：
     * 1110		0.11万
     * 1111		0.11万
     * 111		0.01万
     * 110		0.01万
     * 10		0万
     * 1		0万
     *
     * @param value 输入值
     * @return 返回以“万”为单位的字符串
     */
    public static String getCurrencyWithUnitTenThousand(double value) {
        StringBuilder sb = new StringBuilder();
        double rest = value / TEN_THOUSAND;
        return sb.append(getFormatMaxTwoPointOrNone().format(rest)).append(UNIT_STR_TEN_THOUSAND).toString();
    }

    /**
     * 获取字符串中末尾N位
     *
     * @param mobile
     * @param n
     * @return
     */
    public static String getLastNNumberString(String mobile, int n) {
        if (mobile != null && n > 0) {
            if (mobile.length() >= n) {
                return mobile.substring(mobile.length() - n);
            }
        }
        return "";
    }

    /**
     * 格式化 130***********7654
     *
     * @param num
     * @return
     */
    public static String getIdNumber(String num) {
        if (num.length() > 7) {
            StringBuilder builder = new StringBuilder("");
            builder.append(num.substring(0, 3));
            for (int i = 0; i < num.length() - 7; i++) {
                builder.append("*");
            }
            builder.append(num.substring(num.length() - 4, num.length()));
            return builder.toString();
        }
        return num;
    }

    /**
     * 格式化 ***********7654
     *
     * @param num
     * @return
     */
    public static String getLast4IdNumber(String num) {
        if (num.length() > 4) {
            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < num.length() - 4; i++) {
                builder.append("*");
            }
            builder.append(num.substring(num.length() - 4, num.length()));
            return builder.toString();
        }
        return num;
    }

    /**
     * 格式化，参数化字符串
     *
     * @param formatString
     * @param params
     * @return
     */
    public static String fromatPlaceholder(String formatString, Object... params) {
        return String.format(Locale.CHINA, formatString, params);
    }

    /**
     * 用于拼接字符串，两个字符串之间只允许出现一个seperator
     *
     * @param prefix    前面的字符串
     * @param sufix     后面的字符串
     * @param seperator 拼接符
     * @return 返回拼接后的字符串
     */
    public static String connectWithSingleSeperator(String prefix, String sufix, String seperator, boolean mayStartWithSeperator) {
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(prefix)) {
            if (!TextUtils.isEmpty(sufix)) {
                if (sufix.startsWith(seperator)) {
                    if (mayStartWithSeperator) {
                        stringBuilder.append(sufix);
                    } else {
                        //去除第一个seperator
                        stringBuilder.append(sufix.substring(1));
                    }
                } else {
                    stringBuilder.append(sufix);
                }
            }
        } else {
            if (TextUtils.isEmpty(sufix)) {
                if (prefix.startsWith(seperator)) {
                    if (mayStartWithSeperator) {
                        stringBuilder.append(prefix);
                    } else {
                        //去除第一个seperator
                        stringBuilder.append(prefix.substring(1));
                    }
                } else {
                    stringBuilder.append(prefix);
                }
            } else {
                if (prefix.startsWith(seperator)) {
                    if (mayStartWithSeperator) {
                        stringBuilder.append(prefix);
                    } else {
                        if (prefix.endsWith(seperator)) {
                            if (sufix.startsWith(seperator)) {
                                stringBuilder.append(prefix).append(sufix.substring(1));
                            } else {
                                stringBuilder.append(prefix).append(sufix);
                            }
                        } else {
                            if (sufix.startsWith(seperator)) {
                                stringBuilder.append(prefix).append(sufix);
                            } else {
                                stringBuilder.append(prefix).append(seperator).append(sufix);
                            }
                        }
                    }
                } else {

                }

            }
        }
        return stringBuilder.toString();
    }

    /**
     * 使用“/”来作为分隔符
     *
     * @param prefix
     * @param sufix
     * @return
     */
    public static String connectWithDefaultSeperator(String prefix, String sufix, boolean mayStartWithSeperator) {
        return connectWithSingleSeperator(prefix, sufix, DEFAULT_SEPERATOR, mayStartWithSeperator);
    }

    /**
     * 转化字符串为Int值
     *
     * @param intString
     * @return
     */
    public static int getInt(String intString) {
        int intValue = 0;
        try {
            return Integer.valueOf(intString);
        } catch (Exception e) {
            LogManager.getInstance().d(TAG, "getInt convert error");
        }
        return intValue;
    }

    /**
     * 获取百分比
     *
     * @param top
     * @param bottom
     * @return
     */
    public static String getPercentValue(double top, double bottom) {
        if (top == 0 || bottom == 0) {
            return "0";
        }
        return getFormatWithNoPoint().format(Math.ceil(top / bottom * 100));
    }
}
