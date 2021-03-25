/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist;

import java.io.File;

/**
 *
 * @author lendle
 */
public interface PagedRecordCollectionList {
    public void save() throws Exception;
    public void save(File folder) throws Exception;
    public int size();
}
