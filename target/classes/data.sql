insert into tb_address (city, district, number, street, id)
values ('Test_City', 'Test_District', '11', 'Test_Street', 'd0f76d54-4696-4ef5-af8f-d7adbe8a18bf');
insert into tb_client (address_id, cellphone, email, name, id)
values ('d0f76d54-4696-4ef5-af8f-d7adbe8a18bf', '11911111111', 'testing@testing.com', 'Test_1','e91dcb40-bef8-4e65-b16e-b334211e0731');
--insert into tb_equipment (id, type, brand, client_id, order_id) values ('5a2fe4d5-f602-477b-b8bc-744f95b01dfe', 'Eletronic', 'Samsung','e91dcb40-bef8-4e65-b16e-b334211e0731', null);