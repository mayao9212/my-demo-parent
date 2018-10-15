package com.mayao.jdk.ios;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description:  建表sql及数据导入sql
 * @author: mayao
 * @date: 2018-10-11 19:39
 */
@Slf4j
public class SykTableSqlTest {

    private final static String NEW_TABLE_NAME_REPLACE="#NewTable#";
    private final static String ROOT_TABLE_NAME_REPLACE="#RootTable#";
    private final static String START_NUM = "#StartNum#";
    private final static String END_NUM = "#EndNum#";
    private final static String NEW_TABLE_NAME_SEPARATE = "_";
    private final static String NEW_LINE = "\r\n";

    /**
     * t_account_details
     */
    private String accountDetailSql = "CREATE TABLE `"+NEW_TABLE_NAME_REPLACE+"` (\n" +
            "`id`  int(11) NOT NULL COMMENT '主键' ,\n" +
            "`order_id`  int(11) NULL DEFAULT NULL COMMENT '订单ID' ,\n" +
            "`order_type`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单type(任务订单：TASK)' ,\n" +
            "`money`  decimal(12,2) NULL DEFAULT NULL COMMENT '金额' ,\n" +
            "`task_id`  int(11) NULL DEFAULT NULL COMMENT '任务ID' ,\n" +
            "`user_id`  int(11) NULL DEFAULT NULL COMMENT '用户ID' ,\n" +
            "`task_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称' ,\n" +
            "`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,\n" +
            "`create_by`  bigint(20) NULL DEFAULT NULL COMMENT '创建人id' ,\n" +
            "`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间' ,\n" +
            "`update_by`  bigint(20) NULL DEFAULT NULL COMMENT '修改人id' ,\n" +
            "PRIMARY KEY (`id`),\n" +
            "INDEX `idx_user_id` (`user_id`) USING BTREE ,\n" +
            "INDEX `idx_task_id` (`task_id`) USING BTREE ,\n" +
            "INDEX `idx_create_time` (`create_time`) USING BTREE \n" +
            ")\n" +
            "ENGINE=InnoDB\n" +
            "DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci\n" +
            "COMMENT='账户明细表'\n" +
            ";";
    private String accountDetailDataSql = "INSERT INTO "+NEW_TABLE_NAME_REPLACE+" (\n" +
            "\tSELECT\n" +
            "\t\t*\n" +
            "\tFROM\n" +
            "\t\tt_account_details\n" +
            "\tWHERE\n" +
            "\t\tuser_id BETWEEN "+START_NUM+"\n" +
            "\tAND "+END_NUM+"\n" +
            ");";
    /**
     * sys_sub_table_info
     */
    private String subTableInsertSql = "INSERT INTO sys_sub_table_info ( table_real_name, table_type, start_id, end_id ) VALUES ( '"+NEW_TABLE_NAME_REPLACE+"', '"+ROOT_TABLE_NAME_REPLACE+"', "+START_NUM+", "+END_NUM+");\n";
    /**
     * t_message
     */
    private String messageTableSql = "CREATE TABLE `"+NEW_TABLE_NAME_REPLACE+"` (\n" +
            "`id`  int(11) NOT NULL COMMENT '主键' ,\n" +
            "`user_id`  int(11) NULL DEFAULT NULL COMMENT '用户ID' ,\n" +
            "`reply_id`  int(11) NULL DEFAULT NULL COMMENT '回复ID' ,\n" +
            "`status`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态' ,\n" +
            "`message_type`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息类型' ,\n" +
            "`message_content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息内容' ,\n" +
            "`is_read`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否读过' ,\n" +
            "`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,\n" +
            "`create_by`  bigint(20) NULL DEFAULT NULL COMMENT '创建人id' ,\n" +
            "`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间' ,\n" +
            "`update_by`  bigint(20) NULL DEFAULT NULL COMMENT '修改人id' ,\n" +
            "PRIMARY KEY (`id`),\n" +
            "INDEX `idx_user_id_is_read` (`user_id`, `is_read`) USING BTREE \n" +
            ")\n" +
            "ENGINE=InnoDB\n" +
            "DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci\n" +
            "COMMENT='推送消息表'\n" +
            ";";
    private String messageDataSql = "INSERT INTO "+NEW_TABLE_NAME_REPLACE+" (\n" +
            "\tSELECT\n" +
            "\t\t*\n" +
            "\tFROM\n" +
            "\t\tt_message\n" +
            "\tWHERE\n" +
            "\t\tuser_id BETWEEN "+START_NUM+"\n" +
            "\tAND "+END_NUM+"\n" +
            ");";

    /**
     * t_account_detail  分表sql，数据迁移sql，文件生成
     * @throws IOException
     */
    @Test
    public void accountDetailSql() throws IOException {

        String rootTableName = "t_account_details";
        int userIdSize = 6999;
        createSqlFile(rootTableName,accountDetailSql,accountDetailDataSql,userIdSize);
    }

    /**
     * t_message  分表sql，数据迁移sql，文件生成
     * @throws IOException
     */
    @Test
    public void messageSql() throws IOException {
        String rootTableName = "t_message";
        int userIdSize = 19999;
        createSqlFile(rootTableName,messageTableSql,messageDataSql,userIdSize);
    }

    /**
     *
     * @param rootTableName     被扩展的表名
     * @param tableSql          建表的sql
     * @param dataSql           迁移数据sql
     * @param userIdSize        每张表分配的用户量
     * @throws IOException
     */
    private void createSqlFile(String rootTableName,String tableSql,String dataSql,int userIdSize) throws IOException{

        String parentPath = "F:\\syk\\";

        //分表sql文件
        String tableSqlPath = parentPath.concat(rootTableName).concat("_table.sql");
        Path tablePath = Paths.get(tableSqlPath);
        File tableFile = tablePath.toFile();
        //文件生成
        if( !tableFile.getParentFile().exists() ){
            tableFile.getParentFile().mkdirs();
        }
        //删除+重新生成
        tableFile.delete();
        tableFile.createNewFile();

        //数据迁移sql
        String dataSqlPath = parentPath.concat(rootTableName).concat("_data.sql");
        Path dataPath = Paths.get(dataSqlPath);
        File dataFile = dataPath.toFile();
        dataFile.delete();
        dataFile.createNewFile();

        //子表关联
        String pathStr = parentPath.concat(rootTableName).concat("_sub_table.sql");
        Path subTablePath = Paths.get(pathStr);
        File file = subTablePath.toFile();
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        file.delete();
        file.createNewFile();


        //总人数200W
        int userTotal = 2000000;
        //表数据人数：6999人
        int startNum = 0;
        //结束userId，迁移数据用
        int endNum = userIdSize;

        int tableCount = 0;
        while ( startNum < userTotal ){

            String newTableName = String.join(NEW_TABLE_NAME_SEPARATE,rootTableName,startNum+"");
            log.info("新表名：{}",newTableName);

            //建表sql替换
            tableSql = tableSql.replace(NEW_TABLE_NAME_REPLACE,newTableName);
            //建表sql文件
            Files.write(tablePath,NEW_LINE.getBytes(),StandardOpenOption.APPEND);
            Files.write(tablePath,tableSql.getBytes(),StandardOpenOption.APPEND);
            Files.write(tablePath,(NEW_LINE+NEW_LINE).getBytes(),StandardOpenOption.APPEND);

            //导数据sql替换
            dataSql = dataSql.replace(NEW_TABLE_NAME_REPLACE,newTableName)
                    .replace(START_NUM,startNum+"")
                    .replace(END_NUM,endNum+"");
            //导数据文件
            Files.write(dataPath,NEW_LINE.getBytes(),StandardOpenOption.APPEND);
            Files.write(dataPath,dataSql.getBytes(),StandardOpenOption.APPEND);
            Files.write(dataPath,(NEW_LINE+NEW_LINE).getBytes(),StandardOpenOption.APPEND);

            //模板还原，下次循环调用
            tableSql = tableSql.replace(newTableName,NEW_TABLE_NAME_REPLACE);
            dataSql = dataSql.replace(newTableName,NEW_TABLE_NAME_REPLACE)
                    .replace(startNum+"",START_NUM)
                    .replace(endNum+"",END_NUM);
            log.info("start:{},end:{}",startNum,endNum);

            //子表的关联
            sysSubTableInsertSql(subTablePath,newTableName,rootTableName,startNum,endNum);

            //数目增加
            startNum = endNum + 1;
            endNum = startNum + userIdSize;
            tableCount++;
        }
        log.info("原表名：{},扩张表数目：{}",rootTableName,tableCount);

    }


    /**
     *
     * @param newTableName      新扩展的表名
     * @param rootTableName     原表名
     * @param startNum          起始用户id，包括边界
     * @param endNum            结束用户id，包括边界
     * @throws IOException
     */
    private void sysSubTableInsertSql(Path path,String newTableName,String rootTableName,int startNum,int endNum) throws IOException{

//        String pathStr = "F:\\syk\\"+rootTableName+"_sub_table.sql";
//        Path path = Paths.get(pathStr);
//        File file = path.toFile();
//        if(!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        file.delete();
//        file.createNewFile();

        //替换
        subTableInsertSql = subTableInsertSql.replace(NEW_TABLE_NAME_REPLACE,newTableName)
                .replace(ROOT_TABLE_NAME_REPLACE,rootTableName)
                .replace(START_NUM,startNum+"")
                .replace(END_NUM,endNum+"");
        //生成文件
        Files.write(path,NEW_LINE.getBytes(),StandardOpenOption.APPEND);
        Files.write(path,subTableInsertSql.getBytes(),StandardOpenOption.APPEND);

        //还原
        subTableInsertSql = subTableInsertSql.replace(newTableName,NEW_TABLE_NAME_REPLACE)
                .replace(rootTableName,ROOT_TABLE_NAME_REPLACE)
                .replace(startNum+"",START_NUM)
                .replace(endNum+"",END_NUM);

    }




}
