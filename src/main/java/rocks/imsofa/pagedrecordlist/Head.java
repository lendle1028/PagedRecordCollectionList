/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist;

import java.util.List;

/**
 *
 * @author lendle
 */
public class Head {
    private long pageSize=-1;
    private long length=0;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
    
    
    
    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
