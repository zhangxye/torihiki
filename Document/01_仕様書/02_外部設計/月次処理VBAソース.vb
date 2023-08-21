'マスタデータ
Private mastersheet As Worksheet
'列数(月次処理)
Private columnCount As Integer
'列数(契約処理)
Private contractColCount As Integer
'
Private SendHisId As Integer

'所属会社ID
Private gCompanyName As String
'注文書idの連番
Private orderId As Integer
'個人情報申請書
Const prsnlInfPrprNewBk = "○8_【ＴＳＲＳ】個人情報申請書-新規.xls"
Const prsnlInfCntrNewBk = "○8_契約【ＴＳＲＳ】個人情報申請書-新規.xls"
Const prsnlInfPrprChgBk = "○2_【ＴＳＲＳ-変更】個人情報申請書.xls"
Const prsnlInfCntrChgBk = "○2_契約【ＴＳＲＳ-変更】個人情報申請書.xls"
Const prsnlInfNewSheetNm = "個人情報新規申請書"
Const prsnlInfChgSheetNm = "個人情報変更申請書"
Const accntDispForm = "_ \* #,##0_ ;_ \* -#,##0_ ;_ \* ""-""_ ;_ @_ "
Const prsnlInfChkFlg = "■"
'メール挨拶文定義
Private mailGrtgTxt As String
'メールフッター定義
Private mailFooter As String

Private Sub SetMasterInfo(ByVal rowid)
On Error GoTo Err_Handle
    Dim mastersheet, datasheet, columnid, mainSheet
    Set mastersheet = Application.Workbooks("TSRS帳票管理マスタ.xls").Worksheets("帳票マスタ")
    Set mainSheet = ThisWorkbook.Worksheets("Main")
    columnid = mastersheet.UsedRange.Columns.count

    If mastersheet.Cells(3, columnid).Value <> "人事側状態" Then
        mastersheet.Cells(3, columnid + 1).Value = "月次処理"
        mastersheet.Cells(3, columnid + 1).Interior.ColorIndex = 48
        mastersheet.Cells(3, columnid + 1).Borders.LineStyle = xlContinuous
        mastersheet.Cells(3, columnid + 1).EntireColumn.AutoFit
        mastersheet.Cells(3, columnid + 2).Value = "契約帳票"
        mastersheet.Cells(3, columnid + 2).Interior.ColorIndex = 48
        mastersheet.Cells(3, columnid + 2).Borders.LineStyle = xlContinuous
        mastersheet.Cells(3, columnid + 2).EntireColumn.AutoFit
        mastersheet.Cells(3, columnid + 3).Value = "営業側状態"
        mastersheet.Cells(3, columnid + 3).Interior.ColorIndex = 48
        mastersheet.Cells(3, columnid + 3).Borders.LineStyle = xlContinuous
        mastersheet.Cells(3, columnid + 3).EntireColumn.AutoFit
        mastersheet.Cells(3, columnid + 4).Value = "人事側状態"
        mastersheet.Cells(3, columnid + 4).Interior.ColorIndex = 48
        mastersheet.Cells(3, columnid + 4).Borders.LineStyle = xlContinuous
        mastersheet.Cells(3, columnid + 4).EntireColumn.AutoFit
    End If
    
    Set datasheet = ActiveWorkbook.Worksheets(" (訂)プロパー")
    
    ' 処理年月取得
'    yText = Year(DateSerial(Year(Now), month(Now), 0))
'    mText = month(DateSerial(Year(Now), month(Now), 0))
    yText = mainSheet.Range("d20").Value
    mText = mainSheet.Range("e20").Value
    
    '社員番号
    mastersheet.Range("a" & rowid).Value = datasheet.Range("ac8").Value
    '社員名
    mastersheet.Range("b" & rowid).Value = datasheet.Range("e8").Value
    '所属会社ID
    mastersheet.Range("c" & rowid).Value = datasheet.Range("ad97").Value
    mastersheet.Range("c" & rowid).EntireColumn.AutoFit
    '所属会社名
    mastersheet.Range("d" & rowid).Value = datasheet.Range("l97").Value
    '希望雇用形態
    mastersheet.Range("e" & rowid).Value = datasheet.Range("e10").Value
    '稼働中案件番号
    mastersheet.Range("f" & rowid).Value = datasheet.Range("ab110").Value
    '作業時間（上限・H）
    mastersheet.Range("g" & rowid).Value = datasheet.Range("l74").Value
    '作業時間 (下限)
    mastersheet.Range("h" & rowid).Value = datasheet.Range("l75").Value
    '単価
    mastersheet.Range("i" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("i" & rowid).Value = datasheet.Range("v25").Value
    '原価
    mastersheet.Range("j" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("j" & rowid).Value = datasheet.Range("ad25").Value
    '超過単価 (円)
    mastersheet.Range("k" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("k" & rowid).Value = datasheet.Range("l78").Value
    '控除単価 (円)
    mastersheet.Range("l" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("l" & rowid).Value = datasheet.Range("l79").Value
    '交通費 (円)
    mastersheet.Range("m" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("m" & rowid).Value = datasheet.Range("ac39").Value
    '経費
    mastersheet.Range("n" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("n" & rowid).Value = datasheet.Range("ac40").Value
    '携帯番号
    mastersheet.Range("o" & rowid).Value = datasheet.Range("l105").Value
    '契約開始日
    mastersheet.Range("p" & rowid).Value = Format(datasheet.Range("n122").Value, "YYYY/MM/DD")
    '契約終了日
    mastersheet.Range("q" & rowid).Value = Format(datasheet.Range("t122").Value, "YYYY/MM/DD")
    'E -mail(自宅)
    mastersheet.Range("r" & rowid).Value = datasheet.Range("l107").Value
    '支払日
    mastersheet.Range("s" & rowid).Value = datasheet.Range("r125").Value
    '締日(本人)
    mastersheet.Range("t" & rowid).Value = datasheet.Range("n125").Value
    '入社日
    mastersheet.Range("u" & rowid).Value = Format(datasheet.Range("e9").Value, "YYYY/MM/DD")
    '出金予定日社員向け
    mTemp = mText + AddMonth(datasheet.Range("r125").Value)
    If datasheet.Range("t125").Value = "末" Then
        setTemp = DateSerial(yText, mTemp + 1, 0)
    Else
        setTemp = DateSerial(yText, mTemp, datasheet.Range("t125").Value)
    End If
    'setTemp = wdayAdj(setTemp)
    mastersheet.Range("v" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    '社保加算後
    mastersheet.Range("w" & rowid).NumberFormatLocal = "\#,##0"
    '****************************売上機能の修正 WuYang 2016/06/09 Start************************************
    'mastersheet.Range("w" & rowid).Value = Fix(datasheet.Range("ac49").Value)
    mastersheet.Range("w" & rowid).Value = Application.WorksheetFunction.RoundDown(datasheet.Range("ac49").Value + datasheet.Range("ac50").Value, 0)
    '****************************売上機能の修正 WuYang 2016/06/09 End************************************
    '工数（月次用）
    If (yText & mText) = (Year(datasheet.Range("e9").Value) & month(datasheet.Range("e9").Value)) Then
        wText = datasheet.Range("l124").Value
        If (wText - Fix(wText)) > 0 Then
            setTemp = wText - Fix(wText)
        Else
            setTemp = 1
        End If
    Else
        setTemp = 1
    End If
    mastersheet.Range("x" & rowid).Value = setTemp
    '工数 (契約用)
    mastersheet.Range("by" & rowid).Value = datasheet.Range("l124").Value
    '要員マイナンバー
    mastersheet.Range("y" & rowid).Value = datasheet.Range("ac9").Text
    '所属会社法人マイナンバー
    mastersheet.Range("z" & rowid).Value = datasheet.Range("ac98").Text
    '出勤時刻
    mastersheet.Range("aa" & rowid).Value = datasheet.Range("o84").Text
    '退勤時刻
    mastersheet.Range("ab" & rowid).Value = datasheet.Range("v84").Text
    ' 作業報告書提出締日
    setTemp = DateSerial(yText, mText + 1, 0)
    mastersheet.Range("ac" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    '案件名称
    mastersheet.Range("ae" & rowid).Value = datasheet.Range("l69").Value
    '作業内容・範囲
    mastersheet.Range("af" & rowid).Value = datasheet.Range("l71").Value
    '勤務地
    mastersheet.Range("ag" & rowid).Value = datasheet.Range("l70").Value
    '締日（売上）時間
    If datasheet.Range("n85").Value = "月末" Then
        setTemp = DateSerial(yText, mText + 1, 0)
    Else
        dText = Replace(datasheet.Range("n85").Value, "日", "")
        setTemp = DateSerial(yText, mText, dText)
    End If
    mastersheet.Range("ah" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    '補助（売上）該当案件のステータス(新規更新)
    mastersheet.Range("ai" & rowid).Value = "新規"
    '次のPJ（売上）選択肢：既存、経験
    mastersheet.Range("aj" & rowid).Value = "新規"
    '次のPJ（仕入）選択肢：既存、経験
    mastersheet.Range("ak" & rowid).Value = "新規"
    '入金予定日
    mTemp = mText + AddMonth(datasheet.Range("r85").Value)
    If datasheet.Range("t85").Value = "末" Then
        setTemp = DateSerial(yText, mTemp + 1, 0)
    Else
        setTemp = DateSerial(yText, mTemp, datasheet.Range("t85").Value)
    End If
    'setTemp = wdayAdj(setTemp)
    mastersheet.Range("al" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    '締日（仕入）
    If datasheet.Range("n125").Value = "月末" Then
        setTemp = DateSerial(yText, mText + 1, 0)
    Else
        dText = Replace(datasheet.Range("n125").Value, "日", "")
        setTemp = DateSerial(yText, mText, dText)
    End If
    mastersheet.Range("am" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    '補助（仕入）該当案件のステータス(新規更新)
    mastersheet.Range("an" & rowid).Value = "新規"
    '営業主担当
    mastersheet.Range("ao" & rowid).Value = datasheet.Range("ab4").Value
    '副担当
    mastersheet.Range("ap" & rowid).Value = datasheet.Range("ab5").Value
    '注文書受領日
    mastersheet.Range("aq" & rowid).Value = Format(datasheet.Range("e19").Value, "YYYY/MM/DD")
    'スタート
    mastersheet.Range("ar" & rowid).Value = Format(datasheet.Range("e9").Value, "YYYY/MM/DD")
    '開始日
    cmprDate = Year(datasheet.Range("n122").Value) & month(datasheet.Range("n122").Value)
    If yText & mText > cmprDate Then
        setTemp = DateSerial(yText, mText, 1)
    Else
        setTemp = datasheet.Range("n122").Value
    End If
    mastersheet.Range("at" & rowid).Value = Format(datasheet.Range("n81").Value, "YYYY/MM/DD")
    '終了日
    cmprDate = Year(datasheet.Range("t122").Value) & month(datasheet.Range("t122").Value)
    If yText & mText = cmprDate Then
        setTemp = datasheet.Range("t122").Value
    Else
        tempDate = datasheet.Range("t122").Value
        setTemp = DateSerial(Year(tempDate), month(tempDate) + 1, 0)
    End If
    mastersheet.Range("au" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    '時間単位
    mastersheet.Range("av" & rowid).Value = datasheet.Range("l80").Value
    '担当者ID
    mastersheet.Range("aw" & rowid).Value = datasheet.Range("o61").Value
    '担当者部署
    mastersheet.Range("ax" & rowid).Value = datasheet.Range("l62").Value
    '担当者役職
    mastersheet.Range("ay" & rowid).Value = datasheet.Range("l63").Value
    '担当者読み方
    mastersheet.Range("az" & rowid).Value = datasheet.Range("y61").Value
    '担当者携帯
    mastersheet.Range("ba" & rowid).Value = datasheet.Range("l64").Value
    '担当者電話
    mastersheet.Range("bb" & rowid).Value = datasheet.Range("l59").Value
    '担当者FAX
    mastersheet.Range("bc" & rowid).Value = datasheet.Range("l60").Value
    '担当者メール
    mastersheet.Range("bd" & rowid).Value = datasheet.Range("l65").Value
    '担当者メール (社用)
    mastersheet.Range("be" & rowid).Value = datasheet.Range("l66").Value
    '注文内容
    mastersheet.Range("bf" & rowid).Value = datasheet.Range("l86").Value
    '締日(上位)
    mastersheet.Range("bg" & rowid).Value = datasheet.Range("n85").Value
    '本人社保料加算額（工数考慮）
    mastersheet.Range("bh" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("bh" & rowid).Value = datasheet.Range("as55").Value
    '会社番号
    mastersheet.Range("bl" & rowid).Value = datasheet.Range("ad56").Value
    mastersheet.Range("bl" & rowid).EntireColumn.AutoFit
    '会社名
    mastersheet.Range("bm" & rowid).Value = datasheet.Range("l56").Value
    '電話番号
    mastersheet.Range("bn" & rowid).Value = datasheet.Range("l59").Value
    'FAX番号
    mastersheet.Range("bo" & rowid).Value = datasheet.Range("l60").Value
    '郵便番号
    setTemp = Replace(datasheet.Range("m58").Value, "〒", "")
    mastersheet.Range("bp" & rowid).Value = setTemp
    '連絡先住所
    mastersheet.Range("bq" & rowid).Value = datasheet.Range("r58").Value
    '送付備考
    'お支払期日
    mTemp = mText + AddMonth(datasheet.Range("r85").Value)
    If datasheet.Range("t85").Value = "末" Then
        setTemp = DateSerial(yText, mTemp + 1, 0)
    Else
        setTemp = DateSerial(yText, mTemp, datasheet.Range("t85").Value)
    End If
    'setTemp = wdayAdj(setTemp)
    mastersheet.Range("bs" & rowid).Value = Format(setTemp, "YYYY/MM/DD")
    'フリガナ (住所)
    '住所 (下位)
    mastersheet.Range("bu" & rowid).Value = datasheet.Range("r99").Value
    '〒郵便番号 (下位)
    setTemp = Replace(datasheet.Range("m99").Value, "〒", "")
    mastersheet.Range("bv" & rowid).Value = setTemp
    'TEL (下位)
    mastersheet.Range("bw" & rowid).Value = datasheet.Range("l100").Value
    '法人マイナンバー
    mastersheet.Range("bx" & rowid).Value = datasheet.Range("ac57").Text
    '性別
    mastersheet.Range("cb" & rowid).Value = datasheet.Range("s7").Value
    '生年月日
    mastersheet.Range("cc" & rowid).Value = Format(datasheet.Range("ac7").Value, "YYYY/MM/DD")
    'フリガナ
    mastersheet.Range("cd" & rowid).Value = datasheet.Range("e7").Value
    '休憩時間
    mastersheet.Range("ce" & rowid).Value = datasheet.Range("ae84").Text
    '基本賃金
    mastersheet.Range("cf" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cf" & rowid).Value = datasheet.Range("as11").Value
    '住宅手当
    mastersheet.Range("cg" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cg" & rowid).Value = datasheet.Range("bc11").Value
    '通信手当
    mastersheet.Range("ch" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("ch" & rowid).Value = datasheet.Range("bm11").Value
    '資格手当
    mastersheet.Range("ci" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("ci" & rowid).Value = datasheet.Range("bh12").Value
    '残業手当
    mastersheet.Range("cj" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cj" & rowid).Value = datasheet.Range("bc12").Value
    '賃金締切日
    mastersheet.Range("ck" & rowid).Value = datasheet.Range("n125").Value
    '賃金支払日
    mastersheet.Range("cl" & rowid).Value = datasheet.Range("t125").Value
    '自宅最寄駅の線①
    mastersheet.Range("cm" & rowid).Value = datasheet.Range("b132").Value
    '自宅最寄駅の駅①
    mastersheet.Range("cn" & rowid).Value = datasheet.Range("i132").Value
    '現場最寄駅の線①
    mastersheet.Range("co" & rowid).Value = datasheet.Range("r132").Value
    '現場最寄駅の駅①
    mastersheet.Range("cp" & rowid).Value = datasheet.Range("w132").Value
    '通勤経路①の交通費
    mastersheet.Range("cq" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cq" & rowid).Value = datasheet.Range("ab132").Value
    '自宅最寄駅の線②
    mastersheet.Range("cr" & rowid).Value = datasheet.Range("b133").Value
    '自宅最寄駅の駅②
    mastersheet.Range("cs" & rowid).Value = datasheet.Range("i133").Value
    '現場最寄駅の線②
    mastersheet.Range("ct" & rowid).Value = datasheet.Range("r133").Value
    '現場最寄駅の駅②
    mastersheet.Range("cu" & rowid).Value = datasheet.Range("w133").Value
    '通勤経路②の交通費
    mastersheet.Range("cv" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cv" & rowid).Value = datasheet.Range("ab133").Value
    '本人始業時刻
    mastersheet.Range("cw" & rowid).Value = Format(datasheet.Range("o123").Value, "HH:MM")
    '本人終業時刻
    mastersheet.Range("cx" & rowid).Value = Format(datasheet.Range("v123").Value, "HH:MM")
    '役職手当
    mastersheet.Range("cy" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cy" & rowid).Value = datasheet.Range("ax11").Value
    '扶養手当
    mastersheet.Range("cz" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("cz" & rowid).Value = datasheet.Range("bh11").Value
    '現場手当
    mastersheet.Range("da" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("da" & rowid).Value = datasheet.Range("as12").Value
    '当番手当
    mastersheet.Range("db" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("db" & rowid).Value = datasheet.Range("ax12").Value
    'そのた手当2
    mastersheet.Range("dc" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("dc" & rowid).Value = datasheet.Range("bm12").Value
    '本人上限時間
    mastersheet.Range("dd" & rowid).Value = datasheet.Range("l115").Value
    '本人下限時間
    mastersheet.Range("de" & rowid).Value = datasheet.Range("l116").Value
    '本人超過単価
    mastersheet.Range("df" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("df" & rowid).Value = datasheet.Range("l119").Value
    '本人控除単価
    mastersheet.Range("dg" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("dg" & rowid).Value = datasheet.Range("l120").Value
    '本人調整単位
    mastersheet.Range("dh" & rowid).Value = datasheet.Range("l121").Value
    '本人支払いサイト
    mastersheet.Range("di" & rowid).Value = datasheet.Range("l125").Value
    '自宅最寄駅の線③
    mastersheet.Range("dj" & rowid).Value = datasheet.Range("b134").Value
    '自宅最寄駅の駅③
    mastersheet.Range("dk" & rowid).Value = datasheet.Range("i134").Value
    '現場最寄駅の線③
    mastersheet.Range("dl" & rowid).Value = datasheet.Range("r134").Value
    '現場最寄駅の駅③
    mastersheet.Range("dm" & rowid).Value = datasheet.Range("w134").Value
    '通勤経路③の交通費
    mastersheet.Range("dn" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("dn" & rowid).Value = datasheet.Range("ab134").Value
    '自宅最寄駅の線④
    mastersheet.Range("do" & rowid).Value = datasheet.Range("b135").Value
    '自宅最寄駅の駅④
    mastersheet.Range("dp" & rowid).Value = datasheet.Range("i135").Value
    '現場最寄駅の線④
    mastersheet.Range("dq" & rowid).Value = datasheet.Range("r135").Value
    '現場最寄駅の駅④
    mastersheet.Range("dr" & rowid).Value = datasheet.Range("w135").Value
    '通勤経路④の交通費
    mastersheet.Range("ds" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("ds" & rowid).Value = datasheet.Range("ab135").Value
    '往復交通費日額合計
    mastersheet.Range("dt" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("dt" & rowid).Value = datasheet.Range("h141").Value
    '社会保険加入
    If datasheet.Range("ac44").Value = "" Then
        mastersheet.Range("du" & rowid).Value = "無"
    Else
        mastersheet.Range("du" & rowid).Value = "有"
    End If
    '退社日
    mastersheet.Range("dv" & rowid).Value = datasheet.Range("y155").Value
    '退社後郵便番号
    mastersheet.Range("dw" & rowid).Value = datasheet.Range("z156").Value
    '退社後住所
    mastersheet.Range("dx" & rowid).Value = datasheet.Range("w157").Value
    
    '****************************稼働機能の追加 WuYang 2016/06/09 Start**********************************
    '作業期間 (お客様側)
    mastersheet.Range("dy" & rowid).Value = datasheet.ComboBox1.Value
    mastersheet.Range("dz" & rowid).Value = datasheet.ComboBox2.Value
    mastersheet.Range("ea" & rowid).Value = datasheet.ComboBox3.Value
    mastersheet.Range("eb" & rowid).Value = datasheet.ComboBox4.Value
    mastersheet.Range("ec" & rowid).Value = datasheet.ComboBox5.Value
    mastersheet.Range("ed" & rowid).Value = datasheet.ComboBox6.Value
    mastersheet.Range("ee" & rowid).Value = datasheet.ComboBox7.Value
    mastersheet.Range("ef" & rowid).Value = datasheet.ComboBox8.Value
    mastersheet.Range("eg" & rowid).Value = datasheet.ComboBox9.Value
    mastersheet.Range("eh" & rowid).Value = datasheet.ComboBox10.Value
    mastersheet.Range("ei" & rowid).Value = datasheet.ComboBox11.Value
    mastersheet.Range("ej" & rowid).Value = datasheet.ComboBox12.Value
    mastersheet.Range("ek" & rowid).Value = datasheet.ComboBox13.Value
    'BPのFAX番号
    mastersheet.Range("el" & rowid).Value = datasheet.Range("L101").Value
    '電話番号
    mastersheet.Range("em" & rowid).Value = datasheet.Range("L100").Value
    '担当者（フリガナ）
    mastersheet.Range("en" & rowid).Value = datasheet.Range("L102").Value
    '所属部署
    mastersheet.Range("eo" & rowid).Value = datasheet.Range("L103").Value
    '役職
    mastersheet.Range("ep" & rowid).Value = datasheet.Range("L104").Value
    '担当者メールアドレス
    mastersheet.Range("eq" & rowid).Value = datasheet.Range("L107").Value
     '通勤経路①の交通費
    If (Trim(datasheet.Range("AB152").Value) <> "") Then
       mastersheet.Range("BI" & rowid).Value = Format(datasheet.Range("AB152").Value, "YYYY/MM/DD")
    End If
    If (Trim(datasheet.Range("AB153").Value) <> "") Then
       mastersheet.Range("BI" & rowid).Value = datasheet.Range("AB153").Value
    End If
    If (Trim(datasheet.Range("AB154").Value) <> "") Then
       mastersheet.Range("BI" & rowid).Value = datasheet.Range("AB154").Value
    End If
    If datasheet.processFlag1.Value = True Then
        mastersheet.Range("bj" & rowid).Value = "新規"
    ElseIf datasheet.processFlag2.Value = True Then
        mastersheet.Range("bj" & rowid).Value = "更新"
    ElseIf datasheet.processFlag3.Value = True Then
        mastersheet.Range("bj" & rowid).Value = "終了"
    End If
    '売上計上月
    mastersheet.Range("bk" & rowid).Value = datasheet.Range("AD22").Value
    '支払サイト（上位）
    mastersheet.Range("bz" & rowid).Value = datasheet.Range("L85").Value
    '希望職種
    mastersheet.Range("ca" & rowid).Value = datasheet.Range("T124").Value
    '外勤勤怠用交通費 (円)
    mastersheet.Range("er" & rowid).NumberFormatLocal = "\#,##0"
    mastersheet.Range("er" & rowid).Value = datasheet.Range("ac39").Value
    '****************************稼働機能の追加 WuYang 2016/06/09 End************************************
    
    mastersheet.Rows(rowid).RowHeight = 13.5
    
    If mastersheet.Cells(3, columnid).Value <> "人事側状態" Then
        ' 作成対象帳票（月次）フラグセット
        If mainSheet.chkMthly.Value Then
            mastersheet.Cells(rowid, columnid + 1).Value = "○"
        End If
        ' 作成対象帳票（契約）フラグセット
        If mainSheet.chkCtrct.Value Then
            mastersheet.Cells(rowid, columnid + 2).Value = "○"
        End If
        
        If (datasheet.Range("e10").Value = "正社員" Or datasheet.Range("e10").Value = "契約社員") Then
            ' 営業側状態セット
            mastersheet.Cells(rowid, columnid + 3).Value = "×"
             ' 人事側状態セット
            If datasheet.DrawingObjects("Check Box 48").Value = 1 Then
                mastersheet.Cells(rowid, columnid + 4).Value = "入社"
                mastersheet.Cells(rowid, columnid + 4).Borders.LineStyle = xlContinuous
            ElseIf datasheet.DrawingObjects("Check Box 49").Value = 1 Then
                mastersheet.Cells(rowid, columnid + 4).Value = "変更"
                mastersheet.Cells(rowid, columnid + 4).Borders.LineStyle = xlContinuous
            ElseIf datasheet.DrawingObjects("Check Box 50").Value = 1 Then
                mastersheet.Cells(rowid, columnid + 4).Value = "退社"
                mastersheet.Cells(rowid, columnid + 4).Borders.LineStyle = xlContinuous
            End If
        Else
            ' 人事側状態セット
            mastersheet.Cells(rowid, columnid + 4).Value = "×"
            ' 営業側状態セット
            cmprDate = Year(datasheet.Range("e9").Value) & month(datasheet.Range("e9").Value)
            If yText & mText = cmprDate Then
                mastersheet.Cells(rowid, columnid + 3).Value = "新規"
            Else
                If datasheet.chngPrsnChk.Value = 1 Or datasheet.chngPrsnChk.Value = True Then
                    mastersheet.Cells(rowid, columnid + 3).Value = "変更"
                Else
                    mastersheet.Cells(rowid, columnid + 3).Value = "既存"
                End If
            End If
        End If
    Else
        ' 作成対象帳票（月次）フラグセット
        If mainSheet.chkMthly.Value Then
            mastersheet.Cells(rowid, columnid - 3).Value = "○"
        End If
        ' 作成対象帳票（契約）フラグセット
        If mainSheet.chkCtrct.Value Then
            mastersheet.Cells(rowid, columnid - 2).Value = "○"
        End If
        
        If (datasheet.Range("e10").Value = "正社員" Or datasheet.Range("e10").Value = "契約社員") Then
            '' 営業側状態セット
            mastersheet.Cells(rowid, columnid - 1).Value = "×"
             ' 人事側状態セット
            If datasheet.DrawingObjects("Check Box 48").Value = 1 Then
                mastersheet.Cells(rowid, columnid).Value = "入社"
                mastersheet.Cells(rowid, columnid).Borders.LineStyle = xlContinuous
            ElseIf datasheet.DrawingObjects("Check Box 49").Value = 1 Then
                mastersheet.Cells(rowid, columnid).Value = "変更"
                mastersheet.Cells(rowid, columnid).Borders.LineStyle = xlContinuous
            ElseIf datasheet.DrawingObjects("Check Box 50").Value = 1 Then
                mastersheet.Cells(rowid, columnid).Value = "退社"
                mastersheet.Cells(rowid, columnid).Borders.LineStyle = xlContinuous
            End If
        Else
            ' 人事側状態セット
            mastersheet.Cells(rowid, columnid).Value = "×"
            ' 営業側状態セット
            cmprDate = Year(datasheet.Range("e9").Value) & month(datasheet.Range("e9").Value)
            If yText & mText = cmprDate Then
                mastersheet.Cells(rowid, columnid - 1).Value = "新規"
            Else
                If datasheet.chngPrsnChk.Value = 1 Or datasheet.chngPrsnChk.Value = True Then
                    mastersheet.Cells(rowid, columnid - 1).Value = "変更"
                Else
                    mastersheet.Cells(rowid, columnid - 1).Value = "既存"
                End If
            End If
        End If
    End If

    'If mastersheet.Cells(3, columnid).Value <> "人事側状態" Then
    
        ' 人事側状態セット
        'If datasheet.DrawingObjects("Check Box 48").Value = 1 Then
        '    mastersheet.Cells(rowid, columnid + 4).Value = "入社"
        '    mastersheet.Cells(rowid, columnid + 4).Borders.LineStyle = xlContinuous
        'ElseIf datasheet.DrawingObjects("Check Box 49").Value = 1 Then
        '    mastersheet.Cells(rowid, columnid + 4).Value = "変更"
        '    mastersheet.Cells(rowid, columnid + 4).Borders.LineStyle = xlContinuous
        'ElseIf datasheet.DrawingObjects("Check Box 50").Value = 1 Then
        '    mastersheet.Cells(rowid, columnid + 4).Value = "退社"
        '    mastersheet.Cells(rowid, columnid + 4).Borders.LineStyle = xlContinuous
        'End If
        ' 作成対象帳票（月次）フラグセット
        'If mainSheet.chkMthly.Value Then
        '    mastersheet.Cells(rowid, columnid + 1).Value = "○"
        'End If
        ' 作成対象帳票（契約）フラグセット
        'If mainSheet.chkCtrct.Value Then
        '    mastersheet.Cells(rowid, columnid + 2).Value = "○"
        'End If
        ' 営業側状態セット
        'If mText = month(Now) - 1 Then
        '    mastersheet.Cells(rowid, columnid + 3).Value = "新規"
        'Else
        '    If datasheet.chngPrsnChk.Value = 1 Then
        '        mastersheet.Cells(rowid, columnid + 3).Value = "変更"
        '    Else
        '        mastersheet.Cells(rowid, columnid + 3).Value = "既存"
        '    End If
        'End If
    'Else

        ' 人事側状態セット
        'If datasheet.DrawingObjects("Check Box 48").Value = 1 Then
        '    mastersheet.Cells(rowid, columnid).Value = "入社"
        '    mastersheet.Cells(rowid, columnid).Borders.LineStyle = xlContinuous
        'ElseIf datasheet.DrawingObjects("Check Box 49").Value = 1 Then
        '    mastersheet.Cells(rowid, columnid).Value = "変更"
        '    mastersheet.Cells(rowid, columnid).Borders.LineStyle = xlContinuous
        'ElseIf datasheet.DrawingObjects("Check Box 50").Value = 1 Then
        '    mastersheet.Cells(rowid, columnid).Value = "退社"
        '    mastersheet.Cells(rowid, columnid).Borders.LineStyle = xlContinuous
        'End If
        '' 作成対象帳票（月次）フラグセット
        'If mainSheet.chkMthly.Value Then
        '    mastersheet.Cells(rowid, columnid - 3).Value = "○"
        'End If
        ' 作成対象帳票（契約）フラグセット
        'If mainSheet.chkCtrct.Value Then
        '    mastersheet.Cells(rowid, columnid - 2).Value = "○"
        'End If
        '' 営業側状態セット
        'cmprDate = Year(datasheet.Range("e9").Value) & month(datasheet.Range("e9").Value)
        'If yText & mText = cmprDate Then
        '    mastersheet.Cells(rowid, columnid - 1).Value = "新規"
        'Else
        '    If datasheet.chngPrsnChk.Value = 1 Then
        '        mastersheet.Cells(rowid, columnid - 1).Value = "変更"
        '    Else
        '        mastersheet.Cells(rowid, columnid - 1).Value = "既存"
        '    End If
        'End If
   ' End If
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "TSRS帳票管理マスタ取得処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

Private Function wdayAdj(chkDt)
' weekday adjust
    Select Case Weekday(chkDt)
    Case 1
        wdayAdj = DateSerial(Year(chkDt), month(chkDt), Day(chkDt) - 2)
    Case 7
        wdayAdj = DateSerial(Year(chkDt), month(chkDt), Day(chkDt) - 1)
    Case Else
        wdayAdj = chkDt
    End Select

End Function

Private Function AddMonth(fText)
' 締め加算月数取得
    Select Case fText
    Case "翌月"
        AddMonth = 1
    Case "翌々月"
        AddMonth = 2
    Case "翌々々月"
        AddMonth = 3
    End Select

End Function

'契約帳票ボタン
Private Sub btnContract_Click()
    UserForm4.Show 0
    
On Error GoTo Err_Handle

    Dim templatePath, savePath, endRowNo, rowid, columnCount
    Dim contractFlg, buyFlg, buyFolder, personFlg, companyNm, employForms
    
    'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\テンプレート\契約関連必要テンプレート\"
    
    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    Set mastersheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    endRowNo = mastersheet.UsedRange.Rows(mastersheet.UsedRange.Rows.count).Row
    contractFlg = False
     
    columnCount = mastersheet.UsedRange.Columns.count
    If mastersheet.Cells(3, columnCount).Value = "人事側状態" Then
        'If endRowNo >= 4 Then
            '人事側の契約フォルダを生成
            'If Dir(savePath & "\人事側", vbDirectory) = "" Then
                'MkDir savePath & "\人事側"
            'End If
            
            'If Dir(savePath & "\人事側\正社員", vbDirectory) = "" Then
                'MkDir savePath & "\人事側\正社員"
            'End If
            
            'If Dir(savePath & "\人事側\契約社員", vbDirectory) = "" Then
                'MkDir savePath & "\人事側\契約社員"
            'End If
            
            '購買側の契約フォルダを生成
            'If Dir(savePath & "\購買側", vbDirectory) = "" Then
                'MkDir savePath & "\購買側"
            'End If
        'End If
        
        orderId = 0
        
        '帳票出力
        For rowid = 4 To endRowNo
        '契約帳票フラグを設定する場合、マスタデータを基に、契約帳票を生成する
            If mastersheet.Cells(rowid, columnCount - 2).Value = "○" Then
                contractFlg = True
                buyFlg = mastersheet.Cells(rowid, columnCount - 1).Value
                personFlg = mastersheet.Cells(rowid, columnCount).Value
                companyNm = mastersheet.Range("D" & rowid).Value
                
                '人事側の契約フォルダを生成
                If (mastersheet.Range("E" & rowid).Value = "正社員") Then
                    If Dir(savePath & "\人事側", vbDirectory) = "" Then
                        MkDir savePath & "\人事側"
                    End If
                    If Dir(savePath & "\人事側\正社員", vbDirectory) = "" Then
                        MkDir savePath & "\人事側\正社員"
                    End If
                ElseIf (mastersheet.Range("E" & rowid).Value = "契約社員") Then
                    If Dir(savePath & "\人事側", vbDirectory) = "" Then
                        MkDir savePath & "\人事側"
                    End If
                    If Dir(savePath & "\人事側\契約社員", vbDirectory) = "" Then
                        MkDir savePath & "\人事側\契約社員"
                    End If
                Else
                    '購買側の契約フォルダを生成
                    If Dir(savePath & "\購買側", vbDirectory) = "" Then
                        MkDir savePath & "\購買側"
                    End If
                End If
                
                '購買側の契約を生成
                If buyFlg = "新規" Then
                    Call SetBuySideNew(templatePath, savePath, companyNm, rowid)
                ElseIf buyFlg = "既存" Then
                    Call SetBuySideExist(templatePath, savePath, companyNm, rowid)
                ElseIf buyFlg = "変更" Then
                    Call SetBuySideChange(templatePath, savePath, companyNm, rowid)
                End If
                
                '人事側の契約を生成
                employForms = mastersheet.Range("E" & rowid).Value
                If personFlg = "入社" Then
                    Call SetPersonEnterInfo(employForms, templatePath, savePath, rowid)
                ElseIf personFlg = "変更" Then
                    Call SetPersonChangeInfo(employForms, templatePath, savePath, rowid)
                ElseIf personFlg = "退社" Then
                    Call SetPersonQuitInfo(employForms, templatePath, savePath, rowid)
                End If
            End If
        Next
    
        Application.DisplayAlerts = False
        Application.Workbooks("月次処理マクロ.xls").Worksheets("master").Delete
        Application.Workbooks("月次処理マクロ.xls").Save
        Unload UserForm4
        
        If contractFlg = False Then
            If Dir(savePath & "\人事側", vbDirectory) <> "" Then
                deleteFolder (savePath & "\人事側")
            End If
    
            '購買側の契約フォルダを生成
            If Dir(savePath & "\購買側", vbDirectory) <> "" Then
                deleteFolder (savePath & "\購買側")
            End If
            
            MsgBox "TSRS帳票管理マスタで出力する契約帳票を指定してください。"
        Else
            MsgBox "契約生成処理完了、下記の出力先に確認してください。" & Chr(10) & savePath
        End If
        
        Application.Workbooks("月次処理マクロ.xls").Close
    Else
        Unload UserForm4
        MsgBox "マスタデータが不正ですので、マスタデータを再度作成してください。"
    End If
    Exit Sub
    
Err_Handle:
    Unload UserForm4
    'エラーメッセージを表示
    MsgBox "契約生成処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
    
End Sub

'送付状(書類)内容を作成する
Private Sub setSendFormPrintInfo_keiyaku(ByVal rowid, ByVal wkbName, ByVal flgnum)
On Error GoTo Err_Handle
    Dim sendFormSheet
    Const adrsSplitPos = 22
    Set sendFormSheet = Application.Workbooks(wkbName).Worksheets("書類送付状")
    
    sendFormSheet.Range("i16").Value = Format(Date, "ggge年ooood日")
    '書類部数
    If (flgnum = 1) Then
        '郵便番号
        sendFormSheet.Range("b2").Value = "〒" & mastersheet.Range("BV" & rowid).Value
        '連絡先住所
        setTempAdrs = mastersheet.Range("BU" & rowid).Value
        setTempAdrs1 = setTempAdrs
        setTempAdrs2 = ""
        sendFormSheet.Range("c2").Value = setTempAdrs1
        sendFormSheet.Range("b3").Value = setTempAdrs2
        '下位会社
        sendFormSheet.Range("b5").Value = mastersheet.Range("D" & rowid).Value
        '下位会社担当
        sendFormSheet.Range("b6").Value = mastersheet.Range("EO" & rowid).Value & " " & mastersheet.Range("EN" & rowid).Value
        '下位会社担当
        sendFormSheet.Range("b17").Value = mastersheet.Range("EO" & rowid).Value & " " & mastersheet.Range("EN" & rowid).Value
        '書類
        sendFormSheet.Range("c8").Value = "注文書"
        '書類送付個数
        sendFormSheet.Range("c34").Value = "注文書"
        sendFormSheet.Range("i34").Value = 1
        sendFormSheet.Range("b35").Value = 2
        sendFormSheet.Range("c35").Value = "注文請書"
        sendFormSheet.Range("i35").Value = "1"
        sendFormSheet.Range("j35").Value = "部"
    Else
        '郵便番号
        sendFormSheet.Range("b2").Value = "〒" & mastersheet.Range("BV" & rowid).Value
        '連絡先住所
        setTempAdrs = mastersheet.Range("BU" & rowid).Value
        setTempAdrs1 = setTempAdrs
        setTempAdrs2 = ""
        sendFormSheet.Range("c2").Value = setTempAdrs1
        sendFormSheet.Range("b3").Value = setTempAdrs2
        '下位会社
        'sendFormSheet.Range("b5").Value = mastersheet.Range("D" & rowid).Value
        '下位会社担当
        sendFormSheet.Range("b6").Value = mastersheet.Range("B" & rowid).Value
        '下位会社担当
        sendFormSheet.Range("b17").Value = mastersheet.Range("B" & rowid).Value
    End If
    
    If (flgnum = 2 And mastersheet.Range("E" & rowid).Value = "正社員") Then
        sendFormSheet.Range("c8").Value = "労働条件通知"
        sendFormSheet.Range("c34").Value = "労働条件通知書"
        sendFormSheet.Range("i34").Value = 2
        sendFormSheet.Range("b35").Value = 2
        sendFormSheet.Range("c35").Value = "返信用封筒"
        sendFormSheet.Range("i35").Value = "1"
        sendFormSheet.Range("j35").Value = "部"
    ElseIf (flgnum = 2 And mastersheet.Range("E" & rowid).Value = "契約社員") Then
        sendFormSheet.Range("c8").Value = "雇用契約書"
        sendFormSheet.Range("c34").Value = "雇用契約書"
        sendFormSheet.Range("i34").Value = 2
        sendFormSheet.Range("b35").Value = 2
        sendFormSheet.Range("c35").Value = "返信用封筒"
        sendFormSheet.Range("i35").Value = "1"
        sendFormSheet.Range("j35").Value = "部"
    End If
    
    If (flgnum = 3) Then
        sendFormSheet.Range("c8").Value = "退社連絡票"
        sendFormSheet.Range("c34").Value = "退社連絡票"
        sendFormSheet.Range("i34").Value = 1
        sendFormSheet.Range("b35").Value = 2
        sendFormSheet.Range("c35").Value = "機密保持に関する誓約書"
        sendFormSheet.Range("i35").Value = "1"
        sendFormSheet.Range("j35").Value = "部"
        sendFormSheet.Range("b36").Value = 3
        sendFormSheet.Range("c36").Value = "返信用封筒"
        sendFormSheet.Range("i36").Value = "1"
        sendFormSheet.Range("j36").Value = "部"
    End If
    '送付状(書類)保存
    Application.Workbooks(wkbName).Save
    '送付状(書類)を閉じる
    Application.Workbooks(wkbName).Close
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "契約生成処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'購買側-新規時
Private Sub SetBuySideNew(ByVal templatePath, ByVal savePath, ByVal companyNm, ByVal rowid)
    Dim filePath, saveFilePath
    filePath = templatePath & "\購買側\新規\*.xls"
    
    Dim baseContractFile, secretContractFile, orderFormFile, orderFormName, orderFileName

    '下位会社フォルダの作成
    saveFilePath = savePath & "\購買側\" & companyNm & "_新規"
    If Dir(saveFilePath, vbDirectory) = "" Then
        MkDir saveFilePath
        Call FilesCopy(filePath, saveFilePath)
        
        'ファイルを削除
        If Dir(saveFilePath & "\" & "30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls") <> "" Then
            Kill saveFilePath & "\" & "30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls"
        End If
        
        If Dir(saveFilePath & "\個別契約", vbDirectory) = "" Then
            MkDir saveFilePath & "\個別契約"
        End If
        
    End If
    
    '【TSRS】基本契約書（ﾌｫｰﾏｯﾄ）契約番号付き.xls を作成
    Set baseContractFile = Workbooks.Open(saveFilePath & "\" & UnicodeToString("""\u329e""") & "1【TSRS】基本契約書（ﾌｫｰﾏｯﾄ）契約番号付き.xls")
    Call baseContract(UnicodeToString("""\u329e""") & "1【TSRS】基本契約書（ﾌｫｰﾏｯﾄ）契約番号付き.xls", "OK", rowid)
    baseContractFile.Save
    baseContractFile.Close
    Set baseContractFile = Nothing
        
    '2【TSRS】機密情報守秘契約書.xls を作成
    Set secretContractFile = Workbooks.Open(saveFilePath & "\" & UnicodeToString("""\u329e""") & "2【TSRS】機密情報守秘契約書.xls")
    Call secretContract(UnicodeToString("""\u329e""") & "2【TSRS】機密情報守秘契約書.xls", "OK", rowid)
    secretContractFile.Save
    secretContractFile.Close
    Set secretContractFile = Nothing
    
    '30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls を作成
    'ファイルをコピーして、名前を変更
    orderFileName = "30_注文書・注文請け書_" & companyNm & "様-" _
                  & Format(mastersheet.Range("P" & rowid).Value, "YYYYMM") & "～" & Format(mastersheet.Range("Q" & rowid).Value, "YYYYMM") _
                  & "_" & mastersheet.Range("B" & rowid).Value & ".xls"
    orderFormName = saveFilePath & "\個別契約\" & orderFileName
    
    FileCopy templatePath & "\購買側\新規\30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls", orderFormName
    
    'ファイルを編集
    Set orderFormFile = Workbooks.Open(orderFormName)
    orderId = orderId + 1
    Call orderContract(orderFileName, "注文書", rowid, orderId)
    orderFormFile.Save
    orderFormFile.Close
    Set orderFormFile = Nothing
    
End Sub

'購買側-既存時
Private Sub SetBuySideExist(ByVal templatePath, ByVal savePath, ByVal companyNm, ByVal rowid)
    Dim filePath, saveFilePath, orderFormFile, orderFormName, orderFileName
    filePath = templatePath & "\購買側\既存\*.xls"
    '下位会社フォルダの作成
    saveFilePath = savePath & "\購買側\" & companyNm & "_既存"
    If Dir(saveFilePath, vbDirectory) = "" Then
        MkDir saveFilePath
        Call FilesCopy(filePath, saveFilePath)
        
        'ファイルを削除
        If Dir(saveFilePath & "\" & "30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls") <> "" Then
            Kill saveFilePath & "\" & "30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls"
        End If
        
        If Dir(saveFilePath & "\個別契約", vbDirectory) = "" Then
            MkDir saveFilePath & "\個別契約"
        End If
        
    End If
    
    '30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls を作成
    'ファイルをコピーして、名前を変更
    orderFileName = "30_注文書・注文請け書_" & companyNm & "様-" _
                  & Format(mastersheet.Range("P" & rowid).Value, "YYYYMM") & "～" & Format(mastersheet.Range("Q" & rowid).Value, "YYYYMM") _
                  & "_" & mastersheet.Range("B" & rowid).Value & ".xls"
    orderFormName = saveFilePath & "\個別契約\" & orderFileName
    
    FileCopy templatePath & "\購買側\新規\30_注文書・注文請け書_会社名様-対象期間月_氏名様.xls", orderFormName
    
    'ファイルを編集
    Set orderFormFile = Workbooks.Open(orderFormName)
    orderId = orderId + 1
    Call orderContract(orderFileName, "注文書", rowid, orderId)
    
    'テンプレートパス
    filepath_soufujyou = templatePath & "書類送付状.xls"
    '送付状（書類）の作成
    fileProntName = "書類送付状" & "_注文書・注文請け書_" & Format(Date, "YYYYMMDD") & ".xls"
    filePrint = saveFilePath & "\個別契約" & "\" & fileProntName
    'ファイルを削除
    If Dir(filePrint) <> "" Then
        Kill filePrint
    End If
    FileCopy filepath_soufujyou, filePrint
    Workbooks.Open fileName:=filePrint
    '送付状(書類)内容の作成
    Call setSendFormPrintInfo_keiyaku(rowid, fileProntName, 1)
    
    orderFormFile.Save
    orderFormFile.Close
    Set orderFormFile = Nothing
End Sub

'購買側-変更時
Private Sub SetBuySideChange(ByVal templatePath, ByVal savePath, ByVal companyNm, ByVal rowid)
    Dim filePath, saveFilePath, orderFormFile, orderFormName
    filePath = templatePath & "\購買側\変更\*.xls"

    '下位会社フォルダの作成
    saveFilePath = savePath & "\購買側\" & companyNm & "_変更"
    If Dir(saveFilePath, vbDirectory) = "" Then
        MkDir saveFilePath
        Call FilesCopy(filePath, saveFilePath)
    End If
    
End Sub

'人事側-入社時
Private Sub SetPersonEnterInfo(ByVal employForms, ByVal templatePath, ByVal savePath, ByVal rowid)
    Dim filePath As String, saveFilePath As String, joinedContactFile, adoptNoticeFile, workConditionsFile, workConditionsName, workConditionsFileNm
            
    If employForms = "正社員" Then
        '正社員の場合
        filePath = templatePath & "\人事側_正社員\入社\*.xls"
        saveFilePath = savePath & "\人事側\正社員\" & mastersheet.Range("B" & rowid).Value & "(" & mastersheet.Range("A" & rowid).Value & ")_入社"
        Call FilesCopy(filePath, saveFilePath)
        
        '○【社労士様】入社連絡票.xls を作成
        Set joinedContactFile = Workbooks.Open(saveFilePath & "\" & "○【社労士様】入社連絡票.xls")
        Call contactContract("○【社労士様】入社連絡票.xls", "入社連絡票", rowid)
        joinedContactFile.Save
        joinedContactFile.Close
        Set joinedContactFile = Nothing
        
        '○1_【TSRS】採用通知書.xls を作成
        Set adoptNoticeFile = Workbooks.Open(saveFilePath & "\" & "○1_【TSRS】採用通知書.xls")
        Call noticeContract("○1_【TSRS】採用通知書.xls", "訂正", rowid)
        adoptNoticeFile.Save
        adoptNoticeFile.Close
        Set adoptNoticeFile = Nothing
        
        '○3_【TSRS】労働条件通知書_氏名.xls を作成
        'ファイルを削除
        If Dir(saveFilePath & "\" & "○3_【TSRS】労働条件通知書_氏名.xls") <> "" Then
            Kill saveFilePath & "\" & "○3_【TSRS】労働条件通知書_氏名.xls"
        End If
        
        'ファイルをコピーして、名前を変更
        workConditionsFileNm = "○3_【TSRS】労働条件通知書_" & mastersheet.Range("B" & rowid).Value & ".xls"
        workConditionsName = saveFilePath & "\" & workConditionsFileNm
        FileCopy templatePath & "\人事側_正社員\入社\○3_【TSRS】労働条件通知書_氏名.xls", workConditionsName
        
        'ファイルを編集
        Set workConditionsFile = Workbooks.Open(workConditionsName)
        Call workCondition(workConditionsFileNm, "Sheet1", rowid)
        workConditionsFile.Save
        workConditionsFile.Close
        Set workConditionsFile = Nothing

        '○5_【TSRS】機密保持に関する誓約書（入社時提出）.xls を作成
        Set adoptNoticeFile = Workbooks.Open(saveFilePath & "\" & "○5_【TSRS】機密保持に関する誓約書（入社時提出）.xls")
        Call securityContract("○5_【TSRS】機密保持に関する誓約書（入社時提出）.xls", "訂正", rowid)
        adoptNoticeFile.Save
        adoptNoticeFile.Close
        Set adoptNoticeFile = Nothing

        '個人情報新規申請書
        '○8_【ＴＳＲＳ】個人情報申請書-新規.xls を作成
        Set personalInfoFile = Workbooks.Open(saveFilePath & "\" & prsnlInfPrprNewBk)
        Call prsnlInfoContract(prsnlInfPrprNewBk, prsnlInfNewSheetNm, rowid)
        personalInfoFile.Save
        personalInfoFile.Close
        Set personalInfoFile = Nothing

    Else
        '契約社員の場合
        filePath = templatePath & "\人事側_契約社員\入社\*.xls"
        saveFilePath = savePath & "\人事側\契約社員\" & mastersheet.Range("B" & rowid).Value & "(" & mastersheet.Range("A" & rowid).Value & ")_" & mastersheet.Range("D" & rowid).Value & "_入社"
        Call FilesCopy(filePath, saveFilePath)
        
        '○【社労士様】入社連絡票.xls を作成
        Set joinedContactFile = Workbooks.Open(saveFilePath & "\" & "○【社労士様】入社連絡票.xls")
        Call contactContractDispatch("○【社労士様】入社連絡票.xls", "入社連絡票", rowid)
        joinedContactFile.Save
        joinedContactFile.Close
        Set joinedContactFile = Nothing
        
        '○1_契約_【TSRS】採用通知書.xls を作成
        Set adoptNoticeFile = Workbooks.Open(saveFilePath & "\" & "○1_契約_【TSRS】採用通知書.xls")
        Call noticeContractDispatch("○1_契約_【TSRS】採用通知書.xls", "訂正", rowid)
        adoptNoticeFile.Save
        adoptNoticeFile.Close
        Set adoptNoticeFile = Nothing
        
        '○3_契約【TSRS-変更】労働条件通知書_氏名.xls を作成
        'ファイルを削除
        If Dir(saveFilePath & "\" & "○3_契約【TSRS-変更】労働条件通知書_氏名.xls") <> "" Then
            Kill saveFilePath & "\" & "○3_契約【TSRS-変更】労働条件通知書_氏名.xls"
        End If
        
        'ファイルをコピーして、名前を変更
        workConditionsFileNm = "○3_契約【TSRS-変更】労働条件通知書_" & mastersheet.Range("B" & rowid).Value & ".xls"
        workConditionsName = saveFilePath & "\" & workConditionsFileNm
        FileCopy templatePath & "\人事側_契約社員\入社\○3_契約【TSRS-変更】労働条件通知書_氏名.xls", workConditionsName
        
        'ファイルを編集
        Set workConditionsFile = Workbooks.Open(workConditionsName)
        Call workConditionDispatch(workConditionsFileNm, "Sheet1", rowid)
        workConditionsFile.Save
        workConditionsFile.Close
        Set workConditionsFile = Nothing
    
        '○5_契約【TSRS】機密保持に関する誓約書（入社時提出）.xls を作成
        Set adoptNoticeFile = Workbooks.Open(saveFilePath & "\" & "○5_契約【TSRS】機密保持に関する誓約書（入社時提出）.xls")
        Call securityContractDispatch("○5_契約【TSRS】機密保持に関する誓約書（入社時提出）.xls", "訂正", rowid)
        adoptNoticeFile.Save
        adoptNoticeFile.Close
        Set adoptNoticeFile = Nothing
    
        '個人情報新規申請書
        '○8_契約【ＴＳＲＳ】個人情報申請書-新規.xls
        Set personalInfoFile = Workbooks.Open(saveFilePath & "\" & prsnlInfCntrNewBk)
        Call prsnlInfoContract(prsnlInfCntrNewBk, prsnlInfNewSheetNm, rowid)
        personalInfoFile.Save
        personalInfoFile.Close
        Set personalInfoFile = Nothing
    
    End If
End Sub

'人事側-変更時
Private Sub SetPersonChangeInfo(ByVal employForms, ByVal templatePath, ByVal savePath, ByVal rowid)
    Dim filePath As String, saveFilePath As String, workConditionsFile, workConditionsName, personInfoAplFile, workConditionsFileNm
            
    If employForms = "正社員" Then
        '正社員の場合
        filePath = templatePath & "\人事側_正社員\変更\*.xls"
        saveFilePath = savePath & "\人事側\正社員\" & mastersheet.Range("B" & rowid).Value & "(" & mastersheet.Range("A" & rowid).Value & ")_変更"
        Call FilesCopy(filePath, saveFilePath)
        
        '○1_【TSRS-変更】労働条件通知書_氏名.xls を作成
         'ファイルを削除
        If Dir(saveFilePath & "\" & "○1_【TSRS-変更】労働条件通知書_氏名.xls") <> "" Then
            Kill saveFilePath & "\" & "○1_【TSRS-変更】労働条件通知書_氏名.xls"
        End If
        
        'ファイルをコピーして、名前を変更
        workConditionsFileNm = "○1_【TSRS-変更】労働条件通知書_" & mastersheet.Range("B" & rowid).Value & ".xls"
        workConditionsName = saveFilePath & "\" & workConditionsFileNm
        FileCopy templatePath & "\人事側_正社員\変更\○1_【TSRS-変更】労働条件通知書_氏名.xls", workConditionsName
        
        'ファイルを編集
        Set workConditionsFile = Workbooks.Open(workConditionsName)
        Call changeWorkCondition(workConditionsFileNm, "Sheet1", rowid)
        workConditionsFile.Save
        workConditionsFile.Close
        Set workConditionsFile = Nothing
        
        '○2_【ＴＳＲＳ-変更】個人情報申請書.xls
        Set personInfoAplFile = Workbooks.Open(saveFilePath & "\" & "○2_【ＴＳＲＳ-変更】個人情報申請書.xls")
        Call changePersonInfo("○2_【ＴＳＲＳ-変更】個人情報申請書.xls", "個人情報変更申請書", rowid)
        personInfoAplFile.Save
        personInfoAplFile.Close
        Set personInfoAplFile = Nothing
    Else
        '契約社員の場合
        filePath = templatePath & "\人事側_契約社員\変更\*.xls"
        saveFilePath = savePath & "\人事側\契約社員\" & mastersheet.Range("B" & rowid).Value & "(" & mastersheet.Range("A" & rowid).Value & ")_" & mastersheet.Range("D" & rowid).Value & "_変更"
        Call FilesCopy(filePath, saveFilePath)
        
        '○1_契約【TSRS-変更】労働条件通知書_氏名.xls を作成
         'ファイルを削除
        If Dir(saveFilePath & "\" & "○1_契約【TSRS-変更】労働条件通知書_氏名.xls") <> "" Then
            Kill saveFilePath & "\" & "○1_契約【TSRS-変更】労働条件通知書_氏名.xls"
        End If
        
        'ファイルをコピーして、名前を変更
        workConditionsFileNm = "○1_契約【TSRS-変更】労働条件通知書_" & mastersheet.Range("B" & rowid).Value & ".xls"
        workConditionsName = saveFilePath & "\" & workConditionsFileNm
        FileCopy templatePath & "\人事側_契約社員\変更\○1_契約【TSRS-変更】労働条件通知書_氏名.xls", workConditionsName
        
        'ファイルを編集
        Set workConditionsFile = Workbooks.Open(workConditionsName)
        Call changeWorkConditionDispatch(workConditionsFileNm, "Sheet1", rowid)
        workConditionsFile.Save
        workConditionsFile.Close
        Set workConditionsFile = Nothing
        
        '○2_契約【ＴＳＲＳ-変更】個人情報申請書.xls
        Set personInfoAplFile = Workbooks.Open(saveFilePath & "\" & "○2_契約【ＴＳＲＳ-変更】個人情報申請書.xls")
        Call changePersonInfoDispatch("○2_契約【ＴＳＲＳ-変更】個人情報申請書.xls", "個人情報変更申請書", rowid)
        personInfoAplFile.Save
        personInfoAplFile.Close
        Set personInfoAplFile = Nothing
    End If
    'テンプレートパス
    filepath_soufujyou = templatePath & "書類送付状.xls"
    '送付状（書類）の作成
    fileProntName = "書類送付状" & "_労働条件通知書_" & Format(Date, "YYYYMMDD") & ".xls"
    filePrint = saveFilePath & "\" & fileProntName
    'ファイルを削除
    If Dir(filePrint) <> "" Then
        Kill filePrint
    End If
    FileCopy filepath_soufujyou, filePrint
    Workbooks.Open fileName:=filePrint
    '送付状(書類)内容の作成
    Call setSendFormPrintInfo_keiyaku(rowid, fileProntName, 2)
End Sub

'人事側-退社時
Private Sub SetPersonQuitInfo(ByVal employForms, ByVal templatePath, ByVal savePath, ByVal rowid)
    Dim filePath As String, saveFilePath As String, leaveContactFile
            
    If employForms = "正社員" Then
        '正社員の場合
        filePath = templatePath & "\人事側_正社員\退社\*.xls"
        saveFilePath = savePath & "\人事側\正社員\" & mastersheet.Range("B" & rowid).Value & "(" & mastersheet.Range("A" & rowid).Value & ")_退社"
        Call FilesCopy(filePath, saveFilePath)
        
        '○【社労士様】退社連絡票.xls
        Set leaveContactFile = Workbooks.Open(saveFilePath & "\" & "○【社労士様】退社連絡票.xls")
        Call resignationConnect("○【社労士様】退社連絡票.xls", "退社連絡票", rowid)
        leaveContactFile.Save
        leaveContactFile.Close
        Set leaveContactFile = Nothing

    Else
        '契約社員の場合
        filePath = templatePath & "\人事側_契約社員\退社\*.xls"
        saveFilePath = savePath & "\人事側\契約社員\" & mastersheet.Range("B" & rowid).Value & "(" & mastersheet.Range("A" & rowid).Value & ")_" & mastersheet.Range("D" & rowid).Value & "_退社"
        Call FilesCopy(filePath, saveFilePath)
        
        '○【社労士様】退社連絡票.xls
        Set leaveContactFile = Workbooks.Open(saveFilePath & "\" & "○【社労士様】退社連絡票.xls")
        Call resignationConnectDispatch("○【社労士様】退社連絡票.xls", "退社連絡票", rowid)
        leaveContactFile.Save
        leaveContactFile.Close
        Set leaveContactFile = Nothing
        
    End If
    
    'テンプレートパス
    filepath_soufujyou = templatePath & "書類送付状.xls"
    '送付状（書類）の作成
    fileProntName = "書類送付状" & "_退社連絡票・機密保持に関する誓約書_" & Format(Date, "YYYYMMDD") & ".xls"
    filePrint = saveFilePath & "\" & fileProntName
    'ファイルを削除
    If Dir(filePrint) <> "" Then
        Kill filePrint
    End If
    FileCopy filepath_soufujyou, filePrint
    Workbooks.Open fileName:=filePrint
    '送付状(書類)内容の作成
    Call setSendFormPrintInfo_keiyaku(rowid, fileProntName, 3)
End Sub

'ファイルをコピー
Private Sub FilesCopy(ByVal filePath, ByVal saveFilePath)
    If Dir(saveFilePath, vbDirectory) = "" Then
            MkDir saveFilePath
    End If
        
    Dim Fso As Object
    Set Fso = CreateObject("Scripting.FileSystemObject")
    Fso.copyfile filePath, saveFilePath
    Set Fso = Nothing
    
    'Shell Environ("comspec") & " /c xcopy " & filePath & " " & saveFilePath, vbHide
End Sub

Private Function UnicodeToString(ByVal str)
    Set objJS = CreateObject("msscriptcontrol.scriptcontrol")
    objJS.Language = "javascript"
    objJS.addcode "function aa(str){str=str.replace(/\\/g,""%"");return unescape(str);}"
    
    'resultStr = xx.eval("aa(""\u329e"")")
    UnicodeToString = objJS.eval("aa(" & str & ")")
End Function

'削除フォルダ
Private Sub deleteFolder(ByVal folderPath)
    CreateObject("Scripting.FileSystemObject").deleteFolder folderPath, force:=True
End Sub

'基本契約書
Public Sub baseContract(ByVal book, ByVal sheet, ByVal rowid)

    Set baseSheet = Application.Workbooks(book).Worksheets(sheet)
    'Set masterSheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    '************************************VBAバグ対応 WuYang 20160704 Start************************************************************
    'baseSheet.Range("I" & 1).Value = "基" & mastersheet.Range("C" & rowid).Value & Format(Now, "mmdd")
     baseSheet.Range("G" & 1).Value = "基" & mastersheet.Range("C" & rowid).Value & Format(Now, "mmdd")
    '************************************VBAバグ対応 WuYang 20160704 End************************************************************
    baseSheet.Range("H" & 7).Value = mastersheet.Range("D" & rowid).Value
    
End Sub

'機密情報守秘契約書

Public Sub secretContract(ByVal book, ByVal sheet, ByVal rowid)
    Set baseSheet = Application.Workbooks(book).Worksheets(sheet)

    baseSheet.Range("H" & 1).Value = "基" & mastersheet.Range("C" & rowid).Value & Format(Now, "mmdd")
    baseSheet.Range("G" & 6).Value = mastersheet.Range("D" & rowid).Value
    StrFmt = Format(Now, "ggge年ooood日")
    baseSheet.Range("B" & 212).Value = StrFmt

End Sub

'注文書・注文請け書_会社名様-対象期間月_氏名様
Public Sub orderContract(ByVal book, ByVal sheet, ByVal rowid, ByVal pid)
    Dim startDate, testdate
    Set orderSheet = Application.Workbooks(book).Worksheets(sheet)
    '  masterSheet.Range("P" & rowid).Value
    '契約開始日
    startDate = mastersheet.Range("P" & rowid).Value
    '注文番号
   
    orderSheet.Range("I" & 2).Value = "注" & Format(DateAdd("d", -1, startDate), "YYMMDD") & Format(pid, "00")
    '提出日
    orderSheet.Range("I" & 3).Value = Format(DateAdd("d", -1, startDate), "YYYY/MM/DD")
    '会社
    orderSheet.Range("B" & 8).Value = mastersheet.Range("D" & rowid).Value
    '案件名称
    
     orderSheet.Range("C" & 19).Value = mastersheet.Range(" AE" & rowid).Value
     '作業期間
     
    orderSheet.Range("C" & 20).Value = mastersheet.Range("P" & rowid).Value
    
    orderSheet.Range("F" & 20).Value = mastersheet.Range("Q" & rowid).Value
    '作業内容・範囲
       
        orderSheet.Range("C" & 21).Value = mastersheet.Range("AF" & rowid).Value
    '作業場所
    
    orderSheet.Range("C" & 22).Value = mastersheet.Range("AG" & rowid).Value
    '作業者名
    orderSheet.Range("B" & 24).Value = mastersheet.Range("B" & rowid).Value
    '基準単価
    orderSheet.Range("C" & 24).Value = mastersheet.Range("J" & rowid).Value
    '基本時間
    orderSheet.Range("D" & 24).Value = mastersheet.Range("H" & rowid).Value
    
    orderSheet.Range("F" & 24).Value = mastersheet.Range("G" & rowid).Value
    
    '超過単価
   ' orderSheet.Range("G" & 24).Value = mastersheet.Range("K" & rowid).Value
    '控除単価
    'orderSheet.Range("H" & 24).Value = mastersheet.Range("L" & rowid).Value
    '工数
    orderSheet.Range("I" & 24).Value = mastersheet.Range("BY" & rowid).Value
    '注文内容
    orderSheet.Range("C" & 28).Value = mastersheet.Range("BF" & rowid).Value
    'お支払条件
    '毎月末締め翌々月25日のお支払
     Dim starteDay, endDay
      '賃金締切日
     starteDay = Replace(mastersheet.Range("CK" & rowid).Value, "日", "")
     If IsNumeric(starteDay) Then
        starteDay = starteDay & "日"
     End If
      
    '賃金支払日
'    endDay = Replace(mastersheet.Range("CL" & rowid).Value, "日", "")
'    If IsNumeric(endDay) Then
'        endDay = endDay & "日"
'    End If
     endDay = mastersheet.Range("CL" & rowid).Value
     orderSheet.Range("C" & 33).Value = "毎月" & starteDay & "締め" & mastersheet.Range("S" & rowid).Value & endDay & "日のお支払"
    
End Sub
'
'入社連絡票 正社員_内勤
Public Sub contactContract(ByVal book, ByVal sheet, ByVal rowid)
    Set contactSheet = Application.Workbooks(book).Worksheets(sheet)
    '社員Ｎｏ
    contactSheet.Range("H" & 3).Value = mastersheet.Range("A" & rowid).Value
    'フリガナ
     contactSheet.Range("P" & 3).Value = mastersheet.Range("CD" & rowid).Value
    '氏名
    contactSheet.Range("P" & 4).Value = mastersheet.Range("B" & rowid).Value
    '性別
    contactSheet.Range("AI" & 4).Value = mastersheet.Range("CB" & rowid).Value
    '
    joinDay = mastersheet.Range("U" & rowid).Value
    
    Dim dateFmtStr, dateArray
    If Len(joinDay) > 0 Then
    StrFmt = Format(joinDay, "ggge年ooood日")

    '入社年月日

    dateFmtStr = Replace(Mid(StrFmt, 3), "年", "-")
    dateFmtStr = Replace(dateFmtStr, "月", "-")
    dateFmtStr = Replace(dateFmtStr, "日", "")
    
    dateArray = Split(dateFmtStr, "-")
    contactSheet.Range("J" & 5).Value = dateArray(0)
    contactSheet.Range("M" & 5).Value = dateArray(1)
    contactSheet.Range("P" & 5).Value = dateArray(2)
    
    End If
    '生年月日
    birthDay = mastersheet.Range("CC" & rowid).Value
    If Len(birthDay) > 0 Then
    birdayFmt = Format(birthDay, "ggge年ooood日")
    
    dateFmtStr = Replace(Mid(birdayFmt, 3), "年", "-")
    dateFmtStr = Replace(dateFmtStr, "月", "-")
    dateFmtStr = Replace(dateFmtStr, "日", "")

    dateArray = Split(dateFmtStr, "-")
    contactSheet.Range("AD" & 5).Value = dateArray(0)
    contactSheet.Range("AG" & 5).Value = dateArray(1)
    contactSheet.Range("AJ" & 5).Value = dateArray(2)
    
    '昭 ・ 平
    If InStr(birdayFmt, "平成") > 0 Then
        '年号が平成の場合、"平"を指定する
        contactSheet.Range("Z5").Characters(5, 2).Font.Color = vbRed
        contactSheet.Range("Z5").Characters(5, 2).Font.Bold = True
    ElseIf InStr(birdayFmt, "昭和") > 0 Then
       '年号が昭和の場合、"昭"を指定する
       contactSheet.Range("Z5").Characters(1, 2).Font.Color = vbRed
       contactSheet.Range("Z5").Characters(1, 2).Font.Bold = True
    End If
    
    End If
    'フリガナ
    'contactSheet.Range("H" & 6).Value = mastersheet.Range("BT" & rowid).Value
    '〒
    zipcode = mastersheet.Range("BV" & rowid).Value
    If Len(zipcode) > 0 Then
    'contactSheet.Range("I" & 7).Value = Mid(zipcode, 3, (InStr(zipcode, "-") - 3))
    zipcode = Replace(zipcode, "〒", "")
    Dim zipArray
    zipArray = Split(zipcode, "-")
    contactSheet.Range("I" & 7).Value = zipArray(0)
    If UBound(zipArray) > 0 Then
        contactSheet.Range("M" & 7).Value = zipArray(1)
    End If
    
    
'    contactSheet.Range("I" & 7).Value = Mid(zipcode, 1, (InStr(zipcode, "-") - 3))
'    contactSheet.Range("M" & 7).Value = Mid(zipcode, InStr(zipcode, "-") + 1)
    End If
    'TEL(下位)
'    If Len(mastersheet.Range("BW" & rowid).Value) > 0 Then
'    telArray = Split(mastersheet.Range("BW" & rowid).Value, "-")
'    contactSheet.Range("T" & 7).Value = telArray(0)
'    contactSheet.Range("Y" & 7).Value = telArray(1)
'    contactSheet.Range("AD" & 7).Value = telArray(2)
'    End If
    If Len(mastersheet.Range("O" & rowid).Value) > 0 Then
        telArray = Split(mastersheet.Range("O" & rowid).Value, "-")
        Dim itemNum As Integer
        itemNum = UBound(telArray) - LBound(telArray) + 1
        If itemNum = 3 Then
            contactSheet.Range("T" & 7).Value = telArray(0)
            contactSheet.Range("Y" & 7).Value = telArray(1)
            contactSheet.Range("AD" & 7).Value = telArray(2)
        Else
            contactSheet.Range("T" & 7).Value = mastersheet.Range("O" & rowid).Value
            
        End If
    End If
    '住所(下位)
    contactSheet.Range("H" & 8).Value = mastersheet.Range("BU" & rowid).Value
    '　通勤費（1ヶ月）
    contactSheet.Range("H" & 16).Value = mastersheet.Range("M" & rowid).Value
    '給与（1ヶ月）  本人渡し
    contactSheet.Range("H" & 14).Value = mastersheet.Range("J" & rowid).Value
End Sub

'○1_【TSRS】採用通知書
Public Sub noticeContract(ByVal book, ByVal sheet, ByVal rowid)
 
    Dim startDate, testdate
    Set noticeSheet = Application.Workbooks(book).Worksheets(sheet)
    noticeSheet.Range("A" & 3).Value = mastersheet.Range("B" & rowid).Value & "   様"
    
End Sub

'○5_【TSRS】機密保持に関する誓約書（入社時提出）
Public Sub securityContract(ByVal book, ByVal sheet, ByVal rowid)
 
    Dim startDate, testdate
    Set securitySheet = Application.Workbooks(book).Worksheets(sheet)
    securitySheet.Range("C" & 10).Value = mastersheet.Range("B" & rowid).Value
    
End Sub

'○3_【TSRS】労働条件通知書_氏名
Public Sub workCondition(ByVal book, ByVal sheet, ByVal rowid)
 
    Dim startDate, testdate
    Set conditionSheet = Application.Workbooks(book).Worksheets(sheet)
    '契約者氏名
    conditionSheet.Range("A" & 4).Value = mastersheet.Range("B" & rowid).Value
    conditionSheet.Range("A" & 9).Value = mastersheet.Range("B" & rowid).Value
    
    '入社日
    If Len(mastersheet.Range("U" & rowid).Value) > 0 Then
        conditionSheet.Range("Y" & 3).Value = Format(mastersheet.Range("U" & rowid).Value, "ggge年ooood日")
    End If
    '始業
    conditionSheet.Range("O" & 18).Value = mastersheet.Range("AA" & rowid).Value
    '終業
    conditionSheet.Range("V" & 18).Value = mastersheet.Range("AB" & rowid).Value
    '休憩時間
     conditionSheet.Range("Q" & 27).Value = mastersheet.Range("CE" & rowid).Value
    
    '基本賃金
     conditionSheet.Range("S" & 40).Value = mastersheet.Range("CF" & rowid).Value
    
    '現場手当
     conditionSheet.Range("S" & 42).Value = mastersheet.Range("DA" & rowid).Value
    '住宅手当
     conditionSheet.Range("S" & 43).Value = mastersheet.Range("CG" & rowid).Value
    '残業手当
     conditionSheet.Range("S" & 44).Value = mastersheet.Range("CJ" & rowid).Value
    '通勤手当
     If mastersheet.Range("M" & rowid).Value > 20000 Then
        conditionSheet.Range("S" & 45).Value = 20000
     Else
        conditionSheet.Range("S" & 45).Value = mastersheet.Range("M" & rowid).Value
     End If
     
    'その他手当
    addTot = 0
    '通信手当
     addTot = mastersheet.Range("CH" & rowid).Value
    '資格手当
     addTot = addTot + mastersheet.Range("CI" & rowid).Value
    '役職手当
     addTot = addTot + mastersheet.Range("CY" & rowid).Value
    '扶養手当
     addTot = addTot + mastersheet.Range("CZ" & rowid).Value
    '当番手当
     addTot = addTot + mastersheet.Range("DB" & rowid).Value
    'その他手当２
     addTot = addTot + mastersheet.Range("DC" & rowid).Value
     '合計額セット
     conditionSheet.Range("S" & 46).Value = addTot
    
    '賃金締切日
    If mastersheet.Range("CK" & rowid).Value = "月末" Then
        conditionSheet.Range("T" & 47).Value = "末日"
    Else
        conditionSheet.Range("T" & 47).Value = mastersheet.Range("CK" & rowid).Value
    End If
    '賃金支払日
     conditionSheet.Range("T" & 48).Value = mastersheet.Range("CL" & rowid).Value & "日"
    
End Sub

'○1_【TSRS-変更】労働条件通知書_氏名
Public Sub changeWorkCondition(ByVal book, ByVal sheet, ByVal rowid)
 
    Dim startDate, testdate
    Set conditionSheet = Application.Workbooks(book).Worksheets(sheet)
    '契約者氏名
    conditionSheet.Range("A" & 4).Value = mastersheet.Range("B" & rowid).Value
    conditionSheet.Range("A" & 9).Value = mastersheet.Range("B" & rowid).Value
    
    '入社日
    If Len(mastersheet.Range("U" & rowid).Value) > 0 Then
        conditionSheet.Range("Y" & 3).Value = Format(mastersheet.Range("U" & rowid).Value, "ggge年ooood日")
    End If
    '始業
    conditionSheet.Range("O" & 18).Value = mastersheet.Range("AA" & rowid).Value
    '終業
    conditionSheet.Range("V" & 18).Value = mastersheet.Range("AB" & rowid).Value
    
    '休憩時間
     conditionSheet.Range("P" & 27).Value = mastersheet.Range("CE" & rowid).Value
    '基本賃金
     conditionSheet.Range("S" & 39).Value = mastersheet.Range("CF" & rowid).Value
    '住宅手当
     conditionSheet.Range("S" & 41).Value = mastersheet.Range("CG" & rowid).Value
    '通勤手当
        
     If mastersheet.Range("M" & rowid).Value > 20000 Then
        conditionSheet.Range("S" & 42).Value = 20000
     Else
        conditionSheet.Range("S" & 42).Value = mastersheet.Range("M" & rowid).Value
     End If
     
    '通信手当
     conditionSheet.Range("S" & 43).Value = mastersheet.Range("CH" & rowid).Value
    '資格手当
     conditionSheet.Range("S" & 44).Value = mastersheet.Range("CI" & rowid).Value
    '残業手当
     conditionSheet.Range("S" & 45).Value = mastersheet.Range("CJ" & rowid).Value
    '賃金締切日
     conditionSheet.Range("T" & 46).Value = mastersheet.Range("CK" & rowid).Value
    '賃金支払日
     conditionSheet.Range("T" & 47).Value = mastersheet.Range("CL" & rowid).Value
    
End Sub
'○8_【ＴＳＲＳ】個人情報申請書-新規 / ○8_契約【ＴＳＲＳ】個人情報申請書-新規
Public Sub prsnlInfoContract(ByVal book, ByVal sheet, ByVal rowid)
    
    Dim startDate, testdate
    Set personalSheet = Application.Workbooks(book).Worksheets(sheet)
    
    '帳票カテゴリ
    '社員番号
    tempEmpNum = mastersheet.Range("A" & rowid).Value
    personalSheet.Range("I" & 4).Value = Left(tempEmpNum, 1)
    personalSheet.Range("J" & 4).Value = Mid(tempEmpNum, 2, 1)
    personalSheet.Range("K" & 4).Value = Mid(tempEmpNum, 3, 1)
    personalSheet.Range("L" & 4).Value = Right(tempEmpNum, 1)
    '所属会社名
    personalSheet.Range("E5").Value = mastersheet.Range("D" & rowid).Value
    '社員名
    personalSheet.Range("E6").Value = mastersheet.Range("B" & rowid).Value
    '社員名
    personalSheet.Range("E6").Value = mastersheet.Range("B" & rowid).Value
    '起票日
    personalSheet.Range("E7").Value = Format(Date, "yyyy""年""m""月""d""日""")
    
    '氏名情報欄
    'フリガナ
    personalSheet.Range("L9").Value = mastersheet.Range("CD" & rowid).Value
    '社員名
    personalSheet.Range("L10").Value = mastersheet.Range("B" & rowid).Value
    '性別
    personalSheet.Range("U10").Value = mastersheet.Range("CB" & rowid).Value
    '生年月日
    If mastersheet.Range("CC" & rowid).Value <> "" Then
        personalSheet.Range("W10").Value = Year(mastersheet.Range("CC" & rowid).Value)
        personalSheet.Range("AB10").Value = month(mastersheet.Range("CC" & rowid).Value)
        personalSheet.Range("AE10").Value = Day(mastersheet.Range("CC" & rowid).Value)
    End If

    '住所情報欄
    '〒郵便番号 (下位)
    personalSheet.Range("M12").Value = mastersheet.Range("BV" & rowid).Value
    '住所 (下位)
    personalSheet.Range("L13").Value = mastersheet.Range("BU" & rowid).Value

    '電話番号情報欄
    'TEL (下位)取得
    tempTelNum = mastersheet.Range("BW" & rowid).Value
    If Mid(tempTelNum, 3, 1) = 0 Then
    Else
        'TEL (下位)
        personalSheet.Range("V15").Value = Right(tempTelNum, 4)
        If Left(tempTelNum, 2) = "03" Or Left(tempTelNum, 2) = "06" Then
            personalSheet.Range("P15").Value = Left(tempTelNum, 2)
            If InStr(tempTelNum, "-") > 0 Then
                personalSheet.Range("R15").Value = Mid(tempTelNum, InStr(tempTelNum, "-") + 1, 4)
            Else
                personalSheet.Range("R15").Value = Mid(tempTelNum, 3, 4)
            End If
        Else
            personalSheet.Range("P15").Value = Left(tempTelNum, 3)
            If InStr(tempTelNum, "-") > 0 Then
                personalSheet.Range("R15").Value = Mid(tempTelNum, InStr(tempTelNum, "-") + 1, 3)
            Else
                personalSheet.Range("R15").Value = Mid(tempTelNum, 4, 3)
            End If
        End If
        '携帯番号取得
        tempTelNum = mastersheet.Range("O" & rowid).Value
    End If
    '携帯番号
    personalSheet.Range("V17").Value = Right(tempTelNum, 4)
    personalSheet.Range("P17").Value = Left(tempTelNum, 3)
    If InStr(tempTelNum, "-") > 0 Then
        personalSheet.Range("R17").Value = Mid(tempTelNum, InStr(tempTelNum, "-") + 1, 4)
    Else
        personalSheet.Range("R17").Value = Mid(tempTelNum, 4, 4)
    End If
    
    'メールアドレス情報欄
    'E -mail(自宅)
    tempMAdrr = mastersheet.Range("R" & rowid).Value
    sptMrkPos = InStr(tempMAdrr, "@")
    personalSheet.Range("N19").Value = Left(tempMAdrr, sptMrkPos - 1)
    personalSheet.Range("X19").Value = Mid(tempMAdrr, sptMrkPos + 1)

    '自宅最寄駅
    personalSheet.Range("L25").Value = mastersheet.Range("CM" & rowid).Value
    personalSheet.Range("Q25").Value = mastersheet.Range("CN" & rowid).Value

    '通勤交通費控除額
    '自宅最寄駅の駅～現場最寄駅（線／駅／交通費①
    personalSheet.Range("D33").Value = mastersheet.Range("CM" & rowid).Value
    personalSheet.Range("J33").Value = mastersheet.Range("CN" & rowid).Value
    personalSheet.Range("S33").Value = mastersheet.Range("CO" & rowid).Value
    personalSheet.Range("X33").Value = mastersheet.Range("CP" & rowid).Value
    personalSheet.Range("AC33").Value = mastersheet.Range("CQ" & rowid).Value
    '自宅最寄駅の駅～現場最寄駅（線／駅／交通費②
    personalSheet.Range("D35").Value = mastersheet.Range("CR" & rowid).Value
    personalSheet.Range("J35").Value = mastersheet.Range("CS" & rowid).Value
    personalSheet.Range("S35").Value = mastersheet.Range("CT" & rowid).Value
    personalSheet.Range("X35").Value = mastersheet.Range("CU" & rowid).Value
    personalSheet.Range("AC35").Value = mastersheet.Range("CV" & rowid).Value
    '自宅最寄駅の駅～現場最寄駅（線／駅／交通費③
    personalSheet.Range("D37").Value = mastersheet.Range("DJ" & rowid).Value
    personalSheet.Range("J37").Value = mastersheet.Range("DK" & rowid).Value
    personalSheet.Range("S37").Value = mastersheet.Range("DL" & rowid).Value
    personalSheet.Range("X37").Value = mastersheet.Range("DM" & rowid).Value
    personalSheet.Range("AC37").Value = mastersheet.Range("DN" & rowid).Value
    '自宅最寄駅の駅～現場最寄駅（線／駅／交通費④
    personalSheet.Range("D39").Value = mastersheet.Range("DO" & rowid).Value
    personalSheet.Range("J39").Value = mastersheet.Range("DP" & rowid).Value
    personalSheet.Range("S39").Value = mastersheet.Range("DQ" & rowid).Value
    personalSheet.Range("X39").Value = mastersheet.Range("DR" & rowid).Value
    personalSheet.Range("AC39").Value = mastersheet.Range("DS" & rowid).Value
    '１ヶ月交通費
    personalSheet.Range("S44").Value = mastersheet.Range("M" & rowid).Value
    
    'フラグセット
    '氏名
    personalSheet.Range("B9").Value = prsnlInfChkFlg
    '住所
    personalSheet.Range("B12").Value = prsnlInfChkFlg
    '電話番号
    personalSheet.Range("B15").Value = prsnlInfChkFlg
    'メールアドレス
    personalSheet.Range("B19").Value = prsnlInfChkFlg
    '最寄駅
    personalSheet.Range("B25").Value = prsnlInfChkFlg
    '通勤交通費控除額
    personalSheet.Range("B27").Value = prsnlInfChkFlg
        
    '書式指定
    personalSheet.Range("AC33").NumberFormatLocal = accntDispForm
    personalSheet.Range("AC35").NumberFormatLocal = accntDispForm
    personalSheet.Range("AC37").NumberFormatLocal = accntDispForm
    personalSheet.Range("AC39").NumberFormatLocal = accntDispForm
    
    '正社員用、契約社員用、個別対応項目部分のセット
    Select Case book
    Case prsnlInfPrprNewBk
    Case prsnlInfCntrNewBk
    End Select

End Sub

'○2_【ＴＳＲＳ-変更】個人情報申請書
Public Sub changePersonInfo(ByVal book, ByVal sheet, ByVal rowid)
  
    Dim startDate, testdate
    Set conditionSheet = Application.Workbooks(book).Worksheets(sheet)
    
    '帳票カテゴリ
    '社員番号
    tempEmpNum = mastersheet.Range("A" & rowid).Value
    conditionSheet.Range("I" & 4).Value = Left(tempEmpNum, 1)
    conditionSheet.Range("J" & 4).Value = Mid(tempEmpNum, 2, 1)
    conditionSheet.Range("K" & 4).Value = Mid(tempEmpNum, 3, 1)
    conditionSheet.Range("L" & 4).Value = Right(tempEmpNum, 1)
    '所属会社名
    conditionSheet.Range("E5").Value = mastersheet.Range("D" & rowid).Value
    '社員名
    conditionSheet.Range("E6").Value = mastersheet.Range("B" & rowid).Value
    
    'フリガナ表題
'    personalSheet.Range("L9").Value = "(ﾌﾘｶﾞﾅ)"
'    personalSheet.Range("L9").HorizontalAlignment = xlLeft
    
'    '改名(変更後戸籍名)
'    conditionSheet.Range("L" & 10).Value = mastersheet.Range("CD" & rowid).Value

'    '変更後の通勤経路
'    conditionSheet.Range("D" & 42).Value = mastersheet.Range("CM" & rowid).Value
'    conditionSheet.Range("J" & 42).Value = mastersheet.Range("CN" & rowid).Value
'    conditionSheet.Range("S" & 42).Value = mastersheet.Range("CO" & rowid).Value
'    conditionSheet.Range("X" & 42).Value = mastersheet.Range("CP" & rowid).Value
'    '通勤経路①の交通費
'    conditionSheet.Range("AC" & 42).Value = mastersheet.Range("CQ" & rowid).Value
    
'    conditionSheet.Range("D" & 44).Value = mastersheet.Range("CR" & rowid).Value
'    conditionSheet.Range("J" & 44).Value = mastersheet.Range("CS" & rowid).Value
'    conditionSheet.Range("S" & 44).Value = mastersheet.Range("CT" & rowid).Value
'    conditionSheet.Range("X" & 44).Value = mastersheet.Range("CU" & rowid).Value
'    '通勤経路②の交通費
'    conditionSheet.Range("AC" & 44).Value = mastersheet.Range("CV" & rowid).Value
    
End Sub


'○【社労士様】退社連絡票 正社員_内勤

Public Sub resignationConnect(ByVal book, ByVal sheet, ByVal rowid)
    
    Dim startDate, testdate
    Set resignationSheet = Application.Workbooks(book).Worksheets(sheet)
    '会社名
    resignationSheet.Range("U" & 1).Value = "ＴＳＲソリューションズ株式会社"
    '社員Ｎｏ
    resignationSheet.Range("H" & 3).Value = mastersheet.Range("A" & rowid).Value
    'フリガナ
     resignationSheet.Range("P" & 3).Value = mastersheet.Range("CD" & rowid).Value
    '氏名
    resignationSheet.Range("P" & 4).Value = mastersheet.Range("B" & rowid).Value
    '性別
    resignationSheet.Range("AI" & 4).Value = mastersheet.Range("CB" & rowid).Value
    '生年月日
    birthDay = mastersheet.Range("CC" & rowid).Value
    Dim dateFmtStr, dateArray
    If Len(birthDay) > 0 Then
        birdayFmt = Format(birthDay, "ggge年ooood日")
        
        dateFmtStr = Replace(Mid(birdayFmt, 3), "年", "-")
        dateFmtStr = Replace(dateFmtStr, "月", "-")
        dateFmtStr = Replace(dateFmtStr, "日", "")
        
        dateArray = Split(dateFmtStr, "-")
        resignationSheet.Range("AD" & 5).Value = dateArray(0)
        resignationSheet.Range("AG" & 5).Value = dateArray(1)
        resignationSheet.Range("AJ" & 5).Value = dateArray(2)

        '昭 ・ 平
        If InStr(birdayFmt, "平成") > 0 Then
            '年号が平成の場合、"平"を指定する
            resignationSheet.Range("Z5").Characters(5, 2).Font.Color = vbRed
            resignationSheet.Range("Z5").Characters(5, 2).Font.Bold = True
        ElseIf InStr(birdayFmt, "昭和") > 0 Then
           '年号が昭和の場合、"昭"を指定する
           resignationSheet.Range("Z5").Characters(1, 2).Font.Color = vbRed
           resignationSheet.Range("Z5").Characters(1, 2).Font.Bold = True
        End If
    End If
    '〒
    'If mastersheet.Range("DV" & rowid).Value <> "" Then
        'zipcode = mastersheet.Range("DV" & rowid).Value
    'Else
        zipcode = mastersheet.Range("BV" & rowid).Value
    'End If
    If Len(zipcode) > 0 Then
'    resignationSheet.Range("I" & 7).Value = Mid(zipcode, 3, (InStr(zipcode, "-") - 3))
'    resignationSheet.Range("M" & 7).Value = Mid(zipcode, InStr(zipcode, "-") + 1)
    
        zipcode = Replace(zipcode, "〒", "")
        Dim zipArray
        zipArray = Split(zipcode, "-")
        resignationSheet.Range("I" & 7).Value = zipArray(0)
        If UBound(zipArray) > 0 Then
            resignationSheet.Range("M" & 7).Value = zipArray(1)
        End If
    End If
    'TEL(下位)
    If Len(mastersheet.Range("O" & rowid).Value) > 0 Then
        telArray = Split(mastersheet.Range("O" & rowid).Value, "-")
        Dim itemNum As Integer
        itemNum = UBound(telArray) - LBound(telArray) + 1
        If itemNum = 3 Then
            resignationSheet.Range("T" & 7).Value = telArray(0)
            resignationSheet.Range("Y" & 7).Value = telArray(1)
            resignationSheet.Range("AD" & 7).Value = telArray(2)
        Else
            resignationSheet.Range("T" & 7).Value = mastersheet.Range("O" & rowid).Value
            
        End If
    End If
    '住所(下位)
    If mastersheet.Range("DW" & rowid).Value <> "" Then
        resignationSheet.Range("H" & 8).Value = mastersheet.Range("DW" & rowid).Value
    Else
        resignationSheet.Range("H" & 8).Value = mastersheet.Range("BU" & rowid).Value
    End If
    
    '退職日
    retireDay = mastersheet.Range("DV" & rowid).Value
    If Len(retireDay) > 0 Then
        birdayFmt = Format(retireDay, "ggge年ooood日")
        
        dateFmtStr = Replace(Mid(birdayFmt, 3), "年", "-")
        dateFmtStr = Replace(dateFmtStr, "月", "-")
        dateFmtStr = Replace(dateFmtStr, "日", "")
        
        dateArray = Split(dateFmtStr, "-")
        resignationSheet.Range("J" & 5).Value = dateArray(0)
        resignationSheet.Range("M" & 5).Value = dateArray(1)
        resignationSheet.Range("P" & 5).Value = dateArray(2)
    End If
    
End Sub

'入社連絡票 契約社員_外勤
Public Sub contactContractDispatch(ByVal book, ByVal sheet, ByVal rowid)

   Dim contactSheet, monPosition, dayPosition, zipcode As String, telArray, joinDay
    Set contactSheet = Application.Workbooks(book).Worksheets(sheet)
    '社員Ｎｏ
    contactSheet.Range("H" & 3).Value = mastersheet.Range("A" & rowid).Value
    'フリガナ
     contactSheet.Range("P" & 3).Value = mastersheet.Range("CD" & rowid).Value
    '氏名
    contactSheet.Range("P" & 4).Value = mastersheet.Range("B" & rowid).Value
    '性別
    contactSheet.Range("AI" & 4).Value = mastersheet.Range("CB" & rowid).Value

    joinDay = mastersheet.Range("U" & rowid).Value
    
    Dim dateFmtStr, dateArray
    If Len(joinDay) > 0 Then
    StrFmt = Format(joinDay, "ggge年ooood日")

    '入社年月日

    dateFmtStr = Replace(Mid(StrFmt, 3), "年", "-")
    dateFmtStr = Replace(dateFmtStr, "月", "-")
    dateFmtStr = Replace(dateFmtStr, "日", "")
    
    dateArray = Split(dateFmtStr, "-")
    contactSheet.Range("J" & 5).Value = dateArray(0)
    contactSheet.Range("M" & 5).Value = dateArray(1)
    contactSheet.Range("P" & 5).Value = dateArray(2)
    
    
    End If
    '生年月日
    birthDay = mastersheet.Range("CC" & rowid).Value
    If Len(birthDay) > 0 Then
    birdayFmt = Format(birthDay, "ggge年ooood日")

    dateFmtStr = Replace(Mid(birdayFmt, 3), "年", "-")
    dateFmtStr = Replace(dateFmtStr, "月", "-")
    dateFmtStr = Replace(dateFmtStr, "日", "")

    dateArray = Split(dateFmtStr, "-")
    contactSheet.Range("AD" & 5).Value = dateArray(0)
    contactSheet.Range("AG" & 5).Value = dateArray(1)
    contactSheet.Range("AJ" & 5).Value = dateArray(2)
    
    '昭 ・ 平
        If InStr(birdayFmt, "平成") > 0 Then
            '年号が平成の場合、"平"を指定する
            contactSheet.Range("Z5").Characters(5, 2).Font.Color = vbRed
            contactSheet.Range("Z5").Characters(5, 2).Font.Bold = True
        ElseIf InStr(birdayFmt, "昭和") > 0 Then
           '年号が昭和の場合、"昭"を指定する
           contactSheet.Range("Z5").Characters(1, 2).Font.Color = vbRed
           contactSheet.Range("Z5").Characters(1, 2).Font.Bold = True
        End If
    
    End If
    'フリガナ
    'contactSheet.Range("H" & 6).Value = mastersheet.Range("BT" & rowid).Value
    '〒
    zipcode = mastersheet.Range("BV" & rowid).Value
    If Len(zipcode) > 0 Then
'    contactSheet.Range("I" & 7).Value = Mid(zipcode, 3, (InStr(zipcode, "-") - 3))
'    contactSheet.Range("M" & 7).Value = Mid(zipcode, InStr(zipcode, "-") + 1)

        zipcode = Replace(zipcode, "〒", "")
        Dim zipArray
        zipArray = Split(zipcode, "-")
        contactSheet.Range("I" & 7).Value = zipArray(0)
        If UBound(zipArray) > 0 Then
            contactSheet.Range("M" & 7).Value = zipArray(1)
        End If
    
    End If
    'TEL(下位)
'    If Len(mastersheet.Range("BW" & rowid).Value) > 0 Then
'    telArray = Split(mastersheet.Range("BW" & rowid).Value, "-")
'    contactSheet.Range("T" & 7).Value = telArray(0)
'    contactSheet.Range("Y" & 7).Value = telArray(1)
'    contactSheet.Range("AD" & 7).Value = telArray(2)
'    End If
    
    If Len(mastersheet.Range("O" & rowid).Value) > 0 Then
        telArray = Split(mastersheet.Range("O" & rowid).Value, "-")
        Dim itemNum As Integer
        itemNum = UBound(telArray) - LBound(telArray) + 1
        If itemNum = 3 Then
            contactSheet.Range("T" & 7).Value = telArray(0)
            contactSheet.Range("Y" & 7).Value = telArray(1)
            contactSheet.Range("AD" & 7).Value = telArray(2)
        Else
            contactSheet.Range("T" & 7).Value = mastersheet.Range("O" & rowid).Value
            
        End If
    End If
    '住所(下位)
    contactSheet.Range("H" & 8).Value = mastersheet.Range("BU" & rowid).Value
    '　通勤費（1ヶ月）
    If mastersheet.Range("M" & rowid).Value > 20000 Then
        contactSheet.Range("H" & 16).Value = 20000
    Else
        contactSheet.Range("H" & 16).Value = mastersheet.Range("M" & rowid).Value
    End If
    '給与（1ヶ月）  本人渡し
    contactSheet.Range("H" & 14).Value = mastersheet.Range("J" & rowid).Value
End Sub

'○1_【TSRS】採用通知書 契約社員_外勤
Public Sub noticeContractDispatch(ByVal book, ByVal sheet, ByVal rowid)
 
    Dim startDate, testdate
    Set noticeSheet = Application.Workbooks(book).Worksheets(sheet)
    noticeSheet.Range("A" & 3).Value = mastersheet.Range("B" & rowid).Value & "   様"
    
End Sub

'○5_契約【TSRS】機密保持に関する誓約書（入社時提出）
Public Sub securityContractDispatch(ByVal book, ByVal sheet, ByVal rowid)
 
    Dim startDate, testdate
    Set securitySheet = Application.Workbooks(book).Worksheets(sheet)
    securitySheet.Range("C" & 8).Value = mastersheet.Range("B" & rowid).Value
    
End Sub

'○3_契約【TSRS-変更】労働条件通知書_氏名 契約社員_外勤
Public Sub workConditionDispatch(ByVal book, ByVal sheet, ByVal rowid)
    
    Dim startDate, testdate
    Set conditionSheet = Application.Workbooks(book).Worksheets(sheet)
    '契約者氏名
    conditionSheet.Range("A" & 4).Value = mastersheet.Range("B" & rowid).Value
    conditionSheet.Range("A" & 9).Value = mastersheet.Range("B" & rowid).Value
    
    '入社日
    If Len(mastersheet.Range("BI" & rowid).Value) > 0 Then
        conditionSheet.Range("V" & 3).Value = Format(mastersheet.Range("BI" & rowid).Value, "ggge年ooood日")
    End If
    '始業
    conditionSheet.Range("J" & 15).Value = mastersheet.Range("AA" & rowid).Value
    '終業
    conditionSheet.Range("R" & 15).Value = mastersheet.Range("AB" & rowid).Value
    '休憩時間
    conditionSheet.Range("K" & 17).Value = mastersheet.Range("CE" & rowid).Value
    
    '契約期間
    conditionSheet.Range("F" & 10).Value = Format(mastersheet.Range("P" & rowid).Value, "ggge年ooood日")
    conditionSheet.Range("P" & 10).Value = Format(mastersheet.Range("Q" & rowid).Value, "ggge年ooood日")
    
    '作業場所
    setTemp = "客先（" & mastersheet.Range("AG" & rowid).Value & "）"
    conditionSheet.Range("F" & 11).Value = setTemp
    '職務の内容
    conditionSheet.Range("F" & 12).Value = mastersheet.Range("AF" & rowid).Value
    
    '完全月給制
    conditionSheet.Range("P" & 20).Value = mastersheet.Range("J" & rowid).Value
    '基本給
    conditionSheet.Range("P" & 22).Value = mastersheet.Range("CF" & rowid).Value
    '諸手当（現場手当）
    conditionSheet.Range("P" & 23).Value = mastersheet.Range("DA" & rowid).Value
    '諸手当（残業手当）
    conditionSheet.Range("P" & 24).Value = mastersheet.Range("CJ" & rowid).Value
    '諸手当（通勤手当）
     If mastersheet.Range("M" & rowid).Value > 20000 Then
        conditionSheet.Range("P" & 25).Value = 20000
     Else
        conditionSheet.Range("P" & 25).Value = mastersheet.Range("M" & rowid).Value
     End If
     
    'その他手当
    addTot = 0
    '住宅手当
     addTot = mastersheet.Range("CG" & rowid).Value
    '通信手当
     addTot = mastersheet.Range("CH" & rowid).Value
    '資格手当
     addTot = addTot + mastersheet.Range("CI" & rowid).Value
    '役職手当
     addTot = addTot + mastersheet.Range("CY" & rowid).Value
    '扶養手当
     addTot = addTot + mastersheet.Range("CZ" & rowid).Value
    '当番手当
     addTot = addTot + mastersheet.Range("DB" & rowid).Value
    'その他手当２
     addTot = addTot + mastersheet.Range("DC" & rowid).Value
     '合計額セット
     conditionSheet.Range("P" & 26).Value = addTot
    
    '清算下限時間
    conditionSheet.Range("J" & 27).Value = mastersheet.Range("DE" & rowid).Value
    conditionSheet.Range("L" & 28).Value = mastersheet.Range("DE" & rowid).Value
    '清算上限時間
    conditionSheet.Range("O" & 27).Value = mastersheet.Range("DD" & rowid).Value
    conditionSheet.Range("L" & 29).Value = mastersheet.Range("DD" & rowid).Value
    '控除単価
    conditionSheet.Range("R" & 28).Value = mastersheet.Range("DG" & rowid).Value
    '超過単価
    conditionSheet.Range("R" & 29).Value = mastersheet.Range("DF" & rowid).Value
    '調整単位
    conditionSheet.Range("M" & 30).Value = mastersheet.Range("DH" & rowid).Value
    
    '賃金締切日
    If mastersheet.Range("CK" & rowid).Value = "月末" Then
        setTemp = "末日"
    Else
        setTemp = mastersheet.Range("CK" & rowid).Value
    End If
    conditionSheet.Range("K" & 32).Value = "毎月" & setTemp
    '賃金支払日
    setTemp = mastersheet.Range("S" & rowid).Value
    setTemp = setTemp & mastersheet.Range("CL" & rowid).Value & "日"
    conditionSheet.Range("T" & 32).Value = setTemp
    
    '加入保険
    Select Case mastersheet.Range("DU" & rowid).Value
    Case "無"
        conditionSheet.Range("F" & 34).Value = "雇用保険加入"
    Case "有"
        conditionSheet.Range("F" & 34).Value = "社会保険加入"
    Case Else
        conditionSheet.Range("F" & 34).Value = ""
    End Select
    
End Sub

'○1_【TSRS-変更】労働条件通知書_氏名  契約社員_外勤
Public Sub changeWorkConditionDispatch(ByVal book, ByVal sheet, ByVal rowid)
    
    Dim startDate, testdate
    Set conditionSheet = Application.Workbooks(book).Worksheets(sheet)
    '契約者氏名
    conditionSheet.Range("A" & 4).Value = mastersheet.Range("B" & rowid).Value
    
    '*******************'帳票バグ対応修正 2016年06月02日 WuYang Start*******************************
    'conditionSheet.Range("A" & 10).Value = mastersheet.Range("B" & rowid).Value
    
    '入社日
    'If Len(mastersheet.Range("U" & rowid).Value) > 0 Then
        'conditionSheet.Range("V" & 3).Value = Format(mastersheet.Range("U" & rowid).Value, "ggge年ooood日")
    'End If
    '始業
    'conditionSheet.Range("J" & 18).Value = mastersheet.Range("AA" & rowid).Value
    '終業
    'conditionSheet.Range("R" & 18).Value = mastersheet.Range("AB" & rowid).Value
    
    '休憩時間
    'conditionSheet.Range("K" & 21).Value = mastersheet.Range("CE" & rowid).Value
    '契約期間
    'conditionSheet.Range("F" & 11).Value = Format(mastersheet.Range("P" & rowid).Value, "ggge年ooood日")
    'conditionSheet.Range("P" & 11).Value = Format(mastersheet.Range("Q" & rowid).Value, "ggge年ooood日")
    '作業場所
    'conditionSheet.Range("G" & 13).Value = mastersheet.Range("AG" & rowid).Value
    '職務の内容
    'conditionSheet.Range("F" & 14).Value = mastersheet.Range("AF" & rowid).Value
    
    '完全月給制
    'conditionSheet.Range("P" & 24).Value = mastersheet.Range("J" & rowid).Value
    
    '賃金締切日
    'conditionSheet.Range("K" & 30).Value = mastersheet.Range("CK" & rowid).Value
    '賃金支払日
    'conditionSheet.Range("T" & 30).Value = mastersheet.Range("CL" & rowid).Value
    
    conditionSheet.Range("A" & 9).Value = mastersheet.Range("B" & rowid).Value
    
    '入社日
    If Len(mastersheet.Range("P" & rowid).Value) > 0 Then
        e_data1 = CDate(mastersheet.Range("P" & rowid).Value)
        e_data2 = DateAdd("m", -1, e_data1)
        e_data3 = DateAdd("d", -1, e_data2)
        conditionSheet.Range("V" & 3).Value = Format(e_data3, "ggge年ooood日")
    End If
    '始業
    conditionSheet.Range("J" & 15).Value = mastersheet.Range("AA" & rowid).Value
    '終業
    conditionSheet.Range("R" & 15).Value = mastersheet.Range("AB" & rowid).Value
    '休憩時間
    conditionSheet.Range("K" & 17).Value = mastersheet.Range("CE" & rowid).Value
    
    '契約期間
    conditionSheet.Range("F" & 10).Value = Format(mastersheet.Range("P" & rowid).Value, "ggge年ooood日")
    conditionSheet.Range("P" & 10).Value = Format(mastersheet.Range("Q" & rowid).Value, "ggge年ooood日")
    
    '作業場所
    setTemp = "客先（" & mastersheet.Range("AG" & rowid).Value & "）"
    conditionSheet.Range("F" & 11).Value = setTemp
    '職務の内容
    conditionSheet.Range("F" & 12).Value = mastersheet.Range("AF" & rowid).Value
    
    '完全月給制
    conditionSheet.Range("P" & 20).Value = mastersheet.Range("J" & rowid).Value
    '基本給
    conditionSheet.Range("P" & 22).Value = mastersheet.Range("CF" & rowid).Value
    '諸手当（現場手当）
    conditionSheet.Range("P" & 23).Value = mastersheet.Range("DA" & rowid).Value
    '諸手当（残業手当）
    conditionSheet.Range("P" & 24).Value = mastersheet.Range("CJ" & rowid).Value
    '諸手当（通勤手当）
     If mastersheet.Range("M" & rowid).Value > 20000 Then
        conditionSheet.Range("P" & 25).Value = 20000
     Else
        conditionSheet.Range("P" & 25).Value = mastersheet.Range("M" & rowid).Value
     End If
     
    'その他手当
    addTot = 0
    '住宅手当
     addTot = mastersheet.Range("CG" & rowid).Value
    '通信手当
     addTot = mastersheet.Range("CH" & rowid).Value
    '資格手当
     addTot = addTot + mastersheet.Range("CI" & rowid).Value
    '役職手当
     addTot = addTot + mastersheet.Range("CY" & rowid).Value
    '扶養手当
     addTot = addTot + mastersheet.Range("CZ" & rowid).Value
    '当番手当
     addTot = addTot + mastersheet.Range("DB" & rowid).Value
    'その他手当２
     addTot = addTot + mastersheet.Range("DC" & rowid).Value
     '合計額セット
     conditionSheet.Range("P" & 26).Value = addTot
    
    '清算下限時間
    conditionSheet.Range("J" & 27).Value = mastersheet.Range("DE" & rowid).Value
    conditionSheet.Range("L" & 28).Value = mastersheet.Range("DE" & rowid).Value
    '清算上限時間
    conditionSheet.Range("O" & 27).Value = mastersheet.Range("DD" & rowid).Value
    conditionSheet.Range("L" & 29).Value = mastersheet.Range("DD" & rowid).Value
    '控除単価
    conditionSheet.Range("R" & 28).Value = mastersheet.Range("DG" & rowid).Value
    '超過単価
    conditionSheet.Range("R" & 29).Value = mastersheet.Range("DF" & rowid).Value
    '調整単位
    conditionSheet.Range("M" & 30).Value = mastersheet.Range("DH" & rowid).Value
    
    '賃金締切日
    If mastersheet.Range("CK" & rowid).Value = "月末" Then
        setTemp = "末日"
    Else
        setTemp = mastersheet.Range("CK" & rowid).Value
    End If
    conditionSheet.Range("K" & 32).Value = "毎月" & setTemp
    '賃金支払日
    setTemp = mastersheet.Range("S" & rowid).Value
    setTemp = setTemp & mastersheet.Range("CL" & rowid).Value & "日"
    conditionSheet.Range("T" & 32).Value = setTemp
    
    '加入保険
    Select Case mastersheet.Range("DU" & rowid).Value
    Case "無"
        conditionSheet.Range("F" & 34).Value = "雇用保険加入"
    Case "有"
        conditionSheet.Range("F" & 34).Value = "社会保険加入"
    Case Else
        conditionSheet.Range("F" & 34).Value = ""
    End Select
    
    '*******************'帳票バグ対応修正 2016年06月02日 WuYang End*******************************
End Sub

'○2_【ＴＳＲＳ-変更】個人情報申請書 契約社員_外勤
Public Sub changePersonInfoDispatch(ByVal book, ByVal sheet, ByVal rowid)

    Dim startDate, testdate
    Set conditionSheet = Application.Workbooks(book).Worksheets(sheet)
    
    '帳票カテゴリ
    '社員番号
    tempEmpNum = mastersheet.Range("A" & rowid).Value
    conditionSheet.Range("I" & 4).Value = Left(tempEmpNum, 1)
    conditionSheet.Range("J" & 4).Value = Mid(tempEmpNum, 2, 1)
    conditionSheet.Range("K" & 4).Value = Mid(tempEmpNum, 3, 1)
    conditionSheet.Range("L" & 4).Value = Right(tempEmpNum, 1)
    '所属会社名
    conditionSheet.Range("E5").Value = mastersheet.Range("D" & rowid).Value
    '社員名
    conditionSheet.Range("E6").Value = mastersheet.Range("B" & rowid).Value
    
    'フリガナ表題
'    personalSheet.Range("L9").Value = "(ﾌﾘｶﾞﾅ)"
'    personalSheet.Range("L9").HorizontalAlignment = xlLeft

'    '改名(変更後戸籍名)
'    conditionSheet.Range("L" & 10).Value = mastersheet.Range("CD" & rowid).Value
'
'    '変更後の通勤経路
'    conditionSheet.Range("D" & 42).Value = mastersheet.Range("CM" & rowid).Value
'    conditionSheet.Range("J" & 42).Value = mastersheet.Range("CN" & rowid).Value
'    conditionSheet.Range("S" & 42).Value = mastersheet.Range("CO" & rowid).Value
'    conditionSheet.Range("X" & 42).Value = mastersheet.Range("CP" & rowid).Value
'    '通勤経路①の交通費
'    conditionSheet.Range("AC" & 42).Value = mastersheet.Range("CQ" & rowid).Value
'
'    conditionSheet.Range("D" & 44).Value = mastersheet.Range("CR" & rowid).Value
'    conditionSheet.Range("J" & 44).Value = mastersheet.Range("CS" & rowid).Value
'    conditionSheet.Range("S" & 44).Value = mastersheet.Range("CT" & rowid).Value
'    conditionSheet.Range("X" & 44).Value = mastersheet.Range("CU" & rowid).Value
'    '通勤経路②の交通費
'    conditionSheet.Range("AC" & 44).Value = mastersheet.Range("CV" & rowid).Value

End Sub

'○【社労士様】退社連絡票 契約社員_外勤

Public Sub resignationConnectDispatch(ByVal book, ByVal sheet, ByVal rowid)
    
    Dim startDate, testdate
    Set resignationSheet = Application.Workbooks(book).Worksheets(sheet)
    '会社名
    resignationSheet.Range("U" & 1).Value = "ＴＳＲソリューションズ株式会社"
    '社員Ｎｏ
    resignationSheet.Range("H" & 3).Value = mastersheet.Range("A" & rowid).Value
    'フリガナ
     resignationSheet.Range("P" & 3).Value = mastersheet.Range("CD" & rowid).Value
    '氏名
    resignationSheet.Range("P" & 4).Value = mastersheet.Range("B" & rowid).Value
    '性別
    resignationSheet.Range("AI" & 4).Value = mastersheet.Range("CB" & rowid).Value
    
    '生年月日
    birthDay = mastersheet.Range("CC" & rowid).Value
    
    Dim dateFmtStr, dateArray
    If Len(birthDay) > 0 Then
        birdayFmt = Format(birthDay, "ggge年ooood日")
        
        dateFmtStr = Replace(Mid(birdayFmt, 3), "年", "-")
        dateFmtStr = Replace(dateFmtStr, "月", "-")
        dateFmtStr = Replace(dateFmtStr, "日", "")
        
        dateArray = Split(dateFmtStr, "-")
        resignationSheet.Range("AD" & 5).Value = dateArray(0)
        resignationSheet.Range("AG" & 5).Value = dateArray(1)
        resignationSheet.Range("AJ" & 5).Value = dateArray(2)
        
        '昭 ・ 平
        If InStr(birdayFmt, "平成") > 0 Then
            '年号が平成の場合、"平"を指定する
            resignationSheet.Range("Z5").Characters(5, 2).Font.Color = vbRed
            resignationSheet.Range("Z5").Characters(5, 2).Font.Bold = True
        ElseIf InStr(birdayFmt, "昭和") > 0 Then
           '年号が昭和の場合、"昭"を指定する
           resignationSheet.Range("Z5").Characters(1, 2).Font.Color = vbRed
           resignationSheet.Range("Z5").Characters(1, 2).Font.Bold = True
        End If
        
    End If
    
    '〒
    'If mastersheet.Range("DV" & rowid).Value <> "" Then
        'zipcode = mastersheet.Range("DV" & rowid).Value
    'Else
        zipcode = mastersheet.Range("BV" & rowid).Value
    'End If
    If Len(zipcode) > 0 Then
'    resignationSheet.Range("I" & 7).Value = Mid(zipcode, 3, (InStr(zipcode, "-") - 3))
'    resignationSheet.Range("M" & 7).Value = Mid(zipcode, InStr(zipcode, "-") + 1)
    
        zipcode = Replace(zipcode, "〒", "")
        Dim zipArray
        zipArray = Split(zipcode, "-")
        resignationSheet.Range("I" & 7).Value = zipArray(0)
        If UBound(zipArray) > 0 Then
            resignationSheet.Range("M" & 7).Value = zipArray(1)
        End If
    End If
    'TEL(下位)
'    If Len(mastersheet.Range("BW" & rowid).Value) > 0 Then
'    telArray = Split(mastersheet.Range("BW" & rowid).Value, "-")
'    resignationSheet.Range("T" & 7).Value = telArray(0)
'    resignationSheet.Range("Y" & 7).Value = telArray(1)
'    resignationSheet.Range("AD" & 7).Value = telArray(2)
'    End If
    If Len(mastersheet.Range("O" & rowid).Value) > 0 Then
    telArray = Split(mastersheet.Range("O" & rowid).Value, "-")
    Dim itemNum As Integer
    itemNum = UBound(telArray) - LBound(telArray) + 1
        If itemNum = 3 Then
            resignationSheet.Range("T" & 7).Value = telArray(0)
            resignationSheet.Range("Y" & 7).Value = telArray(1)
            resignationSheet.Range("AD" & 7).Value = telArray(2)
        Else
            resignationSheet.Range("T" & 7).Value = mastersheet.Range("O" & rowid).Value
            
        End If
    End If
    '住所(下位)
    If mastersheet.Range("DW" & rowid).Value <> "" Then
        resignationSheet.Range("H" & 8).Value = mastersheet.Range("DW" & rowid).Value
    Else
        resignationSheet.Range("H" & 8).Value = mastersheet.Range("BU" & rowid).Value
    End If
    
    '退職日
    retireDay = mastersheet.Range("DV" & rowid).Value
    If Len(retireDay) > 0 Then
        birdayFmt = Format(retireDay, "ggge年ooood日")
        
        dateFmtStr = Replace(Mid(birdayFmt, 3), "年", "-")
        dateFmtStr = Replace(dateFmtStr, "月", "-")
        dateFmtStr = Replace(dateFmtStr, "日", "")
        
        dateArray = Split(dateFmtStr, "-")
        resignationSheet.Range("J" & 5).Value = dateArray(0)
        resignationSheet.Range("M" & 5).Value = dateArray(1)
        resignationSheet.Range("P" & 5).Value = dateArray(2)
    End If
    
End Sub

Private Sub updateData()
    Dim mastersht, datasheet
    Dim id, rowCount, i, j, hasflg
    Set mastersht = Application.Workbooks("TSRS帳票管理マスタ.xls").Worksheets("帳票マスタ")
    Set datasheet = ActiveWorkbook.Worksheets(" (訂)プロパー")
    rowCount = mastersht.UsedRange.Rows(mastersht.UsedRange.Rows.count).Row
    id = datasheet.Range("ac8").Value
    id2 = datasheet.Range("ab110").Value    ' 2015^6-02-01 追加
    hasflg = True
    For i = 4 To rowCount
        hasflg = True
'        If Val(id) = Val(mastersht.Range("a" & i).Value) Then
        If Val(id) = Val(mastersht.Range("a" & i).Value) And Val(id2) = Val(mastersht.Range("f" & i).Value) Then
            hasflg = False
            For j = 1 To 74
                mastersht.Cells(i, j).Value = ""
            Next
            Call SetMasterInfo(i)
            Exit Sub
        End If
    Next
    If hasflg Then
        Call SetMasterInfo(rowCount + 1)
    End If
End Sub
Private Sub getMasterData(ByVal sPath$, ByVal sFileType$, ByRef Fso As Object, ByRef arrf$(), ByRef mf&)
    Dim Folder As Object
    Dim SubFolder As Object
    Dim file As Object
    Set Folder = Fso.GetFolder(sPath)
    Dim rowid As Integer
    Dim usrNm, arr, employeeCount
    rowid = 1
    employeeCount = 0
    Set Common.staffDict = Common.GetDictionaryInstance()
    Set dict = CreateObject("Scripting.Dictionary")
    
    For Each file In Folder.Files
    
        If InStr(file.name, "稟議書") > 0 Then
            Dim mypath As String, idvalue
            mypath = sPath & "\" & file.name
            Workbooks.Open fileName:=mypath
            Call updateData
            '*********************稟議書自動保存機能を追加 WuYang 2016/06/06 Start***************************
            ActiveWorkbook.Save
            '*********************稟議書自動保存機能を追加 WuYang 2016/06/06 End***************************
            ActiveWorkbook.Close
        End If
    Next
    
End Sub

'TSRS帳票管理マスタ取得
Private Sub CommandButton1_Click()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    Dim masterPath, masterwk, rowid, masshet, columnid
    Application.ScreenUpdating = False
    Set Fso = CreateObject("Scripting.FileSystemObject")
    If Application.Version <= 11 Then sFileType = "*.xls" Else sFileType = "*.xls*"
    masterPath = ActiveWorkbook.path & "\TSRS帳票管理マスタ.xls"
    If Dir(masterPath) = "" Then
        FileCopy ActiveWorkbook.path & "\テンプレート\月処理必要テンプレート\TSRS帳票管理マスタ.xls", masterPath
    Else
        fNmaneAdd = "_" & Format(DateSerial(Year(Now), month(Now), 0), "YYYYMM")
        prevMasterPath = ActiveWorkbook.path & "\TSRS帳票管理マスタ" & fNmaneAdd & ".xls"
        If Dir(masterPath) = "" Then
            Name masterPath As prevMasterPath
            FileCopy ActiveWorkbook.path & "\テンプレート\月処理必要テンプレート\TSRS帳票管理マスタ.xls", masterPath
        End If
    
    End If
    Set masterwk = Workbooks.Open(masterPath)
    'ファイル選択
    Call getMasterData(TextBox3.Text, sFileType, Fso, arrf, mf)
    Set masshet = Application.Workbooks("TSRS帳票管理マスタ.xls").Worksheets("帳票マスタ")
    rowid = masshet.UsedRange.Rows(masshet.UsedRange.Rows.count).Row
    columnid = masshet.UsedRange.Columns.count
    For j = 4 To rowid
        Call setstyle(j, 1, columnid)
    Next
    
    Application.ScreenUpdating = True7
    Application.DisplayAlerts = False
    Application.Workbooks("TSRS帳票管理マスタ.xls").Save
    Application.Workbooks("TSRS帳票管理マスタ.xls").Close
    MsgBox "TSRS帳票管理マスタ取得しました。"
    
    Application.Workbooks("月次処理マクロ.xls").Close
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "TSRS帳票管理マスタ取得処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub


Private Sub CommandButton22_Click()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            strPath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    '稟議書パスの取得
    TextBox13.Text = strPath
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "ファイル読み失敗!"
End Sub

'稟議書の格納先
Private Sub CommandButton6_Click()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            strPath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    '稟議書パスの取得
    TextBox3.Text = strPath
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "ファイル読み失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'
Private Sub CommandButton3_Click()
    'ファイルの読み取り
    Call Filelist
End Sub

'ファイルの読み取り
Sub Filelist()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            strPath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    '作業報告書パスの取得
    TextBox1.Text = strPath
    Application.ScreenUpdating = False
    Set Fso = CreateObject("Scripting.FileSystemObject")
    If Application.Version <= 11 Then sFileType = "*.xls" Else sFileType = "*.xls*"

    'ファイル選択
    Call searchFile(strPath, sFileType, Fso, arrf, mf)
    Application.ScreenUpdating = True
    Application.Workbooks("月次処理マクロ.xls").Sheets("Main").Select
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "ファイル読み失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'ファイル選択
Private Sub searchFile(ByVal sPath$, ByVal sFileType$, ByRef Fso As Object, ByRef arrf$(), ByRef mf&)
On Error Resume Next
    Dim Folder As Object
    Dim SubFolder As Object
    Dim file As Object
    Set Folder = Fso.GetFolder(sPath)
    columnCount = Application.Workbooks("月次処理マクロ.xls").Worksheets("master").UsedRange.Columns.count
    Dim rowid As Integer
    Dim usrNm, arr, employeeCount
    rowid = 1
    employeeCount = 0
    Set Common.staffDict = Common.GetDictionaryInstance()
    Set dict = CreateObject("Scripting.Dictionary")
    
    For Each file In Folder.Files
    
        If file.name Like sFileType Then
            Dim mypath As String, idvalue
            mypath = sPath & "\" & file.name
            Workbooks.Open fileName:=mypath
            '作業報告書から、社員IDを取得する
            idvalue = ActiveWorkbook.Sheets("作業報告書").Range("AQ4").Value
            '案件IDを取得する
'            srchText = idvalue
            srchText = "(" & idvalue & ")_"
            srchPos = InStr(file.name, srchText)
            If srchPos > 0 Then
                sfxPos = InStr(file.name, ".xl")
                prjLen = Len(file.name) - (Len(file.name) - sfxPos)
                prjLen = prjLen - (srchPos + Len(srchText))
                prjId = Mid(file.name, srchPos + Len(srchText), prjLen)
                If InStr(prjId, "(新)") > 0 Then
                    prjId = "0000"
                End If
                idvalue = idvalue & prjId
            End If
            '社員IDと案件IDの組合せで場所を保存
            If idvalue = "" Then
                'dict.Add svalue, ""
            Else
                dict.Add idvalue, mypath
            End If
            ActiveWorkbook.Close , False
             
        End If
        
        rowid = rowid + 1
    Next
    
   
    
    Dim i As Integer
    Worksheets("master").Cells(3, columnCount + 2).Value = "チェック結果"
    Worksheets("master").Cells(3, columnCount + 2).Interior.ColorIndex = 48
    Worksheets("master").Cells(3, columnCount + 2).Borders.LineStyle = xlContinuous
    Worksheets("master").Cells(3, columnCount + 2).EntireColumn.AutoFit
    Dim masSheet
    Set masSheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    
    For i = 4 To masSheet.UsedRange.Rows(masSheet.UsedRange.Rows.count).Row
        Dim id
        id = Val(Worksheets("master").Range("a" & i).Value)
        If Val(Worksheets("master").Range("f" & i).Value) = 0 Then
            id = id & Format(Val(Worksheets("master").Range("f" & i).Value), "0000")
        Else
            id = id & Val(Worksheets("master").Range("f" & i).Value)
        End If
        Dim name
        name = Worksheets("master").Range("b" & i).Value
        
        If id <> "" Or name <> "" Then
            If dict.exists(id) Then
                Worksheets("master").Cells(i, columnCount + 1).Value = dict.Item(id)
                Worksheets("master").Cells(i, columnCount + 1).NumberFormatLocal = ";;;"
                Worksheets("master").Columns(columnCount + 1).EntireColumn.Hidden = True
                Worksheets("master").Cells(i, columnCount + 2).Value = "○"
                Worksheets("master").Cells(i, columnCount + 2).Interior.ColorIndex = 4
                Worksheets("master").Cells(i, columnCount + 2).Borders.LineStyle = xlContinuous
          
                CommandButton2.Enabled = True
                
                'Common.strStaff = Common.strStaff & Worksheets("Main").Range("c" & i).Value & ","
                'Common.staffDict.Add employeeCount, Worksheets("Main").Range("c" & i).Value
                'employeeCount = employeeCount + 1
            Else
                Worksheets("master").Cells(i, columnCount + 2).Borders.LineStyle = xlContinuous
                Worksheets("master").Cells(i, columnCount + 2).Value = "×"
                Worksheets("master").Cells(i, columnCount + 2).Interior.ColorIndex = 3
            End If
        Else
            Exit For
        End If
    Next

    
    'If Folder.SubFolders.Count > 0 Then
        'For Each SubFolder In Folder.SubFolders
            'Call searchFile(SubFolder.Path, sFileType, Fso, arrf, mf)
        'Next
    'End If
    
    Set Folder = Nothing
    Set file = Nothing
    Set SubFolder = Nothing
End Sub

'帳票生成
Private Sub CommandButton2_Click()
    UserForm3.Show 0
    
On Error GoTo Err_Handle

    'PDF出力設定確認
    If (TextBox1.Text <> "" And chkPDF.Value = True) Then
        If InStr(Application.ActivePrinter, "PDF") > 0 Then
        Else
            msgText = "PDFファイル作成のために、PDF出力用のプリンターを通常使うプリンター"
            msgText = msgText & "（または「規定」）に設定してから再度実行して下さい。"
            MsgBox msgText
            GoTo Err_Handle
        End If
    End If
    
    If (TextBox1.Text <> "" And chkPDF.Value = True And TextBox5.Text = "") Then
        MsgBox "PDF圧縮用パスワードを入力してください。"
        GoTo Err_Handle
    End If

    Dim endRowNo, rowid, savePath, filePath, ReqID, workhour, hisfile, hissheet, addFlg, uncollectedFlg, saleYearMonth, yasumiDay
    Dim monthProcessFlg, tableSheet
    addFlg = False
    uncollectedFlg = False
    monthProcessFlg = False
    ReqID = 1
    kenPBId = 1
    SendHisId = 3
    Dim StrReqPDF(1 To 500) As String
    Dim StrRepPDF(1 To 500) As String
    Dim StrFileName(1 To 500) As String
    Dim StrFileNameblank(1 To 500) As String
    Dim PDFnum, strfilenameNum
    PDFnum = 1
    strfilenameNum = 1
    yasumiDay = 0
    Set tableSheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("Table")
    Set mainSheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("Main")
    yText = mainSheet.Range("d20").Value
    mText = mainSheet.Range("e20").Value
    Set mastersheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    endRowNo = mastersheet.UsedRange.Rows(mastersheet.UsedRange.Rows.count).Row
    columnCount = mastersheet.UsedRange.Columns.count - 2

    If endRowNo >= 4 Then
        mastersheet.Range("A4:FF" & endRowNo).sort Key1:=mastersheet.Range("BM4"), order1:=xlAscending, Header:=xlGuess
    End If
    
    'テンプレートパス
    filePath = Application.ActiveWorkbook.path & "\テンプレート\月処理必要テンプレート\"
    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    gCompanyName = ""
    Dim attendanceFile, attendanceFileName, attendanceSheet, attendanceRowId, attendanceFileown, attendanceFileNameown, attendanceSheetown, seiReqId
    seiReqId = 1
    
    If (TextBox1 <> "") Then
        '稼動勤務勤怠集計表の作成
        attendanceRowId = 5  'タイトル業務
        attendanceFileName = "【TSRS】" & Year(Date) & "年" & month(Date) & "月次処理_稼動勤務勤怠集計表(外勤).xls"
        FileCopy filePath & "稼動勤務勤怠集計表.xls", savePath & "\" & attendanceFileName
        Set attendanceFile = Workbooks.Open(savePath & "\" & attendanceFileName)
        Set attendanceSheet = Application.Workbooks(attendanceFileName).Worksheets("勤怠集計表")
    End If
    If (TextBox2 <> "") Then
        attendanceFileNameown = "【TSRS】" & Year(Date) & "年" & month(Date) & "月次処理_稼動勤務勤怠集計表(内勤).xls"
        FileCopy filePath & "稼動勤務勤怠集計表.xls", savePath & "\" & attendanceFileNameown
        Set attendanceFileown = Workbooks.Open(savePath & "\" & attendanceFileNameown)
        Set attendanceSheetown = Application.Workbooks(attendanceFileNameown).Worksheets("勤怠集計表")
    End If
    If (TextBox1 <> "") Then
        '売上げ仕入れ管理台帳の作成
        Dim saleFile, saleFileName, saleSheet, saleRowId, priceRowId, priceCount, strYearMonth
        saleRowId = 7 '売上明細のタイトルの行目
        priceRowId = 21 '仕入明細のタイトルの行目
        priceCount = 0
        saleFileName = Format(Date, "ggge年") & month(Date) & "月" & "1日～売上げ仕入れ管理台帳.xls"
        FileCopy filePath & "売上げ仕入れ管理台帳.xls", savePath & "\" & saleFileName
        Set saleFile = Workbooks.Open(savePath & "\" & saleFileName)
        Set saleSheet = Application.Workbooks(saleFileName).Worksheets("IT＃月")
        saleSheet.name = "IT" & month(Date) & "月"
        saleSheet.name = "IT" & month(Date) & "月"
        'タイトルの設定
        '年月を取得
        strYearMonth = Format(Date, "ggge年") & month(Date) & "月"
        saleSheet.Range("B3").Value = strYearMonth & "分 売上請求明細"
        saleSheet.Range("B19").Value = strYearMonth & "分　原価明細"
        saleSheet.Range("B29").Value = strYearMonth & "分　インセンティブ明細"
        
        '未回収一覧の作成
        Dim uncollectedFile, uncollectedSheet, uncollectedRowId
        uncollectedRowId = 4 '未回収一覧のタイトルの行目
        FileCopy filePath & "未回収一覧.xls", savePath & "\" & "未回収一覧.xls"
        Set uncollectedFile = Workbooks.Open(savePath & "\" & "未回収一覧.xls")
        Set uncollectedSheet = Application.Workbooks("未回収一覧.xls").Worksheets("YYYYMMDD")
        uncollectedSheet.name = Format(Now(), "YYYYMMDD")
        
        'SE管理台帳の作成
        Dim semanagerFile, semanagerSheet, salesListId, costListId
        Dim semanagerRowId As Integer, semanagerRowId1 As Integer
        semanagerRowId = 9 'SE管理台帳のタイトルの行目
        salesListId = 3
        costListId = 3
        FileCopy filePath & "SE管理台帳.xls", savePath & "\" & "SE管理台帳.xls"
        Set semanagerFile = Workbooks.Open(savePath & "\" & "SE管理台帳.xls")
        Set semanagerSheet = Application.Workbooks("SE管理台帳.xls").Worksheets("最新稼動管理-1")
        
        '書類送付状_履歴_発送処理一覧の作成
        Dim docTraLetterFile, docTraLetterSheet
        Dim docTraLetterRowId As Integer
        docTraLetterRowId = 2
        FileCopy filePath & "書類送付状_履歴_発送処理一覧.xls", savePath & "\" & "書類送付状_履歴_発送処理一覧.xls"
        Set docTraLetterFile = Workbooks.Open(savePath & "\" & "書類送付状_履歴_発送処理一覧.xls")
        Set docTraLetterSheet = Application.Workbooks("書類送付状_履歴_発送処理一覧.xls").Worksheets("送付状_発送履歴")
        
        'PDFフォーム出力用プリンター名取得
        'Dim pdfPrtTempShl As Object
        'Dim pdfPrtTempObj As Object
        'Set pdfPrtTempShl = CreateObject("shell.application")
        'For Each pdfPrtTempObj In pdfPrtTempShl.Namespace(4).items
            'If InStr(pdfPrtTempObj.name, " PDF") > 0 Then
                'pdfPrnrNm = pdfPrtTempObj.name
                'Exit For
            'End If
        'Next pdfPrtTempObj
        'Set pdfPrtTempShl = Nothing
        '帳票を出力
        For rowid = 4 To endRowNo
            Dim path, company, staff, reqFilePath, companyId, requestId, workReportName, workUnit, BPcompanyId
            
            'masterシートの「交通費」と「経費」を空白に設定
            mastersheet.Range("M" & rowid).Value = 0
            mastersheet.Range("N" & rowid).Value = 0
                       
            If mastersheet.Cells(rowid, columnCount - 3).Value = "○" Then
                monthProcessFlg = True
                'チェック結果がtrueの場合
                If mastersheet.Cells(rowid, columnCount + 2).Value = "○" Then
                    addFlg = True
                    '作業報告書パス
                    path = mastersheet.Cells(rowid, columnCount + 1).Value
                    '上位会社名
                    companyforfile = Trim(mastersheet.Range("BM" & rowid).Value)
                    company = Trim(mastersheet.Range("BM" & rowid).Value)
                    If (InStr(company, " ") <> 0) Then
                        company = Replace(company, " ", "")
                        StrFileName(strfilenameNum) = company
                        StrFileNameblank(strfilenameNum) = companyforfile
                    End If
                    If (InStr(company, "　") <> 0) Then
                        company = Replace(company, "　", "")
                        StrFileName(strfilenameNum) = company
                        StrFileNameblank(strfilenameNum) = companyforfile
                    End If
                    strfilenameNum = strfilenameNum + 1
                    'BP会社名
                    BPcompanyId = mastersheet.Range("C" & rowid).Value
                    '上位会社ID
                    companyId = mastersheet.Range("BL" & rowid).Value
                    '社員名
                    staff = mastersheet.Range("B" & rowid).Value
                    '案件ID
                    prjId = mastersheet.Range("F" & rowid).Value
                    '上位会社フォルダの作成
                    Call MakeCompanyFolder(savePath, company, companyId)
                    
                    Dim paraMonth
                    Workbooks.Open fileName:=path
                    paraMonth = ActiveWorkbook.Sheets("作業報告書").Range("a2").Value
                    ActiveWorkbook.Close
                    
                    
                    '作業報告書の作成----------------------------------------------------------------
                    '********************************作業報告書の印刷 WuYang 2016/06/03 Start*************************
                    workReportName = "【TSRS】" & staff & "_【" & companyforfile & "】様" & "_(" & prjId & ")" & Format(Date, "YYYYMMDD") & "_作業報告" & Year(Date) & "年" & month(paraMonth) & "月分.xls"
                    '********************************作業報告書の印刷 WuYang 2016/06/03 End*************************
                    FileCopy path, savePath & "\" & company & "\" & workReportName
                    Workbooks.Open fileName:=savePath & "\" & company & "\" & workReportName
                    StrRepPDF(PDFnum) = savePath & "\" & company & "\" & workReportName
                    
                    Dim workReportSheet
                    Set workReportSheet = ActiveWorkbook.Sheets("作業報告書")
                    
                    '********************************作業報告書の印刷 WuYang 2016/06/03 Start*************************
                    'PDFファイル作成
                    'pFilePDFNm = Replace(savePath & "\" & company & "\" & workReportName, ".xls", ".pdf")
                    'pFilePDFNm = "作業報告書.pdf"
                    'workReportSheet.PrintOut PrToFileName:=pFilePDFNm
                    'prevPrtNm = Application.ActivePrinter
                    'workReportSheet.PrintOut ActivePrinter:=prevPrtNm, PrToFileName:=pFilePDFNm
                    'Application.ActivePrinter = prevPrtNm
                    'workReportSheet.PrintOut
                    '********************************作業報告書の印刷 WuYang 2016/06/03 End*************************
                    '実績時間の取得
                    workhour = workReportSheet.Range("Q41").Text
                    workUnit = Replace(mastersheet.Range("AV" & rowid).Text, "分", "")
                    workhour = GetTime(workhour, workUnit)
                    
                    '作業報告書の年月を取得
                    saleYearMonth = workReportSheet.Range("a2").Value
                    
                    If saleYearMonth <> "" Then
                           '締日（本人）の取得
                        If mastersheet.Range("T" & rowid).Value <> "" Then
                            If mastersheet.Range("T" & rowid).Value = "月末" Then
                                mastersheet.Range("AM" & rowid).Value = Year(saleYearMonth) & "/" & month(saleYearMonth) & "/" & GetMonthEnd(Year(saleYearMonth), month(saleYearMonth))
                            Else
                                mastersheet.Range("AM" & rowid).Value = Year(saleYearMonth) & "/" & month(saleYearMonth) & "/" & mastersheet.Range("T" & rowid).Value
                            End If
                        End If
    
                        '締日（上位）の取得
                        If mastersheet.Range("BG" & rowid).Value <> "" Then
                            If mastersheet.Range("BG" & rowid).Value = "月末" Then
                                mastersheet.Range("AH" & rowid).Value = Year(saleYearMonth) & "/" & month(saleYearMonth) & "/" & GetMonthEnd(Year(saleYearMonth), month(saleYearMonth))
                            Else
                                mastersheet.Range("AH" & rowid).Value = Year(saleYearMonth) & "/" & month(saleYearMonth) & "/" & mastersheet.Range("BG" & rowid).Value
                            End If
                        End If
                    End If
                    
                    '作業報告書の交通費を取得して、masterシートに設定  2015-02-03 追加
                    mastersheet.Range("M" & rowid).Value = workReportSheet.Range("V42").Value
                    '作業報告書の経費を取得して、masterシートに設定  2015-02-03 追加
                    mastersheet.Range("N" & rowid).Value = workReportSheet.Range("V43").Value
                    
                    '======================稼動勤務勤怠集計表用のデータを作成 Str======================
                    Dim absence_days, late_early_days, late_early_time, strDate, strBaseDate, endDate, endBaseDate, baseDate
                    absence_days = 0     '欠勤日数の初期化
                    late_early_days = 0  '遅・早次数の初期化
                    late_early_time = 0  '遅・早時間の初期化
                
                    thisMonthWorkDayCount = 0
                    For iWorkCount = 9 To 39
                        '日付のフォントが赤色にセットする場合、出勤日ではない
                        'If workReportSheet.Range("D" & iWorkCount).Value <> "" And _
                                workReportSheet.Range("D" & iWorkCount).Font.ColorIndex <> 3 Then
                        If workReportSheet.Range("D" & iWorkCount).Value <> "" And _
                        workReportSheet.Range("D" & iWorkCount).Font.ColorIndex <> 3 And _
                        IsNumeric(workReportSheet.Range("D" & iWorkCount).Value) And _
                        workReportSheet.Range("D" & iWorkCount).Value <> "祝" And _
                        workReportSheet.Range("D" & iWorkCount).Value <> "1" And _
                        workReportSheet.Range("D" & iWorkCount).Value <> "7" Then
                                
                            '当月営業日数カウント
                            thisMonthWorkDayCount = thisMonthWorkDayCount + 1
                            '合計時間がなし時、欠勤にする
                            If workReportSheet.Range("U" & iWorkCount).Text = "" Then
                                absence_days = absence_days + 1
                            Else
                                If workReportSheet.Range("F" & iWorkCount).Text <> "" And mastersheet.Range("AA" & rowid).Text <> "" Then
                                    '指定する出勤時間を基に、遅刻次数、時間を算出
                                    strDate = Date & " " & workReportSheet.Range("F" & iWorkCount).Text
                                    strBaseDate = Date & " " & mastersheet.Range("AA" & rowid).Text
    
                                    If IsDate(strDate) And IsDate(strBaseDate) Then
                                        If DateDiff("n", CDate(strBaseDate), CDate(strDate)) > 0 Then
                                            '遅刻次数の算出
                                            late_early_days = late_early_days + 1
    
                                            '遅刻時間の算出
                                            late_early_time = late_early_time + WorksheetFunction.Ceiling(DateDiff("n", strBaseDate, strDate) / 60, 0.5)
                                        End If
                                    End If
                                End If
    
                                If workReportSheet.Range("I" & iWorkCount).Text <> "" And mastersheet.Range("AB" & rowid).Text <> "" Then
                                    '指定する出勤時間を基に、早退次数、時間を算出
                                    endDate = Date & " " & workReportSheet.Range("I" & iWorkCount).Text & ":00"
                                    endBaseDate = Date & " " & mastersheet.Range("AB" & rowid).Text
    
                                    If IsDate(endDate) And IsDate(endBaseDate) Then
                                        If DateDiff("n", CDate(endBaseDate), CDate(endDate)) < 0 Then
                                            '早退次数の算出
                                            late_early_days = late_early_days + 1
    
                                            '早退時間の算出
                                            late_early_time = late_early_time + WorksheetFunction.Ceiling(DateDiff("n", endDate, endBaseDate) / 60, 0.5)
                                        End If
                                    End If
                                End If
                            End If
                        End If
                    Next
                    '工数
                    '************************************20160622 バグ修正 WuYang Start********************************************
                    'If (workhour > mastersheet.Range("DE" & rowid).Value) Then
                        'mastersheet.Range("X" & rowid).Value = 1
                    'Else
                       ' mastersheet.Range("X" & rowid).Value = workReportSheet.Range("V40").Value / thisMonthWorkDayCount
                    'End If
                    '************************************20160622 バグ修正 WuYang End********************************************
                    '欠勤日数
                    'masterSheet.Cells(rowid, columnCount + 3).Value = absence_days
                    mastersheet.Cells(rowid, columnCount + 3).Value = workReportSheet.Range("AA41").Value
                    '遅・早次数
                    mastersheet.Cells(rowid, columnCount + 4).Value = late_early_days
                    '遅・早時間
                    mastersheet.Cells(rowid, columnCount + 5).Value = late_early_time
                    '実働時間
                    mastersheet.Cells(rowid, columnCount + 6).Value = Replace(workhour, ":", ".")
                    '出勤日数
                    mastersheet.Cells(rowid, columnCount + 8).Value = workReportSheet.Range("V40").Value
                    '出勤日数
                    mastersheet.Cells(rowid, columnCount + 10).Value = thisMonthWorkDayCount
                    '有休取得日数
                    mastersheet.Cells(rowid, columnCount + 11).Value = workReportSheet.Range("AA40").Value
                    '調整休日
                    yasumiDay = workReportSheet.Range("V41").Value
                    '本人残業追加
                    culcTempWork = mastersheet.Range("X" & rowid).Value
                    culctempOverBs = mastersheet.Range("DD" & rowid).Value
                    culctempUndrBs = mastersheet.Range("DE" & rowid).Value
                    culcTempRealTm = CInt(mastersheet.Cells(rowid, columnCount + 6).Value)
                    culcTempOverPr = mastersheet.Range("DF" & rowid).Value
                    culcTempUndrPr = mastersheet.Range("DG" & rowid).Value
                    mastersheet.Cells(rowid, columnCount + 14).NumberFormatLocal = "\#,##0"
                    If IsNumeric(culctempOverBs) Then
                        If culcTempRealTm > culctempOverBs * culcTempWork Then
                            culcTempSet = culcTempOverPr * (culcTempRealTm - (culctempOverBs * culcTempWork))
                            mastersheet.Cells(rowid, columnCount + 14).Value = culcTempSet
                        End If
                    Else
                        mastersheet.Cells(rowid, columnCount + 14).Value = 0
                    End If
                    '本人勤怠控除
                    mastersheet.Cells(rowid, columnCount + 15).NumberFormatLocal = "\#,##0"
                    If IsNumeric(culctempUndrBs) Then
                        If culcTempRealTm < culctempUndrBs * culcTempWork Then
                            culcTempSet = culcTempUndrPr * ((culctempUndrBs * culcTempWork) - culcTempRealTm)
                            mastersheet.Cells(rowid, columnCount + 15).Value = culcTempSet
                        End If
                    Else
                        mastersheet.Cells(rowid, columnCount + 15).Value = 0
                    End If
                    '======================稼動勤務勤怠集計表用のデータを作成 End======================
                    '======================検収通知書（兼）請求書の作成　20160614 WuYang Start===========================
                    If (mastersheet.Range("E" & rowid).Value = "外注") Then
                        '保存フォルダの作成
                        BPCompanyForFile = mastersheet.Range("D" & rowid).Value
                        BPCompany = Replace(mastersheet.Range("D" & rowid).Value, " ", "")
                        BPCompany = Replace(BPCompany, "　", "")
                        savedirout = savePath & "\" & "外注向け"
                        If Dir(savedirout, vbDirectory) = "" Then
                            MkDir savedirout
                        End If
                        savedir = savePath & "\" & "外注向け" & "\" & BPCompany
                        If Dir(savedir, vbDirectory) = "" Then
                            MkDir savedir
                        End If
                        '検収通知書名の作成
                        reqBPPath = savedir & "\" & "【" & BPCompanyForFile & "-" & Format(month(paraMonth), "00") & "月" & "】-" & staff & "-" & "検収通知書.xls"
                        '請求書の作成--------------------------------------------------------------------
                        FileCopy filePath & "\" & "外注向け" & "\" & "【BP社名+納品〆対象月】検収通知書.xls", reqBPPath
                        Workbooks.Open fileName:=reqBPPath
                        Set workReqBPSheet = ActiveWorkbook.Sheets("検収通知書")
                        Call SetkensyuFlieInfo(rowid, workReqBPSheet, kenPBId, workhour, paraMonth)
                        ActiveWorkbook.Save
                        ActiveWorkbook.Close
                        Workbooks(workReportName).Close
                        kenPBId = kenPBId + 1
                        'FAX送付状を作成
                        Dim fileFax_ken, filePrint_ken, filecount_ken, fileProntName_ken
                        filecount_ken = Application.WorksheetFunction.CountIf(mastersheet.[C1:C65536], "=" & BPcompanyId)
                        fileFax_ken = savedir & "\送付状_FAX.xls"
                        'fileProntName_ken = "書類送付状_" & BPCompany & "_請求書、作業報告書_" & Format(Date, "YYYYMMDD") & ".xls"
                        'filePrint_ken = savedir & "\" & fileProntName_ken
                        'FAXの場合
                        If (chkFax.Value = True) Then
                            FileCopy filePath & "送付状_FAX.xls", fileFax_ken
                            Workbooks.Open fileName:=fileFax_ken
                            '送付状(fax)内容の作成
                            Call setSendFormFaxInfo_ken(rowid, Application.ActiveWorkbook.name, filecount_ken)
                            '送付状(fax)保存
                            Application.ActiveWorkbook.Save
                            '送付状(fax)を閉じる
                            Application.ActiveWorkbook.Close
                        End If
                    Else
                        Application.ActiveWorkbook.Close
                    End If
                    '======================検収通知書（兼）請求書の作成　20160614 WuYang End===========================
                    SendHisId = SendHisId + 1
                    
                    '請求書ファイル名の作成
                    '********************************請求書の印刷 WuYang 2016/06/09 Start*************************
                    reqFilePath = savePath & "\" & company & "\【" & companyforfile & "】様-" & Year(Now) & Format(month(paraMonth), "00") & "月-" & staff & "-" & prjId & "-請求書.xls"
                    '********************************請求書の印刷 WuYang 2016/06/09 End*************************
                    '請求書の作成--------------------------------------------------------------------
                    FileCopy filePath & "請求書.xls", reqFilePath
                    Workbooks.Open fileName:=reqFilePath
                    Set workReqPDFSheet = ActiveWorkbook.Sheets("請求書 (PDF印刷用)")
                    '請求書内容の作成
                    'requestId = "請" & Format(Date, "YYMMDD") & Format(reqId, "000")
                    requestId = "請" & Format(Date, "YYMMDD") & Format(seiReqId, "000")
                    planrequesid = Format(Date, "YYMMDD") & Format(seiReqId, "000")
                    ReqIDTo = SetReqFlieInfo(rowid, Application.ActiveWorkbook.name, requestId, workhour, paraMonth, planrequesid)
                    seiReqId = seiReqId + 1
                    
                    StrReqPDF(PDFnum) = reqFilePath
                    PDFnum = PDFnum + 1
                    
                    '請求書保存
                    Application.ActiveWorkbook.Save
                    
                    '********************************請求書の印刷 WuYang 2016/06/03 Start*************************
                    'PDFファイル作成
                    'pFilePDFNm = Replace(reqFilePath, ".xls", ".pdf")
                    'workReqPDFSheet.PrintOut PrToFileName:=pFilePDFNm
                    'prevPrtNm = Application.ActivePrinter
                    'workReqPDFSheet.PrintOut ActivePrinter = prevPrtNm, PrToFileName:=pFilePDFNm
                    'Application.ActivePrinter = prevPrtNm
                    'workReqPDFSheet.PrintOut
                    '********************************請求書の印刷 WuYang 2016/06/03 End*************************
                    
                    '請求書を閉じる
                    Application.ActiveWorkbook.Close
                    SendHisId = SendHisId + 1
                    
                    '送付状の作成--------------------------------------------------------------------
                    
                    Dim fileFax, filePrint, filecount, fileProntName
                    
                    filecount = Application.WorksheetFunction.CountIf(mastersheet.[BL1:BL65536], "=" & companyId)
                    fileFax = savePath & "\" & company & "\送付状_FAX.xls"
                    fileProntName = "書類送付状_" & company & "_請求書、作業報告書_" & Format(Date, "YYYYMMDD") & ".xls"
                    filePrint = savePath & "\" & company & "\" & fileProntName
                    'FAXの場合
                    If (chkFax.Value = True) Then
                        FileCopy filePath & "送付状_FAX.xls", fileFax
                        Workbooks.Open fileName:=fileFax
                        '送付状(fax)内容の作成
                        Call setSendFormFaxInfo(rowid, Application.ActiveWorkbook.name, filecount)
                        '送付状(fax)保存
                        Application.ActiveWorkbook.Save
                        '送付状(fax)を閉じる
                        Application.ActiveWorkbook.Close
                    End If
                    If (chkPrint.Value = True) Then
                        FileCopy filePath & "書類送付状.xls", filePrint
                        Workbooks.Open fileName:=filePrint
                        '送付状(書類)内容の作成
                        Call setSendFormPrintInfo(rowid, Application.ActiveWorkbook.name, filecount)
                        '送付状(書類)保存
                        Application.ActiveWorkbook.Save
                        '送付状(書類)を閉じる
                        Application.ActiveWorkbook.Close
                    End If
                    
                    '稼動勤務勤怠集計表にレコードを追加
                    attendanceRowId = attendanceRowId + 1
                    Call SetAttendanceSheet(attendanceFileName, attendanceRowId, rowid, True, yasumiDay)
                    
                    '売上げ仕入れ管理台帳にレコードを追加
                    saleRowId = saleRowId + 1
                    priceCount = priceCount + 1
                    priceRowId = 21 + (saleRowId - 8) + priceCount
                    insentRowId = 32 + (saleRowId - 8) + ((priceCount - 1) * 2)
                    Call SetSaleSheet(saleFileName, saleRowId, priceRowId, insentRowId, rowid, planrequesid)
                    
                    'SE管理台帳の最新稼動管理「確定分」にレコードを追加
                    semanagerRowId = semanagerRowId + 1
                    Call SetSeManagerListInfo(semanagerRowId, rowid)
                    
                    'SE管理台帳のシード「売上げ」
                    salesListId = salesListId + 1
                    Call SetSalesListInfo(salesListId, rowid)
                    
                    'SE管理台帳のシード「原価」
                    costListId = costListId + 1
                    Call SetCostListInfo(costListId, rowid)
                    
                    '書類送付状_履歴_発送処理一覧.xls
                    'If mastersheet.Range("E" & rowid).Value <> "正社員" Then
                    docTraLetterRowId = docTraLetterRowId + 1
                    Call SetDocTransmittalLetter(requestId, docTraLetterRowId, rowid)
                    'End If
                    
                ElseIf mastersheet.Cells(rowid, columnCount + 2).Value = "×" Then
                    uncollectedFlg = True
                    '稼動勤務勤怠集計表にレコードを追加
                    attendanceRowId = attendanceRowId + 1
                    Call SetAttendanceSheet(attendanceFileName, attendanceRowId, rowid, False, yasumiDay)
                    
                    '未回収一覧表にレコードを追加
                    uncollectedRowId = uncollectedRowId + 1
                    Call setUnRecoverInfo(uncollectedSheet.name, uncollectedRowId, rowid)
                    
                    'SE管理台帳の最新稼動管理「確定分」にレコードを追加
                    semanagerRowId = semanagerRowId + 1
                    Call SetSeManagerListInfo(semanagerRowId, rowid)
                End If
            End If
        Next
    
        'SE管理台帳の最新稼動管理「確定分」の合計を設定
        If semanagerRowId > 13 Then
            semanagerSheet.Range("Q" & (semanagerRowId + 1)).Formula = "=SUM(Q10" & ":Q" & (semanagerRowId) & ")"
        End If
        semanagerRowId1 = semanagerRowId
          
        '現在年月と作業報告書の年月
        If saleYearMonth <> "" And Year(Date) = Year(saleYearMonth) And month(Date) = month(saleYearMonth) Then
            saleYearMonth = DateSerial(yText, mText, 1)
        End If
        
        'SE管理台帳の最新稼動管理「案件終了」にレコードを追加
        'Call SetSeManagerProjectsEndInfo(semanagerRowId1)
        
        'PDF印刷
        If (TextBox1.Text <> "" And chkPDF.Value = True) Then
            For i = 1 To PDFnum
                If (StrReqPDF(i) <> "" And StrRepPDF(i) <> "") Then
                    '請求書の印刷
                    Set ReqPDFFile = Workbooks.Open(StrReqPDF(i))
                    Set workReqPDFSheet = Application.Workbooks(ReqPDFFile.name).Worksheets("請求書 (PDF印刷用)")
                    workReqPDFSheet.PrintOut
                    '作業報告書の印刷
                    Set RepPDFFile = Workbooks.Open(StrRepPDF(i))
                    Set workRepPDFSheet = Application.Workbooks(RepPDFFile.name).Worksheets("作業報告書")
                    workRepPDFSheet.PrintOut
                    Workbooks(ReqPDFFile.name).Close
                    Workbooks(RepPDFFile.name).Close
                End If
            Next i
        End If
    End If
    
    '稼動勤務勤怠集計表を保存して、閉じる
    '稼動勤務勤怠集計表（内勤）にデータを出力する
    If TextBox2.Text <> "" And Dir(TextBox2.Text) <> "" Then
        Select Case Right(TextBox2.Text, 4)
        Case ".csv"
        'Csvを基に、稼動勤務勤怠集計表にデータを出力する
            Call SetAttendanceByCsv(attendanceFileNameown, 5)
        Case ".xls", "xlsx"
        'Excelを基に、稼動勤務勤怠集計表にデータを出力する
            Call SetAttendanceByExcl(attendanceFileNameown, 5, yText & mText, tableSheet)
        Case Else
        End Select
        
        attendanceFileown.Save
        attendanceFileown.Close
    End If
    If TextBox1.Text <> "" Then
        '上枠線の設定
        With attendanceSheet.Range("B6:AI" & attendanceRowId).Borders(xlEdgeTop)
            .LineStyle = xlContinuous
            .Weight = 4
        End With
        '下枠線の設定
        With attendanceSheet.Range("B6:AI" & attendanceRowId).Borders(xlEdgeBottom)
                .LineStyle = xlContinuous
            .Weight = 4
        End With
        '左枠線の設定
        With attendanceSheet.Range("B6:AI" & attendanceRowId).Borders(xlEdgeLeft)
                .LineStyle = xlContinuous
            .Weight = 4
        End With
        '右枠線の設定
        With attendanceSheet.Range("B6:AI" & attendanceRowId).Borders(xlEdgeRight)
                .LineStyle = xlContinuous
            .Weight = 4
        End With
        attendanceFile.Save
        attendanceFile.Close
        Set attendanceFile = Nothing
    
        If addFlg = False And Dir(savePath & "\" & attendanceFileName) <> "" Then
            Kill savePath & "\" & attendanceFileName
        ElseIf saleYearMonth <> "" And Dir(savePath & "\" & attendanceFileName) <> "" Then
                FileCopy savePath & "\" & attendanceFileName, savePath & "\" & "【TSRS】" & Year(saleYearMonth) & "年" & month(saleYearMonth) & "月次処理_稼動勤務勤怠集計表(外勤).xls"
                Kill savePath & "\" & attendanceFileName
        End If
    
        'If addFlg = False And Dir(savePath & "\" & attendanceFileNameown) <> "" Then
            'Kill savePath & "\" & attendanceFileNameown
        'ElseIf saleYearMonth <> "" And Dir(savePath & "\" & attendanceFileNameown) <> "" Then
                'FileCopy savePath & "\" & attendanceFileNameown, savePath & "\" & "【TSRS】" & Year(saleYearMonth) & "年" & month(saleYearMonth) & "月次処理_稼動勤務勤怠集計表(内勤).xls"
                'Kill savePath & "\" & attendanceFileNameown
        'End If
        
        '売上げ仕入れ管理台帳を保存して、閉じる
        'セルの数式を変更する
        If addFlg = True Then
            '売上の合計を設定
            saleSheet.Range("I" & (saleRowId + 1)).Formula = "=SUM(I8:I" & saleRowId & ")"
            saleSheet.Range("K" & (saleRowId + 1)).Formula = "=SUM(K8:K" & saleRowId & ")"
            
            '仕入の合計を設定
            saleSheet.Range("I" & (priceRowId + 1)).Formula = "=SUM(I" & (22 + (saleRowId - 8)) & ":I" & (priceRowId) & ")"
            saleSheet.Range("K" & (priceRowId + 1)).Formula = "=SUM(K" & (22 + (saleRowId - 8)) & ":K" & (priceRowId) & ")"
        
            'インセンティブの合計を設定
            saleSheet.Range("I" & (insentRowId + 1)).Formula = "=SUM(I" & (32 + (saleRowId - 8)) & ":I" & (insentRowId) & ")"
            saleSheet.Range("J" & (insentRowId + 1)).Formula = "=SUM(J" & (32 + (saleRowId - 8)) & ":J" & (insentRowId) & ")"
            saleSheet.Range("K" & (insentRowId + 1)).Formula = "=SUM(K" & (32 + (saleRowId - 8)) & ":K" & (insentRowId) & ")"
        
            '売上の財務レポートを設定
            saleSheet.Range("C" & (4 + saleRowId)).Formula = "=COUNTIF(C8:C" & saleRowId & ",""新規"")"
            saleSheet.Range("C" & (5 + saleRowId)).Formula = "=COUNTIF(C8:C" & saleRowId & ",""更新"")"
            saleSheet.Range("C" & (6 + saleRowId)).Formula = "=COUNTIF(C8:C" & saleRowId & ",""終了"")"
            '仕入の財務レポートを設定
            saleSheet.Range("B" & (4 + priceRowId)).Value = ((saleRowId - 7) + priceCount) & "名　（ 売上　" & (saleRowId - 7) & "名　仕入" & priceCount & "名 ）"
            
            'タイトルの設定
            If saleYearMonth <> "" Then
                saleSheet.name = "IT" & month(saleYearMonth) & "月"
                '年月を取得
                strYearMonth = Format(saleYearMonth, "ggge年") & month(saleYearMonth) & "月"
                saleSheet.Range("B3").Value = strYearMonth & "分 売上請求明細"
                saleSheet.Range("B" & (11 + saleRowId)).Value = strYearMonth & "分　原価明細"
                saleSheet.Range("B" & (7 + priceRowId)).Value = strYearMonth & "分　インセンティブ明細"
            End If
            
            'インセンティブ、営業毎・新規／継続別集計とセット
            Call setInsentiveTotal(saleSheet, priceRowId, insentRowId, saleRowId, priceCount)
            
            saleFile.Save
            saleFile.Close
            Set saleFile = Nothing
            
            If saleYearMonth <> "" And Dir(savePath & "\" & saleFileName) <> "" Then
                FileCopy savePath & "\" & saleFileName, savePath & "\" & Format(saleYearMonth, "ggge年") & month(saleYearMonth) & "月" & "1日～売上げ仕入れ管理台帳.xls"
                Kill savePath & "\" & saleFileName
            End If
        Else
            saleFile.Save
            saleFile.Close
            If Dir(savePath & "\" & saleFileName) <> "" Then
                Kill savePath & "\" & saleFileName
            End If
        End If
        
        '未回収一覧を保存して、閉じる
        uncollectedFile.Save
        uncollectedFile.Close
        Set uncollectedFile = Nothing
        If uncollectedFlg = False And Dir(savePath & "\未回収一覧.xls") <> "" Then
            Kill savePath & "\未回収一覧.xls"
        End If
        
        
        'SE管理台帳を保存して、閉じる
        'If semanagerRowId1 > 18 Then
         '   semanagerSheet.Range("A" & semanagerRowId + 2 & ":A" & semanagerRowId1).Merge
        'End If
        '上枠線の設定
        With semanagerSheet.Range("A10:S10").Borders(xlEdgeTop)
            .LineStyle = xlContinuous
            .Weight = 3
        End With
        '下枠線の設定(確定分)
        If semanagerRowId > 12 Then
            With semanagerSheet.Range("A" & (semanagerRowId + 1) & ":S" & (semanagerRowId + 1)).Borders(xlEdgeBottom)
                .LineStyle = xlContinuous
                .Weight = 3
            End With
        Else
            With semanagerSheet.Range("A13:S13").Borders(xlEdgeBottom)
                .LineStyle = xlContinuous
                .Weight = 3
            End With
        End If
    
        '下枠線の設定(案件終了)
        'If semanagerRowId1 > 18 Then
        '    With semanagerSheet.Range("A" & semanagerRowId1 & ":S" & semanagerRowId1).Borders(xlEdgeBottom)
        '        .LineStyle = xlContinuous
        '       .Weight = 3
        '    End With
        'Else
        '    With semanagerSheet.Range("A18:S18").Borders(xlEdgeBottom)
        '        .LineStyle = xlContinuous
        '        .Weight = 3
        '   End With
       ' End If
        
        semanagerFile.Save
        semanagerFile.Close
        Set semanagerFile = Nothing
    
        If addFlg = False And Dir(savePath & "\" & "SE管理台帳.xls") <> "" Then
            Kill savePath & "\" & "SE管理台帳.xls"
        End If
        
        '書類送付状_履歴_発送処理一覧を保存して、閉じる
        docTraLetterFile.Save
        docTraLetterFile.Close
        Set docTraLetterFile = Nothing
        
        If (TextBox1.Text <> "" And chkPDF.Value = True) Then
        
            If addFlg = False And Dir(savePath & "\" & "書類送付状_履歴_発送処理一覧.xls") <> "" Then
                Kill savePath & "\" & "書類送付状_履歴_発送処理一覧.xls"
            End If
            rc = MsgBox("画面切替してください！PDF保存完了後、「はい」クリックし、圧縮実行しますか？", vbYesNo)
            If (rc = vbNo) Then
                rc = MsgBox("画面切替してください！PDF保存完了後、「はい」クリックし、圧縮実行しますか？", vbYesNo)
                If (rc = vbNo) Then
                    Application.Wait (Now + TimeValue("0:00:10"))
                End If
            End If
                
            '送付状_メール発送
            FileCopy filePath & "送付状_メール発送.xls", savePath & "\送付状_メール発送.xls"
            Set hisfile = Workbooks.Open(savePath & "\送付状_メール発送.xls")
        
            Dim mainFolder As Object
            Dim SubFolder As Object
            Dim fileName As Object
            Dim id
        
            Set Fso = CreateObject("Scripting.FileSystemObject")
            Set mainFolder = Fso.GetFolder(savePath)
            id = 3
            Call createMailFixText
            
            str_Pass = TextBox5.Text
            
            For Each fileName In mainFolder.SubFolders
                Dim companyName, staffname, email, remark, content, temp
                
                'If Filename.SubFolders.Count > 0 Then
                Set SubFolder = Fso.GetFolder(savePath & "\" & fileName.name)
            
                '
                
                If (InStr(SubFolder, "外注向け") = 0) Then
                    companyName = fileName.name
                    For i = 1 To strfilenameNum
                        If (StrFileName(i) <> "" And Trim(companyName) = StrFileName(i)) Then
                            companyName = StrFileNameblank(i)
                        End If
                    Next i
                    
                    remark = ""
                    
                    ' 圧縮ファイルを添付
                    remark = TargetFileZip(SubFolder, str_Pass)
                    'remark = SubFolder & "\" & companyName & ".zip"
                    If (remark <> "") Then
                        For rowid = 4 To endRowNo
                            If mastersheet.Cells(rowid, columnCount + 2).Value = "○" And mastersheet.Cells(rowid, columnCount + 2).Value = "○" And companyName = mastersheet.Range("bm" & rowid).Value Then
                                staffname = mastersheet.Range("aw" & rowid).Value
                                email = mastersheet.Range("be" & rowid).Value
                                Exit For
                            End If
                        Next
                        If (saleYearMonth = "") Then
                            saleYearMonth = DateSerial(yText, mText, 1)
                        End If
                '        content = companyName & vbLf & staffname & "様" & vbLf & "いつも大変お世話になっております。" & vbLf & _
                        "ＴＳＲソリューションズ購買担当でございます。" & vbLf & vbLf & BigMonth(month(saleYearMonth)) & "月度請求書をお送りさせていただきます｡" & _
                        "恐れ入りますが、内容のご確認をお願い致します。" & vbLf & vbLf & "また、原本は別途郵送させていただきます。" & _
                        vbLf & vbLf & "以上、引き続き、今後とも宜しくお願い申し上げます。"
                        content = companyName
                        content = content & vbNewLine & staffname & "様"
                        content = content & vbNewLine
                        content = content & mailGrtgTxt
                        'content = content & vbNewLine & BigMonth(month(saleYearMonth)) & "月度請求書をお送りさせていただきます｡"
                        content = content & vbNewLine & month(saleYearMonth) & "月度請求書をお送りさせていただきます｡"
                        content = content & vbNewLine & "※パスワードは別途送付させていただきます｡"
                        content = content & vbNewLine
                        content = content & vbNewLine & "恐れ入りますが､内容のご確認をお願い致します｡"
                        content = content & vbNewLine & "また､原本は別途郵送させていただきます｡"
                        content = content & vbNewLine
                        content = content & vbNewLine & "以上､引き続き､今後とも宜しくお願い申し上げます｡"
                        content = content & mailFooter
                        Call setSendFormList(companyName, content, remark, staffname, email, id, "1")
                        'End If
                        id = id + 1
                        titileID = titileID + 1
                        '２行目セット
                        remark = ""
                        'setTempPass = createBillZipPass(saleYearMonth)
                        content = companyName
                        content = content & vbNewLine & staffname & "様"
                        content = content & vbNewLine
                        content = content & mailGrtgTxt
                        content = content & vbNewLine & "先ほどのパスワード: " & str_Pass
                        content = content & vbNewLine
                        content = content & vbNewLine & "以上､宜しくお願い致します｡"
                        content = content & mailFooter
                        Call setSendFormList(companyName, content, remark, staffname, email, id, "2")
                        id = id + 1
                    End If
                End If
            Next
            
            hisfile.Save
            hisfile.Close
            Set hisfile = Nothing
            
            If (addFlg = False) Then
                Kill savePath & "\送付状_メール発送.xls"
            End If
        End If
    End If
    
    Application.DisplayAlerts = False
    Application.Workbooks("月次処理マクロ.xls").Worksheets("master").Delete
    Application.Workbooks("月次処理マクロ.xls").Save
    Unload UserForm3
    
    If monthProcessFlg = True Then
        MsgBox "月次処理完了、下記の出力先に確認してください" & Chr(10) & savePath
    'Else
    ElseIf monthProcessFlg = False And TextBox2.Text <> "" And TextBox1.Text = "" Then
        MsgBox "月次処理完了、下記の出力先に確認してください" & Chr(10) & savePath
    Else
        MsgBox "TSRS帳票管理マスタで月次処理の実施対象を指定してください。"
    End If
    
    Application.Workbooks("月次処理マクロ.xls").Close
    
    Exit Sub
    
Err_Handle:
    Unload UserForm3
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'メール用 定型挨拶文、フッター作成
Private Sub createMailFixText()
    
    '挨拶文
    mailGrtgTxt = vbNewLine & "いつも大変お世話になっております｡"
    mailGrtgTxt = mailGrtgTxt & vbNewLine & "TSRソリューションズ購買担当でございます｡"
    mailGrtgTxt = mailGrtgTxt & vbNewLine
    
    'フッター
    mailFooter = vbNewLine
    mailFooter = mailFooter & vbNewLine & "━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    mailFooter = mailFooter & vbNewLine & "ＴＳＲソリューションズ株式会社【 TSR Solutions,. Inc. 】"
    mailFooter = mailFooter & vbNewLine & "購買部"
    mailFooter = mailFooter & vbNewLine & "────────────────────────────"
    mailFooter = mailFooter & vbNewLine & "〒169-0075"
    mailFooter = mailFooter & vbNewLine & "東京都新宿区高田馬場3-23-3   ＯＲビル3-4F"
    mailFooter = mailFooter & vbNewLine & "TEL. 03-5937-5378（代表）  　FAX.  03-5937-5379"
    mailFooter = mailFooter & vbNewLine & "URL. http://www.tsrs.co.jp/　MAIL. report@tsrs.co.jp"
    mailFooter = mailFooter & vbNewLine
    mailFooter = mailFooter & vbNewLine & "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓"
    mailFooter = mailFooter & vbNewLine & " このメール及び添付ファイルに含まれる情報は機密内容"
    mailFooter = mailFooter & vbNewLine & " を含んでいる場合があり､意図された受取人以外の方によ"
    mailFooter = mailFooter & vbNewLine & " るこれら情報の無断開示､複製､再配布や転送など一切の"
    mailFooter = mailFooter & vbNewLine & " 利用が禁止されています｡万一この電子メールを誤って受"
    mailFooter = mailFooter & vbNewLine & " け取られた場合､誠に恐縮ですが､送信者にご連絡頂き､"
    mailFooter = mailFooter & vbNewLine & " 直ちに本メールを破棄して頂きますようお願い申し上げます｡"
    mailFooter = mailFooter & vbNewLine & " This e-mail is confidential and intended for the"
    mailFooter = mailFooter & vbNewLine & " named  recipient only.If you are not an intended"
    mailFooter = mailFooter & vbNewLine & " recipient,  please notify the sender and delete"
    mailFooter = mailFooter & vbNewLine & " this e-mail."
    mailFooter = mailFooter & vbNewLine & "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛"
    
End Sub

'請求書圧縮ファイル用パスフレーズ作成
Private Function createBillZipPass(ByVal setTargetDate) As String
    Const passTxt = "seikyu"
    createBillZipPass = passTxt & Right(Year(setTargetDate), 2) & Format(month(setTargetDate), "00")
End Function

'送付状(書類)内容を作成する
Private Sub setSendFormPrintInfo(ByVal rowid, ByVal wkbName, ByVal filecount)
On Error GoTo Err_Handle
    Dim sendFormSheet
    Const adrsSplitPos = 22
    Set sendFormSheet = Application.Workbooks(wkbName).Worksheets("書類送付状")
    
    sendFormSheet.Range("i16").Value = Format(Date, "ggge年ooood日")
    '郵便番号
    sendFormSheet.Range("b2").Value = "〒" & mastersheet.Range("BP" & rowid).Value
    '連絡先住所
    setTempAdrs = mastersheet.Range("BQ" & rowid).Value
'    If Len(setTempAdrs) > adrsSplitPos Then
'        For ltrSrchCnt = 1 To Len(setTempAdrs)
'            If IsNumeric(Mid(setTempAdrs, ltrSrchCnt, 1)) Then
'                Exit For
'            End If
'        Next ltrSrchCnt
'        If ltrSrchCnt > adrsSplitPos Then
'            setTempAdrs1 = Left(setTempAdrs, adrsSplitPos)
'            setTempAdrs2 = Mid(setTempAdrs, adrsSplitPos + 1)
'        Else
'            setTempAdrs1 = Left(setTempAdrs, ltrSrchCnt - 1)
'            setTempAdrs2 = Mid(setTempAdrs, ltrSrchCnt)
'        End If
'    Else
        setTempAdrs1 = setTempAdrs
        setTempAdrs2 = ""
'    End If
    sendFormSheet.Range("c2").Value = setTempAdrs1
    sendFormSheet.Range("b3").Value = setTempAdrs2
    '上位会社
    sendFormSheet.Range("b5").Value = mastersheet.Range("BM" & rowid).Value
    '上位会社担当
    sendFormSheet.Range("b6").Value = mastersheet.Range("AX" & rowid).Value & " " & mastersheet.Range("AW" & rowid).Value
    '上位会社担当
    sendFormSheet.Range("b17").Value = mastersheet.Range("AX" & rowid).Value & " " & mastersheet.Range("AW" & rowid).Value
    '
    sendFormSheet.Range("c8").Value = "請求書、作業報告書"
    '作業報告書部数
    sendFormSheet.Range("i34").Value = filecount
    '請求書部数
    sendFormSheet.Range("i35").Value = filecount
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'上位会社フォルダを作成する
Private Sub MakeCompanyFolder(ByVal savePath, ByVal filePath, ByVal companyId)
On Error GoTo Err_Handle
    '上位会社フォルダが存在しないの場合
'    company = "(" & companyId & ")" & filePath
    If Dir(savePath & "\" & filePath, vbDirectory) = "" Then
        MkDir savePath & "\" & filePath
        '送付状履歴保存
        'If chkPrint.Value = True Or chkFax.Value = True Then
            'Dim result As ADODB.Recordset
            'Dim sql
            'sql = "insert into tb_letter_history (COMPANY_ID,MONTH) values " _
            '& "(" & companyId & "," & month(Date) & ")"
            'Set result = DBUtility.Query(sql)
            'Set result = Nothing
            'DBUtility.CloseConnection
        'End If
    End If
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'送付状(fax)内容を作成する
Private Sub setSendFormFaxInfo(ByVal rowid, ByVal wkbName, ByVal filecount)
On Error GoTo Err_Handle
    Dim sendFormSheet, strRemark
    Set sendFormSheet = Application.Workbooks(wkbName).Worksheets("ＦＡＸ送付状")
    sendFormSheet.Range("i6").Value = Format(Date, "ggge年ooood日")
    '上位会社
    sendFormSheet.Range("b8").Value = mastersheet.Range("BM" & rowid).Value
    '担当者役職
    sendFormSheet.Range("b9").Value = mastersheet.Range("AX" & rowid).Value & mastersheet.Range("AY" & rowid).Value
    '上位会社担当
    sendFormSheet.Range("c9").Value = mastersheet.Range("AW" & rowid).Value
    'FAX番号
    sendFormSheet.Range("c11").Value = mastersheet.Range("BC" & rowid).Value
    '電話番号
    sendFormSheet.Range("c12").Value = mastersheet.Range("BB" & rowid).Value
    '送付備考
    strRemark = mastersheet.Range("BR" & rowid).Value
    If strRemark = "■至急！" Then
        sendFormSheet.Range("a22").Value = "■至急！　□ご確認ください　□折り返しご連絡ください　□ご参考まで"
    ElseIf strRemark = "■ご確認ください" Then
        sendFormSheet.Range("a22").Value = "□至急！　■ご確認ください　□折り返しご連絡ください　□ご参考まで"
    ElseIf strRemark = "■折り返しご連絡ください" Then
        sendFormSheet.Range("a22").Value = "□至急！　□ご確認ください　■折り返しご連絡ください　□ご参考まで"
    Else
        sendFormSheet.Range("a22").Value = "□至急！　□ご確認ください　□折り返しご連絡ください　■ご参考まで"
    End If
    '本社担当
    sendFormSheet.Range("g18").Value = mastersheet.Range("AO" & rowid).Value
    '作業報告書部数
    sendFormSheet.Range("i37").Value = filecount
    '請求書部数
    sendFormSheet.Range("i38").Value = filecount
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'検収通知書の送付状(fax)内容を作成する
Private Sub setSendFormFaxInfo_ken(ByVal rowid, ByVal wkbName, ByVal filecount)
On Error GoTo Err_Handle
    Dim sendFormSheet, strRemark
    Set sendFormSheet = Application.Workbooks(wkbName).Worksheets("ＦＡＸ送付状")
    sendFormSheet.Range("i6").Value = Format(Date, "ggge年ooood日")
    '上位会社
    sendFormSheet.Range("b8").Value = mastersheet.Range("D" & rowid).Value
    '担当者役職
    sendFormSheet.Range("b9").Value = mastersheet.Range("EO" & rowid).Value & mastersheet.Range("EP" & rowid).Value
    '上位会社担当
    sendFormSheet.Range("c9").Value = mastersheet.Range("EN" & rowid).Value
    'FAX番号
    sendFormSheet.Range("c11").Value = mastersheet.Range("EL" & rowid).Value
    '電話番号
    sendFormSheet.Range("c12").Value = mastersheet.Range("EM" & rowid).Value
    '送付備考
    strRemark = mastersheet.Range("BR" & rowid).Value
    If strRemark = "■至急！" Then
        sendFormSheet.Range("a22").Value = "■至急！　□ご確認ください　□折り返しご連絡ください　□ご参考まで"
    ElseIf strRemark = "■ご確認ください" Then
        sendFormSheet.Range("a22").Value = "□至急！　■ご確認ください　□折り返しご連絡ください　□ご参考まで"
    ElseIf strRemark = "■折り返しご連絡ください" Then
        sendFormSheet.Range("a22").Value = "□至急！　□ご確認ください　■折り返しご連絡ください　□ご参考まで"
    Else
        sendFormSheet.Range("a22").Value = "□至急！　□ご確認ください　□折り返しご連絡ください　■ご参考まで"
    End If
    '本社担当
    sendFormSheet.Range("g18").Value = mastersheet.Range("AO" & rowid).Value
    '作業報告書部数
    sendFormSheet.Range("C37").Value = "検収通知書"
    sendFormSheet.Range("i37").Value = filecount
    sendFormSheet.Range("B38").Value = ""
    sendFormSheet.Range("C38").Value = ""
    sendFormSheet.Range("J38").Value = ""
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'請求書内容を作成する
Private Function SetReqFlieInfo(ByVal rowid, ByVal wkbName, ByVal ReqID, ByVal workhour, ByVal paraMonth, ByVal planid)
On Error GoTo Err_Handle
    Dim reqFileSheet
    Set reqFileSheet = Application.Workbooks(wkbName).Worksheets("請求書")
    '請求番号
    reqFileSheet.Range("i2").Value = ReqID
    '提出日
    reqFileSheet.Range("i3").Value = Format(Date, "ggge年ooood日")
    '会社
    reqFileSheet.Range("b6").Value = mastersheet.Range("BM" & rowid).Value
    '案件名称
    reqFileSheet.Range("c19").Value = mastersheet.Range("AE" & rowid).Value
    
    ReqTime = mastersheet.Range("P" & rowid).Value
    ReqYear = Year(ReqTime)
    ReqMonth = month(ReqTime)
    Reqday = Day(ReqTime)
    If (mastersheet.Range("BG" & rowid).Value = "20日") Then
        If (ReqYear = Year(paraMonth)) Then
            If (ReqMonth = month(paraMonth) And Reqday < 21) Then
                '期間(開始)
                reqFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
            'ElseIf (ReqMonth = month(paraMonth) And Reqday > 20) Then
                '期間(開始)
                'kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                'kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 20)
            ElseIf (ReqMonth < month(paraMonth) And Reqday > 20) Then
                '期間(開始)
                reqFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
            Else
                '期間(開始)
                reqFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth) - 1, 21)
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
            End If
        ElseIf (ReqYear < Year(paraMonth) And Reqday > 20) Then
            '期間(開始)
                reqFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
        End If
        
        'If (ReqMonth = month(paraMonth) And Reqday < 21) Then
            '期間(開始)
            'reqFileSheet.Range("c20").Value = ReqTime
            '期間(終了)
            'reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
        'ElseIf (ReqMonth = month(paraMonth) And Reqday > 20) Then
            '期間(開始)
            'reqFileSheet.Range("c20").Value = ReqTime
            '期間(終了)
            'reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 20)
        'Else
            '期間(開始)
            'reqFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth) - 1, 21)
            '期間(終了)
            'reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
        'End If
    ElseIf (mastersheet.Range("BG" & rowid).Value = "25日") Then
        If (ReqYear = Year(paraMonth)) Then
            If (ReqMonth = month(paraMonth) And Reqday < 26) Then
                '期間(開始)
                reqFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
            'ElseIf (ReqMonth = month(paraMonth) And Reqday > 20) Then
                '期間(開始)
                'kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                'kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 20)
            ElseIf (ReqMonth < month(paraMonth) And Reqday > 25) Then
                '期間(開始)
                reqFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
            Else
                '期間(開始)
                reqFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth) - 1, 26)
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
            End If
        ElseIf (ReqYear < Year(paraMonth) And Reqday > 25) Then
            '期間(開始)
                reqFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
        End If
        'If (ReqMonth = month(paraMonth) And Reqday < 26) Then
            '期間(開始)
            'reqFileSheet.Range("c20").Value = ReqTime
            '期間(終了)
            'reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
        'ElseIf (ReqMonth = month(paraMonth) And Reqday > 25) Then
            '期間(開始)
            'reqFileSheet.Range("c20").Value = ReqTime
            '期間(終了)
            'reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 25)
        'Else
            '期間(開始)
            'reqFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth) - 1, 26)
            '期間(終了)
            'reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
        'End If
    ElseIf (mastersheet.Range("BG" & rowid).Value = "月末") Then
        If (ReqMonth = month(paraMonth) And Reqday > 1) Then
            '期間(開始)
            reqFileSheet.Range("c20").Value = ReqTime
            '期間(終了)
            reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 0)
        Else
            '期間(開始)
            reqFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth), 1)
            '期間(終了)
            reqFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 0)
        End If
    End If
    '作業内容・範囲
    reqFileSheet.Range("c21").Value = mastersheet.Range("AF" & rowid).Value
    '作業場所
    setTemp = "御社指定場所（" & mastersheet.Range("AG" & rowid).Value & "）"
    reqFileSheet.Range("c22").Value = setTemp
    '作業者名
    reqFileSheet.Range("b25").Value = mastersheet.Range("B" & rowid).Value
    '基準単価
    reqFileSheet.Range("c25").Value = mastersheet.Range("I" & rowid).Value
    
    '標準作業時間(下限)
    If mastersheet.Range("H" & rowid).Value = "" Then
        reqFileSheet.Range("d25").Value = ""
    ElseIf IsNumeric(mastersheet.Range("H" & rowid).Value) Then
        reqFileSheet.Range("d25").Formula = "=" & mastersheet.Range("H" & rowid).Value & " * " & "E27"
    Else
        reqFileSheet.Range("d25").Value = "固定"
    End If
    
    '標準作業時間(上限)
    If mastersheet.Range("G" & rowid).Value = "" Then
        reqFileSheet.Range("f25").Value = ""
    ElseIf IsNumeric(mastersheet.Range("G" & rowid).Value) Then
        reqFileSheet.Range("f25").Formula = "=" & mastersheet.Range("G" & rowid).Value & " * " & "E27"
    Else
        reqFileSheet.Range("f25").Value = "固定"
    End If

    '実績時間
    reqFileSheet.Range("d26").Value = Replace(workhour, ":", ".")
    '超過単価
    reqFileSheet.Range("g25").Value = mastersheet.Range("K" & rowid).Value
    '控除単価
    reqFileSheet.Range("h25").Value = mastersheet.Range("L" & rowid).Value
    '工数
    reqFileSheet.Range("E27").Formula = mastersheet.Range("X" & rowid).Value
    '金額=基準単価 * 工数
    'reqFileSheet.Range("I25").Formula = "=C25 * " & mastersheet.Range("X" & rowid).Value
    '交通費
    reqFileSheet.Range("c28").Value = mastersheet.Range("M" & rowid).Value
    '月数(交通費)→手入力
    'reqFileSheet.Range("d29").Value = masterSheet.Range("ae" & rowid).Value
    'オンライン当番
    reqFileSheet.Range("c31").Value = mastersheet.Range("N" & rowid).Value
    '日数(オンライン当番)→手入力
    'reqFileSheet.Range("f32").Value = masterSheet.Range("af" & rowid).Value
    'お支払期日
    If mastersheet.Range("BS" & rowid).Value <> "" Then
        reqFileSheet.Range("b48").Value = "お支払期日:" & month(mastersheet.Range("BS" & rowid).Value) & "月" & Day(mastersheet.Range("BS" & rowid).Value) & "日まで"
    Else
        reqFileSheet.Range("b48").Value = "お支払期日:"
    End If
    '税抜金額を取得
    mastersheet.Cells(rowid, columnCount + 7).NumberFormatLocal = "\#,##0"
    mastersheet.Cells(rowid, columnCount + 7).Value = reqFileSheet.Range("i40").Value
    '税込金額を取得
    mastersheet.Cells(rowid, columnCount + 9).NumberFormatLocal = "\#,##0"
    mastersheet.Cells(rowid, columnCount + 9).Value = reqFileSheet.Range("i42").Value
    '残業追加を取得
    mastersheet.Cells(rowid, columnCount + 12).NumberFormatLocal = "\#,##0"
    If reqFileSheet.Range("G26").Value = "" Then
        mastersheet.Cells(rowid, columnCount + 12).Value = 0
    Else
        mastersheet.Cells(rowid, columnCount + 12).Value = reqFileSheet.Range("G26").Value
    End If
    '勤怠控除を取得
    mastersheet.Cells(rowid, columnCount + 13).NumberFormatLocal = "\#,##0"
    If reqFileSheet.Range("H26").Value = "" Then
        mastersheet.Cells(rowid, columnCount + 13).Value = 0
    Else
        mastersheet.Cells(rowid, columnCount + 13).Value = reqFileSheet.Range("H26").Value
    End If
    '売上仕入れ一覧表
    reqFileSheet.Range("B50").Value = "≪社内稟議書≫" & Chr(10) & planid
    '営業担当
    reqFileSheet.Range("I50").Value = mastersheet.Range("AO" & rowid).Value
    
    SetReqFlieInfo = ReqID
    Exit Function
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Function

'***************************検収通知書の追加　WuYang 20160705 Start***********************************************
'検収通知書内容を作成する
Private Function SetkensyuFlieInfo(ByVal rowid, ByVal kensyuFileSheet, ByVal kenPBId, ByVal workhour, ByVal paraMonth)
On Error GoTo Err_Handle
    '検収番号
    kensyuFileSheet.Range("i2").Value = "検" & Format(Date, "YYMMDD") & Format(kenPBId, "000")
    '発 行 日
    kensyuFileSheet.Range("i3").Value = Format(Date, "ggge年ooood日")
    '管理番号
    kensyuFileSheet.Range("i4").Value = "管" & Format(Date, "YYMMDD") & Format(kenPBId, "000")
    'BP名
    kensyuFileSheet.Range("B6").Value = mastersheet.Range("D" & rowid).Value
    '案件名称
    kensyuFileSheet.Range("C19").Value = mastersheet.Range("AE" & rowid).Value
    '期間
    ReqTime = mastersheet.Range("P" & rowid).Value
    ReqYear = Year(ReqTime)
    ReqMonth = month(ReqTime)
    Reqday = Day(ReqTime)
    
    If (mastersheet.Range("T" & rowid).Value = "20日") Then
        If (ReqYear = Year(paraMonth)) Then
            If (ReqMonth = month(paraMonth) And Reqday < 21) Then
                '期間(開始)
                kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
            'ElseIf (ReqMonth = month(paraMonth) And Reqday > 20) Then
                '期間(開始)
                'kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                'kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 20)
            ElseIf (ReqMonth < month(paraMonth) And Reqday > 20) Then
                '期間(開始)
                kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
            Else
                '期間(開始)
                kensyuFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth) - 1, 21)
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
            End If
        ElseIf (ReqYear < Year(paraMonth) And Reqday > 20) Then
            '期間(開始)
                kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 20)
        End If
    ElseIf (mastersheet.Range("T" & rowid).Value = "25日") Then
        If (ReqYear = Year(paraMonth)) Then
            If (ReqMonth = month(paraMonth) And Reqday < 26) Then
                '期間(開始)
                kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
            'ElseIf (ReqMonth = month(paraMonth) And Reqday > 20) Then
                '期間(開始)
                'kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                'kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 20)
            ElseIf (ReqMonth < month(paraMonth) And Reqday > 25) Then
                '期間(開始)
                kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
            Else
                '期間(開始)
                kensyuFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth) - 1, 26)
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
            End If
        ElseIf (ReqYear < Year(paraMonth) And Reqday > 25) Then
            '期間(開始)
                kensyuFileSheet.Range("c20").Value = ReqTime
                '期間(終了)
                kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth), 25)
        End If
        
    ElseIf (mastersheet.Range("T" & rowid).Value = "月末") Then
        If (ReqMonth = month(paraMonth) And Reqday > 1) Then
            '期間(開始)
            kensyuFileSheet.Range("c20").Value = ReqTime
            '期間(終了)
            kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 0)
        Else
            '期間(開始)
            kensyuFileSheet.Range("c20").Value = DateSerial(Year(paraMonth), month(paraMonth), 1)
            '期間(終了)
            kensyuFileSheet.Range("f20").Value = DateSerial(Year(paraMonth), month(paraMonth) + 1, 0)
        End If
    End If
    '作業内容・範囲
    kensyuFileSheet.Range("C21").Value = mastersheet.Range("AF" & rowid).Value
    '作業場所
    setTemp = "弊社指定場所（" & mastersheet.Range("AG" & rowid).Value & "）"
    kensyuFileSheet.Range("c22").Value = setTemp
    '作業者名
    kensyuFileSheet.Range("B25").Value = mastersheet.Range("B" & rowid).Value
    '基準単価
    kensyuFileSheet.Range("C25").Value = mastersheet.Range("J" & rowid).Value
    '標準作業時間
    kensyuFileSheet.Range("D25").Value = mastersheet.Range("DE" & rowid).Value
    kensyuFileSheet.Range("F25").Value = mastersheet.Range("DD" & rowid).Value
    '超過単価
    kensyuFileSheet.Range("G25").Value = mastersheet.Range("DF" & rowid).Value
    '控除単価
    kensyuFileSheet.Range("H25").Value = mastersheet.Range("DG" & rowid).Value
    '実績時間
    kensyuFileSheet.Range("D26").Value = workhour
    '工数
    kensyuFileSheet.Range("E27").Value = mastersheet.Range("X" & rowid).Value
    '交通費
    kensyuFileSheet.Range("C28").Value = mastersheet.Range("M" & rowid).Value
    '経費
    kensyuFileSheet.Range("C31").Value = mastersheet.Range("N" & rowid).Value
    'お支払期日
    If mastersheet.Range("V" & rowid).Value <> "" Then
        kensyuFileSheet.Range("b48").Value = "支払期日:" & month(mastersheet.Range("V" & rowid).Value) & "月" & Day(mastersheet.Range("V" & rowid).Value) & "日まで"
    Else
        kensyuFileSheet.Range("b48").Value = "支払期日:"
    End If
    '営業担当
    kensyuFileSheet.Range("I50").Value = mastersheet.Range("AO" & rowid).Value
    
    Exit Function
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Function
'***************************検収通知書の追加　WuYang 20160705 End***********************************************

'未回収一覧内容を作成する
Private Sub setUnRecoverInfo(ByVal wkbName, ByVal errorid, ByVal rowid)
On Error GoTo Err_Handle
    Dim unRecoverSheet
    Set unRecoverSheet = Application.Workbooks("未回収一覧.xls").Worksheets(wkbName)
    
    If uncollectedRowId > 5 Then
        attendanceSheet.Rows(5).Copy
        attendanceSheet.Rows(uncollectedRowId).Insert
    End If
            
    '番号
    unRecoverSheet.Range("a" & errorid).Value = errorid - 4
    '社員ID
    unRecoverSheet.Range("b" & errorid).Value = mastersheet.Range("A" & rowid).Value
    unRecoverSheet.Range("b" & errorid).EntireColumn.AutoFit
    '社員名
    unRecoverSheet.Range("c" & errorid).Value = mastersheet.Range("B" & rowid).Value
    unRecoverSheet.Range("c" & errorid).EntireColumn.AutoFit
    '区分
    unRecoverSheet.Range("e" & errorid).Value = mastersheet.Range("E" & rowid).Value
    unRecoverSheet.Range("e" & errorid).EntireColumn.AutoFit
    'TEL（社員）
    unRecoverSheet.Range("f" & errorid).Value = mastersheet.Range("O" & rowid).Value
    unRecoverSheet.Range("f" & errorid).EntireColumn.AutoFit
    'メール（社員）
    unRecoverSheet.Range("g" & errorid).Value = mastersheet.Range("R" & rowid).Value
    unRecoverSheet.Range("g" & errorid).EntireColumn.AutoFit
    '上位会社名
    unRecoverSheet.Range("h" & errorid).Value = mastersheet.Range("BM" & rowid).Value
    unRecoverSheet.Range("h" & errorid).EntireColumn.AutoFit
    '上位会社ID
    unRecoverSheet.Range("i" & errorid).Value = mastersheet.Range("BL" & rowid).Value
    unRecoverSheet.Range("i" & errorid).EntireColumn.AutoFit
    '部署
    unRecoverSheet.Range("j" & errorid).Value = mastersheet.Range("AX" & rowid).Value
    unRecoverSheet.Range("j" & errorid).EntireColumn.AutoFit
    '担当名（上位会社）
    unRecoverSheet.Range("k" & errorid).Value = mastersheet.Range("AW" & rowid).Value
    unRecoverSheet.Range("k" & errorid).EntireColumn.AutoFit
    '読み
    unRecoverSheet.Range("l" & errorid).Value = mastersheet.Range("AZ" & rowid).Value
    'TEL（上位会社）
    unRecoverSheet.Range("m" & errorid).Value = mastersheet.Range("BB" & rowid).Value
    unRecoverSheet.Range("m" & errorid).EntireColumn.AutoFit
    'メール（上位会社）
    unRecoverSheet.Range("n" & errorid).Value = mastersheet.Range("BE" & rowid).Value
    unRecoverSheet.Range("n" & errorid).EntireColumn.AutoFit
    '提出締日
    'unRecoverSheet.Range("o" & errorid).Value = masterSheet.Range("AC" & rowid).Value
    'unRecoverSheet.Range("o" & errorid).EntireColumn.AutoFit
    'TSRS担当者
    unRecoverSheet.Range("p" & errorid).Value = mastersheet.Range("AO" & rowid).Value
    unRecoverSheet.Range("p" & errorid).EntireColumn.AutoFit
    '備考
    'unRecoverSheet.Range("q" & errorid).Value = masterSheet.Range("AD" & rowid).Value
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'稼動勤務勤怠集計表にレコードを追加
Private Sub SetAttendanceSheet(ByVal excelName, ByVal attendanceRowId, ByVal masterRowId, ByVal Flg As Boolean, ByVal yasumiDay)
On Error GoTo Err_Handle
    Dim attendanceSheet
    Set attendanceSheet = Application.Workbooks(excelName).Worksheets("勤怠集計表")
    
    If attendanceRowId > 6 Then
        attendanceSheet.Rows(6).Copy
        attendanceSheet.Rows(attendanceRowId).PasteSpecial Paste:=xlPasteAll
    End If
            
    '番号
    attendanceSheet.Range("A" & attendanceRowId).Value = attendanceRowId - 5
    '支払日
    If mastersheet.Range("V" & masterRowId).Value = "" Then
        attendanceSheet.Range("B" & attendanceRowId).Value = ""
    Else
        attendanceSheet.Range("B" & attendanceRowId).Value = mastersheet.Range("V" & masterRowId).Value
    End If
    
    '締日
    If mastersheet.Range("AH" & masterRowId).Value = "" Then
        attendanceSheet.Range("C" & attendanceRowId).Value = ""
    Else
        attendanceSheet.Range("C" & attendanceRowId).Value = mastersheet.Range("AH" & masterRowId).Value
    End If
    
    '本人支払いサイト
    attendanceSheet.Range("D" & attendanceRowId).Value = mastersheet.Range("DI" & masterRowId).Value
    '"ID番号コード"
    attendanceSheet.Range("E" & attendanceRowId).Value = mastersheet.Range("A" & masterRowId).Value
    attendanceSheet.Range("E" & attendanceRowId).EntireColumn.AutoFit
    '氏名
    attendanceSheet.Range("F" & attendanceRowId).Value = mastersheet.Range("B" & masterRowId).Value
    
    '内勤外勤
    attendanceSheet.Range("G" & attendanceRowId).Value = "外"
    
    '契約形態
    attendanceSheet.Range("H" & attendanceRowId).Value = mastersheet.Range("E" & masterRowId).Value
    '主担当
    attendanceSheet.Range("I" & attendanceRowId).Value = mastersheet.Range("AO" & masterRowId).Value
    
    '基本
    If mastersheet.Range("X" & masterRowId).Value < 1 Then
        workLoad = mastersheet.Range("X" & masterRowId).Value
    Else
        workLoad = 1
    End If
    '下限
    If mastersheet.Range("DE" & masterRowId).Value = "" Then
        If mastersheet.Range("H" & masterRowId).Value = "" Then
            attendanceSheet.Range("P" & attendanceRowId).Value = ""
        ElseIf IsNumeric(mastersheet.Range("H" & masterRowId).Value) Then
            attendanceSheet.Range("P" & attendanceRowId).Value = mastersheet.Range("H" & masterRowId).Value * workLoad
        Else
            attendanceSheet.Range("P" & attendanceRowId).Value = "固定"
        End If
    ElseIf IsNumeric(mastersheet.Range("DE" & masterRowId).Value) Then
        attendanceSheet.Range("P" & attendanceRowId).Value = mastersheet.Range("DE" & masterRowId).Value * workLoad
    Else
        attendanceSheet.Range("P" & attendanceRowId).Value = "固定"
    End If
    '上限
    If mastersheet.Range("DD" & masterRowId).Value = "" Then
        If mastersheet.Range("G" & masterRowId).Value = "" Then
            attendanceSheet.Range("Q" & attendanceRowId).Value = ""
        ElseIf IsNumeric(mastersheet.Range("DD" & masterRowId).Value) Then
            attendanceSheet.Range("Q" & attendanceRowId).Value = mastersheet.Range("G" & masterRowId).Value * workLoad
        Else
            attendanceSheet.Range("Q" & attendanceRowId).Value = "固定"
        End If
    ElseIf IsNumeric(mastersheet.Range("DD" & masterRowId).Value) Then
        attendanceSheet.Range("Q" & attendanceRowId).Value = mastersheet.Range("DD" & masterRowId).Value * workLoad
    Else
        attendanceSheet.Range("Q" & attendanceRowId).Value = "固定"
    End If
    
    '入社日
    attendanceSheet.Range("T" & attendanceRowId).Value = mastersheet.Range("U" & masterRowId).Value
    '交通費
    attendanceSheet.Range("AG" & attendanceRowId).Value = mastersheet.Range("M" & masterRowId).Value
    '経費
    attendanceSheet.Range("AH" & attendanceRowId).Value = mastersheet.Range("N" & masterRowId).Value
    '回収ステータス
    If mastersheet.Cells(masterRowId, columnCount + 2).Value = "○" Then
        attendanceSheet.Range("AJ" & attendanceRowId).Value = 5
    End If
    '回収ステータス
    attendanceSheet.Range("AK" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 2).Value
    '精算時間単位
    '************************************20160622 バグ修正 WuYang Start********************************************
    attendanceSheet.Range("AO" & attendanceRowId).Value = mastersheet.Range("DH" & masterRowId).Value
    '************************************20160622 バグ修正 WuYang End********************************************
    
    If Flg = True Then
        '欠勤日数
        attendanceSheet.Range("L" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 3).Value
        '遅・早日数
        'attendanceSheet.Range("M" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 4).Value
        '調整休日
        attendanceSheet.Range("M" & attendanceRowId).Value = yasumiDay
        '遅早時間
        attendanceSheet.Range("N" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 5).Value
        'attendanceSheet.Range("J" & attendanceRowId).Formula _
        '    = "=CEILING(" & masterSheet.Cells(masterRowId, columnCount + 5).Value & "/60,0.5)"
        '実働時間
        If (attendanceSheet.Range("AO" & attendanceRowId).Value <> 0) Then
            attendanceSheet.Range("O" & attendanceRowId).Value = WorksheetFunction.Floor(mastersheet.Cells(masterRowId, columnCount + 6).Value, _
            attendanceSheet.Range("AO" & attendanceRowId).Value / 60)
        Else
            attendanceSheet.Range("O" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 6).Value
        End If
        '出勤日数
        attendanceSheet.Range("K" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 8).Value
        '当月営業日数
        attendanceSheet.Range("J" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 10).Value
        '有休取得日数
        attendanceSheet.Range("Z" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 11).Value
        '残業追加
        TempOverPr = mastersheet.Range("DF" & masterRowId).Value
        TempUndrPr = mastersheet.Range("DG" & masterRowId).Value
        'attendanceSheet.Range("AB" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 14).Value
        overTime = attendanceSheet.Range("S" & attendanceRowId).Value
        attendanceSheet.Range("AB" & attendanceRowId).Value = TempOverPr * overTime
        '勤怠控除
        underTime = attendanceSheet.Range("R" & attendanceRowId).Value
        attendanceSheet.Range("AE" & attendanceRowId).Value = TempUndrPr * underTime
        'attendanceSheet.Range("AE" & attendanceRowId).Value = mastersheet.Cells(masterRowId, columnCount + 15).Value
        '基本給
        attendanceSheet.Range("AQ" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AQ" & attendanceRowId).Value = mastersheet.Range("CF" & masterRowId).Value
        '役職手当
        'attendanceSheet.Range("AR" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AR" & attendanceRowId).Value = mastersheet.Range("CY" & masterRowId).Value
        '住宅手当
        attendanceSheet.Range("AS" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AS" & attendanceRowId).Value = mastersheet.Range("CG" & masterRowId).Value
        '扶養手当
        attendanceSheet.Range("AT" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AT" & attendanceRowId).Value = mastersheet.Range("CZ" & masterRowId).Value
        '通信手当
        'attendanceSheet.Range("AU" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AU" & attendanceRowId).Value = mastersheet.Range("CH" & masterRowId).Value
        '現場手当
        'attendanceSheet.Range("AV" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AV" & attendanceRowId).Value = mastersheet.Range("DA" & masterRowId).Value
        '当番手当
        'attendanceSheet.Range("AW" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AW" & attendanceRowId).Value = mastersheet.Range("DB" & masterRowId).Value
        '残業手当
        'attendanceSheet.Range("AX" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AX" & attendanceRowId).Value = mastersheet.Range("CJ" & masterRowId).Value
        '資格手当
        'attendanceSheet.Range("AY" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AY" & attendanceRowId).Value = mastersheet.Range("CI" & masterRowId).Value
        'その他手当
        'attendanceSheet.Range("AZ" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("AZ" & attendanceRowId).Value = mastersheet.Range("DC" & masterRowId).Value
        '通勤手当(非)
        'attendanceSheet.Range("BA" & attendanceRowId).Value = "\#,##0"
        attendanceSheet.Range("BA" & attendanceRowId).Value = mastersheet.Range("ER" & masterRowId).Value
        '通勤経路
        If (mastersheet.Range("CM" & masterRowId).Value <> "") Then
            attendanceSheet.Range("BB" & attendanceRowId).Value = mastersheet.Range("CM" & masterRowId).Value & "の" & mastersheet.Range("CN" & masterRowId).Value & " ～ " & mastersheet.Range("CO" & masterRowId).Value & "の" & mastersheet.Range("CP" & masterRowId).Value
        End If
        If (mastersheet.Range("CR" & masterRowId).Value <> "") Then
            attendanceSheet.Range("BC" & attendanceRowId).Value = mastersheet.Range("CR" & masterRowId).Value & "の" & mastersheet.Range("CS" & masterRowId).Value & " ～ " & mastersheet.Range("CT" & masterRowId).Value & "の" & mastersheet.Range("CU" & masterRowId).Value
        End If
        If (mastersheet.Range("DJ" & masterRowId).Value <> "") Then
            attendanceSheet.Range("BD" & attendanceRowId).Value = mastersheet.Range("DJ" & masterRowId).Value & "の" & mastersheet.Range("DK" & masterRowId).Value & " ～ " & mastersheet.Range("DL" & masterRowId).Value & "の" & mastersheet.Range("DM" & masterRowId).Value
        End If
        If (mastersheet.Range("DO" & masterRowId).Value <> "") Then
            attendanceSheet.Range("BE" & attendanceRowId).Value = mastersheet.Range("DO" & masterRowId).Value & "の" & mastersheet.Range("DP" & masterRowId).Value & " ～ " & mastersheet.Range("DQ" & masterRowId).Value & "の" & mastersheet.Range("DR" & masterRowId).Value
        End If
        '保険
        attendanceSheet.Range("BG" & attendanceRowId).Value = mastersheet.Range("DU" & masterRowId).Value
    
    Else
         '欠勤日数
        attendanceSheet.Range("L" & attendanceRowId).Value = 0
        '遅・早日数
        attendanceSheet.Range("M" & attendanceRowId).Value = 0
        '遅早時間
        attendanceSheet.Range("N" & attendanceRowId).Value = 0
        '実働時間
        attendanceSheet.Range("O" & attendanceRowId).Value = 0
        '出勤日数
        attendanceSheet.Range("K" & attendanceRowId).Value = 0
        '当月営業日数
        attendanceSheet.Range("J" & attendanceRowId).Value = 0
        '有休取得日数
        attendanceSheet.Range("Z" & attendanceRowId).Value = 0
        '残業追加
        attendanceSheet.Range("AB" & attendanceRowId).Value = 0
        '勤怠控除
        attendanceSheet.Range("AE" & attendanceRowId).Value = 0
    End If
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'売上げ仕入れ管理台帳にレコードを追加
Private Sub SetSaleSheet(ByVal excelName, ByVal saleRowId, ByVal priceRowId, ByVal insentRowId, ByVal masterRowId, ByVal ReqIDTo)
On Error GoTo Err_Handle
    Dim saleSheet
    Set saleSheet = Application.Workbooks(excelName).Worksheets("IT" & month(Date) & "月")
    
    seqHdr = Year(Date) & month(Date) & "-"
    
    '=========================売上請求明細レコードを設定 Str=========================
    'レコードを追加
    If saleRowId > 8 Then
        saleSheet.Rows(8).Copy
        saleSheet.Rows(saleRowId).Insert
    End If
    
    'IDの値を設定
    'saleSheet.Range("A" & saleRowId).Value = seqHdr & Format(saleRowId - 7, "000000")
    saleSheet.Range("A" & saleRowId).Value = ReqIDTo
    '締日の値を設定
    If mastersheet.Range("AH" & masterRowId).Value = "" Then
        saleSheet.Range("B" & saleRowId).Value = ""
    Else
        saleSheet.Range("B" & saleRowId).Value = mastersheet.Range("AH" & masterRowId).Value
    End If
    
    '補助の値を設定
    saleSheet.Range("C" & saleRowId).Value = mastersheet.Range("BJ" & masterRowId).Value
    
    '次のPJの値を設定
    saleSheet.Range("D" & saleRowId).Value = mastersheet.Range("AJ" & masterRowId).Value
    
    '案件名
    saleSheet.Range("E" & saleRowId).Value = mastersheet.Range("AE" & masterRowId)
    saleSheet.Range("E" & saleRowId).EntireColumn.AutoFit
    
    'ID会社番号”会社コードド”に上位会社IDを設定
    saleSheet.Range("F" & saleRowId).Value = mastersheet.Range("BL" & masterRowId).Value
    
    '得意先名に上位会社名を設定
    saleSheet.Range("G" & saleRowId).Value = mastersheet.Range("BM" & masterRowId).Value
    
    '氏名に社員名を設定
    saleSheet.Range("H" & saleRowId).Value = mastersheet.Range("B" & masterRowId).Value
    
    '税抜金額の値を設定
    saleSheet.Range("I" & saleRowId).Value = mastersheet.Cells(masterRowId, columnCount + 7).Value
    saleSheet.Range("I" & saleRowId).NumberFormatLocal = accntDispForm
    '税込金額の値を設定
    saleSheet.Range("K" & saleRowId).Value = mastersheet.Cells(masterRowId, columnCount + 9).Value
    saleSheet.Range("K" & saleRowId).NumberFormatLocal = accntDispForm
    
    '入金予定日の値を設定
    If mastersheet.Range("AL" & masterRowId).Value = "" Then
        saleSheet.Range("J" & saleRowId).Value = ""
    Else
        saleSheet.Range("J" & saleRowId).Value = mastersheet.Range("AL" & masterRowId).Value
    End If
    '仕入額
    saleSheet.Range("T" & saleRowId).Value = (mastersheet.Range("W" & masterRowId).Value) * (mastersheet.Range("X" & masterRowId).Value)
    saleSheet.Range("T" & saleRowId).NumberFormatLocal = accntDispForm
    '交通費
    saleSheet.Range("R" & saleRowId).NumberFormatLocal = accntDispForm
    saleSheet.Range("R" & saleRowId).Value = mastersheet.Range("M" & masterRowId).Value
    '経費合計
    saleSheet.Range("Q" & saleRowId).NumberFormatLocal = accntDispForm
    saleSheet.Range("Q" & saleRowId).Value = mastersheet.Range("N" & masterRowId).Value
    
    '*******************************************項目追加対応 20160613 WuYang Start*********************************************
    '営業主担当
    saleSheet.Range("W" & saleRowId).Value = mastersheet.Range("AO" & masterRowId).Value
    '要員ID
    saleSheet.Range("X" & saleRowId).Value = mastersheet.Range("A" & masterRowId).Value
    '工数
    saleSheet.Range("Z" & saleRowId).Value = mastersheet.Range("X" & masterRowId).Value
    '計上月
    saleSheet.Range("AA" & saleRowId).Value = mastersheet.Range("BK" & masterRowId).Value
    '*******************************************項目追加対応 20160613 WuYang End*********************************************
    
    '=========================売上請求明細レコードを設定 End=========================
       
    '=========================原価明細レコードを設定 Str=========================
    'レコードを追加
    If priceRowId > (22 + (saleRowId - 8)) Then
        saleSheet.Rows(22 + (saleRowId - 8)).Copy
        saleSheet.Rows(priceRowId).Insert
    End If
    
    'IDの値を設定
    saleSheet.Range("A" & priceRowId).Value = saleSheet.Range("A" & saleRowId).Value
    
    '締日の値を設定
    If mastersheet.Range("AM" & masterRowId).Value = "" Then
        saleSheet.Range("B" & priceRowId).Value = ""
    Else
        saleSheet.Range("B" & priceRowId).Value = mastersheet.Range("AM" & masterRowId).Value
    End If
    
    '補助の値を設定
    saleSheet.Range("C" & priceRowId).Value = mastersheet.Range("BJ" & masterRowId).Value
    
    '次のPJの値を設定
    saleSheet.Range("D" & priceRowId).Value = mastersheet.Range("AK" & masterRowId).Value
    
    '案件名
    saleSheet.Range("E" & priceRowId).Value = mastersheet.Range("AE" & masterRowId)
    saleSheet.Range("E" & priceRowId).EntireColumn.AutoFit
    
    'ID会社番号”会社コードド”に上位会社IDを設定
    saleSheet.Range("F" & priceRowId).Value = mastersheet.Range("A" & masterRowId).Value
    
    '得意先名に上位会社名を設定
    saleSheet.Range("G" & priceRowId).Value = mastersheet.Range("D" & masterRowId).Value
    
    '氏名に社員名を設定
    saleSheet.Range("H" & priceRowId).Value = mastersheet.Range("B" & masterRowId).Value
    
    '社保加算後の値を設定
    '*************************バグの修正 WuYang 2016/06/06 Start*****************************
    'culcTempAdd = mastersheet.Cells(masterRowId, columnCount + 14).Value
    'culcTempSub = mastersheet.Cells(masterRowId, columnCount + 15).Value
    'If mastersheet.Range("DU" & masterRowId).Value = "有" Then
        'culcTempBase = mastersheet.Range("BH" & masterRowId).Value
        'If culcTempBase = "" Then
            'culcTempBase = mastersheet.Range("W" & masterRowId).Value * mastersheet.Range("X" & masterRowId).Value
       ' End If
    'Else
        'culcTempBase = mastersheet.Range("J" & masterRowId).Value
   ' End If
    'If (IsNumeric(Trim(culcTempBase)) = False) Then
        'culcTempBase = Mid(culcTempBase, 2, Len(culcTempBase))
    'End If
    
    'If (Trim(culcTempAdd) = "" Or IsNull(culcTempAdd)) Then
        'culcTempAdd = 0
    'End If
    
    'If (Trim(culcTempSub) = "" Or IsNull(culcTempSub)) Then
        'culcTempSub = 0
    'End If
    
    'saleSheet.Range("I" & priceRowId).Value = Format(culcTempBase, "General Number") + Format(culcTempAdd, "General Number") - Format(culcTempSub, "General Number")
     saleSheet.Range("I" & priceRowId).Value = (mastersheet.Range("W" & masterRowId).Value) * (mastersheet.Range("X" & masterRowId).Value)
     saleSheet.Range("I" & priceRowId).NumberFormatLocal = accntDispForm
     '工数
     saleSheet.Range("W" & priceRowId).Value = mastersheet.Range("X" & masterRowId).Value
    '*************************バグの修正 WuYang 2016/06/13 End*****************************
    '出金予定日
    If mastersheet.Range("V" & masterRowId).Value = "" Then
        saleSheet.Range("J" & priceRowId).Value = ""
    Else
        saleSheet.Range("J" & priceRowId).Value = mastersheet.Range("V" & masterRowId).Value
    End If
    '*******************************************項目追加対応 20160613 WuYang Start*********************************************
    '契約形態
    saleSheet.Range("S" & priceRowId).Value = mastersheet.Range("E" & masterRowId).Value
    '営業主担当
    saleSheet.Range("U" & priceRowId).Value = mastersheet.Range("AO" & masterRowId).Value
    '*******************************************項目追加対応 20160613 WuYang End*********************************************
    '=========================原価明細レコードを設定 End=========================
    
    '粗利額
    saleSheet.Range("U" & saleRowId).Value = saleSheet.Range("I" & saleRowId).Value - saleSheet.Range("I" & priceRowId).Value
    saleSheet.Range("U" & saleRowId).NumberFormatLocal = accntDispForm
    saleSheet.Range("AB" & saleRowId).Value = saleSheet.Range("J" & priceRowId).Value - saleSheet.Range("J" & saleRowId).Value
    '=========================インセンティブ明細レコードを設定 Str=========================
    'レコードを追加
    If insentRowId > (32 + ((saleRowId - 8) * 2)) Then
        saleSheet.Rows(32 + ((saleRowId - 8) * 2)).Copy
        saleSheet.Rows(insentRowId).Insert
    End If
    
    'IDの値を設定
    saleSheet.Range("A" & insentRowId).Value = saleSheet.Range("A" & saleRowId).Value
    
    '締日の値を設定
    If mastersheet.Range("AH" & masterRowId).Value = "" Then
        saleSheet.Range("B" & insentRowId).Value = ""
    Else
        saleSheet.Range("B" & insentRowId).Value = mastersheet.Range("AH" & masterRowId).Value
    End If
    
    '契約状態
    saleSheet.Range("C" & insentRowId).Value = mastersheet.Range("AN" & masterRowId).Value
    
    '主担当営業の値を設定
    saleSheet.Range("D" & insentRowId).Value = mastersheet.Range("AO" & masterRowId).Value
    
    '案件名
    saleSheet.Range("E" & insentRowId).Value = mastersheet.Range("AE" & masterRowId)
    saleSheet.Range("E" & insentRowId).EntireColumn.AutoFit
    
    'ID番号コード”に社員IDを設定
    saleSheet.Range("F" & insentRowId).Value = mastersheet.Range("BL" & masterRowId).Value
    
    '得意先名に所属会社名を設定
    saleSheet.Range("G" & insentRowId).Value = mastersheet.Range("BM" & masterRowId).Value
    
    '氏名に社員名を設定
    saleSheet.Range("H" & insentRowId).Value = mastersheet.Range("B" & masterRowId).Value
    
    '税抜金額の値を設定
    saleSheet.Range("I" & insentRowId).Value = mastersheet.Cells(masterRowId, columnCount + 7).Value
    saleSheet.Range("I" & insentRowId).NumberFormatLocal = accntDispForm
    '仕入の値を設定
    saleSheet.Range("J" & insentRowId).Value = (mastersheet.Range("W" & masterRowId).Value) * (mastersheet.Range("X" & masterRowId).Value)
    saleSheet.Range("J" & insentRowId).NumberFormatLocal = accntDispForm
    '=========================インセンティブ明細レコードを設定 End=========================
    If (saleSheet.Range("U" & saleRowId).Value <= 0) Then
        saleSheet.Range("U" & saleRowId).Interior.ColorIndex = 3
        saleSheet.Range("V" & saleRowId).Interior.ColorIndex = 3
    End If
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

'送付状_履歴_発送処理一覧
Private Sub setSendFormList(ByVal company, ByVal content, ByVal remark, ByVal staff, ByVal email, ByVal rowid, ByVal titleID)
On Error GoTo Err_Handle
    Dim sendformHisSheet
    
    Set sendformHisSheet = Application.Workbooks("送付状_メール発送.xls").Worksheets("送付状_発送履歴")
    '会社名
    sendformHisSheet.Range("a" & rowid).Value = company
    '種類
    sendformHisSheet.Range("b" & rowid).Value = "請求書" & "-" & "作業報告書"
    'タイトル
    sendformHisSheet.Range("c" & rowid).Value = "「TSRS」月次請求" & titleID
    '内容
    sendformHisSheet.Range("d" & rowid).Value = content
    sendformHisSheet.Range("d" & rowid).WrapText = False
    '備考
    sendformHisSheet.Range("e" & rowid).Value = remark
    sendformHisSheet.Range("e" & rowid).WrapText = False
    '担当者
    sendformHisSheet.Range("f" & rowid).Value = staff
    'メール
    sendformHisSheet.Range("g" & rowid).Value = email
    'cc
    sendformHisSheet.Range("h" & rowid).Value = "report@tsrs.co.jp"
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "月次処理失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

Private Sub CommandButton4_Click()
    dataForm.Show
End Sub

Private Sub CommandButton5_Click()
    With Application.FileDialog(msoFileDialogFilePicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox2.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
End Sub

'作業報告書テンプレート生成
Private Sub makeReport_Click()
    Dim templatePath, savePath, saveReportName, mastersheet, staffCount, mainSheet, reportDate, reportSheet
    templatePath = Application.ActiveWorkbook.path & "\テンプレート\月処理必要テンプレート\"
    
    Set mastersheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    staffCount = mastersheet.UsedRange.Rows(mastersheet.UsedRange.Rows.count).Row
    
    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    '作業報告書フォルダの作成
    If Dir(savePath & "\作業報告書", vbDirectory) = "" Then
        MkDir savePath & "\作業報告書"
    End If
        
    '作業報告書の作成----------------------------------------------------------------
    Set mainSheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("Main")
    reportDate = Trim(mainSheet.txtYear.Text) & "/" & mainSheet.cmbMonth.Value & "/01"
    For i = 4 To staffCount
        If mastersheet.Range("A" & i).Value <> "" Then
            tTimeS = TimeValue(mastersheet.Range("cw" & i).Value)
            tTimeE = TimeValue(mastersheet.Range("cx" & i).Value)
            tTimeR = TimeValue(TimeSerial(0, mastersheet.Range("ce" & i).Value, 0))
            workBaseTime = tTimeE - tTimeS - tTimeR
            saveReportName = "作業報告書_" & mastersheet.Range("B" & i).Value
            saveReportName = saveReportName & "(" & mastersheet.Range("A" & i).Value & ")"
            saveReportName = saveReportName & "_" & mastersheet.Range("F" & i).Value & ".xls"
            FileCopy templatePath & "作業報告書.xls", savePath & "\作業報告書\" & saveReportName
            Workbooks.Open fileName:=savePath & "\作業報告書\" & saveReportName
            Set reportSheet = ActiveWorkbook.Sheets("作業報告書")
            reportSheet.Range("A2").Value = reportDate
            reportSheet.Range("AQ4").Value = mastersheet.Range("A" & i).Value
            reportSheet.Range("K4").Value = mastersheet.Range("B" & i).Value
            
            For iCount = 9 To 39
                If (reportSheet.Range("D" & iCount).Value <> "") Then
                    reportSheet.Range("AK" & iCount).Value = TimeSerial(Hour(workBaseTime), Minute(workBaseTime), 0)
                    If IsNumeric(Application.Weekday(reportSheet.Range("D" & iCount).Value)) Then
                        If Application.Weekday(reportSheet.Range("D" & iCount).Value) = 1 _
                                Or Application.Weekday(reportSheet.Range("D" & iCount).Value) = 7 Then
                            reportSheet.Range("D" & iCount).Font.ColorIndex = 3
                        End If
                    End If
                End If
            Next
            
            ActiveWorkbook.Save
            ActiveWorkbook.Close
        End If
    Next
    
    MsgBox "作業報告書を生成完了、下記の出力先に確認してください" & Chr(10) & savePath & "\作業報告書"
End Sub

'SE管理台帳の最新稼動管理「確定分」を作成
Private Sub SetSeManagerListInfo(ByVal id, ByVal rowid)
    Dim managerSheet
    Set managerSheet = Application.Workbooks("SE管理台帳.xls").Worksheets("最新稼動管理-1")
    
    If id > 12 Then
        managerSheet.Rows(10).Copy
        managerSheet.Rows(id).Insert
    End If
    
    '番号
    managerSheet.Range("B" & id).Value = id - 9
    managerSheet.Range("B" & id).EntireColumn.AutoFit
    'ステータス
    managerSheet.Range("C" & id).Value = "稼働中"
    '顧客名
    managerSheet.Range("D" & id).Value = mastersheet.Range("BM" & rowid)
    '担当者名
    managerSheet.Range("E" & id).Value = mastersheet.Range("AW" & rowid)
    'Mail Address
    managerSheet.Range("F" & id).Value = mastersheet.Range("BD" & rowid)
    '住所
    managerSheet.Range("G" & id).Value = mastersheet.Range("BQ" & rowid)
    '案件名
    managerSheet.Range("H" & id).Value = mastersheet.Range("AE" & rowid)
    '社員名
    managerSheet.Range("I" & id).Value = mastersheet.Range("B" & rowid)
    '雇用形態
    If mastersheet.Range("E" & rowid) <> "" Then
        If mastersheet.Range("E" & rowid) = "正社員" Then
            managerSheet.Range("J" & id).Value = "正"
        Else
            managerSheet.Range("J" & id).Value = "契"
        End If
    End If

    '注文書受領日
    managerSheet.Range("L" & id).Value = mastersheet.Range("AQ" & rowid)
    '営業主担当
    managerSheet.Range("M" & id).Value = mastersheet.Range("AO" & rowid)
    '副担当
    managerSheet.Range("N" & id).Value = mastersheet.Range("AP" & rowid)
    '契約開始日
    managerSheet.Range("O" & id).Value = mastersheet.Range("P" & rowid)
    '契約終了日
    managerSheet.Range("P" & id).Value = mastersheet.Range("Q" & rowid)
    '受注予定金額
    managerSheet.Range("Q" & id).Value = mastersheet.Range("I" & rowid).Value
    managerSheet.Range("Q" & id).EntireColumn.AutoFit
    '原価
    managerSheet.Range("R" & id).Value = mastersheet.Range("J" & rowid).Value
    
End Sub

'SE管理台帳の最新稼動管理「案件終了」を作成
Private Sub SetSeManagerProjectsEndInfo(ByRef rowid As Integer)
    Dim rst As ADODB.Recordset, sql, rstFiltered As ADODB.Recordset, col, nowMonth, projectId As Integer, endProId, endProNo As Integer
    
    'SQLを実施して、検索結果を取得
    sql = SQLConstant.SEARCH_CASEEND_INFO
    Set rst = DBUtility.Query(sql)

    If Not rst Is Nothing Then
        If Not (rst.EOF) Then
            nowMonth = month(Date)
            
            '「案件終了」の最初行の行番号を設定
            If rowid + 2 > 14 Then
                projectId = rowid + 2
                endProId = rowid + 6
            Else
                projectId = 14
                endProId = 18
            End If
            endProNo = 0 '「案件終了」の件数
            
            Dim i, j
            For i = nowMonth To 12
                rst.Filter = "JOB_END_DATE >= " & Year(Date) & "-" & Right("0" & i, 2) & "-01 and " _
                           & "JOB_END_DATE <= " & Year(Date) & "-" & Right("0" & i, 2) & "-" & GetMonthEnd(Year(Date), i)
                Set rstFiltered = rst
                If Not (rstFiltered.EOF) Then
                    Call SetProjectsEndInfo(rstFiltered, projectId, endProId, endProNo, i)
                End If
            Next
            
            If nowMonth - 1 > 0 Then
            For j = 1 To nowMonth - 1
                rst.Filter = "JOB_END_DATE >= " & Year(Date) & "-" & Right("0" & j, 2) & "-01 and " _
                           & "JOB_END_DATE <= " & Year(Date) & "-" & Right("0" & j, 2) & "-" & GetMonthEnd(Year(Date), i)
                
                Set rstFiltered = rst
                If Not (rstFiltered.EOF) Then
                    Call SetProjectsEndInfo(rstFiltered, projectId, endProId, endProNo, j)
                End If
            Next
            End If
            rowid = projectId
        End If
    End If
    

End Sub

'「案件終了」にレコードを追加
Private Sub SetProjectsEndInfo(ByVal rstFiltered, ByRef projectId As Integer, ByVal endProId, ByRef endProNo As Integer, ByVal pMonth)
    Dim endProSheet, startRowNo
    Set endProSheet = Application.Workbooks("SE管理台帳.xls").Worksheets("最新稼動管理-1")
    
    projectId = projectId + 1
    startRowNo = projectId
    endProSheet.Range("D" & projectId).Value = pMonth & "月末案件終了予定"
    
    While Not (rstFiltered.EOF)
        projectId = projectId + 1
        If projectId > endProId Then
            endProSheet.Rows(endProId - 4).Copy
            endProSheet.Rows(projectId).Insert
        End If
        
        '行番号
        endProNo = endProNo + 1
        endProSheet.Range("B" & projectId).Value = endProNo
        endProSheet.Range("B" & projectId).EntireColumn.AutoFit
        
        'ステータス
        endProSheet.Range("C" & projectId).Value = ""
        
        For col = 0 To rstFiltered.Fields.count - 1
            '顧客名
            If rstFiltered.Fields(col).name = "COMPANY_NAME" Then
                endProSheet.Range("D" & projectId).Value = rstFiltered.Fields(col).Value
    
            '担当者名
            ElseIf rstFiltered.Fields(col).name = "STAFF" Then
                endProSheet.Range("E" & projectId).Value = rstFiltered.Fields(col).Value
                
            'Mail Address
            ElseIf rstFiltered.Fields(col).name = "EMAIL" Then
                endProSheet.Range("F" & projectId).Value = rstFiltered.Fields(col).Value
                
            '住所
            ElseIf rstFiltered.Fields(col).name = "ADDRESS" Then
                endProSheet.Range("G" & projectId).Value = rstFiltered.Fields(col).Value
                
            '案件名
            ElseIf rstFiltered.Fields(col).name = "JOB_NAME" Then
                endProSheet.Range("H" & projectId).Value = rstFiltered.Fields(col).Value
                
            '社員名
            ElseIf rstFiltered.Fields(col).name = "CANDIDATE_NAME" Then
                endProSheet.Range("I" & projectId).Value = rstFiltered.Fields(col).Value
                
            '雇用形態
            ElseIf rstFiltered.Fields(col).name = "EMPLOYMENT" Then
                If rstFiltered.Fields(col).Value <> "" Then
                    If rstFiltered.Fields(col).Value = "正社員" Then
                        endProSheet.Range("J" & projectId).Value = "正"
                    Else
                        endProSheet.Range("J" & projectId).Value = "契"
                    End If
                End If
                
            '契約開始日
            ElseIf rstFiltered.Fields(col).name = "JOB_STR_DATE" Then
                endProSheet.Range("O" & projectId).Value = rstFiltered.Fields(col).Value
                
            '契約終了日
            ElseIf rstFiltered.Fields(col).name = "JOB_END_DATE" Then
                endProSheet.Range("P" & projectId).Value = rstFiltered.Fields(col).Value
            End If
        Next
        
        rstFiltered.MoveNext
    Wend
    projectId = projectId + 1
    If projectId > endProId Then
        endProSheet.Rows(endProId - 4).Copy
        endProSheet.Rows(projectId).Insert
    End If
    endProSheet.Range("P" & startRowNo).sort Key1:=endProSheet.Columns("P"), order1:=xlAscending, Header:=xlGuess
End Sub

'SE管理台帳のシード「売上げ」
Private Sub SetSalesListInfo(ByVal id, ByVal rowid)
    Dim salesSheet
    Set salesSheet = Application.Workbooks("SE管理台帳.xls").Worksheets("売上げ")
    
    If id > 4 Then
        salesSheet.Rows(4).Copy
        salesSheet.Rows(id).Insert
    End If
        
    '番号
    salesSheet.Range("b" & id).Value = id - 3
    '担当営業
    salesSheet.Range("a" & id).Value = mastersheet.Range("ao" & rowid).Value
    salesSheet.Range("a" & id).EntireColumn.AutoFit
    '社員漢字
    salesSheet.Range("c" & id).Value = mastersheet.Range("b" & rowid).Value
    salesSheet.Range("c" & id).EntireColumn.AutoFit
    '所属会社
    salesSheet.Range("d" & id).Value = mastersheet.Range("d" & rowid).Value
    salesSheet.Range("d" & id).EntireColumn.AutoFit
    '担当役職
    salesSheet.Range("g" & id).Value = mastersheet.Range("AY" & rowid).Value
    salesSheet.Range("g" & id).EntireColumn.AutoFit
    '担当漢字
    salesSheet.Range("h" & id).Value = mastersheet.Range("AW" & rowid).Value
    salesSheet.Range("h" & id).EntireColumn.AutoFit
    '電話(携帯)
    salesSheet.Range("i" & id).Value = mastersheet.Range("BA" & rowid).Value
    salesSheet.Range("i" & id).EntireColumn.AutoFit
    '電話
    salesSheet.Range("j" & id).Value = mastersheet.Range("BB" & rowid).Value
    salesSheet.Range("j" & id).EntireColumn.AutoFit
    'FAX
    salesSheet.Range("k" & id).Value = mastersheet.Range("BC" & rowid).Value
    salesSheet.Range("k" & id).EntireColumn.AutoFit
    'メール(担当)
    salesSheet.Range("l" & id).Value = mastersheet.Range("BD" & rowid).Value
    salesSheet.Range("l" & id).EntireColumn.AutoFit
    '社用
    salesSheet.Range("m" & id).Value = mastersheet.Range("BE" & rowid).Value
    salesSheet.Range("m" & id).EntireColumn.AutoFit
    '会社
    salesSheet.Range("n" & id).Value = mastersheet.Range("bm" & rowid).Value
    salesSheet.Range("n" & id).EntireColumn.AutoFit
    '場所
    salesSheet.Range("o" & id).Value = mastersheet.Range("bq" & rowid).Value
    salesSheet.Range("o" & id).EntireColumn.AutoFit
    '単価
    salesSheet.Range("p" & id).Value = mastersheet.Range("i" & rowid).Value
    salesSheet.Range("p" & id).EntireColumn.AutoFit
    
    '作業時間(下限）
    If mastersheet.Range("h" & rowid).Value = "" Then
        salesSheet.Range("q" & id).Value = ""
    ElseIf IsNumeric(mastersheet.Range("h" & rowid).Value) Then
        salesSheet.Range("q" & id).Value = mastersheet.Range("h" & rowid).Value
    Else
        salesSheet.Range("q" & id).Value = "固定"
    End If
    salesSheet.Range("q" & id).EntireColumn.AutoFit
    
    '控除単価（円）
    salesSheet.Range("r" & id).Value = mastersheet.Range("l" & rowid).Value
    salesSheet.Range("r" & id).EntireColumn.AutoFit
    
    '作業時間（上限）
    If mastersheet.Range("g" & rowid).Value = "" Then
        salesSheet.Range("s" & id).Value = ""
    ElseIf IsNumeric(mastersheet.Range("g" & rowid).Value) Then
        salesSheet.Range("s" & id).Value = mastersheet.Range("g" & rowid).Value
    Else
        salesSheet.Range("s" & id).Value = "固定"
    End If
    salesSheet.Range("s" & id).EntireColumn.AutoFit
    
    '超過単価（円）
    salesSheet.Range("t" & id).Value = mastersheet.Range("k" & rowid).Value
    salesSheet.Range("t" & id).EntireColumn.AutoFit
    '時間単位
    salesSheet.Range("u" & id).Value = mastersheet.Range("av" & rowid).Value
    salesSheet.Range("u" & id).EntireColumn.AutoFit
    '支払日
    If mastersheet.Range("al" & rowid).Value = "" Then
        salesSheet.Range("v" & id).Value = ""
    Else
        salesSheet.Range("v" & id).Value = mastersheet.Range("al" & rowid).Value
        salesSheet.Range("v" & id).EntireColumn.AutoFit
    End If
    
    'スタート
    salesSheet.Range("w" & id).Value = mastersheet.Range("ar" & rowid).Value
    '更新
    salesSheet.Range("x" & id).Value = mastersheet.Range("as" & rowid).Value
    '開始
    salesSheet.Range("y" & id).Value = mastersheet.Range("at" & rowid).Value
    '終了
    salesSheet.Range("z" & id).Value = mastersheet.Range("au" & rowid).Value
End Sub

'SE管理台帳のシード「原価」
Private Sub SetCostListInfo(ByVal id, ByVal rowid)
    Dim costSheet
    Set costSheet = Application.Workbooks("SE管理台帳.xls").Worksheets("原価")
    
    If id > 4 Then
        costSheet.Rows(4).Copy
        costSheet.Rows(id).Insert
    End If
    
    '番号
    costSheet.Range("b" & id).Value = id - 3
    '担当営業
    costSheet.Range("a" & id).Value = mastersheet.Range("ao" & rowid).Value
    costSheet.Range("a" & id).EntireColumn.AutoFit
    '社員漢字
    costSheet.Range("c" & id).Value = mastersheet.Range("b" & rowid).Value
    costSheet.Range("c" & id).EntireColumn.AutoFit
    '雇用形態
    costSheet.Range("d" & id).Value = mastersheet.Range("e" & rowid).Value
    costSheet.Range("d" & id).EntireColumn.AutoFit
    '社名
    costSheet.Range("e" & id).Value = mastersheet.Range("d" & rowid).Value
    costSheet.Range("e" & id).EntireColumn.AutoFit
    '担当役職
    costSheet.Range("h" & id).Value = mastersheet.Range("AY" & rowid).Value
    costSheet.Range("h" & id).EntireColumn.AutoFit
    '担当読み方
    costSheet.Range("i" & id).Value = mastersheet.Range("AZ" & rowid).Value
    costSheet.Range("i" & id).EntireColumn.AutoFit
    '電話(携帯)
    costSheet.Range("j" & id).Value = mastersheet.Range("BA" & rowid).Value
    costSheet.Range("j" & id).EntireColumn.AutoFit
    '電話
    costSheet.Range("k" & id).Value = mastersheet.Range("BB" & rowid).Value
    costSheet.Range("k" & id).EntireColumn.AutoFit
    'FAX
    costSheet.Range("l" & id).Value = mastersheet.Range("BC" & rowid).Value
    costSheet.Range("l" & id).EntireColumn.AutoFit
    'メール(担当)
    costSheet.Range("m" & id).Value = mastersheet.Range("BD" & rowid).Value
    costSheet.Range("m" & id).EntireColumn.AutoFit
    'メール(携帯)
    costSheet.Range("n" & id).Value = mastersheet.Range("BD" & rowid).Value
    costSheet.Range("n" & id).EntireColumn.AutoFit
    'メール(社用)
    costSheet.Range("o" & id).Value = mastersheet.Range("BE" & rowid).Value
    costSheet.Range("o" & id).EntireColumn.AutoFit
    '会社
    costSheet.Range("p" & id).Value = mastersheet.Range("bm" & rowid).Value
    costSheet.Range("p" & id).EntireColumn.AutoFit
    '場所
    costSheet.Range("q" & id).Value = mastersheet.Range("bq" & rowid).Value
    costSheet.Range("q" & id).EntireColumn.AutoFit
    '単価
    costSheet.Range("r" & id).Value = mastersheet.Range("j" & rowid).Value
    costSheet.Range("r" & id).EntireColumn.AutoFit
    '作業時間(下限）
    costSheet.Range("s" & id).Value = mastersheet.Range("h" & rowid).Value
    costSheet.Range("s" & id).EntireColumn.AutoFit
    '控除単価（円）
    costSheet.Range("t" & id).Value = mastersheet.Range("l" & rowid).Value
    costSheet.Range("t" & id).EntireColumn.AutoFit
    '作業時間（上限）
    costSheet.Range("u" & id).Value = mastersheet.Range("g" & rowid).Value
    costSheet.Range("u" & id).EntireColumn.AutoFit
    '超過単価（円）
    costSheet.Range("v" & id).Value = mastersheet.Range("k" & rowid).Value
    costSheet.Range("v" & id).EntireColumn.AutoFit
    '時間単位
    costSheet.Range("w" & id).Value = mastersheet.Range("av" & rowid).Value
    costSheet.Range("w" & id).EntireColumn.AutoFit
    '支払日
    If mastersheet.Range("V" & rowid).Value = "" Then
        costSheet.Range("ab" & id).Value = ""
    Else
        costSheet.Range("ab" & id).Value = mastersheet.Range("V" & rowid).Value
        costSheet.Range("ab" & id).EntireColumn.AutoFit
    End If
    
    'スタート
    costSheet.Range("x" & id).Value = mastersheet.Range("ar" & rowid).Value
    costSheet.Range("x" & id).EntireColumn.AutoFit
    '更新
    costSheet.Range("y" & id).Value = mastersheet.Range("as" & rowid).Value
    costSheet.Range("y" & id).EntireColumn.AutoFit
    '開始
    costSheet.Range("z" & id).Value = mastersheet.Range("at" & rowid).Value
    costSheet.Range("z" & id).EntireColumn.AutoFit
    '終了
    costSheet.Range("aa" & id).Value = mastersheet.Range("au" & rowid).Value
    costSheet.Range("aa" & id).EntireColumn.AutoFit
End Sub
'月末を取得
Private Function GetMonthEnd(ByVal pYear, ByVal pMonth)
'******************************稼働機能の追加 WuYang 2016/06/03 Start******************************
    'If pMonth = 1 Or pMonth = 3 Or pMonth = 4 Or pMonth = 5 Or pMonth = 7 Or pMonth = 8 Or pMonth = 10 Or pMonth = 12 Then
    If pMonth = 1 Or pMonth = 3 Or pMonth = 5 Or pMonth = 7 Or pMonth = 8 Or pMonth = 10 Or pMonth = 12 Then
'******************************稼働機能の追加 WuYang 2016/06/03 End******************************
        GetMonthEnd = "31"
    ElseIf pMonth = 2 Then
        If CheckLeapYear(pYear) Then
             '閏年の場合
            GetMonthEnd = "29"
        Else
            GetMonthEnd = "28"
        End If
    Else
        GetMonthEnd = "30"
    End If
End Function

'閏年の判定
Private Function CheckLeapYear(ByVal iDate As Integer)
    
    If iDate Mod 400 = 0 Or iDate Mod 4 = 0 And iDate Mod 400 <> 0 Then
        CheckLeapYear = True '閏年
    Else
        CheckLeapYear = False
        
    End If
End Function

Private Function GetTime(ByVal pTime, ByVal unit)
    Dim pHour, pMinute, pUnit, pColon
    
    pColon = InStr(pTime, ":")
    pHour = Mid(pTime, 1, pColon - 1) '時間
    pMinute = Mid(pTime, pColon + 1) '分
    
    GetTime = pHour + (pMinute / 60)
    
    If IsNumeric(unit) And unit <> 0 Then
        If pMinute Mod unit <> 0 Then
            GetTime = pHour + (Int(pMinute / unit) * unit / 60)
        End If
    End If

End Function

'csvを基に、稼動勤務勤怠集計表にデータを出力する
Private Function SetAttendanceByCsv(ByVal excelName, ByRef attendanceRowId)
    Dim attendanceSheet
    Set attendanceSheet = Application.Workbooks(excelName).Worksheets("勤怠集計表")
    Dim i As Integer, overTime, workTime
    Dim headerFlg As Boolean
    Dim fTextDir As String
    fTextDir = TextBox2.Text

    Open fTextDir For Input As #1
    Do While Not EOF(1)
        Line Input #1, currLine
        
        If headerFlg = False Then
            headerFlg = True
        Else
            attendanceRowId = attendanceRowId + 1
            If attendanceRowId > 6 Then
                attendanceSheet.Rows(6).Copy
                attendanceSheet.Rows(attendanceRowId).PasteSpecial Paste:=xlPasteAll
            End If
            
            rowDataArr = Split(currLine, ",")
            overTime = 0
            workTime = 0
    '*************************csvを基に、稼動勤務勤怠集計表にデータ出力の修正 WuYang 2016/06/06 Start*****************************
            For i = 0 To UBound(rowDataArr)
                '氏名
                'If i = 1 Then
                    'attendanceSheet.Range("F" & attendanceRowId).Value = rowDataArr(i)
                
                '実出勤日数=csv.勤務日数
                'ElseIf i = 2 Then
                    'attendanceSheet.Range("K" & attendanceRowId).Value = rowDataArr(i)
                    
                'CSV.就業時間
                'ElseIf i = 3 And IsNumeric(rowDataArr(i)) Then
                    'workTime = CInt(rowDataArr(i))
                
                '遅・早日数=csv.遅早回数
                'ElseIf i = 5 Then
                    'attendanceSheet.Range("M" & attendanceRowId).Value = rowDataArr(i)
                
                '遅早時間=csv.遅早時間
                'ElseIf i = 6 Then
                    'attendanceSheet.Range("N" & attendanceRowId).Value = rowDataArr(i)
                    
                'CSV.残業時間
                'ElseIf i = 9 And IsNumeric(rowDataArr(i)) Then
                    'overTime = CInt(rowDataArr(i))
                    '就業時間に残業時間を追加
                    'workTime = workTime + CInt(rowDataArr(i))
                    
                'CSV.深夜残業時間
                'ElseIf i = 15 And IsNumeric(rowDataArr(i)) Then
                    '就業時間に深夜残業時間を追加
                    'workTime = workTime + CInt(rowDataArr(i))
                'End If
                
                '********2016/11/30 Mod Start KaitRi 内勤csv取込処理修正　年月値は取込対象にならないように****
                '社員番号
                'If i = 0 Then
                '    attendanceSheet.Range("E" & attendanceRowId).Value = rowDataArr(i)
                    
                '氏名
                'ElseIf i = 3 Then
                '    attendanceSheet.Range("F" & attendanceRowId).Value = rowDataArr(i)
                    
                '当月営業日数
                'ElseIf i = 4 Then
                '    attendanceSheet.Range("J" & attendanceRowId).Value = rowDataArr(i)
                    
                '実出勤日数
                'ElseIf i = 5 Then
                '    attendanceSheet.Range("K" & attendanceRowId).Value = rowDataArr(i)
                
                '欠勤日数
                'ElseIf i = 6 Then
                '    attendanceSheet.Range("L" & attendanceRowId).Value = rowDataArr(i)
                    
                '遅刻
                'ElseIf i = 7 Then
                '    attendanceSheet.Range("M" & attendanceRowId).Value = rowDataArr(i)
                    
                '遅・早時間
                'ElseIf i = 27 Then
                '    attendanceSheet.Range("N" & attendanceRowId).Value = rowDataArr(i)
                    
                '実出勤日数=csv.勤務日数
                'ElseIf i = 16 Then
                '    attendanceSheet.Range("O" & attendanceRowId).Value = rowDataArr(i)
                    
                '下限
                'ElseIf i = 5 Then
                '    attendanceSheet.Range("P" & attendanceRowId).Value = CInt(rowDataArr(i)) * 8
                '上限
                '    attendanceSheet.Range("Q" & attendanceRowId).Value = CInt(rowDataArr(i)) * 8 + 50
                    
                '取得日数
                'ElseIf i = 10 Then
                '    attendanceSheet.Range("Z" & attendanceRowId).Value = rowDataArr(i)
                    
                'End If
                '社員番号
                If i = 1 Then
                    attendanceSheet.Range("E" & attendanceRowId).Value = rowDataArr(i)
                    
                '氏名
                ElseIf i = 4 Then
                    attendanceSheet.Range("F" & attendanceRowId).Value = rowDataArr(i)
                    
                '当月営業日数
                ElseIf i = 5 Then
                    attendanceSheet.Range("J" & attendanceRowId).Value = rowDataArr(i)
                    
                '実出勤日数
                ElseIf i = 6 Then
                    attendanceSheet.Range("K" & attendanceRowId).Value = rowDataArr(i)
                
                '欠勤日数
                ElseIf i = 7 Then
                    attendanceSheet.Range("L" & attendanceRowId).Value = rowDataArr(i)
                    
                '遅刻
                ElseIf i = 8 Then
                    attendanceSheet.Range("M" & attendanceRowId).Value = rowDataArr(i)
                    
                '遅・早時間
                ElseIf i = 28 Then
                    attendanceSheet.Range("N" & attendanceRowId).Value = rowDataArr(i)
                    
                '実出勤日数=csv.勤務日数
                ElseIf i = 17 Then
                    attendanceSheet.Range("O" & attendanceRowId).Value = rowDataArr(i)
                    
                '下限
                ElseIf i = 6 Then
                    attendanceSheet.Range("P" & attendanceRowId).Value = CInt(rowDataArr(i)) * 8
                '上限
                    attendanceSheet.Range("Q" & attendanceRowId).Value = CInt(rowDataArr(i)) * 8 + 50
                    
                '取得日数
                ElseIf i = 11 Then
                    attendanceSheet.Range("Z" & attendanceRowId).Value = rowDataArr(i)
                    
                End If
                '********2016/11/30 Mod End KaitRi 内勤csv取込処理修正　年月値は取込対象にならないように****
            Next i
                        
            '番号
            attendanceSheet.Range("A" & attendanceRowId).Value = attendanceRowId - 5
            '支払日
            attendanceSheet.Range("B" & attendanceRowId).Value = ""
            '締日
            attendanceSheet.Range("C" & attendanceRowId).Value = ""
            '支払いサイト
            attendanceSheet.Range("D" & attendanceRowId).Value = ""
            '"ID番号コード"
            'attendanceSheet.Range("E" & attendanceRowId).Value = ""
            '内勤外勤
            attendanceSheet.Range("G" & attendanceRowId).Value = "内"
            '契約形態
            attendanceSheet.Range("H" & attendanceRowId).Value = ""
            '主担当
            attendanceSheet.Range("I" & attendanceRowId).Value = ""
            '遅・早日数
            'attendanceSheet.Range("M" & attendanceRowId).Value = ""
            '遅・早時間
            'attendanceSheet.Range("N" & attendanceRowId).Value = ""
            '有休休暇繰越（前年度）
            attendanceSheet.Range("W" & attendanceRowId).Value = ""
            '有給休暇（今年度）
            attendanceSheet.Range("X" & attendanceRowId).Value = ""
            '付与
            attendanceSheet.Range("Y" & attendanceRowId).Value = ""
            '残業追加
            attendanceSheet.Range("AB" & attendanceRowId).Value = ""
            '残業単価
            attendanceSheet.Range("AC" & attendanceRowId).Value = ""
            'その他支給額
            attendanceSheet.Range("AD" & attendanceRowId).Value = ""
            '勤怠控除
            attendanceSheet.Range("AE" & attendanceRowId).Value = ""
            '欠勤日数
            'attendanceSheet.Range("L" & attendanceRowId).Formula = "=IF(J" & attendanceRowId & "<> """"," & "J" & attendanceRowId & "- K" & attendanceRowId & ","""")"
            
            '実働時間
            'attendanceSheet.Range("O" & attendanceRowId).Value = workTime
            '下限
            'attendanceSheet.Range("P" & attendanceRowId).Value = ""
            '上限
            'attendanceSheet.Range("Q" & attendanceRowId).Value = ""
            '残業追加
            'attendanceSheet.Range("S" & attendanceRowId).Value = overTime
            '入社日
            attendanceSheet.Range("T" & attendanceRowId).Value = ""
            '交通費
            attendanceSheet.Range("AG" & attendanceRowId).Value = ""
            '経費
            attendanceSheet.Range("AH" & attendanceRowId).Value = ""
            '回収ステータス
            attendanceSheet.Range("AK" & attendanceRowId).Value = "○"
            '*************************csvを基に、稼動勤務勤怠集計表にデータ出力の修正 WuYang 2016/06/06 End*****************************
        End If
    Loop
    Close #1

End Function

'修正後Excelを基に、稼動勤務勤怠集計表にデータを出力する
Private Function SetAttendanceByExcl(ByVal excelName, ByRef attendanceRowId, ByRef procMonth, ByRef tableSheet)
    Dim attendanceSheet
    'Dim i As Integer, overTime, workTime
    Dim fTextDir As String
    Dim workReportSheet
    Dim endRowNo
    Dim Index
    'Const empTable = "内勤簡易テーブル"
    'Const pvTable = "有休加算テーブル"
    'Const empNumPos = 1
    'Const empIncmPos = 3
    'Const empPrcPos = 4
    'Const empAddPrcPos = 5
    'Const dayNumPos = 1
    attendanceRowId = 6
    
    Set attendanceSheet = Application.Workbooks(excelName).Worksheets("勤怠集計表")
    
    'Excelファイルを開き行カウントして行数分繰り返し
    fTextDir = TextBox2.Text
    Set baseFile = Workbooks.Open(fTextDir)
    
    For Each xlSheet In baseFile.Worksheets
        SheetName = xlSheet.name
    Next
    
    Set workReportSheet = Application.Workbooks(baseFile.name).Worksheets(SheetName)
    endRowNo = workReportSheet.UsedRange.Rows(workReportSheet.UsedRange.Rows.count).Row
        
    For Index = 2 To endRowNo
           
        If attendanceRowId > 6 Then
            attendanceSheet.Rows(6).Copy
            attendanceSheet.Rows(attendanceRowId).PasteSpecial Paste:=xlPasteAll
        End If
            
        '社員番号
        attendanceSheet.Range("E" & attendanceRowId).Value = workReportSheet.Range("A" & Index).Value
        '氏名
        attendanceSheet.Range("F" & attendanceRowId).Value = workReportSheet.Range("D" & Index).Value
        '当月営業日数
        attendanceSheet.Range("J" & attendanceRowId).Value = workReportSheet.Range("E" & Index).Value
        '実出勤日数
        attendanceSheet.Range("K" & attendanceRowId).Value = workReportSheet.Range("F" & Index).Value
        '欠勤日数
        attendanceSheet.Range("L" & attendanceRowId).Value = workReportSheet.Range("G" & Index).Value
        '遅刻
        attendanceSheet.Range("M" & attendanceRowId).Value = workReportSheet.Range("H" & Index).Value
        '遅・早時間
        attendanceSheet.Range("N" & attendanceRowId).Value = workReportSheet.Range("AB" & Index).Value
        '実出勤日数=csv.勤務日数
        attendanceSheet.Range("O" & attendanceRowId).Value = workReportSheet.Range("Q" & Index).Value
        '下限
        attendanceSheet.Range("P" & attendanceRowId).Value = CInt(workReportSheet.Range("F" & Index).Value) * 8
        '上限
        attendanceSheet.Range("Q" & attendanceRowId).Value = CInt(workReportSheet.Range("F" & Index).Value) * 8 + 50
        '取得日数
        attendanceSheet.Range("Z" & attendanceRowId).Value = workReportSheet.Range("K" & Index).Value
        '番号
        attendanceSheet.Range("A" & attendanceRowId).Value = attendanceRowId - 5
        '支払日
        attendanceSheet.Range("B" & attendanceRowId).Value = ""
        '締日
        attendanceSheet.Range("C" & attendanceRowId).Value = ""
        '支払いサイト
        attendanceSheet.Range("D" & attendanceRowId).Value = ""
        '"ID番号コード"
        'attendanceSheet.Range("E" & attendanceRowId).Value = ""
        '内勤外勤
        attendanceSheet.Range("G" & attendanceRowId).Value = "内"
        '契約形態
        attendanceSheet.Range("H" & attendanceRowId).Value = ""
        '主担当
        attendanceSheet.Range("I" & attendanceRowId).Value = ""
        '遅・早日数
        'attendanceSheet.Range("M" & attendanceRowId).Value = ""
        '遅・早時間
        'attendanceSheet.Range("N" & attendanceRowId).Value = ""
        '有休休暇繰越（前年度）
        attendanceSheet.Range("W" & attendanceRowId).Value = ""
        '有給休暇（今年度）
        attendanceSheet.Range("X" & attendanceRowId).Value = ""
        '付与
        attendanceSheet.Range("Y" & attendanceRowId).Value = ""
        '残業追加
        attendanceSheet.Range("AB" & attendanceRowId).Value = ""
        '残業単価
        attendanceSheet.Range("AC" & attendanceRowId).Value = ""
        'その他支給額
        attendanceSheet.Range("AD" & attendanceRowId).Value = ""
        '勤怠控除
        attendanceSheet.Range("AE" & attendanceRowId).Value = ""
        '欠勤日数
        'attendanceSheet.Range("L" & attendanceRowId).Formula = "=IF(J" & attendanceRowId & "<> """"," & "J" & attendanceRowId & "- K" & attendanceRowId & ","""")"
        
        '実働時間
        'attendanceSheet.Range("O" & attendanceRowId).Value = workTime
        '下限
        'attendanceSheet.Range("P" & attendanceRowId).Value = ""
        '上限
        'attendanceSheet.Range("Q" & attendanceRowId).Value = ""
        '残業追加
        'attendanceSheet.Range("S" & attendanceRowId).Value = overTime
        '入社日
        attendanceSheet.Range("T" & attendanceRowId).Value = ""
        '交通費
        attendanceSheet.Range("AG" & attendanceRowId).Value = ""
        '経費
        attendanceSheet.Range("AH" & attendanceRowId).Value = ""
        '回収ステータス
        attendanceSheet.Range("AK" & attendanceRowId).Value = "○"
        attendanceRowId = attendanceRowId + 1
        
    Next Index

    'getRowId = 2
    'yText = CInt(Left(procMonth, 4))
    'mText = CInt(Mid(procMonth, 5))

    'データの取り込みとセット
    'For setRowId = attendanceRowId + 1 To endRowNo
        'getRowId = getRowId + 1
        'overTime = 0
        'workTime = 0
        'srchCode = workReportSheet.Range("A" & getRowId).Value
        'wrkDayBase = workReportSheet.Range("D" & getRowId).Value
        'wrkDayReal = workReportSheet.Range("E" & getRowId).Value
        'offDayReal = workReportSheet.Range("K" & getRowId).Value
        'absDayTemp = wrkDayBase - (wrkDayReal + offDayReal)
        'If absDayTemp < 0 Then absDayTemp = 0
        'wrkTimeReal = workReportSheet.Range("P" & getRowId).Value
        'undrTimetemp = (wrkDayBase - absDayTemp) * 8
        'overTimetemp = undrTimetemp + (50 / wrkDayBase * wrkDayReal)
        'addOverTime = wrkTimeReal - overTimetemp
        'If addOverTime < 0 Then addOverTime = 0
        'For Each empInfo In tableSheet.Range(empTable)
        '    If empInfo.Value = srchCode Then Exit For
        'Next empInfo

        'If setRowId > attendanceRowId + 1 Then
        '    attendanceSheet.Rows(attendanceRowId + 1).Copy
        '    attendanceSheet.Rows(setRowId).PasteSpecial Paste:=xlPasteAll
        'End If
       '
        '通番
        'attendanceSheet.Range("A" & setRowId).Value = setRowId - attendanceRowId
        '支払日
        'setTempDate = DateSerial(yText, mText + 1, 25)
        'attendanceSheet.Range("B" & setRowId).Value = setTempDate
        '締日
        'setTempDate = DateSerial(yText, mText + 1, 0)
        'attendanceSheet.Range("C" & setRowId).Value = setTempDate
        '支払いサイト
        'attendanceSheet.Range("D" & setRowId).Value = "25日"
        'ID番号"コード"
        'attendanceSheet.Range("E" & setRowId).Value = empInfo.Offset(0, empNumPos)
        '氏名
        'attendanceSheet.Range("F" & setRowId).Value = workReportSheet.Range("C" & getRowId).Value
        '内勤外勤
        'attendanceSheet.Range("G" & setRowId).Value = "内"
        '契約形態
        'attendanceSheet.Range("H" & setRowId).Value = "正社員"
        '主担当
        'attendanceSheet.Range("I" & setRowId).Value = ""
        '当月営業日数
        'attendanceSheet.Range("J" & setRowId).Value = wrkDayBase
        '実出勤日数
        'attendanceSheet.Range("K" & setRowId).Value = wrkDayReal
        '欠勤日数
        'attendanceSheet.Range("L" & setRowId).Value = absDayTemp
        '遅・早日数
        'attendanceSheet.Range("M" & setRowId).Value = workReportSheet.Range("G" & getRowId).Value
        '遅・早時間
        'attendanceSheet.Range("N" & setRowId).Value = workReportSheet.Range("AB" & getRowId).Value
        '実働時間
        'attendanceSheet.Range("O" & setRowId).Value = wrkTimeReal
        '下限
        'attendanceSheet.Range("P" & setRowId).Value = undrTimetemp
        '上限
        'attendanceSheet.Range("Q" & setRowId).Value = overTimetemp
        '欠勤控除
        'attendanceSheet.Range("R" & setRowId).Value = ""
        '残業追加
        'attendanceSheet.Range("S" & setRowId).Value = addOverTime
        '入社日
        'inComDate = empInfo.Offset(0, empIncmPos)
        'attendanceSheet.Range("T" & setRowId).Value = inComDate
        '6ヶ月有休付与
        'offDayAddDate = DateSerial(Year(inComDate), month(inComDate) + 6, Day(inComDate))
        'attendanceSheet.Range("U" & setRowId).Value = offDayAddDate
        '有給休暇
        'V列　テンプレートに式記載 "=有給休暇繰越+有給休暇今回+有給休暇付与"
        '有給休暇繰越/有給休暇今回/有給休暇付与
        'offDayAddNum0 = 0
        'offDayAddNum1 = 0
        'offDayAddNum2 = 0
        'If month(offDayAddDate) = mText Then
        '    abstYr = yText - Year(offDayAddDate)
        '    If abstYr > 0 Then
        '        If abstYr > 20 Then
        '            offDayAddNum0 = 20
        '            offDayAddNum1 = 20
        '            offDayAddNum2 = 20
        '        Else
        '            For Each offDayNum In tableSheet.Range(pvTable)
        '                If offDayNum.Value = abstYr Then Exit For
        '            Next offDayNum
        '            offDayAddNum0 = offDayNum.Offset(-2, dayNumPos)
        '            offDayAddNum1 = offDayNum.Offset(-1, dayNumPos)
        '            offDayAddNum2 = offDayNum.Offset(0, dayNumPos)
        '        End If
        '    Else
        '        offDayAddNum0 = 0
        '        offDayAddNum1 = 0
        '        offDayAddNum2 = 10
        '    End If
        'End If
        '有給休暇繰越
        'W列　手入力
        '有給休暇今回
        'attendanceSheet.Range("X" & setRowId).Value = offDayAddNum2
        '有給休暇付与
        'If offDayAddNum1 = 0 Then
        '    setCulcText = "=0"
        'Else
        '    setCulcText = "=if(W" & setRowId & " - " & offDayAddNum1 & " > 0, -(W" & setRowId & " - " & offDayAddNum1 & "), 0)"
        'End If
        'attendanceSheet.Range("Y" & setRowId).Formula = setCulcText
        '有給休暇取得日数
        'attendanceSheet.Range("Z" & setRowId).Value = workReportSheet.Range("K" & getRowId).Value
        '有給休暇残日数
        'AA列　テンプレートに式記載 "=有給休暇-有給休暇取得日数"
        
        '残業追加/残業単価
        'ovrPrcByH = empInfo.Offset(0, empAddPrcPos)
        '残業追加
        'attendanceSheet.Range("AB" & setRowId).Value = addOverTime * ovrPrcByH
        '残業単価
        'attendanceSheet.Range("AC" & setRowId).Value = ovrPrcByH
        'その他支給額
        'attendanceSheet.Range("AC" & setRowId).Value = ""
        
        '勤怠控除/稼働工数
        'workPer = Int(wrkDayReal / wrkDayBase * 100) / 100
        '勤怠控除
        'allPrc = empInfo.Offset(0, empPrcPos)
        'attendanceSheet.Range("AD" & setRowId).Value = allPrc * (1 - workPer)
        '稼働工数
        ' attendanceSheet.Range("AE" & setRowId).Value = FormatPercent(workPer, 0)
        '交通費
        ' attendanceSheet.Range("AF" & setRowId).Value = ""
        '経費
        ' attendanceSheet.Range("AG" & setRowId).Value = ""
        '備考
        ' attendanceSheet.Range("AH" & setRowId).Value = ""
        '作業報告書回収
        ' attendanceSheet.Range("AI" & setRowId).Value = ""
        '作業報告書回収ステータス
        ' attendanceSheet.Range("AJ" & setRowId).Value = ""
        '営業確認理由
        ' attendanceSheet.Range("AK" & setRowId).Value = ""
        '営業確認条件変更
        ' attendanceSheet.Range("AL" & setRowId).Value = ""
    'Next setRowId

    baseFile.Close

End Function

'書類送付状_履歴_発送処理一覧.xlsを作成
Private Sub SetDocTransmittalLetter(ByVal requestCode, ByVal docTraLetterRowId, ByVal rowid)

    Dim docTraLetterSheet
    Set docTraLetterSheet = Application.Workbooks("書類送付状_履歴_発送処理一覧.xls").Worksheets("送付状_発送履歴")

    If gCompanyName <> mastersheet.Range("BM" & rowid).Value Then
        gCompanyName = mastersheet.Range("BM" & rowid).Value
        
        'No.
        docTraLetterSheet.Range("A" & docTraLetterRowId).Value = docTraLetterRowId - 2
        
        '会社名・個人名
        docTraLetterSheet.Range("B" & docTraLetterRowId).Value = mastersheet.Range("BM" & rowid).Value
        
        '種類1
        docTraLetterSheet.Range("C" & docTraLetterRowId).Value = "作業報告書"
        '種類2
        docTraLetterSheet.Range("D" & docTraLetterRowId).Value = "請求書"
        '種類3
        If chkPrint.Value = True Or chkFax.Value = True Then
            docTraLetterSheet.Range("E" & docTraLetterRowId).Value = "送付状"
        End If
        '氏名
        docTraLetterSheet.Range("I" & docTraLetterRowId).Value = mastersheet.Range("B" & rowid).Value
        '請求コード
        docTraLetterSheet.Range("J" & docTraLetterRowId).Value = requestCode
    Else
        'No.
        docTraLetterSheet.Range("A" & docTraLetterRowId).Value = docTraLetterRowId - 2
        '氏名
        docTraLetterSheet.Range("I" & docTraLetterRowId).Value = mastersheet.Range("B" & rowid).Value
        '請求コード
        docTraLetterSheet.Range("J" & docTraLetterRowId).Value = requestCode
    End If
End Sub

Private Sub setInsentiveTotal(ByVal saleSheet, ByVal priceRowId, ByVal insentRowId, ByVal saleRowId, ByVal priceCount)
'インセンティブ、営業毎・新規／継続別集計とセット
    
    '各種定義（sub内のみ使用）
    Const setCatCnt = "継続"
    Const setCatNew = "新規"
    Const setCatTot = "合計"
    Const setCatSale = "売上"
    Const setCatPay = "仕入"
    Const scrptngDctnry = "Scripting.Dictionary"
    
    '初期設定
    Dim salesDict As Object
    Dim insentDict As Object
    Set salesDict = CreateObject(scrptngDctnry)
    
    'インセンティブデータ集計（営業毎）
    salesCnt = 0
    'インセンティブ明細表全件読み取り
    For salesLoopCnt = saleSheet.Range("B" & (10 + priceRowId)).Row To insentRowId
        '営業名、契約状態、粗利、各データ取得
        salesNm = saleSheet.Range("D" & salesLoopCnt).Value
        salesCat = saleSheet.Range("C" & salesLoopCnt).Value
        salesPrc = saleSheet.Range("K" & salesLoopCnt).Value
        salesSale = saleSheet.Range("I" & salesLoopCnt).Value
        salesPay = saleSheet.Range("J" & salesLoopCnt).Value
        '計上する営業判定
        salesFlg = 0
        If salesCnt > 0 Then
            For Each salesDictNm In salesDict.keys
                '既に出現済み
                If salesDictNm = salesNm Then
                    salesFlg = 1
                    '該当契約状態の粗利加算
                    salesDict(salesNm)(salesCat) = salesDict(salesNm)(salesCat) + salesPrc
                    '粗利合計へ加算
                    salesDict(salesNm)(setCatTot) = salesDict(salesNm)(setCatTot) + salesPrc
                    salesDict(salesNm)(setCatSale) = salesDict(salesNm)(setCatSale) + salesSale
                    salesDict(salesNm)(setCatPay) = salesDict(salesNm)(setCatPay) + salesPay
                End If
            Next
        End If
        '未だ出現していない営業の計上
        If salesFlg = 0 Then
            salesCnt = salesCnt + 1
            '初期計上
            Set insentDict = CreateObject(scrptngDctnry)
            Select Case salesCat
            Case setCatCnt
                '継続の場合、継続に粗利計上、新規は0
                insentDict.Add setCatCnt, salesPrc
                insentDict.Add setCatNew, 0
            Case setCatNew
                '新規の場合、新規に粗利計上、継続は0
                insentDict.Add setCatCnt, 0
                insentDict.Add setCatNew, salesPrc
                insentDict.Add setCatSale, salesSale
                insentDict.Add setCatPay, salesPay
            Case Else
                '該当なしの場合、継続、新規ともに0
                insentDict.Add setCatCnt, 0
                insentDict.Add setCatNew, 0
            End Select
            '合計に粗利計上
            insentDict.Add setCatTot, salesPrc
            '計上した粗利を営業と紐づけ
            salesDict.Add salesNm, insentDict
        End If
    Next
    
    '計上結果を帳票へセット
    If salesCnt > 0 Then
        salesInsentRowF = 38 + ((saleRowId - 8) * 3)
        setPosRow = salesInsentRowF
        For Each salesDictNm In salesDict.keys
            '対象営業毎に記載
            If setPosRow > salesInsentRowF Then
                saleSheet.Rows(salesInsentRowF).Copy
                saleSheet.Rows(setPosRow).Insert
            End If
            '各項目のセット
            saleSheet.Range("B" & setPosRow).Value = salesDictNm
            saleSheet.Range("C" & setPosRow).Value = salesDict(salesDictNm)(setCatNew)
            saleSheet.Range("C" & setPosRow).NumberFormatLocal = accntDispForm
            saleSheet.Range("E" & setPosRow).Value = salesDict(salesDictNm)(setCatSale)
            saleSheet.Range("E" & setPosRow).NumberFormatLocal = accntDispForm
            saleSheet.Range("F" & setPosRow).Value = salesDict(salesDictNm)(setCatPay)
            saleSheet.Range("F" & setPosRow).NumberFormatLocal = accntDispForm
            setPosRow = setPosRow + 1
        Next
    End If
    'インセンティブの合計を設定
    saleSheet.Range("C" & (setPosRow)).Formula = "=SUM(C" & salesInsentRowF & ":C" & setPosRow - 1 & ")"
    saleSheet.Range("E" & (setPosRow)).Formula = "=SUM(E" & salesInsentRowF & ":E" & setPosRow - 1 & ")"
    saleSheet.Range("F" & (setPosRow)).Formula = "=SUM(F" & salesInsentRowF & ":F" & setPosRow - 1 & ")"

End Sub

Private Sub setstyle(ByVal rowid, ByVal column1, ByVal column2)
    'Cells("rowid,column1:rowid,column2").Select
    Dim sheet, st, ed, mastersheet2, modecell1, modecell2, modecell3, columnid
    Set sheet = Application.Workbooks("TSRS帳票管理マスタ.xls").Worksheets("帳票マスタ")

    Set mastersheet2 = Application.Workbooks("TSRS帳票管理マスタ.xls").Worksheets("帳票マスタ (記入例）")
    columnid = sheet.UsedRange.Columns.count
    st = Cells(rowid, 1).Address(0, 0)
    ed = Cells(rowid, column2).Address(0, 0)
    rn = st & ":" & ed
    sheet.Range(rn).Select

    Selection.Borders(xlDiagonalDown).LineStyle = xlNone
    Selection.Borders(xlDiagonalUp).LineStyle = xlNone
    With Selection.Borders(xlEdgeLeft)
        .LineStyle = xlContinuous
        .Weight = xlThin
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeTop)
        .LineStyle = xlContinuous
        .Weight = xlThin
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeBottom)
        .LineStyle = xlContinuous
        .Weight = xlThin
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeRight)
        .LineStyle = xlContinuous
        .Weight = xlThin
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideVertical)
        .LineStyle = xlContinuous
        .Weight = xlThin
        .ColorIndex = xlAutomatic
    End With

    Set modecell1 = mastersheet2.Range("a7")
    Set modecell2 = mastersheet2.Range("b7")
    Set modecell3 = mastersheet2.Range("c7")
    If sheet.Cells(3, columnid).Value <> "人事側状態" Then
        modecell1.Value = sheet.Cells(rowid, columnid + 1).Value
        modecell1.Copy Destination:=sheet.Cells(rowid, columnid + 1)
        modecell1.Value = sheet.Cells(rowid, columnid + 2).Value
        modecell1.Copy Destination:=sheet.Cells(rowid, columnid + 2)
        modecell2.Value = sheet.Cells(rowid, columnid + 3).Value
        modecell2.Copy Destination:=sheet.Cells(rowid, columnid + 3)
        modecell3.Value = sheet.Cells(rowid, columnid + 4).Value
        modecell3.Copy Destination:=sheet.Cells(rowid, columnid + 4)
    Else
        modecell1.Value = sheet.Cells(rowid, columnid - 3).Value
        modecell1.Copy Destination:=sheet.Cells(rowid, columnid - 3)
        modecell1.Value = sheet.Cells(rowid, columnid - 2).Value
        modecell1.Copy Destination:=sheet.Cells(rowid, columnid - 2)
        modecell2.Value = sheet.Cells(rowid, columnid - 1).Value
        modecell2.Copy Destination:=sheet.Cells(rowid, columnid - 1)
        modecell3.Value = sheet.Cells(rowid, columnid).Value
        modecell3.Copy Destination:=sheet.Cells(rowid, columnid)
    End If
End Sub

Private Function BigMonth(ByVal pMonth)
    Dim month
    If pMonth > 0 And pMonth < 13 Then
        month = pMonth
    Else
        month = month(Now())
    End If
    Select Case month
        Case 1
            BigMonth = "一"
            Exit Function
        Case 2
            BigMonth = "二"
            Exit Function
        Case 3
            BigMonth = "三"
            Exit Function
        Case 4
            BigMonth = "四"
            Exit Function
        Case 5
            BigMonth = "五"
            Exit Function
        Case 6
            BigMonth = "六"
            Exit Function
        Case 7
            BigMonth = "七"
            Exit Function
        Case 8
            BigMonth = "八"
            Exit Function
        Case 9
            BigMonth = "九"
            Exit Function
        Case 10
            BigMonth = "十"
            Exit Function
        Case 11
            BigMonth = "十一"
            Exit Function
        Case 12
            BigMonth = "十二"
            Exit Function
    End Select
End Function

Private Sub MakeZipFile()

    Dim companyName, staffname, email, remark, content, temp

    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    Set Fso = CreateObject("Scripting.FileSystemObject")
    Set mainFolder = Fso.GetFolder(savePath)

    For Each fileName In mainFolder.SubFolders
        
        Set SubFolder = Fso.GetFolder(savePath & "\" & fileName.name)
        remark = TargetFileZip(SubFolder)
    Next fileName

End Sub

'お客様名フォルダと同じ名前で圧縮ファイル(zip)を作成
Private Function TargetFileZip(ByVal targetFldr, ByVal str_Pass) As String
    
    Dim bozip As Boolean
    Set fso4zip = CreateObject("Scripting.FileSystemObject")
    Set shellapp = CreateObject("Shell.Application")
    
    fNmBase = Mid(targetFldr, InStrRev(targetFldr, "\") + 1)
    zipdirPath = targetFldr & "\" & fNmBase
    
    If fso4zip.fileexists(zipdirPath) = True Then
        fso4zip.DeleteFile zipdirPath
    End If
    
    If fso4zip.fileexists(zipdirPath & ".zip") = True Then
        fso4zip.DeleteFile zipdirPath & ".zip"
    End If
    
    'With fso4zip.CreateTextFile(zipPath, True)
        '.Write "PK" & Chr(5) & Chr(6) & String(18, 0)
        '.Close
    'End With
    
    fso4zip.CreateFolder (zipdirPath)
    
    Set zipFileFldr = fso4zip.GetFolder(targetFldr)
    'Set zipFldrNm = shellapp.Namespace(fso4zip.GetAbsolutePathName(zipPath))
 
    bozip = False
    
    For Each trgtFile In zipFileFldr.Files
        trgtFNm = trgtFile.name
        'If trgtFNm = "送付状" Or InStr(trgtFNm, ".pdf") > 0 Then
        If InStr(Right(trgtFNm, 4), ".pdf") > 0 Then
            Application.Wait (Now + TimeValue("0:00:02"))
            'setFileNm = fso4zip.GetAbsolutePathName(trgtFile)
            'zipFldrNm.CopyHere (setFileNm)
            'zipdirPath.copyhere (setFileNm)
            app = fso4zip.copyfile(trgtFile, zipdirPath & "\", True)
            bozip = True
        End If
    Next trgtFile
    
    If (bozip = True) Then
        Flg = Archive_Zip(zipdirPath & ".zip", zipdirPath, str_Pass)
    End If
    
    Application.Wait (Now + TimeValue("0:00:02"))
    'Do Until zipFldrNm.items().count = setCnt
        'Application.Wait (Now + TimeValue("0:00:01"))
    'Loop
    
    fso4zip.deleteFolder zipdirPath
    
    Set fso4zip = Nothing
    Set shellapp = Nothing
    
    If (bozip = True And Flg = 0) Then
        TargetFileZip = zipdirPath & ".zip"
    Else
        TargetFileZip = ""
    End If
    
End Function

'******************************稼働機能の追加 WuYang 2016/06/08 Start******************************
Private Sub CommandButton7_Click()

    Dim kadouSheet
    Dim Outday, today, orderFormName
    Dim wb As Workbook
    Dim j
    Dim flg1
    flg1 = 0
    j = 11
    cnt = 1
    
    'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\テンプレート\受発注必要テンプレート\【TSRS㈱】受発注-契約管理台帳.xls"
    'templatePath_mail = Application.ActiveWorkbook.path & "\テンプレート\受発注必要テンプレート\【TSRS㈱】受発注-（契約更新確認用）送付状_メール発送.xls"
    templatePath_PJ = Application.ActiveWorkbook.path & "\テンプレート\受発注必要テンプレート\【TSRS㈱】PJ推進-ローテーション管理（入退再営業一覧）.xls"

    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath1 = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    savePath2 = savePath1 & "\受発注-契約管理台帳（" & month(Date) & "月分）"
    If Dir(savePath2, vbDirectory) = "" Then
        MkDir savePath2
    End If
    savePath = savePath2 & "\受発注-契約管理台帳_" & Format(Date, "yyyymmdd")
    If Dir(savePath, vbDirectory) = "" Then
        MkDir savePath
    End If
    'ファイル名の作成
    orderFormName = "【TSRS㈱" & month(Date) & "月分】受発注-契約管理台帳.xls"
    'orderFormName_mail = "【TSRS㈱" & month(Date) & "月分】受発注-（契約更新確認用）送付状_メール発送.xls"
    orderFormName_PJ = "【TSRS㈱" & month(Date) & "月分】PJ推進-ローテーション管理（入退再営業一覧）.xls"
    orderFormPath = savePath & "\" & orderFormName
    'orderFormPath_mail = savePath & "\" & orderFormName_mail
    orderFormPath_PJ = savePath & "\" & orderFormName_PJ
    
    'ファイルが開くか判断
    For Each wb In Workbooks
        If wb.name = Dir(orderFormPath) Then
        MsgBox orderFormName & "はすでに開いています"
        Exit Sub
        End If
        'If wb.name = Dir(orderFormPath_mail) Then
        'MsgBox orderFormPath_mail & "はすでに開いています"
        'Exit Sub
        'End If
        If wb.name = Dir(orderFormPath_PJ) Then
        MsgBox orderFormPath_PJ & "はすでに開いています"
        Exit Sub
        End If
    Next wb
    
    If Dir(orderFormPath) <> "" Then
        Kill orderFormPath
    End If
    'If Dir(orderFormPath_mail) <> "" Then
        'Kill orderFormPath_mail
    'End If
    If Dir(orderFormPath_PJ) <> "" Then
        Kill orderFormPath_PJ
    End If
    FileCopy templatePath, orderFormPath
    'FileCopy templatePath_mail, orderFormPath_mail
    FileCopy templatePath_PJ, orderFormPath_PJ
        
    Set mastersheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    endRowNo = mastersheet.UsedRange.Rows(mastersheet.UsedRange.Rows.count).Row
    
    'ファイルを編集
    Set workConditionsFile = Workbooks.Open(orderFormPath)
    Set kadouSheet = Application.Workbooks(orderFormName).Worksheets("契約管理台帳（月次）")
    Set clientSheet = Application.Workbooks(orderFormName).Worksheets("顧客情報")
    Set MenmberSheet = Application.Workbooks(orderFormName).Worksheets("社員情報")
    clientSheet.Range("B2").Value = Format(Date, "yyyy/mm/dd")
    MenmberSheet.Range("B21").Value = Format(Date, "yyyy/mm/dd")
    
    For i = 1 To endRowNo - 3
        'レコードを追加
        If i <> endRowNo - 3 Then
            kadouSheet.Rows(j).Copy
            kadouSheet.Rows(j + 1).Insert
            clientSheet.Rows(j - 7).Copy
            clientSheet.Rows(j - 6).Insert
            MenmberSheet.Rows(j + 12).Copy
            MenmberSheet.Rows(j + 13).Insert
        End If
        '番号
        kadouSheet.Range("B" & j).Value = cnt
        '社員名
        kadouSheet.Range("C" & j).Value = mastersheet.Range("B" & i + 3).Value
        '社員番号
        kadouSheet.Range("D" & j).Value = mastersheet.Range("A" & i + 3).Value
        '希望雇用形態
        kadouSheet.Range("E" & j).Value = mastersheet.Range("E" & i + 3).Value
        '社内担当者
        kadouSheet.Range("F" & j).Value = mastersheet.Range("AO" & i + 3).Value
        '入社日
        kadouSheet.Range("G" & j).Value = mastersheet.Range("U" & i + 3).Value
        '契約開始日
        kadouSheet.Range("H" & j).Value = mastersheet.Range("AT" & i + 3).Value
        '契約終了日
        kadouSheet.Range("I" & j).Value = mastersheet.Range("AU" & i + 3).Value
        'クライアントID
        kadouSheet.Range("P" & j).Value = mastersheet.Range("BL" & i + 3).Value
        'クライアント名
        kadouSheet.Range("Q" & j).Value = mastersheet.Range("BM" & i + 3).Value
        '案件ID
        kadouSheet.Range("R" & j).Value = mastersheet.Range("F" & i + 3).Value
        '案件名
        kadouSheet.Range("S" & j).Value = mastersheet.Range("AE" & i + 3).Value
        '顧客担当名
        'kadouSheet.Range("O" & j).Value = mastersheet.Range("AW" & i + 3).Value
        '受注金額
        kadouSheet.Range("T" & j).Value = mastersheet.Range("I" & i + 3).Value
        '支払サイト（上位）
         kadouSheet.Range("U" & j).Value = mastersheet.Range("BZ" & i + 3).Value
        '案件期間(クライアント)
        kadouSheet.Range("W" & j).Value = mastersheet.Range("DY" & i + 3).Value
        kadouSheet.Range("X" & j).Value = mastersheet.Range("DZ" & i + 3).Value
        kadouSheet.Range("Y" & j).Value = mastersheet.Range("EA" & i + 3).Value
        kadouSheet.Range("Z" & j).Value = mastersheet.Range("EB" & i + 3).Value
        kadouSheet.Range("AA" & j).Value = mastersheet.Range("EC" & i + 3).Value
        kadouSheet.Range("AB" & j).Value = mastersheet.Range("ED" & i + 3).Value
        kadouSheet.Range("AC" & j).Value = mastersheet.Range("EE" & i + 3).Value
        kadouSheet.Range("AD" & j).Value = mastersheet.Range("EF" & i + 3).Value
        kadouSheet.Range("AE" & j).Value = mastersheet.Range("EG" & i + 3).Value
        kadouSheet.Range("AF" & j).Value = mastersheet.Range("EH" & i + 3).Value
        kadouSheet.Range("AG" & j).Value = mastersheet.Range("EI" & i + 3).Value
        kadouSheet.Range("AH" & j).Value = mastersheet.Range("EJ" & i + 3).Value
        kadouSheet.Range("AI" & j).Value = mastersheet.Range("EK" & i + 3).Value
        
        '社員番号
        kadouSheet.Range("AN" & j).Value = mastersheet.Range("A" & i + 3).Value
        '社員名
        kadouSheet.Range("AM" & j).Value = mastersheet.Range("B" & i + 3).Value
        '年齢
        agedate = mastersheet.Range("CC" & i + 3).Value
        If (Format(agedate, "mm/dd") > Format(Date, "mm/dd")) Then
            age = DateDiff("yyyy", agedate, Date) - 1
        Else
            age = DateDiff("yyyy", agedate, Date)
        End If
        kadouSheet.Range("AO" & j).Value = age
        '性別
        kadouSheet.Range("AP" & j).Value = mastersheet.Range("CB" & i + 3).Value
        '発注額
        kadouSheet.Range("AQ" & j).Value = mastersheet.Range("J" & i + 3).Value
        '支払サイト（要員）
        kadouSheet.Range("AR" & j).Value = mastersheet.Range("DI" & i + 3).Value
        '希望職種
        kadouSheet.Range("AS" & j).Value = mastersheet.Range("CA" & i + 3).Value
        '退社日
        kadouSheet.Range("AT" & j).Value = mastersheet.Range("DV" & i + 3).Value
        
        '案件期間(社員)
        today = Format(Date, "YYYYMMDD")
        leaveday = Format(mastersheet.Range("DV" & i + 3).Value, "YYYYMMDD")
        If (Trim(leaveday) <> "") Then
            leaveyear = Mid(leaveday, 1, 4)
            leavemonth = Mid(leaveday, 5, 2)
            todayyear = Mid(today, 1, 4)
            If (leaveyear = todayyear And leavemonth = "12") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
                kadouSheet.Range("BC" & j).Value = mastersheet.Range("EF" & i + 3).Value
                kadouSheet.Range("BD" & j).Value = mastersheet.Range("EG" & i + 3).Value
                kadouSheet.Range("BE" & j).Value = mastersheet.Range("EH" & i + 3).Value
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear <> todayyear And leavemonth = "01") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
                kadouSheet.Range("BC" & j).Value = mastersheet.Range("EF" & i + 3).Value
                kadouSheet.Range("BD" & j).Value = mastersheet.Range("EG" & i + 3).Value
                kadouSheet.Range("BE" & j).Value = mastersheet.Range("EH" & i + 3).Value
                kadouSheet.Range("BF" & j).Value = mastersheet.Range("EI" & i + 3).Value
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear <> todayyear And leavemonth = "02") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
                kadouSheet.Range("BC" & j).Value = mastersheet.Range("EF" & i + 3).Value
                kadouSheet.Range("BD" & j).Value = mastersheet.Range("EG" & i + 3).Value
                kadouSheet.Range("BE" & j).Value = mastersheet.Range("EH" & i + 3).Value
                kadouSheet.Range("BF" & j).Value = mastersheet.Range("EI" & i + 3).Value
                kadouSheet.Range("BG" & j).Value = mastersheet.Range("EJ" & i + 3).Value
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "03") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = "退社"
                kadouSheet.Range("AW" & j).Interior.ColorIndex = 48
                kadouSheet.Range("AX" & j).Value = "退社"
                kadouSheet.Range("AX" & j).Interior.ColorIndex = 48
                kadouSheet.Range("AY" & j).Value = "退社"
                kadouSheet.Range("AY" & j).Interior.ColorIndex = 48
                kadouSheet.Range("AZ" & j).Value = "退社"
                kadouSheet.Range("AZ" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BA" & j).Value = "退社"
                kadouSheet.Range("BA" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BB" & j).Value = "退社"
                kadouSheet.Range("BB" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "04") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = "退社"
                kadouSheet.Range("AX" & j).Interior.ColorIndex = 48
                kadouSheet.Range("AY" & j).Value = "退社"
                kadouSheet.Range("AY" & j).Interior.ColorIndex = 48
                kadouSheet.Range("AZ" & j).Value = "退社"
                kadouSheet.Range("AZ" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BA" & j).Value = "退社"
                kadouSheet.Range("BA" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BB" & j).Value = "退社"
                kadouSheet.Range("BB" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "05") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = "退社"
                kadouSheet.Range("AY" & j).Interior.ColorIndex = 48
                kadouSheet.Range("AZ" & j).Value = "退社"
                kadouSheet.Range("AZ" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BA" & j).Value = "退社"
                kadouSheet.Range("BA" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BB" & j).Value = "退社"
                kadouSheet.Range("BB" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "06") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = "退社"
                kadouSheet.Range("AZ" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BA" & j).Value = "退社"
                kadouSheet.Range("BA" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BB" & j).Value = "退社"
                kadouSheet.Range("BB" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "07") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = "退社"
                kadouSheet.Range("BA" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BB" & j).Value = "退社"
                kadouSheet.Range("BB" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "08") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = "退社"
                kadouSheet.Range("BB" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "09") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
                kadouSheet.Range("BC" & j).Value = "退社"
                kadouSheet.Range("BC" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "10") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
                kadouSheet.Range("BC" & j).Value = mastersheet.Range("EF" & i + 3).Value
                kadouSheet.Range("BD" & j).Value = "退社"
                kadouSheet.Range("BD" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            ElseIf (leaveyear = todayyear And leavemonth = "11") Then
                kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
                kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
                kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
                kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
                kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
                kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
                kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
                kadouSheet.Range("BC" & j).Value = mastersheet.Range("EF" & i + 3).Value
                kadouSheet.Range("BD" & j).Value = mastersheet.Range("EG" & i + 3).Value
                kadouSheet.Range("BE" & j).Value = "退社"
                kadouSheet.Range("BE" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BF" & j).Value = "退社"
                kadouSheet.Range("BF" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BG" & j).Value = "退社"
                kadouSheet.Range("BG" & j).Interior.ColorIndex = 48
                kadouSheet.Range("BH" & j).Value = "退社"
                kadouSheet.Range("BH" & j).Interior.ColorIndex = 48
            End If
        Else
            kadouSheet.Range("AV" & j).Value = mastersheet.Range("DY" & i + 3).Value
            kadouSheet.Range("AW" & j).Value = mastersheet.Range("DZ" & i + 3).Value
            kadouSheet.Range("AX" & j).Value = mastersheet.Range("EA" & i + 3).Value
            kadouSheet.Range("AY" & j).Value = mastersheet.Range("EB" & i + 3).Value
            kadouSheet.Range("AZ" & j).Value = mastersheet.Range("EC" & i + 3).Value
            kadouSheet.Range("BA" & j).Value = mastersheet.Range("ED" & i + 3).Value
            kadouSheet.Range("BB" & j).Value = mastersheet.Range("EE" & i + 3).Value
            kadouSheet.Range("BC" & j).Value = mastersheet.Range("EF" & i + 3).Value
            kadouSheet.Range("BD" & j).Value = mastersheet.Range("EG" & i + 3).Value
            kadouSheet.Range("BE" & j).Value = mastersheet.Range("EH" & i + 3).Value
            kadouSheet.Range("BF" & j).Value = mastersheet.Range("EI" & i + 3).Value
            kadouSheet.Range("BG" & j).Value = mastersheet.Range("EJ" & i + 3).Value
            kadouSheet.Range("BH" & j).Value = mastersheet.Range("EK" & i + 3).Value
        End If
        
        If (kadouSheet.Range("W" & j).Value = "●" And kadouSheet.Range("AV" & j).Value = "退社") Then
            kadouSheet.Range("AV" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("X" & j).Value = "●" And kadouSheet.Range("AW" & j).Value = "退社") Then
            kadouSheet.Range("AW" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("Y" & j).Value = "●" And kadouSheet.Range("AX" & j).Value = "退社") Then
            kadouSheet.Range("AX" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("Z" & j).Value = "●" And kadouSheet.Range("AY" & j).Value = "退社") Then
            kadouSheet.Range("AY" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AA" & j).Value = "●" And kadouSheet.Range("AZ" & j).Value = "退社") Then
            kadouSheet.Range("AZ" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AB" & j).Value = "●" And kadouSheet.Range("BA" & j).Value = "退社") Then
            kadouSheet.Range("BA" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AC" & j).Value = "●" And kadouSheet.Range("BB" & j).Value = "退社") Then
            kadouSheet.Range("BB" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AD" & j).Value = "●" And kadouSheet.Range("BC" & j).Value = "退社") Then
            kadouSheet.Range("BC" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AE" & j).Value = "●" And kadouSheet.Range("BD" & j).Value = "退社") Then
            kadouSheet.Range("BD" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AF" & j).Value = "●" And kadouSheet.Range("BE" & j).Value = "退社") Then
            kadouSheet.Range("BE" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AG" & j).Value = "●" And kadouSheet.Range("BF" & j).Value = "退社") Then
            kadouSheet.Range("BF" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AH" & j).Value = "●" And kadouSheet.Range("BG" & j).Value = "退社") Then
            kadouSheet.Range("BG" & j).Interior.ColorIndex = 3
        End If
        If (kadouSheet.Range("AI" & j).Value = "●" And kadouSheet.Range("BH" & j).Value = "退社") Then
            kadouSheet.Range("BH" & j).Interior.ColorIndex = 3
        End If
        
        Set Outday = mastersheet.Range("AU" & i + 3)
        diffday = DateDiff("d", Date, Outday)
        
        clientSheet.Range("E" & j - 7).Value = mastersheet.Range("AW" & i + 3).Value
        MenmberSheet.Range("I" & j + 12).Value = mastersheet.Range("AW" & i + 3).Value
        clientSheet.Range("F" & j - 7).Value = mastersheet.Range("AT" & i + 3).Value
        clientSheet.Range("G" & j - 7).Value = mastersheet.Range("AU" & i + 3).Value
        MenmberSheet.Range("J" & j + 12).Value = mastersheet.Range("AT" & i + 3).Value
        MenmberSheet.Range("K" & j + 12).Value = mastersheet.Range("AU" & i + 3).Value
        clientSheet.Range("K" & j - 7).Value = mastersheet.Range("BE" & i + 3).Value
        kadouSheet.Range("BP" & j).Value = mastersheet.Range("AW" & i + 3).Value
        kadouSheet.Range("BQ" & j).Value = mastersheet.Range("BE" & i + 3).Value
        If (diffday >= 35) Then
            'リリース時間
            clientSheet.Range("H" & j - 7).Value = diffday
            MenmberSheet.Range("M" & j + 12).Value = diffday
                       
            If (Trim(MenmberSheet.Range("L" & j + 12).Value) <> "") Then
                MenmberSheet.Range("O" & j + 12).Value = "退社"
                MenmberSheet.Range("O" & j + 12).Interior.ColorIndex = 48
                MenmberSheet.Range("M" & j + 12).Interior.ColorIndex = 48
                clientSheet.Range("J" & j - 7).Value = "退社"
                clientSheet.Range("J" & j - 7).Interior.ColorIndex = 48
                If (DateDiff("d", MenmberSheet.Range("L" & j + 12).Value, Outday) > 0) Then
                    clientSheet.Range("I" & j - 7).Value = "●"
                    MenmberSheet.Range("N" & j + 12).Value = "●"
                    MenmberSheet.Range("N" & j + 12).Interior.ColorIndex = 6
                    clientSheet.Range("J" & j - 7).Value = "要"
                    clientSheet.Range("J" & j - 7).Interior.ColorIndex = 3
                    clientSheet.Range("I" & j - 7).Interior.ColorIndex = 6
                End If
            Else
                clientSheet.Range("J" & j - 7).Value = "正常"
                clientSheet.Range("J" & j - 7).Interior.ColorIndex = 43
                MenmberSheet.Range("P" & j + 12).Value = "正常"
                MenmberSheet.Range("P" & j + 12).Interior.ColorIndex = 43
            End If
            
        ElseIf (diffday > 0 And diffday < 35) Then
            clientSheet.Range("H" & j - 7).Value = diffday
            clientSheet.Range("H" & j - 7).Interior.ColorIndex = 40
            clientSheet.Range("J" & j - 7).Value = "要"
            clientSheet.Range("J" & j - 7).Interior.ColorIndex = 40
            
            If (Trim(MenmberSheet.Range("L" & j + 12).Value) <> "") Then
                MenmberSheet.Range("O" & j + 12).Value = "退社"
                MenmberSheet.Range("O" & j + 12).Interior.ColorIndex = 48
                MenmberSheet.Range("M" & j + 12).Value = diffday
                MenmberSheet.Range("M" & j + 12).Interior.ColorIndex = 48
                clientSheet.Range("J" & j - 7).Value = "退社"
                clientSheet.Range("J" & j - 7).Interior.ColorIndex = 48
                If (DateDiff("d", MenmberSheet.Range("L" & j + 12).Value, Outday) > 0) Then
                    clientSheet.Range("I" & j - 7).Value = "●"
                    MenmberSheet.Range("N" & j + 12).Value = "●"
                    MenmberSheet.Range("N" & j + 12).Interior.ColorIndex = 6
                    clientSheet.Range("J" & j - 7).Value = "要"
                    clientSheet.Range("J" & j - 7).Interior.ColorIndex = 3
                    clientSheet.Range("I" & j - 7).Interior.ColorIndex = 6
                End If
            Else
                MenmberSheet.Range("O" & j + 12).Value = "要"
                MenmberSheet.Range("O" & j + 12).Interior.ColorIndex = 40
                MenmberSheet.Range("M" & j + 12).Value = diffday
                MenmberSheet.Range("M" & j + 12).Interior.ColorIndex = 40
            End If
        Else
            clientSheet.Range("H" & j - 7).Value = "終了"
            clientSheet.Range("H" & j - 7).Interior.ColorIndex = 3
            
            clientSheet.Range("J" & j - 7).Value = "要"
            clientSheet.Range("J" & j - 7).Interior.ColorIndex = 3
            
            If (Trim(MenmberSheet.Range("M" & j + 12).Value) <> "") Then
                MenmberSheet.Range("O" & j + 12).Value = "退社"
                MenmberSheet.Range("O" & j + 12).Interior.ColorIndex = 48
                MenmberSheet.Range("M" & j + 12).Value = "アイドル"
                MenmberSheet.Range("M" & j + 12).Interior.ColorIndex = 48
            Else
                MenmberSheet.Range("O" & j + 12).Value = "危ない"
                MenmberSheet.Range("O" & j + 12).Interior.ColorIndex = 3
                MenmberSheet.Range("M" & j + 12).Value = "アイドル"
                MenmberSheet.Range("M" & j + 12).Interior.ColorIndex = 3
            End If
        End If
        
        '案件終了時間（Day）
        kadouSheet.Range("M" & j).Value = clientSheet.Range("H" & j - 7).Value
        If (kadouSheet.Range("M" & j).Value <= 15) Then
            kadouSheet.Range("M" & j).Interior.ColorIndex = 3
        End If
        '案件終了前退社メンバー
        kadouSheet.Range("N" & j).Value = clientSheet.Range("I" & j - 7).Value
        If (kadouSheet.Range("N" & j).Value = "●") Then
            kadouSheet.Range("N" & j).Interior.ColorIndex = 3
        End If
        '連絡:連携
        kadouSheet.Range("O" & j).Value = clientSheet.Range("J" & j - 7).Value
        If (kadouSheet.Range("O" & j).Value = "要") Then
            kadouSheet.Range("O" & j).Interior.ColorIndex = 44
        End If
        '案件終了時間（Day）
        kadouSheet.Range("BK" & j).Value = MenmberSheet.Range("M" & j + 12).Value
        If (kadouSheet.Range("BK" & j).Value = "アイドル") Then
            kadouSheet.Range("BK" & j).Interior.ColorIndex = 3
        End If
        '案件終了前退社
        kadouSheet.Range("BL" & j).Value = MenmberSheet.Range("N" & j + 12).Value
        If (kadouSheet.Range("BL" & j).Value = "●") Then
            kadouSheet.Range("BL" & j).Interior.ColorIndex = 3
        End If
        '案件探す
        kadouSheet.Range("BM" & j).Value = MenmberSheet.Range("O" & j + 12).Value
        If (kadouSheet.Range("BM" & j).Value = "危ない") Then
            kadouSheet.Range("BM" & j).Interior.ColorIndex = 3
        ElseIf (kadouSheet.Range("BM" & j).Value = "要") Then
            kadouSheet.Range("BM" & j).Interior.ColorIndex = 44
        End If
        
        j = j + 1
        cnt = cnt + 1
        Flg = 1
    Next i
    
    'Call SetkadouSendMail(orderFormPath_mail, kadouSheet, MenmberSheet, orderFormName_mail)
    Call SetkadouPJ(orderFormPath_PJ, kadouSheet, orderFormName_PJ)
    If (Flg = 0) Then
        Workbooks(orderFormName).Close
        MsgBox "稼働情報がありません"
        Exit Sub
    End If
    'ファイル自動保存
    Workbooks(orderFormName).Save
    Workbooks(orderFormName).Close
    MsgBox "稼働情報を作成しました"
    'Application.Workbooks("月次処理マクロ.xls").Close
End Sub

Private Sub SetkadouPJ(ByVal targetPJPath, ByVal kadouSheet, ByVal orderFormName_PJ)
    Dim targetNo As Integer
    Dim Endmonth As Integer
    Dim reFlg As Boolean
    Set targetPJFile = Workbooks.Open(targetPJPath)
    Set targetPJSheet = Application.Workbooks(orderFormName_PJ).Worksheets("Rotation")
    targetNo = 5
    titleNo = 1
    Endmonth = 0
    reFlg = False
    endrowno_kadou = kadouSheet.UsedRange.Rows(kadouSheet.UsedRange.Rows.count).Row
    For i = 11 To endrowno_kadou
        If (Trim(kadouSheet.Range("AT" & i).Value) = "" And Trim(kadouSheet.Range("BK" & i).Value) <> "") Then
            If (kadouSheet.Range("BK" & i).Value < 41 Or kadouSheet.Range("BK" & i).Value = "アイドル") Then
                If targetNo > 18 Then
                    targetPJSheet.Rows(targetNo).Copy
                    targetPJSheet.Rows(targetNo + 1).Insert
                End If
                'No.
                targetPJSheet.Range("A" & targetNo).Value = titleNo
                '終了日
                targetPJSheet.Range("B" & targetNo).Value = kadouSheet.Range("I" & i).Value
                'ID
                targetPJSheet.Range("C" & targetNo).Value = kadouSheet.Range("D" & i).Value
                '氏名
                targetPJSheet.Range("D" & targetNo).Value = kadouSheet.Range("C" & i).Value
                '再販区分
                'targetPJSheet.Range("E" & targetNo).Value
                '担当者
                targetPJSheet.Range("F" & targetNo).Value = kadouSheet.Range("F" & i).Value
                'クライアント
                targetPJSheet.Range("G" & targetNo).Value = kadouSheet.Range("Q" & i).Value
                'クライアントID
                targetPJSheet.Range("H" & targetNo).Value = kadouSheet.Range("P" & i).Value
                '案件
                targetPJSheet.Range("I" & targetNo).Value = kadouSheet.Range("S" & i).Value
                '案件ID
                targetPJSheet.Range("J" & targetNo).Value = kadouSheet.Range("R" & i).Value
                '業務開始日
                targetPJSheet.Range("K" & targetNo).Value = kadouSheet.Range("H" & i).Value
                'Enduser
                targetPJSheet.Range("L" & targetNo).Value = kadouSheet.Range("J" & i).Value
                '単価
                targetPJSheet.Range("M" & targetNo).Value = kadouSheet.Range("T" & i).Value
                reFlg = True
            End If
        End If
        If Trim(kadouSheet.Range("AT" & i).Value) <> "" Then
            If targetNo > 18 Then
                targetPJSheet.Rows(targetNo).Copy
                targetPJSheet.Rows(targetNo + 1).Insert
            End If
            'No.
            targetPJSheet.Range("A" & targetNo).Value = titleNo
            '終了日
            targetPJSheet.Range("B" & targetNo).Value = kadouSheet.Range("I" & i).Value
            'ID
            targetPJSheet.Range("C" & targetNo).Value = kadouSheet.Range("D" & i).Value
            '氏名
            targetPJSheet.Range("D" & targetNo).Value = kadouSheet.Range("C" & i).Value
            '再販区分
            targetPJSheet.Range("E" & targetNo).Value = "退社"
            '担当者
            targetPJSheet.Range("F" & targetNo).Value = kadouSheet.Range("F" & i).Value
            'クライアント
            targetPJSheet.Range("G" & targetNo).Value = kadouSheet.Range("Q" & i).Value
            'クライアントID
            targetPJSheet.Range("H" & targetNo).Value = kadouSheet.Range("P" & i).Value
            '案件
            targetPJSheet.Range("I" & targetNo).Value = kadouSheet.Range("S" & i).Value
            '案件ID
            targetPJSheet.Range("J" & targetNo).Value = kadouSheet.Range("R" & i).Value
            '業務開始日
            targetPJSheet.Range("K" & targetNo).Value = kadouSheet.Range("H" & i).Value
            'Enduser
            targetPJSheet.Range("L" & targetNo).Value = kadouSheet.Range("J" & i).Value
            '単価
            targetPJSheet.Range("M" & targetNo).Value = kadouSheet.Range("T" & i).Value
            targetPJSheet.Range("A" & targetNo & ":" & "P" & targetNo).Interior.ColorIndex = 15
        End If
        If (reFlg = True) Then
            targetNo = targetNo + 1
            titleNo = titleNo + 1
        End If
    Next i
     'ファイル自動保存
    Workbooks(orderFormName_PJ).Save
    Workbooks(orderFormName_PJ).Close
End Sub
'******************************稼働機能の追加 WuYang 2016/06/08 end******************************

'******************************稼働機能の追加 WuYang 2016/06/14 Start******************************
'勤怠比較表月次チェック確認
Private Sub CommandButton8_Click()
    Dim kadouSheet
    Dim Outday, today, orderFormName, syuukeipath
    Dim wb As Workbook
    Dim j
    Dim flg1
    Dim fileflg1
    Dim fileflg2
    Dim fileflg3
    fileflg1 = False
    fileflg2 = False
    fileflg3 = False
    flg1 = 0
    j = 7
    cnt = 1
    
    'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\テンプレート\月処理必要テンプレート\【ＴＳＲＳ㈱】勤怠比較表月次チェック確認.xls"
    Set mainSheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("Main")
    
    '勤怠集計表フォルダパス
    syuukeipath = TextBox4.Text()
        
    If (syuukeipath = "") Then
        MsgBox "対象月稼動勤務勤怠集計表を取込してください！"
        Exit Sub
    End If
           
    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    'ファイル名の作成
    'orderFormName = "【ＴＳＲＳ㈱】勤怠比較表月次チェック確認_" & Format(Date, "YYYYMMDD") & ".xls"
    orderFormName = "【ＴＳＲＳ㈱" & month(Date) & "月分】勤怠比較表月次チェック確認.xls"
    orderFormPath = savePath & "\" & orderFormName
    
    'ファイルが開くか判断
    For Each wb In Workbooks
        If wb.name = Dir(orderFormPath) Then
        MsgBox orderFormName & "はすでに開いています"
        Exit Sub
        End If
    Next wb
    
    If Dir(savePath & "\" & "【ＴＳＲＳ㈱】勤怠比較表月次チェック確認_" & Format(Date, "YYYYMMDD") & ".xls") <> "" Then
        Kill savePath & "\" & "【ＴＳＲＳ㈱】勤怠比較表月次チェック確認_" & Format(Date, "YYYYMMDD") & ".xls"
    End If
    FileCopy templatePath, orderFormPath
    
    'ファイルを編集
    Set workConditionsFile = Workbooks.Open(orderFormPath)
    Set matchSheet = Application.Workbooks(orderFormName).Worksheets("＃月次比較表")
    
    Set Fso_ = CreateObject("Scripting.FileSystemObject")
    Set Folder = Fso_.GetFolder(syuukeipath)
    
    Set Common.staffDict = Common.GetDictionaryInstance()
    Set dict = CreateObject("Scripting.Dictionary")
    Dim syuukeiName(1 To 3) As String
    
    '支給控除一覧表取込
    FileNameNum = 1
    For Each file In Folder.Files
        If InStr(file.name, "稼動勤務勤怠集計表") > 0 Then
            syuukeiName(FileNameNum) = file.name
            mypath = syuukeipath & "\" & file.name
            Workbooks.Open fileName:=mypath
            FileNameNum = FileNameNum + 1
        End If
    Next
    If (syuukeiName(1) <> "") Then
        fileflg1 = True
        Set fileStrmonth1sheet = Application.Workbooks(syuukeiName(1)).Worksheets("勤怠集計表")
        endrowno1 = fileStrmonth1sheet.Cells(Rows.count, 1).End(xlUp).Row
        intIndexM1 = InStr(syuukeiName(1), "月")
        intIndexY1 = InStr(syuukeiName(1), "年")
        StrMonth1 = Mid(syuukeiName(1), intIndexY1 + 1, intIndexM1 - intIndexY1)
        'タイトル設定
        matchSheet.Range("B7").Value = StrMonth1
        matchSheet.Range("G7").Value = StrMonth1
        matchSheet.Range("L7").Value = StrMonth1
        matchSheet.Range("Q7").Value = StrMonth1
        matchSheet.Range("X7").Value = StrMonth1
        matchSheet.Range("AC7").Value = StrMonth1
        matchSheet.Range("AH7").Value = StrMonth1
        matchSheet.Range("AM7").Value = StrMonth1
        matchSheet.Range("AR7").Value = StrMonth1
        matchSheet.Range("AU7").Value = StrMonth1
        matchSheet.Range("AX7").Value = StrMonth1
    End If
    
    If (syuukeiName(2) <> "") Then
        fileflg2 = True
        intIndexM2 = InStr(syuukeiName(2), "月")
        intIndexY2 = InStr(syuukeiName(2), "年")
        StrMonth2 = Mid(syuukeiName(2), intIndexY2 + 1, intIndexM2 - intIndexY2)
        Set fileStrmonth2sheet = Application.Workbooks(syuukeiName(2)).Worksheets("勤怠集計表")
        endrowno2 = fileStrmonth2sheet.Cells(Rows.count, 1).End(xlUp).Row
        'タイトル設定
        matchSheet.Range("C7").Value = StrMonth2
        matchSheet.Range("H7").Value = StrMonth2
        matchSheet.Range("M7").Value = StrMonth2
        matchSheet.Range("S7").Value = StrMonth2
        matchSheet.Range("Y7").Value = StrMonth2
        matchSheet.Range("AD7").Value = StrMonth2
        matchSheet.Range("AI7").Value = StrMonth2
        matchSheet.Range("AN7").Value = StrMonth2
        matchSheet.Range("AS7").Value = StrMonth2
        matchSheet.Range("AV7").Value = StrMonth2
        matchSheet.Range("AY7").Value = StrMonth2
    End If
    
    If (syuukeiName(3) <> "") Then
        fileflg3 = True
        intIndexM3 = InStr(syuukeiName(3), "月")
        intIndexY3 = InStr(syuukeiName(3), "年")
        StrMonth3 = Mid(syuukeiName(3), intIndexY3 + 1, intIndexM3 - intIndexY3)
        Set fileStrmonth3sheet = Application.Workbooks(syuukeiName(3)).Worksheets("勤怠集計表")
        endrowno3 = fileStrmonth3sheet.Cells(Rows.count, 1).End(xlUp).Row
        'タイトル設定
        matchSheet.Range("D7").Value = StrMonth3
        matchSheet.Range("I7").Value = StrMonth3
        matchSheet.Range("N7").Value = StrMonth3
        matchSheet.Range("U7").Value = StrMonth3
        matchSheet.Range("Z7").Value = StrMonth3
        matchSheet.Range("AE7").Value = StrMonth3
        matchSheet.Range("AJ7").Value = StrMonth3
        matchSheet.Range("AO7").Value = StrMonth3
        matchSheet.Range("AT7").Value = StrMonth3
        matchSheet.Range("AW7").Value = StrMonth3
        matchSheet.Range("AZ7").Value = StrMonth3
    End If
    
    If (fileflg1 = True) Then
        For n = 1 To endrowno1 - 5
            If (endrowno1 - 5 > n) Then
                Call matchSheet.Range(n + 7 & ":" & n + 7).Copy(matchSheet.Range(n + 8 & ":" & n + 8))
            End If
            '番号
            matchSheet.Range("A" & n + 7).Value = n
            '支払日
            matchSheet.Range("B" & n + 7).Value = fileStrmonth1sheet.Range("B" & n + 5).Value
            '締日
            matchSheet.Range("G" & n + 7).Value = fileStrmonth1sheet.Range("C" & n + 5).Value
            '支払いサイト
            matchSheet.Range("L" & n + 7).Value = fileStrmonth1sheet.Range("D" & n + 5).Value
            '要員ID
            matchSheet.Range("Q" & n + 7).Value = fileStrmonth1sheet.Range("E" & n + 5).Value
            empolyeeID = fileStrmonth1sheet.Range("E" & n + 5).Value
            '要員氏名
            matchSheet.Range("R" & n + 7).Value = fileStrmonth1sheet.Range("F" & n + 5).Value
            '下限
            matchSheet.Range("X" & n + 7).Value = fileStrmonth1sheet.Range("P" & n + 5).Value
            '上限
            matchSheet.Range("AC" & n + 7).Value = fileStrmonth1sheet.Range("Q" & n + 5).Value
            '精算単位
            matchSheet.Range("AH" & n + 7).Value = fileStrmonth1sheet.Range("AO" & n + 5).Value
            '入社日
            matchSheet.Range("AM" & n + 7).Value = fileStrmonth1sheet.Range("T" & n + 5).Value
            '備考
            matchSheet.Range("AR" & n + 7).Value = fileStrmonth1sheet.Range("AI" & n + 5).Value
            '作業報告書回収
            matchSheet.Range("AU" & n + 7).Value = fileStrmonth1sheet.Range("AJ" & n + 5).Value
            matchSheet.Range("AX" & n + 7).Value = fileStrmonth1sheet.Range("AK" & n + 5).Value
            
            If (fileflg2 = True) Then
                For j = 1 To endrowno2 - 5
                    If (fileStrmonth2sheet.Range("E" & j + 5).Value = empolyeeID) Then
                        '支払日
                        matchSheet.Range("C" & n + 7).Value = fileStrmonth2sheet.Range("B" & j + 5).Value
                        '締日
                        matchSheet.Range("H" & n + 7).Value = fileStrmonth2sheet.Range("C" & j + 5).Value
                        '支払いサイト
                        matchSheet.Range("M" & n + 7).Value = fileStrmonth2sheet.Range("D" & j + 5).Value
                        '要員ID
                        matchSheet.Range("S" & n + 7).Value = fileStrmonth2sheet.Range("E" & j + 5).Value
                        '要員氏名
                        matchSheet.Range("T" & n + 7).Value = fileStrmonth2sheet.Range("F" & j + 5).Value
                        '下限
                        matchSheet.Range("Y" & n + 7).Value = fileStrmonth2sheet.Range("P" & j + 5).Value
                        '上限
                        matchSheet.Range("AD" & n + 7).Value = fileStrmonth2sheet.Range("Q" & j + 5).Value
                        '精算単位
                        matchSheet.Range("AI" & n + 7).Value = fileStrmonth2sheet.Range("AO" & j + 5).Value
                        '入社日
                        matchSheet.Range("AN" & n + 7).Value = fileStrmonth2sheet.Range("T" & j + 5).Value
                        '備考
                        matchSheet.Range("AS" & n + 7).Value = fileStrmonth2sheet.Range("AI" & j + 5).Value
                        '作業報告書回収
                        matchSheet.Range("AV" & n + 7).Value = fileStrmonth2sheet.Range("AJ" & j + 5).Value
                        matchSheet.Range("AY" & n + 7).Value = fileStrmonth2sheet.Range("AK" & j + 5).Value
                    End If
                Next j
            End If
            
            If (fileflg3 = True) Then
                For i = 1 To endrowno3 - 5
                    If (fileStrmonth3sheet.Range("E" & i + 5).Value = empolyeeID) Then
                        '支払日
                        matchSheet.Range("D" & n + 7).Value = fileStrmonth3sheet.Range("B" & i + 5).Value
                        '締日
                        matchSheet.Range("I" & n + 7).Value = fileStrmonth3sheet.Range("C" & i + 5).Value
                        '支払いサイト
                        matchSheet.Range("N" & n + 7).Value = fileStrmonth3sheet.Range("D" & i + 5).Value
                        '要員ID
                        matchSheet.Range("U" & n + 7).Value = fileStrmonth3sheet.Range("E" & i + 5).Value
                        '要員氏名
                        matchSheet.Range("V" & n + 7).Value = fileStrmonth3sheet.Range("F" & i + 5).Value
                        '下限
                        matchSheet.Range("Z" & n + 7).Value = fileStrmonth3sheet.Range("P" & i + 5).Value
                        '上限
                        matchSheet.Range("AE" & n + 7).Value = fileStrmonth3sheet.Range("Q" & i + 5).Value
                        '精算単位
                        matchSheet.Range("AJ" & n + 7).Value = fileStrmonth3sheet.Range("AO" & i + 5).Value
                        '入社日
                        matchSheet.Range("AO" & n + 7).Value = fileStrmonth3sheet.Range("T" & i + 5).Value
                        '備考
                        matchSheet.Range("AT" & n + 7).Value = fileStrmonth3sheet.Range("AI" & i + 5).Value
                        '作業報告書回収
                        matchSheet.Range("AW" & n + 7).Value = fileStrmonth3sheet.Range("AJ" & i + 5).Value
                        matchSheet.Range("AZ" & n + 7).Value = fileStrmonth3sheet.Range("AK" & i + 5).Value
                    End If
                Next i
            End If
        Next n
    End If
    
    Application.Workbooks(orderFormName).Save
    If (syuukeiName(1) <> "") Then
        Application.Workbooks(syuukeiName(1)).Close
    End If
    If (syuukeiName(2) <> "") Then
        Application.Workbooks(syuukeiName(2)).Close
    End If
    If (syuukeiName(3) <> "") Then
        Application.Workbooks(syuukeiName(3)).Close
    End If
    Application.Workbooks(orderFormName).Close
    MsgBox StrMonth1 & "," & StrMonth2 & "," & StrMonth3 & "の勤怠を比較完了しました"
    
End Sub

'稼動勤務勤怠集計表の格納先
Private Sub CommandButton9_Click()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox4.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "ファイル読み失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub
'******************************稼働機能の追加 WuYang 2016/06/14 End******************************

'******************************稼働機能の追加 WuYang 2016/06/29 Start******************************
Private Sub CommandButton12_Click()
    
    'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\テンプレート\月処理必要テンプレート\明細チェック関連\【ＴＳＲＳ㈱＃月分】給与明細比較表.xls"
    
    '支給控除一覧表取込パス
    paygetpath = TextBox6.Text()
        
    If (Trim(paygetpath) = "") Then
        MsgBox "支給控除一覧表を取込してください！"
        Exit Sub
    End If
    
    '稼動勤務勤怠集計表取込パス
    kadoupath = TextBox7.Text()
        
    If (Trim(kadoupath) = "") Then
        MsgBox "稼動勤務勤怠集計表を取込してください！"
        Exit Sub
    End If
    
    'TSRS帳票管理マスタパス
    masterdatepath = TextBox9.Text()
        
    If (Trim(masterdatepath) = "") Then
        MsgBox "TSRS帳票管理マスタを取込してください！"
        Exit Sub
    End If
    
    '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    'ファイル名の作成
    targetFormNm = "【ＴＳＲＳ㈱" & month(Date) & "月分】給与明細比較表.xls"
    targetFormName = savePath & "\" & targetFormNm
    
    'ファイルが開くか判断
    For Each wb In Workbooks
        If wb.name = Dir(targetFormName) Then
        MsgBox targetFormName & "はすでに開いています"
        Exit Sub
        End If
    Next wb
    
    If Dir(targetFormName) <> "" Then
        Kill targetFormName
    End If
    FileCopy templatePath, targetFormName
    
    'ファイルを編集
    Set worktargetFile = Workbooks.Open(targetFormName)
    Set targetsheet = Application.Worksheets("給与明細比較表")
    targetendRowNo = targetsheet.Cells(Rows.count, 1).End(xlUp).Row
    
    Dim Folder As Object
    Dim SubFolder As Object
    Dim file As Object
    Dim paygetfileName(1 To 3) As String
    Dim kadoufileName(1 To 3) As String
    Dim masterfileName(1 To 3) As String
    
    Set Fso_paygetPath = CreateObject("Scripting.FileSystemObject")
    Set paygetFolder = Fso_paygetPath.GetFolder(paygetpath)
    
    Set Fso_kadouPath = CreateObject("Scripting.FileSystemObject")
    Set kaDouFolder = Fso_kadouPath.GetFolder(kadoupath)
    
    Set Fso_masterPath = CreateObject("Scripting.FileSystemObject")
    Set masterFolder = Fso_masterPath.GetFolder(masterdatepath)
    
    Set Common.staffDict = Common.GetDictionaryInstance()
    Set dict = CreateObject("Scripting.Dictionary")
    
    '支給控除一覧表取込
    paygetFileNameNum = 1
    For Each file In paygetFolder.Files
        If InStr(file.name, "支給控除一覧") > 0 Then
            paygetfileName(paygetFileNameNum) = file.name
            mypath = paygetpath & "\" & file.name
            Workbooks.Open fileName:=mypath
            paygetFileNameNum = paygetFileNameNum + 1
        End If
    Next
    If (paygetfileName(1) <> "") Then
        Set paygetfilesheet1 = Application.Workbooks(paygetfileName(1)).Worksheets("支給控除一覧表")
        endrowno_payget1 = paygetfilesheet1.Cells(Rows.count, 1).End(xlUp).Row
    End If
    
    Call targetsheet.Range("2:9").Copy(targetsheet.Range(targetendRowNo + 1 & ":" & targetendRowNo + 5))
    
    For paygetNum = 3 To endrowno_payget1
        If (paygetfileName(1) <> "") Then
            targetendRowNo = targetsheet.Cells(Rows.count, 1).End(xlUp).Row
            Call setpaygetvalue(targetsheet, paygetfilesheet1, targetendRowNo - 4, paygetNum)
            If (paygetNum <> endrowno_payget1) Then
                Call targetsheet.Range("5:9").Copy(targetsheet.Range(targetendRowNo + 1 & ":" & targetendRowNo + 5))
            End If
        End If
        
        If (paygetfileName(2) <> "") Then
            Set paygetfilesheet2 = Application.Workbooks(paygetfileName(2)).Worksheets("支給控除一覧表")
            endrowno_payget2 = paygetfilesheet2.Cells(Rows.count, 1).End(xlUp).Row
            paygetNum2 = ""
            For paygetNum2 = 3 To endrowno_payget2
                If (Trim(targetsheet.Range("C" & targetendRowNo - 4).Value) = Trim(paygetfilesheet2.Range("B" & paygetNum2).Value)) Then
                    Call setpaygetvalue(targetsheet, paygetfilesheet2, targetendRowNo - 3, paygetNum2)
                End If
            Next paygetNum2
        End If
        
        If (paygetfileName(3) <> "") Then
            Set paygetfilesheet3 = Application.Workbooks(paygetfileName(3)).Worksheets("支給控除一覧表")
            endrowno_payget3 = paygetfilesheet3.Cells(Rows.count, 1).End(xlUp).Row
            paygetNum3 = ""
            For paygetNum3 = 3 To endrowno_payget3
                If (Trim(targetsheet.Range("C" & targetendRowNo - 4).Value) = Trim(paygetfilesheet3.Range("B" & paygetNum3).Value)) Then
                    Call setpaygetvalue(targetsheet, paygetfilesheet3, targetendRowNo - 2, paygetNum3)
                End If
            Next paygetNum3
        End If
    Next paygetNum
    
    If (paygetfileName(1) <> "") Then
        Application.Workbooks(paygetfileName(1)).Save
        Application.Workbooks(paygetfileName(1)).Close
    End If
    If (paygetfileName(2) <> "") Then
        Application.Workbooks(paygetfileName(2)).Save
        Application.Workbooks(paygetfileName(2)).Close
    End If
    If (paygetfileName(3) <> "") Then
        Application.Workbooks(paygetfileName(3)).Save
        Application.Workbooks(paygetfileName(3)).Close
    End If
    
    '稼動勤務勤怠集計表取込
    kadouFileNameNum = 1
    For Each file In kaDouFolder.Files
        If InStr(file.name, "稼動勤務勤怠集計") > 0 Then
            kadoufileName(kadouFileNameNum) = file.name
            mypath = kadoupath & "\" & file.name
            Workbooks.Open fileName:=mypath
            kadouFileNameNum = kadouFileNameNum + 1
        End If
    Next
        
    targetendRowNo = targetsheet.Cells(Rows.count, 1).End(xlUp).Row
    
    For targetNum = 13 To targetendRowNo
        If (kadoufileName(1) <> "") Then
            Set kadoufilesheet1 = Application.Workbooks(kadoufileName(1)).Worksheets("勤怠集計表")
            endrowno_kadou1 = kadoufilesheet1.Cells(Rows.count, 1).End(xlUp).Row
            kadouNum1 = ""
            For kadouNum1 = 6 To endrowno_kadou1
                If (Trim(targetsheet.Range("C" & targetNum).Value) = Trim(kadoufilesheet1.Range("F" & kadouNum1).Value) _
                And CStr(targetsheet.Range("E" & targetNum).Value) = CStr(kadoufilesheet1.Range("B" & kadouNum1).Value)) Then
                    Call setkadouvalue(targetsheet, kadoufilesheet1, targetNum, kadouNum1)
                End If
            Next kadouNum1
        End If
    
        If (kadoufileName(2) <> "") Then
            Set kadoufilesheet2 = Application.Workbooks(kadoufileName(2)).Worksheets("勤怠集計表")
            endrowno_kadou2 = kadoufilesheet2.Cells(Rows.count, 1).End(xlUp).Row
            kadouNum2 = ""
            For kadouNum2 = 6 To endrowno_kadou2
                If (Trim(targetsheet.Range("C" & targetNum).Value) = Trim(kadoufilesheet2.Range("F" & kadouNum2).Value) _
                And CStr(targetsheet.Range("E" & targetNum).Value) = CStr(kadoufilesheet2.Range("B" & kadouNum2).Value)) Then
                    Call setkadouvalue(targetsheet, kadoufilesheet2, targetNum, kadouNum2)
                End If
            Next kadouNum2
        End If
        
        If (kadoufileName(3) <> "") Then
            Set kadoufilesheet3 = Application.Workbooks(kadoufileName(3)).Worksheets("勤怠集計表")
            endrowno_kadou3 = kadoufilesheet3.Cells(Rows.count, 1).End(xlUp).Row
            kadouNum3 = ""
            For kadouNum3 = 6 To endrowno_kadou3
                If (Trim(targetsheet.Range("C" & targetNum).Value) = Trim(kadoufilesheet3.Range("F" & kadouNum3).Value) _
                And CStr(targetsheet.Range("E" & targetNum).Value) = CStr(kadoufilesheet3.Range("B" & kadouNum3).Value)) Then
                    Call setkadouvalue(targetsheet, kadoufilesheet3, targetNum, kadouNum3)
                End If
            Next kadouNum3
        End If
    Next targetNum
    If (kadoufileName(1) <> "") Then
        Application.Workbooks(kadoufileName(1)).Save
        Application.Workbooks(kadoufileName(1)).Close
    End If
    If (kadoufileName(2) <> "") Then
        Application.Workbooks(kadoufileName(2)).Save
        Application.Workbooks(kadoufileName(2)).Close
    End If
    If (kadoufileName(3) <> "") Then
        Application.Workbooks(kadoufileName(3)).Save
        Application.Workbooks(kadoufileName(3)).Close
    End If
    
    'TSRS帳票管理マスタ取込
    masterNameNum = 1
    For Each file In masterFolder.Files
        If InStr(file.name, "TSRS帳票管理マスタ") > 0 Then
            masterfileName(masterNameNum) = file.name
            mypath = masterdatepath & "\" & file.name
            Workbooks.Open fileName:=mypath
            masterNameNum = masterNameNum + 1
        End If
    Next
    
    'TSRS帳票管理マスタ
    targetendRowNo = targetsheet.Cells(Rows.count, 1).End(xlUp).Row
    
    For targetNum = 13 To targetendRowNo
        If (masterfileName(1) <> "") Then
            Set masterfilesheet1 = Application.Workbooks(masterfileName(1)).Worksheets("帳票マスタ")
            endrowno_master = masterfilesheet1.Cells(Rows.count, 1).End(xlUp).Row
            maseterNum1 = ""
            For maseterNum1 = 4 To endrowno_master
                If (Trim(targetsheet.Range("C" & targetNum).Value) = Trim(masterfilesheet1.Range("B" & maseterNum1).Value) _
                And CStr(targetsheet.Range("E" & targetNum).Value) = CStr(masterfilesheet1.Range("V" & maseterNum1).Value)) Then
                    Call setmastervalue(targetsheet, masterfilesheet1, targetNum, maseterNum1)
                End If
            Next maseterNum1
            End If
    
        If (masterfileName(2) <> "") Then
            Set masterfilesheet2 = Application.Workbooks(masterfileName(2)).Worksheets("帳票マスタ")
            endrowno_master2 = masterfilesheet2.Cells(Rows.count, 1).End(xlUp).Row
            maseterNum2 = ""
            For maseterNum2 = 4 To endrowno_master2
                If (Trim(targetsheet.Range("C" & targetNum).Value) = Trim(masterfilesheet2.Range("B" & maseterNum2).Value) _
                And CStr(targetsheet.Range("E" & targetNum).Value) = CStr(masterfilesheet2.Range("V" & maseterNum2).Value)) Then
                    Call setmastervalue(targetsheet, masterfilesheet2, targetNum, maseterNum2)
                End If
            Next maseterNum2
        End If
        
        If (masterfileName(3) <> "") Then
            Set masterfilesheet3 = Application.Workbooks(masterfileName(3)).Worksheets("帳票マスタ")
            endrowno_master3 = masterfilesheet3.Cells(Rows.count, 1).End(xlUp).Row
            maseterNum3 = ""
            For maseterNum3 = 4 To endrowno_master3
                If (Trim(targetsheet.Range("C" & targetNum).Value) = Trim(masterfilesheet3.Range("B" & maseterNum3).Value) _
                And CStr(targetsheet.Range("E" & targetNum).Value) = CStr(masterfilesheet3.Range("V" & maseterNum3).Value)) Then
                    Call setmastervalue(targetsheet, masterfilesheet3, targetNum, maseterNum3)
                End If
            Next maseterNum3
        End If
    Next targetNum
    If (masterfileName(1) <> "") Then
        Application.Workbooks(masterfileName(1)).Save
        Application.Workbooks(masterfileName(1)).Close
    End If
    If (masterfileName(2) <> "") Then
        Application.Workbooks(masterfileName(2)).Save
        Application.Workbooks(masterfileName(2)).Close
    End If
    If (masterfileName(3) <> "") Then
        Application.Workbooks(masterfileName(3)).Save
        Application.Workbooks(masterfileName(3)).Close
    End If
    
    For i = 1 To 9
        targetsheet.Rows(i).EntireRow.Hidden = True
    Next i
    
    Application.Workbooks(targetFormNm).Save
    Application.Workbooks(targetFormNm).Close
    MsgBox "給与明細比較表を作成しました。"
    
End Sub

'支給控除一覧表取込
Private Sub setpaygetvalue(ByVal targetsheet, ByVal paygetfilesheet, ByVal targetendRowNo, ByVal paygetNum)
    '社員ID
    targetsheet.Range("B" & targetendRowNo).Value = paygetfilesheet.Range("A" & paygetNum).Value
    '従業員名
    targetsheet.Range("C" & targetendRowNo).Value = paygetfilesheet.Range("B" & paygetNum).Value
    '締日
    targetsheet.Range("D" & targetendRowNo).Value = paygetfilesheet.Range("E" & paygetNum).Value
    '支給日
    targetsheet.Range("E" & targetendRowNo).Value = paygetfilesheet.Range("D" & paygetNum).Value
    '支払サイト
    'targetsheet.Range("F" & targetendRowNo).Value = paygetfilesheet.Range("C" & paygetNum).Value
    '基本給
    targetsheet.Range("G" & targetendRowNo).Value = paygetfilesheet.Range("F" & paygetNum).Value
    '役職手当
    targetsheet.Range("H" & targetendRowNo).Value = paygetfilesheet.Range("G" & paygetNum).Value
    '住宅手当
    targetsheet.Range("I" & targetendRowNo).Value = paygetfilesheet.Range("H" & paygetNum).Value
    '扶養手当
    targetsheet.Range("J" & targetendRowNo).Value = paygetfilesheet.Range("I" & paygetNum).Value
    '現場手当
    'targetsheet.Range("K" & targetendRowNo).Value = paygetfilesheet.Range("N" & paygetNum).Value
    targetsheet.Range("K" & targetendRowNo).Value = paygetfilesheet.Range("J" & paygetNum).Value
    '報奨金
    'targetsheet.Range("L" & targetendRowNo).Value = paygetfilesheet.Range("J" & paygetNum).Value
    targetsheet.Range("L" & targetendRowNo).Value = paygetfilesheet.Range("K" & paygetNum).Value
    '調整給
    'targetsheet.Range("M" & targetendRowNo).Value = paygetfilesheet.Range("K" & paygetNum).Value
    targetsheet.Range("M" & targetendRowNo).Value = paygetfilesheet.Range("L" & paygetNum).Value
    '皆勤手当
    'targetsheet.Range("N" & targetendRowNo).Value = paygetfilesheet.Range("L" & paygetNum).Value
    targetsheet.Range("N" & targetendRowNo).Value = paygetfilesheet.Range("M" & paygetNum).Value
    'その他手当
    'targetsheet.Range("O" & targetendRowNo).Value = paygetfilesheet.Range("M" & paygetNum).Value
    targetsheet.Range("O" & targetendRowNo).Value = paygetfilesheet.Range("N" & paygetNum).Value
    '残業手当
    targetsheet.Range("P" & targetendRowNo).Value = paygetfilesheet.Range("O" & paygetNum).Value
    '営業手当
    targetsheet.Range("Q" & targetendRowNo).Value = paygetfilesheet.Range("P" & paygetNum).Value
    'その他支給
    targetsheet.Range("R" & targetendRowNo).Value = paygetfilesheet.Range("Q" & paygetNum).Value
    '立替経費
    targetsheet.Range("S" & targetendRowNo).Value = paygetfilesheet.Range("R" & paygetNum).Value
    '調整額
    targetsheet.Range("T" & targetendRowNo).Value = paygetfilesheet.Range("S" & paygetNum).Value
    '時間外手当
    targetsheet.Range("U" & targetendRowNo).Value = paygetfilesheet.Range("T" & paygetNum).Value
    '勤怠控除
    targetsheet.Range("V" & targetendRowNo).Value = paygetfilesheet.Range("U" & paygetNum).Value
    '通勤手当 (非)
    targetsheet.Range("W" & targetendRowNo).Value = paygetfilesheet.Range("V" & paygetNum).Value
    '(課税支給計)
    targetsheet.Range("X" & targetendRowNo).Value = paygetfilesheet.Range("W" & paygetNum).Value
    '(非税支給計)
    targetsheet.Range("Y" & targetendRowNo).Value = paygetfilesheet.Range("X" & paygetNum).Value
    '(社保対象計)
    targetsheet.Range("Z" & targetendRowNo).Value = paygetfilesheet.Range("Y" & paygetNum).Value
    '実総支給額
    targetsheet.Range("AA" & targetendRowNo).Value = paygetfilesheet.Range("Z" & paygetNum).Value
    '総支給額
    targetsheet.Range("AB" & targetendRowNo).Value = paygetfilesheet.Range("AA" & paygetNum).Value
    '健康保険
    targetsheet.Range("AC" & targetendRowNo).Value = paygetfilesheet.Range("AB" & paygetNum).Value
    '厚生年金保険
    targetsheet.Range("AD" & targetendRowNo).Value = paygetfilesheet.Range("AC" & paygetNum).Value
    '雇用保険
    targetsheet.Range("AE" & targetendRowNo).Value = paygetfilesheet.Range("AD" & paygetNum).Value
    '社会保険合計
    targetsheet.Range("AF" & targetendRowNo).Value = paygetfilesheet.Range("AE" & paygetNum).Value
    '課税対象額
    targetsheet.Range("AG" & targetendRowNo).Value = paygetfilesheet.Range("AF" & paygetNum).Value
    '所得税
    targetsheet.Range("AH" & targetendRowNo).Value = paygetfilesheet.Range("AG" & paygetNum).Value
    '住民税
    targetsheet.Range("AI" & targetendRowNo).Value = paygetfilesheet.Range("AH" & paygetNum).Value
    '総控除額
    targetsheet.Range("AJ" & targetendRowNo).Value = paygetfilesheet.Range("AL" & paygetNum).Value
    '差引支給額
    targetsheet.Range("AK" & targetendRowNo).Value = paygetfilesheet.Range("AN" & paygetNum).Value
    '出勤日数
    targetsheet.Range("AL" & targetendRowNo).Value = paygetfilesheet.Range("AO" & paygetNum).Value
    '欠勤日数
    targetsheet.Range("AM" & targetendRowNo).Value = paygetfilesheet.Range("AP" & paygetNum).Value
    '普通残業時間
    targetsheet.Range("AN" & targetendRowNo).Value = paygetfilesheet.Range("AQ" & paygetNum).Value
    '不就労時間
    targetsheet.Range("AO" & targetendRowNo).Value = paygetfilesheet.Range("AR" & paygetNum).Value
    '勤務時間
    targetsheet.Range("AP" & targetendRowNo).Value = paygetfilesheet.Range("AS" & paygetNum).Value
    '当月有休減
    targetsheet.Range("AQ" & targetendRowNo).Value = paygetfilesheet.Range("AT" & paygetNum).Value
    '当月有休残
    targetsheet.Range("AR" & targetendRowNo).Value = paygetfilesheet.Range("AU" & paygetNum).Value
End Sub

'稼動勤務勤怠集計表取込
Private Sub setkadouvalue(ByVal targetsheet, ByVal naikinfilesheet, ByVal targetendRowNo, ByVal kadouNum)
    '工数
    targetsheet.Range("AS" & targetendRowNo).Value = naikinfilesheet.Range("AF" & kadouNum).Value
    '締日
    targetsheet.Range("AT" & targetendRowNo).Value = naikinfilesheet.Range("C" & kadouNum).Value
    '支払サイト
    targetsheet.Range("AU" & targetendRowNo).Value = naikinfilesheet.Range("D" & kadouNum).Value
    '立替交通費
    targetsheet.Range("AV" & targetendRowNo).Value = naikinfilesheet.Range("AG" & kadouNum).Value
    '立替経費
    targetsheet.Range("AW" & targetendRowNo).Value = naikinfilesheet.Range("AH" & kadouNum).Value
    '残業追加
    targetsheet.Range("AX" & targetendRowNo).Value = naikinfilesheet.Range("AB" & kadouNum).Value
    '勤怠控除
    targetsheet.Range("AY" & targetendRowNo).Value = naikinfilesheet.Range("AE" & kadouNum).Value
End Sub

'TSRS帳票管理マスタ
Private Sub setmastervalue(ByVal targetsheet, ByVal masterdatasheet, ByVal targetendRowNo, ByVal masterNum)
    '支払サイト
    targetsheet.Range("F" & targetendRowNo).Value = masterdatasheet.Range("DI" & masterNum).Value
    '基本給
    targetsheet.Range("AZ" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("AZ" & targetendRowNo).Value = masterdatasheet.Range("CF" & masterNum).Value
    '役職手当
    targetsheet.Range("BA" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BA" & targetendRowNo).Value = masterdatasheet.Range("CY" & masterNum).Value
    '住宅手当
    targetsheet.Range("BB" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BB" & targetendRowNo).Value = masterdatasheet.Range("CG" & masterNum).Value
    '扶養手当
    targetsheet.Range("BC" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BC" & targetendRowNo).Value = masterdatasheet.Range("CZ" & masterNum).Value
    '通信手当
    targetsheet.Range("BD" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BD" & targetendRowNo).Value = masterdatasheet.Range("CH" & masterNum).Value
    '現場手当
    targetsheet.Range("BE" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BE" & targetendRowNo).Value = masterdatasheet.Range("DA" & masterNum).Value
    '当番手当
    targetsheet.Range("BF" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BF" & targetendRowNo).Value = masterdatasheet.Range("DB" & masterNum).Value
    '残業手当
    targetsheet.Range("BG" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BG" & targetendRowNo).Value = masterdatasheet.Range("CJ" & masterNum).Value
    '資格手当
    targetsheet.Range("BH" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BH" & targetendRowNo).Value = masterdatasheet.Range("CI" & masterNum).Value
    'その他手当
    targetsheet.Range("BI" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BI" & targetendRowNo).Value = masterdatasheet.Range("DC" & masterNum).Value
    '通勤手当(非)
    targetsheet.Range("BJ" & targetendRowNo).NumberFormatLocal = "\#,##0"
    targetsheet.Range("BJ" & targetendRowNo).Value = masterdatasheet.Range("M" & masterNum).Value
End Sub

Private Sub CommandButton10_Click()
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox6.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
End Sub

Private Sub CommandButton11_Click()
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox7.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
End Sub

Private Sub CommandButton14_Click()
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox9.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
End Sub
'******************************稼働機能の追加 WuYang 2016/06/29 End******************************

'*****************************作業報告書チェック機能追加 WuYang 20160705 Start**********************************
Private Sub CommandButton13_Click()
    Call FindbookId
End Sub

Private Sub FindbookId()

    Dim workfileName(1 To 200) As String
    Dim fileName(1 To 200) As String
    
    Set targetsheet = Application.Worksheets("Main")
    
    '作業報告書取込パス
    workpath = TextBox1.Text()
    If (Trim(workpath) = "") Then
        MsgBox "作業報告書を取込してください！"
        Exit Sub
    End If
    
    Set Fso = CreateObject("Scripting.FileSystemObject")
    Set workgetFolder = Fso.GetFolder(workpath)
    
    '支給控除一覧表取込
    workFileNameNum = 1
    For Each file In workgetFolder.Files
        If InStr(file.name, "作業報告") > 0 Then
            workfileName(workFileNameNum) = file.name
            fileName(workFileNameNum) = file.name
            mypath = workpath & "\" & file.name
            Workbooks.Open fileName:=mypath
            workFileNameNum = workFileNameNum + 1
        End If
    Next
    
    j = 4
    For i = 1 To workFileNameNum
        If (workfileName(i) <> "") Then
            workfilesheet1 = ""
            Set workfilesheet1 = Application.Workbooks(workfileName(i)).Worksheets("作業報告書")
            z = i + 1
            Do While z < workFileNameNum
                If (workfileName(z) <> "") Then
                    Set workfilesheet2 = Application.Workbooks(workfileName(z)).Worksheets("作業報告書")
                    If (workfilesheet1.Range("AQ" & 4).Value = workfilesheet2.Range("AQ" & 4).Value And StrComp(fileName(i), fileName(z)) <> 0) Then
                        targetsheet.Range("s" & j).Value = workfilesheet1.Range("AQ" & 4).Value
                        targetsheet.Range("t" & j).Value = fileName(i)
                        targetsheet.Range("v" & j).Value = workfilesheet2.Range("AQ" & 4).Value
                        targetsheet.Range("w" & j).Value = fileName(z)
                        j = j + 1
                    End If
                End If
                z = z + 1
             Loop
            
            Application.Workbooks(workfileName(i)).Close
        End If
    Next i
    MsgBox "作業報告書をチェックしました。"

End Sub

'作業報告書テンプレート取込
Private Sub CommandButton18_Click()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox10.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "ファイル読み失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

Private Sub CommandButton17_Click()
     'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\作業報告書テンプレート_メール発送.xls"
    
    '報告書テンプレート取込パス
    Reportpath = TextBox10.Text()
        
    If (Trim(Reportpath) = "") Then
        MsgBox "作業報告書テンプレートを取込してください！"
        Exit Sub
    End If
    
     '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    'ファイル名の作成
    targetFormNm = "【ＴＳＲＳ㈱" & month(Date) & "月分】作業報告書テンプレート_メール発送.xls"
    targetFormName = savePath & "\" & targetFormNm
    
    'ファイルが開くか判断
    For Each wb In Workbooks
        If wb.name = Dir(targetFormName) Then
        MsgBox targetFormName & "はすでに開いています"
        Exit Sub
        End If
    Next wb
    
    If Dir(targetFormName) <> "" Then
        Kill targetFormName
    End If
    FileCopy templatePath, targetFormName
    
    'ファイルを編集
    Set worktargetFile = Workbooks.Open(targetFormName)
    Set targetsheet = Application.Worksheets("送付状_発送履歴")
    Set mastersheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    
    
    Dim Folder As Object
    Dim SubFolder As Object
    Dim file As Object
    Dim targetfileName As String
    Dim targetfilePath As String
    
    Set Fso = CreateObject("Scripting.FileSystemObject")
    Set targetFolder = Fso.GetFolder(Reportpath)
    
    '報告書を取込む
    targetfilenum = 1
    For Each file In targetFolder.Files
        If InStr(file.name, "作業報告書") > 0 Then
            targetfileName = file.name
            fileIDNum1 = InStr(targetfileName, "(")
            fileIDNum2 = InStr(targetfileName, ")")
            fileid = Mid(targetfileName, fileIDNum1 + 1, fileIDNum2 - fileIDNum1 - 1)
            
            fileNameNum1 = InStr(targetfileName, "_")
            fileName = Mid(targetfileName, fileNameNum1 + 1, fileIDNum1 - fileNameNum1 - 1)
            
            targetfilePath = Reportpath & "\" & file.name
            Call settargetValue(targetsheet, mastersheet, fileName, fileid, targetfilePath)
            targetfilenum = targetfilenum + 1
        End If
    Next
    MsgBox "作業報告書の送付状_発送履歴を作成しました。"
    Application.Workbooks(targetFormNm).Save
    Application.Workbooks(targetFormNm).Close
    
End Sub

Private Sub settargetValue(ByVal targetsheet, ByVal masterdatasheet, ByVal fileName, ByVal fileid, ByVal filePath)

    reportDate = DateSerial(Year(Date), month(Date) + 1, 1)
    weekname = yobidate(reportDate)
    
    endRowNo = masterdatasheet.UsedRange.Rows(masterdatasheet.UsedRange.Rows.count).Row
    For i = 4 To endRowNo
        targetendRowNo = targetsheet.Cells(Rows.count, 1).End(xlUp).Row
        If (Trim(masterdatasheet.Range("A" & i).Value) = Trim(fileid) And Trim(masterdatasheet.Range("B" & i).Value) = Trim(fileName)) Then
            If (i <> endRowNo) Then
                targetsheet.Rows(targetendRowNo + 1).Copy
                targetsheet.Rows(targetendRowNo + 2).PasteSpecial Paste:=xlPasteAll
            End If
            targetsheet.Range("A" & targetendRowNo + 1).Value = masterdatasheet.Range("B" & i).Value
            targetsheet.Range("B" & targetendRowNo + 1).Value = "作業報告書"
            targetsheet.Range("C" & targetendRowNo + 1).Value = "【TSRS】" & month(Date) & "月分作業報告書提出のお願い"
            
            content = "お疲れ様です、ＴＳＲソリューションズ購買担当でございます。"
            content = content & vbNewLine
            content = content & vbNewLine & "毎月、作業報告書のご提出にご協力頂き、ありがとうございます。"
            content = content & vbNewLine & "今回の作業報告書提出について、ご連絡いたします。"
            content = content & vbNewLine
            content = content & vbNewLine & "【作業報告書の提出について】"
            content = content & vbNewLine & "①提出期限：" & reportDate & "（" & weekname & "）ＡＭ 10:00"
            content = content & vbNewLine & "※上記期限は厳守をお願いいたします。"
            content = content & vbNewLine & "※遅れる場合は、ご一報をお願いいたします。(メールor電話でお願いします)"
            content = content & vbNewLine
            content = content & vbNewLine & "②提出いただきたい書類（２点）"
            content = content & vbNewLine & "（1）お客様向け作業報告書"
            content = content & vbNewLine & "（2）ＴＳＲＳ社内用作業報告書"
            content = content & vbNewLine & "※上記２点をメールまたはＦＡＸにてご提出下さい。（現場によりＴＳＲＳ書式のみの提出）"
            content = content & vbNewLine & "※通勤以外の外出交通費が発生した方は、合わせてご提出ください。（現場書式がある方は、現場書式もご提出下さい。）"
            content = content & vbNewLine & "※ＴＳＲＳの作業報告書及び交通費申請書雛形は、添付ファイルをご参照・ご利用ください。"
            content = content & vbNewLine
            content = content & vbNewLine & "③書類の提出先"
            content = content & vbNewLine & "  ＜メールにて提出の場合＞"
            content = content & vbNewLine & "　　件名【TSRS】" & month(Date) & "月作業報告書提出分"
            content = content & vbNewLine & "　　TO：購買（report@tsrs.co.jp）"
            content = content & vbNewLine & "　　CC：戸川（koichi.togawa@tsrs.co.jp）"
            content = content & vbNewLine & "    CC：佐藤（hikaru.sato@tsrs.co.jp）"
            content = content & vbNewLine
            content = content & vbNewLine
            content = content & vbNewLine & "　＜ＦＡＸにて提出の場合＞"
            content = content & vbNewLine & "　　03-5937-5379"
            content = content & vbNewLine
            content = content & vbNewLine & "以上、ご提出の程、宜しくお願い申し上げます。"
            content = content & vbNewLine
            content = content & vbNewLine & "＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊"
            content = content & vbNewLine & " ＴＳＲソリューションズ株式会社"
            content = content & vbNewLine & " 〒169-0075"
            content = content & vbNewLine & " 　東京都新宿区高田馬場三丁目23番3号ＯＲビル3-4階"
            content = content & vbNewLine & " TEL：03-5937-5378　FAX：03-5937-5379"
            content = content & vbNewLine & " URL: http://www.tsrs.co.jp"
            content = content & vbNewLine & "＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊"
                        
            targetsheet.Range("D" & targetendRowNo + 1).Value = content
            targetsheet.Range("E" & targetendRowNo + 1).Value = filePath
            targetsheet.Range("F" & targetendRowNo + 1).Value = masterdatasheet.Range("AO" & i).Value
            targetsheet.Range("G" & targetendRowNo + 1).Value = masterdatasheet.Range("EQ" & i).Value
            targetsheet.Range("H" & targetendRowNo + 1).Value = "report@tsrs.co.jp"
            Exit Sub
        End If
    Next i
    
End Sub

Private Function yobidate(ByVal reportData)
    If IsDate(reportData) Then
        Select Case Weekday(reportData)
        Case vbSunday
            yobidate = "日"
        Case vbMonday
            yobidate = "月"
        Case vbTuesday
            yobidate = "火"
        Case vbWednesday
            yobidate = "水"
        Case vbThursday
            yobidate = "木"
        Case vbFriday
            yobidate = "金"
        Case vbSaturday
            yobidate = "土"
        End Select
    End If
End Function


'給与明細表取込
Private Sub CommandButton20_Click()
On Error GoTo Err_Handle
    Dim Fso As Object, sFileType$, strPath$, i&, j&, lc&, arrf$(), mf&, arr, brr()
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox11.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    
    Exit Sub
Err_Handle:
    'エラーメッセージを表示
    MsgBox "ファイル読み失敗!"
    
    'Excelを閉じる
    Dim wb As Workbook
    For Each wb In Workbooks
        If wb.name = Common.vbaFileNm Then
            wb.Close False
            Exit For
        End If
    Next
End Sub

Private Sub CommandButton19_Click()

    'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\給与支給明細書_メール発送.xls"
    '給与支給明細書取込パス
    Paytpath = TextBox11.Text()
    PayPassw = TextBox12.Text()
        
    If (Trim(Paytpath) = "") Then
        MsgBox "給与支給明細書を取込ください！"
        Exit Sub
    End If
    
    If (Trim(PayPassw) = "") Then
        MsgBox "給与支給明細書圧縮用パスワードを入力ください！"
        Exit Sub
    End If
    
     '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
    targetFd = savePath & "\" & "【TSRS㈱" & month(Date) - 1 & "月分】" & "支給明細"
    If Dir(targetFd, vbDirectory) = "" Then
        MkDir targetFd
    End If
            
    'ファイル名の作成
    targetFormNm = "【ＴＳＲＳ㈱" & month(Date) - 1 & "月分】給与支給明細書_メール発送.xls"
    targetFormName = targetFd & "\" & targetFormNm
    
    'ファイルが開くか判断
    For Each wb In Workbooks
        If wb.name = Dir(targetFormName) Then
        MsgBox targetFormName & "はすでに開いています"
        Exit Sub
        End If
    Next wb
    
    If Dir(targetFormName) <> "" Then
        Kill targetFormName
    End If
    FileCopy templatePath, targetFormName
    
    'ファイルを編集
    Set worktargetFile = Workbooks.Open(targetFormName)
    Set targetsheet = Application.Worksheets("送付状_発送履歴")
    
    Set Fso = CreateObject("Scripting.FileSystemObject")
    Set targetFolder = Fso.GetFolder(Paytpath)
    
    '給与支給明細書を取込む
    For Each file In targetFolder.Files
        If InStr(file.name, "給与支給明細書") > 0 Then
            targetfileName = file.name
            If (InStr(targetfileName, "（")) Then
                fileNameNum1 = InStr(targetfileName, "（")
                fileNameNum2 = InStr(targetfileName, "）")
            End If
            If (InStr(targetfileName, "(")) Then
                fileNameNum1 = InStr(targetfileName, "(")
                fileNameNum2 = InStr(targetfileName, ")")
            End If
            
            employeename = Mid(targetfileName, fileNameNum1 + 1, fileNameNum2 - fileNameNum1 - 1)
            If InStr(employeename, "　") <> 0 Then
            employeename = Replace(employeename, "　", "")
            End If
            If InStr(employeename, " ") <> 0 Then
                employeename = Replace(employeename, " ", "")
            End If
            zipFileName = Left(targetfileName, Len(targetfileName) - 4)
            '給与支給明細書フォルダの作成
            zipFordr = targetFd & "\" & zipFileName
            If Dir(zipFordr, vbDirectory) = "" Then
                MkDir zipFordr
            End If
            app = Fso.copyfile(Paytpath & "\" & targetfileName, zipFordr & "\", True)
            Call TargetFileZip(zipFordr, PayPassw)
            Call setPayValue(targetsheet, employeename, zipFordr & "\" & zipFileName & ".zip", PayPassw)
        End If
    Next
    
    MsgBox "給与支給明細書のメール送信を編集しました。"
    Application.Workbooks(targetFormNm).Save
    Application.Workbooks(targetFormNm).Close
End Sub

Private Sub setPayValue(ByVal targetsheet, ByVal employeename, ByVal fileName, ByVal PayPassw)

    Set masterdatasheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    endRowNo = masterdatasheet.UsedRange.Rows(masterdatasheet.UsedRange.Rows.count).Row
    For i = 4 To endRowNo
        targetendRowNo = targetsheet.Cells(Rows.count, 1).End(xlUp).Row
        memberName = masterdatasheet.Range("B" & i).Value
        If InStr(memberName, "　") <> 0 Then
            memberName = Replace(memberName, "　", "")
        End If
        If InStr(memberName, " ") <> 0 Then
            memberName = Replace(memberName, " ", "")
        End If
        If Trim(memberName) = Trim(employeename) Then
            targetsheet.Range("A" & targetendRowNo + 1).Value = masterdatasheet.Range("B" & i).Value
            targetsheet.Range("B" & targetendRowNo + 1).Value = ""
            targetsheet.Range("C" & targetendRowNo + 1).Value = "【TSRS】" & month(Date) - 1 & "月度給与明細"
            content = masterdatasheet.Range("B" & i).Value & "様"
            content = content & vbNewLine
            content = content & vbNewLine & "お疲れ様です、財務経理部です。"
            content = content & vbNewLine
            content = content & vbNewLine & month(Date) - 1 & "月度給与明細を添付いたします。"
            content = content & vbNewLine & "※パスワードは別途送付させていただきます。"
            content = content & vbNewLine
            content = content & vbNewLine
            content = content & vbNewLine & "何卒、よろしくお願いいたします。"
            content = content & vbNewLine
            content = content & vbNewLine
            content = content & vbNewLine
            content = content & vbNewLine & "━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
            content = content & vbNewLine & "ＴＳＲソリューションズ株式会社【 TSR Solutions,. Inc. 】"
            content = content & vbNewLine & "財務経理部"
            content = content & vbNewLine & "────────────────────────────"
            content = content & vbNewLine & "〒169-0075"
            content = content & vbNewLine & "東京都新宿区高田馬場3-23-3   ＯＲビル3-4F"
            content = content & vbNewLine & "TEL. 03-5937-5378（代表）  　FAX.  03-5937-5379"
            content = content & vbNewLine & "URL. http://www.tsrs.co.jp/　MAIL. report@tsrs.co.jp"
            content = content & vbNewLine & ""
            content = content & vbNewLine & "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓"
            content = content & vbNewLine & " このメール及び添付ファイルに含まれる情報は機密内容"
            content = content & vbNewLine & " を含んでいる場合があり、意図された受取人以外の方によ"
            content = content & vbNewLine & " るこれら情報の無断開示、複製、再配布や転送など一切の"
            content = content & vbNewLine & " 利用が禁止されています。万一この電子メールを誤って受"
            content = content & vbNewLine & " け取られた場合、誠に恐縮ですが、送信者にご連絡頂き、"
            content = content & vbNewLine & " 直ちに本メールを破棄して頂きますようお願い申し上げます。"
            content = content & vbNewLine & " This e-mail is confidential and intended for the"
            content = content & vbNewLine & " named  recipient only.If you are not an intended"
            content = content & vbNewLine & " recipient,  please notify the sender and delete"
            content = content & vbNewLine & " this e-mail."
            content = content & vbNewLine & "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛"
            targetsheet.Range("D" & targetendRowNo + 1).Value = content
            targetsheet.Range("E" & targetendRowNo + 1).Value = fileName
            targetsheet.Range("F" & targetendRowNo + 1).Value = "吉田"
            targetsheet.Range("G" & targetendRowNo + 1).Value = masterdatasheet.Range("EQ" & i).Value
            targetsheet.Range("H" & targetendRowNo + 1).Value = "jinji@tsrs.co.jp"
            
            targetsheet.Range("A" & targetendRowNo + 2).Value = masterdatasheet.Range("B" & i).Value
            targetsheet.Range("B" & targetendRowNo + 2).Value = ""
            targetsheet.Range("C" & targetendRowNo + 2).Value = "【TSRS】" & month(Date) - 1 & "月度給与明細"
            content = masterdatasheet.Range("B" & i).Value & "様"
            content = content & vbNewLine
            content = content & vbNewLine & "お疲れ様です、財務経理部です。"
            content = content & vbNewLine
            content = content & vbNewLine & "先ほどのパスワード:" & PayPassw
            content = content & vbNewLine
            content = content & vbNewLine & "以上、宜しくお願い致します。"
            content = content & vbNewLine
            content = content & vbNewLine
            content = content & vbNewLine & "━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
            content = content & vbNewLine & "ＴＳＲソリューションズ株式会社【 TSR Solutions,. Inc. 】"
            content = content & vbNewLine & "財務経理部"
            content = content & vbNewLine & "────────────────────────────"
            content = content & vbNewLine & "〒169-0075"
            content = content & vbNewLine & "東京都新宿区高田馬場3-23-3   ＯＲビル3-4F"
            content = content & vbNewLine & "TEL. 03-5937-5378（代表）  　FAX.  03-5937-5379"
            content = content & vbNewLine & "URL. http://www.tsrs.co.jp/　MAIL. report@tsrs.co.jp"
            content = content & vbNewLine & ""
            content = content & vbNewLine & "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓"
            content = content & vbNewLine & " このメール及び添付ファイルに含まれる情報は機密内容"
            content = content & vbNewLine & " を含んでいる場合があり、意図された受取人以外の方によ"
            content = content & vbNewLine & " るこれら情報の無断開示、複製、再配布や転送など一切の"
            content = content & vbNewLine & " 利用が禁止されています。万一この電子メールを誤って受"
            content = content & vbNewLine & " け取られた場合、誠に恐縮ですが、送信者にご連絡頂き、"
            content = content & vbNewLine & " 直ちに本メールを破棄して頂きますようお願い申し上げます。"
            content = content & vbNewLine & " This e-mail is confidential and intended for the"
            content = content & vbNewLine & " named  recipient only.If you are not an intended"
            content = content & vbNewLine & " recipient,  please notify the sender and delete"
            content = content & vbNewLine & " this e-mail."
            content = content & vbNewLine & "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛"
            targetsheet.Range("D" & targetendRowNo + 2).Value = content
            targetsheet.Range("E" & targetendRowNo + 2).Value = ""
            targetsheet.Range("F" & targetendRowNo + 2).Value = "吉田"
            targetsheet.Range("G" & targetendRowNo + 2).Value = masterdatasheet.Range("EQ" & i).Value
            targetsheet.Range("H" & targetendRowNo + 2).Value = "jinji@tsrs.co.jp"
           Exit Sub
        End If
    Next i
    
End Sub

Private Sub CommandButton15_Click()
    
    'テンプレートパス
    templatePath = Application.ActiveWorkbook.path & "\テンプレート\受発注必要テンプレート\【TSRS㈱】人事-社員名簿.xls"
    '社員名簿「member_list.csv」取込パス
    memberfile = TextBox8.Text()
        
    If (Trim(memberfile) = "") Then
        MsgBox "社員名簿「member_list.csv」を取込ください！"
        Exit Sub
    End If
    
     '指定保存路径
    With Application.FileDialog(msoFileDialogFolderPicker)
        .AllowMultiSelect = True
        If .Show Then
            savePath = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
            
    'ファイル名の作成
    targetFormNm = "【TSRS㈱" & month(Date) & "月】人事-社員名簿.xls"
    targetFormName = savePath & "\" & targetFormNm
    
    'ファイルが開くか判断
    For Each wb In Workbooks
        If wb.name = Dir(targetFormName) Then
        MsgBox targetFormName & "はすでに開いています"
        Exit Sub
        End If
    Next wb
    
    If Dir(targetFormName) <> "" Then
        Kill targetFormName
    End If
    FileCopy templatePath, targetFormName
    
    'ファイルを編集
    Set worktargetFile = Workbooks.Open(targetFormName)
    Set targetsheet = Application.Worksheets("労働者名簿（対象月-月次）")

    Set baseFile = Workbooks.Open(memberfile)
    
    Set MemberSheet = Application.Workbooks(baseFile.name).Worksheets("member_list")
    endRowNo = MemberSheet.UsedRange.Rows(MemberSheet.UsedRange.Rows.count).Row
    
    Set masterdatasheet = Application.Workbooks("月次処理マクロ.xls").Worksheets("master")
    masterendRowNo = masterdatasheet.UsedRange.Rows(masterdatasheet.UsedRange.Rows.count).Row
    n = 3
    For i = 2 To endRowNo
        For j = 4 To masterendRowNo
            If (Trim(MemberSheet.Range("B" & i).Value) = Trim(masterdatasheet.Range("B" & j))) Then
                '社員No.
                targetsheet.Range("A" & n).Value = masterdatasheet.Range("A" & j).Value
                'マイナンバー
                targetsheet.Range("B" & n).Value = masterdatasheet.Range("Y" & j).Value
                '氏名
                targetsheet.Range("C" & n).Value = MemberSheet.Range("B" & i).Value
                'フリガナ
                targetsheet.Range("D" & n).Value = MemberSheet.Range("C" & i).Value
                '性別
                targetsheet.Range("E" & n).Value = masterdatasheet.Range("CB" & j).Value
                '生年月日
                targetsheet.Range("F" & n).Value = masterdatasheet.Range("CC" & j).Value
                '所属
                targetsheet.Range("G" & n).Value = masterdatasheet.Range("EO" & j).Value
                '役職
                targetsheet.Range("H" & n).Value = masterdatasheet.Range("EP" & j).Value
                '〒
                targetsheet.Range("I" & n).Value = masterdatasheet.Range("BV" & j).Value
                '住所１
                targetsheet.Range("J" & n).Value = masterdatasheet.Range("BU" & j).Value
                '住所２
                targetsheet.Range("K" & n).Value = ""
                'TEL
                targetsheet.Range("L" & n).Value = masterdatasheet.Range("BW" & j).Value
                '携帯電話番号
                targetsheet.Range("M" & n).Value = masterdatasheet.Range("O" & j).Value
                'メールアドレス
                targetsheet.Range("N" & n).Value = masterdatasheet.Range("R" & j).Value
                '入社年月日
                targetsheet.Range("O" & n).Value = masterdatasheet.Range("U" & j).Value
                '退社年月日
                targetsheet.Range("P" & n).Value = masterdatasheet.Range("DV" & j).Value
                '雇用保険加入日
                targetsheet.Range("Q" & n).Value = ""
                '社会保険加入日
                targetsheet.Range("R" & n).Value = ""
                'ベネフィット・ワン入会日
                targetsheet.Range("S" & n).Value = MemberSheet.Range("F" & i).Value
                'ベネフィット・ワン退会日
                targetsheet.Range("T" & n).Value = masterdatasheet.Range("DV" & j).Value
                n = n + 1
            End If
        Next j
    Next i
    MsgBox "社員名簿を作成しました。"
    Application.Workbooks(targetFormNm).Save
    Application.Workbooks(targetFormNm).Close
    baseFile.Close
End Sub

Private Sub CommandButton16_Click()
    With Application.FileDialog(msoFileDialogFilePicker)
        .AllowMultiSelect = True
        If .Show Then
            TextBox8.Text = .SelectedItems(1)
        Else
            Exit Sub
        End If
    End With
End Sub

Private Sub CommandButton21_Click()

    Dim filePath As String
    Dim PrinterO As String
    Dim STDprinter As String
    
    PrinterO = ComboBox1.Value
    filePath = TextBox13.Value
    
    If (Trim(PrinterO) = "") Then
        MsgBox "プリンターを選択ください"
        Exit Sub
    End If
    If (Trim(filePath) = "") Then
        MsgBox "印刷したいファイルを取込ください"
        Exit Sub
    End If
    
    'プリンターの設定
    STDprinter = Application.ActivePrinter
    Set net = CreateObject("WScript.Network")
    net.SetDefaultPrinter PrinterO
    'Main処理
    Call getAllFile(filePath)
    
    MsgBox "ファイルを印刷しました。"
    
End Sub

Sub getAllFile(sFolderPath As String)
    On Error Resume Next
    Dim f As String
    Dim file() As String
    Dim i, k, x
    x = 1
    i = 1
    k = 1
      
    ReDim file(1 To i)
    file(1) = sFolderPath & "\"
      
    Do Until i > k
        f = Dir(file(i), vbDirectory)
            Do Until f = ""
                If InStr(f, ".") = 0 Then
                    k = k + 1
                    ReDim Preserve file(1 To k)
                    file(k) = file(i) & f & "\"
                End If
                f = Dir
            Loop
        i = i + 1
    Loop
    '各フォルダのファイルを取込み
    For i = 1 To k
        f = Dir(file(i) & "*.xls")
        Do Until f = ""
            If (InStr(f, "書類送付状") <> 0 And InStr(f, "請求書") <> 0 And InStr(f, "作業報告") <> 0) Then
                PrintFilePath = file(i) & f
                Set PrintFile = Workbooks.Open(PrintFilePath)
                Set workPrintSheet = Application.Workbooks(f).Worksheets("書類送付状")
                workPrintSheet.PrintOut
                Application.Workbooks(f).Close
            ElseIf (InStr(f, "作業報告") <> 0 And InStr(f, "書類送付状") = 0) Then
                PrintFilePath = file(i) & f
                Set PrintFile = Workbooks.Open(PrintFilePath)
                Set workPrintSheet = Application.Workbooks(f).Worksheets("作業報告書")
                workPrintSheet.PrintOut
                Application.Workbooks(f).Close
            
            ElseIf (InStr(f, "請求書") <> 0 And InStr(f, "書類送付状") = 0) Then
                PrintFilePath = file(i) & f
                Set PrintFile = Workbooks.Open(PrintFilePath)
                Set workPrintSheet = Application.Workbooks(f).Worksheets("請求書")
                workPrintSheet.PrintOut
                Application.Workbooks(f).Close
            End If
            f = Dir
        Loop
    Next
End Sub
'*****************************作業報告書チェック機能追加 WuYang 20160705 End**********************************
Private Sub TextBox2_Change()

End Sub
