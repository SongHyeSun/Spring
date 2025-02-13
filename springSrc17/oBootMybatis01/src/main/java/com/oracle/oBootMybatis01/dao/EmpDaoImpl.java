package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository
//final 끌고 들어와준다.
@RequiredArgsConstructor

public class EmpDaoImpl implements EmpDao {
	//Mybatis DB 연동 (DI 작업)
	private final SqlSession session;

	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl Start totalEmp...");
		
		try {
			//.으로 이름 넣어주는 이유 : id가 많기 때문에!!
			//현재 logic을 통해 나온 totEmpCount는 COUNT(*)이다!
			totEmpCount = session.selectOne("com.oracle.oBootMyBatis01.EmpMapper.empTotal");
			System.out.println("EmpDaoImpl totalEmp totEmpCount-> "+totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp e.getMessage()->"+e.getMessage());
		}

		return totEmpCount;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp Start...");
		try {
			//ID가 unique여야한다. (PK처럼 생각해야한다.)
			//								Map ID		parameter
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList.size()-> "+empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp e.getMessage()-> "+e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
//		2. EmpDao   detailEmp method 선언 
////    mapper ID   ,    Parameter
		//emp = session.selectOne("tkEmpSelOne",    empno);
		//System.out.println("emp-> "+emp1);
		
		Emp emp = new Emp();
		System.out.println("EmpDaoImpl detailEmp Start...");
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detailEmp emp-> "+emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detailEmp e.getMessage()-> "+e.getMessage());
		}
		return emp;
	}

	@Override
	public int updateEmp(Emp emp) {
//   2. EmpDao updateEmp method 선언
									//	    mapper ID   ,    Parameter
			//updateCount = session.update("tkEmpUpdate",   emp);
		int updateCount = 0;
		try {
			updateCount = session.update("tkEmpUpdate", emp);
			System.out.println("EmpDaoImpl updateEmp updateCount-> "+updateCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl updateEmp e.getMessage()-> "+e.getMessage());
		}
		return updateCount;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listManager Start...");
		try {
			// emp 관리자만 select				Naming Rule
			empList = session.selectList("tkSelectManager");
			System.out.println("EmpDaoImpl listManager empList-> "+empList);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listManager e.getMessage()-> "+e.getMessage());
		}
		
		return empList;
	}

	@Override
	public int insertEmp(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insertEmp Start...");
		try {
			result = session.insert("insertEmp", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insertEmp e.getMessage()-> "+e.getMessage());
		}
		return result;
	}

	@Override
	public int deleteEmp(int empno) {
		int result = 0;
		System.out.println("EmpDaoImpl deleteEmp Start...");
		System.out.println("EmpDaoImpl deleteEmp empno-> "+empno);
		try {
			result = session.delete("deleteEmp", empno);
			result = session.delete("EmpDaoImpl deleteEmp result-> "+result);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deleteEmp e.getMessage()-> "+e.getMessage());
		}
		return result;
	}

	@Override
	public int condTotalEmp(Emp emp) {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl condTotalEmp Start...");
		try {
			totEmpCount = session.selectOne("condEmpTotal", emp);
			System.out.println("EmpDaoImpl condTotalEmp totEmpCount-> "+totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl condTotalEmp e.getMessage()-> "+e.getMessage());
		}
		
		return totEmpCount;
	}

	@Override
	public List<Emp> empSearchList3(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl empSearchList3 Start...");
		try {
			empList = session.selectList("tkEmpSearchList3", emp);
			System.out.println("EmpDaoImpl empSearchList3 empList-> "+empList);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl empSearchList3 e.getMessage()-> "+e.getMessage());
		}
		return empList;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = null;
		System.out.println("EmpDaoImpl listEmpDept Start...");
		try {
			listEmpDept = session.selectList("tkListEmpDept");
			System.out.println("EmpDaoImpl listEmpDept listEmpDept size()-> "+listEmpDept.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpDept e.getMessage()-> "+e.getMessage());
		}
		return listEmpDept;
	}


	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl deptName Start...");
		String resultStr = "";
		
		try {
			System.out.println("EmpDaoImpl deptName deptno-> "+deptno);
			resultStr = session.selectOne("tkDeptName", deptno);
			System.out.println("EmpDaoImpl deptName resultStr-> "+resultStr);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptName Exception-> "+e.getMessage());
		}
		return resultStr;
	}

}
