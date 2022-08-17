-- 創建資料庫
CREATE DATABASE `yokult`;

USE `yokult`;

-- 會員資料
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `memid` VARCHAR(50) NOT NULL COMMENT '會員帳號',
  `email` VARCHAR(320) NOT NULL COMMENT '會員信箱',
  `password` VARCHAR(256) NOT NULL COMMENT '會員密碼',
  `name` VARCHAR(10) NOT NULL COMMENT '會員姓名',
  `birth` DATE COMMENT '會員生日',
  `cellphone` VARCHAR(10) NOT NULL COMMENT '手機號碼',
  `addr` VARCHAR(50) COMMENT '居住地址',
  `status` VARCHAR(10) NOT NULL default ('UNKNOWN'),
  PRIMARY KEY (`memid`),
  UNIQUE KEY uk_member_email(email))
COMMENT = '會員資料';


INSERT INTO 
  `member` (`memid`, `email`, `password`, `name`, `birth`, `cellphone`, `addr`, `status`)
VALUES
  ('TGA001', 'tga001@gmail.com', '123', '古天樂', '2022-04-11', '0910123456', '110台北市信義區市府路45號', 'APPROVED'),
  ('TGA002', 'tga002@gmail.com', '123', '葛仲珊', '2022-05-01', '0912345678', '407台中市西屯區惠來路二段101號', 'APPROVED'),
  ('TGA003', 'tga003@gmail.com', '123', '任容萱', '2022-05-31', '0972345678', '717台南市仁德區文華路二段66號', 'APPROVED'),
  ('TGA004', 'tga004@gmail.com', '123', '黃麗玲', '2022-06-05', '0954567890', '803高雄市鹽埕區大勇路1號', 'APPROVED'),
  ('TGA005', 'tga005@gmail.com', '123', '盧廣仲', '2022-08-25', '0987654321', '944屏東縣車城鄉後灣路2號', 'APPROVED'),
  ('TGA006', 'tga006@gmail.com', '123', '范少勛', '2022-04-11', '0919495902', '110台北市信義區市府路655號', 'APPROVED'),
  ('TGA007', 'tga007@gmail.com', '123', '曾敬華', '2022-05-01', '0914091490', '407台中市西屯區惠來路二段321號', 'APPROVED'),
  ('TGA008', 'tga008@gmail.com', '123', '金城武', '2022-01-31', '0999930190', '717台南市仁德區文華路二段766號', 'APPROVED'),
  ('TGA009', 'tga009@gmail.com', '123', '霍健華', '2022-02-05', '0999308109', '803高雄市鹽埕區大勇路10號', 'APPROVED'),
  ('TGA0010', 'tga0010@gmail.com', '123', '林俊傑', '2022-03-25', '0919304404', '944屏東縣車城鄉後灣路223號', 'APPROVED'),
  ('TGA0011', 'tga0011@gmail.com', '123', '彭于晏', '2022-09-11', '0911223345', '110台北市信義區市府路125號', 'APPROVED'),
  ('TGA0012', 'tga0012@gmail.com', '123', '王陽明', '2022-10-01', '0912245678', '407台中市西屯區惠來路二段340號', 'APPROVED'),
  ('TGA0013', 'tga0013@gmail.com', '123', '江蕙', '2022-10-31', '0972346478', '717台南市仁德區文華路一段790號', 'APPROVED'),
  ('TGA0014', 'tga0014@gmail.com', '123', '劉德華', '2022-12-05', '0919304511', '803高雄市鹽埕區大勇路86號', 'APPROVED'),
  ('TGA0015', 'tga0015@gmail.com', '123', '陳嘉樺', '2022-11-25', '0909451145', '944屏東縣車城鄉後灣路190號', 'APPROVED'),
  ('TGA0016', 'tga0016@gmail.com', '123', '鄧麗君', '2022-04-10', '0919034503', '110台北市信義區市府路546號', 'APPROVED'),
  ('TGA0017', 'tga0017@gmail.com', '123', '田馥甄', '2022-07-01', '0912348756', '407台中市西屯區惠來路三段399號', 'APPROVED'),
  ('TGA0018', 'tga0018@gmail.com', '123', '李玖哲', '2022-06-30', '0989406723', '717台南市仁德區文華路一段566號', 'APPROVED'),
  ('TGA0019', 'tga0019@gmail.com', '123', '韋禮安', '2022-12-05', '0912580647', '803高雄市鹽埕區大勇路189號', 'APPROVED'),
  ('TGA0020', 'tga0020@gmail.com', '123', '周杰倫', '2022-11-25', '0957905347', '944屏東縣車城鄉後灣路212號', 'APPROVED'),
  ('TGA0021', 'tga0021@gmail.com', '123', '陶喆', '2022-10-11', '0919867432', '110台北市信義區市府路415號', 'APPROVED'),
  ('TGA0022', 'tga0022@gmail.com', '123', '張惠妹', '2022-02-01', '0996029485', '407台中市西屯區惠來路五段329號', 'APPROVED'),
  ('TGA0023', 'tga0023@gmail.com', '123', '蔡依林', '2022-06-30', '0998749201', '717台南市仁德區文華路三段100號', 'APPROVED'),
  ('TGA0024', 'tga0024@gmail.com', '123', '余文樂', '2022-08-05', '0957892039', '803高雄市鹽埕區大勇路231號', 'APPROVED'),
  ('TGA0025', 'tga0025@gmail.com', '123', '吳姍儒', '2022-01-25', '0929190345', '944屏東縣車城鄉後灣路12號', 'APPROVED');
  

CREATE TABLE `product` (
  `proid` int AUTO_INCREMENT COMMENT '商品ID',
  `proname` varchar(300) NOT NULL COMMENT '商品名稱',
  `prostock` int NOT NULL COMMENT '商品庫存',
  `proprice` int NOT NULL COMMENT '商品定價',
  `prospecs` varchar(300) NOT NULL COMMENT '商品規格',
  `probrand` varchar(300) NOT NULL COMMENT '商品品牌',
  `propicture` varchar(300) NOT NULL COMMENT '商品圖片',
  `procategory` varchar(300) NOT NULL COMMENT '商品類別',
  PRIMARY KEY (`proid`)) 
  COMMENT = '商品資訊';
  
  INSERT INTO 
	`product` (`proname`, `prostock`, `proprice`, `prospecs`, `probrand`, `propicture`, `procategory`) 
VALUES 
( 'MARVIS義大利經典牙膏', '30', '339', '85ml', 'MARVIS ', 'https://raw.githubusercontent.com/Peggy79/first/main/img/000.jpg', '清潔相關'),
	('舒酸定強化琺瑯質兒童牙膏', '30', '180', '65g', '舒酸定', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images62.jpg', '清潔相關'),
	( '舒酸定專業修復牙膏', '30', '180', '100g', '舒酸定', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images63.jpg', '清潔相關'),
	( '舒酸定強效抗敏牙膏', '30', '180', '160g', '舒酸定', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images64.jpg', '清潔相關'),
	('BlackIsWhite酷黑淨白牙刷組', '30', '450', '2支', 'BlackIsWhite', 'https://raw.githubusercontent.com/Peggy79/first/main/img/222.png', '清潔相關'),
	( '獅王固齒佳超薄刷頭牙刷', '30', '109', '1支', '獅王', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images68.jpg', '清潔相關'),
	( 'EBISU惠比壽3~6歲兒童牙刷', '30', '50', '1支', 'EBISU', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images69.jpg', '清潔相關'),
	( '獅王固齒佳酵素漱口水', '20', '365', '450ml', '獅王', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images70.jpg', '清潔相關'),
	( '速可淨矯正含氟專用漱口水', '30', '250', '500ml', '速可淨', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images71.jpg', '清潔相關'),
	( '獅王NONIO終結口氣漱口水', '20', '158', '600ml', '獅王', 'https://raw.githubusercontent.com/Peggy79/first/main/img/111.jpg', '清潔相關'),
	( '3M護齒霜', '30', '299', '113g', '3M', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images66.jpg', '保健相關'),
	( '獅王固齒佳固齒牙膏', '20', '180', '130g', '獅王', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images65.jpg', '保健相關'),
	( 'Oral-B歐樂B矯正專用牙間刷', '30', '80', '1支', 'Oral-B', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images73.jpg', '保健相關'),
	( 'TePe緹碧矯正專用單頭刷', '30', '135', '1支', 'TePe', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images75.jpg', '保健相關'),
	('Oral-B歐樂B便攜型牙縫刷', '20', '189', '1盒', 'Oral-B',' https://raw.githubusercontent.com/Peggy79/first/main/img/images74.jpg', '保健相關'),
	('Oral-B深層清潔牙線', '20', '145', '1盒', 'Oral-B', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images76.jpg', '保健相關'),
	('日本UFCFLOSSY兒童木醣醇安全牙線棒', '10', '350', '1盒', 'UFCFLOSSY', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images77.jpg', '保健相關'),
	('maxill(大圈)美適牙橋穿透線', '20', '89', '4包', 'maxill', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images9.png', '保健相關'),
	('maxill(小圈)美適牙橋穿透線', '20', '89', '4包', 'maxill', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images8.png', '保健相關'),
	( 'Platypus鴨嘴獸矯正用牙線棒', '20', '330', '1包', 'Platypus', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images78.jpg', '保健相關'),
	('Panasonic噴射水流攜帶型沖牙機EW-DJ40', '10', '1800', '1台', 'Panasonic', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images79.jpg', '其他品項'),
	('Panasonic噴射水流沖牙機EW-1613-W', '10', '2900', '1台', 'Panasonic', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images81.jpg', '其他品項'),
	('Panasonic國際牌超音波水流沖牙機EW-1513-W', '10', '2680', '1台', 'Panasonic', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images82.jpg', '其他品項'),
	('Panasonic無線國際電壓沖牙機EW-1413-H', '10', '2080', '1台', 'Panasonic', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images83.jpg', '其他品項'),
	('DENTCLEAR碧利妥酵素假牙清潔錠', '15', '300', '1盒', 'DENTCLEAR', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images84.jpg', '其他品項'),
	('DENTCLEAR碧利妥綠茶假牙清潔錠', '10', '300', '1盒', 'DENTCLEAR', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images85.jpg', '其他品項'),
	('德恩奈假牙清潔錠', '10', '120', '1盒', '德恩奈', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images86.jpg', '其他品項'),
	('保麗淨局部假牙清潔錠', '10', '370', '1盒', '保麗淨', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images87.jpg', '其他品項'),
	('德恩奈口腔護具清潔錠', '10', '200', '1盒', '德恩奈', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images88.jpg', '其他品項'),
	('速口舒牙菌斑顯示劑', '20', '130', '1瓶', '速口舒', 'https://raw.githubusercontent.com/Peggy79/first/main/img/images89.jpg', '其他品項');
    
-- 訂單資料
DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `ordid` VARCHAR(50) NOT NULL COMMENT '訂單編號',
  `memid` VARCHAR(50) NOT NULL COMMENT '會員帳號',
  `paymethod` VARCHAR(50) NOT NULL COMMENT '付款方式',
  `orderstatus` VARCHAR(50) NOT NULL COMMENT '訂單狀態',
  `addr` VARCHAR(50) NOT NULL COMMENT '訂單地址',
  `receipter` VARCHAR(50) NOT NULL COMMENT '收貨人',
  `shoptime` DATETIME NOT NULL COMMENT '下單時間',
  `cellphone` VARCHAR(10) NOT NULL COMMENT '手機號碼',
  `phone` VARCHAR(10) COMMENT '市話',
  PRIMARY KEY (`ordid`),
  CONSTRAINT `fk_member_memid`
    FOREIGN KEY (`memid`)
    REFERENCES `member` (`memid`)
    ON UPDATE CASCADE);

INSERT INTO 
  `order` (`ordid`, `memid`, `paymethod`,  `orderstatus`, `addr`, `receipter`, `shoptime`, `cellphone`, `phone`) 
VALUES 
  ('2207290001', 'TGA001', 'cash', 'arrearage', '231新北市新店區民權路100號', '龍蝦', '2022-07-29 00:00:00', '0987654321', 'null'),
  ('2207290002', 'TGA002', 'creditcard', 'processing', '231新北市新店區民權路101號', '蝦蛄', '2022-07-29 01:00:00', '0912345678', 'nul'),
  ('2207290003', 'TGA003', 'payment', 'delivery', '231新北市新店區民權路102號', '波士頓龍蝦', '2022-07-29 02:00:00', '0954545454', 'null'),
  ('2207290004', 'TGA004', 'cash', 'complete', '231新北市新店區民權路103號', '撒尿蝦', '2022-07-29 03:00:00', '0978787878', 'null'),
  ('2207290005', 'TGA005', 'creditcard', 'cancel', '231新北市新店區民權路104號', '水姑娘', '2022-07-29 04:00:00', '0947234567', 'null'),
  ('2207290006', 'TGA006', 'payment', 'return', '242新北市新莊區新泰路100號', '草蝦', '2022-07-29 05:00:00', '0932121321', 'null'),
  ('2207290007', 'TGA007', 'cash', 'arrearage', '242新北市新莊區新泰路101號', '櫻花蛄', '2022-07-29 06:00:00', '0956785678', 'null'),
  ('2207290008', 'TGA008', 'creditcard', 'processing', '242新北市新莊區新泰路102號', '鰲蝦', '2022-07-29 07:00:00', '0944444777', 'null'),
  ('2207290009', 'TGA009', 'payment', 'delivery',  '242新北市新莊區新泰路103號', '泰國蝦', '2022-07-29 08:00:00', '0922222222', 'null'),
  ('22072900010', 'TGA0010', 'cash', 'complete', '242新北市新莊區新泰路104號', '蝦仁', '2022-07-29 09:00:00', '0911234525', 'null'),
  
  ('2208290001', 'TGA001', 'creditcard', 'cancel', '231新北市新店區民權路100號', '龍蝦', '2022-08-29 10:00:00', '0987654321', 'null'),
  ('2208290002', 'TGA002', 'payment', 'return', '231新北市新店區民權路101號', '蝦蛄', '2022-08-29 11:00:00', '0912345678', 'nul'),
  ('2208290003', 'TGA003', 'cash', 'arrearage', '231新北市新店區民權路102號', '波士頓龍蝦', '2022-08-29 12:00:00', '0954545454', 'null'),
  ('2208290004', 'TGA004', 'creditcard', 'processing', '231新北市新店區民權路103號', '撒尿蝦', '2022-08-29 13:00:00', '0978787878', 'null'),
  ('2208290005', 'TGA005', 'payment', 'delivery', '231新北市新店區民權路104號', '水姑娘', '2022-08-29 14:00:00', '0947234567', 'null'),
  ('2208290006', 'TGA006', 'cash', 'complete',  '242新北市新莊區新泰路100號', '草蝦', '2022-08-29 15:00:00', '0932121321', 'null'),
  ('2208290007', 'TGA007', 'creditcard', 'cancel', '242新北市新莊區新泰路101號', '櫻花蛄', '2022-08-29 16:00:00', '0956785678', 'null' ),
  ('2208290008', 'TGA008', 'payment', 'return', '242新北市新莊區新泰路102號', '鰲蝦', '2022-08-29 17:00:00', '0944444777', 'null'),
  ('2208290009', 'TGA009', 'cash', 'arrearage', '242新北市新莊區新泰路103號', '泰國蝦', '2022-08-29 18:00:00', '0922222222', 'null'),
  ('22082900010', 'TGA0010', 'creditcard', 'processing', '242新北市新莊區新泰路104號', '蝦仁', '2022-08-29 19:00:00', '0911234525', 'null'),
  
  ('2209290001', 'TGA001', 'payment', 'delivery', '231新北市新店區民權路100號', '龍蝦', '2022-09-29 20:00:00', '0987654321', 'null'),
  ('2209290002', 'TGA002', 'cash', 'complete', '231新北市新店區民權路101號', '蝦蛄', '2022-09-29 21:00:00', '0912345678', 'nul'),
  ('2209290003', 'TGA003', 'creditcard', 'cancel', '231新北市新店區民權路102號', '波士頓龍蝦', '2022-09-29 22:00:00', '0954545454', 'null'),
  ('2209290004', 'TGA004', 'payment', 'return', '231新北市新店區民權路103號', '撒尿蝦', '2022-09-29 23:00:00', '0978787878', 'null'),
  ('2209290005', 'TGA005', 'cash', 'arrearage', '231新北市新店區民權路104號', '水姑娘', '2022-09-29 20:00:00', '0947234567', 'null'),
  ('2209290006', 'TGA006', 'creditcard', 'processing', '242新北市新莊區新泰路100號', '草蝦', '2022-09-29 00:30:00', '0932121321', 'null'),
  ('2209290007', 'TGA007', 'payment', 'delivery', '242新北市新莊區新泰路101號', '櫻花蛄', '2022-09-29 01:40:00', '0956785678', 'null'),
  ('2209290008', 'TGA008', 'cash', 'complete', '242新北市新莊區新泰路102號', '鰲蝦', '2022-09-29 02:50:00', '0944444777', 'null'),
  ('2209290009', 'TGA009', 'creditcard', 'cancel', '242新北市新莊區新泰路103號', '泰國蝦', '2022-09-29 03:10:00', '0922222222', 'null'),
  ('22092900010', 'TGA0010', 'payment', 'return', '242新北市新莊區新泰路104號', '蝦仁', '2022-09-29 04:20:00', '0911234525', 'null');

CREATE TABLE `orderlist`(
	`orderlistid` INT AUTO_INCREMENT NOT NULL COMMENT '訂單商品編號',
	`proid` INT NOT NULL COMMENT '商品ID',
	`proprice` int NOT NULL COMMENT '商品價格',
	`quantity` int NOT NULL COMMENT '訂購數量',
	`ordid` varchar(50) NOT NULL COMMENT '訂單ID',
    PRIMARY KEY (`orderlistid`),
    CONSTRAINT `fk_order_ordid`
    FOREIGN KEY (`ordid`)
    REFERENCES `order` (`ordid`)
    ON UPDATE CASCADE,
    CONSTRAINT `fk_product_proid`
    FOREIGN KEY (`proid`)
    REFERENCES `product` (`proid`)
    ON UPDATE CASCADE
);

insert into `orderlist` (proid, proprice, quantity, ordid) 
values 
(1, '339', '1', '2207290001'),
(2, '180', '3', '2207290001'),
(3, '180', '5', '2207290001'),
(4, '180', '3', '2207290002'),
(5, '450', '4', '2207290002'),
(6, '109', '5', '2207290002'),
(7, '50', '9', '2207290003'),
(8, '365', '1', '2207290003'),
(9, '250', '2', '2207290003'),
(10, '158', '6', '2207290004'),
(11, '299', '1', '2207290004'),
(12, '180', '3', '2207290004'),
(13, '80', '1', '2207290005'),
(14, '135', '1', '2207290005'),
(15, '189', '1', '2207290005'),
(16, '145', '1', '2207290006'),
(17, '350', '1', '2207290006'),
(18, '89', '4', '2207290006'),
(19, '89', '4', '2207290007'),
(20, '330', '1', '2207290007'),
(21, '1800', '1', '2207290007'),
(22, '2900', '1', '2207290008'),
(23, '2680', '1', '2207290008'),
(24, '2080', '1', '2207290008'),
(25, '300', '8', '2207290009'),
(26, '300', '5', '2207290009'),
(27, '120', '3', '2207290009'),
(28, '370', '9', '22072900010'),
(29, '200', '2', '22072900010'),
(30, '130', '3', '22072900010');

create view v_admin_orderlist (ordid, orderlistid, proname, quantity) 
as select ordid, orderlistid, proname, quantity from orderlist o join product p on o.proid = p.proid;

-- 員工資料
DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `staff_id` varchar(50) NOT NULL COMMENT '員工編號',
  `staff_name` varchar(50) NOT NULL COMMENT '員工姓名',
  `staff_email` varchar(320) NOT NULL COMMENT '員工信箱',
  `staff_idnumber` varchar(50) NOT NULL COMMENT '員工身分證字號',
  `staff_birthday` datetime NOT NULL COMMENT '員工生日',
  `staff_phone` varchar(20) NOT NULL COMMENT '手機號碼',
  `staff_picture` longblob COMMENT '照片',
  `annual_leave` varchar(50)COMMENT '特休',
  `personal_leave` varchar(50)COMMENT '事假',
  `official_leave` varchar(50)COMMENT '基本假',
  PRIMARY KEY (`staff_id`))
  comment = '員工';

INSERT INTO 
  `staff` (`staff_id`, `staff_name`, `staff_email`, `staff_idnumber`, `staff_birthday`, `staff_phone`, `staff_picture`, `annual_leave`, `personal_leave`, `official_leave`)
VALUES

  ('tga000', '醫生', 'doctor@gmail.com', 'a112233445', '1988-01-01','09123456789','','','',''),
  ('tga001', '陳花花', 'flower@gmail.com', 'a223456789', '1999-01-01','0987654399','','7','5','8'),
  ('tga002', '黃泡泡', 'pop@gmail.com', 'a223456788', '1999-01-02','0987654388','','3','3','8'),
  ('tga003', '林毛毛', 'green@gmail.com', 'a223456787', '1999-01-03','0987654377','','4','9','8');
  
  
-- 刪除醫師表格 -- 
DROP TABLE IF EXISTS doctor;
-- 新增醫師表格doctor
CREATE TABLE doctor (
	`doctor_alphabet` VARCHAR(50) DEFAULT 'D00' COMMENT '醫師代表文字',
	`doctor_id` INT NOT NULL AUTO_INCREMENT COMMENT '醫師流水編號',
    `doctor_name` VARCHAR(50) NOT NULL COMMENT '醫師姓名',
    `doctor_photo` LONGBLOB COMMENT'醫師照片',
    `doctor_certificate` VARCHAR(50) COMMENT '醫師證書字號',
    `doctor_email` VARCHAR(50) COMMENT '醫師信箱',
    `doctor_password` VARCHAR(50) COMMENT '醫師密碼',
    PRIMARY KEY(doctor_id)
);

-- 刪除看診時段表格 
DROP TABLE  IF EXISTS doctor_schedule;
-- 新增看診時段表
CREATE TABLE doctor_schedule (
	`serial_number` INT NOT NULL AUTO_INCREMENT COMMENT '流水編號',
    `doctor_alphabet` VARCHAR(50) DEFAULT 'D00' COMMENT '醫師代表文字',
    `doctor_id` INT NOT NULL COMMENT '醫師編號',
    `doctor_schedule_date` date COMMENT '醫師看診日期',
    `doctor_ampm` VARCHAR(50) COMMENT '看診時段',
    `doctor_status` INT DEFAULT 1 COMMENT '看診狀態',
    PRIMARY KEY (`serial_number`),
    CONSTRAINT fk_doctor_schedule_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id)
);

-- 刪除病人表格 
DROP TABLE  IF EXISTS patient;
-- 建立病人表格 patient
CREATE TABLE patient (
	`serial_number` INT NOT NULL AUTO_INCREMENT COMMENT '流水編號',
	`memid` VARCHAR(50) COMMENT '會員帳號',
	`patient_idcard` VARCHAR(50) NOT NULL COMMENT '身分證字號',
	`booking_date` date NOT NULL COMMENT '預約日期',
	`ampm` VARCHAR(50) COMMENT '預約時段',
    `booking_number` INT  COMMENT '預約號碼',
    `doctor_alphabet` VARCHAR(50) DEFAULT 'D00' COMMENT '醫師代表文字',
    `doctor_id` INT COMMENT '醫師編號',
    `checkin_condition` INT DEFAULT 0 COMMENT '報到狀態',
    `chart` TEXT COMMENT '病歷內容',
    PRIMARY KEY(`serial_number`),
    CONSTRAINT fk_patient_MEMBER_id FOREIGN KEY (memid) REFERENCES MEMBER(memid),
    CONSTRAINT fk_patient_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id)
);

-- 新增測試資料
-- 新增醫師
INSERT INTO doctor(doctor_name, doctor_photo, doctor_certificate, doctor_email, doctor_password ) VALUES
('楊安迪' ,NULL ,'0003388', 'doctordavid@gmail.com', '123456'),
('吳大衛' ,NULL ,'0003377', 'doctorpeter@gmail.com', '123456');
-- 新增醫師看診時段
INSERT INTO doctor_schedule (doctor_id, doctor_schedule_date, doctor_ampm, doctor_status) VALUES
(1, '2022-07-12', '早', 1),
(1, '2022-07-13', '早', 1),
(1, '2022-07-14', '早', 1),
(1, '2022-07-15', '午', 1),
(1, '2022-07-16', '早', 1),
(1, '2022-07-17', '早', 1),
(1, '2022-07-18', '午', 1),
(1, '2022-07-19', '早', 1),
(1, '2022-07-20', '早', 1),
(1, '2022-07-22', '早', 1),
(1, '2022-07-22', '晚', 1),
(1, '2022-07-23', '午', 1),
(1, '2022-07-24', '晚', 1),
(1, '2022-07-27', '午', 1),
(1, '2022-07-28', '午', 1),
(1, '2022-07-29', '早', 1),
(1, '2022-08-01', '早', 1),
(1, '2022-08-02', '早', 1),
(1, '2022-08-03', '午', 1),
(1, '2022-08-04', '早', 1),
(1, '2022-08-05', '早', 1),
(1, '2022-08-09', '午', 1),
(1, '2022-08-11', '早', 1),
(1, '2022-08-13', '早', 1),
(1, '2022-08-15', '午', 1),
(1, '2022-08-16', '早', 1),
(1, '2022-08-17', '早', 1),
(1, '2022-08-18', '午', 1),
(1, '2022-08-19', '早', 1),
(1, '2022-08-20', '早', 1),
(1, '2022-08-22', '早', 1),
(1, '2022-08-22', '晚', 1),
(1, '2022-08-23', '午', 1),
(1, '2022-08-24', '晚', 1),
(1, '2022-08-27', '午', 1),
(1, '2022-08-28', '午', 1),
(1, '2022-08-29', '晚', 1),
(1, '2022-08-30', '午', 1),
(1, '2022-08-31', '午', 1),
(2, '2022-08-01', '晚', 1),
(2, '2022-08-02', '早', 1),
(2, '2022-08-03', '午', 1),
(2, '2022-08-06', '晚', 1),
(2, '2022-08-07', '晚', 1),
(2, '2022-08-08', '午', 1),
(2, '2022-08-11', '晚', 1),
(2, '2022-08-13', '晚', 1),
(2, '2022-08-14', '晚', 1),
(2, '2022-08-15', '早', 1),
(2, '2022-08-17', '早', 1),
(2, '2022-08-17', '晚', 1),
(2, '2022-08-18', '晚', 1),
(2, '2022-08-19', '晚', 1),
(2, '2022-08-20', '晚', 1),
(2, '2022-08-21', '晚', 1),
(2, '2022-08-22', '早', 1),
(2, '2022-08-22', '晚', 1),
(2, '2022-08-23', '午', 1),
(2, '2022-08-24', '晚', 1),
(2, '2022-08-27', '晚', 1),
(2, '2022-08-28', '晚', 1),
(2, '2022-08-29', '早', 1),
(2, '2022-08-30', '晚', 1),
(2, '2022-08-31', '晚', 1),
(1, '2022-09-01', '早', 1),
(1, '2022-09-02', '早', 1),
(1, '2022-09-03', '午', 1),
(1, '2022-09-04', '早', 1),
(1, '2022-09-05', '早', 1),
(1, '2022-09-09', '午', 1),
(1, '2022-09-11', '早', 1),
(1, '2022-09-13', '早', 1),
(1, '2022-09-15', '午', 1),
(1, '2022-09-16', '早', 1),
(1, '2022-09-17', '早', 1),
(1, '2022-09-18', '午', 1),
(1, '2022-09-19', '早', 1),
(1, '2022-09-20', '早', 1),
(1, '2022-09-22', '早', 1),
(1, '2022-09-22', '晚', 1),
(1, '2022-09-23', '午', 1),
(1, '2022-09-24', '晚', 1),
(1, '2022-09-27', '午', 1),
(1, '2022-09-28', '午', 1),
(1, '2022-09-29', '晚', 1),
(1, '2022-09-30', '午', 1),
(2, '2022-09-01', '晚', 1),
(2, '2022-09-02', '早', 1),
(2, '2022-09-03', '午', 1),
(2, '2022-09-06', '晚', 1),
(2, '2022-09-07', '晚', 1),
(2, '2022-09-09', '午', 1),
(2, '2022-09-11', '晚', 1),
(2, '2022-09-13', '晚', 1),
(2, '2022-09-14', '晚', 1),
(2, '2022-09-15', '早', 1),
(2, '2022-09-17', '早', 1),
(2, '2022-09-17', '晚', 1),
(2, '2022-09-18', '晚', 1),
(2, '2022-09-19', '晚', 1),
(2, '2022-09-20', '晚', 1),
(2, '2022-09-21', '晚', 1),
(2, '2022-09-22', '早', 1),
(2, '2022-09-22', '晚', 1),
(2, '2022-09-23', '午', 1),
(2, '2022-09-24', '晚', 1),
(2, '2022-09-27', '晚', 1),
(2, '2022-09-28', '晚', 1),
(2, '2022-09-29', '早', 1),
(2, '2022-09-30', '晚', 1);
-- 新增掛號資料
INSERT INTO patient(memid, patient_idcard, booking_date, ampm, booking_number, doctor_id, checkin_condition, chart) VALUES
('TGA001','A123456789', '2022-07-12', '早', 1, 1,1, "主訴:患者表示左邊第一顆門牙疼痛。
診斷:齲齒。
治療內容:根管治療。
建議:加強每日三餐牙線清潔與漱口水使用。"),
('TGA002','B123456789', '2022-07-16', '早', 1, 1,1, "主訴:定期洗牙回診
診斷:檢查後無異狀
治療內容:全口洗牙
建議:持續維持目前清潔習慣"),
('TGA001','A123456789', '2022-07-20', '早', 1, 1, 1,"主訴:患者希望牙齒變白
診斷:無。
治療內容:上排牙齒冷光美白療程
建議:第一周會較痠痛，建議搭配抗敏感牙膏使用"),
('TGA002','B123456789', '2022-07-22', '早', 1, 2, 1,"安排下周全口X光"),
('TGA001','A123456789', '2022-07-23', '午', 1, 2, 1, "下周安排洗牙"),
('TGA002','B123456789', '2022-07-27', '午', 1, 2, 1, "假牙製作衛教"),
('TGA002','B123456789', '2022-07-28', '午', 1, 2, 1, "建議持續使用電動牙刷");

-- 新增掛號未看診資料
INSERT INTO patient(memid, patient_idcard, booking_date, ampm, booking_number, doctor_id, checkin_condition, chart) VALUES
('TGA002','B123456789', '2022-08-10', '午', 1, 2, 0, ""),
('TGA002','B123456789', '2022-08-11', '午', 1, 2, 0, ""),
('TGA002','B123456789', '2022-08-12', '午', 2, 2, 0, ""),
('TGA002','B123456789', '2022-08-13', '午', 1, 2, 0, ""),
('TGA002','B123456789', '2022-08-14', '午', 1, 2, 0, ""),
('TGA002','B123456789', '2022-08-15', '午', 1, 2, 0, ""),
('TGA003','C123456789', '2022-08-08', '午', 1, 2, 0, ""),
('TGA003','C123456789', '2022-08-10', '午', 1, 2, 0, ""),
('TGA003','C123456789', '2022-08-11', '午', 1, 2, 0, ""),
('TGA003','C123456789', '2022-08-12', '午', 2, 2, 0, ""),
('TGA004','D123456789', '2022-08-13', '午', 1, 2, 0, ""),
('TGA004','D123456789', '2022-08-14', '午', 1, 2, 0, ""),
('TGA004','D123456789', '2022-08-15', '午', 1, 2, 0, ""),
('TGA004','D123456789', '2022-08-16', '午', 1, 2, 0, ""),
('TGA005','E123456789', '2022-08-17', '午', 1, 2, 0, ""),
('TGA005','E123456789', '2022-08-18', '午', 1, 2, 0, ""),
('TGA005','E123456789', '2022-08-19', '午', 1, 2, 0, ""),
('TGA005','E123456789', '2022-08-20', '午', 1, 2, 0, ""),
('TGA005','E123456789', '2022-08-08', '午', 1, 2, 0, "")
;
    
  CREATE TABLE `topic` (
  `topid` INT AUTO_INCREMENT COMMENT '文章ID',
  `title` VARCHAR(50) NOT NULL COMMENT '文章標題',
  `foreword` TEXT NOT NULL COMMENT '文章引言',
  `content` TEXT NOT NULL COMMENT '文章內文',
  `sortid` VARCHAR(256) NOT NULL COMMENT '種類編號',
  `views` INT NOT NULL COMMENT '閱覽人數',
  `posttime` DATETIME NOT NULL COMMENT '發表時間',
  `pic` LONGBLOB COMMENT '文章照片',
  PRIMARY KEY (`topid`));
    
    
    
INSERT INTO `yokult`.`topic` (`title`, `foreword`, `content`, `sortid`, `views`, `posttime`) VALUES ('9成國人有牙周病！牙科醫親授3招清潔牙齒，補充5種營養素預防牙周病', '刷牙刷到流血，小心是牙周病前兆！根據國健署調查，高達9成國人患有輕重程度不等的牙周病，且牙周病的罹患率會隨著年齡逐漸增高。', '牙科醫師黃宇艇表示，造成牙周病主要兇手是牙菌斑，牙菌斑是細菌與食物殘渣中碳水化合物產生作用，堆積在牙齒表面或是牙齦溝上的黏稠無色薄膜。3招正確清潔牙齒黃宇艇指出，牙菌斑會製造有毒的酸性物質，刺激牙齦發炎、造成流血，若不即時處理，很可能會進展成棘手牙周病。而預防牙周病最好方式是「落實正確潔牙習慣」，早晚刷牙至少5分鐘、適時使用牙線輔助清潔間隙，並養成每半年定期洗牙好習慣，才能有效預防牙周病。除此之外，營養師莊昕悅也推薦民眾從飲食中補充5種營養素預防牙周病發生。莊提醒，維生素A和維生素E是脂溶性維生素，需搭配含油脂食物一起吃才好吸收。含糖飲料和甜食容易增加蛀牙風險，也與牙周病、牙齦炎有關，建議減少精製糖攝取，並隨時做好口腔清潔。另外，營養不良會影響牙周病修復，因此營養充足均衡飲食非常重要。5營養素預防牙周病1.維生素A莊昕悅表示，維生素A會參與體內細胞分化、視覺、免疫、皮膚治療等作用，對上皮細胞形成特別重要。可以補充到維生素A的食物(1)動物性來源：如肝臟、魚類 (魚油)、蛋黃。(2)植物性來源：深綠色蔬菜、橘黃色蔬果。2.維生素C具強大抗氧化能力，能夠維持及修護結締組織。民眾若嚴重缺乏維生素C會導致壞血病，與牙周病息息相關。牙周病會增加活性氧物質，氧化及發炎等產生，所以各種抗氧化物對於維持牙齦健康都很重要。富含維生素C的食物(1)新鮮水果：紅心芭樂、芭樂、釋迦、黃金奇異果、甜柿、柳橙、草莓。(2)新鮮蔬菜：椒類 (辣椒、青椒、甜椒 )、紫高麗菜、野苦瓜、綠花椰、香椿。3.維生素E莊昕悅說，維生素E可幫助抗氧化，終止自由基傷害，協助免疫反應及維持細胞膜完整性。維生素E能從油脂、堅果種子類食物獲得(1)植物油：芝麻油、大豆油、亞麻仁油、葵花籽油、芥花油、油菜籽油。(2)堅果種子：榛果、杏仁果、芝麻、松子。4.葉酸葉酸與DNA合成，以及胺基酸代謝有關，若缺乏葉酸會影響細胞合成；也可能與牙周中細菌干擾物抵抗有關。可補充葉酸的食物：(1)動物性：肝臟、蛋黃。(2)植物性：深綠葉菜（菠菜、水蕹菜、萵苣）、韭菜、豆類、菇類。5.鈣質鈣質是骨頭、牙齒和肌肉收縮的重要成份，同時幫助支撐齒槽骨密度。長期缺鈣除會增加骨質疏鬆風險，也可能增加牙周病、蛀牙發生率。建議攝取足夠鈣質，維持骨骼及牙齒健康。富含鈣質的食物來源:(1)乳品類：鮮奶、起司、優格。(2)綠色及深綠色蔬菜：甘藍菜、花椰菜、大白菜、芥菜、莧菜。(3)黃豆類：豆花、傳統豆腐、小方豆乾、黑豆干。(4)其他：丁香魚、小魚乾、文蛤、花蛤、芝麻。', '牙周衛教', '0', '2022-07-22');
INSERT INTO `yokult`.`topic` (`title`, `foreword`, `content`, `sortid`, `views`, `posttime`) VALUES ('只是會看牙醫是沒用的！牙周病想痊癒，「這三者」缺一不可！', '從牙齦的顏色可以知道牙齒的健康狀態紅、紫是發炎的顏色，健康的牙齦是淡粉紅色。牙齦會出現緊急狀態，通常是在有害的牙周病菌入侵牙齦的時候。', '當身體感覺到異常，微血管會自動擴張，將大量血液送往牙齦。為了排除想要入侵體內的敵人，就需要血液成分軍團。這支軍團是由巨噬細胞與嗜中性白血球等所組成的白血球團隊。在牙周病菌的入侵現場，血管會稍稍打開，將嗜中性白血球與巨噬細胞送出去。這是排除異物（非自體物質）的自我防衛機能的開端。這個作用是與生俱來的機能，稱為「自然免疫（先天免疫）」。首先出場的嗜中性白血球，總之就是埋頭吞噬敵菌的免疫細胞，比起第二個出場的巨噬細胞為數更多，藉著持續認真吃掉異物來保護我們的身體。緊接著出場的巨噬細胞，也是一個發現敵人便大口大口吃掉的大飯桶細胞。不過，巨噬細胞的真正目的並不在這裡。巨噬細胞吃掉敵菌後，會分析敵菌的弱點與特徵，並將這份資訊傳送給掌管免疫的胸腺細胞（Ｔ細胞）。Ｔ細胞在接收到由巨噬細胞傳來「敵人入侵」的報告後，就會開始製造可以對抗敵菌的抗體。這種製造抗體的一連貫作業，因為是配合當時環境與狀況而自動產生出免疫力，所以稱為「適應性免疫（後天免疫）」。在巨噬細胞與嗜中性白血球不斷纏鬥的時候，大量的血液會被送往牙齦。因此便形成牙齦炎的發炎狀態，此時牙齦看起來帶有紅色。當抗體被製造完成，並且一一打敗陸續出現的牙周病菌之後，發炎的症狀就會獲得弭平，但別以為這場災難結束了，事情並非這麼簡單就能解決。巨噬細胞是吞噬所有有毒牙周病菌的重要免疫細胞，但如同前文已說明過的，巨噬細胞同時也會因為牙周病發炎的刺激，轉變成破壞骨頭的細胞。而且在巨噬細胞與嗜中性白血球每次出兵作戰的時候，都會產生大量的活性氧。也就是說，巨噬細胞打敗牙周病菌的代價，便是齒槽骨與牙齦細胞也會遭到破壞。巨噬細胞其實就是一把兩刃刀。所以，即使牙齦只是呈現出些微的紅色，也要盡快採取清潔牙齒、提高免疫力的對策，藉以降低發炎症狀的程度。如果與牙周病菌對抗的時間拉得太長，牙齦會成為一片焦土，齒槽骨的空洞化會進行，不論敵菌或免疫細胞都會死傷慘重。這時候，牙齦便會轉變成紫色。因為齒槽骨變成空洞了，會堆積了由細胞殘骸、老舊廢物、液體成分等所形成的膿，從外表看起來也呈現腫脹。你可以觸摸自己的牙齦看看。如果有觸摸到堅硬骨頭的觸感的話，那便是齒槽骨還健在的證據。齒槽骨變得空洞的牙齦，觸摸時沒有骨頭堅硬的感覺，觸覺不紮實，摸起來如同含有水分的海綿一般軟軟水水的。牙周病並沒有明顯的自覺症狀，很容易讓人以為這是一種在自己沒有發覺的期間不斷進行，卻突然出現出血、出膿等症狀的疾病。其實每天刷牙時還是可以透過觀察牙齦的顏色、用指頭觸摸牙齦等方式，來檢查自己牙齒的健康狀態。牙齦呈現淡粉紅色的話，就代表健康。如果感覺牙齦帶有紅色，就要仔細刷牙，然後再繼續觀察兩、三天。因為有發炎症狀就代表自體免疫機能正在正常的運作，只要透過清潔口腔、改善營養與睡眠，就有可能會恢復原來的淡粉紅色。但如果紅色一直沒有消退，就應該到牙科診所接受診察。另外，如果牙齦呈現紫色，就必須盡快處理。因為齒槽骨是一旦減損便無法復原的組織，耽擱越久對自己身體的傷害就越大。如果是輕度發炎，透過以特殊的尖端器具將牙菌斑剔除的洗牙治療，就可以讓牙齦恢復原來的樣子。如果是因為咬合問題導致牙周病惡化，就要透過削牙等方式來調整咬合面。如果已經發炎了，應該要採取將牙齦內部根面附著物清除的作業。根面附著物指的是附著於牙齒根部的牙菌斑或牙結石等頑固的附著物，這並不容易清除。牙醫會在患側施以局部麻醉，再以特殊洗牙機器具的尖端插入牙齒與牙齦之間，將牙菌斑和牙結石刮除。這稱為牙結石刮除或牙根整平術（root planing）。如果牙周病已經惡化，就要進行外科治療，先將牙齦翻開之後，再除去堆積在齒槽骨的結石或不良肉芽組織。罹患了牙周病，花在治療上的時間，通常會視症狀與患者本身的狀況而有不同。輕微的人可能只要到醫院診療三次就會治癒，症狀嚴重的話，就得上醫院好幾次，也需要花較長的時間來治療。如果是蛀牙治療，患者只要坐上診療台就可以閉上眼睛，全部都交給牙醫處理。但是牙周病治療，正如同我經常對患者說的，「牙醫佔一半，患者佔一半」。必須要有正確的牙齒保健觀念、健康管理，然後加上治療，三者缺一不可，牙周病才能夠痊癒。此外，也希望讀者可以透過閱讀本書，獲得牙周病與全身關係、以及照護牙齒的相關知識。關於牙齒保健與治療牙周病的詳細情形，將在第五章依據牙齦的各種狀況來進行說明。', '牙周衛教', '0', '2022-07-22');
INSERT INTO `yokult`.`topic` (`title`, `foreword`, `content`, `sortid`, `views`, `posttime`) VALUES ('兒童牙齒成長分三個階段！什麼時候矯正才是黃金階段', '牙齒生長就只有一次機會，如果長歪了就必須要靠牙齒矯正，過往都只認定牙齒矯正只跟美觀有關係，沒有迫切的需要。但臨床牙醫師提醒，牙齒排列不整齊不僅會影響到咀嚼、咬合，也可能引發蛀牙、牙周病，甚至影響到外觀，什麼時間去矯正才是對的呢？小時候做矯正真的比較好？牙醫告訴你黃金階段是這時候。', '牙醫師在臨床上很常遇到心急的家長們來詢問：「到底幾歲要開始矯正比較好呢?小朋友牙齒有縫隙」或者是：「小孩就該開始進行矯正嗎?我好擔心錯過時機點」，眼神總是充斥著各種疑問與擔心。只是為什麼一定要在小時的做矯正？許多成年人也是長大後才在矯正，東隆牙醫診所院長林泰興醫師解釋：「如果在小時候矯正可幫助改善兒童的骨骼發育，避免長大之後如果要再做矯正，可能需要做更高難度的開刀，也可能增加矯正時間。」三個階段注意重點不一樣！到底什麼樣的情況下是為最適合的「黃金矯正期」? 林泰興建議可以先了解兒童牙齒的發展，簡單審視三階段。階段一：乳牙齒列時期（6歲前）此時還不用著急矯正，因咬合還未穩定，這時要注意照顧「乳牙」的健康，以及觀察看看孩子們有沒有不良的壞習慣，像是用口呼吸的習慣、吸拇指等。林泰興說：「在這個階段可以安排定期口腔檢查，大約三個月至半年檢查一次。」避免出現蛀牙、有其他牙齒狀況。階段二：混合齒列時期(6~9歲)70%-80%的幼童在這階段就可以看出牙齒是否需要矯正，林泰興建議可以在8歲的時候做一次齒顎矯正檢查來評估。當孩子有上下顎骨差異太大、門牙咬合時被下排牙齒覆蓋、暴牙、戽斗的情況，在這時候就要追蹤治療改善。階段三：恆牙齒列時期(10~12歲)黃金矯正期大約小五到國一已接近換牙完成的歲數，這期間矯正調整上下顎骨最有效率，林泰興說因為顎骨還未定型，可以省去不少矯正時間，而且也可能減少開刀與拔牙的機率，矯正結果更理想。特別現在醫學發展，「早期矯正」日益被重視，但仍需要視每個孩童骨骼發育情況，每個階段定期看牙仍是很重要，別等到出問題再處理就來不及了。', '兒童衛教', '0', '2022-07-22');
INSERT INTO `yokult`.`topic` (`title`, `foreword`, `content`, `sortid`, `views`, `posttime`) VALUES ('寶寶長牙了！兒童常見7大牙齒問題', '一口健康的牙齒可以幫助孩子不論在咀嚼食物、發音、美觀等都有幫助，但幼兒牙齒不好顧，包括蛀牙、黃牙、長不出恆牙等問題，這次我們專訪專業牙科醫師來解答常見兒童牙齒問題。', '小小的乳牙萌發後，孩子看起來可愛極了！但其實孩子到了5歲左右就會開始換牙，變成成人的恆牙，因此不少家長以為乳牙不必管它，反正都會掉，然而這是相當錯誤的觀念。成大醫院口腔醫學部兒童牙科醫師方柔貽表示，口腔保健不分年齡，從0歲開始就要做好清潔與維護，而乳牙具有咀嚼功能、美觀、幫助發音、引導恆牙萌發，有健康的乳牙也會有比較健康的恆牙。常見兒童牙齒問題方柔貽醫師表示，兒童常見牙齒問題包括蛀牙、清潔、換牙、齒列不正等問題，以下就常見的7個問題一一做說明：Q1.乳牙最晚應該幾歲要長出？第一顆乳牙通常在5～8個月就會長出，大多是先長出下前牙，但這只是平均值，實際長牙年齡還是因人而異。倘若擔心缺牙則可以進一步照X光片確認，但一般來說即使照射後也不會倚靠外力將牙齒拉出，通常仍會等牙齒自然長出，不過要注意的是若整口20顆牙齒只剩一顆一直沒有萌發，可能會被歸為不明原因的萌發障礙，易導致後續換牙問題。Q2.兒童蛀牙原因有哪些？蛀牙構成原因有四大要件，包括牙齒、細菌、食物、時間，由此可知只要排除前述四原因就比較不容易蛀牙。以嬰幼兒來說，常見蛀牙原因有兩者，第一是夜奶，部分寶寶有夜奶習慣，或是睡前含著奶瓶喝奶喝著喝著就睡著了，這時也不太可能再將孩子吵醒刷牙，就有可能導致蛀牙。第二是愛吃零食、甜食，這類食物較黏，容易使牙齒表面沾染食物成分，吃完後應該立即刷牙，降低甜食黏著在口腔的時間，倘若未即時刷牙，就會增加口腔裡面蛀牙細菌的孳生。Q3.預防蛀牙可以用氟化物嗎？不少爸媽一定有聽過一歲時要帶寶寶去塗氟以預防蛀牙，裡面指的就是氟化物，氟化物可以抑制住牙細菌，也能幫助脫鈣牙齒再礦化，使牙齒結構更強壯。目前健保給付6歲以前的孩子，每半年可以健保塗氟一次，加強牙齒的健康度。而平時也可使用含氟牙膏，記得選擇含氟化物濃度1000ppm以上的牙膏才能有效預防蛀牙。另外診間也有爸媽詢問氟錠是否有效果，氟錠的使用方式是含在口內或是咀嚼碎，它與塗氟、含氟牙膏一樣，透過局部接觸牙齒達到預防蛀牙的效果，而吞進肚子隨身體循環到未萌發的牙胚來幫助牙齒強壯則是尚未證實。Q4.兒童換牙期大約從什麼時候開始？兒童平均換牙年齡約落在5～14歲間，女生會比男生略早一些，最先換的是下顎前牙，接著長上顎前牙，之後再慢慢長出臼齒、犬齒，一直到小學六年級到國一左右會長出第二大臼齒，就算是全部換牙完成。Q5.乳牙還沒掉，恆牙就長出，擠在一起怎麼辦？乳牙與恆牙同時並存是相當常見的狀況，不少爸媽以為，若是乳牙在原地，但恆牙已經長出，會使得恆牙長歪，但這其實是個迷思，因為恆牙牙齒的生長空間須看牙弓空間是否足夠，假設不足，即使將乳牙拔出，恆牙還是無法排整齊。此外，有時會看到小朋友的乳牙牙齒排列得很整齊漂亮，一點縫隙都沒有，然而這種狀況並不理想，因為恆牙比乳牙大顆，將來恆牙長出時牙齒就會比較擁擠。至於什麼時候該積極介入乳牙與恆牙並存的狀況呢？一般是恆牙牙齒從側邊長出，卡到下排牙齒並發生錯咬，而乳牙也遲遲還沒掉，這時就需要靠牙醫師將乳牙拔除，讓錯咬的恆牙漸漸回到正確咬合位置。', '兒童衛教', '0', '2022-07-22');
INSERT INTO `yokult`.`topic` (`title`, `foreword`, `content`, `sortid`, `views`, `posttime`) VALUES ('根管治療不能一勞永逸，4種狀況牙痛復發', '張先生有一天刷牙時，突然覺得牙肉後面腫腫的，想一想，最近天氣熱睡不好，也許是火氣大，下班後便買了幾罐椰子水，連喝了幾天似乎有好轉，牙齦也不再腫。但過了1週，牙齦附近的膿包又長出來，這次更嚴重，用手指稍微擠一下，還有膿血跑出來，張先生思考著：這顆牙齒不是抽過神經了，怎麼又出問題了呢？', '檢視張先生發生的情況，經過仔細的理學檢查與影像診斷發現，這是在根管治療（抽神經）多年後發生疾病復發的情況。（推薦閱讀：10種食物　讓牙齒健康、口氣清新）牙齒根管治療，是對齲齒或因牙齒外傷導致口腔內細菌進入牙髓腔而產生疾病的治療方式之一。一般來說，根管治療過程是透過將牙髓腔打開，使用各種器械及藥水移除牙根內發炎及壞死的牙髓組織，並進行清創及消毒，最後再利用馬來膠針等材料將牙髓腔緊密地封填起來，便完成整個根管治療。但是為什麼會復發呢？常見根管治療病灶復發狀況牙齒的冠部滲漏所謂冠部滲漏，泛指口腔內的細菌再度地跑進牙髓腔裡面，牙髓腔因此再次感染而導致治療失敗。因此，根管治療後需搭配一個良好的贋復體才能算是一個完整的治療，若患者的牙齒牙冠結構不足，或沒有一個精密製作的假牙套作為適當的保護，即使做得再完美的根管治療，後續牙齒仍有可能因感染復發或牙冠斷裂導致被拔除的後果。根管系統內或外的持續感染牙齒根管治療中，細菌是最大的敵人。根管治療操作範圍極為狹小，根管系統中又藏有許多分支、峽部甚至死角，可能無法清除徹底。因此，根管治療絕對無法百分之百移除受感染的組織。除此之外，有些頑固型的細菌甚至會對根管治療的藥水或藥劑產生抵抗的作用，導致根管牙髓腔的持續感染。牙根尖囊腫的生成當臨床診斷發現牙齒在X光影像顯示牙根尖陰影，就有可能發生牙根尖囊腫，代表的是牙根尖周圍的骨頭處有被細菌破壞掉。可以透過「再次根管治療」來處理牙根尖周圍炎，不過，一旦根尖骨頭處的破壞範圍太大，常需要透過手術來治療。（推薦閱讀：牙齦紅腫是火氣大，還是牙周病前兆？）牙根斷裂牙根斷裂是所有病患最不願意發生的狀況，有時無聲無息或伴有輕微的敲痛或是咬痛，但有時卻有合併牙周囊袋明顯的變深或牙周圍齒槽骨的破壞，因此，醫師的經驗及專業診斷更顯重要。發生牙齒疾病的時候，千萬不要拖延、或是認為不痛就不需要治療，發現問題並且儘早就診，才是保留牙齒的最佳方法。', '根管衛教', '0', '2022-07-22');
INSERT INTO `yokult`.`topic` (`title`, `foreword`, `content`, `sortid`, `views`, `posttime`) VALUES ('根管治療是什麼 ?', '根管治療就是抽神經嗎？沒錯，根管治療就是俗稱的「抽神經」。負責牙齒的感知、維持生長、養分、防衛等功能的運作都需要依靠「牙髓-牙本質複合系統」，它是牙髓腔、根管內許多細小的血管、神經組織系統所建構而成。而「神經」僅是其中之一。', '什麼情況下需要採取根管治療呢?會採取根管治療大多因素為蛀牙太深、外力撞擊、牙齒磨耗、牙周病...等狀況，而造成內部牙髓感染發炎，倘若不即時進行治療，可能會引發蜂窩性組織炎。根管治療要怎麼進行 ?完整的根管治療步驟包括1. 局部麻醉2. 打開牙髓腔、使用器械將根管內牙髓組織移除，藥水沖洗消毒。由於神經管數目會依照不同部位的牙齒有所差異，牙根從1~4根都有可能性，還須考量根管彎曲的情形。因此完整的療程所需要的時間五、六次都是有可能的。3. 暫時封填4. 根管充填當治療完成後，會以永久性的填封材料將已清潔完的牙髓腔、根管進行充填。根管治療後的牙齒需注意什麼？根管治療後的牙齒因為已清除牙髓組織，喪失養份來源，牙齒會產生變脆、失去彈性，色澤黯沉灰黑。若是因為蛀牙而進行治療的患者，通常都牙齒蛀蝕程度相當厲害，若不適當保護極易斷裂，故治療完成後會視情況裝上柱心填補牙齒結構，並套上假牙保護牙齒以恢復原本的外形、咬合功能及美觀發音，而少數牙齒可以直接填補即可。貼心提醒：此醫療保健內容僅提供概略性醫療資訊參考，每位病患之處置需依各別臨床狀況而有所調整。不能取代醫師之診斷及治療，如有任何疑問請立即就醫。', '根管衛教', '0', '2022-07-22');



  -- 排班資料
DROP TABLE IF EXISTS `schedule`;

CREATE TABLE `schedule` (
  `schedule_date` DATE NOT NULL COMMENT '日期',
  `morning_shift` VARCHAR(20) NOT NULL COMMENT '早班員工',
  `night_shift` VARCHAR(20) NOT NULL COMMENT '晚班員工',
  `day_off1` VARCHAR(20) NOT NULL COMMENT '休假員工',
  `day_off2` VARCHAR(20) NOT NULL COMMENT '休假員工',
  PRIMARY KEY (`schedule_date`))

COMMENT = '班表';

INSERT INTO 
  schedule (schedule_date, morning_shift, night_shift, day_off1, day_off2)
VALUES
  ('2022-08-01', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-02', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-03', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-04', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-05', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-06', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-07', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-08', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-09', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-10', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-11', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-12', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-13', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-14', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-15', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-16', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-17', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-18', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-19', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-20', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-21', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-22', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-23', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-24', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-25', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-26', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-27', 'tga003', 'tga001', 'tga002', ''),
  ('2022-08-28', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-29', 'tga001', 'tga002', 'tga003', ''),
  ('2022-08-30', 'tga002', 'tga003', 'tga001', ''),
  ('2022-08-31', 'tga003', 'tga001', 'tga002', '');
  
  
  -- 畫假資料
  DROP TABLE IF EXISTS `shift_schedule`;

  CREATE TABLE `shift_schedule` (
  `row_num` INTEGER NOT NULL COMMENT '流水號',
`schedule_date` DATE NOT NULL COMMENT '日期',
  `staff_id` varchar(50) NOT NULL COMMENT '員工編號',
  `shiftschedule_type_of_leave` varchar(50) NOT NULL COMMENT '假別',
  `shiftschedule_morningshift_nightshift` varchar(50) NOT NULL COMMENT '早班晚班',
  FOREIGN KEY (`schedule_date`)
  REFERENCES `schedule` (`schedule_date`),
  FOREIGN KEY (`staff_id`)
  REFERENCES `staff` (`staff_id`))
  comment = '排班';

  
  
  

  
  