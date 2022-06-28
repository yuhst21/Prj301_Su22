/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecture;
import model.Room;
import model.Session;
import model.Slot;
import model.Subject;

/**
 *
 * @author win
 */
public class SessionDBContext extends DBContext<Session> {

    GroupDBContext gdb = new GroupDBContext();
    LectureDBContext ldb = new LectureDBContext();
    SubjectDBContext sdb = new SubjectDBContext();

    @Override
    public ArrayList<Session> list() {

        ArrayList<Session> session = new ArrayList<>();
        try {
            String sql = "select s.sessionid,s.roomid,r.roomname,s.slotid,sl.slotname,g.gid,g.gname,g.lid,g.lid,s.[date] from [Session] s \n"
                    + "inner join Room r on s.roomid = r.roomid\n"
                    + "inner join Slot sl on s.slotid = sl.slotid\n"
                    + "inner join [Group] g on s.gid = g.gid";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setSessionid(rs.getInt("sessionid"));
                Room r = new Room();
                r.setRoomid(rs.getInt("roomid"));
                r.setRoomname(rs.getString("roomname"));
                s.setRoom(r);
                Slot sl = new Slot();
                sl.setSlotid(rs.getInt("slotid"));
                sl.setSlotname(rs.getString("slotname"));
                s.setSlot(sl);
                Group g = new Group();
                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                Lecture l = new Lecture();
                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                g.setLec(ldb.get(l));
                Subject sub = new Subject();
                sub.setSubjectid(rs.getInt("subjectid"));
                sub.setSubjectname(rs.getString("subjectname"));
                g.setSub(sdb.get(sub));
                s.setDate(rs.getDate("date"));
                session.add(s);

            }
            return session;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Session get(Session entity) {
        try {
            String sql = "select s.sessionid,s.roomid,r.roomname,s.slotid,sl.slotname,g.gid,g.gname,g.lid,g.lid,s.[date] from [Session] s \n"
                    + "inner join Room r on s.roomid = r.roomid\n"
                    + "inner join Slot sl on s.slotid = sl.slotid\n"
                    + "inner join [Group] g on s.gid = g.gid\n"
                    + "where s.sessionid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSessionid());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setSessionid(rs.getInt("sessionid"));
                Room r = new Room();
                r.setRoomid(rs.getInt("roomid"));
                r.setRoomname(rs.getString("roomname"));
                s.setRoom(r);
                Slot sl = new Slot();
                sl.setSlotid(rs.getInt("slotid"));
                sl.setSlotname(rs.getString("slotname"));
                s.setSlot(sl);
                Group g = new Group();
                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                Lecture l = new Lecture();
                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                g.setLec(ldb.get(l));
                Subject sub = new Subject();
                sub.setSubjectid(rs.getInt("subjectid"));
                sub.setSubjectname(rs.getString("subjectname"));
                g.setSub(sdb.get(sub));
                s.setDate(rs.getDate("date"));
                return s;

            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
