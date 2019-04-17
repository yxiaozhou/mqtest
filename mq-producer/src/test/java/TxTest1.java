import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

public class TxTest1 {
    JdbcTemplate jdbcTemplate;

    DataSource dataSource;

    @Before
    public void beforeAll() {
        Properties properties = new Properties();
        properties.put("jdbcUrl", "jdbc:mysql://172.16.0.46:3306/brand_center??characterEncoding=utf-8");
        properties.put("username", "mso");
        properties.put("password", "asd321");
        System.getProperties().putAll(properties);
        this.dataSource = new HikariDataSource(new HikariConfig(properties));
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    /**
     * 事务A：
     * 事务A先去查找记录ID=30的记录，然后输出，此时记录数为0，然后等待事务B新增数据后再次查询，你会发现，输出记录数为1了
     *
     *
     */
    @Test
    public void testTxA() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(jdbcTemplate.getDataSource());
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
        try {
            List list = jdbcTemplate.queryForList("SELECT * FROM unallocated_data WHERE id = 595");
            System.out.println("第一次查询结果：" + list.size());
            List list1 = jdbcTemplate.queryForList("SELECT * FROM unallocated_data WHERE id = 595");//此处断点，等待事务B提交新纪录
            System.out.println("第二次查询结果：" + list1.size());
            dataSourceTransactionManager.commit(status);
        } catch (Exception e) {
            System.err.println(e.fillInStackTrace().toString());
            dataSourceTransactionManager.rollback(status);
        }
    }

    /**
     * 事务B：在事务A已经打开的情况下，事务B插入一天记录
     */
    @Test
    public void testTxB() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(jdbcTemplate.getDataSource());
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
        try {
            jdbcTemplate.execute("UPDATE `unallocated_data` SET  `child_age` = 17 WHERE `id` = 595");
            List list1 = jdbcTemplate.queryForList("SELECT * FROM unallocated_data WHERE id = 595");
            System.out.println("查询结果：" + list1.size());
            dataSourceTransactionManager.commit(status);
        } catch (Exception e) {
            System.err.println(e.fillInStackTrace().toString());
            dataSourceTransactionManager.rollback(status);
        }
    }
}
