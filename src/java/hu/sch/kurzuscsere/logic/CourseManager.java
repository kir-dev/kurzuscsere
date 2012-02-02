package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.CCRequest;
import hu.sch.kurzuscsere.domain.Lesson;
import hu.sch.kurzuscsere.logic.db.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author balo
 */
public class CourseManager {

    private static final Logger log = LoggerFactory.getLogger(CourseManager.class);

    private CourseManager() {
    }

    public synchronized static CourseManager getInstance() {
        return CourseManager.CourseManagerHolder.INSTANCE;
    }

    private static class CourseManagerHolder {

        private static final CourseManager INSTANCE = new CourseManager();
    }

    public void instertRequest(CCRequest req) {

        Connection conn = DbHelper.getConnection();

        String sqlCCRequest = "INSERT INTO ccrequests (usr_id, course_from_id, status) VALUES (?, ?, ?) ";
        String sqlLessons_courses = "INSERT INTO lessons_courses (lesson_id, course_id) VALUES (?, ?) ";
        String sqlCourses = "INSERT INTO courses(c_code) VALUES (?)";
        try {

            PreparedStatement stmtCourses = conn.prepareStatement(sqlCourses);

            List<String> Courses = req.getTo();

            for (Iterator<String> it = Courses.iterator(); it.hasNext();) {
                String course_code = it.next();
                if (course_code != null) {
                    stmtCourses.setString(1, course_code);
                    stmtCourses.executeUpdate();
                }
            }

            stmtCourses.close();
            
//                PreparedStatement stmtCCRequest = conn.prepareStatement(sqlCCRequest);
//                PreparedStatement stmtLessons_courses = conn.prepareStatement(sqlLessons_courses);
            
            conn.close();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CourseManager.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    /**
     *
     * @param limit
     * @param status
     * @return
     */
    public List<CCRequest> getLastRequests(int limit, CCRequest.Status status) {

        Connection conn = DbHelper.getConnection();
        if (conn == null) {
            return Collections.EMPTY_LIST;
        }

        String statusFilter = "";
        if (status != null) {
            statusFilter = "WHERE ccrequests.status = ? ";
        }

        List<CCRequest> results = new LinkedList<CCRequest>();

        String sql = "SELECT ccrequests.id AS ccId, "
                + "ccrequests.status AS reqStatus, "
                + "ccrequests.lesson_id AS lessonId, "
                + "ccrequests.course_from_code AS fromCourseCode, "
                + "lessons.ls_name AS lessonName, "
                + "lessons.ls_code AS lessonCode "
                + "FROM ccrequests "
                + "INNER JOIN lessons ON (lessons.id = ccrequests.lesson_id) "
                + statusFilter
                + "ORDER BY ccId DESC "
                + "LIMIT ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            if (statusFilter.isEmpty()) {
                stmt.setInt(1, limit);
            } else {
                stmt.setString(1, status.toString());
                stmt.setInt(2, limit);
            }

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                CCRequest ccReq = new CCRequest();
                ccReq.setId(res.getLong("ccid"));

                //lesson
                Lesson lesson = new Lesson();
                lesson.setId(res.getLong("lessonId"));
                lesson.setName(res.getString("lessonName"));
                lesson.setClassCode(res.getString("lessonCode"));
                ccReq.setLesson(lesson);
                //from
                ccReq.setFromCourse(res.getString("fromCourseCode"));
                //owner
                ccReq.setUser(null);
                //status
                ccReq.setStatus(CCRequest.Status.valueOf(res.getString("reqStatus")));
                //to
                List<String> toList =
                        CourseManager.getInstance().getCcRequestToListById(conn, ccReq.getId());
                ccReq.setTo(toList);

                results.add(ccReq);
            }
            stmt.close();
            //
            conn.close();
        } catch (SQLException ex) {
            log.error("Can't get requests", ex);
        }

        return results;
    }

    /**
     * Lekéri egy kurzuscsere-kéréshez tartozó "amikre cserélne" listát
     *
     * @param conn db kapcsolat, ha
     * <pre>null</pre>, akkor elkér egy új kapcsolatot
     * @param ccReqId
     * @return
     */
    public List<String> getCcRequestToListById(Connection conn, final Long ccReqId) {
        List<String> results = new LinkedList<String>();
        boolean closeConnection = true;

        if (conn == null) {
            conn = DbHelper.getConnection();

            if (conn == null) {
                return results;
            }
        } else {
            //kívülről kaptuk a kapcsolatot, ne bontsuk
            closeConnection = false;
        }

        String sql = "SELECT course_to_code "
                + "FROM ccrequests_to_courses "
                + "WHERE ccreq_id = ? "
                + "ORDER BY course_to_code ASC";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ccReqId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                results.add(res.getString("course_to_code"));
            }
            stmt.close();
            //
            if (closeConnection) {
                conn.close();
            }
        } catch (SQLException ex) {
            log.error("Can't get 'to list' from id: " + ccReqId, ex);
        }

        return results;
    }
}
