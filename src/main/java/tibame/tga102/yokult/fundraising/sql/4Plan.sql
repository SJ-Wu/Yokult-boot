-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 建立方案資料
create table `fund_plan`(
	`planID` integer auto_increment not null primary key comment '方案編號',
	`plan_Name` varchar(50) not null comment '方案名稱',
	`plan_Amount` integer not null comment '方案金額',
	`plan_Content` varchar(300) not null comment '方案內容',
	`plan_Post_Note` varchar(100) not null comment '寄送備註',
    `plan_Post_Date` date comment '方案寄送時間',
	`plan_Started_Date_Time` date not null comment '方案開始時間',
	`plan_Ended_Date_Time` date not null comment '方案結束時間',
    `plan_Picture` mediumblob  comment '方案圖檔案',
	`proposalID` integer not null comment '提案編號',
    foreign key(`proposalID`) references `Fund_PROPOSAL`(`proposalID`)
);

-- 存入資料

insert into `fund_plan`(
	`plan_Name`,
	`plan_Amount`,
	`plan_Content`,
	`plan_Post_Note`,
    `plan_Post_Date`,
	`plan_Started_Date_Time`,
	`plan_Ended_Date_Time`,
    `plan_Picture`,
	`proposalID`
) values

-- 1 偏鄉牙醫志工團｜為偏鄉學童巡迴牙齒健檢
('純贊助',200,'備註：僅代表團隊感謝您的贊助','無回饋',null,
'2022-06-19 00:00:00','2023-07-19 00:00:00','',1),
('感謝小卡',500,'備註：感謝小卡將由我們親自設計','只寄送台灣本島',
'2022-09-19 00:00:00','2022-06-19 00:00:00','2022-07-19 00:00:00','',1),
('大力贊助 + 紀念T恤',5000,'備註：紀念T恤為國寶設計師XXX設計','只寄送台灣本島',
'2028-03-25 00:00:00','2022-07-25 00:00:00','2027-12-25 00:00:00','',1),

-- 2 2023微笑曲線計畫｜為每個人打造獨一無二的微笑曲線
('瓷牙貼片',30000,'備註：瓷牙貼片多色可選','直接到診所',
'2023-09-19 00:00:00','2022-07-19 00:00:00','2023-07-19 00:00:00','',2),
('瓷牙貼片 + 齒列矯正',150000,'備註：瓷牙貼片多色可選，並使用AI+3D技術精準定位牙齒','直接到診所',
'2023-09-19 00:00:00','2025-07-19 00:00:00','2023-07-19 00:00:00','',2),

-- 3 環保牙刷計畫 | 刷牙也要用最天然的
('純贊助',200,'備註：僅代表團隊感謝您的贊助','無回饋',null,
'2026-08-01 00:00:00','2026-09-01 00:00:00','',3),
('馬細毛環保牙刷 x 4',1200,'備註：天然材質細微變色為正常現象','只寄送台灣本島',
'2026-08-01 00:00:00','2026-09-01 00:00:00','2026-12-01 00:00:00','',3),
('馬極纖細毛牙刷 x 2',3000,'備註：天然材質細微變色為正常現象','只寄送台灣本島',
'2026-08-01 00:00:00','2026-09-01 00:00:00','2026-12-01 00:00:00','',3)