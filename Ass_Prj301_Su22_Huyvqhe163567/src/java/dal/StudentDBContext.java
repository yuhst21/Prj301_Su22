/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Student;

/**
 *
 * @author win
 */
public class StudentDBContext extends DBContext<Student> {

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> stu = new ArrayList<>();
        try {
            String sql = "Select s.sid,s.sname,s.sgender,s.sdob,s.depid,d.depname from  Student s inner join Department d\n"
                    + "on s.depid = d.depid";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setSgender(rs.getBoolean("sgender"));
                s.setSdob(rs.getDate("sdob"));
                Department d = new Department();
                d.setDepid(rs.getInt("did"));
                d.setDepname(rs.getString("dname"));
                s.setDep(d);
                stu.add(s);
            }
            return stu;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Student get(Student entity) {
        try {
            String sql = "Select s.sid,s.sname,s.sgender,s.sdob,s.depid,d.depname from  Student s inner join Department d\n"
                    + "on s.depid = d.depid where s.sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSid());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setSgender(rs.getBoolean("sgender"));
                s.setSdob(rs.getDate("sdob"));
                Department d = new Department();
                d.setDepid(rs.getInt("did"));
                d.setDepname(rs.getString("dname"));
                s.setDep(d);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
