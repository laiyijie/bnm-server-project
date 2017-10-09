update user_machine_model set width='220/240'
where model_brand='东风' and  model_number='4LZ-5.2Z';

update user_machine_model set width='236/258/275'
where model_brand='巨明' and  model_number='4LZ-6.0';

update user_machine_model set width='200/220'
where model_brand='雷沃' and  model_number='RG50(4LZ-5G)';

update user_machine_model set width='256/275'
where model_brand='雷沃' and  model_number='GF60(4LZ-6F2)Z';

update user_machine_model set width='145/150'
where model_brand='沃得' and  model_number='4LB-150A';

update user_machine_model set width='260/280'
where model_brand='沃得' and  model_number='4LZ-8.0';

update user_machine_model set width='230/250'
where model_brand='沃得' and  model_number='4LZ-6.0K';

update user_machine_model set width='345/360'
where model_brand='中收' and  model_number='4LZ-8A';

update user_machine_model set width='255/275'
where model_brand='中收' and  model_number='4LZ-7(D7150)';

update user_machine_model set width='255/275'
where model_brand='谷王' and  model_number='4LZ-8BZ1';

update user_machine_model set width='200/220'
where model_brand='谷王' and  model_number='PL50(4LZT-5.0Z)';

update user_machine_model set width='200/220'
where model_brand='谷王' and  model_number='PQ50(4LZT-5.0QC)';

update user_machine_model set width='762/910'
where model_brand='约翰迪尔' and  model_number='S660';

update user_machine_model set width='366/457'
where model_brand='约翰迪尔' and  model_number='C100';

update user_machine_model set width='534/457'
where model_brand='约翰迪尔' and  model_number='C240';

update user_machine_model set width='540/660'
where model_brand='约翰迪尔' and  model_number='C440';

update user_machine_model set width='381/381'
where model_brand='约翰迪尔' and  model_number='W70';

update user_machine_model set width='320/366/457'
where model_brand='约翰迪尔' and  model_number='W80';

update user_machine_model set width='457/540'
where model_brand='约翰迪尔' and  model_number='W230';

update user_machine_model set width='250/275'
where model_brand='约翰迪尔' and  model_number='L60';

DELETE FROM user_machine_model WHERE id =1482804954324522;
DELETE FROM user_machine WHERE machine_model_id= 1482804954324522;

DELETE FROM user_machine_model WHERE id =1482804954324562;
DELETE FROM user_machine WHERE machine_model_id= 1482804954324562;