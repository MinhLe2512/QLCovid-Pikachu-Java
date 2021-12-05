--lưu tài khoản
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
--khu điều trị thì lưu trên đây tạo file như back end làm cái gì
CREATE TABLE treatment_place(
	treatment_place_name NVARCHAR(100) NOT NULL,
	treatment_place_id VARCHAR(10) NOT NULL,
	capacity INT,
	current_holding INT,
	PRIMARY KEY(treatment_place_id)
);
--bênh nhân
CREATE TABLE covid_patient(
	citizen_id VARCHAR(10) NOT NULL,
	full_name NVARCHAR(50) NOT NULL,
	date_of_birth DATE NOT NULL,
	citizen_address NVARCHAR(100) NOT NULL,
	condition VARCHAR(2),
	treatment_place_id VARCHAR(10),
	related_to VARCHAR(10),
	balance BIGINT,
	PRIMARY KEY(citizen_id),
	CONSTRAINT FK_PATIENT_ID FOREIGN KEY(citizen_id) 
	REFERENCES ql_user(username),
	CONSTRAINT FK_RELATED_TO FOREIGN KEY(related_to) 
	REFERENCES covid_patient(citizen_id),
	CONSTRAINT FK_TREATMENT_PLACE FOREIGN KEY(treatment_place_id)
	REFERENCES treatment_place(treatment_place_id)
);
ALTER TABLE covid_patient ADD CONSTRAINT check_condition CHECK (condition = 'F1' or condition = 'F2'or condition ='F3' or condition = 'F0');  

--tài khoản chính nhận tiền
CREATE TABLE main_acc(
	main_id VARCHAR(10),
	money_sum BIGINT
);

--gói hàng
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
--quản lý order 
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

ALTER TABLE treatment_place ADD CONSTRAINT check_capacity CHECK (current_holding <= capacity);  
--lịch sử của bệnh nhân
CREATE TABLE patient_history(
	--tự tăng lên 1 ko cần insert
	patient_history_id INT NOT NULL IDENTITY(1, 1),
	patient_id VARCHAR(10) NOT NULL,
	patient_action NVARCHAR(100),
	patient_date DATETIME,
	CONSTRAINT FK_PATIENT_HISTORY_ID FOREIGN KEY(patient_id)
	REFERENCES covid_patient(citizen_id),
	PRIMARY KEY(patient_history_id)
);
--lịch sử chỉnh sửa của supevisor
CREATE TABLE edit(
	--tự tăng lên 1 ko cần insert
	edit_history_id INT NOT NULL IDENTITY(1, 1),
	supevisor_id VARCHAR(10) NOT NULL,
	supevisor_action NVARCHAR(100),
	supevisor_date DATETIME,
	CONSTRAINT FK_SUPEVISOR_HISTORY_ID FOREIGN KEY(supevisor_id)
	REFERENCES ql_user(username),
	PRIMARY KEY(edit_history_id)
);
--lịch sử thanh toán
CREATE TABLE payment_history(
--tự tăng lên 1 ko cần insert
	payment_history_id INT NOT NULL IDENTITY(1, 1),
	citizen_id VARCHAR(10),
	payment_date DATETIME,
	payment_amount BIGINT
	CONSTRAINT FK_CITIZEN_PAYMENT FOREIGN KEY(citizen_id)
	REFERENCES covid_patient(citizen_id),
	PRIMARY KEY(payment_history_id)
)
--lưu tỉnh mắc gì phải gán foreign key cho địa chỉ
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






INSERT INTO ql_user VALUES('0323812311','12345678', 'User', null)
INSERT INTO covid_patient VALUES('0323812311', 'Testing 1', '12/12/2019', N'đâu cũng được', null, null, null)
UPDATE covid_patient
SET condition = 'F1'
WHERE citizen_id = '0323812311'

select * 
from patient_history

