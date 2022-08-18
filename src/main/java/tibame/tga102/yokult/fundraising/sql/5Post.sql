-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 建立寄送資訊
create table `fund_post`(
	`postID` integer auto_increment not null primary key comment '寄送編號',
	`post_Fisrt_Name` varchar(50) not null comment '名字',
	`post_Last_Name` varchar(20) not null comment '姓氏',
	`post_Cellphone` varchar(10) not null comment '手機號碼',
	`post_SID` int not null comment '縣市流水號',
	`post_Address` varchar(100) not null comment '寄送地址',
	`memID` varchar(50) not null comment '會員帳號',
	foreign key(`post_SID`) references `Fund_POSTNUMBER`(`post_SID`),
	foreign key(`memID`) references `MEMBER`(`MEMID`)
);

-- 存入資料

insert into `fund_post`(
	`post_Fisrt_Name`,
	`post_Last_Name`,
	`post_Cellphone`,
	`post_SID`,
	`post_Address`,
	`memID`
) values
('二哥', '關', '0987187187', 24, '北深路二段XX號', 'TGA001'),
('三哥', '張', '0955123789', 24, '埔新街XX號', 'TGA002'),
('四哥', '李', '0955123789', 24, '文化街XX號', 'TGA003'),
('五哥', '陳', '0955789111', 24, '文化街Z1號', 'TGA004'),
('六哥', '王', '0955789122', 24, '文化街Z2號', 'TGA005'),
('七哥', '林', '0955789133', 24, '文化街Z3號', 'TGA001'),
('八哥', '吳', '0955789144', 24, '文化街Z4號', 'TGA002'),
('九哥', '許', '0955789155', 24, '文化街Z5號', 'TGA003')

