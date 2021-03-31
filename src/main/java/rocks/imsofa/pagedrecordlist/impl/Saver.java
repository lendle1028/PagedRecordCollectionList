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
public class Saver {
    public static void saveHead(File rootFolder, Head head) throws Exception{
        File file=new File(rootFolder, "head");
        Gson gson=new Gson();
        String str=gson.toJson(head);
        FileUtils.write(file, str, "utf-8");
    }
    
    public static void savePage(File rootFolder, Page page) throws Exception{
        File file=new File(rootFolder, ""+page.getPageIndex()+".page");
        Gson gson=new Gson();
        String str=gson.toJson(page);
        FileUtils.write(file, str, "utf-8");
    }
}
