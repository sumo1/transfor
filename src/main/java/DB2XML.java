import com.google.common.base.Splitter;

/**
 * User: yunshu.xw
 */
public class DB2XML {
    static String PRE = "<column ";
    static String NAME = "name=";
    static String VALUE_TYPE = "valueType=";
    static String VARCHAR = "VARCHAR";
    static String NUMBER = "NUMBER";
    static String SYSTIME = "SYSTIME";
    static String INCREASE = "increase=";
    static String VALUE = "value=";
    static String LAST = "/>\n";
    static Boolean IS_LAST = false;
    static Boolean IS_FIRST = false;
    public static void main(String[] args) {
        String db = " `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',\n" +
                "  `qualityid` bigint(20) DEFAULT NULL COMMENT '资质id',\n" +
                "  `imgname` varchar(64) DEFAULT NULL COMMENT '图片名称',\n" +
                "  `imgpath` varchar(1024) DEFAULT NULL COMMENT '图片地址',\n" +
                "  `imgsize` varchar(24) DEFAULT NULL COMMENT '图片尺寸',\n" +
                "  `createtime` datetime DEFAULT NULL COMMENT '创建时间',";
        System.out.println(trans2XML(db));
    }
    private static String trans2XML(String db) {
        StringBuilder result = new StringBuilder();
        Iterable<String> rows = Splitter.on("\n").split(db);
        for (String row : rows) {
            Iterable<String> columns = Splitter.on(" ").split(row);
            result.append(PRE);
            IS_LAST = false;
            IS_FIRST = true;
            for (String column : columns) {
                result.append(trans2Value(column, IS_FIRST));
                if(IS_LAST){
                    break;
                }
            }
            result.append(LAST);
        }
        return result.toString();
    }

    private static String trans2Value(String column, Boolean isFirst) {
        if(column.isEmpty()){
            return "";
        }
        if(isFirst){
            IS_FIRST = false;
            return NAME + format(column.substring(1, column.length()));
        }
        if(column.contains("int")){
            return VALUE_TYPE + format(NUMBER);
        }
        if(column.contains("char")){
            return VALUE_TYPE + format(VARCHAR);
        }
        if(column.contains("date")){
            IS_LAST = true;
            return VALUE_TYPE + format(SYSTIME);
        }
        if(column.contains("INCREMENT")){
            return INCREASE + format("true");
        }
        if(column.contains("'")){
            return VALUE + format(column.substring(1, column.length()-2));
        }
        return "";
    }

    private static String format(String column) {
        return "\""+column+"\" ";
    }
}
