INSERT option_cluster(cluster_name,description) VALUE ("是否带高炮","是还是否");
SET @option_cluster =(SELECT LAST_INSERT_ID());
INSERT option_detail(option_name,cluster_id,description) VALUE("是",@option_cluster,"带高炮");
INSERT option_detail(option_name,cluster_id,description) VALUE("否",@option_cluster,"不带高炮");

DROP TABLE IF EXISTS user_machine_option_detail_mapping;
CREATE TABLE user_machine_option_detail_mapping(
user_machine_id BIGINT(20),
option_detail_id BIGINT(20),
PRIMARY KEY (user_machine_id,option_detail_id),
KEY user_machine_id (user_machine_id),
KEY option_detail_id (option_detail_id)
);

DROP TABLE IF EXISTS machine_type_option_cluster_mapping;
CREATE TABLE machine_type_option_cluster_mapping(
machine_type_id BIGINT(20),
option_cluster_id BIGINT(20),
PRIMARY KEY (machine_type_id,option_cluster_id),
KEY machine_type_id (machine_type_id),
KEY option_cluster_id (option_cluster_id)
);

-- INSERT machine_type_option_cluster_mapping VALUES
-- (3,25)
-- (3,27)
-- (3,@option_cluster)
ALTER TABLE user_machine_model ADD COLUMN width VARCHAR(256) DEFAULT 0;
ALTER TABLE user_machine_model ADD COLUMN  cut_num DOUBLE;
ALTER TABLE user_machine ADD COLUMN width DOUBLE;
ALTER TABLE user_machine ADD COLUMN integrity INT DEFAULT 0;
UPDATE user_machine SET integrity =0;