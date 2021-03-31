/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lendle
 */
public class Page {
    private int pageIndex=-1;
    private List<RecordEntry> recordEntries=new ArrayList<>();

    public Page(int index) {
        this.pageIndex=index;
    }

    public Page() {
    }
    
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<RecordEntry> getRecordEntries() {
        return recordEntries;
    }

    public void setRecordEntries(List<RecordEntry> recordEntries) {
        this.recordEntries = recordEntries;
    }
    
    
}
