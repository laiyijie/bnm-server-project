UPDATE option_machine_model_mapping
SET option_id=10001
WHERE option_id IN (SELECT id FROM option_detail WHERE description="带高炮");
UPDATE option_machine_model_mapping
SET option_id=10002
WHERE option_id IN (SELECT id FROM option_detail WHERE description="不带高炮");

DELETE FROM option_cluster WHERE cluster_name="是否带高炮";
INSERT option_cluster VALUE("100","高炮要求","收割机是否带高炮");
DELETE FROM option_detail WHERE description="带高炮" OR description="不带高炮";
INSERT option_detail VALUES(10001,"带高炮",100,""),(10002,"不带高炮",100,"");