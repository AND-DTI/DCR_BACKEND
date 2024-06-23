DROP TABLE IF EXISTS ptdhd.ctpuserrl;
DROP TABLE IF EXISTS ptdhd.ctpuser;
DROP TABLE IF EXISTS ptdhd.ctprole;
DROP TABLE IF EXISTS ptdhd.ctpcomp;
DROP TABLE IF EXISTS ptdhd.ctpattach;
DROP TABLE IF EXISTS ptdhd.ctpint;


create table ptdhd.ctpuser (
    username char(10) NOT NULL,
    name char(100),
    userid int,
    email char(70),
    idarea char(20),
    ativo char(1),
    password char(100),
    token char(200),
    itaudsys char(40),			
    itaudusr char(10),
    itaudhst char(40),    	
    itauddt char(8),	
    itaudhr char(8),
    primary key(username)
);   



create table ptdhd.ctprole (    
    roleid int NOT NULL AUTO_INCREMENT, 
    rolename char(20),
    roledesc varchar(255),
    rolecad char(8),
    itaudsys char(40),			
    itaudusr char(10),
    itaudhst char(40),    	
    itauddt char(8),	
    itaudhr char(8),
    primary key(roleid)
);



create table ptdhd.ctpuserrl (
    username char(10) NOT NULL,
    roleid int NOT NULL,
    rolename char(20),  
    dtacad char(8),    
    FOREIGN KEY(username) REFERENCES ctpuser(username),
    FOREIGN KEY(roleid) REFERENCES ctprole(roleid),
    CONSTRAINT UC_USER_ROLE UNIQUE (username, roleid)
);



create table ptdhd.ctpcomp (	
    compid bigint NOT NULL AUTO_INCREMENT, 
	nroit char(20),	
	nomit char(70),	
	obsit varchar(250),	
	dtacad bigint(15),
	dtacads char(8),
	usrcad int,
	usrcads char(10),		
	category char(10),
	idse char(32),
	seqit int, 
	itaudsys char(40),			
	itaudusr char(10),
	itaudhst char(40),    	
	itauddt char(8),	
	itaudhr char(8),	
    PRIMARY KEY (compid)
) /*AUTO_INCREMENT = 1000*/;


	 	
create table ptdhd.ctpattach(
	atachid  bigint NOT NULL AUTO_INCREMENT,
    atachtb   char(10),
    idreg bigint,
    atachname char(40),
    atachtp char(5),
    atachpath varchar(150),
    b64file   char(47),
    pubpath   varchar(150),
    atachobs varchar(250),
    atachdata mediumblob,
	itaudsys char(40),			
	itaudusr char(10),
	itaudhst char(20),    	
	itauddt char(8),	
	itaudhr char(8),	    
    primary key(atachid)
);



/* interfaces table */
create table ptdhd.ctpint ( 
    intid bigint NOT NULL AUTO_INCREMENT ,
    idreg bigint,
    tablefrom char(10),
    tableto char(10),    
    intdesc varchar(100),
    sysfrom char(20),
    systo char(20),
    dtreply char(8),
    hrreply char(8),
    intstatus numeric(1,0), /*0: pendente, 2:em processamento. 1:processada*/
    itaudsys char(40),			
	itaudusr char(10),
	itaudhst char(20),    	
	itauddt char(8),	
	itaudhr char(8),	
    primary key(intid)
);

