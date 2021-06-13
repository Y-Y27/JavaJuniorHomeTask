CREATE TABLE IF NOT EXISTS user_account
(
	id int8 DEFAULT nextval('user_account_id_seq'::regclass) NOT NULL
		CONSTRAINT user_account_pk
			PRIMARY KEY,
	username VARCHAR(16) NOT NULL
		constraint user_account_username_uindex
			unique,
	firstname VARCHAR(16) NOT NULL,
	lastname VARCHAR(16) NOT NULL,
	password VARCHAR(255),
	role VARCHAR(20) DEFAULT 'USER' NOT NULL,
	status VARCHAR(20) DEFAULT 'ACTIVE',
	createdat TIMESTAMP (6)
);

