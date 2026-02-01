CREATE TABLE course_user(
    user_id uuid NOT NULL,
    course_id uuid NOT NULL,

    CONSTRAINT fk_course_user_user
        FOREIGN KEY(user_id) REFERENCES users(id),

    CONSTRAINT fk_course_user_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);