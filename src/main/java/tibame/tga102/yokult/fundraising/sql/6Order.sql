-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 建立訂單資料
create table `fund_order`(
	`orderID` integer auto_increment not null primary key comment '訂單編號',
	`order_Invoice_Number` varchar(11) not null unique comment '發票編號',
	`order_Date_Time` datetime not null comment '訂單時間',
	`order_Amount` integer not null comment '訂單金額',
	`proposalID` integer not null comment '提案編號',
	`planID` integer not null comment '方案編號',
	`postID` integer not null comment '寄送資訊編號',
	`memID` varchar(50) not null comment '會員帳號',
	foreign key(`proposalID`) references `Fund_PROPOSAL`(`proposalID`),
	foreign key(`planID`) references `Fund_PLAN`(`planID`),
	foreign key(`postID`) references `Fund_POST`(`postID`),
	foreign key(`memID`) references `MEMBER`(`MEMID`)
);

-- 存入資料
insert into `fund_order`(
	`order_Invoice_Number`,
	`order_Date_Time`,
	`order_Amount`,
	`proposalID`,
	`planID` ,
	`postID`,
	`memID`
)values

-- 1 偏鄉牙醫志工團｜為偏鄉學童巡迴牙齒健檢
('TG-100001', '2022-07-20 00:00:00', 200, 1, 1, 1, 'TGA001'),
('TG-100002', '2022-07-21 00:00:00', 500, 1, 2, 2, 'TGA002'),
('TG-100003', '2022-07-22 00:00:00', 5000, 1, 3, 3, 'TGA003'),
('TG-100004', '2022-07-22 00:00:00', 5000, 1, 3, 4, 'TGA004'),
('TG-100005', '2022-07-22 00:00:00', 5000, 1, 3, 5, 'TGA005'),
('TG-100006', '2022-07-22 00:00:00', 5000, 1, 3, 6, 'TGA001'),
('TG-100007', '2022-07-22 00:00:00', 5000, 1, 3, 7, 'TGA002'),


-- 2 2023微笑曲線計畫｜為每個人打造獨一無二的微笑曲線
('TG-100008', '2022-07-20 00:00:00', 30000, 2, 4, 1, 'TGA001'),
('TG-100009', '2022-07-21 00:00:00', 30000, 2, 4, 2, 'TGA002'),
('TG-100010', '2022-07-22 00:00:00', 30000, 2, 5, 3, 'TGA003'),
('TG-100011', '2022-07-22 00:00:00', 150000, 2, 5, 4, 'TGA004')

-- 3 環保牙刷計畫 | 刷牙也要用最天然的
-- 尚未開始無訂單
