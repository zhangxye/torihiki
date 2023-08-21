package com.tsrs.webedi.common.page;

import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 * @author Tsrs-WebEDI
 * @Date 2017年1月22日 下午11:06:41
 */
public class PageInfo<T> {

    // 总结果集
    private List<T> rows;

    // レコード数
    private int total;
    
    // 
    private int pageNo;
    
    // 
    private int pageSize;
    
    private List<T> pageData;
    

    public PageInfo(List<T> page,int pageNo,int pageRecord) {
        this.rows = page;
        this.total = page.size();
        this.pageNo = pageNo;
        this.pageSize = pageRecord;
        if (total <=pageRecord) {
        	pageData = page;
        } else {
			int numPage = pageNo*pageRecord;
			if (numPage > total) {
				pageData =  page.subList(numPage - pageRecord, total);
			} else {
				pageData =  page.subList(numPage - pageRecord, numPage);
			}
        }
    }
    public PageInfo(List<T> pageData,int pageNo, int pageRecord,int total) {
    	 this.rows = pageData;
         this.total = total;
         this.pageNo = pageNo;
         this.pageSize = pageRecord;
         this.pageData = pageData;
    }
    public List<T> getRows() {
        return rows;
    }

  
    public int getTotal() {
        return total;
    }
	
	public int getPageNo() {
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public List<T> getPageData() {
		return pageData;
	}

    
}
