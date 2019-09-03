package oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import utils.JdbcUtils;

public class jdbc_oracle {

//	create or replace PROCEDURE SAYHELLO AS 
//	BEGIN
//	  DBMS_OUTPUT.PUT_LINE('hello oracle');
//	END SAYHELLO;
	@Test
	public void hello() {
//		System.out.println("helo");
		Connection con = null;
		CallableStatement st = null;

//		{call <procedure-name>[(<arg1>,<arg2>, ...)]}
		String sql = "{ call SAYHELLO()}";

		try {
			// 获取连接
			con = JdbcUtils.getConnection();

//			Creates a CallableStatement object for callingdatabase stored procedures.
			CallableStatement call = con.prepareCall(sql);

			call.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseResources(con, st, null);
		}

	}

	/**
	 * 调用存储过程
	 */
//	CREATE OR REPLACE PROCEDURE querysingleempinfo (
//		    pnum      IN    NUMBER,
//		    pname     OUT   VARCHAR2,
//		    pjob      OUT   VARCHAR2,

//		    psal      OUT   NUMBER,
//		    pcomm     OUT   NUMBER,
//		    pincome   OUT   NUMBER
//		) AS
//		BEGIN
//		    SELECT
//		        ename,
//		        job,
//		        sal,
//		        comm,
//		        sal * 12 + nvl(comm, 0)
//		    INTO
//		        pname,
//		        pjob,
//		        psal,
//		        pcomm,
//		        pincome
//		    FROM
//		        emp
//		    WHERE
//		        empno = pnum;
//		END querysingleempinfo;
	@Test
	public void f1() {
		Connection con = null;

//		{?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
//		{call <procedure-name>[(<arg1>,<arg2>, ...)]}	
		String sql = "{ call querysingleempinfo(?,?,?,   ?,?,?)}";

		try {
			con = JdbcUtils.getConnection();
			CallableStatement call = con.prepareCall(sql);

			// 设置in参数
			call.setInt(1, 7839);

			// out参数
			call.registerOutParameter(2, OracleTypes.VARCHAR);
			call.registerOutParameter(3, OracleTypes.VARCHAR);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.registerOutParameter(5, OracleTypes.NUMBER);
			call.registerOutParameter(6, OracleTypes.NUMBER);

			// 执行
			call.execute();

			String name = call.getString(2);
			String job = call.getString(3);
			double sal = call.getDouble(4);
			double comm = call.getDouble(5);
			double incom = call.getDouble(6);

			System.out.println(name + "\t" + job + "\t" + sal + "\t" + comm + "\t" + incom);

			JdbcUtils.releaseResources(con, call, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用存储函数
	 */
//	create or replace FUNCTION QUERYANNUALINCOME 
//	(
//	  PNO IN NUMBER 
//	) RETURN NUMBER AS 
//	totalIncome NUMBER;
//	BEGIN
//	  select sal*12+comm into totalincome from emp where empno=pno;
//	  RETURN totalincome;
//	END QUERYANNUALINCOME;
	@Test
	public void f2() {
		Connection con = null;

		try {
			con = JdbcUtils.getConnection();

//		{?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
//		{call <procedure-name>[(<arg1>,<arg2>, ...)]}	
			String sql = "{ ?=call QUERYANNUALINCOME(?) }";
			CallableStatement call = con.prepareCall(sql);

			// 返回值
			call.registerOutParameter(1, OracleTypes.NUMBER);

			// 输入参数
			call.setDouble(2, 7839);

			call.execute();

			String income = call.getString(1);

			System.out.println(income);
			JdbcUtils.releaseResources(con, call, null);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testFunction() {
		// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		String sql = "{?=call queryEmpIncome(?)}";

		Connection conn = null;
		CallableStatement call = null;
		try {
			conn = JdbcUtils.getConnection();
			call = conn.prepareCall(sql);

			// 对于out参数，申明
			call.registerOutParameter(1, OracleTypes.NUMBER);

			// 对于in参数，赋值
			call.setInt(2, 7839);

			call.execute();

			// 取出结果
			double income = call.getDouble(1);
			System.out.println(income);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseResources(conn, call, null);
		}
	}

	/*
	 * 查询指定部门的所有员工；
	 */
//	CREATE OR REPLACE 
//	PACKAGE QUERYALL AS 
//	 TYPE emplist is ref cursor;
//	  procedure queryallemp(pnum in number  ,reslist out emplist );
//	END QUERYALL;
//	
//	CREATE OR REPLACE
//	PACKAGE BODY QUERYALL AS
//	--查询指定部门的所有员工信息
//	  procedure queryallemp(pnum in number  ,reslist out emplist ) AS
//	  BEGIN
//	    open reslist for select * from emp where DEPTNO=pnum;
//	  END queryallemp;
//
//	END QUERYALL;
	@Test
	public void f3() {
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "{ call QUERYALL.queryallemp(?,?) }";
			CallableStatement call = con.prepareCall(sql);

//			in参数
			call.setInt(1, 10);

//			out参数
			call.registerOutParameter(2, OracleTypes.CURSOR);

//			执行
			call.execute();

//			获取结果
			ResultSet cursor = ((OracleCallableStatement) call).getCursor(2);

			while (cursor.next()) {
//EMPNO    ENAME    JOB      MGR      HIREDATE SAL      COMM     DEPTNO  
				String EMPNO = cursor.getString("EMPNO");
				String ENAME = cursor.getString("ENAME");
				String JOB = cursor.getString("JOB");

				String MGR = cursor.getString("MGR");
				String HIREDATE = cursor.getString("HIREDATE");
				String SAL = cursor.getString("SAL");

				String COMM = cursor.getString("COMM");
				String DEPTNO = cursor.getString("DEPTNO");

				System.out.println(EMPNO + "\t" + ENAME + "\t" + JOB + "\t" + MGR + "\t" + HIREDATE + "\t" + SAL + "\t"
						+ COMM + "\t" + DEPTNO);
			}

			JdbcUtils.releaseResources(con, call, cursor);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
