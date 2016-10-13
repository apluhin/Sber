package jdbc.entity;

public class Visit {
    private final int student_id;
    private final int scheduled_id;

    public Visit(int student_id, int scheduled_id) {
        this.student_id = student_id;
        this.scheduled_id = scheduled_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getScheduled_id() {
        return scheduled_id;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "student_id=" + student_id +
                ", scheduled_id=" + scheduled_id +
                '}';
    }
}
