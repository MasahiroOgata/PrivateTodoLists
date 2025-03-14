/* DB作成 */
CREATE DATABASE IF NOT EXISTS private_todo;

/* ユーザーテーブル作成 */
CREATE TABLE IF NOT EXISTS private_todo.users (
    id INT AUTO_INCREMENT NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);