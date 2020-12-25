package com.bcb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	private JDBCUtil() {}  // 将构造方法设置为私有，外部new不出来
	private static JDBCUtil util = null; 
	public synchronized static JDBCUtil getUtil() {
		if(util == null) {
			util = new JDBCUtil();
		}
		return util; // 外部通过这个方法来调用工具类,避免每次都new效率低
	}
	                     // for Oracle用的jdbc
	// 驱动类的地址ַ
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	// 数据库连接地址ַ
	private final static String PATH = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	// 数据库用户名
	private final static String NAME = "JJA1806";
	// 数据库密码
	private final static String PWD = "JJA1806";
	
	// 连接对象
	private Connection con;
	// 语句对象
	private PreparedStatement ps;
	// 结果集
	private ResultSet rs;
	
	
	/**
	 * 获取连接的方法
	 * @since 2020年6月23日21:24:03
	 * @author bcb
	 * @return 连接对象con
	 * 
	 */
	public Connection getCon() {
		
		try {
			// 加载驱动
			Class.forName(DRIVER_NAME);
			// 获取连接对象
			con = DriverManager.getConnection(PATH,NAME,PWD);
		} catch (Exception e) {
			System.out.println("�������ݿ�ʱ�����쳣");
			e.printStackTrace();
		}
		
		return con;	
	}
	
	/**
	 * 关闭数据库的方法
	 * @since 2020年6月23日21:28:49
	 * @author bcb
	 */
	public void closeAll() {
		
		try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(con!=null) {
					con.close();
				}
		} catch (Exception e) {
			System.out.println("�ر����ݿ�ʱ�����쳣");
			e.printStackTrace();
		}
	
	}	
	
	// 增删改通用方法
	// ... 表示动态传参 可当数组看
	/**
	 * @author bcb
	 * @since 2020年6月23日21:33:35
	 * @param sql 增删改语句
	 * @param obj 传入的参数，可以是1也可以是多个，也可以不写 （类型...表示动态传参  jdk1.5以上支持）
	 * @return  受影响行数
	 */
	public int update(String sql,Object...obj) {
		con = getCon();
		int result = 0;  // 受影响行数
		try {
			// 获取语句对象
			ps  = con.prepareStatement(sql);
			// 判断是否有需要置入参数
			if(obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					// 将参数设置到对应问号的位置
					ps.setObject(i+1, obj[i]);
				}		
			}
			//执行增删改语句
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
		return result;
	}
	
	/**
	 * 查询的通用方法
	 * @param sql 查询语句
	 * @param obj 传入的？参数
	 * @return  查询到的数据的结果集
	 * @since 2020年6月23日22:18:56
	 * @author bcb
	 */
	public ResultSet query(String sql,Object...obj) {
		
		con = getCon();
		try {
			// 获取语句对象
			ps  = con.prepareStatement(sql);
			// 判断是否有需要置入参数
			if(obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					// 将参数设置到对应问号的位置
					ps.setObject(i+1, obj[i]);
				}		
			}
			// 执行查询语句
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 不做关闭操作,外部调用.closeAll（）方法关闭
		
		return rs;
		
		
		
	}
	
	
	
	
}
