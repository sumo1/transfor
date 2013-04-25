import com.google.common.base.Splitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yunshu.xw
 */
public class DB2DO {

    static String DOPRE = "private";
    static String LONGS = " Long ";
    static String STRINGS = " string ";
    static String INTS = " Integer ";
    static String DATES = " Date ";
    static String DOLAST = ";\n";
    static String ERRORMASSAGE = "\n" + "//TODO unknown column, check this column!";
    public static void main(String[] args) throws IOException {
        String db = "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',\n" +
                "  `logdate` datetime DEFAULT NULL COMMENT '审核时间',\n" +
                "  `qualityid` bigint(20) DEFAULT NULL COMMENT '资质id',\n" +
                "  `qualityname` varchar(64) DEFAULT NULL COMMENT '资质名称',\n" +
                "  `memberid` bigint(20) DEFAULT NULL COMMENT '用户id',\n" +
                "  `nickname` varchar(64) DEFAULT NULL COMMENT '用户昵称',\n" +
                "  `status` tinyint(4) DEFAULT NULL COMMENT '审核状态（1.待审核， 2.待确认，3.通过， 4.拒绝 ）',\n" +
                "  `qualitytag` varchar(64) DEFAULT NULL COMMENT '资质标签名称',\n" +
                "  `begintime` date DEFAULT NULL COMMENT '资质有效开始时间',\n" +
                "  `endtime` date DEFAULT NULL COMMENT '资质有效结束时间',\n" +
                "  `auditorid` bigint(20) DEFAULT NULL COMMENT '审核人id',\n" +
                "  `auditorname` varchar(64) DEFAULT NULL COMMENT '审核人旺旺昵称',\n" +
                "  `auditcomments` varchar(1024) DEFAULT NULL COMMENT '审核备注',\n";
        System.out.println(trans2DO(db));
    }

    private static String trans2DO(String db) {
        StringBuilder result = new StringBuilder();
        Iterable<String> rows = Splitter.on("\n").split(db);
        for (String row : rows) {
            if(row.isEmpty()){
                continue;
            }
            Iterable<String> columns = Splitter.on(" ").split(row);
            result.append(trans2Value(columns));
            result.append(DOLAST);
        }
        return result.toString();
    }

    private static String trans2Value(Iterable<String> columns) {
        List<String> transValues = new ArrayList<String>();
        Boolean first = true;
        Boolean last = false;
        String comment = "";
        for (String column : columns) {
            if(column.isEmpty()){
                continue;
            }
            if(first){
                transValues.add(column.substring(1, column.length()-1));
                first = false;
                continue;
            }
            if(column.contains("bigint")){
                transValues.add(LONGS);
                continue;
            }
            if(column.contains("int")){
                transValues.add(INTS);
                continue;
            }
            if(column.contains("char")){
                transValues.add(STRINGS);
                continue;
            }
            if(column.contains("date")){
                transValues.add(DATES);
                continue;
            }
            if(column.contains("COMMENT")){
                last = true;
                continue;
            }
            if(last){
                comment += column;
            }
        }
        if(comment != ""){
            transValues.add(comment.substring(1, comment.length()-2));
        }
        if(transValues.size() != 3){
            return ERRORMASSAGE;
        }
        return formatValues(transValues);

    }

    private static String formatValues(List<String> transValues) {
        String annotation =  "\n" + "//" + transValues.get(2) + "\n";
        return  annotation + DOPRE + transValues.get(1) + transValues.get(0);
    }
}
