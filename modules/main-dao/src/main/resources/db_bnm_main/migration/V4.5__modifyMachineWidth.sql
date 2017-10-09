UPDATE user_machine,user_machine_model
SET user_machine.width=CONVERT(user_machine_model.width,DECIMAL)
WHERE user_machine.machine_model_id=user_machine_model.id AND user_machine_model.width NOT LIKE "%/%";
