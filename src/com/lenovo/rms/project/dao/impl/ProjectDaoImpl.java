package com.lenovo.rms.project.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;

@Repository("projectDao")
public class ProjectDaoImpl extends HibernateBaseDaoImpl<Project, Long> implements IProjectDao {

    @Override
    public List<Project> getAllProjects() {
        return findAll();
    }

    public static void main(String[] args) {
        /*
         * ApplicationContext ctx = new
         * ClassPathXmlApplicationContext("spring-servlet.xml");
         * 
         * System.out.println("Strat"); ProjectDaoImpl dao =
         * ctx.getBean(ProjectDaoImpl.class); List<Project> projects =
         * dao.getOwnedProjects("yangym"); for(Project p:projects){
         * System.out.println(p.getProjectName()); }
         */
    }

    @Override
    public Project getByProjectNo(String projectNo) {
        Criterion condition = Restrictions.eq("projectNo", projectNo);
        Project p= findUnique(condition);
        return p;
    }

    @Override
    public List<String> getByItLeader(String itCode) {
        String hql = "select projectNo from ProjectOwner w where w.projectItLeader=:projectItLeader";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectItLeader", itCode);
        List<String> projects = findHql(hql, params, String.class);
        return projects;
    }

    @Override
    public List<String> getProjectMembers(String projectNo) {
        String hql = "from ProjectMember w where w.projectNo=:projectNo";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectNo", projectNo);
        List<String> projects = findHql(hql, params, String.class);
        return projects;
    }

}
