/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist.impl;

import com.google.gson.Gson;
import java.io.File;
import org.apache.commons.io.FileUtils;
import rocks.imsofa.pagedrecordlist.Head;
import rocks.imsofa.pagedrecordlist.Page;

/**
 *
 * @author lendle
 */
public class Loader {
    public static Page loadPage(File rootFolder, long pageIndex) throws Exception{
        File file=new File(rootFolder, ""+pageIndex+".page");
        String str=FileUtils.readFileToString(file, "utf-8");
        Gson gson=new Gson();
        Page page=gson.fromJson(str, Page.class);
        return page;
    }
    
    public static Head loadHead(File rootFolder) throws Exception{
        File file=new File(rootFolder, "head");
        String str=FileUtils.readFileToString(file, "utf-8");
        Gson gson=new Gson();
        Head head=gson.fromJson(str, Head.class);
        return head;
    }
    /**
     * 
     * @param rootFolder
     * @return the page index of the last page; if return -1, there is no saved page at all
     * @throws Exception 
     */
    public static long getLastPageIndex(File rootFolder) throws Exception{
        long maxPageIndex=-1;
        for(File file : rootFolder.listFiles()){
            String name=file.getName().toLowerCase();
            if(name.endsWith(".page")){
                long pageIndex=Long.valueOf(name.substring(0, name.indexOf(".")));
                maxPageIndex=Math.max(maxPageIndex, pageIndex);
            }
        }
        return maxPageIndex;
    }
}
