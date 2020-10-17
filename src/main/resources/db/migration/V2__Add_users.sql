insert into security (id, email, first_name, last_name, password, role, status)
    values (1,'admin@admin.com','admin','admin', '$2y$12$TG4G4h5ijTqdXitCCteN8.wu1V0KXp6mCXKnC6SmnyJECbaDT1FMi','ADMIN','ACTIVE');
insert into security (id, email, first_name, last_name, password, role, status)
values (2,'user@user.com','admin','admin', '$2y$12$TG4G4h5ijTqdXitCCteN8.wu1V0KXp6mCXKnC6SmnyJECbaDT1FMi','USER','ACTIVE');
SELECT setval('security_id_seq', 4);
