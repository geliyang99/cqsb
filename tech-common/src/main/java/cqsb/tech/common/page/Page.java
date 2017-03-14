package cqsb.tech.common.page;  
  
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
  
public class Page<T> {  
    private List<?> result; // 结果集  
    private int pageSize; // 页大小  
    private int startPage; // 起始页 从1开始  
    private ScrollableResults scrollableResults;  
    private int totalResults; // 总记录的条数  
    private int totalPages; // 总页数  
  
    public Page(int startPage, int pageSize, Query query) {  
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.result = null;
        try {  
            this.scrollableResults = query.scroll();
            if (scrollableResults.last() && scrollableResults.getRowNumber() >= 0) {
                this.totalResults = this.scrollableResults.getRowNumber() + 1;
            } else {
                this.totalResults = 0;
            }
            setTotalPages();
            result = query.setFirstResult(
                    (this.getStartPage() - 1) * this.pageSize).setMaxResults(
                    this.pageSize).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public Page() {
    	
	}
    /** 
     * 查询结果 
     * @return 查询结果 
     */  
    public List<?> getResult() {
        return result;
    }
  
    /** 
     * 起始页 
     * @return 
     */  
    public int getStartPage() {
        if (startPage < 1) {
            startPage = 1;
        }
        if (startPage > totalPages) {
            startPage = totalPages;
        }
        return startPage;
    }
  
    /**
     * 设置总页数
     */
    private void setTotalPages() {
        this.totalPages = this.totalResults / this.pageSize;
        if (totalPages * pageSize < totalResults) {
            totalPages++;
        }
    }
}