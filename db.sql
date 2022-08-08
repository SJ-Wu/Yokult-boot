-- 創建資料庫
CREATE DATABASE `yokult`;

USE `yokult`;

-- 會員資料
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `memid` VARCHAR(50) NOT NULL COMMENT '會員帳號',
  `email` VARCHAR(320) NOT NULL COMMENT '會員信箱',
  `password` VARCHAR(10) NOT NULL COMMENT '會員密碼',
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
