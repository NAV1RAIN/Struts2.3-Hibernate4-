package service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDAO;

//ѧ��ҵ���߼��ӿڵ�ʵ����
public class StudentsDAOImpl implements StudentsDAO {

	//��ѯ����ѧ������
	@Override
	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Students> list = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Students";
			Query query = session.createQuery(hql);
			
			list = query.list();
			tx.commit();
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return list;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}

	@Override
	public Students queryStudentsBySid(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Students stu = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			stu = (Students)session.get(Students.class, sid);
			tx.commit();
			return stu;
		} catch(Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return stu;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean addStudents(Students s) {
		// TODO Auto-generated method stub
		s.setSid(getNewSid()); //����ѧ����ѧ��
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return false;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean updateStudents(Students s) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return false;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean deleteStudents(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
//		String hql = "";
		
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students s = (Students)session.get(Students.class, sid);
			session.delete(s);
			tx.commit();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return false;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}
	
	public String getNewSid() {
		Transaction tx = null;
		String hql = "";
		String sid = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//��õ�ǰѧ���������
			hql = "select max(sid) from Students";
			Query query = session.createQuery(hql);
			sid = (String)query.uniqueResult();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String date=simpleDateFormat.format(new Date());
			String y = date.substring(0, 4);
			if(sid == null || "".equals(sid)) {
				//��һ��Ĭ�������
				sid = y + "0001";
			} else {
				String temp = sid.substring(4); //ȡ����λ
				int i = Integer.parseInt(temp); //ת������
				i++;
				//�ٻ�ԭ���ַ���
				temp = String.valueOf(i);
				int len = temp.length();
				for(int j = 0; j < 4-len; j++) {
					temp = "0" + temp;
				}
				sid = y + temp;
			}
			tx.commit();
			return sid;
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return null;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}
	
}
