-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 提案階段
create table `fund_status`(
	`statusID` varchar(2) not null primary key comment '階段編號',
	`status_Name` varchar(10) not null comment '階段名稱'
);

insert into `fund_status`(
	`statusID`, 
	`status_Name`
) values
(1, '募資中'),
(2, '已結束'),
(3, '即將開始')