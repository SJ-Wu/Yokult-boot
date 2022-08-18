-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 建立募資提案資料
create table Fund_PROPOSAL(
	proposalID integer auto_increment not null comment '提案編號',
	proposalName varchar(40) not null comment '提案名稱',
	proposalHostName varchar(70) not null comment '提案人姓名',
	proposalGoal integer not null comment '目標金額',
	proposalCategoryID varchar(2) not null comment '分類編號',
	proposalStartedDateTime datetime not null comment '提案開始時間',
	proposalEndedDateTime datetime not null comment '提案結束時間',
	statusID varchar(2) not null comment '階段編號',
	proposalEmail varchar(320) not null comment '電子信箱',
	proposalCellphone varchar(10) not null comment '手機號碼',
	proposalSummary varchar(500) not null comment '提案概要',
	proposalPageContent blob not null comment '頁面內容檔案',
	memID varchar(50) not null comment '會員帳號'
);

-- 提案類別
create table Fund_CATEGORY(
	categoryID varchar(2) not null comment '分類編號',
	categoryName varchar(10) not null comment '分類名稱'
);

-- 提案階段
create table Fund_STATUS(
	statusID varchar(2) not null comment '階段編號',
	statusName varchar(10) not null comment '階段名稱'
);

-- 建立方案資料
create table Fund_PLAN(
	planID Integer auto_increment not null primary key comment '方案編號',
	planName varchar(50) not null comment '方案名稱',
	planAmount integer not null comment '方案金額',
	planContent varchar(300) not null comment '方案內容',
	planPostNote varchar(100) not null comment '寄送備註',
	planStartedTime datetime not null comment '方案開始時間',
	planEndedTime datetime not null comment '方案結束時間',
	statusID varchar(2) not null comment '階段編號',
	proposalID integer not null comment '提案編號'
);

-- 建立寄送資訊
create table Fund_PostInfo(
	postID integer auto_increment not null comment '寄送資訊編號',
	postFisrtName varchar(50) not null comment '名字',
	postLastName varchar(20) not null comment '姓氏',
	postCellphone varchar(10) not null comment '手機號碼',
	cityID varchar(2) not null comment '縣市編號',
	districtID varchar(3) not null comment '區域編號',
	postAddress varchar(100) not null comment '寄送地址',
	memID varchar(50) not null comment '會員帳號'
);

-- 建立縣市資料
create table Fund_CITY(
	cityID varchar(2) not null primary key comment '縣市編號',
	cityName varchar(3) not null comment '縣市名稱'
);

-- 建立區域資料
create table Fund_DISTRICT(
	districtID varchar(3) not null primary key comment '區域編號',
	districtName varchar(4) not null comment '區域名稱'
);

-- 建立訂單資料
create table Fund_ORDER(
orderID integer auto_increment not null primary key comment '訂單編號',
orderInvoiceNumber varchar(10) not null comment '發票編號',
orderTimeStampt datetime not null comment '訂單時間',
orderAmount integer not null comment '訂單金額',
proposalID integer not null comment '提案編號',
planID integer not null comment '方案編號',
memID varchar(50) not null comment '會員帳號',
postID integer not null comment '寄送資訊編號'
);

