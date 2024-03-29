
	--Table BANK--

create table bank (bank_id int not null, bank_name varchar(255) not null, bank_address varchar(255) not null);
create sequence bank_id_seq start with 1 increment by 1;
alter table bank add constraint bank_id_seq primary key(bank_id);




--Tbale BRANCH--

create table branch(branch_id int not null, branch_name varchar(255) not null,branch_address varchar(255) not null,branch_ifsc varchar(255) not null, bank_id int not null); 
create sequence branch_id_seq start with 101 increment by 1;
alter table branch add constraint branch_id_seq primary key(branch_id);
alter table branch add foreign key(bank_id) references bank(bank_id);






--Table ROLE--

create table role(role_id int not null, role_name varchar(255) not null,role_desc varchar(255) not null,role_status varchar(255) not null, branch_id int not null, username varchar(255) not null, password varchar(255) not null); 
create sequence role_id_seq start with 111 increment by 1;
alter table role add constraint role_seq primary key(role_id);
alter table role add foreign key(branch_id) references branch(branch_id);





--Table ADMIN loans--
create table loan_scheme(loan_scheme_id int not null,loan_scheme_type varchar(255) not null,loan_scheme_name varchar(255) not null,loan_scheme_desc varchar(255) not null, loan_scheme_roi float not null); 
create sequence loan_scheme_seq start with 5000 increment by 1;
alter table loan_scheme add constraint loan_scheme_seq primary key(loan_scheme_id);



--Table CUSTOMER--

create table customer(customer_id int not null,customer_name varchar(255) not null,customer_address varchar(255) not null, customer_status varchar(255) not null, customer_contact int not null, username varchar(255) not null, password varchar(255) not null); 
create sequence customer_id_seq start with 1 increment by 1;
alter table customer add constraint customer_id_seq primary key(customer_id);






--Table PROIFLES--

create table profile(profile_id int not null,profile_pan varchar(255) not null,profile_aadhaar int not null, profile_status varchar(255) not null, customer_id int not null); 
create sequence profile_id_seq;
alter table profile add constraint profile_id_seq primary key(profile_id);
alter table profile add foreign key(customer_id) references customer(customer_id);







--Table Account--
create table account(account_id int not null,account_type varchar(255) not null,account_number int not null, account_avl_balance float not null,account_status varchar(255) not null, customer_id int not null,branch_id int not null); 
create sequence account_id_seq start with 2001 increment by 1;
alter table account add constraint account_id_seq primary key(account_id);
alter table account add foreign key(customer_id) references customer(customer_id);
alter table account add foreign key(branch_id) references branch(branch_id);
alter table account modify (account_number unique);


--Table TRANSACTION--
create table transaction(txn_id int not null,txn_from int not null,txn_to int not null,txn_amount float not null, txn_status varchar(255) not null, trans_date DATE not null); 
create sequence txn_id_seq start with 86545965 increment by 1;
alter table transaction add constraint txn_id_seq primary key(txn_id);
alter table transaction add foreign key(txn_from) references account(account_number);
alter table transaction add foreign key(txn_to) references account(account_number);


--Table PAYEE--

create table payee(payee_id int not null,payee_name varchar(255) not null,payee_account_number int not null,customer_id int not null);
create sequence payee_id_seq;
alter table payee add constraint payee_id_seq primary key(payee_id);
alter table payee add foreign key(customer_id) references customer(customer_id);
alter table payee add foreign key(payee_account_number) references account(account_number);



--Table Loan-project--
create table loan(loan_App_id int not null, loan_amount float not null, loan_emi float not null,loan_scheme_id int not null,customer_id int not null); 
create sequence loan_App_id_seq start with 90034 increment by 1;
alter table loan add constraint loan_App_id_seq primary key(loan_App_id);
alter table loan add foreign key(customer_id) references customer(customer_id);
alter table loan add foreign key(loan_scheme_id) references loan_scheme(loan_scheme_id);

--insert values--

insert into bank values(BANK_ID_SEQ.nextval, 'Alpha Bank', 'Jaynagar,Ring road,Banglore-583101');


insert into branch values(BRANCH_ID_SEQ.nextval, 'Infantry', '8th block MSC towers,Ballari-583260','ALPB86556',BANK_ID_SEQ.currval);
insert into branch values(BRANCH_ID_SEQ.nextval, 'Avenue road', '2nd block,4th main Opposite to kaveri bhavan,Banglore-583102','ALPB86051',BANK_ID_SEQ.currval);
insert into branch values(BRANCH_ID_SEQ.nextval, 'VijayaNagar', 'Club road 12th block,Manglore-564190','ALPB860438',BANK_ID_SEQ.currval);


insert into role values(role_id_seq.nextval, 'Admin', 'Has all privileges','active',101,'apoorva','appu12');
insert into role values(role_id_seq.nextval, 'Official', 'Managing operations','active',101,'anusha','anu12');

insert into role values(role_id_seq.nextval, 'Admin', 'Has all privileges','active',102,'sirisha','siri12');
insert into role values(role_id_seq.nextval, 'Official', 'Managing operations','active',102,'manoj','manu12');

insert into role values(role_id_seq.nextval, 'Admin', 'Has all privileges','active',103,'girish','giri12');
insert into role values(role_id_seq.nextval, 'Official', 'Managing operations','active',103,'john','jo12');


insert into loan_scheme values(loan_scheme_seq.NEXTVAL, 'education loan', 'vidhyasiri','students persuing degree', 9.5);
insert into loan_scheme values(loan_scheme_seq.NEXTVAL, 'vehicle loan', 'vahana raksa','customer having cibil>700 can apply', 6.5);
insert into loan_scheme values(loan_scheme_seq.NEXTVAL, 'gold loan', 'siri','accepts even 18 carot gold', 11.5);
insert into loan_scheme values(loan_scheme_seq.NEXTVAL, 'home  loan', 'ghura kshema','offers loan upto 40lakh', 5.5);


insert into customer values(customer_id_seq.nextval,'karthik','Gandhi nagar,Ballari','active',6361333806,'karthi','karthi12');
insert into customer values(customer_id_seq.nextval,'Afsha','vidhya nagar,Banglore','active',6376543106,'afsh','affu12');
insert into customer values(customer_id_seq.nextval,'Nandish','indira nagar,Gulbarga','active',7434567667,'nandi','nandi12');
insert into customer values(customer_id_seq.nextval,'Avinash','siddharth nagar,Kolar','active',9876544561,'avi','avi12');

insert into account values(account_id_seq.nextval,'savings',4384357894567,25000.83,'active',1,101);
insert into account values(account_id_seq.nextval,'current',5458954578876,09273.83,'active',1,102);

insert into account values(account_id_seq.nextval,'savings',2543923872387,9839.73,'active',2,102);
insert into account values(account_id_seq.nextval,'savings',2635678628373,96829.3,'active',2,103);

insert into account values(account_id_seq.nextval,'savings',9786529456394,3228783.28,'active',3,101);
insert into account values(account_id_seq.nextval,'savings',5392634974387,873822.171,'active',3,103);

insert into account values(account_id_seq.nextval,'savings',7362837839382,2892.26,'active',4,102);
insert into account values(account_id_seq.nextval,'savings',2362878273283,873832.882,'active',4,103);

insert into profile values(profile_id_seq.nextval,'HYRE56802',547987654478,'active',1);
insert into profile values(profile_id_seq.nextval,'NCJS86567',445677678889,'active',2);
insert into profile values(profile_id_seq.nextval,'BSXS23456',192839438492,'active',3);
insert into profile values(profile_id_seq.nextval,'IUEK34675',236488372989,'active',4);

insert into transaction values(txn_id_seq.nextval,4384357894567,2635678628373,1093.4,'successful','12-mar-2023');
insert into transaction values(txn_id_seq.nextval,5458954578876,9786529456394,15.0,'failed','24-apr-2023');

insert into transaction values(txn_id_seq.nextval,2543923872387,4384357894567,300.4,'successful','12-mar-2023');
insert into transaction values(txn_id_seq.nextval,2635678628373,2362878273283,15.0,'successful','24-apr-2023');

insert into transaction values(txn_id_seq.nextval,9786529456394,5458954578876,854523.4,'failed','12-mar-2023');
insert into transaction values(txn_id_seq.nextval,5392634974387,2543923872387,34523.34,'successful','24-apr-2023');

insert into transaction values(txn_id_seq.nextval,7362837839382,9786529456394,1000.0,'successful','12-mar-2023');
insert into transaction values(txn_id_seq.nextval,2362878273283,5392634974387,45872.87,'successful','24-apr-2023');

insert into payee values(payee_id_seq.nextval,'Deepthi',2635678628373,1);
insert into payee values(payee_id_seq.nextval,'Asif',9786529456394,1);

insert into payee values(payee_id_seq.nextval,'Goutham',4384357894567,2);
insert into payee values(payee_id_seq.nextval,'Kethan',2362878273283,2);

insert into payee values(payee_id_seq.nextval,'Faizu',5458954578876,3);
insert into payee values(payee_id_seq.nextval,'Razak',2543923872387,3);


insert into payee values(payee_id_seq.nextval,' Guru',9786529456394,4);
insert into payee values(payee_id_seq.nextval,'Aruna',5392634974387,4);



insert into loan values(loan_App_id_seq.NEXTVAL, 50000.00, 7800.653, 5000, 1);
insert into loan values(loan_App_id_seq.NEXTVAL, 100000.00, 7800.653, 5002, 1);
insert into loan values(loan_App_id_seq.NEXTVAL, 250000.00, 7800.653, 5003, 2);
insert into loan values(loan_App_id_seq.NEXTVAL, 64000.00, 7800.653, 5001, 4);
insert into loan values(loan_App_id_seq.NEXTVAL, 70000.00, 7800.653, 5003, 3);


