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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        File file = new File("testListHome");
        File testRootFolder = new File(file, "test1");
//        PagedRecordCollectionList list = PagedRecordCollectionList.newInstance(testRootFolder);
//        for (int i = 0; i < 35000; i++) {
//            RecordEntry entry = new RecordEntry();
//            entry.setValue("value1", i);
//            entry.setValue("value2", "value2");
//            list.add(entry);
//        }
//        list.save();
        //reload
        PagedRecordCollectionList list = PagedRecordCollectionList.newInstance(testRootFolder);
        for(int i=0; i<list.size(); i++){
            System.out.println("i="+i+":"+list.size());
            RecordEntry entry=list.get(i);
            System.out.println(entry.getValue("value1"));
        }
        System.out.println("1");
    }

}
