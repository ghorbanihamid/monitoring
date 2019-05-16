Calendar._DN = new Array
("یکشنبه",
 "دوشنبه",
 "سه شنبه",
 "چهارشنبه",
 "پنجشنبه",
 "جمعه",
 "شنبه",
 "یکشنبه");

Calendar._SDN = new Array
("یک",
 "دو",
 "سه",
 "چهار",
 "پنج",
 "جمعه",
 "شنبه",
 "یک");

Calendar._FD = 6;

Calendar._MN = new Array
("ژانویه",
 "فوریه",
 "مارس",
 "آوریل",
 "می",
 "جون",
 "جولای",
 "آگوست",
 "سپتامبر",
 "اکتبر",
 "نوامبر",
 "دسامبر");

Calendar._SMN = new Array
("Jan",
 "Feb",
 "Mar",
 "Apr",
 "May",
 "Jun",
 "Jul",
 "Aug",
 "Sep",
 "Oct",
 "Nov",
 "Dec");

 // full month names
Calendar._JMN = new Array
("فروردین",
 "اردیبهشت",
 "خرداد",
 "تیر",
 "مرداد",
 "شهریور",
 "مهر",
 "آبان",
 "آذر",
 "دی",
 "بهمن",
 "اسفند");

// short month names
Calendar._JSMN = new Array
("فروردین",
 "اردیبهشت",
 "خرداد",
 "تیر",
 "مرداد",
 "شهریور",
 "مهر",
 "آبان",
 "آذر",
 "دی",
 "بهمن",
 "اسفند");


// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "درباره تقویم";

Calendar._TT["ABOUT"] =
    "انتخاب تاریخ:\n" +
    "از کلیدهای \xab, \xbb برای انتخاب سال استفاده نمایید \n" +
    "از کلیدهای " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + "برای انتخاب ماه استفاده نمایید \n" +
        " برای انتخاب سریعتر ماه یا سال کلید موس را بر روی کلید های فرمان بالای تقویم نگه دارید.";

Calendar._TT["ABOUT_TIME"] = "\n\n" +
"Time selection:\n" +
"- Click on any of the time parts to increase it\n" +
"- or Shift-click to decrease it\n" +
"- or click and drag for faster selection.";

Calendar._TT["PREV_YEAR"] = "سال قبل (hold for menu)";
Calendar._TT["PREV_MONTH"] = "ماه قبل (hold for menu)";
Calendar._TT["GO_TODAY"] = "رفتن به امروز";
Calendar._TT["NEXT_MONTH"] = "ماه بعد (hold for menu)";
Calendar._TT["NEXT_YEAR"] = "سال بعد (hold for menu)";
Calendar._TT["SEL_DATE"] = "انتخاب تاریخ";
Calendar._TT["DRAG_TO_MOVE"] = "Drag to move";
Calendar._TT["PART_TODAY"] = " (امروز)";

Calendar._TT["DAY_FIRST"] = "ابتدا %s نمایش داده شود";

Calendar._TT["SELECT_COLUMN"] = "انتخاب تمام %s‌های این ماه";
Calendar._TT["SELECT_ROW"] = "انتخاب روزهای این هفته";

Calendar._TT["WEEKEND"] = "5";

Calendar._TT["CLOSE"] = "بستن";
Calendar._TT["TODAY"] = "امروز";
Calendar._TT["TIME_PART"] = "(Shift-)Click or drag to change value";

Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "%A, %e %b";

Calendar._TT["WK"] = "هفته";
Calendar._TT["TIME"] = "زمان :";

Calendar._TT["LAM"] = "ق.ظ.";
Calendar._TT["AM"] = "ق.ظ.";
Calendar._TT["LPM"] = "ب.ظ.";
Calendar._TT["PM"] = "ب.ظ.";

Calendar._NUMBERS = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'];

Calendar._DIR = 'rtl';