/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist;

import java.io.File;
import rocks.imsofa.pagedrecordlist.impl.DefaultPagedRecordCollectionListImpl;

/**
 *
 * @author lendle
 */
public interface PagedRecordCollectionList {
    public void save() throws Exception;
    public void save(File folder) throws Exception;
    public long size();
    public RecordEntry get(int index) throws Exception;
    public Head getHead();
    public File getFolder();
    public void add(RecordEntry entry) throws Exception;
    
    public static PagedRecordCollectionList newInstance(File rootFolder) throws Exception{
        if(!rootFolder.exists() || !rootFolder.isDirectory()){
            rootFolder.mkdirs();
        }
        return new DefaultPagedRecordCollectionListImpl(rootFolder);
    }
}
