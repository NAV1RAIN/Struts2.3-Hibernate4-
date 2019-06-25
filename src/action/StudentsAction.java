package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.Students;
import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

//ѧ��Action��
public class StudentsAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date birthday;
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	//��ѯ����ѧ������
	public String query() {
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		//�Ž�session��
		if(list != null && list.size() > 0) {
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}
	
	//ɾ��ѧ������
	public String delete() {
		StudentsDAO sdao = new StudentsDAOImpl();
		String sid = request.getParameter("sid");
		sdao.deleteStudents(sid); //����ɾ������
		return "delete_success";
	}
	
	//���ѧ������
	public String add(){
		Students s = new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));

		s.setBirthday(birthday);
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao = new StudentsDAOImpl();
		sdao.addStudents(s);
		return "add_success";
	}
	

	//�޸�ѧ�����϶���
	public String modify() {
		//��ô��ݹ�����ѧ�����
		String sid = request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = sdao.queryStudentsBySid(sid);
		//�����ڻỰ��
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	
	//�����޸ĺ��ѧ�����϶���
	public String save() throws Exception {
		Students s = new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));

		s.setBirthday(birthday);
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao = new StudentsDAOImpl();
		sdao.updateStudents(s);
		return "save_success";
	}
}
