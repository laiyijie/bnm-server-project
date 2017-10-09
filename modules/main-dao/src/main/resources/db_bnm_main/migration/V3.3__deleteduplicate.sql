DELETE FROM user_machine_model WHERE id=1482804954324603;
UPDATE user_machine SET
machine_model_id=1482804954324533
WHERE machine_model_id=1482804954324603;

DELETE FROM option_machine_model_mapping   where model_id =1482804954324603;

DELETE FROM option_working_type_machine_model_mapping where machine_model_id =1482804954324603;