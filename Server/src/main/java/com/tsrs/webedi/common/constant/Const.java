package com.tsrs.webedi.common.constant;

import java.math.BigDecimal;

/**
 * 系统常量
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月12日 下午9:42:53
 */
public interface Const {

    /**
     * 系统默认的管理员パスワード
     */
    String DEFAULT_PWD = "111111";
   
    /**
     * 管理员役割的名字
     */
    String ADMIN_NAME = "administrator";

    /**
     * 管理员id
     */
    Integer ADMIN_ID = 1;

    /**
     * 超级管理员役割id
     */
    Integer ADMIN_ROLE_ID = 1;

    /**
     * 接口文档的菜单名
     */
    String API_MENU_NAME = "接口文档";
    
    
    /**
     * アップロードファイル格納用のフォルダ名称
     */
    String UL_FILE_FOLD = "data";
    
    /**
     * システムコード
     */
    String SYS_CD = "webedi";
    
    /**
     * メニューComponent
     */
    String MENU_COMPONENT = "Layout";
    
    /**
     * 使用FLG Y:使用 N:削除
     */
    interface USE_FLG{   	
    	String VALID = "Y";
    	String INVALID = "N";
    };
    
    /**
     *  稟議書状態
     *  1：申請待    稟議書データ登録直後
     *  2：承認待    稟議書データ承認WFを申請後　※申請後、承認中前、申請取消が可能
     *  3：承認中    稟議書承認WFがスタート後
     *  4：承認済    承認経路の承認者がすべて承認済
     *  5：契約済    受注先と合意済の後
     *  A:取消     ユーザー取消（画面上で検索可能）
     *
     */
    interface APPR_DOC_ST{
    	String APPLY_WAIT = "1";
    	String CONFIRM_WAIT = "2";
    	String CONFIRM_RUNNING = "3";
    	String CONFIRM_FINISH = "4";
    	String CONTRACT_FINISH = "5";
    	String CANCEL = "A";
    }
    
    /**
     * 機能ID
     *
     */
    interface PG{
    	//デフォルト機能
    	String DEF_PG = "Unknow";
    	//稟議書
    	String APPR_DOC = "ApprDoc";
    	//ロール
    	String ROLE = "Role";
    	//見積
    	String EST = "Est";
    	//受注
    	String ORDER = "Order";
    	//月次請求
    	String MON_REQ = "MonReq";
    }
    
    /**
     * 画面ページ
     *
     */
    interface PAGE{
    	String ENT = "Ent";
    	String COPY = "Cpy";
    	String UPD = "Upd";
    	String INQ= "Inq";
    	String LIST= "Lst";
    	String DETAIL= "Dtl";
    	String DEL = "Del";
    	String APPLY = "Apl";
    	String CONFIRM = "Con";
    }
    
    interface COMPANY_CATE{
    	String COMP = "3";	//会社カテゴリー
    	String BP = "4";	//BP社
    	String END = "5";	//エンドー→上位→TSRS㈱
    	String SI = "6";	//SI屋
    	String JIET = "7";	//JIET
    }
    
    //承認ステータス
    interface CONFIRM_ST{  	
    	String APPLY_FINISH = "0";	//0：申請済（申請者はWFの一つ目に表示する）
    	String CONFIRM_WAIT = "1";	//1：未承認
    	String APPROVAL = "2";	//2：同意
    	String REJECT = "3";	//3：拒否
    }
    
    //社会保険タイプ
    interface INSUR_TYPE{
    	String HEALTH_INSURANCE = "1";	//1：健康保険
    	String EMPLOYE_EPENSION = "2";	//2：厚生年金	
    }
    
    //精算条件
    interface ACC_PAT_K{
    	//1：上下割
    	String MAX_MIN = "1";
    	//2：中割
    	String MID = "2";
    	//3：固定
    	String FIXED = "3";
    }
    
    //雇用保険
    BigDecimal EMPLOYEE_INSURANCE = new BigDecimal(9000);

}
