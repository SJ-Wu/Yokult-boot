-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 建立募資提案table

create table `fund_proposal`(
	`proposalID` integer auto_increment not null primary key comment '提案編號',
	`proposal_Name` varchar(40) not null comment '提案名稱',
	`proposal_Host_Name` varchar(70) not null comment '提案人姓名',
	`proposal_Goal` integer not null comment '目標金額',
	`proposal_CategoryID` varchar(2) not null comment '分類編號',
	`proposal_Started_Date_Time` datetime not null comment '提案開始時間',
	`proposal_Ended_Date_Time` datetime not null comment '提案結束時間',
	`proposal_Email` varchar(320) not null comment '電子信箱',
	`proposal_Cellphone` varchar(10) not null comment '手機號碼',
	`proposal_Summary` varchar(500) not null comment '提案概要',
	`proposal_Picture` mediumblob  comment '提案首圖檔案',
	`proposal_Picture_Zip` mediumblob  comment '提案首圖縮圖檔案',
    `proposal_Html_Content` varchar(15000) comment '提案網頁內容',
	`memID` varchar(50) not null comment '會員帳號',
    foreign key(`memID`) references `MEMBER`(`MEMID`)
);


-- 存入以下資料庫的數據

insert into `fund_proposal` (	
	`proposal_Name`,
	`proposal_Host_Name`,
	`proposal_Goal`,
	`proposal_CategoryID`,
	`proposal_Started_Date_Time`,
	`proposal_Ended_Date_Time`,
	`proposal_Email`,
	`proposal_Cellphone`,
	`proposal_Summary`,
	`proposal_Picture`,
	`proposal_Picture_Zip`,
    `proposal_Html_Content`,
	`memID`
) values
(
'偏鄉牙醫志工團｜為偏鄉學童巡迴牙齒健檢', 
'社團法人中華民國牙醫師公會全國聯合會', 
'20000',
3, 
'2022-06-01 00:00:00', 
'2022-09-01 00:00:00',
'gogogo@gmail.com',
'0912345678',
'跟我們一起為偏鄉國小注入活力吧！',
'',
'',
'<div class="col-lg-8 wow slideInUp" data-wow-delay="0.3s" style="background-color: white">
                        <h3 style="text-align: center;">
                            <strong>大家好，我們是築夢志工團第五屆!</strong>
                        </h3>
                        <ul>
                            <li class="article">
                                <strong>起源</strong>
                                <strong>
                                    2016年3月，二十名來自全台各地的高中生經由網路自發募集，組成「築夢教育志工團」，並在2016年4月15日向區域和平志工團申請青年自組團隊資格。我們懷抱熱情和一顆回饋的心，決定前往嘉義偏鄉籌組教育服務營。
                                </strong>
                            </li>
                            <img src="./img/fundraising/proposal-1/content/price-1_XXX.jpg" class="article">
                            <li class="article">
                                <strong>
                                    嘉義縣為全台灣人口老化最高的城市，隔代教養現象頻繁，容易衍伸認知及價值觀上的差異，相處模式中產生無形的時代隔閡，對於孩子的行為及表現也較容易產生拒絕、失落，甚至是缺乏自信心的問題。
                                    由於城鄉差距的問題造成學校教育資源的不足，學校學生人數未達教育部輔導師資分配標準，使學習障礙、情緒管理障礙或是自信心不足等特殊孩童無法接受專業的輔導協助，而是和一般小朋友接受一樣的教育模式，更可能因為小朋友對於同儕的”特殊”欠缺認識，產生同儕排擠的情況發生，而這些孩子甚至被孤立，也因為學業上的表現感到自卑、銳減自信心等。
                                </strong>
                            </li>
                            <hr>
                            <li class="article">
                                <strong>暑期服務營活動概要：</strong>
                            </li>
                            <p class="article">
                                <strong>名稱：2021築夢志工團暑期教育服務營</strong>
                            </p>
                            <p class="article">
                                <strong>日期：2021年7月6日至7月9日(共四天)</strong>
                            </p>
                            <p class="article">
                                <strong>地點：嘉義縣東石鄉港墘國小、嘉義縣故宮南院</strong>
                            </p>
                            <p class="article">
                                <strong>參與對象：嘉義縣東石鄉港墘國小之小一至小六學生</strong>
                            </p>
                            <p class="article">
                                <strong>活動費用：免費</strong>
                            </p>
                            <p class="article">
                                <strong>參加人數：目前已有45位港墘國小學生報名。</strong>
                            </p>
                            <img src="./img/fundraising/proposal-1/content/price-2_XXX.jpg" class="article">
                            <img src="./img/fundraising/proposal-1/content/price-3_XXX.jpg" class="article">
                        </ul>
                    </div>',
'TGA001'
),
(
'2023微笑曲線計畫｜為每個人打造獨一無二的微笑曲線', 
'牙樂多診所', 
'500000',
2, 
'2022-08-01 00:00:00', 
'2026-11-01 00:00:00',
'smile@yahoo.com',
'0987654321',
'跟我們一起打造獨一無二的微笑曲線吧！',
'',
'',
'<div class="col-lg-8 wow slideInUp" data-wow-delay="0.3s" style="background-color: white">
                        <h3 style="text-align: center;">
                            <strong>大家好，我們是築夢志工團第五屆!</strong>
                        </h3>
                        <ul>
                            <li class="article">
                                <strong>起源</strong>
                                <strong>
                                    2016年3月，二十名來自全台各地的高中生經由網路自發募集，組成「築夢教育志工團」，並在2016年4月15日向區域和平志工團申請青年自組團隊資格。我們懷抱熱情和一顆回饋的心，決定前往嘉義偏鄉籌組教育服務營。
                                </strong>
                            </li>
                            <img src="./img/fundraising/proposal-1/content/price-1_XXX.jpg" class="article">
                            <li class="article">
                                <strong>
                                    嘉義縣為全台灣人口老化最高的城市，隔代教養現象頻繁，容易衍伸認知及價值觀上的差異，相處模式中產生無形的時代隔閡，對於孩子的行為及表現也較容易產生拒絕、失落，甚至是缺乏自信心的問題。
                                    由於城鄉差距的問題造成學校教育資源的不足，學校學生人數未達教育部輔導師資分配標準，使學習障礙、情緒管理障礙或是自信心不足等特殊孩童無法接受專業的輔導協助，而是和一般小朋友接受一樣的教育模式，更可能因為小朋友對於同儕的”特殊”欠缺認識，產生同儕排擠的情況發生，而這些孩子甚至被孤立，也因為學業上的表現感到自卑、銳減自信心等。
                                </strong>
                            </li>
                            <hr>
                            <li class="article">
                                <strong>暑期服務營活動概要：</strong>
                            </li>
                            <p class="article">
                                <strong>名稱：2021築夢志工團暑期教育服務營</strong>
                            </p>
                            <p class="article">
                                <strong>日期：2021年7月6日至7月9日(共四天)</strong>
                            </p>
                            <p class="article">
                                <strong>地點：嘉義縣東石鄉港墘國小、嘉義縣故宮南院</strong>
                            </p>
                            <p class="article">
                                <strong>參與對象：嘉義縣東石鄉港墘國小之小一至小六學生</strong>
                            </p>
                            <p class="article">
                                <strong>活動費用：免費</strong>
                            </p>
                            <p class="article">
                                <strong>參加人數：目前已有45位港墘國小學生報名。</strong>
                            </p>
                            <img src="./img/fundraising/proposal-1/content/price-2_XXX.jpg" class="article">
                            <img src="./img/fundraising/proposal-1/content/price-3_XXX.jpg" class="article">
                        </ul>
                    </div>',
'TGA002'
),
(
'環保牙刷計畫 | 刷牙當然也要用最天然的', 
'刷具研究所', 
'10000',
1, 
'2026-08-01 00:00:00', 
'2030-08-05 00:00:00',
'brush@yahoo.com',
'0987654321',
'刷牙當然也要用最天然的！',
'',
'',
'<div class="col-lg-8 wow slideInUp" data-wow-delay="0.3s" style="background-color: white">
                        <h3 style="text-align: center;">
                            <strong>大家好，我們是築夢志工團第五屆!</strong>
                        </h3>
                        <ul>
                            <li class="article">
                                <strong>起源</strong>
                                <strong>
                                    2016年3月，二十名來自全台各地的高中生經由網路自發募集，組成「築夢教育志工團」，並在2016年4月15日向區域和平志工團申請青年自組團隊資格。我們懷抱熱情和一顆回饋的心，決定前往嘉義偏鄉籌組教育服務營。
                                </strong>
                            </li>
                            <img src="./img/fundraising/proposal-1/content/price-1_XXX.jpg" class="article">
                            <li class="article">
                                <strong>
                                    嘉義縣為全台灣人口老化最高的城市，隔代教養現象頻繁，容易衍伸認知及價值觀上的差異，相處模式中產生無形的時代隔閡，對於孩子的行為及表現也較容易產生拒絕、失落，甚至是缺乏自信心的問題。
                                    由於城鄉差距的問題造成學校教育資源的不足，學校學生人數未達教育部輔導師資分配標準，使學習障礙、情緒管理障礙或是自信心不足等特殊孩童無法接受專業的輔導協助，而是和一般小朋友接受一樣的教育模式，更可能因為小朋友對於同儕的”特殊”欠缺認識，產生同儕排擠的情況發生，而這些孩子甚至被孤立，也因為學業上的表現感到自卑、銳減自信心等。
                                </strong>
                            </li>
                            <hr>
                            <li class="article">
                                <strong>暑期服務營活動概要：</strong>
                            </li>
                            <p class="article">
                                <strong>名稱：2021築夢志工團暑期教育服務營</strong>
                            </p>
                            <p class="article">
                                <strong>日期：2021年7月6日至7月9日(共四天)</strong>
                            </p>
                            <p class="article">
                                <strong>地點：嘉義縣東石鄉港墘國小、嘉義縣故宮南院</strong>
                            </p>
                            <p class="article">
                                <strong>參與對象：嘉義縣東石鄉港墘國小之小一至小六學生</strong>
                            </p>
                            <p class="article">
                                <strong>活動費用：免費</strong>
                            </p>
                            <p class="article">
                                <strong>參加人數：目前已有45位港墘國小學生報名。</strong>
                            </p>
                            <img src="./img/fundraising/proposal-1/content/price-2_XXX.jpg" class="article">
                            <img src="./img/fundraising/proposal-1/content/price-3_XXX.jpg" class="article">
                        </ul>
                    </div>',
'TGA002'
)



