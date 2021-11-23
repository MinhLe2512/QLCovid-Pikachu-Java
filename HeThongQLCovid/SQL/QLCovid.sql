CREATE TABLE ql_user(
	username VARCHAR(10) NOT NULL,
	user_password BINARY(64) NOT NULL,
	user_role NVARCHAR(20) NOT NULL,
	user_validation VARCHAR(1),
	PRIMARY KEY(username)
);
ALTER TABLE ql_user 
ADD CONSTRAINT check_role CHECK (user_role = 'admin' or user_role = 'user' or user_role = 'supevisor');
ALTER TABLE ql_user
ADD CONSTRAINT check_validation CHECK (user_validation = 'Y' or user_validation = 'N');  

CREATE TABLE covid_patient(
	citizen_id VARCHAR(10) NOT NULL,
	full_name NVARCHAR(50) NOT NULL,
	date_of_birth DATE NOT NULL,
	citizen_address NVARCHAR(100) NOT NULL,
	condition VARCHAR(2),
	treatment_place_id NVARCHAR(10),
	related_to VARCHAR(10),
	PRIMARY KEY(citizen_id),
	CONSTRAINT FK_PATIENT_ID FOREIGN KEY(citizen_id) 
	REFERENCES ql_user(username),
	CONSTRAINT FK_RELATED_TO FOREIGN KEY(related_to) 
	REFERENCES covid_patient(citizen_id)
);
ALTER TABLE covid_patient ADD CONSTRAINT check_condition CHECK (condition = 'F1' or condition = 'F2'or condition ='F3' or condition = 'F0');  

CREATE TABLE package(
	package_id VARCHAR(10) NOT NULL,
	name NVARCHAR(50) NOT NULL,
	limit INT,
	package_start DATE,
	package_end DATE,
	price INT NOT NULL,
	PRIMARY KEY(package_id) 
);
ALTER TABLE package ADD CONSTRAINT check_order_date CHECK (package_start <= package_end);  
CREATE TABLE ql_order(
	order_id VARCHAR(10) NOT NULL,
	customer_id VARCHAR(10) NOT NULL,
	package_id VARCHAR(10) NOT NULL,
	order_date DATE,
	CONSTRAINT FK_CUSTOMER_ORDER FOREIGN KEY(customer_id)
	REFERENCES ql_user(username),
	CONSTRAINT FK_PACKAGE_ORDER FOREIGN KEY(package_id)
	REFERENCES package(package_id),
	PRIMARY KEY(order_id)
);
CREATE TABLE treatment_place(
	treatment_place_name NVARCHAR(100) NOT NULL,
	treatment_place_id VARCHAR(10) NOT NULL,
	capacity INT,
	current_holding INT,
	PRIMARY KEY(treatment_place_id)
);
ALTER TABLE treatment_place ADD CONSTRAINT check_capacity CHECK (current_holding <= capacity);  

CREATE TABLE patient_history(
	patient_history_id INT NOT NULL IDENTITY(1, 1),
	patient_id VARCHAR(10) NOT NULL,
	patient_action NVARCHAR(100),
	patient_date DATETIME,
	CONSTRAINT FK_PATIENT_HISTORY_ID FOREIGN KEY(patient_id)
	REFERENCES covid_patient(citizen_id),
	PRIMARY KEY(patient_history_id)
);
CREATE TABLE edit(
	edit_history_id INT NOT NULL IDENTITY(1, 1),
	supevisor_id VARCHAR(10) NOT NULL,
	supevisor_action NVARCHAR(100),
	supevisor_date DATETIME,
	CONSTRAINT FK_SUPEVISOR_HISTORY_ID FOREIGN KEY(supevisor_id)
	REFERENCES covid_patient(citizen_id),
	PRIMARY KEY(edit_history_id)
);
CREATE TABLE province(
	province_id INT NOT NULL IDENTITY(1, 1),
	province_name NVARCHAR(50)
	PRIMARY KEY(province_id)
)
CREATE TABLE district(
	district_id INT NOT NULL IDENTITY(1, 1),
	district_name NVARCHAR(50)
	PRIMARY KEY(district_id)
)
CREATE TABLE province_has_district(
	province_id INT,
	district_id INT
	CONSTRAINT FK_PRO_ID FOREIGN KEY(province_id) 
	REFERENCES province(province_id),
	CONSTRAINT FK_DIS_ID FOREIGN KEY(district_id) 
	REFERENCES district(district_id),
	PRIMARY KEY(province_id, district_id)
)
CREATE TABLE ward(
	ward_id INT NOT NULL IDENTITY(1, 1),
	ward_name NVARCHAR(50),
	PRIMARY KEY(ward_id)
)
CREATE TABLE district_has_ward(
	district_id INT,
	ward_id INT,
	CONSTRAINT FK_DIS2_ID FOREIGN KEY(district_id) 
	REFERENCES district(district_id),
	CONSTRAINT FK_WARD_ID FOREIGN KEY(ward_id) 
	REFERENCES ward(ward_id),
	PRIMARY KEY(ward_id, district_id)
)





--alter table covid_patient
--drop constraint FK_RELATED_TO, FK_PATIENT_ID
--alter table patient_history
--drop constraint FK_PATIENT_HISTORY_ID 
--alter table edit
--drop FK_SUPEVISOR_HISTORY_ID
--alter table ql_order
--drop FK_CUSTOMER_ORDER, FK_PACKAGE_ORDER

--drop table patient_history
--drop table ql_order
--drop table covid_patient
--drop table package
--drop table edit
--drop table treatment_place
--drop table ql_user
--DROP TRIGGER trigger_patient_history
GO
CREATE TRIGGER trigger_patient_history
ON covid_patient
AFTER UPDATE
AS 
BEGIN
	SET NOCOUNT ON;
	INSERT INTO patient_history(patient_id, patient_date) 
	SELECT cp.citizen_id, GETDATE()
	FROM covid_patient cp
END;

INSERT INTO ql_user VALUES('0323812311','12345678', 'User', null)
INSERT INTO covid_patient VALUES('0323812311', 'Testing 1', '12/12/2019', N'đâu cũng được', null, null, null)
UPDATE covid_patient
SET condition = 'F1'
WHERE citizen_id = '0323812311'

select * 
from patient_history

