insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'艾禾','4LZT-4.6Z',100,'260',4.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'艾禾','4LZT-4.0ZB',85,'200',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'艾禾','4LZT-2.0ZL',50,'200',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'艾禾','4LZT-5.0ZA',100,'240',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'艾禾','4LZT-4.0ZA',100,'213',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'春雨','4LZ-6',140,'250',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'春雨','4LZ-5',125,'250',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'春雨','4LZ-7',140,'260',7);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'春雨','4LZ-8CZ',140,'275',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'大丰王','4LZ-2',40,'180',1.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'大丰王','4LZ-2.6',92,'236',2.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'大丰王','4LZ-1.5',50,'180',1.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'大丰王','4LZ-3.0',75,'251',3);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'大丰王','4LZ-4.0',88,'220',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'大丰王','4LZ-2.0',65,'220',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东风','4LZ-5.2Z',80,'220/240',5.2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东风井关','HF608',60,'145',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东风井关','HF608G',60,'145',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东风井关','HF608H',60,'145',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东华','4LBZ-150/150B',65,'150',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东华','4LZ-5.5Z',100,'220',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东华','4LZ－2.5Z',85,'200',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东华','4LZ-4.0Z',85,'220',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东华','4LZ-2.5Y',70,'200',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'东华','4LZ-3.5',80,'200',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'江苏东洋','CF690G',96,'198',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'江苏东洋','C704G',70.8,'145',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'江苏东洋','CF585',86.9,'170',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'江苏东洋','HL6060C',60,'150',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'江苏东洋','4LZ-2.5(TC758)',75,'200',2.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='久保田', model_number='4LBZ-145C(PRO588-I)', model_power=60, width=145, cut_num=42924 where id=1482804954324521;
update user_machine_model set model_brand='久保田', model_number='4LZB-105(PRO208)', model_power=20, width=105, cut_num=null where id=1482804954324519;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'久保田','4LBZ-145G(PRO588I-G)',60,'145',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1117);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='久保田', model_number='4LBZ-145(PRO488)', model_power=48, width=145, cut_num=null where id=1482804954324520;
update user_machine_model set model_brand='久保田', model_number='4LBZ-172B(PRO888GM)', model_power=88, width=172, cut_num=null where id=1482804954324523;
update user_machine_model set model_brand='久保田', model_number='4LZ-5(PRO100)', model_power=100, width=260, cut_num=5 where id=1482804954324557;
update user_machine_model set model_brand='久保田', model_number='4LZ-2.5(PRO688Q)', model_power=68, width=200, cut_num=2.5 where id=1482804954324524;
update user_machine_model set model_brand='久保田', model_number='4LZ-4（PRO988Q)', model_power=98, width=230, cut_num=4 where id=1482804954324525;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'巨明','6.0/7.0',110,'258',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'巨明','4L-2.8型',60,'236',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'巨明','4LZ-2',90,'325',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'巨明','4LZ-6.0',null,'236/258/275',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'巨明','4LZ-3',65,'238',3);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'科乐收','TUCANO 320',279,'770',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'科乐收','TUCANO 340',279,'770',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'科乐收','TUCANO 450',299,'922',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'科乐收','TUCANO 570',326,'922',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'科乐收','TUCANO 440',279,'922',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'科乐收','TUCANO 430',258,'922',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='雷沃', model_number='RG50(4LZ-5G)', model_power=73, width=200/220, cut_num=5 where id=1482804954324586;
update user_machine_model set model_brand='雷沃', model_number='4LZ-6F', model_power=145, width=320, cut_num=6 where id=1482804954324587;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'雷沃','4LZ-4G1',88,'200',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1122);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='雷沃', model_number='RC35(4LZ-3.5C1)', model_power=75, width=200, cut_num=3.5 where id=1482804954324589;
update user_machine_model set model_brand='雷沃', model_number='RF50(4LZ-5F)', model_power=120, width=310, cut_num=5 where id=1482804954324588;
update user_machine_model set model_brand='雷沃', model_number='RF40(4LZ-4F)', model_power=85, width=288, cut_num=4 where id=1482804954324591;
update user_machine_model set model_brand='雷沃', model_number='RB20(4LZ-2B)', model_power=65, width=200, cut_num=2 where id=1482804954324590;
update user_machine_model set model_brand='雷沃', model_number='RC18(4LZ-1.8)', model_power=55, width=180, cut_num=1.8 where id=1482804954324592;
update user_machine_model set model_brand='雷沃', model_number='4LZ-3.5（DF288）', model_power=88, width=288, cut_num=3.5 where id=1482804954324596;
update user_machine_model set model_brand='雷沃', model_number='4LZ-3（DC238）', model_power=74, width=238, cut_num=3 where id=1482804954324598;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'雷沃','RC40(4LZ-4C)',88,'200',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1122);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='雷沃', model_number='4LZ-3.5(DF290A)', model_power=90, width=290, cut_num=3.5 where id=1482804954324594;
update user_machine_model set model_brand='雷沃', model_number='RG25(4LZ-2.5G)', model_power=75, width=200, cut_num=2.5 where id=1482804954324593;
update user_machine_model set model_brand='雷沃', model_number='4LZ-3（DC220）', model_power=65, width=220, cut_num=3 where id=1482804954324599;
update user_machine_model set model_brand='雷沃', model_number='RE25(4LZ-2.5E)', model_power=75, width=210, cut_num=2.5 where id=1482804954324584;
update user_machine_model set model_brand='雷沃', model_number='4LZ-3（DE238）', model_power=75, width=238, cut_num=3 where id=1482804954324595;
update user_machine_model set model_brand='雷沃', model_number='4LZ-3（DE258）', model_power=75, width=258, cut_num=3 where id=1482804954324597;
update user_machine_model set model_brand='雷沃', model_number='GK80(4LZ-8)', model_power=185, width=457, cut_num=8 where id=1482804954324528;
update user_machine_model set model_brand='雷沃', model_number='GF60(4LZ-6F2)', model_power=140, width=256/275, cut_num=6 where id=1482804954324561;
update user_machine_model set model_brand='雷沃', model_number='GE70(4LZ-7E1)', model_power=140, width=256, cut_num=7 where id=1482804954324563;
update user_machine_model set model_brand='雷沃', model_number='GE60(4LZ-6E3)', model_power=145, width=325, cut_num=6 where id=1482804954324560;
update user_machine_model set model_brand='雷沃', model_number='GE40(4LZ-4E1)', model_power=102, width=236, cut_num=4 where id=1482804954324565;
update user_machine_model set model_brand='雷沃', model_number='GE60(4LZ-6E2)', model_power=133, width=256, cut_num=6 where id=1482804954324527;
update user_machine_model set model_brand='雷沃', model_number='GN120(4LZ-12)', model_power=210, width=534, cut_num=12 where id=1482804954324564;
update user_machine_model set model_brand='雷沃', model_number='GE50(4LZ-5E)', model_power=105, width=250, cut_num=5 where id=1482804954324526;
update user_machine_model set model_brand='雷沃', model_number='GF35', model_power=100, width=250, cut_num=3.5 where id=1482804954324571;
update user_machine_model set model_brand='雷沃', model_number='GF40', model_power=100, width=325, cut_num=4 where id=1482804954324570;
update user_machine_model set model_brand='雷沃', model_number='GF50', model_power=125, width=325, cut_num=5 where id=1482804954324569;
update user_machine_model set model_brand='雷沃', model_number='GN70（4LZ-7N）', model_power=170, width=457, cut_num=7 where id=1482804954324529;
update user_machine_model set model_brand='雷沃', model_number='GE60(4LZ-6E7)', model_power=145, width=256, cut_num=6 where id=1482804954324567;
update user_machine_model set model_brand='雷沃', model_number='RG40(4LZ-4G1)', model_power=88, width=200, cut_num=4 where id=1482804954324585;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'牧神','4LZ-8',190,'457',8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-4.0Z',75,'200',14.4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-2.8',50,'220',2.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-3.0S',55,'200',10.8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-2.0B',45,'201',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-3.0Z',55,'202',10.8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-2.0',65,'203',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'三联','4LZ-3.0Z',62,'204',10.8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='沃得', model_number='4LB-150', model_power=62, width=145, cut_num=null where id=1482804954324553;
update user_machine_model set model_brand='沃得', model_number='DR150A', model_power=70, width=150, cut_num=null where id=1482804954324552;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'沃得','4LB-150A',62.5,'145/150',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1119);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='沃得', model_number='巨龙DR55系列', model_power=123, width=320, cut_num=5.5 where id=1482804954324547;
update user_machine_model set model_brand='沃得', model_number='捷龙4LZ-4.5L', model_power=75, width=200, cut_num=4.5 where id=1482804954324531;
update user_machine_model set model_brand='沃得', model_number='4LZ-5.0E', model_power=72, width=220, cut_num=5 where id=1482804954324545;
update user_machine_model set model_brand='沃得', model_number='飞龙4LZ-4.0A', model_power=85, width=200, cut_num=4 where id=1482804954324546;
update user_machine_model set model_brand='沃得', model_number='4LZ-4.0E', model_power=85, width=200, cut_num=4 where id=1482804954324532;
update user_machine_model set model_brand='沃得', model_number='迅龙4LZ-1.6E', model_power=31, width=136, cut_num=1.6 where id=1482804954324549;
update user_machine_model set model_brand='沃得', model_number='4LZ-8.0', model_power=125, width=260/280, cut_num=8 where id=1482804954324551;
update user_machine_model set model_brand='沃得', model_number='巨龙DR60系列', model_power=150, width=390, cut_num=6 where id=1482804954324548;
update user_machine_model set model_brand='沃得', model_number='4LZ-6.0K', model_power=100, width=230/250, cut_num=6 where id=1482804954324550;
update user_machine_model set model_brand='沃得', model_number='DC50(4LZ-4)', model_power=100, width=300, cut_num=4 where id=1482804954324530;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-2.5T',75,'200',2.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-4.2S',99,'220',4.2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-3.6Q',82,'200',3.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-4.2Z',88,'220',4.2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-3.6Z',88,'20',3.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-3.6S',82,'200',3.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-4.6S',99,'260',4.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZ-2.0T',55,'200',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZY-3.5Z',88,'220',3.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZY-3.5S',99,'220',3.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LZY-1.5S',61,'200',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'星光','4LL-2.0D',60,'200',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'洋马','AG600',60,'140',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1116);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1118);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='洋马', model_number='AW6120', model_power=120, width=198, cut_num=null where id=1482804954324600;
update user_machine_model set model_brand='洋马', model_number='AG600G', model_power=60, width=140, cut_num=null where id=1482804954324533;
update user_machine_model set model_brand='洋马', model_number='Ce-2M（AW500）', model_power=48, width=140, cut_num=null where id=1482804954324602;
update user_machine_model set model_brand='洋马', model_number='Ce-1M', model_power=35, width=140, cut_num=null where id=1482804954324605;
update user_machine_model set model_brand='洋马', model_number='Ee-60', model_power=14, width=60, cut_num=null where id=1482804954324604;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'洋马','4LZ-2.5',70,'180',2.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1118);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='洋马', model_number='4LZ-1.8A(AW70GY)', model_power=70, width=206, cut_num=null where id=1482804954324534;
update user_machine_model set model_brand='洋马', model_number='4LZ-2.8（AW82GRW)', model_power=82, width=206, cut_num=2.8 where id=1482804954324535;
update user_machine_model set model_brand='洋马', model_number='A70G(4LZ-2.5A)', model_power=70, width=197.5, cut_num=null where id=1482804954324601;
update user_machine_model set model_brand='洋马', model_number='AW85G(4L-2.8A)', model_power=85, width=197.5, cut_num=null where id=1482804954324536;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'勇猛','4LY-8(ZL1130)',325,null,null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'勇猛','4YL-6（ZL2150）',220,null,null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'勇猛','4YL-8(ZL3150)',270,null,null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'勇猛','4YL-5（ZL5150）',160,null,null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中联','4LZ-2.6',80,'275',2.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中联','4LZ-2A',90,'236',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中联','4YLZ-2A',90,'236',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中联','4LZ-3.0',100,'275',3);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中联','4LZ-3.6',90,'325',3.6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中联','4LZ-3A',110,'250',3);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-7A',160,'345',7);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-12',191,'549',12);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-8A',160,'345/360',8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-10',202,'534',10);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-7(D7140)',133,'255',7);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-8B（D8160)',118,'280',8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-6B2',115,'275',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-7(D7150)',140,'255/275',7);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'中收','4LZ-6B1',110,'250',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='谷王', model_number='TA30(4LZ-3A)', model_power=90, width=236, cut_num=3 where id=1482804954324510;
update user_machine_model set model_brand='谷王', model_number='TB60（4LZ-6B）', model_power=125, width=251, cut_num=6 where id=1482804954324516;
update user_machine_model set model_brand='谷王', model_number='TB70(4LZ-7B)', model_power=125, width=275, cut_num=7 where id=1482804954324517;
update user_machine_model set model_brand='谷王', model_number='TC70(4LZ-7C)', model_power=140, width=350, cut_num=7 where id=1482804954324518;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','TC60(4LZ-6C)',null,'290',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','TC80(4LZ-8CZ)',null,'350',8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','TA50(4LZ-5A)',110,'236',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','4LZ-8BZ1',160,'275/290',8);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PH50(4LZ-5.0H)',120,'320',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PH40(4LZ-4.0H)',110,'290',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='谷王', model_number='PQ35(4LZ-3.5QA)', model_power=90, width=200, cut_num=3.5 where id=1482804954324515;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PQ40(4LZ-4.0QA)',95,'220',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PQ40(4LZ-4.0QB)',90,'246',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PH60',150,'320',6);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PH35(4LZ-3.5HA)',90,'290',3.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PL50(4LZT-5.0Z)',100,'200/220',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PL50(4LZT-5.0ZC)',100,'200',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PQ50(4LZT-5.0QC)',70,'200/220',5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PL40(4LZT-4.0ZE)',75,'180',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PL40(4LZT-4.0ZC)',95,'200',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PL40(4LZ－4.0ZA)',88,'220',4);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PL30(4LZ－3.0ZA)',82,'220',3);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PQ25(4LZ-2.5Q)',72,'200',2.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'谷王','PT25(4LZ-2.5)',75,'200',2);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1121);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@no_id);
insert into option_machine_model_mapping value (@machine_id,1123);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','S660',320,'762/910',null);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='约翰迪尔', model_number='C100', model_power=140, width=366/457, cut_num=6 where id=1482804954324537;
update user_machine_model set model_brand='约翰迪尔', model_number='C110', model_power=160, width=457, cut_num=7 where id=1482804954324538;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','C120',165,'457',9);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','C230',200,'540',11);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','C240',238,'534/457',13);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','C440',238,'540/660',13);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='约翰迪尔', model_number='W70', model_power=108, width=381/381, cut_num=3.5 where id=1482804954324539;
update user_machine_model set model_brand='约翰迪尔', model_number='W80', model_power=117, width=320/366/457, cut_num=5 where id=1482804954324540;
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','W230',185,'457/540',7);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','L60',80,'250/275',3.5);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
insert into user_machine_model
(state,user_machine_type_id,model_brand,model_number,model_power,width,cut_num) value
(400,3,'约翰迪尔','L50',76,'275',3);
set @machine_id=(select LAST_INSERT_ID());
insert into option_machine_model_mapping value (@machine_id,1120);
insert into option_machine_model_mapping value (@machine_id,1115);
set @yes_id=(select id from option_detail where option_name='是');
set @no_id=(select id from option_detail where option_name='否');
insert into option_machine_model_mapping value (@machine_id,@yes_id);
insert into option_working_type_machine_model_mapping value(@machine_id,1116);
insert into option_working_type_machine_model_mapping value(@machine_id,1115);
update user_machine_model set model_brand='东禾', model_number='4LZ-4.0Z', model_power=98, width=200, cut_num=4 where id=1482804954324542;