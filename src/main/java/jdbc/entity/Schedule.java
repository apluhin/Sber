package jdbc.entity;

import java.time.LocalDateTime;

public class Schedule {
    private final int id;
    private final LocalDateTime timeLesson;
    private final int room;
    private final String subject;

    public Schedule(int id, LocalDateTime localDateTime, int room, String subject) {
        this.id = id;
        this.timeLesson = localDateTime;
        this.room = room;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimeLesson() {
        return timeLesson;
    }

    public int getRoom() {
        return room;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", timeLesson=" + timeLesson +
                ", room=" + room +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (id != schedule.id) return false;
        if (room != schedule.room) return false;
        if (timeLesson != null ? !timeLesson.equals(schedule.timeLesson) : schedule.timeLesson != null) return false;
        return subject != null ? subject.equals(schedule.subject) : schedule.subject == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (timeLesson != null ? timeLesson.hashCode() : 0);
        result = 31 * result + room;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
