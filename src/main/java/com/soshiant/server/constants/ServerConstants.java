package com.soshiant.server.constants;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 8/17/11
 * Time: 8:41 PM
 */
public interface ServerConstants {

    public static boolean isAppLogEnabled = true;

    public static boolean rmfMonitoringIsEnabled = true;
    public static boolean saveRmfMonitoringDataIsEnabled = true;

    public static String RMF_MONITORING_SERVER_IP_PORT = "172.1.1.1:8083";


    public static final String LOCALE_KEY = "WW_TRANS_I18N_LOCALE";
    public final String EQUAL_SIGN = "=";

    public static final String defaultRefreshTime = "2";

    public static final String FIRST_TERM_MONTH_OF_YEAR = "01";
    public static final String SECOND_TERM_MONTH_OF_YEAR = "02";
    public static final String LAST_TERM_MONTH_OF_YEAR = "12";
    public static final String ELEVEN_TERM_MONTH_OF_YEAR = "11";

    public static final byte TIME_FORMAT_WITH_SECOND = 6;
    public static final byte TIME_FORMAT_WITH_MINUTE = 4;

    public static final byte START_DAY_OF_TERM = 20;
    public static final byte NUMBER_OF_GAP_TERMS = 4;

    public static final byte CHART_ORIENTATION_HORIZONTAL = 1;
    public static final byte CHART_ORIENTATION_LANDSCAPE = 2;

    public static final byte ActionMethodIsMainOperation = 1;
    public static final byte ActionMethodIsNotMainOperation = 0;
    public static final String NoSqlText = "";
    public static final String EmptyString = "";
    public static final String EmptyModel = "";
    public static final String NoExceptionMessage = "";


    public static final short SERVER_TYPE_MAINFRAME = 1;

    public static final short SMS_MESSAGE_TEST_MAX_LENGTH = 200;

    public static final byte MESSAGE_TYPE_SMS = 1;
    public static final byte MESSAGE_TYPE_EMAIL = 2;
    public static final byte MESSAGE_TYPE_SMS_AND_EMAIL = 3;

    public static final short SMS_MESSAGE_TYPE_PLAIN_TEXT = 1;

    public static final short SMS_MESSAGE_ENCODING_LATIN = 1;

    public static final short SMS_MESSAGE_SEND_STATUS_OK = 0;
    public static final short SMS_MESSAGE_SEND_STATUS_INVALID_USERNAME = 1;
    public static final short SMS_MESSAGE_SEND_STATUS_INVALID_PASSWORD = 2;
    public static final short SMS_MESSAGE_SEND_STATUS_INVALID_SHORT_NUMBER = 3;
    public static final short SMS_MESSAGE_SEND_STATUS_NOT_ENOUGH_CREDIT = 5;
    public static final short SMS_MESSAGE_SEND_STATUS_ILLEGAL_PARAMETERS = 7;
    public static final short SMS_MESSAGE_SEND_STATUS_DISABLED_ACCOUNT = 10;
    public static final short SMS_MESSAGE_SEND_STATUS_TOO_MANY_REQUESTS = 15;

    public static final String SMS_MESSAGE_COUNTRY_PREFIX = "98";
    public static final String SMS_ADP_MESSAGE_USERNAME = "hamid";
    public static final String SMS_ADP_MESSAGE_PASSWORD = "hamid";
    public static final String SMS_ADP_SOURCE_NUMBER = "9820001166";

    public final static String SMTP_SERVER_GMAIL = "smtpGmail";
    public final static String SMTP_SERVER_KORDASTI = "smtphamid";
    public final static String SMTP_GMAIL_PLACEMENT_TEST_EMAIL_USER = "hamid@gmail.com";
    public final static String SMTP_GMAIL_PLACEMENT_TEST_EMAIL_PASSWORD = "hamid123";
    public static final String PLACEMENT_TEST_SENDER_GMAIL_ADDRESS = "hamid@gmail.com";
    public final static String SMTP_KORDASTI_PLACEMENT_TEST_EMAIL_USER = "hamid@gmail.com";
    public final static String SMTP_KORDASTI_PLACEMENT_TEST_EMAIL_PASSWORD = "hamid123";
    public static final String PLACEMENT_TEST_SENDER_KORDASTI_ADDRESS = "hamid@gmail.com";

    public static final short UserNameMinLength = 5;
    public static final short UserNameMaxLength = 20;
    public static final short PasswordMinLength = 8;
    public static final short PasswordMaxLength = 20;

    public static final short staffIdMinLength = 6;
    public static final short studentIdMinLength = 6;

    public static final short OBJECT_GENDER_NOT_DEFINED = 0;
    public static final short OBJECT_GENDER_MALE = 1;
    public static final short OBJECT_GENDER_FEMALE = 2;

    public static final short PARAMETER_KEY_QUESTIONS_COUNT = 3;
    public static final short PARAMETER_RIGHT_ANSWERS_COUNT = 5;

    public static final short MAX_CLASS_DAYS_COUNT = 20;
    public static final short MAX_CLASS_CYCLES_COUNT = 20;

    public static final String DEFAULT_PASSWORD = "11111111";

    public static final String OBJECT_CAPTCHA_KEY                       = "jcaptchaKey";
    public static final String OBJECT_CAPTCHA_RESPONSE                  = "jcaptchaResponse";
    public static final String OBJECT_USER_LOGIN_STATUS_COMMON_USER     = "commonUserLogin";
    public static final String OBJECT_USER_LOGIN_STATUS_FIRST_LOGIN     = "firstLoginChgPass";

    public static final String OBJECT_USERNAME = "userName";
    public static final String OBJECT_USER_ID = "userId";
    public static final String OBJECT_USER_TYPE = "userType";
    public static final String OBJECT_USER_PERMISSIONS = "userPermissions";
    public static final String OBJECT_USER_LOGIN_STATUS = "userLoginStatus";
    public static final String OBJECT_USER_BRANCH = "userBranch";
    public static final String OBJECT_USER_INFO = "userInfoModel";

    public final static String USER_ID = "userId";
    public final static String USER_PERMISSION_START_DATE = "startPermissionDate";
    public final static String USER_PERMISSION_END_DATE = "endPermissionDate";
    public final static String STAFF_ID = "staffId";
    public final static String POSITION_ID = "positionId";
    public final static String BRANCH_ID = "branchId";
    public final static String ID_NUMBER = "idNumber";
    public final static String SITE_NAME = "siteName";
    public final static String SITE_MANAGER_ID = "managerId";
    public final static String NATIONAL_CODE = "nationalCode";
    public final static String EMAIL_ADDRESS = "emailAddress";
    public final static String CELL_PHONE = "cellPhone";
    public final static String PARAMETER_YEAR = "parameterYear";
    public final static String PARAMETER_NAME = "parameterName";
    public final static String SEND_DATE = "sendDate";
    public final static String SEND_STATUS = "sendStatus";
    public final static String PERSIAN_TRANS_DATE = "persianTransDate";
    public final static String LOG_DATE = "logDate";
    public final static String DOER_ID = "doerId";
    public final static String SERVER_ID = "serverId";
    public final static String SERVER_STATUS = "serverStatus";
    public final static String SERVER_TYPE = "serverType";
    public final static String RMF_METRIC_ID = "metricId";
    public final static String RMF_METRIC_DESC = "metricDesc";
    public final static String SHIFT_DATE = "shiftDate";
    public final static String SHIFT_START_TIME = "shiftStartTime";
    public final static String SHIFT_END_TIME = "shiftEndTime";
    public final static String RMF_TRANS_START_TIME = "transStartTime";
    public final static String RMF_TRANS_END_TIME = "transEndTime";

    public final static String LABEL_RMF_METRIC_DESC = "Rmf Metric Desc: ";
    public final static String LABEL_VALUE = "Value: ";


    public final static String SMS_RECEIVED_CONFIRM_PERSIAN_TEXT = "بله";
    public final static String SMS_RECEIVED_CONFIRM_ENGLISH_TEXT = "yes";
    public final static String SMS_NEW_LINE = "\r\n";
    public final static String SMS_DEAR_NEW_USER_TEXT = "Dear new user";
    public final static String SMS_USER_NAME_TEXT = "username : ";
    public final static String SMS_PASSWORD_TEXT = "password : ";
    public final static String SMS_INSTITUTE_NAME = "Kordasti institute";



    public static final byte CHART_TYPE_COLUMN2D = 1;
    public static final byte CHART_TYPE_COLUMN3D = 2;
    public static final byte CHART_TYPE_BAR2D = 3;
    public static final byte CHART_TYPE_PIE2D = 4;
    public static final byte CHART_TYPE_PIE3D = 5;
    public static final byte CHART_TYPE_LINE = 6;
    public static final byte CHART_TYPE_TIME_LINE = 7;
    public static final byte CHART_TYPE_TREE = 8;
    public static final byte CHART_TYPE_BUBBLE = 9;
    public static final byte CHART_TYPE_AREA2D = 10;
    public static final byte CHART_TYPE_AREA3D  = 11;
    public static final byte CHART_TYPE_DOUGHNUT2D = 12;
    public static final byte CHART_TYPE_DOUGHNUT3D = 13;
    public static final byte CHART_TYPE_PARETO2D = 14;
    public static final byte CHART_TYPE_PARETO3D = 15;
    public static final byte CHART_TYPE_ZOOMLINE = 16;

    public static final byte STAFF_STATUS_INACTIVE = 0;
    public static final byte STAFF_STATUS_ACTIVE = 1;
    public static final byte STAFF_STATUS_DISABLE = 2;
    public static final byte STAFF_STATUS_EXPIRED = 3;

    public static final byte SERVER_STATUS_INACTIVE = 0;
    public static final byte SERVER_STATUS_ACTIVE = 1;
    public static final byte SERVER_STATUS_DISABLE = 2;

    public static final byte STAFF_POSITION_STATUS_ACTIVE = 1;

    public static final byte STAFF_POSITION_STATUS_DISABLE = 2;

    public static final boolean USER_STATUS_ACTIVE = true;

    public static final String OBJECT_MONITORABLE_SERVERS = "ObjectMonitorableServers";

    public static final String POSITION_TYPE_NAME_UNKNOWN = "Unknown";
    public static final String POSITION_TYPE_NAME_STUDENT = "Student";
    public static final String POSITION_TYPE_NAME_ADVISOR = "Advisor";
    public static final String POSITION_TYPE_NAME_TEACHER = "Teacher";
    public static final String POSITION_TYPE_NAME_BRANCH_ASSISTANT = "Branch Assistant";
    public static final String POSITION_TYPE_NAME_BRANCH_HEAD = "Branch Head";
    public static final String POSITION_TYPE_NAME_HEAD_TEACHER = "Head Teacher";
    public static final String POSITION_TYPE_NAME_FINANCE_ADMIN = "Finance Admin";
    public static final String POSITION_TYPE_NAME_EDUCATION_ARCHITECT = "Education Architect";
    public static final String POSITION_TYPE_NAME_SHARE_HOLDERS = "Share Holder";
    public static final String POSITION_TYPE_NAME_COMPUTER_ADMIN = "Computer Admin";
    public static final String POSITION_TYPE_NAME_CHAIRMAN = "Chairman";

    public static final byte CLASS_TYPE_TERMIC = 1;
    public static final byte CLASS_TYPE_ESP    = 2;

    public static final byte POSITION_TYPE_UNKNOWN = 0;
    public static final byte POSITION_TYPE_STUDENT = 1;
    public static final byte POSITION_TYPE_ADVISOR = 2;
    public static final byte POSITION_TYPE_TEACHER = 3;
    public static final byte POSITION_TYPE_BRANCH_ASSISTANT = 4;
    public static final byte POSITION_TYPE_BRANCH_HEAD = 5;
    public static final byte POSITION_TYPE_HEAD_TEACHER = 6;
    public static final byte POSITION_TYPE_FINANCE_ADMIN = 7;
    public static final byte POSITION_TYPE_EDUCATION_ARCHITECT = 8;
    public static final byte POSITION_TYPE_SHARE_HOLDERS = 9;
    public static final byte POSITION_TYPE_COMPUTER_ADMIN = 10;
    public static final byte POSITION_TYPE_CHAIRMAN = 11;


    public static final byte HOMEWORK_SCORE = 6;
    public static final byte KEY_QUESTION_SCORE = 3;
    public static final byte RIGHT_ANSWER_SCORE = 1;
    public static final byte WARNING_SCORE = -5;
    public static final byte ABSENT_SCORE = -2;
    public static final byte PROJECT_SCORE = 18;

    public static final byte TERMS_CYCLE_GROUP = 1;
    public static final byte ZABANCELL_CYCLE_GROUP = 2;
    public static final byte FACILITATION_CYCLE_GROUP = 3;
    public static final byte COMPREHENSIVE_CYCLE_GROUP = 4;
    public static final byte PROFESSIONAL_CYCLE_GROUP = 5;


    public static final double ADVISOR_PERCENT = 0/15;
    public static final double TEACHER_PERCENT = 0/3;
    public static final double IT_PERCENT      = 0.024;



    public static final short SUCCESSFUL = 0;
    public static final short ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS = 1000;
    public static final short ERROR_DB_DUPLICATE_NATIONAL_CODE = 1001;
    public static final short ERROR_DB_DUPLICATE_CELL_PHONE = 1002;
    public static final short ERROR_DB_DUPLICATE_EMAIL_ADDRESS = 1003;
    public static final short ERROR_DB_CYCLE_ID_NOT_FOUND = 1004;
    public static final short ERROR_DB_LEVEL_ID_NOT_FOUND = 1005;
    public static final short ERROR_DB_BRANCH_NOT_FOUND = 1006;
    public static final short ERROR_DB_INFORMATION_NOT_FOUND = 1007;


    public static final short ERROR_DB_STUDENT_ID_NOT_FOUND = 1100;
    public static final short ERROR_DB_STUDENT_IS_NOT_ACTIVE = 1101;
    public static final short ERROR_DB_STUDENT_IS_DISABLED = 1102;

    public static final short ERROR_DB_STAFF_ID_NOT_FOUND = 1200;
    public static final short ERROR_DB_STAFF_IS_NOT_ACTIVE = 1201;
    public static final short ERROR_DB_STAFF_IS_DISABLED = 1202;

    public static final short ERROR_DB_STAFF_ID_TWO_SESSION_NOT_FOUND = 1210;
    public static final short ERROR_DB_STAFF_TWO_SESSION_IS_NOT_ACTIVE = 1211;
    public static final short ERROR_DB_STAFF_TWO_SESSION_IS_DISABLED = 1212;

    public static final short ERROR_DB_STAFF_ID_ONE_SESSION_NOT_FOUND = 1220;
    public static final short ERROR_DB_STAFF_ONE_SESSION_IS_NOT_ACTIVE = 1221;
    public static final short ERROR_DB_STAFF_ONE_SESSION_IS_DISABLED = 1222;

    public static final short ERROR_DB_STAFF_DOES_NOT_HAVE_POSITION = 1230;
    public static final short ERROR_DB_STAFF_ALREADY_HAS_POSITION = 1231;
    public static final short ERROR_DB_STAFF_POSITION_IS_NOT_ACTIVE = 1232;

    public static final short ERROR_DB_CLASS_ID_NOT_FOUND = 1300;
    public static final short ERROR_DB_DUPLICATE_CLASS_ID = 1301;
    public static final short ERROR_DB_CLASS_IS_FULL = 1302;
    public static final short ERROR_DB_CLASS_NOT_PASSED = 1303;
    public static final short ERROR_DB_SESSION_TYPE_NOT_VALID = 1304;
    public static final short ERROR_DB_FIRST_CLASS_TYPE_NOT_VALID = 1305;
    public static final short ERROR_DB_FIRST_CLASS_TIME_NOT_VALID = 1306;
    public static final short ERROR_DB_SECOND_CLASS_TIME_NOT_VALID = 1307;

    public static final short ERROR_INVALID_CAPTCHA = 1400;
    public static final short ERROR_DB_USER_NOT_FOUND = 1401;
    public static final short ERROR_DB_USER_INVALID_PASSWORD = 1402;

    public static final short ERROR_DB_DUPLICATE_SESSION = 1500;


    public static final short ERROR_SYSTEM_COULD_NOT_CONNECT_EMAIL_SERVER = 1900;
    public static final short ERROR_SYSTEM_COULD_NOT_SEND_EMAIL = 1901;

    public static final short ERROR_HTTP_STATUS_CODE_403 = 403;
    public static final short ERROR_SYSTEM_COULD_NOT_RESPOND_1 = 2001;
    public static final short ERROR_SYSTEM_COULD_NOT_RESPOND_2 = 2002;
    public static final short ERROR_SYSTEM_COULD_NOT_RESPOND_3 = 2003;

    public static final String TEACHER_CLASSES_LIST_VECTOR = "teacherClassesListVector";
    public static final String CLASS_STUDENTS_LIST_VECTOR = "classStudentsListVector";
    public static final String STUDENT_ASSIGNABLE_CLASSES_LIST_VECTOR = "studentAssignableClassesListVector";
    public static final String SESSION_STUDENTS_LIST_VECTOR = "sessionStudentsListVector";
    public static final String ADVISOR_URREG_STUDENTS_COUNT       = "advisorRegStudentsCount";
    public static final String ADVISOR_ALL_STUDENTS_LIST_VECTOR = "advisorAllStudentsListVector";
    public static final String CHART_RMF_MONITORING_DATA_LIST = "chartRmfMonitoringDataList";
}
