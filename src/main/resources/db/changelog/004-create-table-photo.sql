CREATE TABLE nix.photo (
    id BIGINT NOT NULL PRIMARY KEY
    , student_id BIGINT NOT NULL
    , url VARCHAR(255) NOT NULL
    , FOREIGN KEY (student_id) REFERENCES nix.student(id)
)