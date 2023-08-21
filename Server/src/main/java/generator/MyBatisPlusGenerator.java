package generator;

import java.util.Collections;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

/**
 * mybatis-plus代码生成器(用于生成entity)<br>
 * 注意:<br>
 * 因为没用mybatis-plus的Service和Controller所以生成完之后要删掉TTT目录
 * 
 * @author tsrs
 * @Date 2017/5/21 12:38
 */
public class MyBatisPlusGenerator {

	/**
	 * 数据源配置
	 */
	private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
			"jdbc:mysql://localhost:3306/webedi?characterEncoding=utf8", "root", "123")
//        .schema("baomidou")
			.dbQuery(new MySqlQuery()).typeConvert(new MySqlTypeConvert()).keyWordsHandler(new MySqlKeyWordsHandler())
			.build();

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator(DATA_SOURCE_CONFIG);

		// 全局配置
		GlobalConfig gc = new GlobalConfig.Builder()
//        		.outputDir("D:\\PPJ\\PJ01\\Source\\Server\\src\\main\\java")
//        		.fileOverride(true)
				.author("jin-qk").build();
//        gc.setOutputDir("D:\\PPJ\\PJ01\\Source\\Server\\src\\main\\java");//这里写你自己的java目录
//        gc.setFileOverride(true);//是否覆盖
//        gc.setActiveRecord(true);
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(false);// XML columList
//        gc.setAuthor("jin-qk");
		mpg.global(gc);

		// 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setTypeConvert(new MySqlTypeConvert() {
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                return super.processTypeConvert(fieldType);
//            }
//        });
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
////        dsc.setUrl("jdbc:mysql://172.19.71.164:3306/webedi?characterEncoding=utf8");
//        dsc.setUrl("jdbc:mysql://localhost:3306/webedi?characterEncoding=utf8");
//        mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig.Builder().entityBuilder().naming(NamingStrategy.underline_to_camel)
				.enableLombok()
				.enableActiveRecord()
				.enableFileOverride()
				.logicDeleteColumnName("use_flg").logicDeletePropertyName("useFlg")
				.idType(IdType.AUTO)
				.addTableFills(new Column("sys_ins_user_id", FieldFill.INSERT))
				.addTableFills(new Column("sys_ins_dt", FieldFill.INSERT))
				.addTableFills(new Column("sys_ins_pg_id", FieldFill.INSERT))
				.addTableFills(new Column("sys_upd_user_id", FieldFill.INSERT_UPDATE))
				.addTableFills(new Column("sys_upd_dt", FieldFill.INSERT_UPDATE))
				.addTableFills(new Column("sys_upd_pg_id", FieldFill.INSERT_UPDATE)).build();
//        //strategy.setTablePrefix(new String[]{"_"});// 此处可以修正为您的表前缀
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        strategy
//        List<TableFill> tableFillList = new ArrayList<TableFill>(6);
//        tableFillList.add(new TableFill("sys_ins_user_id",FieldFill.INSERT));
//        tableFillList.add(new TableFill("sys_ins_dt",FieldFill.INSERT));
//        tableFillList.add(new TableFill("sys_ins_pg_id",FieldFill.INSERT));
//        tableFillList.add(new TableFill("sys_upd_user_id",FieldFill.INSERT_UPDATE));
//        tableFillList.add(new TableFill("sys_upd_dt",FieldFill.INSERT_UPDATE));
//        tableFillList.add(new TableFill("sys_upd_pg_id",FieldFill.INSERT_UPDATE));
//        strategy.setTableFillList(tableFillList);
		mpg.strategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig.Builder().parent("com.tsrs.webedi.modular.system")
				.entity("persistence.model")
				.mapper("persistence.dao")
				.xml("persistence.dao.mapping")
				.service("service")
				.serviceImpl("service.impl")
				.controller("controller").build();
//        pc.setParent(null);
//        pc.setEntity("com.tsrs.webedi.modular.system.persistence.model");
//        pc.setMapper("com.tsrs.webedi.modular.system.persistence.dao");
//        pc.setXml("com.tsrs.webedi.modular.system.persistence.dao.mapping");
//        pc.setService("com.tsrs.webedi.modular.system.service");
//        pc.setServiceImpl("com.tsrs.webedi.modular.system.service.impl");
//        pc.setController("com.tsrs.webedi.modular.system.controller");
		mpg.packageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = new InjectionConfig.Builder()
				.beforeOutputFile((tableInfo, objectMap) -> {
						System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
				})
				.customMap(Collections.singletonMap("abc", gc.getAuthor() + "-mp"))
				.build();
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
		mpg.injection(cfg);

		// 执行生成
		mpg.execute();

		// 打印注入设置
		// System.err.println(mpg.getCfg().getMap().get("abc"));
	}

}