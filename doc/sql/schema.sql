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

/* TODOテーブル作成 */
CREATE TABLE IF NOT EXISTS private_todo.todo_items (
		id INT AUTO_INCREMENT NOT NULL,
        	user_id INT NOT NULL,
		item_name VARCHAR(100) NOT NULL,
		registration_date DATE,
		expire_date DATE,
		finished_date DATE,
		is_deleted TINYINT NOT NULL DEFAULT 0,
		main_todo INT,
		tag_id INT,
		create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
		update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		PRIMARY KEY (id)
);

/* 設定テーブル作成 */
CREATE TABLE IF NOT EXISTS private_todo.settings (
		id INT AUTO_INCREMENT NOT NULL,
        	user_id INT NOT NULL,
            	customize_key VARCHAR(50) NOT NULL,
            	customize_value VARCHAR(50) NOT NULL,
            	create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
		update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		PRIMARY KEY (id)
);

/* タグテーブル作成 */
CREATE TABLE IF NOT EXISTS private_todo.tags (
		id INT AUTO_INCREMENT NOT NULL,
		user_id INT NOT NULL,
	        tag_name VARCHAR(50) NOT NULL,
		tag_color VARCHAR(10) NOT NULL,
	        tag_icon VARCHAR(50) NOT NULL,
		is_deleted TINYINT NOT NULL DEFAULT 0,
		create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
		update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		PRIMARY KEY (id)
);
